package com.lvnam0801.Luna.Resource.Core.SKUItem.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.Time;
import java.time.LocalTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.Core.ProductDimension.Representation.ProductDimension;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/product-instance")
public class SKUItemController {
    private final JdbcTemplate jdbcTemplate;
    public SKUItemController (JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/get-all")
    public List<SKUItem> getAllSKUItems() {
        String sql = """
                SELECT si.ItemID, si.SKU, si.Quantity, p.Name, p.PhotoURL, p.Origin, p.WholesalePrice, p.RetailPrice, m.Name AS ManufacturerName, si.Status
                FROM SKUItem si
                JOIN Product p ON p.ProductID = si.ProductID
                JOIN Manufacturer m ON m.ManufacturerID = p.ManufacturerID;
                """;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Map<Integer, SKUItem> skuItemMap = new LinkedHashMap<>();
        
        for (Map<String,Object> row : rows) {
            Integer itemID = (Integer) row.get("ItemID");
            
            Time sqlTime = (Time)row.get("ExpiredDate");
            LocalTime expiredTime = (sqlTime != null)? sqlTime.toLocalTime() : null;
            
            skuItemMap.putIfAbsent(
                itemID, 
                new SKUItem(
                    (Integer) row.get("ItemID"), 
                    (String) row.get("SKU"), 
                    (Integer) row.get("Quantity"), 
                    expiredTime, 
                    (String) row.get("Name"),
                    (String) row.get("PhotoURL"), 
                    (String) row.get("Origin"), 
                    (Long) row.get("WholesalePrice"),
                    (Long) row.get("RetailPrice"), 
                    (String) "",
                    (String) row.get("ManufacturerName"),
                    (String) row.get("CategoryName"),
                    (String) row.get("Status"),
                    new ArrayList<>()
                )
            );
            
            // if(row.get("DimensionType") != null)
            // {
            //     ProductDimension productDimension = new ProductDimension
            //     (
            //         (String) row.get("DimensionType"),
            //         ((Number) row.get("Value")).doubleValue(),
            //         (String) row.get("Unit")  
            //     );
            //     skuItemMap.get(itemID).dimensions().add(productDimension);
            // }

        }
        List<SKUItem> skuItems = new ArrayList<SKUItem>(skuItemMap.values());
        return skuItems;
    
    }
}
