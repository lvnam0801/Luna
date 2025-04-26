package com.lvnam0801.Luna.Resource.Core.Role.Controller;

import com.lvnam0801.Luna.Resource.Core.Role.Representation.Role;
import com.lvnam0801.Luna.Resource.Core.Role.Representation.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public Role[] getRoles() {
        String sql = "SELECT * FROM Role";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Role(
                rs.getInt("RoleID"),
                rs.getString("RoleName"),
                rs.getString("Description"),
                rs.getString("Status")
            )
        ).toArray(Role[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody RoleRequest request) {
        String sql = """
            INSERT INTO Role (RoleName, Description, Status)
            VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(sql, request.roleName(), request.description(), request.status());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        Role role = new Role(id, request.roleName(), request.description(), request.status());
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }
}