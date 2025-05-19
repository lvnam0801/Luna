package com.lvnam0801.Luna.Resource.Export.Shipment.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.Address.Representation.Address;
import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityActionType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityTargetType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service.ExportActivityLogService;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.Packing;
import com.lvnam0801.Luna.Resource.Export.Packing.Service.PackingService;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.Shipment;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPacking;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPackingCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPackingCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentUpdateResponse;

import java.sql.ResultSet;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final JdbcTemplate jdbcTemplate;
    private final ExportActivityLogService activityLogService;
    private final UserContext userContext;
    private final PackingService packingService;
    
    public ShipmentServiceImpl(JdbcTemplate jdbcTemplate, ExportActivityLogService activityLogService, UserContext userContext, PackingService packingService) {
        this.jdbcTemplate = jdbcTemplate;
        this.activityLogService = activityLogService;
        this.userContext = userContext;
        this.packingService = packingService;
    }

    @Override
    public Shipment[] getByOrderID(Integer orderID) {
        String sql = """
            SELECT 
                s.ShipmentID, s.ShipmentNumber,
                s.OrderID, o.OrderNumber,
                s.CarrierID, c.ContactName AS CarrierName,
                s.WarehouseID,
                w.Name AS WarehouseName,
                s.ShipFromLocationID, l.LocationName AS ShipFromLocationName,
                s.ShipToAddressID,
                a.StreetAddress, a.City, a.StateProvince, a.PostalCode, a.Country,
                s.ShippedDate, s.ExpectedArrivalDate, s.ActualArrivalDate,
                s.ShipmentStatus, s.Notes, s.Status,
                s.CreatedBy, cu.FullName AS CreatedByName,
                s.CreatedAt, s.UpdatedBy, uu.FullName AS UpdatedByName, s.UpdatedAt
            FROM Shipment s
            JOIN ExportOrderHeader o ON s.OrderID = o.OrderID
            LEFT JOIN Party c ON s.CarrierID = c.PartyID
            LEFT JOIN Warehouse w ON s.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON s.ShipFromLocationID = l.LocationID
            LEFT JOIN Address a ON s.ShipToAddressID = a.AddressID
            LEFT JOIN User cu ON s.CreatedBy = cu.UserID
            LEFT JOIN User uu ON s.UpdatedBy = uu.UserID
            WHERE s.OrderID = ?
            ORDER BY s.ShipmentID
        """;

        List<Shipment> results = jdbcTemplate.query(sql, (rs, rowNum) ->
            new Shipment(
                rs.getInt("ShipmentID"),
                rs.getString("ShipmentNumber"),

                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),

                rs.getInt("CarrierID"),
                rs.getString("CarrierName"),

                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),

                rs.getInt("ShipFromLocationID"),
                rs.getString("ShipFromLocationName"),

                rs.getInt("ShipToAddressID"),
                new Address(
                    rs.getInt("ShipToAddressID"),
                    rs.getString("StreetAddress"),
                    rs.getString("City"),
                    rs.getString("StateProvince"),
                    rs.getString("PostalCode"),
                    rs.getString("Country")
                ),

                rs.getDate("ShippedDate"),
                rs.getDate("ExpectedArrivalDate"),
                rs.getDate("ActualArrivalDate"),

                rs.getString("ShipmentStatus"),
                rs.getString("Notes"),
                rs.getString("Status"),

                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),

                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt"),

                getShipmentPackings(rs.getInt("ShipmentID")) // 🧩 <- inject nested packing
            ),
            new Object[]{orderID}
        );

        return results.toArray(new Shipment[0]);
    }

    @Override
    public Shipment getByID(Integer shipmentID) {
         String sql = """
            SELECT 
                s.ShipmentID, s.ShipmentNumber,
                s.OrderID, o.OrderNumber,
                s.CarrierID, c.ContactName AS CarrierName,
                s.WarehouseID,
                w.Name AS WarehouseName,
                s.ShipFromLocationID, l.LocationName AS ShipFromLocationName,
                s.ShipToAddressID,
                a.StreetAddress, a.City, a.StateProvince, a.PostalCode, a.Country,
                s.ShippedDate, s.ExpectedArrivalDate, s.ActualArrivalDate,
                s.ShipmentStatus, s.Notes, s.Status,
                s.CreatedBy, cu.FullName AS CreatedByName,
                s.CreatedAt, s.UpdatedBy, uu.FullName AS UpdatedByName, s.UpdatedAt
            FROM Shipment s
            JOIN ExportOrderHeader o ON s.OrderID = o.OrderID
            LEFT JOIN Party c ON s.CarrierID = c.PartyID
            LEFT JOIN Warehouse w ON s.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON s.ShipFromLocationID = l.LocationID
            LEFT JOIN Address a ON s.ShipToAddressID = a.AddressID
            LEFT JOIN User cu ON s.CreatedBy = cu.UserID
            LEFT JOIN User uu ON s.UpdatedBy = uu.UserID
            WHERE s.ShipmentID = ?
            ORDER BY s.ShipmentID
        """;

        Shipment shipment = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            new Shipment(
                rs.getInt("ShipmentID"),
                rs.getString("ShipmentNumber"),

                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),

                rs.getInt("CarrierID"),
                rs.getString("CarrierName"),

                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),

                rs.getInt("ShipFromLocationID"),
                rs.getString("ShipFromLocationName"),

                rs.getInt("ShipToAddressID"),
                new Address(
                    rs.getInt("ShipToAddressID"),
                    rs.getString("StreetAddress"),
                    rs.getString("City"),
                    rs.getString("StateProvince"),
                    rs.getString("PostalCode"),
                    rs.getString("Country")
                ),

                rs.getDate("ShippedDate"),
                rs.getDate("ExpectedArrivalDate"),
                rs.getDate("ActualArrivalDate"),

                rs.getString("ShipmentStatus"),
                rs.getString("Notes"),
                rs.getString("Status"),

                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),

                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt"),

                getShipmentPackings(rs.getInt("ShipmentID")) // 🧩 <- inject nested packing
            ),
            new Object[]{shipmentID}
        );

        return shipment;
    }

    private List<ShipmentPacking> getShipmentPackings(int shipmentID) {
        String sql = """
            SELECT sp.ShipmentPackingID, sp.ShipmentID, sp.PackingID, p.CreatedAt
            FROM ShipmentPacking sp
            JOIN Packing p ON sp.PackingID = p.PackingID
            WHERE sp.ShipmentID = ?
        """;
    
        return jdbcTemplate.query(sql, new Object[]{shipmentID}, (rs, rowNum) ->
            new ShipmentPacking(
                rs.getInt("ShipmentPackingID"),
                rs.getInt("ShipmentID"),
                rs.getInt("PackingID"),
                rs.getTimestamp("CreatedAt")
            )
        );
    }
    @Override
    public ShipmentCreateResponse createShipment(ShipmentCreateRequest request) {
        Integer userID = userContext.getCurrentUserID();

        String sql = """
            INSERT INTO Shipment (
                ShipmentNumber, OrderID, CarrierID, WarehouseID, ShipFromLocationID, ShipToAddressID,
                ShippedDate, ExpectedArrivalDate, ActualArrivalDate, ShipmentStatus, Notes, Status,
                CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.shipmentNumber(),
            request.orderID(),
            request.carrierID(),
            request.warehouseID(),
            request.shipFromLocationID(),
            request.shipToAddressID(),
            request.shippedDate(),
            request.expectedArrivalDate(),
            request.actualArrivalDate(),
            request.shipmentStatus(),
            request.notes(),
            request.status(),
            userID,
            userID
        );

        Integer shipmentID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        activityLogService.log(new ExportActivityLogRequest(
            request.orderID(),
            userID,
            ExportActivityTargetType.SHIPMENT.value(),
            ExportActivityActionType.CREATE.value(),
            shipmentID,
            "Tạo vận đơn mới: " + request.shipmentNumber()
        ));

        return new ShipmentCreateResponse(shipmentID, "Shipment created successfully.");
    }

    @Override
    public ShipmentUpdateResponse updateShipmentPartially(Integer shipmentID, ShipmentUpdateRequest request) {
        Integer userID = userContext.getCurrentUserID();

        StringBuilder sql = new StringBuilder("UPDATE Shipment SET ");
        List<Object> params = new java.util.ArrayList<>();

        if (request.carrierID() != null) {
            sql.append("CarrierID = ?, ");
            params.add(request.carrierID());
        }
        if (request.shipFromLocationID() != null) {
            sql.append("ShipFromLocationID = ?, ");
            params.add(request.shipFromLocationID());
        }
        if (request.shipToAddressID() != null) {
            sql.append("ShipToAddressID = ?, ");
            params.add(request.shipToAddressID());
        }
        if (request.shippedDate() != null) {
            sql.append("ShippedDate = ?, ");
            params.add(request.shippedDate());
        }
        if (request.expectedArrivalDate() != null) {
            sql.append("ExpectedArrivalDate = ?, ");
            params.add(request.expectedArrivalDate());
        }
        if (request.actualArrivalDate() != null) {
            sql.append("ActualArrivalDate = ?, ");
            params.add(request.actualArrivalDate());
        }
        if (request.shipmentStatus() != null) {
            sql.append("ShipmentStatus = ?, ");
            params.add(request.shipmentStatus());
        }
        if (request.notes() != null) {
            sql.append("Notes = ?, ");
            params.add(request.notes());
        }
        if (request.status() != null) {
            sql.append("Status = ?, ");
            params.add(request.status());
        }

        sql.append("UpdatedBy = ?, UpdatedAt = CURRENT_TIMESTAMP WHERE ShipmentID = ?");
        params.add(userID);
        params.add(shipmentID);

        jdbcTemplate.update(sql.toString(), params.toArray());
        Integer orderID = getOrderIDByShipmentID(shipmentID);
        if (orderID == null) {
            throw new RuntimeException("Order not found for shipment ID: " + shipmentID);
        }
        String shipmentNumber = getShipmentNumberByID(shipmentID);
        if (shipmentNumber == null) {
            throw new RuntimeException("Shipment not found with ID: " + shipmentID);
        }
        
        activityLogService.log(new ExportActivityLogRequest(
            orderID,
            userID,
            ExportActivityTargetType.SHIPMENT.value(),
            ExportActivityActionType.UPDATE.value(),
            shipmentID,
            "Cập nhật vận đơn " + shipmentNumber
        ));

        return new ShipmentUpdateResponse(shipmentID, "Shipment updated successfully.");
    }

    private Integer getOrderIDByShipmentID(Integer shipmentID) {
        String sql = "SELECT OrderID FROM Shipment WHERE ShipmentID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{shipmentID}, Integer.class);
    }

    private String getShipmentNumberByID(Integer shipmentID) {
        String sql = "SELECT ShipmentNumber FROM Shipment WHERE ShipmentID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{shipmentID}, String.class);
    }

    @Override
    public ShipmentPackingCreateResponse createShipmentPacking(ShipmentPackingCreateRequest request) {
        String sql = """
            INSERT INTO ShipmentPacking (ShipmentID, PackingID)
            VALUES (?, ?)
        """;

        jdbcTemplate.update(sql, request.shipmentID(), request.packingID());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        if (id == null) {
            throw new IllegalStateException("Failed to retrieve last inserted ShipmentPacking ID");
        }

        return new ShipmentPackingCreateResponse(id, "Shipment-Packing link created successfully.");
    }

    @Override
    public ShipmentPacking[] getShipmentPackingsByShipmentID(Integer shipmentID) {
        String sql = """
            SELECT * FROM ShipmentPacking
            WHERE ShipmentID = ?
        """;

        List<ShipmentPacking> mappings = jdbcTemplate.query(
            sql,
            new Object[]{shipmentID},
            (rs, rowNum) -> new ShipmentPacking(
                rs.getInt("ShipmentPackingID"),
                rs.getInt("ShipmentID"),
                rs.getInt("PackingID"),
                rs.getTimestamp("CreatedAt")
            )
        );

        return mappings.toArray(ShipmentPacking[]::new);
    }

    @Override
    public Packing[] getAvailablePackingsByOrderID(Integer orderID)
    {
        return packingService.getAvailablePackingsByOrderID(orderID);
    }

}