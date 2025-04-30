package com.lvnam0801.Luna.Resource.Export.Packing.Representation;

import java.util.List;

public record PackingWithDetails(
    Packing packing,
    List<PackingDetail> items
) {}