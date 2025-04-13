CREATE TABLE ExportOrderLineItem (
    OrderLineItemID INT AUTO_INCREMENT PRIMARY KEY,       -- Unique identifier for the line item
    OrderID INT NOT NULL,                                 -- Associated export order
    ItemID INT NOT NULL,                                  -- SKU item being exported
    LineItemNumber INT,                                   -- Line number for sorting/reference

    OrderedQuantity INT NOT NULL,                         -- Quantity ordered by the customer
    ShippedQuantity INT DEFAULT 0,                        -- Quantity that has been shipped
    UnitPrice BIGINT,                                     -- Price per unit
    RequestedDeliveryDate DATE,                           -- Customer's preferred delivery date
    Notes TEXT,                                           -- Additional details
    Status ENUM('pending', 'picked', 'packed', 'shipped', 'cancelled') DEFAULT 'pending', -- Order item status

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ItemID) REFERENCES SKUItem(ItemID) ON DELETE CASCADE
);
