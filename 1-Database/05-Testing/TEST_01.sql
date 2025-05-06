-- Insert test Product Category
INSERT INTO ProductCategory (Name, Description, ParentID, Status)
VALUES ('Electronics', 'Electronic Items', NULL, 'active');

-- Insert test Manufacturer
INSERT INTO Manufacturer (Name, Phone, Email, Website, LogoURL, StreetAddress, City, StateProvince, PostalCode, Country, Status)
VALUES ('TechCorp', '1234567890', 'contact@techcorp.com', 'https://techcorp.com', 'logo.png', '123 Tech St', 'Saigon', 'HCM', '700000', 'Vietnam', 'active');

-- Insert test Product
INSERT INTO Product (Name, PhotoURL, Origin, RetailPrice, WholesalePrice, Status, ManufacturerID)
VALUES ('Smartphone X', 'smartphone.jpg', 'Vietnam', 10000000, 8000000, 'active', 1);

-- Insert test SKUItem
INSERT INTO SKUItem (SKU, Quantity, ProductID)
VALUES ('SKU-0001', 100, 1);

-- Insert test Warehouse
INSERT INTO Warehouse (Name, Phone, Email, StreetAddress, City, StateProvince, PostalCode, Country, Status)
VALUES ('Main Warehouse', '0987654321', 'main@warehouse.com', '456 Storage Ave', 'Saigon', 'HCM', '700000', 'Vietnam', 'active');

-- Insert test Location
INSERT INTO Location (LocationType, Value, Unit, Status, WarehouseID)
VALUES ('Shelf', 'A1', 'box', 'active', 1);

-- Insert test Party
INSERT INTO Party (PartyType, ContactName, Phone, Email, Note, Status, StreetAddress, City, StateProvince, PostalCode, Country)
VALUES ('supplier', 'Supplier One', '111222333', 'supplier1@example.com', 'Main supplier', 'active', '12 Supplier Rd', 'Saigon', 'HCM', '700000', 'Vietnam');

-- Insert test User
INSERT INTO User (UserName, Password, FirstName, LastName, Email, Phone, Status, RoleID)
VALUES ('testuser', 'hashedpassword', 'Nam', 'Le', 'nam@example.com', '0123456789', 'active', NULL);

-- Insert test Address
INSERT INTO Address (StreetAddress, City, StateProvince, PostalCode, Country)
VALUES ('789 Delivery Ln', 'Hanoi', 'HN', '100000', 'Vietnam');

-- Insert test Import Receipt Header
INSERT INTO ImportReceiptHeader (ReceiptNumber, ASNNumber, PONumber, OriginLocationID, ExpectedArrivalDate, ActualArrivalDate, ReceivingDock, ReceiptStatus, Notes, CarrierID, SupplierID, WarehouseID, CreatedBy)
VALUES ('RCPT-001', 'ASN-001', 'PO-001', 1, '2024-01-01', '2024-01-02', 'Dock 5', 'completed', 'Initial import', 1, 1, 1, 1);

-- Insert test Import Receipt Line Item
INSERT INTO ImportReceiptLineItem (ReceiptID, ItemID, LineItemNumber, ExpectedQuantity, ReceivedQuantity, DiscrepancyReasonCode, LotNumber, SerialNumber, ExpirationDate, UnitCost, Status)
VALUES (1, 1, 1, 100, 100, NULL, 'LOT-01', 'SN-01', '2025-01-01', 100000, 'inspected');

-- Insert test Quality Inspection
INSERT INTO QualityInspection (ReceiptLineItemID, InspectorID, InspectionDate, InspectionStatus, Result, QuarantineLocationID, Notes, Status)
VALUES (1, 1, '2024-01-03', 'passed', 'All good', NULL, '', 'active');

-- Insert test Putaway
INSERT INTO Putaway (ReceiptLineItemID, LocationID, PutawayBy, Quantity, PutawayDate, Status)
VALUES (1, 1, 1, 100, '2024-01-04', 'completed');

-- Insert test ImportProduct
INSERT INTO ImportProduct (ReceiptID, ProductID, Amount)
VALUES (1, 1, 100);

-- Insert test Export Order Header
INSERT INTO ExportOrderHeader (OrderNumber, CustomerID, CarrierID, WarehouseID, ShippingAddressID, OrderDate, RequestedDeliveryDate, ShippingMethod, OrderStatus, Notes, CreatedBy)
VALUES ('ORD-001', 1, 1, 1, 1, '2024-02-01', '2024-02-05', 'ground', 'processing', 'Customer order', 1);

-- Insert test Export Order Line Item
INSERT INTO ExportOrderLineItem (OrderID, ItemID, LineItemNumber, OrderedQuantity, ShippedQuantity, UnitPrice, RequestedDeliveryDate, Notes, Status)
VALUES (1, 1, 1, 2, 0, 11000000, '2024-02-05', '', 'pending');

-- Insert test Picking Task
INSERT INTO PickingTask (OrderLineItemID, LocationID, PickedBy, QuantityPicked, PickedDate, Status)
VALUES (1, 1, 1, 2, '2024-02-03', 'completed');

-- Insert test Packing
INSERT INTO Packing (OrderID, PackedBy, PackingDate, PackageNumber, PackageWeight, PackageDimension, Notes, Status)
VALUES (1, 1, '2024-02-04', 'PKG-001', 1.5, '20x20x10', '', 'packed');

-- Insert test Shipment
INSERT INTO Shipment (OrderID, CarrierID, ShippedBy, ShipmentDate, TrackingNumber, ShippingCost, Status)
VALUES (1, 1, 1, '2024-02-05', 'TRACK-001', 150000, 'in_transit');

-- Insert test Export Product
INSERT INTO ExportProduct (OrderID, ProductID, Amount)
VALUES (1, 1, 2);

-- Insert test Direct Transition
INSERT INTO DirectTransition (ReceiptID, OrderID, TransitionDate)
VALUES (1, 1, '2024-01-05');
