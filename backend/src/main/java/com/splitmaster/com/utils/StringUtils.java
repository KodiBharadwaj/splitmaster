package com.splitmaster.com.utils;

import com.splitmaster.com.utils.enums.UserRoles;

import java.util.Map;

public class StringUtils {

    private StringUtils() {}

    public static final Map<Integer, String> userRoleAssociation = Map.of(1, UserRoles.ADMIN.name(), 2, UserRoles.USER.name());
}
