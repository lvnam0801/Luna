-- SELECT sp.ShipmentPackingID, sp.ShipmentID, sp.PackingID, p.PackingNumber
-- FROM ShipmentPacking sp
-- JOIN Packing p ON sp.PackingID = p.PackingID
-- WHERE sp.ShipmentID = 1
DELETE FROM ShipmentPacking;