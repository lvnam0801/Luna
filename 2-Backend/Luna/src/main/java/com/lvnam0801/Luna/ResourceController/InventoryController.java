package com.lvnam0801.Luna.ResourceController;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.lvnam0801.Luna.ResourceRepresentation.Product;

@RestController
public class InventoryController {
    @GetMapping("/inventory/product")
    public Product[] getMethodName() {
        return new Product[]{
            new Product(1, "Nike Air Max 270", "NK-AM270-001", "HCM", "2025-12-31", "120 M", "90 M", "active", "shoes"),
            new Product(2, "Adidas Ultraboost", "AD-UB-001", "HN", "2025-12-31", "150 M", "120 M", "active", "shoes"),
            new Product(3, "Puma RS-X", "PM-RSX-001", "DN", "2025-12-31", "80 M", "60 M", "inactive", "shoes"),
            new Product(4, "Converse Chuck Taylor", "CV-CT-002", "HCM", "2026-06-15", "70 M", "50 M", "active", "shoes"),
            new Product(5, "New Balance 327", "NB-327-003", "HN", "2025-09-10", "130 M", "100 M", "active", "shoes"),
            new Product(6, "Vans Old Skool", "VANS-OS-004", "DN", "2026-01-20", "90 M", "70 M", "active", "shoes"),
            new Product(7, "Reebok Club C 85", "RB-CC85-005", "HCM", "2025-08-25", "110 M", "85 M", "inactive", "shoes"),
            new Product(8, "Asics Gel-Kayano", "AS-GK-006", "HN", "2025-12-01", "140 M", "115 M", "active", "shoes"),
            new Product(9, "Under Armour HOVR", "UA-HOVR-007", "DN", "2026-05-05", "125 M", "95 M", "active", "shoes"),
            new Product(10, "Mizuno Wave Rider", "MZ-WR-008", "HCM", "2025-11-30", "135 M", "105 M", "inactive", "shoes")    
        };
    }
    
}
