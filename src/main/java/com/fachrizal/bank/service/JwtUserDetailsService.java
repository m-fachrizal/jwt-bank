package com.fachrizal.bank.service;

import com.fachrizal.bank.model.UserDetails;
import com.fachrizal.bank.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/*
JWTUserDetailsService implements the Spring Security UserDetailsService interface.
It overrides the loadUserByUsername for fetching user details from the database using the username.
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    //loadUserByUsername method can be found at UserDetailsService and DaoAuthenticationProvider
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));

        return new org.springframework.security.core.userdetails.User(userDetails.getUserId(), userDetails.getPassword(),
                new ArrayList<>());
    }

}
