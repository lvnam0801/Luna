package com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Service;

import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeader;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderCreateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderCreateResponse;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.ExportOrderHeader.Representation.ExportOrderHeaderUpdateResponse;

public interface ExportOrderHeaderService {
    public ExportOrderHeader[] getAllExportOrders();
    public ExportOrderHeader getExportOrderById(Integer orderID);
    public ExportOrderHeaderCreateResponse createExportOrder(ExportOrderHeaderCreateRequest request);
    public ExportOrderHeaderUpdateResponse updateExportOrderPartially(Integer orderID, ExportOrderHeaderUpdateRequest request);
}
