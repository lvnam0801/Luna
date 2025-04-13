
-- CREATE TABLE ProductCategory (
--     CategoryID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each category
--     Name VARCHAR(255) NOT NULL,  -- Category name, text with a reasonable length
--     Description TEXT,  -- Longer text for category details
--     ParentID INT NULL,  -- Refers to another CategoryID (self-referencing for subcategories)
--     Status ENUM('Active', 'Inactive') DEFAULT 'Active' -- Indicates if the category is available
-- );

-- ALTER TABLE ProductCategory 
-- ADD CONSTRAINT fk_parent FOREIGN KEY (ParentID) REFERENCES ProductCategory(CategoryID) ON DELETE CASCADE;

-- CREATE TABLE Manufacturer (
--     ManufacturerID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each manufacturer
--     Name VARCHAR(255) NOT NULL,  -- Manufacturer name
--     LogoURL VARCHAR(500),  -- URL link to the manufacturer’s logo
--     StreetAddress VARCHAR(255),  -- Specific street address
--     City VARCHAR(100),  -- City name
--     StateProvince VARCHAR(100),  -- State or province name
--     PostalCode VARCHAR(20),  -- Postal/ZIP code
--     Country VARCHAR(100),  -- Country name
--     Phone VARCHAR(20),  -- Contact phone number
--     Email VARCHAR(255),  -- Manufacturer’s email address
--     Website VARCHAR(500),  -- Manufacturer’s website URL
--     Status ENUM('Active', 'Inactive') DEFAULT 'Active'  -- Manufacturer status
-- );

-- ALTER TABLE Manufacturer MODIFY COLUMN Email VARCHAR(255);


-- CREATE TABLE Warehouse (
--     WarehouseID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each warehouse
--     Name VARCHAR(255) NOT NULL,  -- Warehouse name
--     StreetAddress VARCHAR(255),  -- Specific street address
--     City VARCHAR(100),  -- City name
--     StateProvince VARCHAR(100),  -- State or province name
--     PostalCode VARCHAR(20),  -- Postal/ZIP code
--     Country VARCHAR(100),  -- Country name
--     Phone VARCHAR(20),  -- Contact phone number
--     Email VARCHAR(255) UNIQUE,  -- Warehouse email (must be unique)
--     Status ENUM('active', 'inactive') DEFAULT 'active'  -- Warehouse operational status
-- );

-- ALTER TABLE Warehouse MODIFY COLUMN Status ENUM('active', 'inactive') DEFAULT 'active';

-- CREATE TABLE WarehouseCapacity (
--     CapacityID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each capacity entry
--     WarehouseID INT,  -- Foreign key to link to the Warehouse table
--     CapacityType ENUM('Volume', 'Weight', 'Pallets') NOT NULL,  -- Type of capacity
--     Value DECIMAL(10,2) NOT NULL,  -- The capacity value (e.g., 1000 cubic meters, 20000 kg)
--     Unit VARCHAR(50) NOT NULL,  -- The measurement unit (e.g., "cubic meters", "kg")
--     Status ENUM('Active', 'Inactive') DEFAULT 'Active',  -- Status of the capacity entry
--     FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE CASCADE
-- );

-- CREATE TABLE Product (
--     ProductID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each product specification
--     Name VARCHAR(255) NOT NULL,  -- Product name
--     PhotoURL VARCHAR(500),  -- URL to product image
--     Origin VARCHAR(100),  -- Country or region where the product is manufactured
--     WholesalePrice BIGINT NOT NULL,  -- Wholesale price (supplier price)
--     RetailPrice BIGINT NOT NULL,  -- Retail price for customers
--     Status ENUM('active', 'inactive') DEFAULT 'active',  -- Specification status (if the product is still in production)
--     ManufacturerID INT,  -- Foreign key to the Manufacturer table
--     CategoryID INT,  -- Foreign key to the Product Category table
--     FOREIGN KEY (ManufacturerID) REFERENCES Manufacturer(ManufacturerID) ON DELETE SET NULL,
--     FOREIGN KEY (CategoryID) REFERENCES ProductCategory(CategoryID) ON DELETE SET NULL
-- );

-- ALTER TABLE Product MODIFY COLUMN WholesalePrice BIGINT;
-- ALTER TABLE Product MODIFY COLUMN RetailPrice BIGINT;
-- ALTER TABLE Product MODIFY COLUMN Status ENUM('active', 'inactive') DEFAULT 'active';

-- CREATE TABLE ProductDimensions (
--     DimensionID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each dimension
--     ProductID INT NOT NULL,  -- Foreign key linking to Product table
--     DimensionType VARCHAR(100) NOT NULL,  -- Type of dimension (e.g., Length, Width, Height, Weight)
--     Value DECIMAL(10,2) NOT NULL,  -- The value of the dimension (e.g., 10.5, 200.00)
--     Unit VARCHAR(50) NOT NULL,  -- Unit of measurement (e.g., cm, kg, inches)
    
--     FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE  -- Ensures dimensions are deleted if the product is removed
-- );


-- CREATE TABLE ProductInstance (
--     InstanceID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for each physical product instance
--     ProductID INT NOT NULL,  -- Links to the product specification
--     WarehouseID INT NOT NULL,  -- Links to the warehouse where the product is stored
--     SKU VARCHAR(50) UNIQUE NOT NULL,  -- Stock Keeping Unit (unique product identifier),
--     Quantity INT DEFAULT 0,  -- Number of items in stock
--     ExpiredDate DATE,  -- Expiration date for perishable products
--     Status ENUM('in-stock', 'sold', 'reserved', 'damaged', 'returned') DEFAULT 'in-stock',  -- Instance-specific status
--     FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
--     FOREIGN KEY (WarehouseID) REFERENCES Warehouse(WarehouseID) ON DELETE CASCADE
-- );

-- ALTER TABLE ProductInstance MODIFY COLUMN Status ENUM('in-stock', 'sold', 'reserved', 'damaged', 'returned') DEFAULT 'in-stock';

-- ALTER TABLE ProductInstance ADD COLUMN Quantity INT DEFAULT 0; 
-- ALTER TABLE ProductInstance ADD COLUMN ExpiredDate DATE;