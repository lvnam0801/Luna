package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

import java.sql.Date;

public record PackingUpdateRequest(
    Integer packToLocationID,
    String status,
    Date packedDate
) {}