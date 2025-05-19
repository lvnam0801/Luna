DELETE FROM Putaway;
DELETE FROM QualityInspection;
DELETE FROM ImportReceiptLineItem;
DELETE FROM ImportReceiptHeader;
DELETE FROM ImportActivityLog;

ALTER TABLE Putaway AUTO_INCREMENT = 1;
ALTER TABLE QualityInspection AUTO_INCREMENT = 1;
ALTER TABLE ImportReceiptLineItem AUTO_INCREMENT = 1;
ALTER TABLE ImportReceiptHeader AUTO_INCREMENT = 1;
ALTER TABLE ImportActivityLog AUTO_INCREMENT = 1;

