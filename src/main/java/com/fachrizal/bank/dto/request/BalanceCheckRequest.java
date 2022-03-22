package com.fachrizal.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BalanceCheckRequest {
    private String accountNo;
}
