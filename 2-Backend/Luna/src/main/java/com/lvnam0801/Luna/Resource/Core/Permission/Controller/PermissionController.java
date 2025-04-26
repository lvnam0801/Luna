package com.lvnam0801.Luna.Resource.Core.Permission.Controller;

import com.lvnam0801.Luna.Resource.Core.Permission.Representation.Permission;
import com.lvnam0801.Luna.Resource.Core.Permission.Representation.PermissionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public Permission[] getPermissions() {
        String sql = "SELECT * FROM Permission";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Permission(
                rs.getInt("PermissionID"),
                rs.getString("PermissionName"),
                rs.getString("Description"),
                rs.getString("Status")
            )
        ).toArray(Permission[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<Permission> createPermission(@RequestBody PermissionRequest request) {
        String sql = """
            INSERT INTO Permission (PermissionName, Description, Status)
            VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(sql, request.permissionName(), request.description(), request.status());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        Permission permission = new Permission(id, request.permissionName(), request.description(), request.status());
        return ResponseEntity.status(HttpStatus.CREATED).body(permission);
    }
}