CREATE TABLE Warehouse (
    WarehouseID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each warehouse
    Name VARCHAR(255) NOT NULL,  -- Warehouse name
    StreetAddress VARCHAR(255),  -- Specific street address
    City VARCHAR(100),  -- City name
    StateProvince VARCHAR(100),  -- State or province name
    PostalCode VARCHAR(20),  -- Postal/ZIP code
    Country VARCHAR(100),  -- Country name
    Phone VARCHAR(20),  -- Contact phone number
    Email VARCHAR(255) UNIQUE,  -- Warehouse email (must be unique)
    Status ENUM('active', 'inactive') DEFAULT 'active'  -- Warehouse operational status
);
