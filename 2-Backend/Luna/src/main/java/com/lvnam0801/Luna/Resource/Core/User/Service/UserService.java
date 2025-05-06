package com.lvnam0801.Luna.Resource.Core.User.Service;

import com.lvnam0801.Luna.Resource.Core.User.Repository.UserDAO;

public interface UserService {
    public UserDAO findByUserName(String userName);
    public String getFullName(int userId);
}