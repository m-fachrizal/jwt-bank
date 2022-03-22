package com.fachrizal.bank.service.impl;

import com.fachrizal.bank.dto.request.TransactionHistoryRequest;
import com.fachrizal.bank.dto.response.DataResponse;
import com.fachrizal.bank.dto.response.TransactionHistoryResponse;
import com.fachrizal.bank.dto.response.TransactionsResponse;
import com.fachrizal.bank.model.TransactionDetails;
import com.fachrizal.bank.repository.TransactionDetailsRepository;
import com.fachrizal.bank.service.TransactionHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public ResponseEntity<?> getTransactionHistory(TransactionHistoryRequest transactionHistoryRequest) {
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

            log.info("response list: " + transactionHistoryResponses);
            log.info("successfully get transaction history");

            return ResponseEntity.ok(
                    DataResponse
                            .builder()
                            .responseCode("00")
                            .responseDesc("Success")
                            .data(TransactionsResponse.builder()
                                    .transactions(transactionHistoryResponses)
                                    .build())
                            .build());
        }
    }
}
