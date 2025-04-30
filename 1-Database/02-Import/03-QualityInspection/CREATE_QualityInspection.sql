-- DROP TABLE IF EXISTS QualityInspection;

CREATE TABLE QualityInspection (
    InspectionID INT AUTO_INCREMENT PRIMARY KEY,                        -- Unique identifier for this inspection
    InspectionNumber VARCHAR(100) NOT NULL UNIQUE,                             -- Unique identifier for the inspection
    ReceiptLineItemID INT NOT NULL,                                     -- The line item being inspected
    InspectedBy INT,                                                    -- User who performed the inspection
    InspectionDate DATE NOT NULL,                                       -- Date when inspection took place
    InspectedLocationID INT,                                            -- Location where inspection was performed
    Quantity INT NOT NULL,                                              -- Number of items inspected
    InspectionResult ENUM('passed', 'failed') NOT NULL,                 -- Result of the inspection
    Notes TEXT,                                                         -- Additional notes from the inspector

    Status ENUM('pending', 'active', 'cancelled', 'archived')          -- Process state of the inspection
        DEFAULT 'active',                                              -- Controls visibility/editability
    
    CreatedBy INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedBy INT,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (ReceiptLineItemID) REFERENCES ImportReceiptLineItem(ReceiptLineItemID) ON DELETE CASCADE,
    FOREIGN KEY (InspectedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (InspectedLocationID) REFERENCES Location(LocationID) ON DELETE SET NULL,
    FOREIGN KEY (CreatedBy) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (UpdatedBy) REFERENCES User(UserID) ON DELETE SET NULL
);

