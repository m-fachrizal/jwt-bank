package com.fachrizal.bank.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BalanceCheckResponse {

    private String accountBalance;
}
