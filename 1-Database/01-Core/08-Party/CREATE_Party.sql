-- CREATE TABLE Party (
--     PartyID INT AUTO_INCREMENT PRIMARY KEY,       -- Unique identifier
--     PartyType ENUM('supplier', 'carrier', 'customer', 'internal', 'other') NOT NULL, -- Type of party
--     ContactName VARCHAR(255),                     -- Name of the contact person
    
--     StreetAddress VARCHAR(255),
--     StateProvince VARCHAR(100),
--     City VARCHAR(100),
--     PostalCode VARCHAR(20),
--     Country VARCHAR(100),

--     Phone VARCHAR(20),                            -- Phone number
--     Email VARCHAR(255),                           -- Email address
--     Note TEXT,                                     -- Any additional notes
--     Status ENUM('active', 'inactive') DEFAULT 'active', -- Party status

--     CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
-- );
