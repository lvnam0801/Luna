package com.lvnam0801.Luna.Auth.Service;

import com.lvnam0801.Luna.Auth.Representation.LoginRequest;
import com.lvnam0801.Luna.Auth.Representation.LoginResponse;
import com.lvnam0801.Luna.Auth.Utils.JwtUtils;
import com.lvnam0801.Luna.Resource.Core.User.Repository.UserDAO;
import com.lvnam0801.Luna.Resource.Core.User.Service.UserService;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        UserDAO user = userService.findByUserName(request.getUsername());

        if (!user.password().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtils.generateToken(user.userID(), user.userName());

        return new LoginResponse(user.userID(), user.userName(), token);
    }
}