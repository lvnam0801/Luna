package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation;

import java.util.List;

public record DashboardResponse(
    StockLevelOverview stockLevel,
    List<BestSellingProduct> bestSellingProducts,
    SummaryMetrics summaryMetrics,
    List<DailyRevenueEntry> dailyRevenue,
    List<RecentExportOrderEntry> recentExportOrders,
    List<RecentImportReceiptEntry> recentImportReceipts
) {}