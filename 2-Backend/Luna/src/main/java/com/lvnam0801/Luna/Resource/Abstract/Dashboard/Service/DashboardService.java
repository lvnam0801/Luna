package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Service;

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
    public SummaryMetrics fetchSummaryMetrics();
    public List<DailyRevenueEntry> fetchDailyRevenue();
    public List<RecentExportOrderEntry> fetchRecentExportOrders();
    public List<RecentImportReceiptEntry> fetchRecentImportReceipts();
}