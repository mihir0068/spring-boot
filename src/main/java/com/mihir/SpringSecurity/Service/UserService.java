package com.mihir.SpringSecurity.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import com.mihir.SpringSecurity.Model.ApiResponse;
import com.mihir.SpringSecurity.Model.Users;
import com.mihir.SpringSecurity.Repository.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public ResponseEntity<ApiResponse<Users>> register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        user.setRefreshToken(refreshToken);

        Users response = repo.save(user);
        ApiResponse<Users> apiResponse = new ApiResponse<>("Registration Succeessfully Done.", response,
                accessToken,
                refreshToken,
                HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<Users>> verify(Users user) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            String accessToken = jwtService.generateAccessToken(user.getUsername());
            String refreshToken = jwtService.generateRefreshToken(user.getUsername());

            Users existingUser = repo.findByUsername(user.getUsername());
            existingUser.setRefreshToken(refreshToken);
            repo.save(existingUser);

            ApiResponse<Users> apiResponse = new ApiResponse<>("Logged in successfully",
                    user,
                    accessToken,
                    refreshToken,
                    HttpStatus.OK.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    public ResponseEntity<ApiResponse<Users>> refreshToken(String refreshToken) {
        if (jwtService.validateRefreshToken(refreshToken)) {
            final String username = jwtService.extractusername(refreshToken);
            System.out.println(jwtService.isTokenExpired(refreshToken));
            if (jwtService.isTokenExpired(refreshToken)) {
                ApiResponse<Users> apiResponse = new ApiResponse<>("Refresh token expired. Please log in again.", null,
                        null, null,
                        HttpStatus.OK.value());
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }

            String newAccessToken = jwtService.generateAccessToken(username);
            String newRefreshToken = jwtService.generateRefreshToken(username);

            Users user = repo.findByUsername(username);
            if (user == null) {
                ApiResponse<Users> apiResponse = new ApiResponse<>("User not found", null, null, null,
                        HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }

            user.setRefreshToken(newRefreshToken);
            repo.save(user);

            ApiResponse<Users> apiResponse = new ApiResponse<>("Tokens refreshed successfully", user, newAccessToken,
                    newRefreshToken,
                    HttpStatus.OK.value());

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

        return null;
    }
}