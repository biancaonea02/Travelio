package com.example.demo.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; //5 days in milliseconds
    public static final String TOKEN_PREFIX = "Bearer "; //whoever has this token, I can make sure it's verified
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "Travelio";
    public static final String GET_ARRAYS_ADMINISTRATION = "Admins portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log-in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/user/login", "/user/register", "/user/resetPassword/**", "/user",
            "/message/addMessage", "/message", "/hotel/addHotel", "/hotel", "/hotel/{id}", "/room", "/room/addRoom",
            "/room/getHotelRooms", "/room/getAvailableHotelRooms", "/reservation", "/hotel/numberOfHotelsOfCity",
            "/user/{id}", "/ws/**", "/room/getNrAvailableHotelRooms"};

}
