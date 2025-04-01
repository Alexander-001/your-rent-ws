package com.yourrent.your_rent_ws.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginPhone {
    @NotBlank(message = "Numero de telefono no puede estar vacio.")
    private String phoneNumber;
}
