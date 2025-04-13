CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,            -- Unique identifier for each user
    UserName VARCHAR(100) NOT NULL UNIQUE,            -- Unique username
    Password VARCHAR(255) NOT NULL,                   -- Hashed password
    FirstName VARCHAR(100),                           -- User's first name
    LastName VARCHAR(100),                            -- User's last name
    PhotoURL VARCHAR(500),                            -- Optional profile photo URL
    Email VARCHAR(255) UNIQUE,                        -- User's email address
    Phone VARCHAR(20),                                -- Contact phone number
    Status ENUM('active', 'inactive') DEFAULT 'active', -- Account status
    RoleID INT,                                       -- Reference to Role table

    FOREIGN KEY (RoleID) REFERENCES Role(RoleID) ON DELETE SET NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
