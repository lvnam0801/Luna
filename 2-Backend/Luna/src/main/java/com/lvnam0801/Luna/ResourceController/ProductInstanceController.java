package com.lvnam0801.Luna.ResourceController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.Time;
import java.time.LocalTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.ResourceRepresentation.ProductDimension;
import com.lvnam0801.Luna.ResourceRepresentation.ProductInstance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/product-instance")
public class ProductInstanceController {
    private final JdbcTemplate jdbcTemplate;
    public ProductInstanceController (JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/get-all")
    public List<ProductInstance> getAllProductInstances() {
        String sql = """
                SELECT pi.InstanceID, pi.SKU, pi.Quantity, pi.ExpiredDate, p.Name, p.PhotoURL, p.Origin, p.WholesalePrice, p.RetailPrice, w.Name AS WarehouseName, m.Name AS ManufacturerName, c.Name AS CategoryName, d.DimensionType, d.Value, d.Unit, pi.Status
                FROM ProductInstance pi
                JOIN Product p ON p.ProductID = pi.ProductID
                JOIN Warehouse w ON w.warehouseID = pi.WarehouseID
                JOIN Manufacturer m ON m.ManufacturerID = p.ManufacturerID
                JOIN ProductCategory c ON c.CategoryID = p.CategoryID
                JOIN ProductDimensions d ON d.ProductID = p.ProductID
                """;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Map<Integer, ProductInstance> productInstanceMap = new LinkedHashMap<>();
        
        for (Map<String,Object> row : rows) {
            Integer productInstanceID = (Integer) row.get("InstanceID");
            
            Time sqlTime = (Time)row.get("ExpiredDate");
            LocalTime expiredTime = (sqlTime != null)? sqlTime.toLocalTime() : null;
            
            productInstanceMap.putIfAbsent(
                productInstanceID, 
                new ProductInstance(
                    (Integer) row.get("InstanceID"), 
                    (String) row.get("SKU"), 
                    (Integer) row.get("Quantity"), 
                    expiredTime, 
                    (String) row.get("Name"),
                    (String) row.get("PhotoURL"), 
                    (String) row.get("Origin"), 
                    (Long) row.get("WholesalePrice"),
                    (Long) row.get("RetailPrice"), 
                    (String) row.get("WarehouseName"),
                    (String) row.get("ManufacturerName"),
                    (String) row.get("CategoryName"),
                    (String) row.get("Status"),
                    new ArrayList<>()
                )
            );
            
            if(row.get("DimensionType") != null)
            {
                ProductDimension productDimension = new ProductDimension
                (
                    (String) row.get("DimensionType"),
                    ((Number) row.get("Value")).doubleValue(),
                    (String) row.get("Unit")  
                );
                productInstanceMap.get(productInstanceID).dimensions().add(productDimension);
            }

        }
        List<ProductInstance> productInstances = new ArrayList<ProductInstance>(productInstanceMap.values());
        return productInstances;
    
    }
}
