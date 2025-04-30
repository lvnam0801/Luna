CREATE TABLE ExportOrderLineItem (
    OrderLineItemID INT AUTO_INCREMENT PRIMARY KEY,       -- Unique export line item ID
    OrderID INT NOT NULL,                            -- Reference to ExportOrderHeader
    ProductID INT NOT NULL,                          -- Product to be exported
    LineItemNumber VARCHAR(100) NOT NULL UNIQUE,     -- Line identifier (unique within order)

    ExportedQuantity INT NOT NULL,                   -- Quantity of items to be exported
    LotNumber VARCHAR(100),                          -- Optional batch/lot tracking
    ExpirationDate DATE,                             -- For perishables
    UnitPrice DECIMAL(10,2),                         -- Price per unit for export

    Status ENUM('pending', 'picked', 'packed', 'shipped', 'cancelled') DEFAULT 'pending', -- Fulfillment state

    CreatedBy INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UpdatedBy INT,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);