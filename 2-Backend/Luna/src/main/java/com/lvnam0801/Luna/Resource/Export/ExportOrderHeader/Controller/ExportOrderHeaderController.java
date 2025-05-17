package com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Controller;

import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeader;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderCreateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderCreateResponse;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderUpdateResponse;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Service.ExportOrderHeaderService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/export-order")
public class ExportOrderHeaderController {
    private final ExportOrderHeaderService exportOrderHeaderService;
    public ExportOrderHeaderController(ExportOrderHeaderService exportOrderHeaderService) {
        this.exportOrderHeaderService = exportOrderHeaderService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllExportOrders() {
        try {
            ExportOrderHeader[] orders = exportOrderHeaderService.getAllExportOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch export orders: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{orderID}")
    public ResponseEntity<?> getExportOrderById(@PathVariable Integer orderID) {
        try {
            ExportOrderHeader order = exportOrderHeaderService.getExportOrderById(orderID);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("Export order not found with ID: " + orderID);
            }
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch export order: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExportOrder(@RequestBody ExportOrderHeaderCreateRequest request) {
        try {
            ExportOrderHeaderCreateResponse response = exportOrderHeaderService.createExportOrder(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create export order: " + e.getMessage());
        }
    }

    @PatchMapping("/update/{orderID}")
    public ResponseEntity<?> updateExportOrder(
        @PathVariable Integer orderID,
        @RequestBody ExportOrderHeaderUpdateRequest request
    ) {
        try {
            ExportOrderHeaderUpdateResponse response = exportOrderHeaderService.updateExportOrderPartially(orderID, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to update export order: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-date-range")
    public ResponseEntity<?> getExportOrdersByDateRange(
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            List<ExportOrderHeader> orders = exportOrderHeaderService.getExportOrdersByDateRange(fromDate, toDate);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch export orders by date range: " + e.getMessage());
        }
    }
}