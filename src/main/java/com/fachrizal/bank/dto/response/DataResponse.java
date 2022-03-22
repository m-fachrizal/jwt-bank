package com.fachrizal.bank.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataResponse<T> {
    private String responseCode;
    private String responseDesc;
    private T data;
}
