package com.fachrizal.bank.service;

import com.fachrizal.bank.dto.request.FundTransferRequest;
import org.springframework.http.ResponseEntity;

public interface FundTransferService {

    ResponseEntity<?> fundTransfer(FundTransferRequest fundTransferRequest);
}
