package com.lvnam0801.Luna.Auth.Service;

import com.lvnam0801.Luna.Auth.Representation.LoginRequest;
import com.lvnam0801.Luna.Auth.Representation.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}