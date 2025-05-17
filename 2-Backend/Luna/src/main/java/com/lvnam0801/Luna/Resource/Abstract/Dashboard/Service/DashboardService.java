package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Service;

import java.time.LocalDate;
import java.util.List;

import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.BestSellingProduct;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.DailyRevenueEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.DashboardResponse;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.RecentExportOrderEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.RecentImportReceiptEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.StockLevelOverview;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.SummaryMetrics;

public interface DashboardService {
    public DashboardResponse getDashboardOverview();
    public StockLevelOverview fetchStockLevelOverview();
    
    public List<BestSellingProduct> fetchBestSellingProducts();
    public List<BestSellingProduct> fetchBestSellingProducts(Integer warehouseID);
    public List<BestSellingProduct> fetchBestSellingProducts(LocalDate fromDate, LocalDate toDate);
    public List<BestSellingProduct> fetchBestSellingProducts(Integer warehouseID, LocalDate fromDate, LocalDate toDate);

    public SummaryMetrics fetchSummaryMetrics();
    public List<DailyRevenueEntry> fetchDailyRevenue();
    public List<DailyRevenueEntry> fetchDailyRevenue(LocalDate fromDate, LocalDate toDate);
    public List<DailyRevenueEntry> fetchDailyRevenue(Integer warehouseID, LocalDate fromDate, LocalDate toDate);

    public List<RecentExportOrderEntry> fetchRecentExportOrders();
    public List<RecentExportOrderEntry> fetchRecentExportOrders(Integer warehouseID);
    public List<RecentExportOrderEntry> fetchRecentExportOrders(LocalDate fromDate, LocalDate toDate);
    public List<RecentExportOrderEntry> fetchRecentExportOrders(Integer warehouseID, LocalDate fromDate, LocalDate toDate);

    public List<RecentImportReceiptEntry> fetchRecentImportReceipts();
    public List<RecentImportReceiptEntry> fetchRecentImportReceipts(Integer warehouseID);
    public List<RecentImportReceiptEntry> fetchRecentImportReceipts(LocalDate fromDate, LocalDate toDate);
    public List<RecentImportReceiptEntry> fetchRecentImportReceipts(Integer warehouseID, LocalDate fromDate, LocalDate toDate);
}