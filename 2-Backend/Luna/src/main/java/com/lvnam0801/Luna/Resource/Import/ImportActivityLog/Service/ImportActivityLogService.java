package com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service;

import java.util.List;

import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityLog;

public interface ImportActivityLogService {
    public void log(Integer receiptID, Integer userID, String targetType, String actionType, int targetID, String content);
    public List<ImportActivityLog> getLogsByReceipt(int receiptID);
}