CREATE TABLE ExportProduct (
    ExportProductID INT AUTO_INCREMENT PRIMARY KEY,     -- Unique identifier for each exported product
    OrderID INT NOT NULL,                               -- Reference to the export order
    ProductID INT NOT NULL,                             -- Reference to the product being exported
    Amount INT NOT NULL,                                -- Quantity of the product exported

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (OrderID) REFERENCES ExportOrderHeader(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE
);
