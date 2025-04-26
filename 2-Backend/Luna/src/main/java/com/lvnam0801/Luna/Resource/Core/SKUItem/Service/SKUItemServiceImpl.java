package com.lvnam0801.Luna.Resource.Core.SKUItem.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItem;
import com.lvnam0801.Luna.Resource.Core.SKUItem.Representation.SKUItemRequest;
import com.lvnam0801.Luna.Utility.SKUGenerator;


@Service
public class SKUItemServiceImpl implements SKUItemService {
    private final JdbcTemplate jdbcTemplate;

    public SKUItemServiceImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SKUItem[] getAllSKUItems()
    {
        String sql = """
            SELECT 
                s.ItemID,
                s.SKU,
                s.Quantity,
                i.ExpirationDate,

                p.Name,
                p.PhotoURL,
                p.Origin,
                p.WholesalePrice,
                p.RetailPrice,

                m.Name AS ManufacturerName,
                c.Name AS CategoryName,

                s.Status
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            LEFT JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID
            LEFT JOIN ProductCategory c ON p.CategoryID = c.CategoryID
            LEFT JOIN ImportReceiptLineItem i ON i.ItemID = s.ItemID
        """;

        List<SKUItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> new SKUItem(
            rs.getInt("ItemID"),
            rs.getString("SKU"),
            rs.getInt("Quantity"),
            rs.getObject("ExpirationDate", Date.class) != null 
                ? rs.getDate("ExpirationDate").toLocalDate().atStartOfDay().toLocalTime() 
                : null,
            rs.getString("Name"),
            rs.getString("PhotoURL"),
            rs.getString("Origin"),
            rs.getLong("WholesalePrice"),
            rs.getLong("RetailPrice"),
            rs.getString("ManufacturerName"),
            rs.getString("CategoryName"),
            rs.getString("Status")
        ));
        return items.toArray(SKUItem[]::new);
    }

    @Override
    public Integer createSKUItem(SKUItemRequest request)
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
        return itemID;
    }
}
