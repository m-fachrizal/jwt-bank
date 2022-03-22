package com.fachrizal.bank.controller;

import com.fachrizal.bank.dto.request.TransactionHistoryRequest;
import com.fachrizal.bank.service.TransactionHistoryService;
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
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @RequestMapping(value = "/transaction-history", method = RequestMethod.POST)
    public ResponseEntity<?> getTransactionHistory(
            @Valid @RequestBody TransactionHistoryRequest transactionHistoryRequest) {
        log.info("Post Request for /transaction-history");
        return transactionHistoryService.getTransactionHistory(transactionHistoryRequest);
    }
}


