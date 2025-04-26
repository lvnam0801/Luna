package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

import java.sql.Date;

public record PackingRequest(
    Integer orderID,
    Integer packedBy,
    Date packingDate,
    String packageNumber,
    Double packageWeight,
    String packageDimension,
    String notes,
    String status
) {}