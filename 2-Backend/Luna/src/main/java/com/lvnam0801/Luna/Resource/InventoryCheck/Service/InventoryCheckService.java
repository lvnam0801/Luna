package com.lvnam0801.Luna.Resource.InventoryCheck.Service;

import java.time.LocalDate;
import java.util.List;

import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheck;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckCreateRequest;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckCreateResponse;

public interface InventoryCheckService {
    public InventoryCheckCreateResponse createInventoryCheck(InventoryCheckCreateRequest request);
    public List<InventoryCheck> getInventoryChecks();
    public List<InventoryCheck> getInventoryChecks(Integer warehouseID);
    public List<InventoryCheck> getInventoryChecks(LocalDate fromDate, LocalDate toDate);
    public List<InventoryCheck> getInventoryChecks(Integer warehouseID, LocalDate fromDate, LocalDate toDate);

    public InventoryCheck getById(int id);
}