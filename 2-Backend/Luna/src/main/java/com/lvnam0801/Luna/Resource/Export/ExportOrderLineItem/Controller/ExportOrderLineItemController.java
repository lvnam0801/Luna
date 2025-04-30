package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItem;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemCreateResponse;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemUpdateResponse;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Service.ExportOrderLineItemService;

@RestController
@RequestMapping("/api/export-order-line")
public class ExportOrderLineItemController {

    private final ExportOrderLineItemService exportOrderLineItemService;

    public ExportOrderLineItemController(ExportOrderLineItemService exportOrderLineItemService) {
        this.exportOrderLineItemService = exportOrderLineItemService;
    }

    @GetMapping("/get-by-order/{orderID}")
    public ResponseEntity<?> getByOrderID(@PathVariable Integer orderID) {
        try {
            ExportOrderLineItem[] items = exportOrderLineItemService.getByOrderID(orderID);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch line items: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{orderLineItemID}")
    public ResponseEntity<?> getByID(@PathVariable Integer orderLineItemID) {
        try {
            ExportOrderLineItem item = exportOrderLineItemService.getByID(orderLineItemID);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch line item: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLineItem(@RequestBody ExportOrderLineItemCreateRequest request) {
        try {
            ExportOrderLineItemCreateResponse response = exportOrderLineItemService.createOrderLineItem(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create line item: " + e.getMessage());
        }
    }

    @PatchMapping("/update/{orderLineItemID}")
    public ResponseEntity<?> updateLineItem(
        @PathVariable Integer orderLineItemID,
        @RequestBody ExportOrderLineItemUpdateRequest request
    ) {
        try {
            ExportOrderLineItemUpdateResponse response = exportOrderLineItemService.updateOrderLineItemPartially(orderLineItemID, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update line item: " + e.getMessage());
        }
    }
}