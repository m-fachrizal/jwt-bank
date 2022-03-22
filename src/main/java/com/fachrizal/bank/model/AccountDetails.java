package com.fachrizal.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer accountNo;

    @Column
    private Double availableBalance;
}
