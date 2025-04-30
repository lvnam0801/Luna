package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service;

import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItem;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateResponse;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemUpdateResponse;

public interface ImportReceiptLineItemService {
    public ImportReceiptLineItem[] getByReceipt(Integer receiptID);
    public ImportReceiptLineItem getById(Integer receiptLineItemID);
    public ImportReceiptLineItemCreateResponse createLine(ImportReceiptLineItemCreateRequest request);
    public Integer getReceiptIDByLineItemID(int lineItemID);
    public ImportReceiptLineItemUpdateResponse updateReceiptLineItemPartially(Integer receiptLineItemID, ImportReceiptLineItemUpdateRequest request);
}