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
    public SKUItem[] getSKUItems() {
        String sql = """
            SELECT
                s.ItemID,
                s.ProductID,
                s.SKU,
                s.Quantity,
                s.Status,

                p.ProductCode,
                p.Name,
                p.PhotoURL,
                p.UnitName,
                p.Origin,
                p.WholesalePrice,
                p.RetailPrice,
                p.CategoryID,
                p.ManufacturerID,
    
                c.Name AS CategoryName,
                m.Name AS ManufacturerName,
    
                s.WarehouseID,
                w.Name AS WarehouseName,
                s.LocationID,
                l.LocationName AS LocationName,
    
                s.ReceiptLineItemID,
                r.ExpirationDate,
                r.LotNumber

    
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            
            LEFT JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID

            LEFT JOIN Warehouse w ON s.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON s.LocationID = l.LocationID
            LEFT JOIN ImportReceiptLineItem r ON s.ReceiptLineItemID = r.ReceiptLineItemID
            WHERE s.Quantity > 0
        """;
    
        List<SKUItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> new SKUItem(
            rs.getInt("ItemID"),
            rs.getInt("ProductID"),
            rs.getString("ProductCode"),
            rs.getString("SKU"),
            rs.getInt("Quantity"),
            rs.getString("Status"),
    
            rs.getString("Name"),
            rs.getString("PhotoURL"),
            rs.getString("UnitName"),
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
    
            rs.getInt("ReceiptLineItemID"),
            rs.getDate("ExpirationDate"),
            rs.getString("LotNumber")
        ));
    
        return items.toArray(SKUItem[]::new);
    }

    @Override
    public SKUItem[] getSKUItems(Integer warehouseID) {
        String sql = """
            SELECT
                s.ItemID,
                s.ProductID,
                s.SKU,
                s.Quantity,
                s.Status,

                p.ProductCode,
                p.Name,
                p.PhotoURL,
                p.UnitName,
                p.Origin,
                p.WholesalePrice,
                p.RetailPrice,
                p.CategoryID,
                p.ManufacturerID,
    
                c.Name AS CategoryName,
                m.Name AS ManufacturerName,
    
                s.WarehouseID,
                w.Name AS WarehouseName,
                s.LocationID,
                l.LocationName AS LocationName,
    
                s.ReceiptLineItemID,
                r.ExpirationDate,
                r.LotNumber

    
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            
            LEFT JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID

            LEFT JOIN Warehouse w ON s.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON s.LocationID = l.LocationID
            LEFT JOIN ImportReceiptLineItem r ON s.ReceiptLineItemID = r.ReceiptLineItemID
            WHERE s.Quantity > 0 AND s.WarehouseID = ?
        """;
    
        List<SKUItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> new SKUItem(
                rs.getInt("ItemID"),
                rs.getInt("ProductID"),
                rs.getString("ProductCode"),
                rs.getString("SKU"),
                rs.getInt("Quantity"),
                rs.getString("Status"),
        
                rs.getString("Name"),
                rs.getString("PhotoURL"),
                rs.getString("UnitName"),
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
        
                rs.getInt("ReceiptLineItemID"),
                rs.getDate("ExpirationDate"),
                rs.getString("LotNumber")
            ),
            new Object[]{warehouseID}
        );
    
        return items.toArray(SKUItem[]::new);
    }

    @Override
    public SKUItem[] getSKUItems(Integer warehouseID, String SKU) {
        String sql = """
            SELECT
                s.ItemID,
                s.ProductID,
                s.SKU,
                s.Quantity,
                s.Status,

                p.ProductCode,
                p.Name,
                p.PhotoURL,
                p.UnitName,
                p.Origin,
                p.WholesalePrice,
                p.RetailPrice,
                p.CategoryID,
                p.ManufacturerID,
    
                c.Name AS CategoryName,
                m.Name AS ManufacturerName,
    
                s.WarehouseID,
                w.Name AS WarehouseName,
                s.LocationID,
                l.LocationName AS LocationName,
    
                s.ReceiptLineItemID,
                r.ExpirationDate,
                r.LotNumber

    
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            
            LEFT JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID

            LEFT JOIN Warehouse w ON s.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON s.LocationID = l.LocationID
            LEFT JOIN ImportReceiptLineItem r ON s.ReceiptLineItemID = r.ReceiptLineItemID
            WHERE s.Quantity > 0 AND s.WarehouseID = ? AND s.SKU = ?
        """;
    
        List<SKUItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> new SKUItem(
                rs.getInt("ItemID"),
                rs.getInt("ProductID"),
                rs.getString("ProductCode"),
                rs.getString("SKU"),
                rs.getInt("Quantity"),
                rs.getString("Status"),
        
                rs.getString("Name"),
                rs.getString("PhotoURL"),
                rs.getString("UnitName"),
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
        
                rs.getInt("ReceiptLineItemID"),
                rs.getDate("ExpirationDate"),
                rs.getString("LotNumber")
            ),
            new Object[]{warehouseID, SKU}
        );
    
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

                p.ProductCode,
                p.Name,
                p.PhotoURL,
                p.UnitName,
                p.Origin,
                p.WholesalePrice,
                p.RetailPrice,
                p.CategoryID,
                p.ManufacturerID,
    
                c.Name AS CategoryName,
                m.Name AS ManufacturerName,
    
                s.WarehouseID,
                w.Name AS WarehouseName,
                s.LocationID,
                l.LocationName AS LocationName,
    
                s.ReceiptLineItemID,
                r.ExpirationDate,
                r.LotNumber
    
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            LEFT JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID
            LEFT JOIN Warehouse w ON s.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON s.LocationID = l.LocationID
            LEFT JOIN ImportReceiptLineItem r ON s.ReceiptLineItemID = r.ReceiptLineItemID
            WHERE s.Quantity > 0 AND s.ProductID = ?
        """;
    
        List<SKUItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> new SKUItem(
            rs.getInt("ItemID"),
            rs.getInt("ProductID"),
            rs.getString("ProductCode"),
            rs.getString("SKU"),
            rs.getInt("Quantity"),
            rs.getString("Status"),
    
            rs.getString("Name"),
            rs.getString("PhotoURL"),
            rs.getString("UnitName"),
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
    
            rs.getInt("ReceiptLineItemID"),
            rs.getDate("ExpirationDate"),
            rs.getString("LotNumber")        
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

                p.ProductCode,
                p.Name,
                p.PhotoURL,
                p.UnitName,
                p.Origin,
                p.WholesalePrice,
                p.RetailPrice,
                p.CategoryID,
                p.ManufacturerID,
    
                c.Name AS CategoryName,
                m.Name AS ManufacturerName,
    
                s.WarehouseID,
                w.Name AS WarehouseName,
                s.LocationID,
                l.LocationName AS LocationName,
    
                s.ReceiptLineItemID,
                r.ExpirationDate,
                r.LotNumber
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            LEFT JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID
            LEFT JOIN Warehouse w ON s.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON s.LocationID = l.LocationID
            LEFT JOIN ImportReceiptLineItem r ON s.ReceiptLineItemID = r.ReceiptLineItemID
            WHERE s.ItemID = ?
            """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new SKUItem(
                rs.getInt("ItemID"),
                rs.getInt("ProductID"),
                rs.getString("ProductCode"),
                rs.getString("SKU"),
                rs.getInt("Quantity"),
                rs.getString("Status"),

                rs.getString("Name"),
                rs.getString("PhotoURL"),
                rs.getString("UnitName"),
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

                rs.getInt("ReceiptLineItemID"),
                rs.getDate("ExpirationDate"),
                rs.getString("LotNumber")
        ), itemID);
    }

    private String getProductCode(Integer productID)
    {
        String sql = """
            SELECT ProductCode FROM Product
            WHERE ProductID = ?
        """;
        String productCode = jdbcTemplate.queryForObject(sql, String.class, new Object[]{productID});
        return productCode;
    }

    private String getLotNumber(Integer receiptLineItemID)
    {
        String sql = """
            SELECT LotNumber FROM ImportReceiptLineItem
            WHERE ReceiptLineItemID = ?        
        """;
        String lotNumber = jdbcTemplate.queryForObject(sql, String.class, new Object[]{receiptLineItemID});
        return lotNumber;
    }
    @Override
    public SKUItemCreateResponse createSKUItem(SKUItemCreateRequest request)
    {
        String productCode = getProductCode(request.productID());
        String lotNumber = getLotNumber(request.receiptLineItemID());

        // Step 1: Generate SKU automatically
        String generatedSKU = SKUGenerator.generateSKU(productCode, lotNumber);

        // Step 2: Insert into SKUItem
        String sql = """
            INSERT INTO SKUItem (SKU, Quantity, ProductID, ReceiptLineItemID, WarehouseID, LocationID, Status)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            generatedSKU,
            request.quantity(),
            request.productID(),
            request.receiptLineItemID(),
            request.warehouseID(),
            request.locationID(),
            request.status()
        );

        Integer itemID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        SKUItemCreateResponse item = new SKUItemCreateResponse(
            itemID,
            generatedSKU
        );
        // Step 3: Return the created SKUItem
        return item;
    }

    @Override
    public void decreaseSKUItemQuantity(Integer skuItemID, Integer quantityToDecrease) {
        // Validate enough quantity exists
        Integer currentQty = jdbcTemplate.queryForObject(
            "SELECT Quantity FROM SKUItem WHERE ItemID = ? FOR UPDATE",
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
