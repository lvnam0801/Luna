package com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Service;

import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeader;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderCreateResponse;

public interface ImportReceiptHeaderService 
{
    public ImportReceiptHeader[] getAllReceipts();
    public ImportReceiptHeader getReceiptById(int id);
    public ImportReceiptHeaderCreateResponse createReceipt(ImportReceiptHeaderCreateRequest request);
}
