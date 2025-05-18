package com.lvnam0801.Luna.Resource.InventoryCheck.Service;

import java.util.List;

import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheck;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckRequest;

public interface InventoryCheckService {
    public InventoryCheck createInventoryCheck(InventoryCheckRequest request);
    public List<InventoryCheck> getAllChecks();
    public InventoryCheck getById(int id);
}