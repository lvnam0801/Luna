CREATE TABLE Product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each product specification
    ProductCode VARCHAR(100) NOT NULL UNIQUE,
    Name VARCHAR(255) NOT NULL,  -- Product name
    PhotoURL VARCHAR(500),  -- URL to product image
    Origin VARCHAR(100),  -- Country or region where the product is manufactured
    WholesalePrice BIGINT NOT NULL,  -- Wholesale price (supplier price)
    RetailPrice BIGINT NOT NULL,  -- Retail price for customers
    Status ENUM('active', 'inactive') DEFAULT 'active',  -- Specification status (if the product is still in production)
    ManufacturerID INT,  -- Foreign key to the Manufacturer table
    CategoryID INT,  -- Foreign key to the Product Category table
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (ManufacturerID) REFERENCES Manufacturer(ManufacturerID) ON DELETE SET NULL,
    FOREIGN KEY (CategoryID) REFERENCES ProductCategory(CategoryID) ON DELETE SET NULL
);