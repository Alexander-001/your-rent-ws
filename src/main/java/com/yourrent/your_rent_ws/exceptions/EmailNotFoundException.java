package com.yourrent.your_rent_ws.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {

    public EmailNotFoundException(String message) {
        super(message);
    }

    public EmailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
