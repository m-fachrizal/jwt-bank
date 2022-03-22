package com.fachrizal.bank.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionsResponse {
    private List<TransactionHistoryResponse> transactions;
}
