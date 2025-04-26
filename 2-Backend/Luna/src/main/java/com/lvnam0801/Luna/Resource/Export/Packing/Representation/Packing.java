package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

import java.sql.Date;

public record Packing(
    Integer packingID,
    Integer orderID,
    Integer packedBy,
    Date packingDate,
    String packageNumber,
    Double packageWeight,
    String packageDimension,
    String notes,
    String status
) {}