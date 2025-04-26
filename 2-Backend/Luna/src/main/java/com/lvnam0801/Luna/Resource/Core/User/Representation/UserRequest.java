package com.lvnam0801.Luna.Resource.Core.User.Representation;

public record UserRequest(
    String userName,
    String password,
    String firstName,
    String lastName,
    String photoURL,
    String email,
    String phone,
    String status,
    Integer roleID
) {}