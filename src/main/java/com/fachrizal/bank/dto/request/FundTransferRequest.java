package com.fachrizal.bank.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FundTransferRequest {

    @NotBlank
    private String amount;

    @NotBlank
    private String fromAccount;

    @NotBlank
    private String toAccount;
}
