package com.lvnam0801.Luna.Resource.Import.Putaway.Service;

import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.Putaway;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateRequest;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayCreateResponse;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.Putaway.Representation.PutawayUpdateResponse;

public interface PutawayService {
    public Putaway[] getByReceiptLine(Integer receiptLineItemID);
    public Putaway getById(Integer putawayID);
    public Putaway getBySKUItem(Integer skuItemID);
    
    public boolean canPutawayByResultType(Integer receiptLineItemID, String putawayResult, Integer putawayQuantity);
    public boolean canPutawayQuantityAgainstReceived(Integer receiptLineItemID, Integer putawayQuantity);
    public boolean isPutawayEditable(Integer receiptLineItemID);
    public PutawayCreateResponse createPutaway(PutawayCreateRequest request);

    public boolean canUpdatePutawayQuantityAgainstReceived(Integer putawayID, Integer newQuantity);
    public boolean canUpdatePutawayQuantityByResultType(Integer putawayID, String putawayResult, Integer newQuantity);
    public PutawayUpdateResponse updatePutawayPartially(Integer putawayID, PutawayUpdateRequest request);
}
