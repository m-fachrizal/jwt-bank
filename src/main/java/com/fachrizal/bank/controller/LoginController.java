package com.fachrizal.bank.controller;

import com.fachrizal.bank.configuration.JwtTokenUtil;
import com.fachrizal.bank.dto.request.LoginRequest;
import com.fachrizal.bank.dto.response.DataResponse;
import com.fachrizal.bank.dto.response.LoginResponse;
import com.fachrizal.bank.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/*
Expose a POST API "/login" using the LoginController.
The POST API gets userId and password in the body- Using Spring Authentication Manager
we authenticate the userId and password.If the credentials are valid,
a JWT token is created using the JWTTokenUtil and provided to the client.
*/

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUserId(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUserId());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(DataResponse
                .builder()
                .responseCode("00")
                .responseDesc("Success")
                .data(LoginResponse.builder().accessToken(token).build())
                .build());
    }

    private void authenticate(String userId, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
