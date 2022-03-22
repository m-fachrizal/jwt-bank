package com.fachrizal.bank.controller;

import com.fachrizal.bank.dto.request.FundTransferRequest;
import com.fachrizal.bank.dto.response.DataResponse;
import com.fachrizal.bank.dto.response.FundTransferResponse;
import com.fachrizal.bank.model.AccountDetails;
import com.fachrizal.bank.model.TransactionDetails;
import com.fachrizal.bank.repository.AccountDetailsRepository;
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
public class FundTransferController {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @RequestMapping(value = "/fund-transfer", method = RequestMethod.POST)
    public ResponseEntity<?> fundTransfer(
            @RequestBody FundTransferRequest fundTransferRequest) {

        AccountDetails senderAccount = accountDetailsRepository
                .findById(Integer.valueOf(fundTransferRequest.getFromAccount())).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        AccountDetails recipientAccount= accountDetailsRepository
                .findById(Integer.valueOf(fundTransferRequest.getToAccount())).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        //kurangin saldo pengirim
        Double senderAfterTransfer = senderAccount.getAvailableBalance() - Double.parseDouble(fundTransferRequest.getAmount());
        log.info("saldo pengirim " + senderAfterTransfer);

        //tambah saldo penerima
        Double recipientAfterTransfer = recipientAccount.getAvailableBalance() + Double.parseDouble(fundTransferRequest.getAmount());
        log.info("saldo penerima " + recipientAfterTransfer);

        //updateAccountDetails
        senderAccount.setAvailableBalance(senderAfterTransfer);
        recipientAccount.setAvailableBalance(recipientAfterTransfer);
        accountDetailsRepository.save(senderAccount);
        accountDetailsRepository.save(recipientAccount);

        //random number for referenceNumber
        int min = 1000000000;
        int max = 2147483647;
        int generatedReferenceNumber = min + (int) (new Random().nextFloat() * (max - min) );
        log.info(Integer.toString(generatedReferenceNumber));

        //insertTransactionDetails
        TransactionDetails senderTransactionDetails = TransactionDetails.builder()
                .accountNo(senderAccount.getAccountNo())
                .transactionFlag("C")
                .transactionAmount(Double.parseDouble(fundTransferRequest.getAmount()))
                .referenceNumber(generatedReferenceNumber)
                .build();

        TransactionDetails recipientTransactionDetails = TransactionDetails.builder()
                .accountNo(recipientAccount.getAccountNo())
                .transactionFlag("D")
                .transactionAmount(Double.parseDouble(fundTransferRequest.getAmount()))
                .referenceNumber(generatedReferenceNumber)
                .build();

        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        transactionDetailsList.add(senderTransactionDetails);
        transactionDetailsList.add(recipientTransactionDetails);
        transactionDetailsRepository.saveAll(transactionDetailsList);


        return ResponseEntity.ok(
                DataResponse
                .builder()
                .responseCode("00")
                .responseDesc("Success")
                .data(FundTransferResponse.builder()
                        .accountBalance(String.format("%.2f", senderAccount.getAvailableBalance()))
                        .referenceNumber(Integer.toString(generatedReferenceNumber))
                        .build())
                .build()
        );


    }


}
