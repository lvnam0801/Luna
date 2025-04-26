package com.lvnam0801.Luna.Resource.Import.Putaway.Controller;

import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.Putaway;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateRequest;
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
    public Putaway[] getByReceiptLine(@PathVariable Integer receiptLineItemID) {
       return putawayService.getByReceiptLine(receiptLineItemID);
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
            Putaway putaway = putawayService.createPutaway(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(putaway);

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