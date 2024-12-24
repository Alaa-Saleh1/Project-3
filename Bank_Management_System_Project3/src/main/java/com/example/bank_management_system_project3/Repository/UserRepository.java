package com.example.bank_management_system_project3.Repository;

import com.example.bank_management_system_project3.Model.Customer;
import com.example.bank_management_system_project3.Model.Employee;
import com.example.bank_management_system_project3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserById(Integer id);

    List<User> findUsersByCustomer(Customer customer);

    List<User> findUsersByEmployee(Employee employee);

}
