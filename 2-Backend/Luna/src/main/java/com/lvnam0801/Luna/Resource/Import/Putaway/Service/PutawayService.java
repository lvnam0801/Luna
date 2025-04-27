package com.lvnam0801.Luna.Resource.Import.Putaway.Service;

import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.Putaway;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateRequest;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateResponse;

public interface PutawayService {
    public Putaway[] getByReceiptLine(Integer receiptLineItemID);
    public Putaway getById(Integer putawayID);
    
    public boolean canPutawayByResultType(Integer receiptLineItemID, String putawayResult, Integer putawayQuantity);
    public boolean canPutawayQuantityAgainstReceived(Integer receiptLineItemID, Integer putawayQuantity);
    public boolean isLineItemFinalized(Integer receiptLineItemID);
    public PutawayCreateResponse createPutaway(PutawayCreateRequest request);
}
