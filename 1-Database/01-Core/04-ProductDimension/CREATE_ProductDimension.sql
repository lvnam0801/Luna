CREATE TABLE ProductDimensions (
    DimensionID INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for each dimension
    ProductID INT NOT NULL,  -- Foreign key linking to Product table
    DimensionType VARCHAR(100) NOT NULL,  -- Type of dimension (e.g., Length, Width, Height, Weight)
    Value DECIMAL(10,2) NOT NULL,  -- The value of the dimension (e.g., 10.5, 200.00)
    Unit VARCHAR(50) NOT NULL,  -- Unit of measurement (e.g., cm, kg, inches)
    
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE  -- Ensures dimensions are deleted if the product is removed
);