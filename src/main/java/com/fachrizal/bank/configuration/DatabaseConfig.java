package com.fachrizal.bank.configuration;


import com.fachrizal.bank.model.AccountDetails;
import com.fachrizal.bank.model.UserDetails;
import com.fachrizal.bank.repository.AccountDetailsRepository;
import com.fachrizal.bank.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfig {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            UserDetails firstUser = UserDetails.builder()
                    .userId("ABC12345")
                    .accountNo(1234567890)
                    .password(bcryptEncoder.encode("password1"))
                    .build();

            UserDetails secondUser = UserDetails.builder()
                    .userId("ABC12346")
                    .accountNo(2123456789)
                    .password(bcryptEncoder.encode("password2"))
                    .build();

            List<UserDetails> userDetailsList = new ArrayList<>();
            userDetailsList.add(firstUser);
            userDetailsList.add(secondUser);

            AccountDetails firstAccountDetails = AccountDetails.builder()
                    .accountNo(1234567890)
                    .availableBalance(4000.00)
                    .build();

            AccountDetails secondAccountDetails = AccountDetails.builder()
                    .accountNo(2123456789)
                    .availableBalance(0.00)
                    .build();

            List<AccountDetails> accountDetailsList = new ArrayList<>();
            accountDetailsList.add(firstAccountDetails);
            accountDetailsList.add(secondAccountDetails);

            userDetailsRepository.saveAll(userDetailsList);
            accountDetailsRepository.saveAll(accountDetailsList);
        };
    }
}
