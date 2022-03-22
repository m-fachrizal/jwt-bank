package com.fachrizal.bank.controller;

import com.fachrizal.bank.dto.request.BalanceCheckRequest;
import com.fachrizal.bank.service.BalanceCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class BalanceCheckController {

    @Autowired
    private BalanceCheckService balanceCheckService;

    @RequestMapping(value = "/balance-check", method = RequestMethod.POST)
    public ResponseEntity<?> getAccountBalance(
            @Valid @RequestBody BalanceCheckRequest balanceCheckRequest) {
        log.info("Post Request for /balance-check");
        return balanceCheckService.getAccountBalance(balanceCheckRequest);
    }
}
