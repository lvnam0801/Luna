package com.lvnam0801.Luna.Resource.Core.SKUItem.Service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                s.ProductID,
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
            rs.getInt("ProductID"),
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
    public SKUItem[] getSKUItemsByProductID(Integer productID)
    {
        String sql = """
            SELECT
                s.ItemID,
                s.ProductID,
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
            WHERE s.ProductID = ?
        """;
    
        List<SKUItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> new SKUItem(
            rs.getInt("ItemID"),
            rs.getInt("ProductID"),
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
        ), productID);
        return items.toArray(SKUItem[]::new);
    }

    @Override
    public SKUItem getSKUItemByID(Integer itemID) {
        String sql = """
            SELECT
                s.ItemID,
                s.ProductID,
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
                rs.getInt("ProductID"),
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

    @Override
    public void decreaseSKUItemQuantity(Integer skuItemID, Integer quantityToDecrease) {
        // Validate enough quantity exists
        Integer currentQty = jdbcTemplate.queryForObject(
            "SELECT Quantity FROM SKUItem WHERE SKUItemID = ? FOR UPDATE",
            Integer.class,
            skuItemID
        );

        if (currentQty == null) {
            throw new IllegalArgumentException("SKUItem not found with ID: " + skuItemID);
        }

        if (currentQty < quantityToDecrease) {
            throw new IllegalArgumentException("Insufficient quantity in stock. Current: " + currentQty);
        }

        jdbcTemplate.update(
            "UPDATE SKUItem SET Quantity = Quantity - ? WHERE ItemID = ?",
            quantityToDecrease,
            skuItemID
        );
    }

    @Override
    public void increaseSKUItemQuantity(Integer skuItemID, Integer quantityToRestore) {
        jdbcTemplate.update(
            "UPDATE SKUItem SET Quantity = Quantity + ? WHERE ItemID = ?",
            quantityToRestore,
            skuItemID
        );
    }

    @Override
    public void updateSKUItemStatus(Integer skuItemID, String newStatus) {
        jdbcTemplate.update(
            "UPDATE SKUItem SET Status = ? WHERE ItemID = ?",
            newStatus,
            skuItemID
        );
    }

    @Override
    @Transactional
    public void reserveQuantity(Integer skuItemID, int quantityToReserve) {
        // Step 1: Check available quantity
        String checkSql = "SELECT Quantity FROM SKUItem WHERE ItemID = ? FOR UPDATE";
        Integer currentQuantity = jdbcTemplate.queryForObject(checkSql, Integer.class, skuItemID);

        if (currentQuantity == null) {
            throw new IllegalArgumentException("SKU Item not found with ID: " + skuItemID);
        }

        if (currentQuantity < quantityToReserve) {
            throw new IllegalStateException("Not enough SKUItem quantity available. Current: " 
                + currentQuantity + ", Required: " + quantityToReserve);
        }

        // Step 2: Decrease quantity
        String updateSql = "UPDATE SKUItem SET Quantity = Quantity - ? WHERE ItemID = ?";
        int rowsAffected = jdbcTemplate.update(updateSql, quantityToReserve, skuItemID);

        if (rowsAffected != 1) {
            throw new DataAccessException("Failed to reserve quantity for SKUItemID: " + skuItemID) {};
        }
    }
}
