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
                li.ReceiptID,
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
                uu.FullName AS UpdatedByName
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
            )
        );

        return items.toArray(ImportReceiptLineItem[]::new);
    }

    @Override
    public ImportReceiptLineItem getById(Integer receiptLineItemID) {
        String sql = """
            SELECT
                li.ReceiptLineItemID,
                li.ReceiptID,
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
                CONCAT(uu.FirstName, ' ', uu.LastName) AS UpdatedByName
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
                ReceivedQuantity,
                LotNumber, ExpirationDate,
                UnitCost, Status,
                CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
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
            "Tạo phiếu sản phẩm: " + lineItemNumber
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

        if (request.lotNumber() != null) {
            updates.add("LotNumber = ?");
            params.add(request.lotNumber());
        }
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
        String lineItemNumberSql = "SELECT LineItemNumber FROM ImportReceiptLineItem WHERE ReceiptLineItemID = ?";
        String lineItemNumber = jdbcTemplate.queryForObject(lineItemNumberSql, new Object[]{receiptLineItemID}, String.class);
        if (lineItemNumber == null) {
            throw new IllegalArgumentException("ReceiptLineItemID not found.");
        }

        importActivityLogService.log(
            getReceiptIDByLineItemID(receiptLineItemID),
            userContext.getCurrentUserID(),
            ImportActivityTargetType.LINE_ITEM.value(),
            ImportActivityActionType.UPDATE.value(),
            receiptLineItemID,
            "Cập nhật phiếu sản phẩm: " + lineItemNumber
        );
        String message = "Import Receipt Line Item updated successfully.";
        return new ImportReceiptLineItemUpdateResponse(receiptLineItemID, message);
    }
}
