package com.example.demo.enumeration;


import static com.example.demo.constant.Authorities.*;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_HOTEL_OWNER(HOTEL_OWNER_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities)
    {
        this.authorities = authorities;
    }

    public String[] getAuthorities()
    {
        return authorities;
    }
}
