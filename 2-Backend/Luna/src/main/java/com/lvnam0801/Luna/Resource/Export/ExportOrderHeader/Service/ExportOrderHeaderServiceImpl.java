package com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.Address.Representation.Address;
import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityActionType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityTargetType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service.ExportActivityLogService;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeader;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderCreateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderCreateResponse;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderUpdateResponse;

@Service
public class ExportOrderHeaderServiceImpl implements ExportOrderHeaderService {
    private final JdbcTemplate jdbcTemplate;
    private final ExportActivityLogService exportActivityLogService;
    private final UserContext userContext;
    public ExportOrderHeaderServiceImpl(JdbcTemplate jdbcTemplate, ExportActivityLogService exportActivityLogService, UserContext userContext) {
        this.jdbcTemplate = jdbcTemplate;
        this.exportActivityLogService = exportActivityLogService;
        this.userContext = userContext;
    }
    
    @Override
    public ExportOrderHeader[] getAllExportOrders() {
        String sql = """
            SELECT
                o.OrderID,
                o.OrderNumber,
    
                o.CustomerID,
                customer.ContactName AS CustomerName,
                o.CarrierID,
                carrier.ContactName AS CarrierName,
    
                o.WarehouseID,
                w.Name AS WarehouseName,
    
                o.ShippingAddressID,
                a.StreetAddress,
                a.City,
                a.StateProvince,
                a.PostalCode,
                a.Country,

                o.ExportPurpose,
                o.OrderDate,
                o.RequestedDeliveryDate,
                o.ShippingMethod,
                o.OrderStatus,
                o.Notes,
                o.Status,
                o.ReceiptID,
    
                o.CreatedBy,
                createdByUser.FullName AS CreatedByName,
                o.UpdatedBy,
                updatedByUser.FullName AS UpdatedByName,
                o.CreatedAt,
                o.UpdatedAt,
                o.DestinationWarehouseID,
                w2.Name AS DestinationWarehouseName,
                o.LinkedImportReceiptID,
                ih.ReceiptNumber
            FROM ExportOrderHeader o
            LEFT JOIN Party customer ON o.CustomerID = customer.PartyID
            LEFT JOIN Party carrier ON o.CarrierID = carrier.PartyID
            LEFT JOIN Warehouse w ON o.WarehouseID = w.WarehouseID
            LEFT JOIN Address a ON o.ShippingAddressID = a.AddressID
            LEFT JOIN User createdByUser ON o.CreatedBy = createdByUser.UserID
            LEFT JOIN User updatedByUser ON o.UpdatedBy = updatedByUser.UserID
            LEFT JOIN Warehouse w2 ON o.DestinationWarehouseID = w2.WarehouseID
            LEFT JOIN ImportReceiptHeader ih ON o.LinkedImportReceiptID = ih.ReceiptID
            ORDER BY o.OrderID
            """;
    
        List<ExportOrderHeader> orders = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ExportOrderHeader(
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
    
                rs.getInt("CustomerID"),
                rs.getString("CustomerName"),
                rs.getInt("CarrierID"),
                rs.getString("CarrierName"),
    
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
    
                rs.getInt("ShippingAddressID"),
                new Address(
                    rs.getInt("ShippingAddressID"),
                    rs.getString("StreetAddress"),
                    rs.getString("City"),
                    rs.getString("StateProvince"),
                    rs.getString("PostalCode"),
                    rs.getString("Country")
                ),

                rs.getString("ExportPurpose"),
                rs.getDate("OrderDate"),
                rs.getDate("RequestedDeliveryDate"),
                rs.getString("ShippingMethod"),
                rs.getString("OrderStatus"),
                rs.getString("Notes"),
                rs.getString("Status"),
    
                rs.getObject("ReceiptID", Integer.class), // can be NULL
                rs.getInt("DestinationWarehouseID"),
                rs.getString("DestinationWarehouseName"),
                rs.getInt("LinkedImportReceiptID"),
                rs.getString("ReceiptNumber"),
    
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getTimestamp("UpdatedAt")
            )
        );
    
        return orders.toArray(ExportOrderHeader[]::new);
    }

    @Override
    public ExportOrderHeader getExportOrderById(Integer orderID) {
        String sql = """
            SELECT 
                o.OrderID,
                o.OrderNumber,
                
                o.CustomerID,
                customer.ContactName AS CustomerName,
                o.CarrierID,
                carrier.ContactName AS CarrierName,
                
                o.WarehouseID,
                w.Name AS WarehouseName,
                
                o.ShippingAddressID,
                a.StreetAddress,
                a.City,
                a.StateProvince,
                a.PostalCode,
                a.Country,
                
                o.ExportPurpose,
                o.OrderDate,
                o.RequestedDeliveryDate,
                o.ShippingMethod,
                o.OrderStatus,
                o.Notes,
                o.Status,
                o.ReceiptID,
                
                o.CreatedBy,
                createdByUser.FullName AS CreatedByName,
                o.UpdatedBy,
                updatedByUser.FullName AS UpdatedByName,
                o.CreatedAt,
                o.UpdatedAt,
                o.DestinationWarehouseID,
                w2.Name AS DestinationWarehouseName,
                o.LinkedImportReceiptID,
                ih.ReceiptNumber
            FROM ExportOrderHeader o
            LEFT JOIN Party customer ON o.CustomerID = customer.PartyID
            LEFT JOIN Party carrier ON o.CarrierID = carrier.PartyID
            LEFT JOIN Warehouse w ON o.WarehouseID = w.WarehouseID
            LEFT JOIN Address a ON o.ShippingAddressID = a.AddressID
            LEFT JOIN User createdByUser ON o.CreatedBy = createdByUser.UserID
            LEFT JOIN User updatedByUser ON o.UpdatedBy = updatedByUser.UserID
            LEFT JOIN Warehouse w2 ON o.DestinationWarehouseID = w2.WarehouseID
            LEFT JOIN ImportReceiptHeader ih ON o.LinkedImportReceiptID = ih.ReceiptID
            WHERE o.OrderID = ?
        """;

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new ExportOrderHeader(
                    rs.getInt("OrderID"),
                    rs.getString("OrderNumber"),
                    
                    rs.getInt("CustomerID"),
                    rs.getString("CustomerName"),
                    rs.getInt("CarrierID"),
                    rs.getString("CarrierName"),
                    
                    rs.getInt("WarehouseID"),
                    rs.getString("WarehouseName"),
                    
                    rs.getInt("ShippingAddressID"),
                    new Address(
                        rs.getInt("ShippingAddressID"),
                        rs.getString("StreetAddress"),
                        rs.getString("City"),
                        rs.getString("StateProvince"),
                        rs.getString("PostalCode"),
                        rs.getString("Country")
                    ),
                    rs.getString("ExportPurpose"),
                    rs.getDate("OrderDate"),
                    rs.getDate("RequestedDeliveryDate"),
                    rs.getString("ShippingMethod"),
                    rs.getString("OrderStatus"),
                    rs.getString("Notes"),
                    rs.getString("Status"),
                    
                    rs.getObject("ReceiptID", Integer.class), // nullable,
                    rs.getInt("DestinationWarehouseID"),
                    rs.getString("DestinationWarehouseName"),
                    rs.getInt("LinkedImportReceiptID"),
                    rs.getString("ReceiptNumber"),

                    
                    rs.getInt("CreatedBy"),
                    rs.getString("CreatedByName"),
                    rs.getInt("UpdatedBy"),
                    rs.getString("UpdatedByName"),
                    rs.getTimestamp("CreatedAt"),
                    rs.getTimestamp("UpdatedAt")
                ),
                new Object[]{orderID}
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // if not found, return null gracefully
        }
    }

   @Override
    public ExportOrderHeaderCreateResponse createExportOrder(ExportOrderHeaderCreateRequest request) {
        Integer currentUserID = userContext.getCurrentUserID();
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        String sql = """
            INSERT INTO ExportOrderHeader (
                OrderNumber,
                CustomerID,
                CarrierID,
                WarehouseID,
                ShippingAddressID,
                ExportPurpose,
                OrderDate,
                RequestedDeliveryDate,
                ShippingMethod,
                OrderStatus,
                Notes,
                Status,
                ReceiptID,
                DestinationWarehouseID,
                LinkedImportReceiptID,
                CreatedBy,
                CreatedAt,
                UpdatedBy,
                UpdatedAt
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            request.orderNumber(),
            request.customerID(),
            request.carrierID(),
            request.warehouseID(),
            request.shippingAddressID(),
            request.exportPurpose(),
            request.orderDate(),
            request.requestedDeliveryDate(),
            request.shippingMethod(),
            request.orderStatus(),
            request.notes(),
            request.status(),
            request.receiptID(),
            request.destinationWarehouseID(),
            request.linkedImportReceiptID(),
            currentUserID,
            now,
            currentUserID,
            now
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        if (id == null) {
            throw new IllegalStateException("Failed to retrieve the last inserted ID.");
        }

        // Log creation activity using the record
        exportActivityLogService.log(new ExportActivityLogRequest(
            id, // receiptID (Export Order ID)
            currentUserID,
            ExportActivityTargetType.ORDER.value(),
            ExportActivityActionType.CREATE.value(),
            id,
            "Tạo đơn xuất kho: " + request.orderNumber()
        ));

        return new ExportOrderHeaderCreateResponse(id, "Export Order created successfully.");
    }

    @Override
    public ExportOrderHeaderUpdateResponse updateExportOrderPartially(Integer orderID, ExportOrderHeaderUpdateRequest request) {
        List<String> updates = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (request.orderNumber() != null) {
            updates.add("OrderNumber = ?");
            params.add(request.orderNumber());
        }
        if (request.customerID() != null) {
            updates.add("CustomerID = ?");
            params.add(request.customerID());
        }
        if (request.carrierID() != null) {
            updates.add("CarrierID = ?");
            params.add(request.carrierID());
        }
        if (request.warehouseID() != null) {
            updates.add("WarehouseID = ?");
            params.add(request.warehouseID());
        }
        if (request.shippingAddressID() != null) {
            updates.add("ShippingAddressID = ?");
            params.add(request.shippingAddressID());
        }
        if (request.orderDate() != null) {
            updates.add("OrderDate = ?");
            params.add(request.orderDate());
        }
        if (request.requestedDeliveryDate() != null) {
            updates.add("RequestedDeliveryDate = ?");
            params.add(request.requestedDeliveryDate());
        }
        if (request.shippingMethod() != null) {
            updates.add("ShippingMethod = ?");
            params.add(request.shippingMethod());
        }
        if (request.orderStatus() != null) {
            updates.add("OrderStatus = ?");
            params.add(request.orderStatus());
        }
        if (request.notes() != null) {
            updates.add("Notes = ?");
            params.add(request.notes());
        }
        if (request.status() != null) {
            updates.add("Status = ?");
            params.add(request.status());
        }
        if (request.receiptID() != null) {
            updates.add("ReceiptID = ?");
            params.add(request.receiptID());
        }
        if(request.linkedImportReceiptID() != null){
            updates.add("LinkedImportReceiptID = ?");
            params.add(request.linkedImportReceiptID());
        }

        if (updates.isEmpty()) {
            throw new IllegalArgumentException("No valid fields provided to update.");
        }

        // Always update updatedBy and updatedAt
        updates.add("UpdatedBy = ?");
        params.add(userContext.getCurrentUserID());
        updates.add("UpdatedAt = CURRENT_TIMESTAMP");

        // Where clause
        params.add(orderID);

        String sql = "UPDATE ExportOrderHeader SET " + String.join(", ", updates) + " WHERE OrderID = ?";

        int rows = jdbcTemplate.update(sql, params.toArray());
        if (rows == 0) {
            throw new IllegalStateException("Update failed: no rows affected.");
        }

        String message = "Export Order updated successfully.";
        return new ExportOrderHeaderUpdateResponse(orderID, message);
    }

    @Override
    public List<ExportOrderHeader> getExportOrdersByDateRange(LocalDate fromDate, LocalDate toDate) {
        String sql = """
            SELECT
                o.OrderID,
                o.OrderNumber,
                o.CustomerID,
                customer.ContactName AS CustomerName,
                o.CarrierID,
                carrier.ContactName AS CarrierName,
                o.WarehouseID,
                w.Name AS WarehouseName,
                o.ShippingAddressID,
                a.StreetAddress,
                a.City,
                a.StateProvince,
                a.PostalCode,
                a.Country,
                o.ExportPurpose,
                o.OrderDate,
                o.RequestedDeliveryDate,
                o.ShippingMethod,
                o.OrderStatus,
                o.Notes,
                o.Status,
                o.ReceiptID,
                o.CreatedBy,
                createdByUser.FullName AS CreatedByName,
                o.UpdatedBy,
                updatedByUser.FullName AS UpdatedByName,
                o.CreatedAt,
                o.UpdatedAt,
                o.DestinationWarehouseID,
                w2.Name AS DestinationWarehouseName,
                o.LinkedImportReceiptID,
                ih.ReceiptNumber
            FROM ExportOrderHeader o
            LEFT JOIN Party customer ON o.CustomerID = customer.PartyID
            LEFT JOIN Party carrier ON o.CarrierID = carrier.PartyID
            LEFT JOIN Warehouse w ON o.WarehouseID = w.WarehouseID
            LEFT JOIN Address a ON o.ShippingAddressID = a.AddressID
            LEFT JOIN User createdByUser ON o.CreatedBy = createdByUser.UserID
            LEFT JOIN User updatedByUser ON o.UpdatedBy = updatedByUser.UserID
            LEFT JOIN Warehouse w2 ON o.DestinationWarehouseID = w2.WarehouseID
            LEFT JOIN ImportReceiptHeader ih ON o.LinkedImportReceiptID = ih.ReceiptID
            WHERE o.OrderDate BETWEEN ? AND ?
            AND o.Status = 'active'
            ORDER BY o.OrderDate DESC
            """;

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ExportOrderHeader(
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getInt("CustomerID"),
                rs.getString("CustomerName"),
                rs.getInt("CarrierID"),
                rs.getString("CarrierName"),
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
                rs.getInt("ShippingAddressID"),
                new Address(
                    rs.getInt("ShippingAddressID"),
                    rs.getString("StreetAddress"),
                    rs.getString("City"),
                    rs.getString("StateProvince"),
                    rs.getString("PostalCode"),
                    rs.getString("Country")
                ),
                rs.getString("ExportPurpose"),
                rs.getDate("OrderDate"),
                rs.getDate("RequestedDeliveryDate"),
                rs.getString("ShippingMethod"),
                rs.getString("OrderStatus"),
                rs.getString("Notes"),
                rs.getString("Status"),
                rs.getObject("ReceiptID", Integer.class),
                rs.getInt("DestinationWarehouseID"),
                rs.getString("DestinationWarehouseName"),
                rs.getInt("LinkedImportReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getTimestamp("UpdatedAt")
            ),
            new Object[]{fromDate, toDate}
        );
    }
}
