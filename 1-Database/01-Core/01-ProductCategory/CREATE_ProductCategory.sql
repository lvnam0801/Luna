CREATE TABLE ProductCategory (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each category
    Name VARCHAR(255) NOT NULL,  -- Category name, text with a reasonable length
    Description TEXT,  -- Longer text for category details
    ParentID INT NULL,  -- Refers to another CategoryID (self-referencing for subcategories)
    Status ENUM('Active', 'Inactive') DEFAULT 'Active' -- Indicates if the category is available
);

ALTER TABLE ProductCategory 
ADD CONSTRAINT fk_parent FOREIGN KEY (ParentID) REFERENCES ProductCategory(CategoryID) ON DELETE CASCADE;