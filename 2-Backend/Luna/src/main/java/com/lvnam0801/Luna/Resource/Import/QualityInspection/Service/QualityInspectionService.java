package com.lvnam0801.Luna.Resource.Import.QualityInspection.Service;

import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspection;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionCreateRequest;
import com.lvnam0801.Luna.Resource.Import.QualityInspection.Representation.QualityInspectionUpdateRequest;

public interface QualityInspectionService 
{
    public QualityInspection[] getByLineItem(Integer receiptLineItemID);
    public boolean canAddInspectionQuantity(int receiptLineItemId, int additionalQuantity);
    public QualityInspection createInspection(QualityInspectionCreateRequest qualityInspectionCreateRequest);
    public boolean canUpdateInspection(Integer inspectionId);
    public boolean canUpdateInspectionQuantity(int inspectionId, int updatedQuantity);
    public QualityInspection updateInspectionPartially(Integer InspectionID, QualityInspectionUpdateRequest qualityInspectionUpdateRequest);
}
