package com.mihir.SpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mihir.SpringSecurity.Model.Login;

public interface LoginRepo extends JpaRepository<Login, Long> {

    Login findByMobileNoAndOtp(long mobileNo, int otp);

}