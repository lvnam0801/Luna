package com.lvnam0801.Luna.Resource.Export.DirectTransition.Controller;

import com.lvnam0801.Luna.Resource.Export.DirectTransition.Representation.DirectTransition;
import com.lvnam0801.Luna.Resource.Export.DirectTransition.Representation.DirectTransitionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/direct-transition")
public class DirectTransitionController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public DirectTransition[] getAll() {
        String sql = "SELECT * FROM DirectTransition";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new DirectTransition(
                rs.getInt("DirectTransitionID"),
                rs.getInt("ExportOrderID"),
                rs.getInt("ImportReceiptID"),
                rs.getDate("TransitionDate"),
                rs.getString("Notes"),
                rs.getString("Status")
            )
        ).toArray(DirectTransition[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<DirectTransition> create(@RequestBody DirectTransitionRequest request) {
        String sql = """
            INSERT INTO DirectTransition (ExportOrderID, ImportReceiptID, TransitionDate, Notes, Status)
            VALUES (?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.exportOrderID(),
            request.importReceiptID(),
            request.transitionDate(),
            request.notes(),
            request.status()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        DirectTransition transition = new DirectTransition(
            id,
            request.exportOrderID(),
            request.importReceiptID(),
            request.transitionDate(),
            request.notes(),
            request.status()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(transition);
    }
}