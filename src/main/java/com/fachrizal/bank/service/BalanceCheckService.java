package com.fachrizal.bank.service;

import com.fachrizal.bank.dto.request.BalanceCheckRequest;
import org.springframework.http.ResponseEntity;

public interface BalanceCheckService {

    ResponseEntity<?> getAccountBalance(BalanceCheckRequest balanceCheckRequest);
}
