package com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Controller;

import java.util.List;

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
    public ResponseEntity<List<ImportActivityLog>> getLogsByReceipt(@PathVariable int receiptId) {
        List<ImportActivityLog> logs = importActivityLogService.getLogsByReceipt(receiptId);
        System.out.println("Hit api get import activity log: " + receiptId);
        return ResponseEntity.ok(logs);
    }
}