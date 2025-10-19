package com.bank.bank_account_service.service;

import com.bank.bank_account_service.dto.BankAccountRequestDTO;
import com.bank.bank_account_service.dto.BankAccountResponseDTO;
import com.bank.bank_account_service.entities.BankAccount;
import com.bank.bank_account_service.enums.AccountType;

public interface BankAccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
