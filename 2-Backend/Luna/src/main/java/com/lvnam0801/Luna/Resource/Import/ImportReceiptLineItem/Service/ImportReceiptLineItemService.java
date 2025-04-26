package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service;

import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItem;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemResponse;

public interface ImportReceiptLineItemService {
    public ImportReceiptLineItemResponse[] getByReceipt(Integer receiptID);
    public ImportReceiptLineItem createLine(ImportReceiptLineItemCreateRequest request);    
}