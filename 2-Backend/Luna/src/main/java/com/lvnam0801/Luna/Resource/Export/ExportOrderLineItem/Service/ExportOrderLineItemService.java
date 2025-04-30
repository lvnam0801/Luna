package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Service;

import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItem;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemCreateResponse;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation.ExportOrderLineItemUpdateResponse;

// Service Interface
public interface ExportOrderLineItemService {
    public ExportOrderLineItem[] getByOrderID(Integer orderID);
    public ExportOrderLineItem getByID(Integer lineItemID);
    public ExportOrderLineItemCreateResponse createOrderLineItem(ExportOrderLineItemCreateRequest request);
    public ExportOrderLineItemUpdateResponse updateOrderLineItemPartially(Integer lineItemID, ExportOrderLineItemUpdateRequest request);
}