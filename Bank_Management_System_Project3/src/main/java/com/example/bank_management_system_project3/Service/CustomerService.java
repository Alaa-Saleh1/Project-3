package com.example.bank_management_system_project3.Service;

import com.example.bank_management_system_project3.ApiResponse.ApiException;
import com.example.bank_management_system_project3.InputDTO.InputCustomer;
import com.example.bank_management_system_project3.Model.Account;
import com.example.bank_management_system_project3.Model.Customer;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.OutModel.OutAccount;
import com.example.bank_management_system_project3.OutModel.OutCustomer;
import com.example.bank_management_system_project3.Repository.CustomerRepository;
import com.example.bank_management_system_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public List<OutCustomer> getAllCustomers(Integer id) {
        User user = userRepository.findUserById(id);
        if (user.getRole().equals("CUSTOMER")) {
            throw new ApiException("Unauthorized access");
        }
        List<Customer> customers = customerRepository.findAll();
        List<OutCustomer> outCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            OutCustomer customer1 = convertCustomerToOutCustomer(customer);
            outCustomers.add(customer1);
        }
        return outCustomers;
    }

    public OutCustomer getCustomerById(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return convertCustomerToOutCustomer(customer);
    }

    public void customerRegister(InputCustomer inputCustomer) {
        //add user
        User user = new User();
        user.setRole("CUSTOMER");
        user.setUsername(inputCustomer.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(inputCustomer.getPassword());
        user.setPassword(hashPassword);
        user.setName(inputCustomer.getName());
        user.setEmail(inputCustomer.getEmail());

        //add customer
        Customer customer = new Customer();
        customer.setId(null);
        customer.setPhoneNumber(inputCustomer.getPhoneNumber());
        customer.setUser(user);
        //save
        customerRepository.save(customer);
        userRepository.save(user);

    }


    public void updateCustomerProfile(Integer customer_id , InputCustomer inputCustomer) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        customer.setPhoneNumber(inputCustomer.getPhoneNumber());
        if(customer.getUser() != null) {
            User user = customer.getUser();
            user.setUsername(inputCustomer.getUsername());
            String hashPassword = new BCryptPasswordEncoder().encode(inputCustomer.getPassword());
            user.setPassword(hashPassword);
            user.setName(inputCustomer.getName());
            user.setEmail(inputCustomer.getEmail());
            userRepository.save(user);
        }
        customerRepository.save(customer);

    }

    public void deleteCustomer(Integer customer_id) {
        User user = userRepository.findUserById(customer_id);
        if (user == null) {
            throw new ApiException("Customer not found");
        }
        userRepository.delete(user);
    }


    public OutCustomer convertCustomerToOutCustomer(Customer customer) {
        User user = customer.getUser();
        AtomicInteger counter = new AtomicInteger(1);
        return new OutCustomer(counter.getAndIncrement(),user.getName(),customer.getPhoneNumber(), user.getEmail());
    }



}
