package com.lvnam0801.Luna.Resource.Core.SKUItem.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateRequest;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateResponse;

public interface SKUItemService {

    public SKUItem[] getSKUItems();
    public SKUItem[] getSKUItems(Integer warehouseID);
    public SKUItem[] getSKUItems(Integer warehouseID, String SKU);

    public SKUItem[] getSKUItemsByProductID(Integer productID);
    
    public SKUItem getSKUItemByID(Integer itemID) ;
    public SKUItemCreateResponse createSKUItem(SKUItemCreateRequest request);

    public void decreaseSKUItemQuantity(Integer skuItemID, Integer quantityToDecrease);
    public void increaseSKUItemQuantity(Integer skuItemID, Integer quantityToRestore);
    public void updateSKUItemStatus(Integer skuItemID, String newStatus);
    public void reserveQuantity(Integer skuItemID, int quantityToReserve);
}
