package com.lvnam0801.Luna.Resource.Export.Packing.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityActionType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityTargetType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service.ExportActivityLogService;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItem;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Service.ExportOrderLineItemService;
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
    private final ExportOrderLineItemService exportOrderLineItemService;
    private final ExportActivityLogService activityLogService;

    public PackingServiceImpl(JdbcTemplate jdbcTemplate, UserContext userContext, ExportOrderLineItemService exportOrderLineItemService, ExportActivityLogService activityLogService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userContext = userContext;
        this.exportOrderLineItemService = exportOrderLineItemService;
        this.activityLogService = activityLogService;
    }

    @Override
    public Packing[] getByOrderID(Integer orderID) {
        String sql = """
            SELECT 
                p.*,
                eh.OrderNumber AS OrderNumber,
                el.LotNumber AS LotNumber,
                w.Name AS WarehouseName, 
                l.LocationName AS PackToLocationName,
                u1.FullName AS CreatedByName,
                u2.FullName AS UpdatedByName,
                u3.FullName AS PackedByName,
                pd.PackingDetailID,
                pd.SKUItemID,
                s.SKU,
                pr.Name AS ProductName,
                pd.PackedQuantity
            FROM Packing p
            LEFT JOIN ExportOrderHeader eh ON p.OrderID = eh.OrderID
            LEFT JOIN ExportOrderLineItem el ON p.OrderLineItemID = el.OrderLineItemID
            LEFT JOIN Warehouse w ON p.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON p.PackToLocationID = l.LocationID
            LEFT JOIN User u1 ON p.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON p.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON p.PackedBy = u3.UserID
            LEFT JOIN PackingDetail pd ON p.PackingID = pd.PackingID
            LEFT JOIN SKUItem s ON pd.SKUItemID = s.ItemID
            LEFT JOIN Product pr ON s.ProductID = pr.ProductID
            WHERE p.OrderID = ?
            ORDER BY p.PackingID
        """;

        Map<Integer, Packing> packingMap = new LinkedHashMap<>();

        jdbcTemplate.query(sql, rs -> {
            Integer packingID = rs.getInt("PackingID");

            // Only create Packing object if not already present
            packingMap.putIfAbsent(packingID, new Packing(
                packingID,
                rs.getString("PackingNumber"),
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getInt("OrderLineItemID"),
                rs.getString("LotNumber"),
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
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
                rs.getTimestamp("UpdatedAt"),
                new ArrayList<>() // Empty list of skuItems
            ));

            // Add packing detail if exists
            if (rs.getObject("PackingDetailID") != null) {
                Packing packing = packingMap.get(packingID);
                packing.skuItems().add(new PackingDetail(
                    rs.getInt("PackingDetailID"),
                    packingID,
                    rs.getInt("SKUItemID"),
                    rs.getString("SKU"),
                    rs.getString("ProductName"),
                    rs.getInt("PackedQuantity")
                ));
            }
            }, 
            new Object[]{orderID}
        );

        return packingMap.values().toArray(new Packing[0]);
    }

    @Override
    public Packing[] getByOrderLineItemID(Integer orderLineItemID)
    {
        String sql = """
            SELECT 
                p.*,
                eh.OrderNumber AS OrderNumber,
                el.LotNumber AS LotNumber,
                w.Name AS WarehouseName, 
                l.LocationName AS PackToLocationName,
                u1.FullName AS CreatedByName,
                u2.FullName AS UpdatedByName,
                u3.FullName AS PackedByName,
                pd.PackingDetailID,
                pd.SKUItemID,
                s.SKU,
                pr.Name AS ProductName,
                pd.PackedQuantity
            FROM Packing p
            LEFT JOIN ExportOrderHeader eh ON p.OrderID = eh.OrderID
            LEFT JOIN ExportOrderLineItem el ON p.OrderLineItemID = el.OrderLineItemID
            LEFT JOIN Warehouse w ON p.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON p.PackToLocationID = l.LocationID
            LEFT JOIN User u1 ON p.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON p.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON p.PackedBy = u3.UserID
            LEFT JOIN PackingDetail pd ON p.PackingID = pd.PackingID
            LEFT JOIN SKUItem s ON pd.SKUItemID = s.ItemID
            LEFT JOIN Product pr ON s.ProductID = pr.ProductID
            WHERE p.OrderLineItemID = ?
            ORDER BY p.PackingID
        """;

        Map<Integer, Packing> packingMap = new LinkedHashMap<>();

        jdbcTemplate.query(sql, rs -> {
            Integer packingID = rs.getInt("PackingID");

            // Only create Packing object if not already present
            packingMap.putIfAbsent(packingID, new Packing(
                packingID,
                rs.getString("PackingNumber"),
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getInt("OrderLineItemID"),
                rs.getString("LotNumber"),
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
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
                rs.getTimestamp("UpdatedAt"),
                new ArrayList<>() // Empty list of skuItems
            ));

            // Add packing detail if exists
            if (rs.getObject("PackingDetailID") != null) {
                Packing packing = packingMap.get(packingID);
                packing.skuItems().add(new PackingDetail(
                    rs.getInt("PackingDetailID"),
                    packingID,
                    rs.getInt("SKUItemID"),
                    rs.getString("SKU"),
                    rs.getString("ProductName"),
                    rs.getInt("PackedQuantity")
                ));
            }
            }, 
            new Object[]{orderLineItemID}
        );

        return packingMap.values().toArray(new Packing[0]);
    }


    @Override
    public Packing getByID(Integer packingID) {
        return getPackingByIDInternal(packingID);
    }

    private Packing getPackingByIDInternal(Integer packingID) {
        String sql = """
            SELECT p.*, 
                    eh.OrderNumber AS OrderNumber,
                    el.LotNumber AS LotNumber,
                    w.Name AS WarehouseName,  
                    l.LocationName AS PackToLocationName,
                    u1.FullName AS CreatedByName,
                    u2.FullName AS UpdatedByName,
                    u3.FullName AS PackedByName
            FROM Packing p
            LEFT JOIN ExportOrderHeader eh ON p.OrderID = eh.OrderID
            LEFT JOIN ExportOrderLineItem el ON p.OrderLineItemID = el.OrderLineItemID
            LEFT JOIN Warehouse w ON p.WarehouseID = w.WarehouseID
            JOIN Location l ON p.PackToLocationID = l.LocationID
            LEFT JOIN User u1 ON p.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON p.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON p.PackedBy = u3.UserID
            WHERE p.PackingID = ?
        """;
        String detailSql = """
            SELECT 
                d.PackingDetailID,
                d.PackingID,
                d.SKUItemID,
                s.SKU,
                p.Name AS ProductName,
                d.PackedQuantity
            FROM PackingDetail d
            JOIN SKUItem s ON d.SKUItemID = s.ItemID
            JOIN Product p ON s.ProductID = p.ProductID
            WHERE d.PackingID = ?
        """;

        List<PackingDetail> skuItems = jdbcTemplate.query(
            detailSql,
            (rs, rowNum) -> new PackingDetail(
                rs.getInt("PackingDetailID"),
                rs.getInt("PackingID"),
                rs.getInt("SKUItemID"),
                rs.getString("SKU"),
                rs.getString("ProductName"),
                rs.getInt("PackedQuantity")
            ),
            new Object[]{packingID}
        );

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Packing(
                rs.getInt("PackingID"),
                rs.getString("PackingNumber"),
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getInt("OrderLineItemID"),
                rs.getString("LotNumber"),
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
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
                rs.getTimestamp("UpdatedAt"),
                skuItems
            ),
            new Object[]{packingID}
        );
    }

    @Override
    public PackingCreateResponse createPacking(PackingCreateRequest request) {
        Integer userID = userContext.getCurrentUserID();
        String sql = """
            INSERT INTO Packing (
                PackingNumber, 
                OrderID, 
                OrderLineItemID,
                WarehouseID, 
                PackToLocationID, 
                Status, 
                PackedBy, 
                PackedDate, 
                CreatedBy, 
                UpdatedBy
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                request.packingNumber(),
                request.orderID(),
                request.orderLineItemID(),
                request.warehouseID(),
                request.packToLocationID(),
                request.status(),
                userID,
                request.packedDate(),
                userID,
                userID
        );

        Integer packingID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        ExportOrderLineItem exportOrderLineItem = exportOrderLineItemService.getByID(request.orderLineItemID());
        if(exportOrderLineItem == null)
        {
            throw new IllegalArgumentException("The export order line item not found: " + request.orderLineItemID());
        }
        
        activityLogService.log(new ExportActivityLogRequest(
                request.orderID(),
                userID,
                ExportActivityTargetType.PACKING.value(),
                ExportActivityActionType.CREATE.value(),
                packingID,
                "Tạo bản ghi đóng gói  " + request.packingNumber() + " trên lô hàng " + exportOrderLineItem.lotNumber()
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
                "Cập nhật bản ghi đóng gói " + getPackingNumberByID(packingID)
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

    @Override
    public String generatePackingNumber() {
        // Example: PACK-20240507-083015
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return "PACK-" + timestamp;
    }

    @Override
    public Packing[] getAvailablePackingsByOrderID(Integer orderID) {
        String sql = """
            SELECT
                p.*,
                eh.OrderNumber AS OrderNumber,
                el.LotNumber AS LotNumber,
                w.Name AS WarehouseName,  
                l.LocationName AS PackToLocationName,
                u1.FullName AS CreatedByName,
                u2.FullName AS UpdatedByName,
                u3.FullName AS PackedByName,
                pd.PackingDetailID,
                pd.SKUItemID,
                s.SKU,
                pr.Name AS ProductName,
                pd.PackedQuantity
            FROM Packing p
            LEFT JOIN ExportOrderHeader eh ON p.OrderID = eh.OrderID
            LEFT JOIN ExportOrderLineItem el ON p.OrderLineItemID = el.OrderLineItemID
            LEFT JOIN Warehouse w ON p.WarehouseID = w.WarehouseID
            JOIN Location l ON p.PackToLocationID = l.LocationID
            LEFT JOIN User u1 ON p.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON p.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON p.PackedBy = u3.UserID
            LEFT JOIN PackingDetail pd ON p.PackingID = pd.PackingID
            LEFT JOIN SKUItem s ON pd.SKUItemID = s.ItemID
            LEFT JOIN Product pr ON s.ProductID = pr.ProductID
            WHERE p.OrderID = ?
            AND p.Status = 'completed'
            AND p.PackingID NOT IN (
                SELECT sd.PackingID
                FROM Shipment s
                JOIN ShipmentPacking sd ON s.ShipmentID = sd.ShipmentID
                WHERE s.OrderID = ?
            )
            ORDER BY p.PackingID;
        """;

        Map<Integer, Packing> packingMap = new LinkedHashMap<>();

        jdbcTemplate.query(sql,rs -> {
                Integer packingID = rs.getInt("PackingID");

                packingMap.putIfAbsent(packingID, new Packing(
                    packingID,
                    rs.getString("PackingNumber"),
                    rs.getInt("OrderID"),
                    rs.getString("OrderNumber"),
                    rs.getInt("OrderLineItemID"),
                    rs.getString("LotNumber"),
                    rs.getInt("WarehouseID"),
                    rs.getString("WarehouseName"),
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
                    rs.getTimestamp("UpdatedAt"),
                    new ArrayList<>()
                ));

                if (rs.getObject("PackingDetailID") != null) {
                    Packing packing = packingMap.get(packingID);
                    packing.skuItems().add(new PackingDetail(
                        rs.getInt("PackingDetailID"),
                        packingID,
                        rs.getInt("SKUItemID"),
                        rs.getString("SKU"),
                        rs.getString("ProductName"),
                        rs.getInt("PackedQuantity")
                    ));
                }
            },
            new Object[]{orderID, orderID}
        );

        return packingMap.values().toArray(new Packing[0]);
    }
}

