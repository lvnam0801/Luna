package com.lvnam0801.Luna.Resource.InventoryCheck.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheck;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckDetail;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckDetailRequest;
import com.lvnam0801.Luna.Resource.InventoryCheck.Representation.InventoryCheckRequest;

@Service
public class InventoryCheckServiceImpl implements InventoryCheckService {

    private final JdbcTemplate jdbc;
    public InventoryCheckServiceImpl(JdbcTemplate jdbcTemplate){
        this.jdbc = jdbcTemplate;
    }

    @Override
    public InventoryCheck createInventoryCheck(InventoryCheckRequest request) {
        // 1. Insert into InventoryChecks
        String insertHeader = "INSERT INTO InventoryChecks (WarehouseID, CheckedBy, CreatedBy) VALUES (?, ?, ?)";
        jdbc.update(insertHeader, request.getWarehouseID(), request.getCheckedBy(), request.getCheckedBy()); // Assuming createdBy = checkedBy

        // 2. Get generated InventoryCheckID
        Integer checkID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // 3. Insert details
        for (InventoryCheckDetailRequest detail : request.getDetails()) {
            String insertDetail = """
                INSERT INTO InventoryCheckDetails (InventoryCheckID, SKUItemID, SystemQuantity, ActualQuantity, QuantityDifferenceReason, Note, Status)
                VALUES (?, ?, ?, ?, ?, ?, 'active')
            """;
            jdbc.update(insertDetail,
                checkID,
                detail.getSkuItemID(),
                detail.getSystemQuantity(),
                detail.getActualQuantity(),
                detail.getQuantityDifferenceReason(),
                detail.getNote()
            );
        }

        return getById(checkID);
    }

    @Override
    public List<InventoryCheck> getAllChecks() {
        // Optional: implement a lightweight list query
        return new ArrayList<>();
    }

    @Override
    public InventoryCheck getById(int id) {
        // 1. Get header
        String sqlHeader = "SELECT * FROM InventoryChecks WHERE InventoryCheckID = ?";
        InventoryCheck check = jdbc.queryForObject(sqlHeader, new Object[]{id}, (rs, rowNum) -> {
            InventoryCheck c = new InventoryCheck();
            c.setInventoryCheckID(rs.getInt("InventoryCheckID"));
            c.setWarehouseID(rs.getInt("WarehouseID"));
            c.setCheckedBy(rs.getInt("CheckedBy"));
            c.setCheckedDate(rs.getTimestamp("CheckedDate").toLocalDateTime());
            c.setCreatedBy(rs.getInt("CreatedBy"));
            c.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
            c.setUpdatedBy(rs.getInt("UpdatedBy"));
            c.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
            return c;
        });

        // 2. Get details
        String sqlDetails = "SELECT * FROM InventoryCheckDetails WHERE InventoryCheckID = ?";
        List<InventoryCheckDetail> details = jdbc.query(sqlDetails, new Object[]{id}, (rs, rowNum) -> {
            InventoryCheckDetail d = new InventoryCheckDetail();
            d.setInventoryCheckDetailsID(rs.getInt("InventoryCheckDetailsID"));
            d.setInventoryCheckID(rs.getInt("InventoryCheckID"));
            d.setSkuItemID(rs.getInt("SKUItemID"));
            d.setSystemQuantity(rs.getInt("SystemQuantity"));
            d.setActualQuantity(rs.getInt("ActualQuantity"));
            d.setQuantityDifferenceReason(rs.getString("QuantityDifferenceReason"));
            d.setNote(rs.getString("Note"));
            d.setStatus(rs.getString("Status"));
            return d;
        });

        check.setDetails(details);
        return check;
    }
}