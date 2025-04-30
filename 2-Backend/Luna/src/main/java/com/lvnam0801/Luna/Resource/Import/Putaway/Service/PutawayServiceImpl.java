package com.lvnam0801.Luna.Resource.Import.Putaway.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateRequest;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateResponse;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Service.SKUItemService;
import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service.ImportReceiptLineItemService;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.Putaway;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateRequest;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateResponse;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayUpdateResponse;

@Service
public class PutawayServiceImpl implements PutawayService{
    private final JdbcTemplate jdbcTemplate;
    private final SKUItemService skuItemService;
    private final ImportReceiptLineItemService importReceiptLineItemService;
    private final ImportActivityLogService importActivityLogService;
    private final UserContext userContext;

    public PutawayServiceImpl(JdbcTemplate jdbcTemplate, SKUItemService skuItemService, ImportReceiptLineItemService importReceiptLineItemService, ImportActivityLogService importActivityLogService, UserContext userContext)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.skuItemService = skuItemService;
        this.importReceiptLineItemService = importReceiptLineItemService;
        this.importActivityLogService = importActivityLogService;
        this.userContext = userContext;
    }

    // ----------GET PUTAWAY----------
    @Override
    public Putaway[] getByReceiptLine(Integer receiptLineItemID) {
        String sql = """
            SELECT 
                p.PutawayID,
                p.PutawayNumber,
                p.ReceiptLineItemID,
                p.PutawayAtLocationID,
                loc.LocationName AS PutawayAtLocationName,
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
            LEFT JOIN Location loc ON p.PutawayAtLocationID = loc.LocationID
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
                rs.getInt("PutawayAtLocationID"),
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
                p.PutawayAtLocationID,
                loc.LocationName AS PutawayAtLocationName,
                p.SKUItemID,
                sku.SKU AS SKUValue,
                p.Quantity,
                p.PutawayResult,
                p.Status,
                p.PutawayBy,
                CONCAT(u1.FirstName, ' ', u1.LastName) AS PutawayByName,
                p.PutawayDate,
                p.CreatedBy,
                CONCAT(u2.FirstName, ' ', u2.LastName) AS CreatedByName,
                p.CreatedAt,
                p.UpdatedBy,
                CONCAT(u3.FirstName, ' ', u3.LastName) AS UpdatedByName,
                p.UpdatedAt
            FROM Putaway p
            LEFT JOIN Location loc ON p.PutawayAtLocationID = loc.LocationID
            LEFT JOIN SKUItem sku ON p.SKUItemID = sku.ItemID
            LEFT JOIN User u1 ON p.PutawayBy = u1.UserID
            LEFT JOIN User u2 ON p.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON p.UpdatedBy = u3.UserID
            WHERE p.PutawayID = ?
            """;
    
        return jdbcTemplate.queryForObject(
            sql,
            new Object[]{putawayID},
            (rs, rowNum) -> new Putaway(
                rs.getInt("PutawayID"),
                rs.getString("PutawayNumber"),
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("PutawayAtLocationID"),
                rs.getString("PutawayAtLocationName"),
                rs.getInt("SKUItemID"),
                rs.getString("SKUValue"),
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
        );
    }

    // ----------CREATE PUTAWAY----------
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
    public boolean isPutawayEditable(Integer receiptLineItemID) {
        String sql = """
            SELECT Status
            FROM ImportReceiptLineItem
            WHERE ReceiptLineItemID = ?
            """;

        try {
            String status = jdbcTemplate.queryForObject(sql, String.class, receiptLineItemID);
            System.out.println("ReceiptLineItemID: " + receiptLineItemID + ", Status: " + status);
            boolean isFinalized = "completed".equalsIgnoreCase(status) || "cancelled".equalsIgnoreCase(status);
            return !isFinalized;
        } catch (EmptyResultDataAccessException e) {
            // Line item not found, treat as not finalized (or handle differently if needed)
            return false;
        }
    }

    @Override
    public PutawayCreateResponse createPutaway(PutawayCreateRequest request) 
    {
        // Step 1: Determine SKUItem status based on PutawayResult
        Integer skuItemID = null;
        if ("completed".equalsIgnoreCase(request.status()))
        {
            skuItemID = createSKUItemFromPutaway(request.putawayResult(), request.receiptLineItemID(), request.quantity());
        }

        // Step 2: Generate unique PutawayNumber
        String putawayNumber = "PUT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Integer currentUserId = userContext.getCurrentUserID();
        // Step 3: Insert Putaway with new SKUItemID and PutawayNumber
        String sql = """
            INSERT INTO Putaway (
                PutawayNumber, ReceiptLineItemID, PutawayAtLocationID, SKUItemID, PutawayBy,
                Quantity, PutawayResult, Status, PutawayDate, CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        
        jdbcTemplate.update(sql,
            putawayNumber,
            request.receiptLineItemID(),
            request.putawayAtLocationID(),
            skuItemID,
            currentUserId,
            request.quantity(),
            request.putawayResult(),
            request.status(),
            request.putawayDate(),
            currentUserId,
            currentUserId
        );

        // Step 4: Get the inserted Putaway ID
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        if (id == null) {
            throw new IllegalStateException("Failed to retrieve the last inserted Putaway ID.");
        }

        // Step 5: Log the activity
        Integer receiptID = importReceiptLineItemService.getReceiptIDByLineItemID(request.receiptLineItemID());
        importActivityLogService.log(
            receiptID,
            currentUserId,
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

    private Integer createSKUItemFromPutaway(String putawayResult, Integer receiptLineItemID, Integer putawayQuantity)
    {
        // Step 1: Determine SKUItem status based on PutawayResult
        String skuItemStatus;
        if ("stored".equalsIgnoreCase(putawayResult)) {
            skuItemStatus = "in_stock";
        } else if ("quarantined".equalsIgnoreCase(putawayResult)) {
            skuItemStatus = "quarantined";
        } else {
            throw new IllegalArgumentException("Invalid putawayResult value.");
        }
        Integer productID = getProductIDByLineItemID(receiptLineItemID);

        // Step 3: Create SKUItem
        SKUItemCreateRequest skuItemRequest = new SKUItemCreateRequest(
            productID,
            putawayQuantity,
            skuItemStatus
        );

        SKUItemCreateResponse createdSKUItem = skuItemService.createSKUItem(skuItemRequest);
        Integer receiptID = importReceiptLineItemService.getReceiptIDByLineItemID(receiptLineItemID);

        // Step 4: Log the activity

        importActivityLogService.log(
            receiptID,
            userContext.getCurrentUserID(),
            ImportActivityTargetType.SKU_ITEM.value(),
            ImportActivityActionType.CREATE.value(),
            createdSKUItem.itemID(),
            "Tạo SKUItem: " + createdSKUItem.sku() + " với số lượng: " + putawayQuantity
        );
        return createdSKUItem.itemID();
    }

    private Integer getProductIDByLineItemID(Integer receiptLineItemID) 
    {
        String productQuery = "SELECT ProductID FROM ImportReceiptLineItem WHERE ReceiptLineItemID = ?";
        Integer productID = jdbcTemplate.queryForObject (productQuery, Integer.class, receiptLineItemID);
        if (productID == null)
        {
            throw new IllegalArgumentException("Invalid receiptLineItemID: ProductID not found.");
        }
        return productID;
    }
    
    // ----------UPDATE PUTAWAY----------
    @Override
    public boolean canUpdatePutawayQuantityAgainstReceived(Integer putawayID, Integer newQuantity) {
        Integer receiptLineItemID = jdbcTemplate.queryForObject(
            "SELECT ReceiptLineItemID FROM Putaway WHERE PutawayID = ?",
            Integer.class,
            putawayID
        );

        String sql = """
            SELECT
                i.ReceivedQuantity,
                COALESCE(SUM(p.Quantity), 0) AS TotalPutaway,
                (SELECT Quantity FROM Putaway WHERE PutawayID = ?) AS CurrentPutawayQty
            FROM ImportReceiptLineItem i
            LEFT JOIN Putaway p ON i.ReceiptLineItemID = p.ReceiptLineItemID AND p.Status != 'cancelled'
            WHERE i.ReceiptLineItemID = ?
            GROUP BY i.ReceivedQuantity
        """;

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Integer receivedQty = rs.getObject("ReceivedQuantity", Integer.class);
            Integer totalPutawayQty = rs.getObject("TotalPutaway", Integer.class);
            Integer currentPutawayQty = rs.getObject("CurrentPutawayQty", Integer.class);

            receivedQty = (receivedQty != null) ? receivedQty : 0;
            totalPutawayQty = (totalPutawayQty != null) ? totalPutawayQty : 0;
            currentPutawayQty = (currentPutawayQty != null) ? currentPutawayQty : 0;

            // Adjust totalPutaway: remove old, add new
            int adjustedPutawayQty = (totalPutawayQty - currentPutawayQty) + newQuantity;

            return adjustedPutawayQty <= receivedQty;
        }, putawayID, receiptLineItemID));
    }

    @Override
    public boolean canUpdatePutawayQuantityByResultType(Integer putawayID, String putawayResult, Integer newQuantity) {
        // Map result type
        String inspectionResult;
        if ("stored".equalsIgnoreCase(putawayResult)) {
            inspectionResult = "passed";
        } else if ("quarantined".equalsIgnoreCase(putawayResult)) {
            inspectionResult = "failed";
        } else {
            return false; // invalid result
        }

        Integer receiptLineItemID = jdbcTemplate.queryForObject(
            "SELECT ReceiptLineItemID FROM Putaway WHERE PutawayID = ?",
            Integer.class,
            putawayID
        );

        String sql = """
            SELECT
                (SELECT SUM(Quantity) FROM QualityInspection WHERE ReceiptLineItemID = ? AND InspectionResult = ?) AS TotalInspected,
                (SELECT SUM(Quantity) FROM Putaway WHERE ReceiptLineItemID = ? AND PutawayResult = ? AND Status != 'cancelled') AS TotalPutaway,
                (SELECT Quantity FROM Putaway WHERE PutawayID = ?) AS CurrentPutawayQty
        """;

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Integer totalInspected = rs.getObject("TotalInspected", Integer.class);
            Integer totalPutaway = rs.getObject("TotalPutaway", Integer.class);
            Integer currentPutawayQty = rs.getObject("CurrentPutawayQty", Integer.class);

            totalInspected = (totalInspected != null) ? totalInspected : 0;
            totalPutaway = (totalPutaway != null) ? totalPutaway : 0;
            currentPutawayQty = (currentPutawayQty != null) ? currentPutawayQty : 0;

            int adjustedPutawayQty = (totalPutaway - currentPutawayQty) + newQuantity;

            return adjustedPutawayQty <= totalInspected;
        }, receiptLineItemID, inspectionResult, receiptLineItemID, putawayResult, putawayID));
    }

    @Override
    public PutawayUpdateResponse updatePutawayPartially(Integer putawayID, PutawayUpdateRequest request) {
        // Step 1: Fetch current Putaway
        String fetchSql = "SELECT * FROM Putaway WHERE PutawayID = ?";
        Putaway existingPutaway = jdbcTemplate.queryForObject(
            fetchSql,
            new Object[]{putawayID},
            (rs, rowNum) -> new Putaway(
                rs.getInt("PutawayID"),
                rs.getString("PutawayNumber"),
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("PutawayAtLocationID"),
                null, // putawayAtLocationName
                rs.getObject("SKUItemID", Integer.class),
                null, // SKU
                rs.getInt("Quantity"),
                rs.getString("PutawayResult"),
                rs.getString("Status"),
                rs.getInt("PutawayBy"),
                null, // putawayByName
                rs.getDate("PutawayDate"),
                rs.getInt("CreatedBy"),
                null, // createdByName
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                null, // updatedByName
                rs.getTimestamp("UpdatedAt")
            )
        );

        if (existingPutaway == null) {
            throw new IllegalArgumentException("Putaway not found with ID: " + putawayID);
        }
    
        // Step 2: Check current status
        if ("completed".equalsIgnoreCase(existingPutaway.status()) || "cancelled".equalsIgnoreCase(existingPutaway.status())) {
            throw new IllegalStateException("Cannot update a completed or cancelled Putaway.");
        }
    
        // Step 3: Build dynamic update SQL
        List<String> updates = new ArrayList<>();
        List<Object> params = new ArrayList<>();
    
        if (request.putawayAtLocationID() != null) {
            updates.add("PutawayAtLocationID = ?");
            params.add(request.putawayAtLocationID());
        }
        if (request.quantity() != null) {
            updates.add("Quantity = ?");
            params.add(request.quantity());
        }
        if (request.putawayResult() != null) {
            updates.add("PutawayResult = ?");
            params.add(request.putawayResult());
        }
        if (request.status() != null) {
            updates.add("Status = ?");
            params.add(request.status());
        }
        if (request.putawayDate() != null) {
            updates.add("PutawayDate = ?");
            params.add(request.putawayDate());
        }
    
        // Step 4: Handle SKUItem creation if status moved to 'completed' and SKUItemID is null
        Integer skuItemID = existingPutaway.skuItemID();
        if ("completed".equalsIgnoreCase(request.status()) && skuItemID == null) {
            skuItemID = createSKUItemFromPutaway(existingPutaway.putawayResult(), existingPutaway.receiptLineItemID(), existingPutaway.quantity());
            updates.add("SKUItemID = ?");
            params.add(skuItemID);
        }
    
        // Always update UpdatedBy and UpdatedAt
        updates.add("UpdatedBy = ?");
        params.add(userContext.getCurrentUserID());
        updates.add("UpdatedAt = CURRENT_TIMESTAMP");
    
        // Step 5: Finalize and execute update
        if (updates.isEmpty()) {
            throw new IllegalArgumentException("No valid fields provided for update.");
        }
    
        String updateSql = "UPDATE Putaway SET " + String.join(", ", updates) + " WHERE PutawayID = ?";
        params.add(putawayID);
    
        int rows = jdbcTemplate.update(updateSql, params.toArray());
        if (rows == 0) {
            throw new IllegalStateException("Update failed: no rows affected.");
        }

        // Step 6: Log the activity
        Integer receiptID = importReceiptLineItemService.getReceiptIDByLineItemID(existingPutaway.receiptLineItemID());
        importActivityLogService.log(
            receiptID,
            userContext.getCurrentUserID(),
            ImportActivityTargetType.PUTAWAY.value(),
            ImportActivityActionType.UPDATE.value(),
            putawayID,
            "Cập nhật phiếu putaway: " + existingPutaway.putawayNumber()
        );
    
        return new PutawayUpdateResponse(
            putawayID,
            "Putaway updated successfully."
        );
    }
}
