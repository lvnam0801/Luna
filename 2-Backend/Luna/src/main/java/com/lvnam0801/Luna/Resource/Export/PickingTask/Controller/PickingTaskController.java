package com.lvnam0801.Luna.Resource.Export.PickingTask.Controller;

import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTask;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/picking-task")
public class PickingTaskController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-by-order-line/{orderLineItemID}")
    public PickingTask[] getByOrderLine(@PathVariable Integer orderLineItemID) {
        String sql = "SELECT * FROM PickingTask WHERE OrderLineItemID = ?";

        return jdbcTemplate.query(
            sql,
            new Object[]{orderLineItemID},
            (rs, rowNum) -> new PickingTask(
                rs.getInt("PickingTaskID"),
                rs.getInt("OrderLineItemID"),
                rs.getInt("LocationID"),
                rs.getObject("PickedBy", Integer.class),
                rs.getInt("QuantityPicked"),
                rs.getDate("PickedDate"),
                rs.getString("Status")
            )
        ).toArray(PickingTask[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<PickingTask> createPickingTask(@RequestBody PickingTaskRequest request) {
        String sql = """
            INSERT INTO PickingTask (OrderLineItemID, LocationID, PickedBy, QuantityPicked, PickedDate, Status)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.orderLineItemID(),
            request.locationID(),
            request.pickedBy(),
            request.quantityPicked(),
            request.pickedDate(),
            request.status()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        PickingTask task = new PickingTask(
            id,
            request.orderLineItemID(),
            request.locationID(),
            request.pickedBy(),
            request.quantityPicked(),
            request.pickedDate(),
            request.status()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
}