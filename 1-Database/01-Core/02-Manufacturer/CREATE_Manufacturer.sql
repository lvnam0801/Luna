CREATE TABLE Manufacturer (
    ManufacturerID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each manufacturer
    Name VARCHAR(255) NOT NULL,  -- Manufacturer name
    LogoURL VARCHAR(500),  -- URL link to the manufacturer’s logo
    StreetAddress VARCHAR(255),  -- Specific street address
    City VARCHAR(100),  -- City name
    StateProvince VARCHAR(100),  -- State or province name
    PostalCode VARCHAR(20),  -- Postal/ZIP code
    Country VARCHAR(100),  -- Country name
    Phone VARCHAR(20),  -- Contact phone number
    Email VARCHAR(255),  -- Manufacturer’s email address
    Website VARCHAR(500),  -- Manufacturer’s website URL
    Status ENUM('active', 'inactive') DEFAULT 'active'  -- Manufacturer status
);
