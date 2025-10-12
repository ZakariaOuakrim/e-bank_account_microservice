package com.bank.bank_account_service.web;

import com.bank.bank_account_service.entities.BankAccount;
import com.bank.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @PostMapping("/bankAccounts")
    public BankAccount saveAccount(@RequestBody BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount saveAccount(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount bankAccount1=bankAccountRepository.findById(id).orElseThrow();
       if(bankAccount1.getBalance()!=null) bankAccount1.setBalance(bankAccount.getBalance());
        if(bankAccount1.getCreatedDate()!=null) bankAccount1.setCreatedDate(new Date());
        if(bankAccount1.getType()!=null) bankAccount1.setType(bankAccount.getType());
        if(bankAccount1.getCurrency()!=null) bankAccount1.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(bankAccount1);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
