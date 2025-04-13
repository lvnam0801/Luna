CREATE TABLE QualityInspection (
    InspectionID INT AUTO_INCREMENT PRIMARY KEY,            -- Unique identifier for this inspection
    ReceiptLineItemID INT NOT NULL,                         -- Line item being inspected
    InspectorID INT,                                        -- User who performed the inspection
    InspectionDate DATE NOT NULL,                           -- Date the inspection took place

    InspectionStatus ENUM('pending', 'passed', 'failed', 'quarantined') DEFAULT 'pending', -- Inspection outcome status
    Result TEXT,                                            -- Optional summary or detailed result
    QuarantineLocationID INT,                               -- If failed, where the items are stored temporarily
    Notes TEXT,                                             -- Additional notes from the inspector
    Status ENUM('active', 'inactive') DEFAULT 'active',     -- Row status (enabled/disabled)

    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (ReceiptLineItemID) REFERENCES ImportReceiptLineItem(ReceiptLineItemID) ON DELETE CASCADE,
    FOREIGN KEY (InspectorID) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (QuarantineLocationID) REFERENCES Location(LocationID) ON DELETE SET NULL
);
