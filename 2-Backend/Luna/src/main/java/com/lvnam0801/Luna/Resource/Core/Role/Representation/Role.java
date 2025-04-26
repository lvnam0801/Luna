package com.lvnam0801.Luna.Resource.Core.Role.Representation;

public record Role(
    Integer roleID,
    String roleName,
    String description,
    String status
) {}