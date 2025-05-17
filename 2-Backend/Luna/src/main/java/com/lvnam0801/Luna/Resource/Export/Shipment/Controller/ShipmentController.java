package com.lvnam0801.Luna.Resource.Export.Shipment.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lvnam0801.Luna.Resource.Export.Packing.Representation.Packing;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.Shipment;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPacking;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPackingCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPackingCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentUpdateResponse;
import com.lvnam0801.Luna.Resource.Export.Shipment.Service.ShipmentService;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/get-by-order/{orderID}")
    public ResponseEntity<?> getByOrderID(@PathVariable Integer orderID) {
        try {
            Shipment[] shipments = shipmentService.getByOrderID(orderID);
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get shipments: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{shipmentID}")
    public ResponseEntity<?> getByID(@PathVariable Integer shipmentID) {
        try {
            Shipment shipment = shipmentService.getByID(shipmentID);
            return ResponseEntity.ok(shipment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to get shipment: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ShipmentCreateRequest request) {
        try {
            ShipmentCreateResponse response = shipmentService.createShipment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create shipment: " + e.getMessage());
        }
    }

    @PatchMapping("/update/{shipmentID}")
    public ResponseEntity<?> updatePartially(@PathVariable Integer shipmentID,
                                             @RequestBody ShipmentUpdateRequest request) {
        try {
            ShipmentUpdateResponse response = shipmentService.updateShipmentPartially(shipmentID, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update shipment: " + e.getMessage());
        }
    }

    @PostMapping("/create-shipment-packing")
    public ResponseEntity<?> linkPackingToShipment(@RequestBody ShipmentPackingCreateRequest request) {
        try {
            ShipmentPackingCreateResponse response = shipmentService.createShipmentPacking(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to link packing to shipment: " + e.getMessage());
        }
    }

    @GetMapping("/get-shipment-packing-by-shipmentID/{shipmentID}")
    public ResponseEntity<?> getPackingsByShipment(@PathVariable Integer shipmentID) {
        try {
            ShipmentPacking[] packings = shipmentService.getShipmentPackingsByShipmentID(shipmentID);
            return ResponseEntity.ok(packings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch packings: " + e.getMessage());
        }
    }

    @GetMapping("/get-available-packings/{orderID}")
    public ResponseEntity<?> getAllAvailablePackings(@PathVariable Integer orderID) {
        try {
            Packing[] availablePackings = shipmentService.getAvailablePackingsByOrderID(orderID);
            return ResponseEntity.ok(availablePackings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch packings: " + e.getMessage());
        }
    }
}
