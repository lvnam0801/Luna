package com.lvnam0801.Luna.Auth.Representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Integer userId;
    private String username;
    private String token;
}