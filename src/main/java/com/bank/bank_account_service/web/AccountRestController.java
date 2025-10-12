package com.bank.bank_account_service.web;

import com.bank.bank_account_service.entities.BankAccount;
import com.bank.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    @Autowired
    BankAccountRepository bankAccountRepository;


    @GetMapping("/bankAccounts")
    public List<BankAccount> getAllAccountBank(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getAllAccountBank(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));

    }
}
