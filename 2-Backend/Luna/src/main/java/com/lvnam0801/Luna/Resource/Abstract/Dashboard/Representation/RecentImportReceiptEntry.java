package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RecentImportReceiptEntry(
    Integer receiptID,
    String providerName,
    BigDecimal totalPrice,
    String status,
    LocalDate date
) {}