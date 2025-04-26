package com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Controller;

import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeader;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderRequest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import-receipt")
public class ImportReceiptHeaderController {

    private final JdbcTemplate jdbcTemplate;
    private final ImportActivityLogService importActivityLogService;

    public ImportReceiptHeaderController(
        JdbcTemplate jdbcTemplate,
        ImportActivityLogService importActivityLogService
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.importActivityLogService = importActivityLogService;
    }

    @GetMapping("/get-all")
    public ImportReceiptHeader[] getAllReceipts() {
        String sql = "SELECT * FROM ImportReceiptHeader";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ImportReceiptHeader(
                rs.getInt("ReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getString("ASNNumber"),
                rs.getString("PONumber"),
                rs.getInt("OriginLocationID"),
                rs.getDate("ExpectedArrivalDate"),
                rs.getDate("ActualArrivalDate"),
                rs.getString("ReceivingDock"),
                rs.getString("ReceiptStatus"),
                rs.getString("Notes"),
                rs.getInt("CarrierID"),
                rs.getInt("SupplierID"),
                rs.getInt("WarehouseID"),
                rs.getInt("CreatedBy"),
                rs.getTimestamp("CreatedDate")
            )
        ).toArray(ImportReceiptHeader[]::new);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ImportReceiptHeader> getReceiptById(@PathVariable int id) {
        String sql = "SELECT * FROM ImportReceiptHeader WHERE ReceiptID = ?";

        try {
            ImportReceiptHeader receipt = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new ImportReceiptHeader(
                    rs.getInt("ReceiptID"),
                    rs.getString("ReceiptNumber"),
                    rs.getString("ASNNumber"),
                    rs.getString("PONumber"),
                    rs.getObject("OriginLocationID", Integer.class),
                    rs.getDate("ExpectedArrivalDate"),
                    rs.getDate("ActualArrivalDate"),
                    rs.getString("ReceivingDock"),
                    rs.getString("ReceiptStatus"),
                    rs.getString("Notes"),
                    rs.getObject("CarrierID", Integer.class),
                    rs.getObject("SupplierID", Integer.class),
                    rs.getInt("WarehouseID"),
                    rs.getObject("CreatedBy", Integer.class),
                    rs.getTimestamp("CreatedDate")
                )
            );
            return ResponseEntity.ok(receipt);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ImportReceiptHeader> createReceipt(@RequestBody ImportReceiptHeaderRequest request) {
        String sql = """
            INSERT INTO ImportReceiptHeader (
                ReceiptNumber, ASNNumber, PONumber, OriginLocationID, ExpectedArrivalDate,
                ActualArrivalDate, ReceivingDock, ReceiptStatus, Notes,
                CarrierID, SupplierID, WarehouseID, CreatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.receiptNumber(),
            request.asnNumber(),
            request.poNumber(),
            request.originLocationID(),
            request.expectedArrivalDate(),
            request.actualArrivalDate(),
            request.receivingDock(),
            request.receiptStatus(),
            request.notes(),
            request.carrierID(),
            request.supplierID(),
            request.warehouseID(),
            request.createdBy()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ImportReceiptHeader receipt = new ImportReceiptHeader(
            id,
            request.receiptNumber(),
            request.asnNumber(),
            request.poNumber(),
            request.originLocationID(),
            request.expectedArrivalDate(),
            request.actualArrivalDate(),
            request.receivingDock(),
            request.receiptStatus(),
            request.notes(),
            request.carrierID(),
            request.supplierID(),
            request.warehouseID(),
            request.createdBy(),
            now
        );
        
        // Log creation activity
        importActivityLogService.log(
            receipt.receiptID(),
            receipt.createdBy(),
            ImportActivityTargetType.RECEIPT.value(),
            ImportActivityActionType.CREATE.value(),
            receipt.receiptID(),
            "Tạo phiếu nhập: " + request.receiptNumber()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<String> updateReceiptStatus(@PathVariable int id, @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("status");

        // Validate status
        List<String> validStatuses = Arrays.asList(
            "pending", "confirmed", "in_transit", "arrived",
            "receiving", "completed", "cancelled"
        );

        if (!validStatuses.contains(newStatus)) {
            return ResponseEntity.badRequest().body("Invalid status: " + newStatus);
        }

        // Update ImportReceiptHeader status
        String updateHeaderSql = "UPDATE ImportReceiptHeader SET ReceiptStatus = ? WHERE ReceiptID = ?";
        int updatedRows = jdbcTemplate.update(updateHeaderSql, newStatus, id);

        if (updatedRows == 0) {
            return ResponseEntity.notFound().build();
        }

        // If status is completed or cancelled, update related SKUItem statuses
        if (newStatus.equals("completed")) {
            updateSkuItemStatus(id, "in_stock");
        } else if (newStatus.equals("cancelled")) {
            updateSkuItemStatus(id, "quarantined");
        }

        return ResponseEntity.ok("Receipt status and related SKU items updated.");
    }

    private void updateSkuItemStatus(int receiptId, String newSkuStatus) {
        String sql = """
            UPDATE SKUItem
            SET Status = ?
            WHERE ItemID IN (
                SELECT ItemID
                FROM ImportReceiptLineItem
                WHERE ReceiptID = ?
            )
        """;
        jdbcTemplate.update(sql, newSkuStatus, receiptId);
    }

    @PostMapping("/check-line-items/{id}")
    public ResponseEntity<String> checkAndUpdateSkuItemStatuses(@PathVariable int id) {
        // Get all line items for this receipt
        String fetchLineItemsSql = """
            SELECT ReceiptLineItemID, Status
            FROM ImportReceiptLineItem
            WHERE ReceiptID = ?
        """;

        List<Map<String, Object>> lineItems = jdbcTemplate.queryForList(fetchLineItemsSql, id);

        for (Map<String, Object> lineItem : lineItems) {
            int lineItemId = (int) lineItem.get("ReceiptLineItemID");
            String status = (String) lineItem.get("Status");

            if ("stored".equalsIgnoreCase(status)) {
                updateSkuItemStatusByLineItem(lineItemId, "in_stock");
            } else if ("cancelled".equalsIgnoreCase(status)) {
                updateSkuItemStatusByLineItem(lineItemId, "quarantined");
            }
        }

        return ResponseEntity.ok("Checked line items and updated SKU item statuses accordingly.");
    }

    private void updateSkuItemStatusByLineItem(int lineItemId, String newStatus) {
        String updateSql = """
            UPDATE SKUItem
            SET Status = ?
            WHERE ItemID IN (
                SELECT ItemID
                FROM ImportReceiptLineItem
                WHERE ReceiptLineItemID = ?
            )
        """;

        jdbcTemplate.update(updateSql, newStatus, lineItemId);
    }
}