package com.fachrizal.bank.dto.request;

import lombok.Data;

@Data
public class FundTransferRequest {
    private String amount;
    private String fromAccount;
    private String toAccount;
}
