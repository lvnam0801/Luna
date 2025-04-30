CREATE TABLE ImportActivityLog (
    ActivityLogID INT AUTO_INCREMENT PRIMARY KEY,
    ReceiptID INT NOT NULL,
    UserID INT,
    TargetType ENUM('Receipt', 'LineItem', 'Putaway','QualityInspection', 'SKUItem') NOT NULL,
    ActionType ENUM('create', 'update', 'delete') NOT NULL,
    TargetID INT,
    LoggedTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Content TEXT NOT NULL,
    Status ENUM('active', 'inactive') DEFAULT 'active',

    FOREIGN KEY (ReceiptID) REFERENCES ImportReceiptHeader(ReceiptID) ON DELETE CASCADE,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE SET NULL
);