package com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.Address.Representation.Address;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeader;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptHeader.Representation.ImportReceiptHeaderCreateResponse;

@Service
public class ImportReceiptHeaderServiceImpl implements ImportReceiptHeaderService
{
    private final JdbcTemplate jdbcTemplate;
    private final ImportActivityLogService importActivityLogService;

    public ImportReceiptHeaderServiceImpl(JdbcTemplate jdbcTemplate, ImportActivityLogService importActivityLogService)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.importActivityLogService = importActivityLogService;
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

                h.CarrierID,
                carrier.ContactName AS CarrierName,
                h.SupplierID,
                supplier.ContactName AS SupplierName,

                h.WarehouseID,
                w.Name AS WarehouseName,

                h.ReceivingDockID,
                l.Value AS ReceivingDockName,

                h.CreatedBy,
                h.CreatedAt,
                h.UpdatedAt

            FROM ImportReceiptHeader h
            LEFT JOIN Address a ON h.OriginLocationID = a.AddressID
            LEFT JOIN Party carrier ON h.CarrierID = carrier.PartyID
            LEFT JOIN Party supplier ON h.SupplierID = supplier.PartyID
            LEFT JOIN Warehouse w ON h.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON h.ReceivingDockID = l.LocationID
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

                rs.getInt("CarrierID"),
                rs.getString("CarrierName"),
                rs.getInt("SupplierID"),
                rs.getString("SupplierName"),

                rs.getInt("WarehouseID"),
                rs.getString("WarehouseName"),

                rs.getInt("ReceivingDockID"),
                rs.getString("ReceivingDockName"),

                rs.getInt("CreatedBy"),
                rs.getTimestamp("CreatedAt"),
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

                h.CarrierID,
                carrier.ContactName AS CarrierName,
                h.SupplierID,
                supplier.ContactName AS SupplierName,

                h.WarehouseID,
                w.Name AS WarehouseName,

                h.ReceivingDockID,
                l.Value AS ReceivingDockName,

                h.CreatedBy,
                h.CreatedAt,
                h.UpdatedAt

            FROM ImportReceiptHeader h
            LEFT JOIN Address a ON h.OriginLocationID = a.AddressID
            LEFT JOIN Party carrier ON h.CarrierID = carrier.PartyID
            LEFT JOIN Party supplier ON h.SupplierID = supplier.PartyID
            LEFT JOIN Warehouse w ON h.WarehouseID = w.WarehouseID
            LEFT JOIN Location l ON h.ReceivingDockID = l.LocationID
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

                    rs.getInt("CarrierID"),
                    rs.getString("CarrierName"),
                    rs.getInt("SupplierID"),
                    rs.getString("SupplierName"),

                    rs.getInt("WarehouseID"),
                    rs.getString("WarehouseName"),

                    rs.getInt("ReceivingDockID"),
                    rs.getString("ReceivingDockName"),

                    rs.getInt("CreatedBy"),
                    rs.getTimestamp("CreatedAt"),
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
        Integer mockupCreatedBy = 1;

        String sql = """
            INSERT INTO ImportReceiptHeader (
                ReceiptNumber, ASNNumber, PONumber, OriginLocationID,
                ExpectedArrivalDate, ActualArrivalDate,
                ReceiptStatus, Notes,
                CarrierID, SupplierID, WarehouseID,
                ReceivingDockID,
                CreatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
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
            mockupCreatedBy
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Log creation activity
        importActivityLogService.log(
            id,
            mockupCreatedBy,
            ImportActivityTargetType.RECEIPT.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo phiếu nhập: " + request.receiptNumber()
        );
        String message = "Import Receipt created successfully.";
        ImportReceiptHeaderCreateResponse response = new ImportReceiptHeaderCreateResponse(id, message);
        return response;
    }
}
