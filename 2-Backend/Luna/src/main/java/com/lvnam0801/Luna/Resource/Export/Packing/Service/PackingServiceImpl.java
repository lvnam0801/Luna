package com.lvnam0801.Luna.Resource.Export.Packing.Service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityActionType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityTargetType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service.ExportActivityLogService;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.Packing;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingDetail;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingDetailCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingDetailCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingUpdateResponse;

@Service
public class PackingServiceImpl implements PackingService {

    private final JdbcTemplate jdbcTemplate;
    private final UserContext userContext;
    private final ExportActivityLogService activityLogService;

    public PackingServiceImpl(JdbcTemplate jdbcTemplate, UserContext userContext, ExportActivityLogService activityLogService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userContext = userContext;
        this.activityLogService = activityLogService;
    }

    @Override
    public Packing[] getByOrderID(Integer orderID) {
        String sql = """
            SELECT p.*, l.LocationName AS PackToLocationName,
                   u1.FullName AS CreatedByName,
                   u2.FullName AS UpdatedByName,
                   u3.FullName AS PackedByName
            FROM Packing p
            JOIN Location l ON p.PackToLocationID = l.LocationID
            LEFT JOIN User u1 ON p.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON p.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON p.PackedBy = u3.UserID
            WHERE p.OrderID = ?
        """;

        return jdbcTemplate.query(sql, new Object[]{orderID}, (rs, rowNum) -> new Packing(
                rs.getInt("PackingID"),
                rs.getString("PackingNumber"),
                rs.getInt("OrderID"),
                rs.getInt("PackToLocationID"),
                rs.getString("PackToLocationName"),
                rs.getString("Status"),
                rs.getInt("PackedBy"),
                rs.getString("PackedByName"),
                rs.getDate("PackedDate"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
        )).toArray(Packing[]::new);
    }

    @Override
    public Packing getByID(Integer packingID) {
        return getPackingByIDInternal(packingID);
    }

    private Packing getPackingByIDInternal(Integer packingID) {
        String sql = """
            SELECT p.*, l.LocationName AS PackToLocationName,
                   u1.FullName AS CreatedByName,
                   u2.FullName AS UpdatedByName,
                   u3.FullName AS PackedByName
            FROM Packing p
            JOIN Location l ON p.PackToLocationID = l.LocationID
            LEFT JOIN User u1 ON p.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON p.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON p.PackedBy = u3.UserID
            WHERE p.PackingID = ?
        """;

        return jdbcTemplate.queryForObject(sql, new Object[]{packingID}, (rs, rowNum) -> new Packing(
                rs.getInt("PackingID"),
                rs.getString("PackingNumber"),
                rs.getInt("OrderID"),
                rs.getInt("PackToLocationID"),
                rs.getString("PackToLocationName"),
                rs.getString("Status"),
                rs.getInt("PackedBy"),
                rs.getString("PackedByName"),
                rs.getDate("PackedDate"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
        ));
    }

    @Override
    public PackingCreateResponse createPacking(PackingCreateRequest request) {
        Integer userID = userContext.getCurrentUserID();
        String sql = """
            INSERT INTO Packing (PackingNumber, OrderID, PackToLocationID, Status, PackedBy, PackedDate, CreatedBy, UpdatedBy)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                request.packingNumber(),
                request.orderID(),
                request.packToLocationID(),
                request.status(),
                userID,
                request.packedDate(),
                userID,
                userID
        );

        Integer packingID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        activityLogService.log(new ExportActivityLogRequest(
                request.orderID(),
                userID,
                ExportActivityTargetType.PACKING.value(),
                ExportActivityActionType.CREATE.value(),
                packingID,
                "Tạo packing  " + request.packingNumber()
        ));

        return new PackingCreateResponse(packingID, "Packing created successfully.");
    }

    @Override
    public PackingUpdateResponse updatePacking(Integer packingID, PackingUpdateRequest request) {
        Integer userID = userContext.getCurrentUserID();

        StringBuilder sql = new StringBuilder("UPDATE Packing SET ");
        List<Object> params = new java.util.ArrayList<>();

        if (request.packToLocationID() != null) {
            sql.append("PackToLocationID = ?, ");
            params.add(request.packToLocationID());
        }
        if (request.status() != null) {
            sql.append("Status = ?, ");
            params.add(request.status());
        }
        if (request.packedDate() != null) {
            sql.append("PackedDate = ?, ");
            params.add(request.packedDate());
        }

        sql.append("UpdatedBy = ?, UpdatedAt = CURRENT_TIMESTAMP WHERE PackingID = ?");
        params.add(userID);
        params.add(packingID);

        jdbcTemplate.update(sql.toString(), params.toArray());

        activityLogService.log(new ExportActivityLogRequest(
                getOrderIDFromPacking(packingID),
                userID,
                ExportActivityTargetType.PACKING.value(),
                ExportActivityActionType.UPDATE.value(),
                packingID,
                "Cập nhật packing " + getPackingNumberByID(packingID)
        ));

        return new PackingUpdateResponse(packingID, "Packing updated successfully.");
    }

    @Override
    public PackingDetail[] getDetailsByPackingID(Integer packingID) {
        String sql = """
            SELECT pd.*, s.SKU, p.Name AS ProductName
            FROM PackingDetail pd
            JOIN SKUItem s ON pd.SKUItemID = s.ItemID
            JOIN Product p ON s.ProductID = p.ProductID
            WHERE pd.PackingID = ?
        """;

        return jdbcTemplate.query(sql, new Object[]{packingID}, (rs, rowNum) -> new PackingDetail(
                rs.getInt("PackingDetailID"),
                rs.getInt("PackingID"),
                rs.getInt("SKUItemID"),
                rs.getString("SKU"),
                rs.getString("ProductName"),
                rs.getInt("PackedQuantity")
        )).toArray(PackingDetail[]::new);
    }

    @Override
    public PackingDetailCreateResponse addPackingDetail(PackingDetailCreateRequest request) {
        String sql = """
            INSERT INTO PackingDetail (PackingID, SKUItemID, PackedQuantity)
            VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(sql, request.packingID(), request.skuItemID(), request.packedQuantity());

        Integer detailID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        return new PackingDetailCreateResponse(detailID, "Packing detail added successfully.");
    }

    private Integer getOrderIDFromPacking(Integer packingID) {
        return jdbcTemplate.queryForObject("""
            SELECT OrderID
            FROM Packing
            WHERE PackingID = ?
        """, new Object[]{packingID}, Integer.class);
    }
 
    private String getPackingNumberByID(Integer packingID) {
        return jdbcTemplate.queryForObject("""
            SELECT PackingNumber
            FROM Packing
            WHERE PackingID = ?
        """, new Object[]{packingID}, String.class);
    }
}

