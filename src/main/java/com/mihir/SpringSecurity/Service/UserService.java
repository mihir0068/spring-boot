package com.mihir.SpringSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mihir.SpringSecurity.Model.Users;
import com.mihir.SpringSecurity.Repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public Users register(Users user) {

        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

}
