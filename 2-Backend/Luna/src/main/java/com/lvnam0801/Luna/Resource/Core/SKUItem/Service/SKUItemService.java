package com.lvnam0801.Luna.Resource.Core.SKUItem.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemRequest;

public interface SKUItemService {
    public SKUItem[] getAllSKUItems();
    public Integer createSKUItem(SKUItemRequest request);
}
