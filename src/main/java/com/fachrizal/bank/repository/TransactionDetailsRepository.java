package com.fachrizal.bank.repository;

import com.fachrizal.bank.model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {
    List<TransactionDetails> findByAccountNo(Integer accountNo);
}
