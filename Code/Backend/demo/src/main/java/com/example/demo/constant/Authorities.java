package com.example.demo.constant;

public class Authorities {
    public static final String[] USER_AUTHORITIES = {"user:read"};
    public static final String[] ADMIN_AUTHORITIES = {"user:read", "user:update", "user:create", "user:delete"};
    public static final String[] HOTEL_OWNER_AUTHORITIES = {"user:read", "user:update"};
}
