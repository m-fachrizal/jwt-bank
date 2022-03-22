package com.fachrizal.bank.controller;

import com.fachrizal.bank.dto.request.FundTransferRequest;
import com.fachrizal.bank.service.FundTransferService;
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
public class FundTransferController {

    @Autowired
    private FundTransferService fundTransferService;

    @RequestMapping(value = "/fund-transfer", method = RequestMethod.POST)
    public ResponseEntity<?> fundTransfer(
            @Valid @RequestBody FundTransferRequest fundTransferRequest) {
        log.info("Post Request for /fund-transfer");
        return fundTransferService.fundTransfer(fundTransferRequest);
    }


}
