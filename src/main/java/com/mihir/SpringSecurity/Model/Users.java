package com.mihir.SpringSecurity.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    private int id;
    private String username;
    private String password;

    // @Value("${refresh_token:' '}")
    @Column(name = "refresh_token", nullable = true)
    private String refreshToken;

}
