package com.bank.bank_account_service.web;

import com.bank.bank_account_service.dto.BankAccountRequestDTO;
import com.bank.bank_account_service.dto.BankAccountResponseDTO;
import com.bank.bank_account_service.entities.BankAccount;
import com.bank.bank_account_service.repositories.BankAccountRepository;
import com.bank.bank_account_service.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private BankAccountService bankAccountService;

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument  String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }

    @MutationMapping
    public BankAccountResponseDTO  addAccount(@Argument BankAccountRequestDTO bankAccount){
        return bankAccountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO  updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return bankAccountService.updateAccount(id,bankAccount);
    }

    @MutationMapping
    public void deleteAccount(@Argument String id){
         bankAccountRepository.deleteById(id);
    }


}

record BankAccountDTO(double balance,String type,String currency){

}