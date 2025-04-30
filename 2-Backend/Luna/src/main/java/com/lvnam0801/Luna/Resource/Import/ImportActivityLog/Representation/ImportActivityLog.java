package com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation;

import java.sql.Timestamp;

public record ImportActivityLog(
    Integer activityLogID,
    Integer receiptID,
    Integer userID,
    String userDisplayName,
    String targetType,
    String actionType,
    Integer targetID,
    Timestamp loggedTime,
    String content,
    String status
) {}