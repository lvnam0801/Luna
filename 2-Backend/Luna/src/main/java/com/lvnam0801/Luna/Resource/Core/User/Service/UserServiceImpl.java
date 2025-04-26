package com.lvnam0801.Luna.Resource.Core.User.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    
    public UserServiceImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getFullName(int userId) {
        String sql = "SELECT CONCAT(FirstName, ' ', LastName) FROM User WHERE UserID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userId);
    }
}