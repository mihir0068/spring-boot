package com.mihir.SpringSecurity.Model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Login {

    @Id
    @GeneratedValue
    private int id;

    private int userId;
    private long mobileNo;
    
    @Value("${otp:0}")
    private int otp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date otpExpiry;
}
