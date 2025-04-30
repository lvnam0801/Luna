package com.lvnam0801.Luna.Resource.Export.PickingTask.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTask;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskCreateRequest;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskCreateResponse;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskUpdateResponse;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Service.PickingTaskService;

@RestController
@RequestMapping("/api/picking-task")
public class PickingTaskController {

    private final PickingTaskService pickingTaskService;

    public PickingTaskController(PickingTaskService pickingTaskService) {
        this.pickingTaskService = pickingTaskService;
    }

    @GetMapping("/get-by-order-line/{orderLineItemID}")
    public ResponseEntity<?> getByOrderLineItemID(@PathVariable Integer orderLineItemID) {
        try {
            PickingTask[] tasks = pickingTaskService.getByOrderLineItemID(orderLineItemID);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch picking tasks: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{pickingTaskID}")
    public ResponseEntity<?> getByID(@PathVariable Integer pickingTaskID) {
        try {
            PickingTask task = pickingTaskService.getByID(pickingTaskID);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch picking task: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PickingTaskCreateRequest request) {
        try {
            PickingTaskCreateResponse response = pickingTaskService.createPickingTask(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create picking task: " + e.getMessage());
        }
    }

    @PatchMapping("/update/{pickingTaskID}")
    public ResponseEntity<?> update(@PathVariable Integer pickingTaskID,
                                    @RequestBody PickingTaskUpdateRequest request) {
        try {
            PickingTaskUpdateResponse response = pickingTaskService.updatePickingTaskPartially(pickingTaskID, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update picking task: " + e.getMessage());
        }
    }
}
