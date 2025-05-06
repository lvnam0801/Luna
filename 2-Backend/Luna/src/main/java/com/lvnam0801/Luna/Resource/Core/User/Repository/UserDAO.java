package com.lvnam0801.Luna.Resource.Core.User.Repository;


public record UserDAO(
    Integer userID,
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