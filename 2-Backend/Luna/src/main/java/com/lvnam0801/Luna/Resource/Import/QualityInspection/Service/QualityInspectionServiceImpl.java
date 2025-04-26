package com.lvnam0801.Luna.Resource.Import.QualityInspection.Service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspection;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionCreateRequest;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionUpdateRequest;

@Service
public class QualityInspectionServiceImpl implements QualityInspectionService{
    
    private final JdbcTemplate jdbcTemplate;
    private final ImportActivityLogService  importActivityLogService;

    public QualityInspectionServiceImpl(JdbcTemplate jdbcTemplate, ImportActivityLogService importActivityLogService)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.importActivityLogService = importActivityLogService;
    }

    @Override
    public QualityInspection[] getByLineItem(Integer receiptLineItemID) {
        String sql = """
            SELECT * FROM QualityInspection WHERE ReceiptLineItemID = ?
        """;
    
        return jdbcTemplate.query(
            sql,
            new Object[]{receiptLineItemID},
            (rs, rowNum) -> new QualityInspection(
                rs.getInt("InspectionID"),
                rs.getString("InspectionNumber"),
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("InspectedBy"),
                rs.getDate("InspectionDate"),
                rs.getInt("InspectedLocationID"),
                rs.getInt("Quantity"),
                rs.getString("InspectionResult"),
                rs.getString("Notes"),
                rs.getString("Status"),
                rs.getInt("CreatedBy"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getTimestamp("UpdatedAt")
            )
        ).toArray(QualityInspection[]::new);
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
    public QualityInspection createInspection(QualityInspectionCreateRequest inspectionCreateRequest) {
        Integer mockCreatedById = 3;
        Integer mockUpdatedById = 3;

        // Step 1: Generate inspection number
        String inspectionNumber = "INS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Step 2: Insert into database
        String sql = """
            INSERT INTO QualityInspection (
                InspectionNumber,
                ReceiptLineItemID,
                InspectedBy,
                InspectionDate,
                InspectedLocationID,
                Quantity,
                InspectionResult,
                Notes,
                Status,
                CreatedBy,
                UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
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

        // Step 3: Retrieve new ID and timestamps
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // Step 4: Return full record
        QualityInspection inspection = new QualityInspection(
            id,
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
            now,
            mockUpdatedById,
            now
        );
        
        // Step 5: Log the activity
        importActivityLogService.log(
            id,
            mockCreatedById,
            ImportActivityTargetType.QUALITY_INSPECTION.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo phiếu kiểm tra chất lượng: " + inspectionNumber
        );

        return inspection;
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
    @Nullable
    public QualityInspection updateInspectionPartially(Integer inspectionId, QualityInspectionUpdateRequest request) {
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
    
        String sql = "UPDATE QualityInspection SET " + String.join(", ", updates) + " WHERE InspectionID = ?";
        params.add(inspectionId);
    
        int rows = jdbcTemplate.update(sql, params.toArray());
        if (rows == 0) {
            throw new IllegalStateException("Update failed: no rows affected.");
        }
    
        // Fetch updated data
        String fetchSql = "SELECT * FROM QualityInspection WHERE InspectionID = ?";
        return jdbcTemplate.queryForObject(fetchSql, new Object[]{inspectionId}, (rs, rowNum) ->
            new QualityInspection(
                rs.getInt("InspectionID"),
                rs.getString("InspectionNumber"),
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("InspectedBy"),
                rs.getDate("InspectionDate"),
                rs.getObject("InspectedLocationID", Integer.class),
                rs.getInt("Quantity"),
                rs.getString("InspectionResult"),
                rs.getString("Notes"),
                rs.getString("Status"),
                rs.getInt("CreatedBy"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getTimestamp("UpdatedAt")
            )
        );
    }
}
