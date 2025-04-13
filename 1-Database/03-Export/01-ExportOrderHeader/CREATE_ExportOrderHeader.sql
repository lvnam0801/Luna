CREATE TABLE ExportOrderHeader (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,                -- Unique export order identifier
    OrderNumber VARCHAR(100) NOT NULL UNIQUE,              -- External or internal order number
    CustomerID INT NOT NULL,                               -- Foreign key to the customer (Party)
    CarrierID INT,                                         -- Optional foreign key to the carrier (Party)
    WarehouseID INT NOT NULL,                              -- Warehouse from which the order is shipped
    ShippingAddressID INT NOT NULL,                        -- Address where the order is shipped to
    OrderDate DATE NOT NULL,                               -- Date the order was created
    RequestedDeliveryDate DATE,                            -- Requested delivery date by customer
    ShippingMethod VARCHAR(100),                           -- Chosen shipping method (e.g., ground, express)
    OrderStatus ENUM('pending', 'processing', 'shipped', 'cancelled') DEFAULT 'pending', -- Current order status
    Notes TEXT,                                             -- Any additional notes
    CreatedBy INT,                                          -- User who created the order
    CreatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,        -- Order creation timestamp

    FOREIGN KEY (CustomerID) REFERENCES Party(PartyID) ON DELETE CASCADE,
    FOREIGN KEY (CarrierID) REFERENCES Party(PartyID) ON DELETE SET NULL,
    FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE CASCADE,
    FOREIGN KEY (ShippingAddressID) REFERENCES Address(AddressID) ON DELETE CASCADE,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);
