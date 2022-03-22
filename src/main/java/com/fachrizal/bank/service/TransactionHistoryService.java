package com.fachrizal.bank.service;

import com.fachrizal.bank.dto.request.TransactionHistoryRequest;
import org.springframework.http.ResponseEntity;

public interface TransactionHistoryService {

    ResponseEntity<?> getTransactionHistory(TransactionHistoryRequest transactionHistoryRequest);
}
