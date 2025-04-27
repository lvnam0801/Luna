package com.lvnam0801.Luna.Resource.Core.SKUItem.Controller;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Service.SKUItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sku-item")
public class SKUItemController {

    private final SKUItemService skuItemService;
    
    public SKUItemController(SKUItemService skuItemService)
    {
        this.skuItemService = skuItemService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<SKUItem[]> getAllSKUItems() {
        SKUItem[] items = skuItemService.getAllSKUItems();
        return ResponseEntity.ok(items);
    }

    // @PostMapping("/create")
    // public ResponseEntity<Integer> createSKUItem(@RequestBody SKUItemRequest request) 
    // {
    //     Integer itemID = skuItemService.createSKUItem(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(itemID);
    // }
}