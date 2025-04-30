package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation;

import java.sql.Date;
import java.math.BigDecimal;

public record ImportReceiptLineItemUpdateRequest(
    String lotNumber,
    Date expirationDate,
    BigDecimal unitCost,
    String status // Only status like pending, inspected, completed
) {}