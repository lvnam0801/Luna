package com.lvnam0801.Luna.Resource.Core.Permission.Representation;

public record PermissionRequest(
    String permissionName,
    String description,
    String status
) {}