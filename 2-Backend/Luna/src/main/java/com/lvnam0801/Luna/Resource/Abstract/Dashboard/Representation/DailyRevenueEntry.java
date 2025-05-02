package com.lvnam0801.Luna.Resource.Abstract.Dashboard.Representation;

public record DailyRevenueEntry(
    String day,
    long revenue,
    long cost
) {}
