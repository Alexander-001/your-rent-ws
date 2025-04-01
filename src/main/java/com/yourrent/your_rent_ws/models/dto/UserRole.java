package com.yourrent.your_rent_ws.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRole {
    @NotBlank(message = "Email no puede estar vacio.")
    private String email;

    public UserRole() {
    }
}
