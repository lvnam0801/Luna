package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation;

import java.util.List;

public record StockLevelOverview(
    int totalProductTypes,
    int totalSKUItemQuantity,
    List<ProductStockByName> productStockByNames
) {}
