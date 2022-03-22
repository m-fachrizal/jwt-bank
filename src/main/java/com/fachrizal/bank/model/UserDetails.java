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
public class UserDetails {

    @Id
    private String userId;

    @Column
    private Integer accountNo;

    @Column
    private String password;

}
