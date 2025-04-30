package com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service;

import java.util.List;

import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLog;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;

public interface ExportActivityLogService {
    public void log(ExportActivityLogRequest request);
    public List<ExportActivityLog> getLogsByOrder(int orderID);
}