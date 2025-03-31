package com.yourrent.your_rent_ws.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthoritiesJsonCreator {
    @JsonCreator
    public SimpleGrantedAuthoritiesJsonCreator(@JsonProperty("authority") String role) {
    }
}
