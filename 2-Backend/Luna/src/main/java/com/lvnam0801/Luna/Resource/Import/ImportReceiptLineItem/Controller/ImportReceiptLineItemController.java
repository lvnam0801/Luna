package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Controller;

import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItem;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemResponse;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service.ImportReceiptLineItemService;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import-receipt-line")
public class ImportReceiptLineItemController {

    private final ImportReceiptLineItemService importReceiptLineItemService;

    public ImportReceiptLineItemController(
        ImportReceiptLineItemService importReceiptLineItemService
    ){
        this.importReceiptLineItemService = importReceiptLineItemService;
    }


    @GetMapping("/get-by-receipt/{receiptID}")
    public ResponseEntity<?> getByReceipt(@PathVariable Integer receiptID) {
        try {
            if(receiptID == null){
                return ResponseEntity.badRequest().body("Missing required fields: receiptID");
            }
            ImportReceiptLineItemResponse[] importReceiptLineItemResponses = importReceiptLineItemService.getByReceipt(receiptID);
            return ResponseEntity.ok(importReceiptLineItemResponses);
        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database error while retrieving line items: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error occurred while retrieving line items: " + e.getMessage());
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> createLine(@RequestBody ImportReceiptLineItemCreateRequest request) {
        try {
            if (request.receiptID() == null || request.productID() == null || request.createdBy() == null) {
                return ResponseEntity.badRequest().body("Missing required fields: receiptID, itemID, or createdBy.");
            }
            ImportReceiptLineItem createdLineItem = importReceiptLineItemService.createLine(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLineItem);

        } catch (DataAccessException dae) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database error while creating the line item: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error occurred while creating the line item: " + e.getMessage());
        }
    }
}