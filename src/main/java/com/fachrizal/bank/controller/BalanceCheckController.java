package com.fachrizal.bank.controller;

import com.fachrizal.bank.dto.request.BalanceCheckRequest;
import com.fachrizal.bank.dto.request.LoginRequest;
import com.fachrizal.bank.dto.response.BalanceCheckResponse;
import com.fachrizal.bank.dto.response.DataResponse;
import com.fachrizal.bank.dto.response.LoginResponse;
import com.fachrizal.bank.model.AccountDetails;
import com.fachrizal.bank.repository.AccountDetailsRepository;
import com.fachrizal.bank.service.BalanceCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class BalanceCheckController {

    @Autowired
    private BalanceCheckService balanceCheckService;

    @RequestMapping(value = "/balance-check", method = RequestMethod.POST)
    public ResponseEntity<?> balanceCheck(
            @RequestBody BalanceCheckRequest balanceCheckRequest) {
        log.info("PostMapping for /balance-check");
        return balanceCheckService.getAccountBalance(balanceCheckRequest);
    }
}
