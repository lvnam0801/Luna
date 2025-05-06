package com.lvnam0801.Luna.Resource.Core.SKUItem.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateRequest;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateResponse;

public interface SKUItemService {
    public SKUItem[] getAllSKUItems();
    public SKUItem getSKUItemByID(Integer itemID) ;
    public SKUItem[] getSKUItemsByProductID(Integer productID);
    public SKUItemCreateResponse createSKUItem(SKUItemCreateRequest request);
    public void decreaseSKUItemQuantity(Integer skuItemID, Integer quantityToDecrease);
    public void increaseSKUItemQuantity(Integer skuItemID, Integer quantityToRestore);
    public void updateSKUItemStatus(Integer skuItemID, String newStatus);
    public void reserveQuantity(Integer skuItemID, int quantityToReserve);
}
