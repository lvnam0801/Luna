-- Clear existing data and reset auto-increment ID

DELETE FROM ProductDimensions;
ALTER TABLE ProductDimensions AUTO_INCREMENT = 1;

-- Seed ProductDimensions data
INSERT INTO ProductDimensions (ProductID, DimensionType, Value, Unit) VALUES
-- Product 1
(1, 'Chiều dài', 25.5, 'cm'),
(1, 'Chiều rộng', 15.0, 'cm'),
(1, 'Chiều cao', 10.0, 'cm'),
(1, 'Khối lượng', 0.75, 'kg'),

-- Product 2
(2, 'Chiều dài', 30.0, 'cm'),
(2, 'Chiều rộng', 20.0, 'cm'),
(2, 'Chiều cao', 12.5, 'cm'),
(2, 'Khối lượng', 1.2, 'kg'),

-- Product 3
(3, 'Chiều dài', 10.0, 'cm'),
(3, 'Chiều rộng', 8.0, 'cm'),
(3, 'Chiều cao', 4.0, 'cm'),
(3, 'Khối lượng', 0.3, 'kg'),

-- Product 4
(4, 'Chiều dài', 50.0, 'cm'),
(4, 'Chiều rộng', 40.0, 'cm'),
(4, 'Chiều cao', 30.0, 'cm'),
(4, 'Khối lượng', 3.5, 'kg'),

-- Product 5
(5, 'Chiều dài', 12.0, 'cm'),
(5, 'Chiều rộng', 12.0, 'cm'),
(5, 'Chiều cao', 20.0, 'cm'),
(5, 'Khối lượng', 1.0, 'kg');