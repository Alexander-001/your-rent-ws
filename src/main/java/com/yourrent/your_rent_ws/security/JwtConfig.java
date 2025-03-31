package com.yourrent.your_rent_ws.security;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

//* Global JWT variables
public class JwtConfig {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json; charset=UTF-8";
}