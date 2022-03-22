package com.fachrizal.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {

    @Id
    @Column
    private Integer accountNo;

    @Column
    private Double availableBalance;
}
