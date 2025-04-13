CREATE TABLE Role (
    RoleID INT AUTO_INCREMENT PRIMARY KEY,            -- Unique identifier for each role
    RoleName VARCHAR(100) NOT NULL UNIQUE,            -- Name of the role (e.g., admin, user)
    Description TEXT,                                 -- Description of the role
    Status ENUM('active', 'inactive') DEFAULT 'active', -- Role status

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
