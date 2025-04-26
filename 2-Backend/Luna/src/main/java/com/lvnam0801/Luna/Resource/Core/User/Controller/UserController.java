package com.lvnam0801.Luna.Resource.Core.User.Controller;

import com.lvnam0801.Luna.Resource.Core.User.Representation.User;
import com.lvnam0801.Luna.Resource.Core.User.Representation.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-all")
    public User[] getUsers() {
        String sql = "SELECT * FROM User";

        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new User(
                rs.getInt("UserID"),
                rs.getString("UserName"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("PhotoURL"),
                rs.getString("Email"),
                rs.getString("Phone"),
                rs.getString("Status"),
                rs.getObject("RoleID", Integer.class)
            )
        ).toArray(User[]::new);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequest request) {
        String sql = """
            INSERT INTO User (UserName, Password, FirstName, LastName, PhotoURL, Email, Phone, Status, RoleID)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            request.userName(),
            request.password(),
            request.firstName(),
            request.lastName(),
            request.photoURL(),
            request.email(),
            request.phone(),
            request.status(),
            request.roleID()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        User user = new User(
            id,
            request.userName(),
            request.firstName(),
            request.lastName(),
            request.photoURL(),
            request.email(),
            request.phone(),
            request.status(),
            request.roleID()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}