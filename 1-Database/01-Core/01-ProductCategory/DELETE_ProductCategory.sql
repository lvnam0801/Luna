-- DELETE FROM ProductCategory WHERE CategoryID = 15;
-- -- ALTER TABLE ProductCategory AUTO_INCREMENT = 1

DELETE FROM ProductCategory
WHERE CategoryID NOT IN (
    SELECT DISTINCT CategoryID FROM Product
    WHERE CategoryID IS NOT NULL
);