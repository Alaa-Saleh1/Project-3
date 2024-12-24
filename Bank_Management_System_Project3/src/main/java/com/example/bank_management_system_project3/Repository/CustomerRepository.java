package com.example.bank_management_system_project3.Repository;

import com.example.bank_management_system_project3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


    Customer findCustomerById(Integer id);


}
