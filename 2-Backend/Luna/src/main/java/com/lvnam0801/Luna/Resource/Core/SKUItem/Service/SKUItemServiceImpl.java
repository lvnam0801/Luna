package com.lvnam0801.Luna.Resource.Core.SKUItem.Service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateRequest;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemCreateResponse;
import com.lvnam0801.Luna.Utility.SKUGenerator;


@Service
public class SKUItemServiceImpl implements SKUItemService {
    private final JdbcTemplate jdbcTemplate;

    public SKUItemServiceImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SKUItem[] getAllSKUItems() {
        String sql = """
            SELECT
                s.ItemID,
                s.SKU,
                s.Quantity,
                s.Status,
    
                p.Name,
                p.PhotoURL,
                p.Origin,
                p.WholesalePrice,
                p.RetailPrice,
                p.CategoryID,
                p.ManufacturerID,
    
                c.Name AS CategoryName,
                m.Name AS ManufacturerName,
    
                w.WarehouseID,
                w.Name AS WarehouseName,
                l.LocationID,
                l.LocationName AS LocationName,
    
                r.ExpirationDate
    
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            LEFT JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID
            LEFT JOIN Putaway pu ON pu.SKUItemID = s.ItemID
            LEFT JOIN Location l ON pu.PutawayAtLocationID = l.LocationID
            LEFT JOIN Warehouse w ON l.WarehouseID = w.WarehouseID
            LEFT JOIN ImportReceiptLineItem r ON pu.ReceiptLineItemID = r.ReceiptLineItemID
        """;
    
        List<SKUItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> new SKUItem(
            rs.getInt("ItemID"),
            rs.getString("SKU"),
            rs.getInt("Quantity"),
            rs.getString("Status"),
    
            rs.getString("Name"),
            rs.getString("PhotoURL"),
            rs.getString("Origin"),
            rs.getLong("WholesalePrice"),
            rs.getLong("RetailPrice"),
    
            rs.getInt("CategoryID"),
            rs.getString("CategoryName"),
            rs.getInt("ManufacturerID"),
            rs.getString("ManufacturerName"),
    
            rs.getInt("WarehouseID"),
            rs.getString("WarehouseName"),
            rs.getInt("LocationID"),
            rs.getString("LocationName"),
    
            rs.getDate("ExpirationDate")        
        ));
    
        return items.toArray(SKUItem[]::new);
    }
    @Override
    public SKUItem getSKUItemByID(Integer itemID) {
        String sql = """
            SELECT
                s.ItemID,
                s.SKU,
                s.Quantity,
                s.Status,
                p.Name,
                p.PhotoURL,
                p.Origin,
                p.WholesalePrice,
                p.RetailPrice,
                p.CategoryID,
                p.ManufacturerID,
                c.Name AS CategoryName,
                m.Name AS ManufacturerName,
                w.WarehouseID,
                w.Name AS WarehouseName,
                l.LocationID,
                l.LocationName,
                r.ExpirationDate
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            LEFT JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID
            LEFT JOIN Putaway pu ON pu.SKUItemID = s.ItemID
            LEFT JOIN Location l ON pu.PutawayAtLocationID = l.LocationID
            LEFT JOIN Warehouse w ON l.WarehouseID = w.WarehouseID
            LEFT JOIN ImportReceiptLineItem r ON pu.ReceiptLineItemID = r.ReceiptLineItemID
            WHERE s.ItemID = ?
            """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new SKUItem(
                rs.getInt("ItemID"),
                rs.getString("SKU"),
                rs.getInt("Quantity"),
                rs.getString("Status"),

                rs.getString("Name"),
                rs.getString("PhotoURL"),
                rs.getString("Origin"),
                rs.getLong("WholesalePrice"),
                rs.getLong("RetailPrice"),

                rs.getInt("CategoryID"),
                rs.getString("CategoryName"),
                rs.getInt("ManufacturerID"),
                rs.getString("ManufacturerName"),

                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),
                rs.getInt("LocationID"),
                rs.getString("LocationName"),

                rs.getDate("ExpirationDate")
        ), itemID);
    }

    @Override
    public SKUItemCreateResponse createSKUItem(SKUItemCreateRequest request)
    {
        // Step 1: Generate SKU automatically
        String generatedSKU = SKUGenerator.generateSKU(jdbcTemplate, request.productID());

        // Step 2: Insert into SKUItem
        String sql = """
            INSERT INTO SKUItem (SKU, Quantity, ProductID, Status)
            VALUES (?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            generatedSKU,
            request.quantity(),
            request.productID(),
            request.status()
        );

        Integer itemID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        SKUItemCreateResponse item = new SKUItemCreateResponse(
            itemID,
            generatedSKU,
            request.quantity()
        );
        // Step 3: Return the created SKUItem
        return item;
    }
}
