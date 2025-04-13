CREATE TABLE Permission (
    PermissionID INT AUTO_INCREMENT PRIMARY KEY,       -- Unique identifier for each permission
    PermissionName VARCHAR(255) NOT NULL UNIQUE,       -- Name of the permission (e.g., view_user, edit_product)
    Description TEXT,                                  -- Description of what the permission allows
    Status ENUM('active', 'inactive') DEFAULT 'active',-- Permission status

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
