package com.mihir.SpringSecurity.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mihir.SpringSecurity.Model.ApiResponse;
import com.mihir.SpringSecurity.Model.Users;
import com.mihir.SpringSecurity.Service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> register(@RequestBody Users user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Users>> login(@RequestBody Users user) {
        return service.verify(user);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<Users>> refreshToekn(@RequestBody Users user) {
        return service.refreshToken(user.getRefreshToken());
    }
}
