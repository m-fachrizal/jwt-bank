package com.fachrizal.bank.controller;

import com.fachrizal.bank.dto.request.FundTransferRequest;
import com.fachrizal.bank.dto.request.TransactionHistoryRequest;
import com.fachrizal.bank.dto.response.DataResponse;
import com.fachrizal.bank.dto.response.FundTransferResponse;
import com.fachrizal.bank.dto.response.TransactionHistoryResponse;
import com.fachrizal.bank.dto.response.TransactionsResponse;
import com.fachrizal.bank.model.AccountDetails;
import com.fachrizal.bank.model.TransactionDetails;
import com.fachrizal.bank.repository.TransactionDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
public class TransactionHistoryController {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @RequestMapping(value = "/transaction-history", method = RequestMethod.POST)
    public ResponseEntity<?> transactionHistory(
            @RequestBody TransactionHistoryRequest transactionHistoryRequest) {

        List<TransactionDetails> transactionDetailsList = transactionDetailsRepository
                .findByAccountNo(Integer.valueOf(transactionHistoryRequest.getAccountNo()));

        if(transactionDetailsList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            List<TransactionHistoryResponse> transactionHistoryResponses = new ArrayList<>();
            transactionDetailsList.forEach(
                    data -> transactionHistoryResponses.add(TransactionHistoryResponse
                            .builder()
                            .amount(String.format("%.2f", data.getTransactionAmount()))
                            .referenceNumber(data.getReferenceNumber().toString())
                            .transactionFlag(data.getTransactionFlag())
                            .build())
            );
            log.info("response list " + transactionHistoryResponses);

            return ResponseEntity.ok(
                    DataResponse
                            .builder()
                            .responseCode("00")
                            .responseDesc("Success")
                            .data(TransactionsResponse.builder()
                                    .transactions(transactionHistoryResponses)
                                    .build())
                            .build()
            );
        }

    }
}


