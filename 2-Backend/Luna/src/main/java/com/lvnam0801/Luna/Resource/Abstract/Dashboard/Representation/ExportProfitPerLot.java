package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExportProfitPerLot(
    String lotNumber,
    Integer productID,
    String productName,
    Integer totalExportedQty,
    BigDecimal unitPrice,
    BigDecimal unitCost,
    BigDecimal totalSales,
    BigDecimal totalCost,
    BigDecimal profit,
    LocalDate orderDate,
    Integer warehouseID
) {}