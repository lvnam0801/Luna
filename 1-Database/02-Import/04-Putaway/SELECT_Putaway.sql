SELECT
                p.PutawayID,
                p.PutawayNumber,
                p.ReceiptLineItemID,
                p.PutawayAtLocationID,
                loc.Value AS PutawayAtLocationName,
                p.SKUItemID,
                sku.SKU AS SKU,
                p.Quantity,
                p.PutawayResult,
                p.Status,
                p.PutawayBy,
                CONCAT(u1.FirstName, ' ', u1.LastName) AS PutawayByName,
                p.PutawayDate,
                p.CreatedBy,
                CONCAT(u2.FirstName, ' ', u2.LastName) AS CreatedByName,
                p.CreatedAt,
                p.UpdatedBy,
                CONCAT(u3.FirstName, ' ', u3.LastName) AS UpdatedByName,
                p.UpdatedAt
            FROM Putaway p
            LEFT JOIN Location loc ON p.PutawayAtLocationID = loc.LocationID
            LEFT JOIN SKUItem sku ON p.SKUItemID = sku.ItemID
            LEFT JOIN User u1 ON p.PutawayBy = u1.UserID
            LEFT JOIN User u2 ON p.CreatedBy = u2.UserID
            LEFT JOIN User u3 ON p.UpdatedBy = u3.UserID
            WHERE p.PutawayID = 1