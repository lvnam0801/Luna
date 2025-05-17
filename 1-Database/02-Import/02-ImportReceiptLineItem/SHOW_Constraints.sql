SELECT
    rc.constraint_name,
    kcu.column_name,
    rc.referenced_table_name,
    kcu.referenced_column_name
FROM
    information_schema.referential_constraints rc
JOIN
    information_schema.key_column_usage kcu
    ON rc.constraint_name = kcu.constraint_name
WHERE
    rc.constraint_schema = 'LunaWMS'
    AND kcu.table_name = 'ImportReceiptLineItem';