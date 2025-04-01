package com.yourrent.your_rent_ws.services;

public interface OtpService {

    void generateAndSendOtp(String phoneNumber);

    boolean validateOtp(String phoneNumber, String otp);

    void removeOtp(String phoneNumber);

}
