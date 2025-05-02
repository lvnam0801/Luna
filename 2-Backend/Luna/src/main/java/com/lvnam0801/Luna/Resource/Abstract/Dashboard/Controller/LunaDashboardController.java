package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.BestSellingProduct;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.DailyRevenueEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.DashboardResponse;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.RecentExportOrderEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.RecentImportReceiptEntry;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.StockLevelOverview;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation.SummaryMetrics;
import com.lvnam0801.Luna.Resource.Abstract.Dashboard.Service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class LunaDashboardController {
    private final DashboardService dashboardService;
    
    public LunaDashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/overview")
    public ResponseEntity<?> getDashboardOverview() {
        try {
            DashboardResponse response = dashboardService.getDashboardOverview();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch dashboard overview: " + e.getMessage());
        }
    }

    @GetMapping("/stock-level")
    public ResponseEntity<?> getStockLevelOverview() {
        try {
            StockLevelOverview overview = dashboardService.fetchStockLevelOverview();
            return ResponseEntity.ok(overview);
        } catch (Exception e) {
            // Optional: log the error
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch stock level overview: " + e.getMessage());
        }
    }

    @GetMapping("/best-selling-products")
    public ResponseEntity<?> getBestSellingProducts() {
        try {
            List<BestSellingProduct> products = dashboardService.fetchBestSellingProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            // Optional: log the error
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch best-selling products: " + e.getMessage());
        }
    }

    @GetMapping("/summary-metrics")
    public ResponseEntity<?> getSummaryMetrics() {
        try {
            SummaryMetrics summary = dashboardService.fetchSummaryMetrics();
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch summary metrics: " + e.getMessage());
        }
    }
    
    @GetMapping("/daily-revenue")
    public ResponseEntity<?> getDailyRevenue() {
        try {
            List<DailyRevenueEntry> revenueData = dashboardService.fetchDailyRevenue();
            return ResponseEntity.ok(revenueData);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch daily revenue: " + e.getMessage());
        }
    }

    @GetMapping("/recent-export-orders")
    public ResponseEntity<?> getRecentExportOrders() {
        try {
            List<RecentExportOrderEntry> orders = dashboardService.fetchRecentExportOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch recent export orders: " + e.getMessage());
        }
    }

    @GetMapping("/recent-import-receipts")
    public ResponseEntity<?> getRecentImportReceipts() {
        try {
            List<RecentImportReceiptEntry> receipts = dashboardService.fetchRecentImportReceipts();
            return ResponseEntity.ok(receipts);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to fetch recent import receipts: " + e.getMessage());
        }
    }
}