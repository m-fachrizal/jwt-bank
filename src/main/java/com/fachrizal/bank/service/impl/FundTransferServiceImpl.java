package com.fachrizal.bank.service.impl;

import com.fachrizal.bank.dto.request.FundTransferRequest;
import com.fachrizal.bank.dto.response.DataResponse;
import com.fachrizal.bank.dto.response.FundTransferResponse;
import com.fachrizal.bank.model.AccountDetails;
import com.fachrizal.bank.model.TransactionDetails;
import com.fachrizal.bank.repository.AccountDetailsRepository;
import com.fachrizal.bank.repository.TransactionDetailsRepository;
import com.fachrizal.bank.service.FundTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class FundTransferServiceImpl implements FundTransferService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Override
    public ResponseEntity<?> fundTransfer(FundTransferRequest fundTransferRequest) {

        AccountDetails senderAccount = accountDetailsRepository
                .findById(Integer.valueOf(fundTransferRequest.getFromAccount())).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        AccountDetails recipientAccount = accountDetailsRepository
                .findById(Integer.valueOf(fundTransferRequest.getToAccount())).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        updateAccountDetails(senderAccount, recipientAccount, Double.parseDouble(fundTransferRequest.getAmount()));

        Integer generatedReferenceNumber = generateReferenceNumber();

        createTransactionDetails(senderAccount.getAccountNo(), recipientAccount.getAccountNo(),
                Double.parseDouble(fundTransferRequest.getAmount()), generatedReferenceNumber);

        return ResponseEntity.ok(
                DataResponse
                        .builder()
                        .responseCode("00")
                        .responseDesc("Success")
                        .data(FundTransferResponse.builder()
                                .accountBalance(String.format("%.2f", senderAccount.getAvailableBalance()))
                                .referenceNumber(Integer.toString(generatedReferenceNumber))
                                .build())
                        .build());
    }

    private void updateAccountDetails(
            AccountDetails senderAccount, AccountDetails recipientAccount, Double transferAmount) {

        //sender balance deducted
        Double senderBalanceAfterTransfer = senderAccount.getAvailableBalance() - transferAmount;

        //recipient balance increased
        Double recipientBalanceAfterTransfer = recipientAccount.getAvailableBalance() + transferAmount;

        //updateAccountDetails
        senderAccount.setAvailableBalance(senderBalanceAfterTransfer);
        recipientAccount.setAvailableBalance(recipientBalanceAfterTransfer);
        accountDetailsRepository.save(senderAccount);
        accountDetailsRepository.save(recipientAccount);
        log.info("Account Details updated successfully");
    }

    private void createTransactionDetails(
            Integer senderAccountNo, Integer recipientAccountNo,
            Double transferAmount, Integer generatedReferenceNumber) {

        TransactionDetails senderTransactionDetails = TransactionDetails.builder()
                .accountNo(senderAccountNo)
                .transactionFlag("C")
                .transactionAmount(transferAmount)
                .referenceNumber(generatedReferenceNumber)
                .build();

        TransactionDetails recipientTransactionDetails = TransactionDetails.builder()
                .accountNo(recipientAccountNo)
                .transactionFlag("D")
                .transactionAmount(transferAmount)
                .referenceNumber(generatedReferenceNumber)
                .build();

        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        transactionDetailsList.add(senderTransactionDetails);
        transactionDetailsList.add(recipientTransactionDetails);
        //insertTransactionDetails
        transactionDetailsRepository.saveAll(transactionDetailsList);
        log.info("Transaction Details created successfully");
    }

    private Integer generateReferenceNumber(){
        //generate random number for referenceNumber
        int min = 1000000000;
        int max = 2147483647;
        int generatedReferenceNumber = min + (int) (new Random().nextFloat() * (max - min) );

        return generatedReferenceNumber;
    }
}
