package com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation;

import java.sql.Timestamp;

public record ExportActivityLog(
    Integer activityLogID,
    Integer orderID,
    Integer userID,
    String userDisplayName,
    String targetType,
    String actionType,
    Integer targetID,
    Timestamp loggedTime,
    String content,
    String status
) {}