package com.bank.bank_account_service.entities;

import com.bank.bank_account_service.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdDate;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne
    private Customer customer;
}
