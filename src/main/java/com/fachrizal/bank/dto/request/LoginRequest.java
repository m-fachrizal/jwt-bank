package com.fachrizal.bank.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRequest {
    private String userId;
    private String password;
}
