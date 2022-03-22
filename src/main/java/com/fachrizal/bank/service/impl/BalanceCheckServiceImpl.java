package com.fachrizal.bank.service.impl;

import com.fachrizal.bank.dto.request.BalanceCheckRequest;
import com.fachrizal.bank.dto.response.BalanceCheckResponse;
import com.fachrizal.bank.dto.response.DataResponse;
import com.fachrizal.bank.model.AccountDetails;
import com.fachrizal.bank.repository.AccountDetailsRepository;
import com.fachrizal.bank.service.BalanceCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class BalanceCheckServiceImpl implements BalanceCheckService {

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Override
    public ResponseEntity<?> getAccountBalance(BalanceCheckRequest balanceCheckRequest){
        AccountDetails accountDetails = accountDetailsRepository
                .findById(Integer.valueOf(balanceCheckRequest.getAccountNo())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        log.info("successfully get account balance");
        return ResponseEntity.ok(DataResponse
                .builder()
                .responseCode("00")
                .responseDesc("Success")
                .data(BalanceCheckResponse.builder()
                        .accountBalance(String.format("%.2f", accountDetails.getAvailableBalance()))
                        .build())
                .build());
    }
}
