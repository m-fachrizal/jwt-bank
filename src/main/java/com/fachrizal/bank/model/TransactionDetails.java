package com.fachrizal.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer accountNo;

    @Column
    @Size(min = 1, max = 1)
    private String transactionFlag;

    @Column
    private Double transactionAmount;

    @Column
    private Integer referenceNumber;



}
