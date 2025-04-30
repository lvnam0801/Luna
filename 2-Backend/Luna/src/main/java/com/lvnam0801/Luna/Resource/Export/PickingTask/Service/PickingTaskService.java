package com.lvnam0801.Luna.Resource.Export.PickingTask.Service;

import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTask;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskCreateRequest;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskCreateResponse;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskUpdateRequest;
import com.lvnam0801.Luna.Resource.Export.PickingTask.Representation.PickingTaskUpdateResponse;

public interface PickingTaskService {

    public PickingTask[] getByOrderLineItemID(Integer orderLineItemID);

    public PickingTask getByID(Integer pickingTaskID);

    public PickingTaskCreateResponse createPickingTask(PickingTaskCreateRequest request);

    public PickingTaskUpdateResponse updatePickingTaskPartially(Integer pickingTaskID, PickingTaskUpdateRequest request);
}