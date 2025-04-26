package com.lvnam0801.Luna.Resource.Core.Role.Representation;

public record RoleRequest(
    String roleName,
    String description,
    String status
) {}