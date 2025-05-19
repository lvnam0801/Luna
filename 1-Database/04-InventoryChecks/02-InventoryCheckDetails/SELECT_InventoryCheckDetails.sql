INSERT INTO InventoryCheckDetails (
    InventoryCheckID, SKUItemID, LocationID, SystemQuantity, ActualQuantity, 
    QuantityDifferenceReason, Note, Status
)
VALUES (
    1, 
    10, 
    5, 
    100, 
    95, 
    'Damaged during storage', 
    'Found 5 damaged units on shelf', 
    'active')