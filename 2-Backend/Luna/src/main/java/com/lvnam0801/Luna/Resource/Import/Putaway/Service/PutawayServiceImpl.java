package com.lvnam0801.Luna.Resource.Import.Putaway.Service;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemRequest;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Service.SKUItemService;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service.ImportReceiptLineItemService;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.Putaway;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateRequest;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateResponse;

@Service
public class PutawayServiceImpl implements PutawayService{
    private final JdbcTemplate jdbcTemplate;
    private final SKUItemService skuItemService;
    private final ImportReceiptLineItemService importReceiptLineItemService;
    private final ImportActivityLogService importActivityLogService;

    public PutawayServiceImpl(JdbcTemplate jdbcTemplate, SKUItemService skuItemService, ImportReceiptLineItemService importReceiptLineItemService, ImportActivityLogService importActivityLogService)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.skuItemService = skuItemService;
        this.importReceiptLineItemService = importReceiptLineItemService;
        this.importActivityLogService = importActivityLogService;
    }

    @Override
    public Putaway[] getByReceiptLine(Integer receiptLineItemID) {
        String sql = """
            SELECT 
                p.PutawayID,
                p.PutawayNumber,
                p.ReceiptLineItemID,
                p.PutawayAtLocation,
                loc.Value AS PutawayAtLocationName,
                p.SKUItemID,
                s.SKU AS SKU,
                p.Quantity,
                p.PutawayResult,
                p.Status,
                p.PutawayBy,
                u1.FullName AS PutawayByName,
                p.PutawayDate,
                p.CreatedBy,
                u2.FullName AS CreatedByName,
                p.CreatedAt,
                p.UpdatedBy,
                u3.FullName AS UpdatedByName,
                p.UpdatedAt
            FROM Putaway p
            LEFT JOIN Location loc ON p.PutawayAtLocation = loc.LocationID
            LEFT JOIN SKUItem s ON p.SKUItemID = s.ItemID
            LEFT JOIN User u1 ON p.PutawayBy = u1.UserID
            LEFT JOIN User u2 ON p.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON p.UpdatedBy = u3.UserID
            WHERE p.ReceiptLineItemID = ?
        """;

        return jdbcTemplate.query(
            sql,
            new Object[]{receiptLineItemID},
            (rs, rowNum) -> new Putaway(
                rs.getInt("PutawayID"),
                rs.getString("PutawayNumber"),
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("PutawayAtLocation"),
                rs.getString("PutawayAtLocationName"),
                rs.getInt("SKUItemID"),
                rs.getString("SKU"),
                rs.getInt("Quantity"),
                rs.getString("PutawayResult"),
                rs.getString("Status"),
                rs.getInt("PutawayBy"),
                rs.getString("PutawayByName"),
                rs.getDate("PutawayDate"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
            )
        ).toArray(Putaway[]::new);
    }

    @Override
    public Putaway getById(Integer putawayID) {
        String sql = """
            SELECT
                p.PutawayID,
                p.PutawayNumber,
                p.ReceiptLineItemID,
                p.PutawayAtLocation,
                loc.Value AS PutawayAtLocationName,
                p.SKUItemID,
                sku.Name AS SKUItemName,
                p.Quantity,
                p.PutawayResult,
                p.Status,
                p.PutawayBy,
                u1.FullName AS PutawayByName,
                p.PutawayDate,
                p.CreatedBy,
                u2.FullName AS CreatedByName,
                p.CreatedAt,
                p.UpdatedBy,
                u3.FullName AS UpdatedByName,
                p.UpdatedAt
            FROM Putaway p
            LEFT JOIN Location loc ON p.PutawayAtLocation = loc.LocationID
            LEFT JOIN SKUItem sku ON p.SKUItemID = sku.ItemID
            LEFT JOIN Product prod ON sku.ProductID = prod.ProductID
            LEFT JOIN User u1 ON p.PutawayBy = u1.UserID
            LEFT JOIN User u2 ON p.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON p.UpdatedBy = u3.UserID
            WHERE p.PutawayID = ?
        """;

        return jdbcTemplate.queryForObject(sql, new Object[]{putawayID}, (rs, rowNum) -> new Putaway(
            rs.getInt("PutawayID"),
            rs.getString("PutawayNumber"),
            rs.getInt("ReceiptLineItemID"),
            rs.getInt("PutawayAtLocation"),
            rs.getString("PutawayAtLocationName"),
            rs.getInt("SKUItemID"),
            rs.getString("SKUItemName"),
            rs.getInt("Quantity"),
            rs.getString("PutawayResult"),
            rs.getString("Status"),
            rs.getInt("PutawayBy"),
            rs.getString("PutawayByName"),
            rs.getDate("PutawayDate"),
            rs.getInt("CreatedBy"),
            rs.getString("CreatedByName"),
            rs.getTimestamp("CreatedAt"),
            rs.getInt("UpdatedBy"),
            rs.getString("UpdatedByName"),
            rs.getTimestamp("UpdatedAt")
        ));
    }

    @Override
    public boolean canPutawayByResultType(Integer receiptLineItemID, String putawayResult, Integer putawayQuantity) {
        // Map to corresponding inspection result
        String inspectionResult;
        if ("stored".equalsIgnoreCase(putawayResult)) {
            inspectionResult = "passed";
        } else if ("quarantined".equalsIgnoreCase(putawayResult)) {
            inspectionResult = "failed";
        } else {
            return false; // Invalid putawayResult
        }
    
        String sql = """
            SELECT
                (SELECT SUM(Quantity) FROM QualityInspection WHERE ReceiptLineItemID = ? AND InspectionResult = ?) AS totalInspected,
                (SELECT SUM(Quantity) FROM Putaway WHERE ReceiptLineItemID = ? AND PutawayResult = ? AND Status != 'cancelled') AS totalPutaway
            """;
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Integer totalInspected = rs.getObject("totalInspected", Integer.class);
            Integer totalPutaway = rs.getObject("totalPutaway", Integer.class);
    
            totalInspected = (totalInspected != null) ? totalInspected : 0;
            totalPutaway = (totalPutaway != null) ? totalPutaway : 0;
    
            return totalPutaway + putawayQuantity <= totalInspected;
        }, receiptLineItemID, inspectionResult, receiptLineItemID, putawayResult));
    }

    @Override
    public boolean canPutawayQuantityAgainstReceived(Integer receiptLineItemID, Integer putawayQuantity) {
        String sql = """
            SELECT
                i.ReceivedQuantity,
                COALESCE(SUM(p.Quantity), 0) AS TotalPutaway
            FROM ImportReceiptLineItem i
            LEFT JOIN Putaway p ON i.ReceiptLineItemID = p.ReceiptLineItemID AND p.Status != 'cancelled'
            WHERE i.ReceiptLineItemID = ?
            GROUP BY i.ReceivedQuantity
            """;
    
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Integer receivedQty = rs.getObject("ReceivedQuantity", Integer.class);
            Integer currentPutawayQty = rs.getObject("TotalPutaway", Integer.class);
    
            receivedQty = (receivedQty != null) ? receivedQty : 0;
            currentPutawayQty = (currentPutawayQty != null) ? currentPutawayQty : 0;
    
            return currentPutawayQty + putawayQuantity <= receivedQty;
        }, receiptLineItemID));
    }

    @Override
    public boolean isLineItemFinalized(Integer receiptLineItemID) {
        String sql = """
            SELECT Status
            FROM ImportReceiptLineItem
            WHERE ReceiptLineItemID = ?
            """;

        try {
            String status = jdbcTemplate.queryForObject(sql, String.class, receiptLineItemID);
            return "stored".equalsIgnoreCase(status) || "quarantined".equalsIgnoreCase(status);
        } catch (EmptyResultDataAccessException e) {
            // Line item not found, treat as not finalized (or handle differently if needed)
            return false;
        }
    }

    @Override
    public PutawayCreateResponse createPutaway(PutawayCreateRequest request) {
        // Step 1: Determine SKUItem status based on PutawayResult
        String skuItemStatus;
        if ("stored".equalsIgnoreCase(request.putawayResult())) {
            skuItemStatus = "in_stock";
        } else if ("quarantined".equalsIgnoreCase(request.putawayResult())) {
            skuItemStatus = "quarantined";
        } else {
            throw new IllegalArgumentException("Invalid putawayResult value.");
        }

        // Step 2: Lookup ProductID using ReceiptLineItemID
        String productQuery = "SELECT ProductID FROM ImportReceiptLineItem WHERE ReceiptLineItemID = ?";
        Integer productID = jdbcTemplate.queryForObject(productQuery, Integer.class, request.receiptLineItemID());
        if (productID == null) {
            throw new IllegalArgumentException("Invalid receiptLineItemID: ProductID not found.");
        }

        // Step 3: Create SKUItem
        SKUItemRequest skuItemRequest = new SKUItemRequest(
            productID,
            request.quantity(),
            skuItemStatus
        );
        Integer skuItemID = skuItemService.createSKUItem(skuItemRequest);

        // Step 4: Generate unique PutawayNumber
        String putawayNumber = "PUT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Step 5: Insert Putaway with new SKUItemID and PutawayNumber
        String sql = """
            INSERT INTO Putaway (
                PutawayNumber, ReceiptLineItemID, PutawayAtLocation, SKUItemID, PutawayBy,
                Quantity, PutawayResult, Status, PutawayDate, CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        Integer mockUserId = request.putawayBy(); // Assume you have user context

        jdbcTemplate.update(sql,
            putawayNumber,
            request.receiptLineItemID(),
            request.locationID(),
            skuItemID,
            mockUserId,
            request.quantity(),
            request.putawayResult(),
            request.status(),
            request.putawayDate(),
            mockUserId,
            mockUserId
        );

        // Step 6: Get the inserted Putaway ID
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Step 7: Log the activity
        Integer receiptID = importReceiptLineItemService.getReceiptIDByLineItemID(request.receiptLineItemID());
        importActivityLogService.log(
            receiptID,
            mockUserId,
            ImportActivityTargetType.PUTAWAY.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo phiếu putaway: " + putawayNumber
        );

        // Step 8: Return response
        return new PutawayCreateResponse(
            id,
            "Putaway created successfully."
        );
    }
}
