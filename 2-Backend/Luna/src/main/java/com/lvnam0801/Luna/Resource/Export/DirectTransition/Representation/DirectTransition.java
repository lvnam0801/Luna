package com.lvnam0801.Luna.Resource.Export.DirectTransition.Representation;

import java.sql.Date;

public record DirectTransition(
    Integer transitionID,
    Integer exportOrderID,
    Integer importReceiptID,
    Date transitionDate,
    String notes,
    String status
) {}