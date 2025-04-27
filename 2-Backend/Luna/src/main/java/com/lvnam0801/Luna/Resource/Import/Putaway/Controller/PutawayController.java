package com.lvnam0801.Luna.Resource.Import.Putaway.Controller;

import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.Putaway;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateRequest;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateResponse;
import com.lvnam0801.Luna.Resource.Import.Putaway.Service.PutawayService;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/putaway")
public class PutawayController {
    private final PutawayService putawayService;

    public PutawayController(PutawayService putawayService)
    {
        this.putawayService = putawayService;
    }

    @GetMapping("/get-by-receipt-line/{receiptLineItemID}")
    public ResponseEntity<?> getByReceiptLine(@PathVariable Integer receiptLineItemID) {
        try {
            if (receiptLineItemID == null) {
                return ResponseEntity.badRequest().body("Missing required field: receiptLineItemID");
            }
    
            Putaway[] putaways = putawayService.getByReceiptLine(receiptLineItemID);
            return ResponseEntity.ok(putaways);
        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error while retrieving putaways: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }
    
    @GetMapping("/get-by-id/{putawayID}")
    public ResponseEntity<?> getPutawayById(@PathVariable Integer putawayID) {
        try {
            if (putawayID == null) {
                return ResponseEntity.badRequest().body("Missing required field: putawayID");
            }
    
            Putaway putaway = putawayService.getById(putawayID);
            return ResponseEntity.ok(putaway);
        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error while retrieving putaway: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPutaway(@RequestBody PutawayCreateRequest request) {
        try {
            // Step 1: Validate required fields
            if (request.receiptLineItemID() == null || request.quantity() == null ||
                request.putawayResult() == null || request.status() == null || request.putawayBy() == null) {
                return ResponseEntity.badRequest().body("Missing required fields.");
            }

            // Step 2: Business validations
            if (request.quantity() <= 0) {
                return ResponseEntity.badRequest().body("Quantity must be greater than 0.");
            }

            if (putawayService.isLineItemFinalized(request.receiptLineItemID())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This line item is finalized. No further putaway allowed.");
            }

            if (!putawayService.canPutawayQuantityAgainstReceived(request.receiptLineItemID(), request.quantity())) {
                return ResponseEntity.badRequest().body("Putaway quantity exceeds the received quantity.");
            }

            if (!putawayService.canPutawayByResultType(
                    request.receiptLineItemID(),
                    request.putawayResult(),
                    request.quantity())) {
                return ResponseEntity.badRequest().body("Putaway quantity exceeds inspected quantity of type: " + request.putawayResult());
            }

            // Step 4: Create Putaway
            PutawayCreateResponse response = putawayService.createPutaway(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database error while creating putaway: " + dae.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred while creating putaway: " + e.getMessage());
        }
    }
}