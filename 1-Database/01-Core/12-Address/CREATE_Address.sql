CREATE TABLE Address (
    AddressID INT AUTO_INCREMENT PRIMARY KEY,         -- Unique identifier for the address
    StreetAddress VARCHAR(255),                       -- Street and house number
    StateProvince VARCHAR(100),                       -- State or province
    City VARCHAR(100),                                -- City name
    PostalCode VARCHAR(20),                           -- Postal or ZIP code
    Country VARCHAR(100),                             -- Country

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
