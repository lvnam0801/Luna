package com.lvnam0801.Luna.Resource.Export.Shipment.Service;

import com.lvnam0801.Luna.Resource.Export.Packing.Representation.Packing;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.Shipment;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPacking;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPackingCreateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentPackingCreateResponse;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.Shipment.Representation.ShipmentUpdateResponse;

public interface ShipmentService {

    public Shipment[] getByOrderID(Integer orderID);
    public Shipment getByID(Integer shipmentID);
    public ShipmentCreateResponse createShipment(ShipmentCreateRequest request);
    public ShipmentUpdateResponse updateShipmentPartially(Integer shipmentID, ShipmentUpdateRequest request);

    public ShipmentPacking[] getShipmentPackingsByShipmentID(Integer shipmentID);
    public ShipmentPackingCreateResponse createShipmentPacking(ShipmentPackingCreateRequest request);
    public Packing[] getAvailablePackingsByOrderID(Integer orderID);
}
