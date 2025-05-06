package com.lvnam0801.Luna.Resource.Core.User.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Repository.UserDAO;

@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    
    public UserServiceImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public UserDAO findByUserName(String userName)
    {
        String sql = "SELECT * FROM User WHERE UserName = ?";
        UserDAO user = jdbcTemplate.queryForObject(sql, new Object[]{userName}, (rs, row) ->
            new UserDAO(
                rs.getInt("UserID"), 
                rs.getString("UserName"),
                rs.getString("Password"),
                rs.getString("FirstName"), 
                rs.getString("LastName"), 
                rs.getString("PhotoURL"), 
                rs.getString("Email"), 
                rs.getString("Phone"), 
                rs.getString("Status"), 
                rs.getInt("RoleID"))
        );
        return user;
    }


    @Override
    public String getFullName(int userId) {
        String sql = "SELECT CONCAT(FirstName, ' ', LastName) FROM User WHERE UserID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userId);
    }
}