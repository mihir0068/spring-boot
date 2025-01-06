package com.mihir.simpleWebApp.model;

import java.util.List;

// import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ApiResponse<T> {

    private String msg;
    private T data;
    private int status;
}
