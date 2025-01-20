package com.mihir.SpringSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mihir.SpringSecurity.Model.ApiResponse;
import com.mihir.SpringSecurity.Model.Login;
import com.mihir.SpringSecurity.Model.Users;
import com.mihir.SpringSecurity.Service.LoginService;
import com.mihir.SpringSecurity.Service.SessionService;
import com.mihir.SpringSecurity.Service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> register(@RequestBody Users user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Users>> login(@RequestBody Users user) {
        return service.login(user);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<Users>> refreshToekn(@RequestBody Users user) {
        return service.refreshToken(user.getRefreshToken());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(@RequestBody Users user) {
        return service.logout(user.getRefreshToken());
    }

    @GetMapping("/sessions/{userId}")
    public ResponseEntity<ApiResponse<Integer>> getActiveSessions(@PathVariable int userId) {
        return sessionService.getActiveSessions(userId);
    }

    @PostMapping("/deactivate-session")
    public ResponseEntity<ApiResponse<String>> deactivateSessions(@RequestBody Users user) {
        return sessionService.deactivateSessions(user);
    }

    // extra feature login with mobile no and otp
    @PostMapping("/login-mobileNo")
    public ResponseEntity<ApiResponse<Integer>> generateOtp(@RequestBody Users user) {
        return loginService.login(user);
    }

    @PostMapping("/login-verify")
    public ResponseEntity<ApiResponse<String>> verifyOtp(@RequestBody Login login) {
        return loginService.verifyOtp(login);
    }

}
