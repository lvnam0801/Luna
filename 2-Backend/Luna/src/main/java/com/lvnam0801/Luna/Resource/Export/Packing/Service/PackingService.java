package com.lvnam0801.Luna.Resource.Export.Packing.Service;

import com.lvnam0801.Luna.Resource.Export.Packing.Representation.Packing;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingDetail;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingDetailCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingDetailCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.Packing.Representation.PackingUpdateResponse;

public interface PackingService {

    // Packing header operations
    public Packing[] getByOrderID(Integer orderID);
    public Packing[] getByOrderLineItemID(Integer orderLineItemID);
    public Packing getByID(Integer packingID);
    public PackingCreateResponse createPacking(PackingCreateRequest request);
    public PackingUpdateResponse updatePacking(Integer packingID, PackingUpdateRequest request);

    // Packing detail operations
    public PackingDetail[] getDetailsByPackingID(Integer packingID);
    public PackingDetailCreateResponse addPackingDetail(PackingDetailCreateRequest request);

    // Utils
    public String generatePackingNumber();

    public Packing[] getAvailablePackingsByOrderID(Integer orderID);
}
