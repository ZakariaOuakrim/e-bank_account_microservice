package com.bank.bank_account_service.repositories;

import com.bank.bank_account_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
