package com.lvnam0801.Luna.Resource.Import.QualityInspection.Controller;

import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspection;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionCreateRequest;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionCreateResponse;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionUpdateResponse;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Service.QualityInspectionService;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quality-inspection")
public class QualityInspectionController {
    
    private final QualityInspectionService qualityInspectionService;

    public QualityInspectionController(QualityInspectionService qualityInspectionService) {
        this.qualityInspectionService = qualityInspectionService;
    }

    @GetMapping("/get-by-receipt-line/{receiptLineItemID}")
    public ResponseEntity<?> getByLineItem(@PathVariable Integer receiptLineItemID) {
        try {
            QualityInspection[] qualityInspections = qualityInspectionService.getByLineItem(receiptLineItemID);
            return ResponseEntity.ok(qualityInspections);
        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error while retrieving inspections: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred while retrieving inspections: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{inspectionID}")
    public ResponseEntity<?> getInspectionById(@PathVariable Integer inspectionID) {
        try {
            if (inspectionID == null) {
                return ResponseEntity.badRequest().body("Missing required field: inspectionID");
            }

            QualityInspection inspection = qualityInspectionService.getById(inspectionID);
            return ResponseEntity.ok(inspection);

        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error while retrieving inspection: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred while retrieving inspection: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createInspection(
        @RequestBody QualityInspectionCreateRequest request
    ) {
        try {
            // Check if required fields are present
            if (request.receiptLineItemID() == null || request.quantity() == null) {
                return ResponseEntity.badRequest().body("Missing required field: receiptLineItemID or quantity");
            }
    
            // Check quantity constraint before creation
            if (!qualityInspectionService.canAddInspectionQuantity(request.receiptLineItemID(), request.quantity())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The total inspected quantity would exceed the received quantity of the line item.");
            }
    
            QualityInspectionCreateResponse response = qualityInspectionService.createInspection(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity
                .badRequest()
                .body("Validation error: " + e.getMessage());
    
        } catch (DataAccessException dae) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database error while creating inspection: " + dae.getMessage());
    
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error occurred while creating inspection: " + e.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateInspectionPartially(
        @PathVariable int id,
        @RequestBody QualityInspectionUpdateRequest request
    ) {
        try {
            // Finalized line item block
            if (!qualityInspectionService.canUpdateInspection(id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("This inspection cannot be updated because the related line item is finalized.");
            }

            // Quantity logic constraint block
            if (request.quantity() != null &&
                !qualityInspectionService.canUpdateInspectionQuantity(id, request.quantity())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The total inspected quantity would exceed the received quantity of the line item.");
            }

            QualityInspectionUpdateResponse response = qualityInspectionService.updateInspectionPartially(id, request);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity
                .badRequest()
                .body("Validation error: " + e.getMessage());
        } catch (DataAccessException dae) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database error while updating inspection: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error occurred while updating inspection: " + e.getMessage());
        }
    }
}