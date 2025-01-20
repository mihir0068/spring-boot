package com.mihir.SpringSecurity.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mihir.SpringSecurity.Model.ApiResponse;
import com.mihir.SpringSecurity.Model.Login;
import com.mihir.SpringSecurity.Model.Sessions;
import com.mihir.SpringSecurity.Model.Users;
import com.mihir.SpringSecurity.Repository.LoginRepo;
import com.mihir.SpringSecurity.Repository.SessionRepo;
import com.mihir.SpringSecurity.Repository.UserRepo;

@Service
public class LoginService {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public LoginRepo loginRepo;

    @Autowired
    public SessionRepo sessionRepo;

    @Autowired
    public JwtService jwtService;

    public ResponseEntity<ApiResponse<Integer>> login(Users data) {

        Optional<Users> optionalUser = userRepo.findByMobileNo(data.getMobileNo());
        System.out.println(optionalUser);
        if (optionalUser == null) {
            ApiResponse<Integer> response = new ApiResponse<>("This number is not registerd.", null, null, null,
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Users user = optionalUser.get();

        int otp = new Random().nextInt(9000) + 1000;
        Login loginRecord = new Login();
        loginRecord.setUserId(user.getId());
        loginRecord.setMobileNo(user.getMobileNo());
        loginRecord.setOtp(otp);
        loginRecord.setOtpExpiry(new Date(System.currentTimeMillis() + 5 * 60 * 1000));

        loginRepo.save(loginRecord);
        ApiResponse<Integer> response = new ApiResponse<>(
                "OTP generated and stored successfully.",
                otp,
                null,
                null,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<String>> verifyOtp(Login data) {

        Login storedUser = loginRepo.findByMobileNoAndOtp(data.getMobileNo(), data.getOtp());
        System.out.println("Fetched user: " + storedUser);

        if (storedUser == null) {
            ApiResponse<String> response = new ApiResponse<>("Invalid OTP or user not found.", null, null, null,
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        if (storedUser.getOtpExpiry() == null || storedUser.getOtpExpiry().before(new Date())) {
            ApiResponse<String> response = new ApiResponse<>("OTP expired. Please request a new login.", null, null,
                    null,
                    HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        storedUser.setOtp(0);
        storedUser.setOtpExpiry(null);
        loginRepo.save(storedUser);

        Optional<Users> optionalUser = userRepo.findById((long) storedUser.getUserId());
        if (optionalUser.isEmpty()) {
            ApiResponse<String> response = new ApiResponse<>(
                    "User not found for the provided OTP.",
                    null,
                    null,
                    null,
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Users user = optionalUser.get();

        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        user.setRefreshToken(refreshToken);
        userRepo.save(user);

        Sessions session = new Sessions();
        session.setUserId(user.getId());
        session.setRefreshToken(refreshToken);
        sessionRepo.save(session);
        
        ApiResponse<String> response = new ApiResponse<>(
                "OTP verified successfully. Tokens generated.",
                null,
                accessToken,
                refreshToken,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
