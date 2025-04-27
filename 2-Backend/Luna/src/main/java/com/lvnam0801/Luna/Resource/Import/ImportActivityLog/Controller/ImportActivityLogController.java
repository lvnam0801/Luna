package com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Controller;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityLog;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;

@RestController
@RequestMapping("/api/import-activity-log")
public class ImportActivityLogController {

    private final ImportActivityLogService importActivityLogService;

    public ImportActivityLogController(ImportActivityLogService importActivityLogService) {
        this.importActivityLogService = importActivityLogService;
    }

    @GetMapping("/receipt/{receiptId}")
    public ResponseEntity<?> getLogsByReceipt(@PathVariable int receiptId) {
        try {
            if (receiptId <= 0) {
                return ResponseEntity.badRequest().body("Missing or invalid receiptId");
            }

            List<ImportActivityLog> logs = importActivityLogService.getLogsByReceipt(receiptId);
            System.out.println("Hit API get import activity log: " + receiptId);
            return ResponseEntity.ok(logs);
        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error while retrieving activity logs: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }
}