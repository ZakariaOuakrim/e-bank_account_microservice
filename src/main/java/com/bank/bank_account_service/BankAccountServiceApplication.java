package com.bank.bank_account_service;

import com.bank.bank_account_service.entities.BankAccount;
import com.bank.bank_account_service.entities.Customer;
import com.bank.bank_account_service.enums.AccountType;
import com.bank.bank_account_service.repositories.BankAccountRepository;
import com.bank.bank_account_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
		return args ->{
			Stream.of("Zakaria","Adil","Hafed").forEach(name -> {
				Customer customer = Customer.builder()
						.name(name)
						.build();
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(customer -> {
				for(int i =0;i<10;i++){
					BankAccount bankAccount=BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random()>0.5? AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
							.balance(10000+Math.random()*90000)
							.createdDate(new Date())
							.currency("MAD")
							.customer(customer)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});

		};
	}
}
