package com.lvnam0801.Luna.Resource.Core.SKUItem.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateRequest;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateResponse;

public interface SKUItemService {
    public SKUItem[] getAllSKUItems();
    public SKUItem getSKUItemByID(Integer itemID) ;
    public SKUItemCreateResponse createSKUItem(SKUItemCreateRequest request);
}
