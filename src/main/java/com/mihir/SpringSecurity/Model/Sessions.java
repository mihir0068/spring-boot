package com.mihir.SpringSecurity.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sessions {

    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private String refreshToken;
}
