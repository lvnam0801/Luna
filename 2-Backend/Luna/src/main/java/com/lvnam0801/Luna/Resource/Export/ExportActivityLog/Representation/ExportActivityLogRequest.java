package com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation;

public record ExportActivityLogRequest(
    Integer orderID,
    Integer userID,
    String targetType,
    String actionType,
    Integer targetID,
    String content
) {}