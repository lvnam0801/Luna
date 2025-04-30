package com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.Address.Representation.Address;
import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeader;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderCreateResponse;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderUpdateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderUpdateResponse;

@Service
public class ImportReceiptHeaderServiceImpl implements ImportReceiptHeaderService
{
    private final JdbcTemplate jdbcTemplate;
    private final ImportActivityLogService importActivityLogService;
    private final UserContext userContext;

    public ImportReceiptHeaderServiceImpl(JdbcTemplate jdbcTemplate, ImportActivityLogService importActivityLogService, UserContext userContext)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.importActivityLogService = importActivityLogService;
        this.userContext = userContext;
    }
    
   @Override
    public ImportReceiptHeader[] getAllReceipts() {
        String sql = """
            SELECT
                h.ReceiptID,
                h.ReceiptNumber,
                h.ASNNumber,
                h.PONumber,

                h.OriginLocationID,
                a.StreetAddress,
                a.City,
                a.StateProvince,
                a.PostalCode,
                a.Country,

                h.ExpectedArrivalDate,
                h.ActualArrivalDate,
                h.ReceiptStatus,
                h.Notes,
                h.Status,

                h.CarrierID,
                carrier.ContactName AS CarrierName,
                h.SupplierID,
                supplier.ContactName AS SupplierName,

                h.WarehouseID,
                w.Name AS WarehouseName,

                h.ReceivingDockID,
                l.LocationName AS ReceivingDockName,

                h.CreatedBy,
                createdByUser.FullName AS CreatedByName,
                h.CreatedAt,

                h.UpdatedBy,
                updatedByUser.FullName AS UpdatedByName,
                h.UpdatedAt

            FROM ImportReceiptHeader h
            LEFT JOIN Address a ON h.OriginLocationID = a.AddressID
            LEFT JOIN Party carrier ON h.CarrierID = carrier.PartyID
            LEFT JOIN Party supplier ON h.SupplierID = supplier.PartyID
            LEFT JOIN Warehouse w ON h.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON h.ReceivingDockID = l.LocationID
            LEFT JOIN User createdByUser ON h.CreatedBy = createdByUser.UserID
            LEFT JOIN User updatedByUser ON h.UpdatedBy = updatedByUser.UserID
        """;

        List<ImportReceiptHeader> receipts = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new ImportReceiptHeader(
                rs.getInt("ReceiptID"),
                rs.getString("ReceiptNumber"),
                rs.getString("ASNNumber"),
                rs.getString("PONumber"),

                rs.getInt("OriginLocationID"),
                new Address(
                    rs.getInt("OriginLocationID"),
                    rs.getString("StreetAddress"),
                    rs.getString("City"),
                    rs.getString("StateProvince"),
                    rs.getString("PostalCode"),
                    rs.getString("Country")
                ),

                rs.getDate("ExpectedArrivalDate"),
                rs.getDate("ActualArrivalDate"),
                rs.getString("ReceiptStatus"),
                rs.getString("Notes"),
                rs.getString("Status"),

                rs.getInt("CarrierID"),
                rs.getString("CarrierName"),
                rs.getInt("SupplierID"),
                rs.getString("SupplierName"),

                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),

                rs.getInt("ReceivingDockID"),
                rs.getString("ReceivingDockName"),

                rs.getInt("CreatedBy"),
                rs.getString("CreatedByName"),
                rs.getTimestamp("CreatedAt"),
                rs.getInt("UpdatedBy"),
                rs.getString("UpdatedByName"),
                rs.getTimestamp("UpdatedAt")
            )
        );

        return receipts.toArray(ImportReceiptHeader[]::new);
    }

    @Override
    public ImportReceiptHeader getReceiptById(int id) {
        String sql = """
            SELECT
                h.ReceiptID,
                h.ReceiptNumber,
                h.ASNNumber,
                h.PONumber,

                h.OriginLocationID,
                a.StreetAddress,
                a.City,
                a.StateProvince,
                a.PostalCode,
                a.Country,

                h.ExpectedArrivalDate,
                h.ActualArrivalDate,
                h.ReceiptStatus,
                h.Notes,
                h.Status,

                h.CarrierID,
                carrier.ContactName AS CarrierName,
                h.SupplierID,
                supplier.ContactName AS SupplierName,

                h.WarehouseID,
                w.Name AS WarehouseName,

                h.ReceivingDockID,
                l.LocationName AS ReceivingDockName,

                h.CreatedBy,
                createdByUser.FullName AS CreatedByName,
                h.CreatedAt,

                h.UpdatedBy,
                updatedByUser.FullName AS UpdatedByName,
                h.UpdatedAt

            FROM ImportReceiptHeader h
            LEFT JOIN Address a ON h.OriginLocationID = a.AddressID
            LEFT JOIN Party carrier ON h.CarrierID = carrier.PartyID
            LEFT JOIN Party supplier ON h.SupplierID = supplier.PartyID
            LEFT JOIN Warehouse w ON h.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON h.ReceivingDockID = l.LocationID
            LEFT JOIN User createdByUser ON h.CreatedBy = createdByUser.UserID
            LEFT JOIN User updatedByUser ON h.UpdatedBy = updatedByUser.UserID
            WHERE h.ReceiptID = ?
        """;

        try {
            ImportReceiptHeader receipt = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new ImportReceiptHeader(
                    rs.getInt("ReceiptID"),
                    rs.getString("ReceiptNumber"),
                    rs.getString("ASNNumber"),
                    rs.getString("PONumber"),

                    rs.getInt("OriginLocationID"),
                    new Address(
                        rs.getInt("OriginLocationID"),
                        rs.getString("StreetAddress"),
                        rs.getString("City"),
                        rs.getString("StateProvince"),
                        rs.getString("PostalCode"),
                        rs.getString("Country")
                    ),

                    rs.getDate("ExpectedArrivalDate"),
                    rs.getDate("ActualArrivalDate"),
                    rs.getString("ReceiptStatus"),
                    rs.getString("Notes"),
                    rs.getString("Status"),

                    rs.getInt("CarrierID"),
                    rs.getString("CarrierName"),
                    rs.getInt("SupplierID"),
                    rs.getString("SupplierName"),

                    rs.getInt("WarehouseID"),
                    rs.getString("WarehouseName"),

                    rs.getInt("ReceivingDockID"),
                    rs.getString("ReceivingDockName"),

                    rs.getInt("CreatedBy"),
                    rs.getString("CreatedByName"),
                    rs.getTimestamp("CreatedAt"),
                    rs.getInt("UpdatedBy"),
                    rs.getString("UpdatedByName"),
                    rs.getTimestamp("UpdatedAt")
                )
            );
            return receipt;
        } catch (EmptyResultDataAccessException e) {
            return null; // No result found for this ID
        }
    }

    @Override
    public ImportReceiptHeaderCreateResponse createReceipt(ImportReceiptHeaderCreateRequest request) 
    {
        String sql = """
            INSERT INTO ImportReceiptHeader (
                ReceiptNumber, ASNNumber, PONumber, OriginLocationID,
                ExpectedArrivalDate, ActualArrivalDate,
                ReceiptStatus, Notes,
                CarrierID, SupplierID, WarehouseID,
                ReceivingDockID,
                CreatedBy,
                UpdatedBy,
                Status
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            request.receiptNumber(),
            request.asnNumber(),
            request.poNumber(),
            request.originLocationID(),
            request.expectedArrivalDate(),
            request.actualArrivalDate(),
            request.receiptStatus(),
            request.notes(),
            request.carrierID(),
            request.supplierID(),
            request.warehouseID(),
            request.receivingDockID(),
            userContext.getCurrentUserID(),
            userContext.getCurrentUserID(),
            request.status()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        if (id == null) {
            throw new IllegalStateException("Failed to retrieve the last inserted ID.");
        }
        // Log creation activity
        importActivityLogService.log(
            id,
            userContext.getCurrentUserID(),
            ImportActivityTargetType.RECEIPT.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo phiếu nhập: " + request.receiptNumber()
        );
        String message = "Import Receipt created successfully.";
        ImportReceiptHeaderCreateResponse response = new ImportReceiptHeaderCreateResponse(id, message);
        return response;
    }

    @Override
    public ImportReceiptHeaderUpdateResponse updateReceiptPartially(Integer receiptID, ImportReceiptHeaderUpdateRequest request) {
        List<String> updates = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (request.receiptNumber() != null) {
            updates.add("ReceiptNumber = ?");
            params.add(request.receiptNumber());
        }
        if (request.asnNumber() != null) {
            updates.add("ASNNumber = ?");
            params.add(request.asnNumber());
        }
        if (request.poNumber() != null) {
            updates.add("PONumber = ?");
            params.add(request.poNumber());
        }
        if (request.originLocationID() != null) {
            updates.add("OriginLocationID = ?");
            params.add(request.originLocationID());
        }
        if (request.expectedArrivalDate() != null) {
            updates.add("ExpectedArrivalDate = ?");
            params.add(request.expectedArrivalDate());
        }
        if (request.actualArrivalDate() != null) {
            updates.add("ActualArrivalDate = ?");
            params.add(request.actualArrivalDate());
        }
        if (request.receiptStatus() != null) {
            updates.add("ReceiptStatus = ?");
            params.add(request.receiptStatus());
        }
        if (request.notes() != null) {
            updates.add("Notes = ?");
            params.add(request.notes());
        }
        if (request.carrierID() != null) {
            updates.add("CarrierID = ?");
            params.add(request.carrierID());
        }
        if (request.supplierID() != null) {
            updates.add("SupplierID = ?");
            params.add(request.supplierID());
        }
        if (request.warehouseID() != null) {
            updates.add("WarehouseID = ?");
            params.add(request.warehouseID());
        }
        if (request.receivingDockID() != null) {
            updates.add("ReceivingDockID = ?");
            params.add(request.receivingDockID());
        }
        if (request.status() != null) {
            updates.add("Status = ?");
            params.add(request.status());
        }

        if (updates.isEmpty()) {
            throw new IllegalArgumentException("No fields provided to update.");
        }
        // Add the updatedBy field
        updates.add("UpdatedBy = ?");
        params.add(userContext.getCurrentUserID());
        // Add the updatedAt field
        updates.add("UpdatedAt = CURRENT_TIMESTAMP");

        // Build and execute the SQL
        String sql = "UPDATE ImportReceiptHeader SET " + String.join(", ", updates) + " WHERE ReceiptID = ?";
        params.add(receiptID);

        int rows = jdbcTemplate.update(sql, params.toArray());
        if (rows == 0) {
            throw new IllegalStateException("Update failed: no rows affected.");
        }

        String receiptNumber = getReceiptNumberByID(receiptID);
        if (receiptNumber == null) {
            throw new IllegalStateException("Update failed: receipt not found.");
        }
        String content = "Cập nhật phiếu nhập: " + receiptNumber;
        // Log update activity
        importActivityLogService.log(
            receiptID,
            userContext.getCurrentUserID(),
            ImportActivityTargetType.RECEIPT.value(),
            ImportActivityActionType.UPDATE.value(),
            receiptID,
            content
        );
        return new ImportReceiptHeaderUpdateResponse(receiptID, "Import Receipt updated successfully.");
    }

    private String getReceiptNumberByID(Integer receiptID) {
        String sql = "SELECT ReceiptNumber FROM ImportReceiptHeader WHERE ReceiptID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{receiptID}, String.class);
    }
}
