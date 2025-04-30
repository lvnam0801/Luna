package com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Controller;

import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeader;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderCreateResponse;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderUpdateResponse;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Service.ImportReceiptHeaderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import-receipt")
public class ImportReceiptHeaderController {
    private final ImportReceiptHeaderService importReceiptHeaderService;

    public ImportReceiptHeaderController(ImportReceiptHeaderService importReceiptHeaderService) 
    {
        this.importReceiptHeaderService = importReceiptHeaderService;
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllReceipts() {
        try {
            ImportReceiptHeader[] receipts = importReceiptHeaderService.getAllReceipts();
            return ResponseEntity.ok(receipts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch receipts: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getReceiptById(@PathVariable int id) {
        try {
            ImportReceiptHeader receipt = importReceiptHeaderService.getReceiptById(id);
            if (receipt == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receipt not found for ID: " + id);
            }
            return ResponseEntity.ok(receipt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving receipt by ID: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReceipt(@RequestBody ImportReceiptHeaderCreateRequest request) {
        try {
            ImportReceiptHeaderCreateResponse response = importReceiptHeaderService.createReceipt(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create receipt: " + e.getMessage());
        }
    }

    @PatchMapping("/update/{receiptID}")
    public ResponseEntity<?> updateReceipt(
        @PathVariable Integer receiptID,
        @RequestBody ImportReceiptHeaderUpdateRequest request
    ) {
        try {
            ImportReceiptHeaderUpdateResponse response = importReceiptHeaderService.updateReceiptPartially(receiptID, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to update receipt: " + e.getMessage());
        }
    }
}