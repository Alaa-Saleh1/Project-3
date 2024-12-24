package com.example.bank_management_system_project3.Repository;

import com.example.bank_management_system_project3.Model.Account;
import com.example.bank_management_system_project3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountById(Integer id);

    List<Account> findAccountsByCustomer(Customer customer);
}
