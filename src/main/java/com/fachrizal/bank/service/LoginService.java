package com.fachrizal.bank.service;

import com.fachrizal.bank.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<?> createAuthenticationToken(LoginRequest authenticationRequest);
}
