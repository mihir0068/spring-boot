package com.mihir.SpringSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mihir.SpringSecurity.Model.ApiResponse;
import com.mihir.SpringSecurity.Model.Users;
import com.mihir.SpringSecurity.Repository.SessionRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SessionService {

    @Autowired
    public SessionRepo sessionRepo;

    @Autowired
    public JwtService jwtService;

    public ResponseEntity<ApiResponse<Integer>> getActiveSessions(int id) {
        int sessionCount = sessionRepo.countByUserId(id);

        ApiResponse<Integer> response = new ApiResponse<>(
                "Active sessions retrieved successfully",
                sessionCount,
                null,
                null,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<String>> deactivateSessions(@RequestBody Users user) {
        // System.out.println(user.getRefreshToken());
        if (user.getRefreshToken() == null || jwtService.isTokenExpired(user.getRefreshToken())) {
            ApiResponse<String> response = new ApiResponse<>(
                    "Invalid or expired refresh token",
                    null,
                    null,
                    null,
                    HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        int deletedRows = sessionRepo.deleteByRefreshToken(user.getRefreshToken());
        if (deletedRows == 0) {
            ApiResponse<String> response = new ApiResponse<>(
                    "No active session found for the provided refresh token",
                    null,
                    null,
                    null,
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        ApiResponse<String> response = new ApiResponse<>(
                "All past sessions deactivated successfully",
                null,
                null,
                null,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
