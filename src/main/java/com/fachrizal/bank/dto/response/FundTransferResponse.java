package com.fachrizal.bank.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FundTransferResponse {

    private String accountBalance;
    private String referenceNumber;
}
