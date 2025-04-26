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
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.Putaway;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateRequest;

@Service
public class PutawayServiceImpl implements PutawayService{
    private final JdbcTemplate jdbcTemplate;
    private final SKUItemService skuItemService;
    private final ImportActivityLogService importActivityLogService;

    public PutawayServiceImpl(JdbcTemplate jdbcTemplate, SKUItemService skuItemService, ImportActivityLogService importActivityLogService)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.skuItemService = skuItemService;
        this.importActivityLogService = importActivityLogService;
    }

    @Override
    public Putaway[] getByReceiptLine(Integer receiptLineItemID) {
        String sql = "SELECT * FROM Putaway WHERE ReceiptLineItemID = ?";
    
        return jdbcTemplate.query(
            sql,
            new Object[]{receiptLineItemID},
            (rs, rowNum) -> new Putaway(
                rs.getInt("PutawayID"),
                rs.getString("PutawayNumber"),
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("PutawayAtLocation"),
                rs.getInt("SKUItemID"),
                rs.getInt("Quantity"),
                rs.getString("PutawayResult"),
                rs.getString("Status"),
                rs.getObject("PutawayBy", Integer.class),
                rs.getDate("PutawayDate"),
                rs.getObject("CreatedBy", Integer.class),
                rs.getTimestamp("CreatedAt"),
                rs.getObject("UpdatedBy", Integer.class),
                rs.getTimestamp("UpdatedAt")
            )
        ).toArray(Putaway[]::new);
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
    public Putaway createPutaway(PutawayCreateRequest request) {
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

        // Step 5: Insert Putaway with the new SKUItemID and PutawayNumber
        String sql = """
            INSERT INTO Putaway (
                PutawayNumber,
                ReceiptLineItemID,
                PutawayAtLocation,
                SKUItemID,
                PutawayBy,
                Quantity,
                PutawayResult,
                Status,
                PutawayDate,
                CreatedBy,
                UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        Integer mockUserId = request.putawayBy(); // or from context
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
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // Step 7: Log the activity
        importActivityLogService.log(
            id,
            mockUserId,
            ImportActivityTargetType.PUTAWAY.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo phiếu putaway: " + putawayNumber
        );

        // Step 8: Return Putaway with the new putawayNumber
        return new Putaway(
            id,
            putawayNumber,
            request.receiptLineItemID(),
            request.locationID(),
            skuItemID,
            request.quantity(),
            request.putawayResult(),
            request.status(),
            request.putawayBy(),
            request.putawayDate(),
            mockUserId,
            now,
            mockUserId,
            now
        );
    }
}
