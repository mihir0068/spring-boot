package com.mihir.SpringSecurity.Model;

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
    private String RefreshToken;
    
}
