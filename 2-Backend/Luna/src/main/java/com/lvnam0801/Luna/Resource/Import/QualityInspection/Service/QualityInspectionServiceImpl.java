package com.lvnam0801.Luna.Resource.Import.QualityInspection.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service.ImportReceiptLineItemService;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspection;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionCreateRequest;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionCreateResponse;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionUpdateResponse;

@Service
public class QualityInspectionServiceImpl implements QualityInspectionService{
    
    private final JdbcTemplate jdbcTemplate;
    private final ImportReceiptLineItemService importReceiptLineItemService;
    private final ImportActivityLogService  importActivityLogService;

    public QualityInspectionServiceImpl(JdbcTemplate jdbcTemplate, ImportReceiptLineItemService importReceiptLineItemService, ImportActivityLogService importActivityLogService)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.importReceiptLineItemService = importReceiptLineItemService;
        this.importActivityLogService = importActivityLogService;
    }

    @Override
    public QualityInspection[] getByLineItem(Integer receiptLineItemID) {
        String sql = """
            SELECT 
                i.InspectionID,
                i.InspectionNumber,
                i.ReceiptLineItemID,
                i.InspectedBy,
                ub.UserName AS InspectedByName,
                i.InspectionDate,
                i.InspectedLocationID,
                l.Value AS InspectedLocationName,
                i.Quantity,
                i.InspectionResult,
                i.Notes,
                i.Status,
                i.CreatedBy,
                uc.UserName AS CreatedByName,
                i.CreatedAt,
                i.UpdatedBy,
                uu.UserName AS UpdatedByName,
                i.UpdatedAt
            FROM QualityInspection i
            LEFT JOIN User ub ON i.InspectedBy = ub.UserID
            LEFT JOIN Location l ON i.InspectedLocationID = l.LocationID
            LEFT JOIN User uc ON i.CreatedBy = uc.UserID
            LEFT JOIN User uu ON i.UpdatedBy = uu.UserID
            WHERE i.ReceiptLineItemID = ?
        """;

        List<QualityInspection> inspections = jdbcTemplate.query(
            sql,
            new Object[]{receiptLineItemID},
            (rs, rowNum) -> new QualityInspection(
                rs.getInt("InspectionID"),
                rs.getString("InspectionNumber"),
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("InspectedBy"),
                rs.getString("InspectedByName"),
                rs.getDate("InspectionDate"),
                rs.getInt("InspectedLocationID"),
                rs.getString("InspectedLocationName"),
                rs.getInt("Quantity"),
                rs.getString("InspectionResult"),
                rs.getString("Notes"),
                rs.getString("Status"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
            )
        );

        return inspections.toArray(QualityInspection[]::new);
    }

    @Override
    public QualityInspection getById(Integer inspectionID) {
        String sql = """
            SELECT 
                i.*, 
                cu.Name AS CreatedByName,
                uu.Name AS UpdatedByName,
                ins.Name AS InspectedByName,
                l.Value AS InspectedLocationName
            FROM QualityInspection i
            LEFT JOIN User cu ON i.CreatedBy = cu.UserID
            LEFT JOIN User uu ON i.UpdatedBy = uu.UserID
            LEFT JOIN User ins ON i.InspectedBy = ins.UserID
            LEFT JOIN Location l ON i.InspectedLocationID = l.LocationID
            WHERE i.InspectionID = ?
        """;

        return jdbcTemplate.queryForObject(sql, new Object[]{inspectionID}, (rs, rowNum) -> new QualityInspection(
            rs.getInt("InspectionID"),
            rs.getString("InspectionNumber"),
            rs.getInt("ReceiptLineItemID"),
            rs.getInt("InspectedBy"),
            rs.getString("InspectedByName"),
            rs.getDate("InspectionDate"),
            rs.getInt("InspectedLocationID"),
            rs.getString("InspectedLocationName"),
            rs.getInt("Quantity"),
            rs.getString("InspectionResult"),
            rs.getString("Notes"),
            rs.getString("Status"),
            rs.getInt("CreatedBy"),
            rs.getString("CreatedByName"),
            rs.getTimestamp("CreatedAt"),
            rs.getInt("UpdatedBy"),
            rs.getString("UpdatedByName"),
            rs.getTimestamp("UpdatedAt")
        ));
    }

    @Override
    public boolean canAddInspectionQuantity(int receiptLineItemId, int additionalQuantity) {
        String sql = """
            SELECT i.ReceivedQuantity, 
                   COALESCE(SUM(q.Quantity), 0) AS TotalInspected
            FROM ImportReceiptLineItem i
            LEFT JOIN QualityInspection q 
              ON q.ReceiptLineItemID = i.ReceiptLineItemID 
             AND q.Status != 'cancelled'
            WHERE i.ReceiptLineItemID = ?
            GROUP BY i.ReceivedQuantity
            """;
    
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                int received = rs.getInt("ReceivedQuantity");
                int totalInspected = rs.getInt("TotalInspected");
                return (totalInspected + additionalQuantity) <= received;
            }, receiptLineItemId);
        } catch (EmptyResultDataAccessException e) {
            // No such line item found
            return false;
        }
    }

    @Override
    public QualityInspectionCreateResponse createInspection(QualityInspectionCreateRequest inspectionCreateRequest) {
        Integer mockCreatedById = 3;
        Integer mockUpdatedById = 3;

        // Step 1: Generate inspection number
        String inspectionNumber = "INS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Step 2: Insert into database
        String sql = """
            INSERT INTO QualityInspection (
                InspectionNumber, ReceiptLineItemID,
                InspectedBy, InspectionDate,
                InspectedLocationID, Quantity,
                InspectionResult, Notes,
                Status, CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            inspectionNumber,
            inspectionCreateRequest.receiptLineItemID(),
            inspectionCreateRequest.inspectedBy(),
            inspectionCreateRequest.inspectionDate(),
            inspectionCreateRequest.inspectedLocationID(),
            inspectionCreateRequest.quantity(),
            inspectionCreateRequest.inspectionResult(),
            inspectionCreateRequest.notes(),
            inspectionCreateRequest.status(),
            mockCreatedById,
            mockUpdatedById
        );

        // Step 3: Get inserted ID
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Step 4: Get ReceiptID for logging
        Integer receiptID = importReceiptLineItemService.getReceiptIDByLineItemID(inspectionCreateRequest.receiptLineItemID());

        // Step 5: Log activity
        importActivityLogService.log(
            receiptID,
            mockCreatedById,
            ImportActivityTargetType.QUALITY_INSPECTION.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo phiếu kiểm tra chất lượng: " + inspectionNumber
        );

        // Step 6: Return simple response
        return new QualityInspectionCreateResponse(
            id,
            "Quality Inspection created successfully."
        );
    }
        
    @Override
    public boolean canUpdateInspection(Integer inspectionId) {
        String sql = """
            SELECT i.Status AS LineItemStatus
            FROM QualityInspection q
            JOIN ImportReceiptLineItem i ON q.ReceiptLineItemID = i.ReceiptLineItemID
            WHERE q.InspectionID = ?
        """;
    
        String status = jdbcTemplate.queryForObject(sql, String.class, inspectionId);
        if (status == null) {
            return false; // No matching record, be safe and disallow updates
        }
    
        return !status.equalsIgnoreCase("stored") && !status.equalsIgnoreCase("quarantined");
    }

    @Override
    public boolean canUpdateInspectionQuantity(int inspectionId, int updatedQuantity) {
        String sql = """
            SELECT 
                i.ReceivedQuantity,
                SUM(q.Quantity) AS TotalOtherInspected
            FROM QualityInspection q
            JOIN ImportReceiptLineItem i ON q.ReceiptLineItemID = i.ReceiptLineItemID
            WHERE q.InspectionID != ? 
              AND q.Status != 'cancelled'
              AND q.ReceiptLineItemID = (
                  SELECT ReceiptLineItemID FROM QualityInspection WHERE InspectionID = ?
              )
            GROUP BY i.ReceivedQuantity
        """;
    
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                int receivedQuantity = rs.getInt("ReceivedQuantity");
                int totalOtherInspected = rs.getInt("TotalOtherInspected");
                return totalOtherInspected + updatedQuantity <= receivedQuantity;
            }, inspectionId, inspectionId);
        } catch (EmptyResultDataAccessException e) {
            // If this is the only inspection, fall back to checking received quantity directly
            String fallbackSql = """
                SELECT i.ReceivedQuantity
                FROM QualityInspection q
                JOIN ImportReceiptLineItem i ON q.ReceiptLineItemID = i.ReceiptLineItemID
                WHERE q.InspectionID = ?
            """;
            Integer receivedQuantity = jdbcTemplate.queryForObject(fallbackSql, Integer.class, inspectionId);
            return updatedQuantity <= receivedQuantity;
        }
    }

    @Override
    public QualityInspectionUpdateResponse updateInspectionPartially(Integer inspectionID, QualityInspectionUpdateRequest request) {
        Integer mockCreatedById = 3;

        List<String> updates = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (request.inspectedBy() != null) {
            updates.add("InspectedBy = ?");
            params.add(request.inspectedBy());
        }
        if (request.inspectionDate() != null) {
            updates.add("InspectionDate = ?");
            params.add(request.inspectionDate());
        }
        if (request.inspectedLocationID() != null) {
            updates.add("InspectedLocationID = ?");
            params.add(request.inspectedLocationID());
        }
        if (request.quantity() != null) {
            updates.add("Quantity = ?");
            params.add(request.quantity());
        }
        if (request.inspectionResult() != null) {
            updates.add("InspectionResult = ?");
            params.add(request.inspectionResult());
        }
        if (request.notes() != null) {
            updates.add("Notes = ?");
            params.add(request.notes());
        }
        if (request.status() != null) {
            updates.add("Status = ?");
            params.add(request.status());
        }

        if (updates.isEmpty()) {
            throw new IllegalArgumentException("No valid fields provided to update.");
        }

        // Update database
        String sql = "UPDATE QualityInspection SET " + String.join(", ", updates) + " WHERE InspectionID = ?";
        params.add(inspectionID);

        int rows = jdbcTemplate.update(sql, params.toArray());
        if (rows == 0) {
            throw new IllegalStateException("Update failed: no rows affected.");
        }

        // Log the update
        Integer receiptID = getReceiptIDFromInspection(inspectionID);

        importActivityLogService.log(
            receiptID,
            mockCreatedById, // you should get current user ID from session later
            ImportActivityTargetType.QUALITY_INSPECTION.value(),
            ImportActivityActionType.UPDATE.value(),
            inspectionID,
            "Cập nhật phiếu kiểm tra chất lượng #" + inspectionID
        );

        // Return simple response
        return new QualityInspectionUpdateResponse(
            inspectionID,
            "Quality Inspection updated successfully."
        );
    }

    /**
     * Get ReceiptID from InspectionID.
     * 
     * Steps:
     * 1. Find ReceiptLineItemID by InspectionID (QualityInspection table).
     * 2. Find ReceiptID by ReceiptLineItemID (ImportReceiptLineItem table).
     */
    public Integer getReceiptIDFromInspection(Integer inspectionID) {
        // Step 1: Get ReceiptLineItemID
        String sql1 = "SELECT ReceiptLineItemID FROM QualityInspection WHERE InspectionID = ?";
        Integer receiptLineItemID = jdbcTemplate.queryForObject(sql1, Integer.class, inspectionID);

        if (receiptLineItemID == null) {
            throw new IllegalArgumentException("No Receipt Line Item found for Inspection ID: " + inspectionID);
        }

        // Step 2: Get ReceiptID
        String sql2 = "SELECT ReceiptID FROM ImportReceiptLineItem WHERE ReceiptLineItemID = ?";
        Integer receiptID = jdbcTemplate.queryForObject(sql2, Integer.class, receiptLineItemID);

        if (receiptID == null) {
            throw new IllegalArgumentException("No Receipt found for Receipt Line Item ID: " + receiptLineItemID);
        }

        return receiptID;
    }
}
