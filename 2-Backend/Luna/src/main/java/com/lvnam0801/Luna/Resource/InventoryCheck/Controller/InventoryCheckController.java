package com.lvnam0801.Luna.Resource.InventoryCheck.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheck;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckCreateRequest;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckCreateResponse;
import com.lvnam0801.Luna.Resource.InventoryCheck.Service.InventoryCheckService;

@RestController
@RequestMapping("/api/inventory-check")
public class InventoryCheckController {

    private final InventoryCheckService inventoryCheckService;

    public InventoryCheckController(InventoryCheckService inventoryCheckService) {
        this.inventoryCheckService = inventoryCheckService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody InventoryCheckCreateRequest request) {
        try {
            InventoryCheckCreateResponse response = inventoryCheckService.createInventoryCheck(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error creating inventory check: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to create inventory check");
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            InventoryCheck result = inventoryCheckService.getById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.err.println("Error fetching inventory check by ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve inventory check with ID: " + id);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        try {
            List<InventoryCheck> list = inventoryCheckService.getInventoryChecks();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            System.err.println("Error retrieving all inventory checks: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve inventory checks");
        }
    }

    @GetMapping("/get-by-warehouse")
    public ResponseEntity<?> getByWarehouse(
        @RequestParam("warehouseID") Integer warehouseID) {
        
        try {
            List<InventoryCheck> list = inventoryCheckService.getInventoryChecks(warehouseID);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            System.err.println("Error retrieving all inventory checks: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve inventory checks");
        }
    }

    @GetMapping("/get-by-date-range")
    public ResponseEntity<?> getByDateRange(
        @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
        @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate){
        try {
            List<InventoryCheck> list = inventoryCheckService.getInventoryChecks(fromDate, toDate);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            System.err.println("Error retrieving all inventory checks: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve inventory checks");
        }
    }

    @GetMapping("/get-by-warehouse-and-date-range")
    public ResponseEntity<?> getByWarehouseAndDateRange(
        @RequestParam("warehouseID") Integer warehouseID,
        @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
        @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        try {
            List<InventoryCheck> list = inventoryCheckService.getInventoryChecks(warehouseID, fromDate, toDate);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            System.err.println("Error retrieving all inventory checks: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to retrieve inventory checks");
        }
    }
}