package com.lvnam0801.Luna.Resource.Export.PickingTask.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityActionType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityTargetType;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service.ExportActivityLogService;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTask;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskCreateRequest;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskCreateResponse;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskUpdateResponse;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PickingTaskServiceImpl implements PickingTaskService {

    private JdbcTemplate jdbcTemplate;
    private UserContext userContext;
    private ExportActivityLogService activityLogService;

    public PickingTaskServiceImpl(
            JdbcTemplate jdbcTemplate,
            UserContext userContext,
            ExportActivityLogService activityLogService
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.userContext = userContext;
        this.activityLogService = activityLogService;
    }

    @Override
    public PickingTask[] getByOrderLineItemID(Integer orderLineItemID) {
        String sql = """
            SELECT pt.*, s.SKU, l.LocationName AS PickFromLocationName,
                   u1.FullName AS CreatedByName,
                   u2.FullName AS UpdatedByName,
                   u3.FullName AS PickedByName
            FROM PickingTask pt
            JOIN SKUItem s ON pt.SKUItemID = s.ItemID
            JOIN Location l ON pt.PickFromLocationID = l.LocationID
            LEFT JOIN User u1 ON pt.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON pt.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON pt.PickedBy = u3.UserID
            WHERE pt.OrderLineItemID = ?
        """;

        List<PickingTask> tasks = jdbcTemplate.query(sql, new Object[]{orderLineItemID}, (rs, rowNum) -> new PickingTask(
                rs.getInt("PickingTaskID"),
                rs.getString("PickingNumber"),
                rs.getInt("OrderLineItemID"),
                rs.getInt("SKUItemID"),
                rs.getString("SKU"),
                rs.getInt("PickedQuantity"),
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
        ));

        return tasks.toArray(new PickingTask[0]);
    }

    @Override
    public PickingTask getByID(Integer pickingTaskID) {
        return getByPickingTaskID(pickingTaskID);
    }

    private PickingTask getByPickingTaskID(Integer id) {
        String sql = """
            SELECT pt.*, s.SKU, l.LocationName AS PickFromLocationName,
                   u1.FullName AS CreatedByName,
                   u2.FullName AS UpdatedByName,
                   u3.FullName AS PickedByName
            FROM PickingTask pt
            JOIN SKUItem s ON pt.SKUItemID = s.ItemID
            JOIN Location l ON pt.PickFromLocationID = l.LocationID
            LEFT JOIN User u1 ON pt.CreatedBy = u1.UserID
            LEFT JOIN User u2 ON pt.UpdatedBy = u2.UserID
            LEFT JOIN User u3 ON pt.PickedBy = u3.UserID
            WHERE pt.PickingTaskID = ?
        """;

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new PickingTask(
                rs.getInt("PickingTaskID"),
                rs.getString("PickingNumber"),
                rs.getInt("OrderLineItemID"),
                rs.getInt("SKUItemID"),
                rs.getString("SKU"),
                rs.getInt("PickedQuantity"),
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
        ));
    }

    @Override
    public PickingTaskCreateResponse createPickingTask(PickingTaskCreateRequest request) {
        Integer userID = userContext.getCurrentUserID();

        String sql = """
            INSERT INTO PickingTask (PickingNumber, OrderLineItemID, SKUItemID, PickedQuantity, PickFromLocationID,
                                     Status, PickedBy, PickedDate, CreatedBy, UpdatedBy)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                request.pickingNumber(),
                request.orderLineItemID(),
                request.skuItemID(),
                request.pickedQuantity(),
                request.pickFromLocationID(),
                request.status(),
                userID,
                request.pickedDate(),
                userID,
                userID
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        if (id == null) throw new IllegalStateException("Could not retrieve new PickingTask ID");

        activityLogService.log(new ExportActivityLogRequest(
                getOrderIDFromPickingTaskID(id),
                userID,
                ExportActivityTargetType.PICKING_TASK.value(),
                ExportActivityActionType.CREATE.value(),
                id,
                "Tạo picking task cho dòng xuất kho " + request.pickingNumber()
        ));

        return new PickingTaskCreateResponse(id, "Picking Task created successfully.");
    }

    @Override
    public PickingTaskUpdateResponse updatePickingTaskPartially(Integer pickingTaskID, PickingTaskUpdateRequest request) {
        Integer userID = userContext.getCurrentUserID();

        List<String> updates = new java.util.ArrayList<>();
        List<Object> params = new java.util.ArrayList<>();

        if (request.pickedQuantity() != null) {
            updates.add("PickedQuantity = ?");
            params.add(request.pickedQuantity());
        }
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

        if (updates.isEmpty()) throw new IllegalArgumentException("No valid fields provided to update.");

        updates.add("UpdatedBy = ?");
        params.add(userID);

        String sql = "UPDATE PickingTask SET " + String.join(", ", updates) +
                ", UpdatedAt = CURRENT_TIMESTAMP WHERE PickingTaskID = ?";
        params.add(pickingTaskID);

        int rows = jdbcTemplate.update(sql, params.toArray());
        if (rows == 0) throw new IllegalStateException("Update failed. No rows affected.");

        String pickingNumber = getPickingNumberByID(pickingTaskID);
        if (pickingNumber == null) throw new IllegalStateException("Could not retrieve PickingNumber");

        activityLogService.log(new ExportActivityLogRequest(
                getOrderIDFromPickingTaskID(pickingTaskID),
                userID,
                ExportActivityTargetType.PICKING_TASK.value(),
                ExportActivityActionType.UPDATE.value(),
                pickingTaskID,
                "Cập nhật picking task " + pickingNumber
        ));

        return new PickingTaskUpdateResponse(pickingTaskID, "Picking Task updated successfully.");
    }

    private Integer getOrderIDFromPickingTaskID(Integer taskID) {
        return jdbcTemplate.queryForObject("""
            SELECT el.OrderID
            FROM PickingTask pt
            JOIN ExportOrderLineItem el ON pt.OrderLineItemID = el.OrderLineItemID
            WHERE pt.PickingTaskID = ?
        """, new Object[]{taskID}, Integer.class);
    }

    private String getPickingNumberByID(Integer taskID) {
        return jdbcTemplate.queryForObject("SELECT PickingNumber FROM PickingTask WHERE PickingTaskID = ?",
                new Object[]{taskID}, String.class);
    }
}
