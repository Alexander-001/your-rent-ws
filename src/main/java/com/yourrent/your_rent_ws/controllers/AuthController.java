package com.yourrent.your_rent_ws.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourrent.your_rent_ws.models.User;
import com.yourrent.your_rent_ws.models.dto.LoginPhone;
import com.yourrent.your_rent_ws.models.dto.UserRole;
import com.yourrent.your_rent_ws.services.OtpService;
import com.yourrent.your_rent_ws.services.UserService;
import com.yourrent.your_rent_ws.validations.ValidationBindingResult;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/auth")
public class AuthController {

    private final OtpService otpService;

    @Autowired
    private ValidationBindingResult validationBindingResult;

    @Autowired
    private UserService userService;

    public AuthController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send-code-phone")
    public ResponseEntity<Map<String, Object>> sendOtp(@Valid @RequestBody LoginPhone loginPhone,
            BindingResult result) {
        if (result.hasFieldErrors()) {
            return validationBindingResult.validation(result, "send", false);
        }
        String phoneNumber = loginPhone.getPhoneNumber();
        Map<String, Object> response = new HashMap<>();
        try {
            otpService.generateAndSendOtp(phoneNumber);
            response.put("message", "Código OTP enviado.");
            response.put("send", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo un error al enviar código");
            response.put("send", false);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/validate-email")
    public ResponseEntity<Map<String, Object>> validateEmail(@Valid @RequestBody UserRole userRole,
            BindingResult result) {
        if (result.hasFieldErrors()) {
            return validationBindingResult.validation(result, "existsUser", false);
        }
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> userDb = userService.findByEmail(userRole.getEmail());
            if (userDb.isPresent()) {
                response.put("message", "Usuario encontrado.");
                response.put("existsUser", true);
                response.put("user", userDb.get());
                return ResponseEntity.ok(response);
            }
            response.put("message", "Usuario no existe");
            response.put("user", null);
            response.put("existsUser", false);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo un error al buscar usuario");
            response.put("send", false);
            return ResponseEntity.internalServerError().body(response);
        }

    }
}
