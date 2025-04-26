package com.lvnam0801.Luna.Resource.Import.Putaway.Service;

import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.Putaway;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateRequest;

public interface PutawayService {
    public Putaway[] getByReceiptLine(Integer receiptLineItemID);

    public boolean canPutawayByResultType(Integer receiptLineItemID, String putawayResult, Integer putawayQuantity);
    public boolean canPutawayQuantityAgainstReceived(Integer receiptLineItemID, Integer putawayQuantity);
    public boolean isLineItemFinalized(Integer receiptLineItemID);
    public Putaway createPutaway(PutawayCreateRequest request);
}
