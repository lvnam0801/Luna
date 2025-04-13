CREATE TABLE SKUItem (
    ItemID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each SKU item
    SKU VARCHAR(100) NOT NULL UNIQUE,       -- Unique SKU code
    Quantity INT NOT NULL DEFAULT 0,        -- Quantity available
    ProductID INT NOT NULL,                 -- Reference to Product table

    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
