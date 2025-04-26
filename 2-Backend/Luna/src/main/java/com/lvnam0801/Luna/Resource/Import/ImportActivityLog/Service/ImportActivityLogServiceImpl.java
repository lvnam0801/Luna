package com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Import.ImportActivityLog.Representation.ImportActivityLog;

@Service
public class ImportActivityLogServiceImpl implements ImportActivityLogService {

    private final JdbcTemplate jdbcTemplate;
    public ImportActivityLogServiceImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void log(Integer receiptID, Integer userID, String targetType, String actionType, int targetID, String content) {
        String sql = """
            INSERT INTO ImportActivityLog 
            (ReceiptID, UserID, TargetType, ActionType, TargetID, Content)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql, receiptID, userID, targetType, actionType, targetID, content);
    }

    @Override
    public List<ImportActivityLog> getLogsByReceipt(int receiptID) {
        String sql = """
            SELECT 
                log.ActivityLogID,
                log.ReceiptID,
                log.UserID,
                CONCAT(u.FirstName, ' ', u.LastName) AS UserDisplayName,
                log.TargetType,
                log.ActionType,
                log.TargetID,
                log.LoggedTime,
                log.Content,
                log.Status
            FROM ImportActivityLog log
            JOIN User u ON log.UserID = u.UserID
            WHERE log.ReceiptID = ? AND log.Status = 'active'
            ORDER BY log.LoggedTime ASC
        """;

        return jdbcTemplate.query(
            sql,
            new Object[]{receiptID},
            (rs, rowNum) -> new ImportActivityLog(
                rs.getInt("ActivityLogID"),
                rs.getInt("ReceiptID"),
                rs.getInt("UserID"),
                rs.getString("UserDisplayName"),  // 👈 from CONCAT
                rs.getString("TargetType"),
                rs.getString("ActionType"),
                rs.getInt("TargetID"),
                rs.getTimestamp("LoggedTime").toLocalDateTime(),
                rs.getString("Content"),
                rs.getString("Status")
            )
        );
    }
}