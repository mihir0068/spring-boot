package com.mihir.SpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mihir.SpringSecurity.Model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

    void save(String refreshToken);

}
