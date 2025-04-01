package com.yourrent.your_rent_ws.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.yourrent.your_rent_ws.services.OtpService;

@Service
public class OtpServiceImpl implements OtpService {

    private final Map<String, String> otpStorage = new HashMap<>();

    public void generateAndSendOtp(String phoneNumber) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStorage.put(phoneNumber, otp);
        System.out.println("OTP para " + phoneNumber + ": " + otp);
    }

    public boolean validateOtp(String phoneNumber, String otp) {
        return otp.equals(otpStorage.get(phoneNumber));
    }

    public void removeOtp(String phoneNumber) {
        otpStorage.remove(phoneNumber);
    }
}
