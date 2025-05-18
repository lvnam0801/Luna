package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Core.User.Service.UserService;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItem;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateResponse;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemUpdateResponse;

@Service
public class ImportReceiptLineItemServiceImpl implements ImportReceiptLineItemService{
    private final JdbcTemplate jdbcTemplate;
    private final ImportActivityLogService importActivityLogService;
    private final UserContext userContext;

    public ImportReceiptLineItemServiceImpl(JdbcTemplate jdbcTemplate, UserService userService, ImportActivityLogService importActivityLogService, UserContext userContext)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.importActivityLogService = importActivityLogService;
        this.userContext = userContext;
    }

    @Override
    public ImportReceiptLineItem[] getByReceipt(Integer receiptID) {
        String sql = """
            SELECT
                li.ReceiptLineItemID,
                li.WarehouseID,
                li.ReceiptID,
                ih.ReceiptNumber AS ReceiptNumber,
                li.ProductID,
                li.LineItemNumber,
                li.ReceivedQuantity,
                li.LotNumber,
                li.ExpirationDate,
                li.UnitCost,
                li.Status,
                li.CreatedBy,
                li.CreatedAt,
                li.UpdatedBy,
                li.UpdatedAt,
                p.Name AS ProductName,
                cu.FullName AS CreatedByName,
                uu.FullName AS UpdatedByName,
                w.Name AS WarehouseName
            FROM ImportReceiptLineItem li
            LEFT JOIN ImportReceiptHeader ih ON li.ReceiptID = ih.ReceiptID
            LEFT JOIN Product p ON li.ProductID = p.ProductID
            LEFT JOIN User cu ON li.CreatedBy = cu.UserID
            LEFT JOIN User uu ON li.UpdatedBy = uu.UserID
            LEFT JOIN Warehouse w ON li.WarehouseID = w.WarehouseID
            WHERE li.ReceiptID = ?
            """;

        List<ImportReceiptLineItem> items = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ImportReceiptLineItem(
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
                rs.getInt("ReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getInt("ProductID"),
                rs.getString("ProductName"),
                rs.getString("LineItemNumber"),
                rs.getInt("ReceivedQuantity"),
                rs.getString("LotNumber"),
                rs.getDate("ExpirationDate"),
                rs.getBigDecimal("UnitCost"),
                rs.getString("Status"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
            ),
            new Object[]{receiptID}
        );

        return items.toArray(ImportReceiptLineItem[]::new);
    }

    @Override
    public ImportReceiptLineItem getById(Integer receiptLineItemID) {
        String sql = """
            SELECT
                li.ReceiptLineItemID,
                li.WarehouseID,
                li.ReceiptID,
                ih.ReceiptNumber AS ReceiptNumber,
                li.ProductID,
                li.LineItemNumber,
                li.ReceivedQuantity,
                li.LotNumber,
                li.ExpirationDate,
                li.UnitCost,
                li.Status,
                li.CreatedBy,
                li.CreatedAt,
                li.UpdatedBy,
                li.UpdatedAt,
                p.Name AS ProductName,
                CONCAT(cu.FirstName, ' ', cu.LastName) AS CreatedByName,
                CONCAT(uu.FirstName, ' ', uu.LastName) AS UpdatedByName,
                w.Name AS WarehouseName
            FROM ImportReceiptLineItem li
            LEFT JOIN ImportReceiptHeader ih ON li.ReceiptID = ih.ReceiptID
            LEFT JOIN Product p ON li.ProductID = p.ProductID
            LEFT JOIN User cu ON li.CreatedBy = cu.UserID
            LEFT JOIN User uu ON li.UpdatedBy = uu.UserID
            LEFT JOIN Warehouse w ON li.WarehouseID = w.WarehouseID
            WHERE li.ReceiptLineItemID = ?
            """;

        return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> new ImportReceiptLineItem(
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
                rs.getInt("ReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getInt("ProductID"),
                rs.getString("ProductName"),
                rs.getString("LineItemNumber"),
                rs.getInt("ReceivedQuantity"),
                rs.getString("LotNumber"),
                rs.getDate("ExpirationDate"),
                rs.getBigDecimal("UnitCost"),
                rs.getString("Status"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
            ),
            new Object[]{receiptLineItemID}
        );
    }

    @Override
    public Integer getReceiptIDByLineItemID(int lineItemID)
    {
        String sql = "SELECT ReceiptID FROM ImportReceiptLineItem WHERE ReceiptLineItemID = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, new Object[]{lineItemID});
    }

    @Override
    public ImportReceiptLineItemCreateResponse createLine(ImportReceiptLineItemCreateRequest request) {
        // Generate LineItemNumber
        String lineItemNumber = "RLI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        String sql = """
            INSERT INTO ImportReceiptLineItem (
                WarehouseID, ReceiptID, ProductID, LineItemNumber,
                ReceivedQuantity,
                LotNumber, ExpirationDate,
                UnitCost, Status,
                CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            request.warehouseID(),
            request.receiptID(),
            request.productID(),
            lineItemNumber,
            request.receivedQuantity(),
            request.lotNumber(),
            request.expirationDate(),
            request.unitCost(),
            request.status(),
            userContext.getCurrentUserID(),
            userContext.getCurrentUserID()
            );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        if (id == null) {
            throw new IllegalStateException("Failed to retrieve the last inserted ID.");
        }

        // Log creation activity
        importActivityLogService.log(
            request.receiptID(),
            userContext.getCurrentUserID(),
            ImportActivityTargetType.LINE_ITEM.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo lô hàng: " + request.lotNumber()
        );

        return new ImportReceiptLineItemCreateResponse(
            id,
            "Import Receipt Line Item created successfully."
        );
    }

    @Override
    public ImportReceiptLineItemUpdateResponse updateReceiptLineItemPartially(Integer receiptLineItemID, ImportReceiptLineItemUpdateRequest request) {
        List<String> updates = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (request.expirationDate() != null) {
            updates.add("ExpirationDate = ?");
            params.add(request.expirationDate());
        }
        if (request.unitCost() != null) {
            updates.add("UnitCost = ?");
            params.add(request.unitCost());
        }
        if (request.status() != null) {
            updates.add("Status = ?");
            params.add(request.status());
        }

        // Always update UpdatedBy and UpdatedAt
        updates.add("UpdatedBy = ?");
        params.add(userContext.getCurrentUserID());
        updates.add("UpdatedAt = CURRENT_TIMESTAMP");

        if (updates.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }

        // Build and execute the SQL
        String sql = "UPDATE ImportReceiptLineItem SET " + String.join(", ", updates) + " WHERE ReceiptLineItemID = ?";
        params.add(receiptLineItemID);

       
        int rows = jdbcTemplate.update(sql, params.toArray());
        if (rows == 0) {
            throw new IllegalStateException("Update failed: no rows affected.");
        }
        // Log update activity
        // Get lineNumber for logging
        String lotNumberSql = "SELECT LotNumber FROM ImportReceiptLineItem WHERE ReceiptLineItemID = ?";
        String lotNumber = jdbcTemplate.queryForObject(lotNumberSql, String.class, new Object[]{receiptLineItemID});
        if (lotNumber == null) {
            throw new IllegalArgumentException("ReceiptLineItemID not found.");
        }

        importActivityLogService.log(
            getReceiptIDByLineItemID(receiptLineItemID),
            userContext.getCurrentUserID(),
            ImportActivityTargetType.LINE_ITEM.value(),
            ImportActivityActionType.UPDATE.value(),
            receiptLineItemID,
            "Câp nhật lô hàng: " + lotNumber
        );
        String message = "Import Receipt Line Item updated successfully.";
        return new ImportReceiptLineItemUpdateResponse(receiptLineItemID, message);
    }
}
