DROP TABLE IF EXISTS Shipment;
-- CREATE TABLE Shipment (
--     ShipmentID INT AUTO_INCREMENT PRIMARY KEY,             -- Unique ID for each shipment record
--     OrderID INT NOT NULL,                                  -- Related export order
--     CarrierID INT,                                         -- Carrier handling the shipment
--     ShippedBy INT,                                         -- User who processed the shipment
--     ShipmentDate DATE NOT NULL,                            -- When the shipment was sent
--     TrackingNumber VARCHAR(100),                           -- Tracking code from carrier
--     ShippingCost BIGINT,                                   -- Cost of shipping (in smallest unit, e.g., cents or VND)
--     Status ENUM('pending', 'in_transit', 'delivered', 'cancelled') DEFAULT 'pending', -- Shipment status

--     CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

--     FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
--     FOREIGN KEY (CarrierID) REFERENCES Party(PartyID) ON DELETE SET NULL,
--     FOREIGN KEY (ShippedBy) REFERENCES User(UserID) ON DELETE SET NULL
-- );
