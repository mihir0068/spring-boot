package com.mihir.SpringSecurity.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private String msg;
    private T data;
    private String accessToken;
    private String refreshToken;
    private int status;
}
