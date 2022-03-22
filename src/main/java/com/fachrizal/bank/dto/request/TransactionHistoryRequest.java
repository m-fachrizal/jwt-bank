package com.fachrizal.bank.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class TransactionHistoryRequest {

    @NotBlank
    private String accountNo;
}
