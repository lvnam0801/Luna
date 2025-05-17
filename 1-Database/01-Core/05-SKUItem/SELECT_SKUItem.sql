SELECT COUNT(DISTINCT s.ProductID)
            FROM SKUItem s
            WHERE s.Status = 'in_stock' AND s.Quantity > 0