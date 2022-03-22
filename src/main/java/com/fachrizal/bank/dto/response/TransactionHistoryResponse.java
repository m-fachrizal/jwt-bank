package com.fachrizal.bank.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionHistoryResponse {
    private String amount;
    private String referenceNumber;
    private String transactionFlag;
}
