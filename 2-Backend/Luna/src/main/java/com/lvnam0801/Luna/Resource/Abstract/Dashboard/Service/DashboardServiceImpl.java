package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.BestSellingProduct;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.DailyRevenueEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.DashboardResponse;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.ExportProfitPerLot;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.ProductStockByName;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.RecentExportOrderEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.RecentImportReceiptEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.StockLevelOverview;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.SummaryMetrics;

@Service
public class DashboardServiceImpl implements DashboardService {
    
    private final JdbcTemplate jdbcTemplate;
    
    public DashboardServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DashboardResponse getDashboardOverview() {
        StockLevelOverview stockLevel = fetchStockLevelOverview();
        List<BestSellingProduct> bestSellers = fetchBestSellingProducts();
        SummaryMetrics summary = fetchSummaryMetrics();
        List<DailyRevenueEntry> dailyRevenue = fetchDailyRevenue();
        List<RecentExportOrderEntry> exportOrders = fetchRecentExportOrders();
        List<RecentImportReceiptEntry> importReceipts = fetchRecentImportReceipts();

        return new DashboardResponse(
            stockLevel,
            bestSellers,
            summary,
            dailyRevenue,
            exportOrders,
            importReceipts
        );
    }

    @Override
    public StockLevelOverview fetchStockLevelOverview() {
        // 1. Fetch product name and stock count
        String productStockQuery = """
            SELECT p.Name AS name, SUM(s.Quantity) AS count, p.UnitName as unitName
            FROM SKUItem s
            JOIN Product p ON s.ProductID = p.ProductID
            WHERE s.Status = 'in_stock' AND s.Quantity > 0
            GROUP BY p.ProductID, p.Name
        """;

        List<ProductStockByName> productStockList = jdbcTemplate.query(
            productStockQuery,
            (rs, rowNum) -> new ProductStockByName(
                rs.getString("name"),
                rs.getInt("count"),
                rs.getString("unitName")
            )
        );

        // 2. Fetch total product types
        String totalProductTypesQuery = """
            SELECT COUNT(DISTINCT s.ProductID)
            FROM SKUItem s
            WHERE s.Status = 'in_stock' AND s.Quantity > 0
        """;

        Integer totalProductTypes = jdbcTemplate.queryForObject(totalProductTypesQuery, Integer.class);
        if (totalProductTypes == null) {
            totalProductTypes = 0; // Default to 0 if no products found
        }

        // 3. Count total SKUItem quantity
        String totalSKUItemQuantityQuery = """
                SELECT SUM(Quantity) FROM SKUItem
                """;
        Integer totalSKUItemQuantity = jdbcTemplate.queryForObject(totalSKUItemQuantityQuery, Integer.class);
        if (totalSKUItemQuantity == null) {
            totalSKUItemQuantity = 0;
        }

        // 4. Return final record
        return new StockLevelOverview(totalProductTypes, totalSKUItemQuantity, productStockList);
    }

    @Override
    public List<BestSellingProduct> fetchBestSellingProducts() {
        String sql = """
            SELECT 
                p.ProductCode AS productCode,
                p.Name AS productName,
                SUM(eol.ExportedQuantity) AS unitsSold
            FROM ExportOrderLineItem eol
            JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
            JOIN Product p ON eol.ProductID = p.ProductID
            WHERE eh.ExportPurpose='sale'
            GROUP BY p.ProductID, p.ProductCode, p.Name
            ORDER BY unitsSold DESC
            LIMIT 10
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new BestSellingProduct(
                rs.getString("productCode"),
                rs.getString("productName"),
                rs.getInt("unitsSold")
            )
        );
    }

    @Override
    public List<BestSellingProduct> fetchBestSellingProducts(Integer warehouseID) {
        String sql = """
            SELECT 
                p.ProductCode AS productCode,
                p.Name AS productName,
                SUM(eol.ExportedQuantity) AS unitsSold
            FROM ExportOrderLineItem eol
            JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
            JOIN Product p ON eol.ProductID = p.ProductID
            WHERE eh.ExportPurpose='sale' AND eh.WarehouseID = ?
            GROUP BY p.ProductID, p.ProductCode, p.Name
            ORDER BY unitsSold DESC
            LIMIT 10
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new BestSellingProduct(
                rs.getString("productCode"),
                rs.getString("productName"),
                rs.getInt("unitsSold")
            ),
            new Object[]{warehouseID}
        );
    }

    @Override
    public List<BestSellingProduct> fetchBestSellingProducts(LocalDate fromDate, LocalDate toDate) {
        String sql = """
            SELECT 
                p.ProductCode AS productCode,
                p.Name AS productName,
                SUM(eol.ExportedQuantity) AS unitsSold
            FROM ExportOrderLineItem eol
            JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
            JOIN Product p ON eol.ProductID = p.ProductID
            WHERE eh.ExportPurpose='sale' AND eh.OrderDate BETWEEN ? AND ?
            GROUP BY p.ProductID, p.ProductCode, p.Name
            ORDER BY unitsSold DESC
            LIMIT 10
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new BestSellingProduct(
                rs.getString("productCode"),
                rs.getString("productName"),
                rs.getInt("unitsSold")
            ),
            new Object[]{fromDate, toDate}
        );
    }

    @Override
    public List<BestSellingProduct> fetchBestSellingProducts(Integer warehouseID, LocalDate fromDate, LocalDate toDate) {
        String sql = """
            SELECT 
                p.ProductCode AS productCode,
                p.Name AS productName,
                SUM(eol.ExportedQuantity) AS unitsSold
            FROM ExportOrderLineItem eol
            JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
            JOIN Product p ON eol.ProductID = p.ProductID
            WHERE eh.ExportPurpose='sale' AND eh.WarehouseID = ? AND eh.OrderDate BETWEEN ? AND ?
            GROUP BY p.ProductID, p.ProductCode, p.Name
            ORDER BY unitsSold DESC
            LIMIT 10
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new BestSellingProduct(
                rs.getString("productCode"),
                rs.getString("productName"),
                rs.getInt("unitsSold")
            ),
            new Object[]{warehouseID, fromDate, toDate}
        );
    }

    @Override
    public SummaryMetrics fetchSummaryMetrics() {
        String productCountSql = "SELECT COUNT(*) FROM Product WHERE Status = 'active'";
        String skuCountSql = "SELECT SUM(Quantity) FROM SKUItem WHERE Status = 'in_stock'";
        String exportCountSql = "SELECT COUNT(*) FROM ExportOrderHeader WHERE Status = 'active'";
        String importCountSql = "SELECT COUNT(*) FROM ImportReceiptHeader WHERE Status = 'active'";
    
        try {
            Integer totalProducts = jdbcTemplate.queryForObject(productCountSql, Integer.class);
            if (totalProducts == null) {
                totalProducts = 0; // Default to 0 if no products found
            }
            Integer totalStockItems = jdbcTemplate.queryForObject(skuCountSql, Integer.class);
            if (totalStockItems == null) {
                totalStockItems = 0; // Default to 0 if no stock items found
            }
            Integer totalExportOrders = jdbcTemplate.queryForObject(exportCountSql, Integer.class);
            if (totalExportOrders == null) {
                totalExportOrders = 0; // Default to 0 if no export orders found
            }
            Integer totalImportReceipts = jdbcTemplate.queryForObject(importCountSql, Integer.class);
            if (totalImportReceipts == null) {
                totalImportReceipts = 0; // Default to 0 if no import receipts found
            }
    
            return new SummaryMetrics(
                totalProducts,
                totalStockItems != null ? totalStockItems : 0,
                totalExportOrders,
                totalImportReceipts
            );
        } catch (Exception e) {
            // Optional: log the exception or throw a custom error
            throw new RuntimeException("Error while fetching summary metrics: " + e.getMessage(), e);
        }
    }

    @Override
    public List<DailyRevenueEntry> fetchDailyRevenue() {
        String sql = """
            SELECT 
                DATE_FORMAT(e.day, '%Y-%m-%d') AS day,
                e.revenue,
                IFNULL(i.cost, 0) AS cost
            FROM (
                SELECT DATE(eh.OrderDate) AS day,
                       SUM(eol.ExportedQuantity * eol.UnitPrice) AS revenue
                FROM ExportOrderHeader eh
                JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
                WHERE eh.Status = 'active'
                GROUP BY DATE(eh.OrderDate)
            ) e
            LEFT JOIN (
                SELECT DATE(ih.ActualArrivalDate) AS day,
                       SUM(il.ReceivedQuantity * il.UnitCost) AS cost
                FROM ImportReceiptHeader ih
                JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
                WHERE ih.Status = 'active'
                GROUP BY DATE(ih.ActualArrivalDate)
            ) i ON e.day = i.day
    
            UNION
    
            SELECT 
                DATE_FORMAT(i.day, '%Y-%m-%d') AS day,
                IFNULL(e.revenue, 0) AS revenue,
                i.cost
            FROM (
                SELECT DATE(eh.OrderDate) AS day,
                       SUM(eol.ExportedQuantity * eol.UnitPrice) AS revenue
                FROM ExportOrderHeader eh
                JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
                WHERE eh.Status = 'active'
                GROUP BY DATE(eh.OrderDate)
            ) e
            RIGHT JOIN (
                SELECT DATE(ih.ActualArrivalDate) AS day,
                       SUM(il.ReceivedQuantity * il.UnitCost) AS cost
                FROM ImportReceiptHeader ih
                JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
                WHERE ih.Status = 'active'
                GROUP BY DATE(ih.ActualArrivalDate)
            ) i ON e.day = i.day
    
            ORDER BY day ASC
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new DailyRevenueEntry(
                rs.getString("day"),
                rs.getLong("revenue"),
                rs.getLong("cost")
            )
        );
    }

    @Override
    public List<DailyRevenueEntry> fetchDailyRevenue(LocalDate fromDate, LocalDate toDate) {
        String sql = """
            SELECT 
                DATE_FORMAT(e.day, '%Y-%m-%d') AS day,
                e.revenue,
                IFNULL(i.cost, 0) AS cost
            FROM (
                SELECT DATE(eh.OrderDate) AS day,
                       SUM(eol.ExportedQuantity * eol.UnitPrice) AS revenue
                FROM ExportOrderHeader eh
                JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
                WHERE eh.Status = 'active' AND eh.OrderDate BETWEEN ? AND ?
                GROUP BY DATE(eh.OrderDate)
            ) e
            LEFT JOIN (
                SELECT DATE(ih.ActualArrivalDate) AS day,
                       SUM(il.ReceivedQuantity * il.UnitCost) AS cost
                FROM ImportReceiptHeader ih
                JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
                WHERE ih.Status = 'active' AND ih.ActualArrivalDate BETWEEN ? AND ?
                GROUP BY DATE(ih.ActualArrivalDate)
            ) i ON e.day = i.day
    
            UNION
    
            SELECT 
                DATE_FORMAT(i.day, '%Y-%m-%d') AS day,
                IFNULL(e.revenue, 0) AS revenue,
                i.cost
            FROM (
                SELECT DATE(eh.OrderDate) AS day,
                       SUM(eol.ExportedQuantity * eol.UnitPrice) AS revenue
                FROM ExportOrderHeader eh
                JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
                WHERE eh.Status = 'active' AND eh.OrderDate BETWEEN ? AND ?
                GROUP BY DATE(eh.OrderDate)
            ) e
            RIGHT JOIN (
                SELECT DATE(ih.ActualArrivalDate) AS day,
                       SUM(il.ReceivedQuantity * il.UnitCost) AS cost
                FROM ImportReceiptHeader ih
                JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
                WHERE ih.Status = 'active' AND ih.ActualArrivalDate BETWEEN ? AND ?
                GROUP BY DATE(ih.ActualArrivalDate)
            ) i ON e.day = i.day
    
            ORDER BY day ASC
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new DailyRevenueEntry(
                rs.getString("day"),
                rs.getLong("revenue"),
                rs.getLong("cost")
            ),
            new Object[]{fromDate, toDate, fromDate, toDate, fromDate, toDate, fromDate, toDate}
        );
    }

    public List<DailyRevenueEntry> fetchDailyRevenue(Integer warehouseID, LocalDate fromDate, LocalDate toDate)
    {
        String sql = """
            SELECT 
                DATE_FORMAT(e.day, '%Y-%m-%d') AS day,
                e.revenue,
                IFNULL(i.cost, 0) AS cost
            FROM (
                SELECT DATE(eh.OrderDate) AS day,
                       SUM(eol.ExportedQuantity * eol.UnitPrice) AS revenue
                FROM ExportOrderHeader eh
                JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
                WHERE eh.Status = 'active' AND eh.warehouseID = ? AND eh.OrderDate BETWEEN ? AND ?
                GROUP BY DATE(eh.OrderDate)
            ) e
            LEFT JOIN (
                SELECT DATE(ih.ActualArrivalDate) AS day,
                       SUM(il.ReceivedQuantity * il.UnitCost) AS cost
                FROM ImportReceiptHeader ih
                JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
                WHERE ih.Status = 'active' AND ih.WarehouseID = ? AND ih.ActualArrivalDate BETWEEN ? AND ?
                GROUP BY DATE(ih.ActualArrivalDate)
            ) i ON e.day = i.day
    
            UNION
    
            SELECT 
                DATE_FORMAT(i.day, '%Y-%m-%d') AS day,
                IFNULL(e.revenue, 0) AS revenue,
                i.cost
            FROM (
                SELECT DATE(eh.OrderDate) AS day,
                       SUM(eol.ExportedQuantity * eol.UnitPrice) AS revenue
                FROM ExportOrderHeader eh
                JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
                WHERE eh.Status = 'active' AND eh.warehouseID = ? AND eh.OrderDate BETWEEN ? AND ?
                GROUP BY DATE(eh.OrderDate)
            ) e
            RIGHT JOIN (
                SELECT DATE(ih.ActualArrivalDate) AS day,
                       SUM(il.ReceivedQuantity * il.UnitCost) AS cost
                FROM ImportReceiptHeader ih
                JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
                WHERE ih.Status = 'active' AND ih.WarehouseID = ? AND ih.ActualArrivalDate BETWEEN ? AND ?
                GROUP BY DATE(ih.ActualArrivalDate)
            ) i ON e.day = i.day
    
            ORDER BY day ASC
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new DailyRevenueEntry(
                rs.getString("day"),
                rs.getLong("revenue"),
                rs.getLong("cost")
            ),
            new Object[]{warehouseID, fromDate, toDate, warehouseID, fromDate, toDate, warehouseID, fromDate, toDate, warehouseID, fromDate, toDate}
        );
    }

    @Override
    public List<RecentExportOrderEntry> fetchRecentExportOrders() {
        String sql = """
            SELECT 
                eh.OrderID, eh.OrderNumber,
                p.ContactName AS customerName,
                SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalPrice,
                eh.OrderStatus AS status,
                eh.OrderDate
            FROM ExportOrderHeader eh
            JOIN Party p ON eh.CustomerID = p.PartyID
            JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
            WHERE eh.Status = 'active'
            GROUP BY eh.OrderID, p.ContactName, eh.OrderStatus, eh.OrderDate
            ORDER BY eh.OrderDate DESC
            LIMIT 5
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new RecentExportOrderEntry(
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getString("customerName"),
                rs.getBigDecimal("totalPrice"),
                rs.getString("status"),
                rs.getDate("OrderDate").toLocalDate()
            )
        );
    }

    @Override
    public List<RecentExportOrderEntry> fetchRecentExportOrders(Integer warehouseID) 
    {
        String sql = """
            SELECT 
                eh.OrderID, eh.OrderNumber,
                p.ContactName AS customerName,
                SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalPrice,
                eh.OrderStatus AS status,
                eh.OrderDate
            FROM ExportOrderHeader eh
            JOIN Party p ON eh.CustomerID = p.PartyID
            JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
            WHERE eh.Status = 'active' AND eh.WarehouseID = ?
            GROUP BY eh.OrderID, p.ContactName, eh.OrderStatus, eh.OrderDate
            ORDER BY eh.OrderDate DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new RecentExportOrderEntry(
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getString("customerName"),
                rs.getBigDecimal("totalPrice"),
                rs.getString("status"),
                rs.getDate("OrderDate").toLocalDate()
            ), 
            new Object[]{warehouseID} 
        );
    }

    @Override
    public List<RecentExportOrderEntry> fetchRecentExportOrders(LocalDate fromDate, LocalDate toDate) 
    {
        String sql = """
            SELECT 
                eh.OrderID, eh.OrderNumber,
                p.ContactName AS customerName,
                SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalPrice,
                eh.OrderStatus AS status,
                eh.OrderDate
            FROM ExportOrderHeader eh
            JOIN Party p ON eh.CustomerID = p.PartyID
            JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
            WHERE eh.Status = 'active'
            AND eh.OrderDate BETWEEN ? AND ?
            GROUP BY eh.OrderID, p.ContactName, eh.OrderStatus, eh.OrderDate
            ORDER BY eh.OrderDate DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new RecentExportOrderEntry(
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getString("customerName"),
                rs.getBigDecimal("totalPrice"),
                rs.getString("status"),
                rs.getDate("OrderDate").toLocalDate()
            ), 
            new Object[]{fromDate, toDate} 
        );
    }

    @Override
    public List<RecentExportOrderEntry> fetchRecentExportOrders(Integer warehouseID, LocalDate fromDate, LocalDate toDate) 
    {
        String sql = """
            SELECT 
                eh.OrderID, eh.OrderNumber,
                p.ContactName AS customerName,
                SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalPrice,
                eh.OrderStatus AS status,
                eh.OrderDate
            FROM ExportOrderHeader eh
            JOIN Party p ON eh.CustomerID = p.PartyID
            JOIN ExportOrderLineItem eol ON eh.OrderID = eol.OrderID
            WHERE eh.Status = 'active' AND eh.WarehouseID = ? AND eh.OrderDate BETWEEN ? AND ?
            GROUP BY eh.OrderID, p.ContactName, eh.OrderStatus, eh.OrderDate
            ORDER BY eh.OrderDate DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new RecentExportOrderEntry(
                rs.getInt("OrderID"),
                rs.getString("OrderNumber"),
                rs.getString("customerName"),
                rs.getBigDecimal("totalPrice"),
                rs.getString("status"),
                rs.getDate("OrderDate").toLocalDate()
            ), 
            new Object[]{warehouseID, fromDate, toDate} 
        );
    }

    

    @Override
    public List<RecentImportReceiptEntry> fetchRecentImportReceipts() {
        String sql = """
            SELECT 
                ih.ReceiptID,
                ih.ReceiptNumber,
                p.ContactName AS providerName,
                SUM(il.ReceivedQuantity * il.UnitCost) AS totalPrice,
                ih.ReceiptStatus AS status,
                ih.ActualArrivalDate AS date
            FROM ImportReceiptHeader ih
            JOIN Party p ON ih.SupplierID = p.PartyID
            JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
            WHERE ih.Status = 'active'
            GROUP BY ih.ReceiptID, p.ContactName, ih.ReceiptStatus, ih.ActualArrivalDate
            ORDER BY ih.ActualArrivalDate DESC
            LIMIT 5
        """;
    
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new RecentImportReceiptEntry(
                rs.getInt("ReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getString("providerName"),
                rs.getBigDecimal("totalPrice"),
                rs.getString("status"),
                rs.getDate("date").toLocalDate()
            )
        );
    }

    @Override
    public List<RecentImportReceiptEntry> fetchRecentImportReceipts(Integer warehouseID)
    {
        String sql = """
            SELECT 
                ih.ReceiptID,
                ih.ReceiptNumber,
                p.ContactName AS providerName,
                SUM(il.ReceivedQuantity * il.UnitCost) AS totalPrice,
                ih.ReceiptStatus AS status,
                ih.ActualArrivalDate AS date
            FROM ImportReceiptHeader ih
            JOIN Party p ON ih.SupplierID = p.PartyID
            JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
            WHERE ih.Status = 'active' AND ih.WarehouseID = ?
            GROUP BY ih.ReceiptID, p.ContactName, ih.ReceiptStatus, ih.ActualArrivalDate
            ORDER BY ih.ActualArrivalDate DESC
        """;
    
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new RecentImportReceiptEntry(
                rs.getInt("ReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getString("providerName"),
                rs.getBigDecimal("totalPrice"),
                rs.getString("status"),
                rs.getDate("date").toLocalDate()
            ),
            new Object[]{warehouseID}
        );
    }

    @Override
    public List<RecentImportReceiptEntry> fetchRecentImportReceipts(LocalDate fromDate, LocalDate toDate)
    {
        String sql = """
            SELECT 
                ih.ReceiptID,
                ih.ReceiptNumber,
                p.ContactName AS providerName,
                SUM(il.ReceivedQuantity * il.UnitCost) AS totalPrice,
                ih.ReceiptStatus AS status,
                ih.ActualArrivalDate AS date
            FROM ImportReceiptHeader ih
            JOIN Party p ON ih.SupplierID = p.PartyID
            JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
            WHERE ih.Status = 'active'
              AND ih.ActualArrivalDate BETWEEN ? AND ?
            GROUP BY ih.ReceiptID, p.ContactName, ih.ReceiptStatus, ih.ActualArrivalDate
            ORDER BY ih.ActualArrivalDate DESC
        """;
    
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new RecentImportReceiptEntry(
                rs.getInt("ReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getString("providerName"),
                rs.getBigDecimal("totalPrice"),
                rs.getString("status"),
                rs.getDate("date").toLocalDate()
            ),
            new Object[]{fromDate, toDate}
        );
    }

    @Override
    public List<RecentImportReceiptEntry> fetchRecentImportReceipts(Integer warehouseID, LocalDate fromDate, LocalDate toDate)
    {
        String sql = """
            SELECT 
                ih.ReceiptID,
                ih.ReceiptNumber,
                p.ContactName AS providerName,
                SUM(il.ReceivedQuantity * il.UnitCost) AS totalPrice,
                ih.ReceiptStatus AS status,
                ih.ActualArrivalDate AS date
            FROM ImportReceiptHeader ih
            JOIN Party p ON ih.SupplierID = p.PartyID
            JOIN ImportReceiptLineItem il ON ih.ReceiptID = il.ReceiptID
            WHERE ih.Status = 'active' AND ih.WarehouseID = ? AND ih.ActualArrivalDate BETWEEN ? AND ?
            GROUP BY ih.ReceiptID, p.ContactName, ih.ReceiptStatus, ih.ActualArrivalDate
            ORDER BY ih.ActualArrivalDate DESC
        """;
    
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new RecentImportReceiptEntry(
                rs.getInt("ReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getString("providerName"),
                rs.getBigDecimal("totalPrice"),
                rs.getString("status"),
                rs.getDate("date").toLocalDate()
            ),
            new Object[]{warehouseID, fromDate, toDate}
        );
    }

    @Override
    public List<ExportProfitPerLot> getExportProfitByLot() {
        String sql = """
            SELECT
                eol.LotNumber,
                eol.ProductID,
                p.Name AS productName,
                SUM(eol.ExportedQuantity) AS totalExportedQty,
                eol.UnitPrice,

                (
                    SELECT irl.UnitCost
                    FROM ImportReceiptLineItem irl
                    WHERE irl.LotNumber = eol.LotNumber
                    AND irl.UnitCost IS NOT NULL
                    AND irl.UnitCost > 0
                    ORDER BY irl.CreatedAt ASC
                    LIMIT 1
                ) AS unitCost,

                SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalSales,

                SUM(
                    eol.ExportedQuantity * (
                        SELECT IFNULL(irl.UnitCost, 0)
                        FROM ImportReceiptLineItem irl
                        WHERE irl.LotNumber = eol.LotNumber
                        AND irl.UnitCost IS NOT NULL
                        AND irl.UnitCost > 0
                        ORDER BY irl.CreatedAt ASC
                        LIMIT 1
                    )
                ) AS totalCost,

                SUM(eol.ExportedQuantity * eol.UnitPrice) - SUM(
                    eol.ExportedQuantity * (
                        SELECT IFNULL(irl.UnitCost, 0)
                        FROM ImportReceiptLineItem irl
                        WHERE irl.LotNumber = eol.LotNumber
                        AND irl.UnitCost IS NOT NULL
                        AND irl.UnitCost > 0
                        ORDER BY irl.CreatedAt ASC
                        LIMIT 1
                    )
                ) AS profit,

                eh.OrderDate,
                eh.WarehouseID
            
                FROM ExportOrderLineItem eol
                JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
                JOIN Product p ON p.ProductID = eol.ProductID
                WHERE eh.ExportPurpose = 'sale'
                
            GROUP BY eol.LotNumber, eol.ProductID, eol.UnitPrice, eh.OrderDate, eh.WarehouseID, p.Name
            ORDER BY eh.OrderDate ASC, eol.ProductID
        """;

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ExportProfitPerLot(
                rs.getString("LotNumber"),
                rs.getInt("ProductID"),
                rs.getString("productName"),
                rs.getInt("totalExportedQty"),
                rs.getBigDecimal("UnitPrice"),
                rs.getBigDecimal("unitCost"),
                rs.getBigDecimal("totalSales"),
                rs.getBigDecimal("totalCost"),
                rs.getBigDecimal("profit"),
                rs.getDate("OrderDate").toLocalDate(),
                rs.getInt("WarehouseID")
            ),
            new Object[]{}
        );
    }

    @Override
    public List<ExportProfitPerLot> getExportProfitByLot(int warehouseID) {
        String sql = """
            SELECT
                eol.LotNumber,
                eol.ProductID,
                p.Name AS productName,
                SUM(eol.ExportedQuantity) AS totalExportedQty,
                eol.UnitPrice,

                (
                    SELECT irl.UnitCost
                    FROM ImportReceiptLineItem irl
                    WHERE irl.LotNumber = eol.LotNumber
                    AND irl.UnitCost IS NOT NULL
                    AND irl.UnitCost > 0
                    ORDER BY irl.CreatedAt ASC
                    LIMIT 1
                ) AS unitCost,

                SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalSales,

                SUM(
                    eol.ExportedQuantity * (
                        SELECT IFNULL(irl.UnitCost, 0)
                        FROM ImportReceiptLineItem irl
                        WHERE irl.LotNumber = eol.LotNumber
                        AND irl.UnitCost IS NOT NULL
                        AND irl.UnitCost > 0
                        ORDER BY irl.CreatedAt ASC
                        LIMIT 1
                    )
                ) AS totalCost,

                SUM(eol.ExportedQuantity * eol.UnitPrice) - SUM(
                    eol.ExportedQuantity * (
                        SELECT IFNULL(irl.UnitCost, 0)
                        FROM ImportReceiptLineItem irl
                        WHERE irl.LotNumber = eol.LotNumber
                        AND irl.UnitCost IS NOT NULL
                        AND irl.UnitCost > 0
                        ORDER BY irl.CreatedAt ASC
                        LIMIT 1
                    )
                ) AS profit,

                eh.OrderDate,
                eh.WarehouseID

            FROM ExportOrderLineItem eol
            JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
            JOIN Product p ON p.ProductID = eol.ProductID

            WHERE eh.WarehouseID = ?
            AND eh.ExportPurpose = 'sale'

            GROUP BY eol.LotNumber, eol.ProductID, eol.UnitPrice, eh.OrderDate, eh.WarehouseID, p.Name
            ORDER BY eh.OrderDate ASC, eol.ProductID
        """;

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ExportProfitPerLot(
                rs.getString("LotNumber"),
                rs.getInt("ProductID"),
                rs.getString("productName"),
                rs.getInt("totalExportedQty"),
                rs.getBigDecimal("UnitPrice"),
                rs.getBigDecimal("unitCost"),
                rs.getBigDecimal("totalSales"),
                rs.getBigDecimal("totalCost"),
                rs.getBigDecimal("profit"),
                rs.getDate("OrderDate").toLocalDate(),
                rs.getInt("WarehouseID")
            ),
            new Object[]{warehouseID}
        );
    }

    @Override
    public List<ExportProfitPerLot> getExportProfitByLot(LocalDate fromDate, LocalDate toDate) {
        String sql = """
            SELECT
                eol.LotNumber,
                eol.ProductID,
                p.Name AS productName,
                SUM(eol.ExportedQuantity) AS totalExportedQty,
                eol.UnitPrice,

                (
                    SELECT irl.UnitCost
                    FROM ImportReceiptLineItem irl
                    WHERE irl.LotNumber = eol.LotNumber
                    AND irl.UnitCost IS NOT NULL
                    AND irl.UnitCost > 0
                    ORDER BY irl.CreatedAt ASC
                    LIMIT 1
                ) AS unitCost,

                SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalSales,

                SUM(
                    eol.ExportedQuantity * (
                        SELECT IFNULL(irl.UnitCost, 0)
                        FROM ImportReceiptLineItem irl
                        WHERE irl.LotNumber = eol.LotNumber
                        AND irl.UnitCost IS NOT NULL
                        AND irl.UnitCost > 0
                        ORDER BY irl.CreatedAt ASC
                        LIMIT 1
                    )
                ) AS totalCost,

                SUM(eol.ExportedQuantity * eol.UnitPrice) - SUM(
                    eol.ExportedQuantity * (
                        SELECT IFNULL(irl.UnitCost, 0)
                        FROM ImportReceiptLineItem irl
                        WHERE irl.LotNumber = eol.LotNumber
                        AND irl.UnitCost IS NOT NULL
                        AND irl.UnitCost > 0
                        ORDER BY irl.CreatedAt ASC
                        LIMIT 1
                    )
                ) AS profit,

                eh.OrderDate,
                eh.WarehouseID

            FROM ExportOrderLineItem eol
            JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
            JOIN Product p ON p.ProductID = eol.ProductID

            WHERE eh.OrderDate BETWEEN ? AND ?
            AND eh.ExportPurpose = 'sale'

            GROUP BY eol.LotNumber, eol.ProductID, eol.UnitPrice, eh.OrderDate, eh.WarehouseID, p.Name
            ORDER BY eh.OrderDate ASC, eol.ProductID
        """;

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ExportProfitPerLot(
                rs.getString("LotNumber"),
                rs.getInt("ProductID"),
                rs.getString("productName"),
                rs.getInt("totalExportedQty"),
                rs.getBigDecimal("UnitPrice"),
                rs.getBigDecimal("unitCost"),
                rs.getBigDecimal("totalSales"),
                rs.getBigDecimal("totalCost"),
                rs.getBigDecimal("profit"),
                rs.getDate("OrderDate").toLocalDate(),
                rs.getInt("WarehouseID")
            ),
            new Object[]{fromDate, toDate}
        );
    }

    @Override
    public List<ExportProfitPerLot> getExportProfitByLot(int warehouseID, LocalDate fromDate, LocalDate toDate) {
        String sql = """
            SELECT
                eol.LotNumber,
                eol.ProductID,
                p.Name AS productName,
                SUM(eol.ExportedQuantity) AS totalExportedQty,
                eol.UnitPrice,

                (
                    SELECT irl.UnitCost
                    FROM ImportReceiptLineItem irl
                    WHERE irl.LotNumber = eol.LotNumber
                    AND irl.UnitCost IS NOT NULL
                    AND irl.UnitCost > 0
                    ORDER BY irl.CreatedAt ASC
                    LIMIT 1
                ) AS unitCost,

                SUM(eol.ExportedQuantity * eol.UnitPrice) AS totalSales,

                SUM(
                    eol.ExportedQuantity * (
                        SELECT IFNULL(irl.UnitCost, 0)
                        FROM ImportReceiptLineItem irl
                        WHERE irl.LotNumber = eol.LotNumber
                        AND irl.UnitCost IS NOT NULL
                        AND irl.UnitCost > 0
                        ORDER BY irl.CreatedAt ASC
                        LIMIT 1
                    )
                ) AS totalCost,

                SUM(eol.ExportedQuantity * eol.UnitPrice) - SUM(
                    eol.ExportedQuantity * (
                        SELECT IFNULL(irl.UnitCost, 0)
                        FROM ImportReceiptLineItem irl
                        WHERE irl.LotNumber = eol.LotNumber
                        AND irl.UnitCost IS NOT NULL
                        AND irl.UnitCost > 0
                        ORDER BY irl.CreatedAt ASC
                        LIMIT 1
                    )
                ) AS profit,

                eh.OrderDate,
                eh.WarehouseID

            FROM ExportOrderLineItem eol
            JOIN ExportOrderHeader eh ON eol.OrderID = eh.OrderID
            JOIN Product p ON p.ProductID = eol.ProductID

            WHERE eh.WarehouseID = ?
            AND eh.OrderDate BETWEEN ? AND ?
            AND eh.ExportPurpose = 'sale'

            GROUP BY eol.LotNumber, eol.ProductID, eol.UnitPrice, eh.OrderDate, eh.WarehouseID, p.Name
            ORDER BY eh.OrderDate ASC, eol.ProductID
        """;

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ExportProfitPerLot(
                rs.getString("LotNumber"),
                rs.getInt("ProductID"),
                rs.getString("productName"),
                rs.getInt("totalExportedQty"),
                rs.getBigDecimal("UnitPrice"),
                rs.getBigDecimal("unitCost"),
                rs.getBigDecimal("totalSales"),
                rs.getBigDecimal("totalCost"),
                rs.getBigDecimal("profit"),
                rs.getDate("OrderDate").toLocalDate(),
                rs.getInt("WarehouseID")
            ),
            new Object[]{warehouseID, fromDate, toDate}
        );
    }
}