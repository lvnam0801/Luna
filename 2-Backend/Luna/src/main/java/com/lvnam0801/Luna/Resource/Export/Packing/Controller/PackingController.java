package com.lvnam0801.Luna.Resource.Export.Packing.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingDetailCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Service.PackingService;

@RestController
@RequestMapping("/api/packing")
public class PackingController {

    private final PackingService packingService;

    public PackingController(PackingService packingService) {
        this.packingService = packingService;
    }

    @GetMapping("/get-by-order-line/{orderLineItemID}")
    public ResponseEntity<?> getByOrderLineItemID(@PathVariable Integer orderLineItemID) {
        try {
            return ResponseEntity.ok(packingService.getByOrderID(orderLineItemID));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to fetch packing records: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{packingID}")
    public ResponseEntity<?> getByID(@PathVariable Integer packingID) {
        try {
            return ResponseEntity.ok(packingService.getByID(packingID));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to fetch packing record: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPacking(@RequestBody PackingCreateRequest request) {
        try {
            return ResponseEntity.ok(packingService.createPacking(request));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to create packing: " + e.getMessage());
        }
    }

    @PatchMapping("/update/{packingID}")
    public ResponseEntity<?> updatePacking(@PathVariable Integer packingID, @RequestBody PackingUpdateRequest request) {
        try {
            return ResponseEntity.ok(packingService.updatePacking(packingID, request));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to update packing: " + e.getMessage());
        }
    }

    @GetMapping("/details/{packingID}")
    public ResponseEntity<?> getDetailsByPackingID(@PathVariable Integer packingID) {
        try {
            return ResponseEntity.ok(packingService.getDetailsByPackingID(packingID));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to fetch packing details: " + e.getMessage());
        }
    }

    @PostMapping("/details/create")
    public ResponseEntity<?> addPackingDetail(@RequestBody PackingDetailCreateRequest request) {
        try {
            return ResponseEntity.ok(packingService.addPackingDetail(request));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to add packing detail: " + e.getMessage());
        }
    }
}
