package com.bank.bank_account_service.service;

import com.bank.bank_account_service.dto.BankAccountRequestDTO;
import com.bank.bank_account_service.dto.BankAccountResponseDTO;
import com.bank.bank_account_service.entities.BankAccount;
import com.bank.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;


@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdDate(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount savedBankAccount =bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .balance(savedBankAccount.getBalance())
                .createdDate(savedBankAccount.getCreatedDate())
                .currency(savedBankAccount.getCurrency())
                .type(savedBankAccount.getType())
                .id(savedBankAccount.getId())
                .build();
        return bankAccountResponseDTO;
    }
}
