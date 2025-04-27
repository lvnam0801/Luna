package com.lvnam0801.Luna.Resource.Import.QualityInspection.Service;

import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspection;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionCreateRequest;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionCreateResponse;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionUpdateResponse;

public interface QualityInspectionService 
{
    public QualityInspection[] getByLineItem(Integer receiptLineItemID);
    public QualityInspection getById(Integer inspectionID);
    
    public boolean canAddInspectionQuantity(int receiptLineItemId, int additionalQuantity);
    public QualityInspectionCreateResponse createInspection(QualityInspectionCreateRequest qualityInspectionCreateRequest);
    
    public boolean canUpdateInspection(Integer inspectionId);
    public boolean canUpdateInspectionQuantity(int inspectionId, int updatedQuantity);
    public QualityInspectionUpdateResponse updateInspectionPartially(Integer InspectionID, QualityInspectionUpdateRequest qualityInspectionUpdateRequest);
}
