package com.lvnam0801.Luna.Resource.InventoryCheck.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Service.SKUItemService;
import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheck;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckDetail;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckDetailCreateRequest;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckCreateRequest;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckCreateResponse;

@Service
public class InventoryCheckServiceImpl implements InventoryCheckService {

    private final JdbcTemplate jdbcTemplate;
    private final UserContext userContext;
    private final SKUItemService skuItemService;
    public InventoryCheckServiceImpl(JdbcTemplate jdbcTemplate, UserContext userContext, SKUItemService skuItemService){
        this.jdbcTemplate = jdbcTemplate;
        this.userContext = userContext;
        this.skuItemService = skuItemService;
    }

    @Override
    public InventoryCheckCreateResponse createInventoryCheck(InventoryCheckCreateRequest request) {
        Integer currentUserID = userContext.getCurrentUserID();
        // 1. Insert InventoryChecks (header)
        String insertHeader = """
            INSERT INTO InventoryChecks (InventoryCheckNumber, WarehouseID, CheckedBy, CheckedDate, CreatedBy, UpdatedBy)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(insertHeader,
            request.inventoryCheckNumber(),
            request.warehouseID(),
            request.checkedBy(),
            request.checkedDate(),
            currentUserID, // Assume createdBy = checkedBy,
            currentUserID
        );

        // 2. Retrieve generated InventoryCheckID
        Integer checkID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        if(checkID == null)
        {
            throw new IllegalStateException("Failed to retrieve the last inserted ID.");
        }

        // 3. Insert each InventoryCheckDetail
        String insertDetail = """
            INSERT INTO InventoryCheckDetails (
                InventoryCheckID, SKUItemID, LocationID, SystemQuantity, ActualQuantity, 
                QuantityDifferenceReason, Note, Status
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, 'active')
        """;

        for (InventoryCheckDetailCreateRequest detail : request.details()) {
            jdbcTemplate.update(insertDetail,
                checkID,
                detail.skuItemID(),
                detail.locationID(),
                detail.systemQuantity(),
                detail.actualQuantity(),
                detail.quantityDifferenceReason(),
                detail.note()
            );

            // Adjust SKU quantity based on difference
            int diff = detail.actualQuantity() - detail.systemQuantity();
            if (diff > 0) {
                skuItemService.increaseSKUItemQuantity(detail.skuItemID(), diff);
            } else if (diff < 0) {
                skuItemService.decreaseSKUItemQuantity(detail.skuItemID(), -diff);
            }
        }

        // 4. Return the complete record using getById
        return new InventoryCheckCreateResponse(checkID, "Create new inventory check successfully");
    }

    @Override
    public List<InventoryCheck> getInventoryChecks() {
        String sqlHeader = """
            SELECT 
                ic.InventoryCheckID,
                ic.InventoryCheckNumber,
                ic.WarehouseID,
                w.Name AS warehouseName,
                ic.CheckedBy,
                u1.FullName AS checkedByName,
                ic.CheckedDate,
                ic.CreatedBy,
                u2.FullName AS createdByName,
                ic.CreatedAt,
                ic.UpdatedBy,
                u3.FullName AS updatedByName,
                ic.UpdatedAt
            FROM InventoryChecks ic
            JOIN Warehouse w ON ic.WarehouseID = w.WarehouseID
            LEFT JOIN User u1 ON ic.CheckedBy = u1.UserID
            LEFT JOIN User u2 ON ic.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON ic.UpdatedBy = u3.UserID
            ORDER BY ic.InventoryCheckID DESC
        """;

        List<InventoryCheck> result = jdbcTemplate.query(sqlHeader, (rs, rowNum) -> {
            int inventoryCheckID = rs.getInt("InventoryCheckID");

            // Fetch details for this check
            List<InventoryCheckDetail> details = getInventoryCheckDetails(inventoryCheckID);

            return new InventoryCheck(
                inventoryCheckID,
                rs.getString("InventoryCheckNumber"),
                rs.getInt("WarehouseID"),
                rs.getString("warehouseName"),
                rs.getObject("CheckedBy", Integer.class),
                rs.getString("checkedByName"),
                rs.getTimestamp("CheckedDate") != null ? rs.getTimestamp("CheckedDate").toLocalDateTime() : null,
                rs.getObject("CreatedBy", Integer.class),
                rs.getString("createdByName"),
                rs.getTimestamp("CreatedAt").toLocalDateTime(),
                rs.getObject("UpdatedBy", Integer.class),
                rs.getString("updatedByName"),
                rs.getTimestamp("UpdatedAt").toLocalDateTime(),
                details
            );
        });

        return result;
    }
    
    @Override
    public List<InventoryCheck> getInventoryChecks(Integer warehouseID)
    {
        String sqlHeader = """
            SELECT 
                ic.InventoryCheckID,
                ic.InventoryCheckNumber,
                ic.WarehouseID,
                w.Name AS warehouseName,
                ic.CheckedBy,
                u1.FullName AS checkedByName,
                ic.CheckedDate,
                ic.CreatedBy,
                u2.FullName AS createdByName,
                ic.CreatedAt,
                ic.UpdatedBy,
                u3.FullName AS updatedByName,
                ic.UpdatedAt
            FROM InventoryChecks ic
            JOIN Warehouse w ON ic.WarehouseID = w.WarehouseID
            LEFT JOIN User u1 ON ic.CheckedBy = u1.UserID
            LEFT JOIN User u2 ON ic.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON ic.UpdatedBy = u3.UserID
            WHERE ic.WarehouseID = ?
            ORDER BY ic.InventoryCheckID DESC
        """;

        List<InventoryCheck> result = jdbcTemplate.query(sqlHeader, (rs, rowNum) -> {
            int inventoryCheckID = rs.getInt("InventoryCheckID");

            // Fetch details for this check
            List<InventoryCheckDetail> details = getInventoryCheckDetails(inventoryCheckID);

            return new InventoryCheck(
                inventoryCheckID,
                rs.getString("InventoryCheckNumber"),
                rs.getInt("WarehouseID"),
                rs.getString("warehouseName"),
                rs.getObject("CheckedBy", Integer.class),
                rs.getString("checkedByName"),
                rs.getTimestamp("CheckedDate") != null ? rs.getTimestamp("CheckedDate").toLocalDateTime() : null,
                rs.getObject("CreatedBy", Integer.class),
                rs.getString("createdByName"),
                rs.getTimestamp("CreatedAt").toLocalDateTime(),
                rs.getObject("UpdatedBy", Integer.class),
                rs.getString("updatedByName"),
                rs.getTimestamp("UpdatedAt").toLocalDateTime(),
                details
            );
        },
        new Object[]{warehouseID});

        return result;
    }

    @Override
    public List<InventoryCheck> getInventoryChecks(LocalDate fromDate, LocalDate toDate)
    {
        String sqlHeader = """
            SELECT 
                ic.InventoryCheckID,
                ic.InventoryCheckNumber,
                ic.WarehouseID,
                w.Name AS warehouseName,
                ic.CheckedBy,
                u1.FullName AS checkedByName,
                ic.CheckedDate,
                ic.CreatedBy,
                u2.FullName AS createdByName,
                ic.CreatedAt,
                ic.UpdatedBy,
                u3.FullName AS updatedByName,
                ic.UpdatedAt
            FROM InventoryChecks ic
            JOIN Warehouse w ON ic.WarehouseID = w.WarehouseID
            LEFT JOIN User u1 ON ic.CheckedBy = u1.UserID
            LEFT JOIN User u2 ON ic.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON ic.UpdatedBy = u3.UserID
            WHERE ic.CheckedDate BETWEEN ? AND ?
            ORDER BY ic.InventoryCheckID DESC
        """;

        List<InventoryCheck> result = jdbcTemplate.query(sqlHeader, (rs, rowNum) -> {
            int inventoryCheckID = rs.getInt("InventoryCheckID");

            // Fetch details for this check
            List<InventoryCheckDetail> details = getInventoryCheckDetails(inventoryCheckID);

            return new InventoryCheck(
                inventoryCheckID,
                rs.getString("InventoryCheckNumber"),
                rs.getInt("WarehouseID"),
                rs.getString("warehouseName"),
                rs.getObject("CheckedBy", Integer.class),
                rs.getString("checkedByName"),
                rs.getTimestamp("CheckedDate") != null ? rs.getTimestamp("CheckedDate").toLocalDateTime() : null,
                rs.getObject("CreatedBy", Integer.class),
                rs.getString("createdByName"),
                rs.getTimestamp("CreatedAt").toLocalDateTime(),
                rs.getObject("UpdatedBy", Integer.class),
                rs.getString("updatedByName"),
                rs.getTimestamp("UpdatedAt").toLocalDateTime(),
                details
            );
        },
        new Object[]{fromDate, toDate});

        return result;
    }

    public List<InventoryCheck> getInventoryChecks(Integer warehouseID, LocalDate fromDate, LocalDate toDate)
    {
        String sqlHeader = """
            SELECT 
                ic.InventoryCheckID,
                ic.InventoryCheckNumber,
                ic.WarehouseID,
                w.Name AS warehouseName,
                ic.CheckedBy,
                u1.FullName AS checkedByName,
                ic.CheckedDate,
                ic.CreatedBy,
                u2.FullName AS createdByName,
                ic.CreatedAt,
                ic.UpdatedBy,
                u3.FullName AS updatedByName,
                ic.UpdatedAt
            FROM InventoryChecks ic
            JOIN Warehouse w ON ic.WarehouseID = w.WarehouseID
            LEFT JOIN User u1 ON ic.CheckedBy = u1.UserID
            LEFT JOIN User u2 ON ic.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON ic.UpdatedBy = u3.UserID
            WHERE ic.WarehouseID = ? AND ic.CheckedDate BETWEEN ? AND ?
            ORDER BY ic.InventoryCheckID DESC
        """;

        List<InventoryCheck> result = jdbcTemplate.query(sqlHeader, (rs, rowNum) -> {
            int inventoryCheckID = rs.getInt("InventoryCheckID");

            // Fetch details for this check
            List<InventoryCheckDetail> details = getInventoryCheckDetails(inventoryCheckID);

            return new InventoryCheck(
                inventoryCheckID,
                rs.getString("InventoryCheckNumber"),
                rs.getInt("WarehouseID"),
                rs.getString("warehouseName"),
                rs.getObject("CheckedBy", Integer.class),
                rs.getString("checkedByName"),
                rs.getTimestamp("CheckedDate") != null ? rs.getTimestamp("CheckedDate").toLocalDateTime() : null,
                rs.getObject("CreatedBy", Integer.class),
                rs.getString("createdByName"),
                rs.getTimestamp("CreatedAt").toLocalDateTime(),
                rs.getObject("UpdatedBy", Integer.class),
                rs.getString("updatedByName"),
                rs.getTimestamp("UpdatedAt").toLocalDateTime(),
                details
            );
        },
        new Object[]{warehouseID, fromDate, toDate});

        return result;
    }

    private List<InventoryCheckDetail> getInventoryCheckDetails(int checkID) {
        String sql = """
            SELECT 
                d.InventoryCheckDetailsID,
                d.InventoryCheckID,
                d.SKUItemID,
                s.SKU,
                s.LocationID,
                l.LocationName AS LocationName,
                d.SystemQuantity,
                d.ActualQuantity,
                d.QuantityDifferenceReason,
                d.Note,
                d.Status
            FROM InventoryCheckDetails d
            JOIN SKUItem s ON d.SKUItemID = s.ItemID
            JOIN Location l ON s.LocationID = l.LocationID
            WHERE d.InventoryCheckID = ?
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new InventoryCheckDetail(
            rs.getInt("InventoryCheckDetailsID"),
            rs.getInt("InventoryCheckID"),
            rs.getInt("SKUItemID"),
            rs.getString("SKU"),
            rs.getInt("LocationID"),
            rs.getString("LocationName"),
            rs.getInt("SystemQuantity"),
            rs.getInt("ActualQuantity"),
            rs.getString("QuantityDifferenceReason"),
            rs.getString("Note"),
            rs.getString("Status")
        ),
        new Object[]{checkID});
    }

    @Override
    public InventoryCheck getById(int id) {
        String sqlHeader = """
            SELECT 
                ic.InventoryCheckID,
                ic.InventoryCheckNumber,
                ic.WarehouseID,
                w.Name AS warehouseName,
                ic.CheckedBy,
                u1.FullName AS checkedByName,
                ic.CheckedDate,
                ic.CreatedBy,
                u2.FullName AS createdByName,
                ic.CreatedAt,
                ic.UpdatedBy,
                u3.FullName AS updatedByName,
                ic.UpdatedAt
            FROM InventoryChecks ic
            JOIN Warehouse w ON ic.WarehouseID = w.WarehouseID
            LEFT JOIN User u1 ON ic.CheckedBy = u1.UserID
            LEFT JOIN User u2 ON ic.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON ic.UpdatedBy = u3.UserID
            WHERE InventoryCheckID = ?
        """;

        InventoryCheck inventoryCheck = jdbcTemplate.queryForObject(sqlHeader, (rs, rowNum) -> {
            int inventoryCheckID = rs.getInt("InventoryCheckID");

            // Fetch details for this check
            List<InventoryCheckDetail> details = getInventoryCheckDetails(inventoryCheckID);

            return new InventoryCheck(
                inventoryCheckID,
                rs.getString("InventoryCheckNumber"),
                rs.getInt("WarehouseID"),
                rs.getString("warehouseName"),
                rs.getObject("CheckedBy", Integer.class),
                rs.getString("checkedByName"),
                rs.getTimestamp("CheckedDate") != null ? rs.getTimestamp("CheckedDate").toLocalDateTime() : null,
                rs.getObject("CreatedBy", Integer.class),
                rs.getString("createdByName"),
                rs.getTimestamp("CreatedAt").toLocalDateTime(),
                rs.getObject("UpdatedBy", Integer.class),
                rs.getString("updatedByName"),
                rs.getTimestamp("UpdatedAt").toLocalDateTime(),
                details
            );
        },
        new Object[]{id});
        return inventoryCheck;
    }
}