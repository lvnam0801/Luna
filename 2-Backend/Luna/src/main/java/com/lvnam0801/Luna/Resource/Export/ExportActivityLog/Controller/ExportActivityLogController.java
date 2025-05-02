package com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLog;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service.ExportActivityLogService;

@RestController
@RequestMapping("/api/export-activity-log")
public class ExportActivityLogController {

    private final ExportActivityLogService exportActivityLogService;

    public ExportActivityLogController(ExportActivityLogService exportActivityLogService) {
        this.exportActivityLogService = exportActivityLogService;
    }

    @GetMapping("/get-by-order/{orderID}")
    public ResponseEntity<?> getLogsByOrder(@PathVariable Integer orderID) {
        try {
            List<ExportActivityLog> logs = exportActivityLogService.getLogsByOrder(orderID);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to retrieve export activity logs: " + e.getMessage());
        }
    }
}