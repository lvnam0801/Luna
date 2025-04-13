CREATE TABLE ImportProduct (
    ImportProductID INT AUTO_INCREMENT PRIMARY KEY,     -- Unique identifier for each record
    ReceiptID INT NOT NULL,                             -- Reference to the import receipt
    ProductID INT NOT NULL,                             -- Reference to the product being imported
    Amount INT NOT NULL,                                -- Quantity of the product imported

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (ReceiptID) REFERENCES ImportReceiptHeader(ReceiptID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE
);
