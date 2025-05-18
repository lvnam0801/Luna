package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityActionType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityTargetType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service.ExportActivityLogService;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItem;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemCreateResponse;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemUpdateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ExportOrderLineItemServiceImpl implements ExportOrderLineItemService {
    
    private final JdbcTemplate jdbcTemplate;
    private final UserContext userContext;
    private final ExportActivityLogService exportActivityLogService;
    
    public ExportOrderLineItemServiceImpl(JdbcTemplate jdbcTemplate, UserContext userContext, ExportActivityLogService exportActivityLogService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userContext = userContext;
        this.exportActivityLogService = exportActivityLogService;
    }

    @Override
    public ExportOrderLineItem[] getByOrderID(Integer orderID) {
        String sql = """
            SELECT e.*,
                w.Name AS WarehouseName,
                eh.OrderNumber AS OrderNumber, 
                p.Name AS ProductName,
                u1.FullName AS CreatedByName, 
                u2.FullName AS UpdatedByName 
            FROM ExportOrderLineItem e
            LEFT JOIN Warehouse w ON e.WarehouseID = w.WarehouseID
            LEFT JOIN ExportOrderHeader eh ON e.OrderID = eh.OrderID
            LEFT JOIN Product p ON e.ProductID = p.ProductID
            LEFT JOIN User u1 ON e.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON e.UpdatedBy = u2.UserID
            WHERE e.OrderID = ?
        """;

        List<ExportOrderLineItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> new ExportOrderLineItem(
            rs.getInt("OrderLineItemID"),
            rs.getInt("WarehouseID"),
            rs.getString("WarehouseName"),
            rs.getInt("OrderID"),
            rs.getString("OrderNumber"),
            rs.getInt("ProductID"),
            rs.getString("ProductName"),
            rs.getString("LineItemNumber"),
            rs.getString("SKU"),
            rs.getInt("ExportedQuantity"),
            rs.getString("LotNumber"),
            rs.getDate("ExpirationDate"),
            rs.getBigDecimal("UnitPrice"),
            rs.getString("Status"),
            rs.getInt("CreatedBy"),
            rs.getString("CreatedByName"),
            rs.getTimestamp("CreatedAt"),
            rs.getInt("UpdatedBy"),
            rs.getString("UpdatedByName"),
            rs.getTimestamp("UpdatedAt")
        ),
        new Object[]{orderID});
        return items.toArray(new ExportOrderLineItem[0]);
    }

    @Override
    public ExportOrderLineItem getByID(Integer orderLineItemID) {
        String sql = """
            SELECT e.*,
                w.Name AS WarehouseName,
                eh.OrderNumber AS OrderNumber,  
                p.Name,
                u1.FullName AS CreatedByName, 
                u2.FullName AS UpdatedByName 
            FROM ExportOrderLineItem e
            LEFT JOIN Warehouse w ON e.WarehouseID = w.WarehouseID
            LEFT JOIN ExportOrderHeader eh ON e.OrderID = eh.OrderID
            LEFT JOIN Product p ON e.ProductID = p.ProductID
            LEFT JOIN User u1 ON e.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON e.UpdatedBy = u2.UserID
            WHERE e.OrderLineItemID = ?
        """;

        ExportOrderLineItem item = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new ExportOrderLineItem(
            rs.getInt("OrderLineItemID"),
            rs.getInt("WarehouseID"),
            rs.getString("WarehouseName"),
            rs.getInt("OrderID"),
            rs.getString("OrderNumber"),
            rs.getInt("ProductID"),
            rs.getString("Name"),
            rs.getString("LineItemNumber"),
            rs.getString("SKU"),
            rs.getInt("ExportedQuantity"),
            rs.getString("LotNumber"),
            rs.getDate("ExpirationDate"),
            rs.getBigDecimal("UnitPrice"),
            rs.getString("Status"),
            rs.getInt("CreatedBy"),
            rs.getString("CreatedByName"),
            rs.getTimestamp("CreatedAt"),
            rs.getInt("UpdatedBy"),
            rs.getString("UpdatedByName"),
            rs.getTimestamp("UpdatedAt")
        ),
        new Object[]{orderLineItemID});
        if (item == null) {
            throw new NoSuchElementException("No line item found with ID: " + orderLineItemID);
        }
        return item;
    }


    @Override
    public ExportOrderLineItemCreateResponse createOrderLineItem(ExportOrderLineItemCreateRequest request) {
        String lineItemNumber = "EOLI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Integer userID = userContext.getCurrentUserID();
    
        String sql = """
            INSERT INTO ExportOrderLineItem (
                WarehouseID, OrderID, ProductID, LineItemNumber, SKU, ExportedQuantity, LotNumber,
                ExpirationDate, UnitPrice, Status, CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
    
        jdbcTemplate.update(sql,
            request.warehouseID(),
            request.orderID(),
            request.productID(),
            lineItemNumber,
            request.SKU(),
            request.exportedQuantity(),
            request.lotNumber(),
            request.expirationDate(),
            request.unitPrice(),
            request.status(),
            userID,
            userID
        );
    
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        if (id == null) {
            throw new IllegalStateException("Failed to retrieve the last inserted ID.");
        }
        String content = "Tạo dòng xuất kho " + lineItemNumber;

        // Log activity
        exportActivityLogService.log(new ExportActivityLogRequest(
            request.orderID(),
            userID,
            ExportActivityTargetType.LINE_ITEM.value(),
            ExportActivityActionType.CREATE.value(),
            id,
            content
        ));
    
        return new ExportOrderLineItemCreateResponse(id, "Export order line item created successfully.");
    }

    @Override
    public ExportOrderLineItemUpdateResponse updateOrderLineItemPartially(Integer lineItemID, ExportOrderLineItemUpdateRequest request) {
        Integer userID = userContext.getCurrentUserID();
    
        List<String> updates = new ArrayList<>();
        List<Object> params = new ArrayList<>();
    
        if (request.lotNumber() != null) {
            updates.add("LotNumber = ?");
            params.add(request.lotNumber());
        }
        if (request.expirationDate() != null) {
            updates.add("ExpirationDate = ?");
            params.add(request.expirationDate());
        }
        if (request.unitPrice() != null) {
            updates.add("UnitPrice = ?");
            params.add(request.unitPrice());
        }
        if (request.status() != null) {
            updates.add("Status = ?");
            params.add(request.status());
        }
    
        if (updates.isEmpty()) {
            throw new IllegalArgumentException("No valid fields provided to update.");
        }
    
        updates.add("UpdatedBy = ?");
        params.add(userID);
        updates.add("UpdatedAt = CURRENT_TIMESTAMP");
    
        String sql = "UPDATE ExportOrderLineItem SET " + String.join(", ", updates) + " WHERE OrderLineItemID = ?";
        params.add(lineItemID);
    
        jdbcTemplate.update(sql, params.toArray());
    
        // Retrieve OrderID for logging
        Integer orderID = jdbcTemplate.queryForObject("""
            SELECT OrderID FROM ExportOrderLineItem WHERE OrderLineItemID = ?
        """, Integer.class, lineItemID);
        
        if (orderID == null) {
            throw new NoSuchElementException("Order ID not found for the given line item ID.");
        }
        // String lineItemNumber = getOrderLineNumberByID(lineItemID);
        ExportOrderLineItem exportOrderLineItem = getByID(lineItemID);
        String content = "Cập nhật trạng thái lô hàng " + exportOrderLineItem.lotNumber();
        // Log activity
        exportActivityLogService.log(new ExportActivityLogRequest(
            orderID,
            userID,
            ExportActivityTargetType.LINE_ITEM.value(),
            ExportActivityActionType.UPDATE.value(),
            lineItemID,
            content
        ));
    
        return new ExportOrderLineItemUpdateResponse(lineItemID, "Export order line item updated successfully.");
    }

    private String getOrderLineNumberByID(Integer lineItemID) {
        String sql = "SELECT LineItemNumber FROM ExportOrderLineItem WHERE OrderLineItemID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, new Object[]{lineItemID});
    }
}
