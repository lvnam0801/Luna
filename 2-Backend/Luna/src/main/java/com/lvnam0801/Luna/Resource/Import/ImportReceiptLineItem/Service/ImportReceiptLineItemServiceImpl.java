package com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Service.UserService;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityActionType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityTargetType;
import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service.ImportActivityLogService;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItem;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemCreateRequest;
import com.lvnam0801.Luna.Resource.Import.ImportReceiptLineItem.Representation.ImportReceiptLineItemResponse;

@Service
public class ImportReceiptLineItemServiceImpl implements ImportReceiptLineItemService{
    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;
    private final ImportActivityLogService importActivityLogService;

    public ImportReceiptLineItemServiceImpl(JdbcTemplate jdbcTemplate, UserService userService, ImportActivityLogService importActivityLogService)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.importActivityLogService = importActivityLogService;
        this.userService = userService;
    }

    @Override
    public ImportReceiptLineItemResponse[] getByReceipt(Integer receiptID)
    {
        String sql = "SELECT * FROM ImportReceiptLineItem WHERE ReceiptID = ?";
        List<ImportReceiptLineItem> items = jdbcTemplate.query(
            sql,
            new Object[]{receiptID},
            (rs, rowNum) -> new ImportReceiptLineItem(
                rs.getInt("ReceiptLineItemID"),
                rs.getInt("ReceiptID"),
                rs.getInt("ProductID"),
                rs.getInt("LineItemNumber"),
                rs.getInt("ExpectedQuantity"),
                rs.getInt("ReceivedQuantity"),
                rs.getInt("QuantityDiscrepancy"),
                rs.getString("DiscrepancyReasonCode"),
                rs.getString("LotNumber"),
                rs.getString("SerialNumber"),
                rs.getDate("ExpirationDate"),
                rs.getBigDecimal("UnitCost"),
                rs.getString("Status"),
                rs.getInt("CreatedBy"),
                rs.getTimestamp("CreatedDate"),
                rs.getInt("UpdatedBy"),
                rs.getTimestamp("UpdatedDate")
            )
        );

        // Map to response objects with names
        List<ImportReceiptLineItemResponse> responseList = items.stream()
            .map(item -> new ImportReceiptLineItemResponse(
                item.receiptLineItemID(),
                item.receiptID(),
                item.productID(),
                item.lineItemNumber(),
                item.expectedQuantity(),
                item.receivedQuantity(),
                item.quantityDiscrepancy(),
                item.discrepancyReasonCode(),
                item.lotNumber(),
                item.serialNumber(),
                item.expirationDate(),
                item.unitCost(),
                item.status(),
                item.createdBy(),
                userService.getFullName(item.createdBy()),
                item.createdDate(),
                item.updatedBy(),
                userService.getFullName(item.updatedBy()),
                item.updatedDate()
            ))
            .toList();

        return responseList.toArray(ImportReceiptLineItemResponse[]::new);
    }

    @Override 
    public ImportReceiptLineItem createLine(ImportReceiptLineItemCreateRequest request)
    {
        
        String sql = """
            INSERT INTO ImportReceiptLineItem (
                ReceiptID, ProductID, LineItemNumber, ExpectedQuantity, ReceivedQuantity,
                DiscrepancyReasonCode, LotNumber, SerialNumber, ExpirationDate,
                UnitCost, Status, CreatedBy, UpdatedBy
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.receiptID(),
            request.productID(),
            request.lineItemNumber(),
            request.expectedQuantity(),
            request.receivedQuantity(),
            request.discrepancyReasonCode(),
            request.lotNumber(),
            request.serialNumber(),
            request.expirationDate(),
            request.unitCost(),
            request.status(),
            request.createdBy(),
            request.createdBy()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        ImportReceiptLineItem lineItem = new ImportReceiptLineItem(
            id,
            request.receiptID(),
            request.productID(),
            request.lineItemNumber(),
            request.expectedQuantity(),
            request.receivedQuantity(),
            request.receivedQuantity() - request.expectedQuantity(),
            request.discrepancyReasonCode(),
            request.lotNumber(),
            request.serialNumber(),
            request.expirationDate(),
            request.unitCost(),
            request.status(),
            request.createdBy(),
            now,
            request.createdBy(),
            now
        );

        importActivityLogService.log(
            request.receiptID(),
            request.createdBy(),
            ImportActivityTargetType.LINE_ITEM.value(),
            ImportActivityActionType.CREATE.value(),
            id,
            "Tạo dòng sản phẩm: " + request.lineItemNumber()
        );
        return lineItem;
    }
}
