package com.mihir.SpringSecurity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihir.SpringSecurity.Model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByrefreshToken(String refreshToken);

    Optional<Users> findByMobileNo(long mobileNo);

}
