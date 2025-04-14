package com.lvnam0801.Luna.Resource.Common.Dashboard.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.Resource.Common.Dashboard.Representation.StockLevel;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class DashboardController {

    @GetMapping("dashboard/stock-level")
    public StockLevel[] getStockLevels() {
        ArrayList<StockLevel> stockLevels = new ArrayList<StockLevel>();
        StockLevel stockLevel1 = new StockLevel("Nike Air Max 270", 11, "low");
        stockLevels.add(stockLevel1);

        StockLevel stockLevel2 = new StockLevel("Adidas Ultraboost", 45, "medium");
        stockLevels.add(stockLevel2);

        StockLevel stockLevel3 = new StockLevel("Puma RS-X", 80, "high");
        stockLevels.add(stockLevel3);

        StockLevel stockLevel4 = new StockLevel("Nike Air Force 1", 10, "low");
        stockLevels.add(stockLevel4);

        StockLevel stockLevel5 = new StockLevel("Adidas NMD", 25, "medium");
        stockLevels.add(stockLevel5);
        return stockLevels.toArray(new StockLevel[0]);
    }
}
// { product: "Nike Air Max 270", quantity: 15, status: 'low' },
// { product: "Adidas Ultraboost", quantity: 45, status: 'medium' },
// { product: "Puma RS-X", quantity: 80, status: 'high' },
// { product: "Nike Air Force 1", quantity: 10, status: 'low' },
// { product: "Adidas NMD", quantity: 25, status: 'medium' }
