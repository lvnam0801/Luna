package com.lvnam0801.Luna.Resource.Export.ExportOrderLineItem.Representation;

import java.math.BigDecimal;
import java.sql.Date;

public record ExportOrderLineItemUpdateRequest(
    String lotNumber,
    Date expirationDate,
    BigDecimal unitPrice,
    String status
) {}
