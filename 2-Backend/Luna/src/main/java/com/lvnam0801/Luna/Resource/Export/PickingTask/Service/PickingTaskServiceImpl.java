package com.lvnam0801.Luna.Resource.Export.PickingTask.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Service.SKUItemService;
import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityActionType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityTargetType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service.ExportActivityLogService;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItem;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Service.ExportOrderLineItemService;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTask;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskCreateRequest;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskCreateResponse;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskUpdateResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class PickingTaskServiceImpl implements PickingTaskService {

    private final JdbcTemplate jdbcTemplate;
    private final SKUItemService skuItemService;
    private final UserContext userContext;
    private final ExportOrderLineItemService exportOrderLineItemService;
    private final ExportActivityLogService activityLogService;

    public PickingTaskServiceImpl(
            JdbcTemplate jdbcTemplate,
            SKUItemService skuItemService,
            UserContext userContext,
            ExportOrderLineItemService exportOrderLineItemService,
            ExportActivityLogService activityLogService
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.skuItemService = skuItemService;
        this.userContext = userContext;
        this.exportOrderLineItemService = exportOrderLineItemService;
        this.activityLogService = activityLogService;
    }

    @Override
    public PickingTask[] getByOrderLineItemID(Integer orderLineItemID) {
        String sql = """
            SELECT pt.*,
                    eh.OrderNumber AS OrderNumber,
                    el.LotNumber AS LotNumber,
                    s.SKU,
                    w.Name AS WarehouseName,
                    l.LocationName AS PickFromLocationName,
                    u1.FullName AS CreatedByName,
                    u2.FullName AS UpdatedByName,
                    u3.FullName AS PickedByName
            FROM PickingTask pt
            LEFT JOIN ExportOrderHeader eh ON pt.OrderID = eh.OrderID
            LEFT JOIN ExportOrderLineItem el ON pt.OrderLineItemID = el.OrderLineItemID
            LEFT JOIN SKUItem s ON pt.SKUItemID = s.ItemID
            LEFT JOIN Warehouse w ON pt.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON pt.PickFromLocationID = l.LocationID
            LEFT JOIN User u1 ON pt.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON pt.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON pt.PickedBy = u3.UserID
            WHERE pt.OrderLineItemID = ?
        """;

        List<PickingTask> tasks = jdbcTemplate.query(sql, (rs, rowNum) -> new PickingTask(
                rs.getInt("PickingTaskID"),
                rs.getString("PickingNumber"),
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getInt("OrderLineItemID"),
                rs.getString("LotNumber"),
                rs.getInt("SKUItemID"),
                rs.getString("SKU"),
                rs.getInt("PickedQuantity"),
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
                rs.getInt("PickFromLocationID"),
                rs.getString("PickFromLocationName"),
                rs.getString("Status"),
                rs.getInt("PickedBy"),
                rs.getString("PickedByName"),
                rs.getDate("PickedDate"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
            ),
            new Object[]{orderLineItemID}
        );

        return tasks.toArray(new PickingTask[0]);
    }

    @Override
    public PickingTask getByID(Integer pickingTaskID) {
        return getByPickingTaskID(pickingTaskID);
    }

    private PickingTask getByPickingTaskID(Integer id) {
        String sql = """
            SELECT pt.*,
                    eh.OrderNumber AS OrderNumber,
                    el.LotNumber AS LotNumber,
                    s.SKU,
                    w.Name AS WarehouseName,
                    l.LocationName AS PickFromLocationName,
                    u1.FullName AS CreatedByName,
                    u2.FullName AS UpdatedByName,
                    u3.FullName AS PickedByName
            FROM PickingTask pt
            LEFT JOIN ExportOrderHeader eh ON pt.OrderID = eh.OrderID
            LEFT JOIN ExportOrderLineItem el ON pt.OrderLineItemID = el.OrderLineItemID
            LEFT JOIN SKUItem s ON pt.SKUItemID = s.ItemID
            LEFT JOIN Warehouse w ON pt.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON pt.PickFromLocationID = l.LocationID
            LEFT JOIN User u1 ON pt.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON pt.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON pt.PickedBy = u3.UserID
            WHERE pt.PickingTaskID = ?
        """;

        PickingTask pickingTask = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new PickingTask(
                rs.getInt("PickingTaskID"),
                rs.getString("PickingNumber"),
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getInt("OrderLineItemID"),
                rs.getString("LotNumber"),
                rs.getInt("SKUItemID"),
                rs.getString("SKU"),
                rs.getInt("PickedQuantity"),
                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
                rs.getInt("PickFromLocationID"),
                rs.getString("PickFromLocationName"),
                rs.getString("Status"),
                rs.getInt("PickedBy"),
                rs.getString("PickedByName"),
                rs.getDate("PickedDate"),
                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
        ), new Object[]{id});

        return pickingTask;
    }

    @Override
    @Transactional
    public PickingTaskCreateResponse createPickingTask(PickingTaskCreateRequest request) {
        Integer userID = userContext.getCurrentUserID();

        // Step 1: Reserve quantity in SKUItem
        skuItemService.reserveQuantity(
            request.skuItemID(),
            request.pickedQuantity()
        );

        // Step 2: Insert picking task
        String sql = """
            INSERT INTO PickingTask (
                PickingNumber,
                OrderID, 
                OrderLineItemID, 
                SKUItemID,
                PickedQuantity, 
                WarehouseID,
                PickFromLocationID,
                Status, 
                PickedBy, 
                PickedDate,
                CreatedBy, 
                UpdatedBy
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            request.pickingNumber(),
            request.orderID(),
            request.orderLineItemID(),
            request.skuItemID(),
            request.pickedQuantity(),
            request.warehouseID(),
            request.pickFromLocationID(),
            request.status(),
            userID,
            request.pickedDate(),
            userID,
            userID
        );

        // Step 3: Get new ID
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        if (id == null)
        {
            throw new IllegalStateException("Could not retrieve new PickingTask ID");
        }
        
        ExportOrderLineItem exportOrderLineItem = exportOrderLineItemService.getByID(request.orderLineItemID());
        if (exportOrderLineItem == null)
        {
            throw new IllegalStateException("Could not find the export order line item: " + request.orderLineItemID());
        }
        
        // Step 4: Log activity
        activityLogService.log(new ExportActivityLogRequest(
            exportOrderLineItem.orderID(),
            userID,
            ExportActivityTargetType.PICKING_TASK.value(),
            ExportActivityActionType.CREATE.value(),
            id,
            "Tạo nhiệm vụ lấy hàng " + request.pickingNumber() + " cho lô hàng " + exportOrderLineItem.lotNumber()
        ));

        return new PickingTaskCreateResponse(id, "Picking Task created successfully.");
    }

   @Override
    @Transactional
    public PickingTaskUpdateResponse updatePickingTaskPartially(Integer pickingTaskID, PickingTaskUpdateRequest request) {
        Integer userID = userContext.getCurrentUserID();

        // Step 1: Fetch the original PickingTask first
        PickingTask originalTask = getByPickingTaskID(pickingTaskID);
        if (originalTask == null) {
            throw new IllegalArgumentException("PickingTask not found with ID: " + pickingTaskID);
        }

        // Step 2: If status is changing to 'cancelled', release reserved quantity
        if ("cancelled".equalsIgnoreCase(request.status())
            && !"cancelled".equalsIgnoreCase(originalTask.status())) {
            // Step 2.1: Log the rollback
            rollbackSKUQuantityOnCancel(pickingTaskID, originalTask, userID);
        }

        // Step 3: Build SQL for partial update
        List<String> updates = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (request.pickFromLocationID() != null) {
            updates.add("PickFromLocationID = ?");
            params.add(request.pickFromLocationID());
        }
        if (request.status() != null) {
            updates.add("Status = ?");
            params.add(request.status());
        }
        if (request.pickedDate() != null) {
            updates.add("PickedDate = ?");
            params.add(request.pickedDate());
        }

        updates.add("UpdatedBy = ?");
        updates.add("UpdatedAt = CURRENT_TIMESTAMP");
        params.add(userID);
        params.add(pickingTaskID);

        String sql = "UPDATE PickingTask SET " + String.join(", ", updates) + " WHERE PickingTaskID = ?";

        int rows = jdbcTemplate.update(sql, params.toArray());
        if (rows == 0) throw new IllegalStateException("Update failed");

        String pickingNumber = getPickingNumberByID(pickingTaskID);
        if (pickingNumber == null) {
            throw new IllegalStateException("Could not retrieve PickingNumber for ID: " + pickingTaskID);
        }
        // Step 4: Log activity
        activityLogService.log(new ExportActivityLogRequest(
            getOrderIDFromPickingTaskID(pickingTaskID),
            userID,
            ExportActivityTargetType.PICKING_TASK.value(),
            ExportActivityActionType.UPDATE.value(),
            pickingTaskID,
            "Cập nhật nhiệm vụ lấy hàng " + pickingNumber
        ));

        return new PickingTaskUpdateResponse(pickingTaskID, "Picking Task updated successfully.");
    }

    private Integer getOrderIDFromPickingTaskID(Integer taskID) {
        return jdbcTemplate.queryForObject("""
            SELECT el.OrderID
            FROM PickingTask pt
            JOIN ExportOrderLineItem el ON pt.OrderLineItemID = el.OrderLineItemID
            WHERE pt.PickingTaskID = ?
        """, Integer.class, new Object[]{taskID});
    }

    private String getPickingNumberByID(Integer taskID) {
        return jdbcTemplate.queryForObject("SELECT PickingNumber FROM PickingTask WHERE PickingTaskID = ?",
                String.class, new Object[]{taskID});
    }

    private void rollbackSKUQuantityOnCancel(Integer pickingTaskID, PickingTask originalTask, Integer userID) {
        // Step 1: Restore quantity
        skuItemService.increaseSKUItemQuantity(
            originalTask.skuItemID(),
            originalTask.pickedQuantity()
        );
    
        // Step 2: Log the rollback
        activityLogService.log(new ExportActivityLogRequest(
            getOrderIDFromPickingTaskID(pickingTaskID),
            userID,
            ExportActivityTargetType.SKU_ITEM.value(),
            ExportActivityActionType.UPDATE.value(),
            originalTask.skuItemID(),
            "Khôi phục số lượng SKU vì huỷ picking task: " + pickingTaskID
        ));
    }
}
