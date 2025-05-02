package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RecentExportOrderEntry(
    Integer orderID,
    String customerName,
    BigDecimal totalPrice,
    String status,
    LocalDate date
) {}