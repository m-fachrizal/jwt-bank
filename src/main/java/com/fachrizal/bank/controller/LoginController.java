package com.fachrizal.bank.controller;

import com.fachrizal.bank.dto.request.LoginRequest;
import com.fachrizal.bank.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
Expose a POST API "/login" using the LoginController.
The POST API gets userId and password in the body- Using Spring Authentication Manager
we authenticate the userId and password.If the credentials are valid,
a JWT token is created using the JWTTokenUtil and provided to the client.
*/

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @Valid @RequestBody LoginRequest authenticationRequest) {
        log.info("Post Request for /login");
        return loginService.createAuthenticationToken(authenticationRequest);
    }

}
