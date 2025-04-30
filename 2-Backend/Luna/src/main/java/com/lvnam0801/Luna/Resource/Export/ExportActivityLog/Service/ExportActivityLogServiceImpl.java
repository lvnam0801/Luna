package com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLog;
import com.lvnam0801.Luna.Resource.Export.ExportActivityLog.Representation.ExportActivityLogRequest;

@Service
public class ExportActivityLogServiceImpl implements ExportActivityLogService {

    private final JdbcTemplate jdbcTemplate;

    public ExportActivityLogServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void log(ExportActivityLogRequest request) {
        String sql = """
            INSERT INTO ExportActivityLog
            (OrderID, UserID, TargetType, ActionType, TargetID, Content)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
            sql,
            request.orderID(),
            request.userID(),
            request.targetType(),
            request.actionType(),
            request.targetID(),
            request.content()
        );
    }

    @Override
    public List<ExportActivityLog> getLogsByOrder(int orderID) {
        String sql = """
            SELECT
                log.ActivityLogID,
                log.OrderID,
                log.UserID,
                CONCAT(u.FirstName, ' ', u.LastName) AS UserDisplayName,
                log.TargetType,
                log.ActionType,
                log.TargetID,
                log.LoggedTime,
                log.Content,
                log.Status
            FROM ExportActivityLog log
            JOIN User u ON log.UserID = u.UserID
            WHERE log.OrderID = ? AND log.Status = 'active'
            ORDER BY log.LoggedTime ASC
        """;

        return jdbcTemplate.query(
            sql,
            new Object[]{orderID},
            (rs, rowNum) -> new ExportActivityLog(
                rs.getInt("ActivityLogID"),
                rs.getInt("OrderID"),
                rs.getInt("UserID"),
                rs.getString("UserDisplayName"),
                rs.getString("TargetType"),
                rs.getString("ActionType"),
                rs.getInt("TargetID"),
                rs.getTimestamp("LoggedTime"),
                rs.getString("Content"),
                rs.getString("Status")
            )
        );
    }
}