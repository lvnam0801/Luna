package com.lvnam0801.Luna.Resource.Core.User.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Context.UserContext;
import com.lvnam0801.Luna.Resource.Core.User.Repository.UserDAO;
import com.lvnam0801.Luna.Resource.Core.User.Representation.User;

@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    private final UserContext userContext;
    
    public UserServiceImpl(JdbcTemplate jdbcTemplate, UserContext userContext){
        this.jdbcTemplate = jdbcTemplate;
        this.userContext = userContext;
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
    public User getUserProfile()
    {
        String sql = "SELECT * FROM User WHERE UserId = ?";
        User user = jdbcTemplate.queryForObject(sql, (rs, row) ->
            new User(
                rs.getInt("UserID"), 
                rs.getString("UserName"),
                rs.getString("FirstName"), 
                rs.getString("LastName"), 
                rs.getString("PhotoURL"), 
                rs.getString("Email"), 
                rs.getString("Phone"), 
                rs.getString("Status"), 
                rs.getInt("RoleID")
            ),
            new Object[]{userContext.getCurrentUserID()}
        );
        return user;
    }


    @Override
    public String getFullName(int userId) {
        String sql = "SELECT CONCAT(FirstName, ' ', LastName) FROM User WHERE UserID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userId);
    }
}