package com.lvnam0801.Luna.Resource.Core.SKUItem.Controller;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Service.SKUItemService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getAllSKUItems() {
        try {
            SKUItem[] items = skuItemService.getSKUItems();
            return ResponseEntity.ok(items);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch SKU items: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-warehouse")
    public ResponseEntity<?> getSKUItemsByWarehouse(
        @RequestParam("warehouseID") Integer warehouseID) {
        try {
            SKUItem[] items = skuItemService.getSKUItems(warehouseID);
            return ResponseEntity.ok(items);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch SKU items: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-warehouse-and-sku")
    public ResponseEntity<?> getSKUItemsByWarehouseAndSKU(
        @RequestParam("warehouseID") Integer warehouseID,
        @RequestParam("sku") String sku){
        try {
            SKUItem[] items = skuItemService.getSKUItems(warehouseID, sku);
            return ResponseEntity.ok(items);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch SKU items: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-product/{productID}")
    public ResponseEntity<?> getSKUItemsByProductID(@PathVariable Integer productID) {
        try {
            SKUItem[] skuItems = skuItemService.getSKUItemsByProductID(productID);
            return ResponseEntity.ok(skuItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch SKU items: " + e.getMessage());
        }
    }

    @GetMapping("/get-by-id/{itemID}")
    public ResponseEntity<?> getSKUItemByID(@PathVariable Integer itemID) {
        try {
            SKUItem skuItem = skuItemService.getSKUItemByID(itemID);
            return ResponseEntity.ok(skuItem);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("SKU Item not found with ID: " + itemID);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch SKU item: " + e.getMessage());
        }
    }
}