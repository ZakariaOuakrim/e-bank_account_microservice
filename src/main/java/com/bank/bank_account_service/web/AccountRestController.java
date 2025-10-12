package com.bank.bank_account_service.web;

import com.bank.bank_account_service.dto.BankAccountRequestDTO;
import com.bank.bank_account_service.dto.BankAccountResponseDTO;
import com.bank.bank_account_service.entities.BankAccount;
import com.bank.bank_account_service.mappers.BankAccountMapper;
import com.bank.bank_account_service.repositories.BankAccountRepository;
import com.bank.bank_account_service.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    @GetMapping("/bankAccounts")
    public List<BankAccount> getAllAccountBank(){
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getAllAccountBank(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO saveAccount(@RequestBody BankAccountRequestDTO requestDTO){
        return bankAccountService.addAccount(requestDTO);
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
