CREATE TABLE Party (
    PartyID INT AUTO_INCREMENT PRIMARY KEY,       -- Unique identifier
    PartyType VARCHAR(100) NOT NULL,              -- Type of party (e.g., Supplier, Carrier, Customer)
    ContactName VARCHAR(255),                     -- Name of the contact person
    Phone VARCHAR(20),                            -- Phone number
    Email VARCHAR(255),                           -- Email address
    Note TEXT,                                     -- Any additional notes
    Status ENUM('active', 'inactive') DEFAULT 'active', -- Party status

    StreetAddress VARCHAR(255),
    City VARCHAR(100),
    StateProvince VARCHAR(100),
    PostalCode VARCHAR(20),
    Country VARCHAR(100),

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
