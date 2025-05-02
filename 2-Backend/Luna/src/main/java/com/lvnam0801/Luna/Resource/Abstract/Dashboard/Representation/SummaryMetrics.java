package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation;

public record SummaryMetrics(
    int totalProducts,
    int totalStockItems,
    int totalExportOrders,
    int totalImportReceipts
) {}