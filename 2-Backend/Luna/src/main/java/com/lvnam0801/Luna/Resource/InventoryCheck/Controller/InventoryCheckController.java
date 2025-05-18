package com.lvnam0801.Luna.Resource.InventoryCheck.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheck;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckRequest;
import com.lvnam0801.Luna.Resource.InventoryCheck.Service.InventoryCheckService;

@RestController
@RequestMapping("/api/inventory-check")
public class InventoryCheckController {

    private final InventoryCheckService inventoryCheckService;
    public InventoryCheckController(InventoryCheckService inventoryCheckService)
    {
        this.inventoryCheckService = inventoryCheckService;
    }


    @PostMapping
    public ResponseEntity<InventoryCheck> create(@RequestBody InventoryCheckRequest request) {
        InventoryCheck created = inventoryCheckService.createInventoryCheck(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryCheck> getById(@PathVariable int id) {
        return ResponseEntity.ok(inventoryCheckService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<InventoryCheck>> getAll() {
        return ResponseEntity.ok(inventoryCheckService.getAllChecks());
    }
}