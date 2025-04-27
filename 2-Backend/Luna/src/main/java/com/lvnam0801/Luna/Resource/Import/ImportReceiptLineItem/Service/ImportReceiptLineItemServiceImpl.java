package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Service.UserService;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItem;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateResponse;

@Service
public class ImportReceiptLineItemServiceImpl implements ImportReceiptLineItemService{
    private final JdbcTemplate jdbcTemplate;
    private final ImportActivityLogService importActivityLogService;

    public ImportReceiptLineItemServiceImpl(JdbcTemplate jdbcTemplate, UserService userService, ImportActivityLogService importActivityLogService)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.importActivityLogService = importActivityLogService;
    }

    @Override
    public ImportReceiptLineItem[] getByReceipt(Integer receiptID) {
        String sql = """
            SELECT
                li.*,
                p.Name AS ProductName,
                cu.Name AS CreatedByName,
                uu.Name AS UpdatedByName
            FROM ImportReceiptLineItem li
            LEFT JOIN Product p ON li.ProductID = p.ProductID
            LEFT JOIN User cu ON li.CreatedBy = cu.UserID
            LEFT JOIN User uu ON li.UpdatedBy = uu.UserID
            WHERE li.ReceiptID = ?
        """;
    
        List<ImportReceiptLineItem> items = jdbcTemplate.query(
            sql,
            new Object[]{receiptID},
            (rs, rowNum) -> new ImportReceiptLineItem(
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("ReceiptID"),
                rs.getInt("ProductID"),
                rs.getString("ProductName"),
                rs.getString("LineItemNumber"),
                rs.getInt("ExpectedQuantity"),
                rs.getInt("ReceivedQuantity"),
                rs.getInt("QuantityDiscrepancy"),
                rs.getString("DiscrepancyReasonCode"),
                rs.getString("LotNumber"),
                rs.getString("SerialNumber"),
                rs.getDate("ExpirationDate"),
                rs.getBigDecimal("UnitCost"),
                rs.getString("Status"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedDate"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedDate")
            )
        );
    
        return items.toArray(ImportReceiptLineItem[]::new);
    }

    @Override
    public ImportReceiptLineItem getById(Integer receiptLineItemID) {
        String sql = """
            SELECT
                li.*,
                p.Name AS ProductName,
                cu.Name AS CreatedByName,
                uu.Name AS UpdatedByName
            FROM ImportReceiptLineItem li
            LEFT JOIN Product p ON li.ProductID = p.ProductID
            LEFT JOIN User cu ON li.CreatedBy = cu.UserID
            LEFT JOIN User uu ON li.UpdatedBy = uu.UserID
            WHERE li.ReceiptLineItemID = ?
        """;

        return jdbcTemplate.queryForObject(
            sql,
            new Object[]{receiptLineItemID},
            (rs, rowNum) -> new ImportReceiptLineItem(
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("ReceiptID"),
                rs.getInt("ProductID"),
                rs.getString("ProductName"),
                rs.getString("LineItemNumber"),
                rs.getInt("ExpectedQuantity"),
                rs.getInt("ReceivedQuantity"),
                rs.getInt("QuantityDiscrepancy"),
                rs.getString("DiscrepancyReasonCode"),
                rs.getString("LotNumber"),
                rs.getString("SerialNumber"),
                rs.getDate("ExpirationDate"),
                rs.getBigDecimal("UnitCost"),
                rs.getString("Status"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedDate"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedDate")
            )
        );
    }

    @Override
    public Integer getReceiptIDByLineItemID(int lineItemID)
    {
        String sql = "SELECT ReceiptID FROM ImportReceiptLineItem WHERE ReceiptLineItemID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{lineItemID}, Integer.class);
    }

    @Override
    public ImportReceiptLineItemCreateResponse createLine(ImportReceiptLineItemCreateRequest request) {
        // Generate LineItemNumber
        String lineItemNumber = "RLI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        String sql = """
            INSERT INTO ImportReceiptLineItem (
                ReceiptID, ProductID, LineItemNumber,
                ExpectedQuantity, ReceivedQuantity,
                DiscrepancyReasonCode, LotNumber, SerialNumber, ExpirationDate,
                UnitCost, Status,
                CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            request.receiptID(),
            request.productID(),
            lineItemNumber,
            request.expectedQuantity(),
            request.receivedQuantity(),
            request.discrepancyReasonCode(),
            request.lotNumber(),
            request.serialNumber(),
            request.expirationDate(),
            request.unitCost(),
            request.status(),
            request.createdBy(),
            request.createdBy() // For now createdBy = updatedBy at creation
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Log creation activity
        importActivityLogService.log(
            request.receiptID(),
            request.createdBy(),
            ImportActivityTargetType.LINE_ITEM.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo dòng sản phẩm: " + lineItemNumber
        );

        return new ImportReceiptLineItemCreateResponse(
            id,
            "Import Receipt Line Item created successfully."
        );
    }
}
