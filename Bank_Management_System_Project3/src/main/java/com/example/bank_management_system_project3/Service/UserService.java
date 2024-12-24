package com.example.bank_management_system_project3.Service;

import com.example.bank_management_system_project3.ApiResponse.ApiException;
import com.example.bank_management_system_project3.Model.Account;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.Repository.AccountRepository;
import com.example.bank_management_system_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public void activateAccount(Integer accountId, Integer customerId) {
        User user = userRepository.findUserById(customerId);
        if (user.getRole().equals("CUSTOMER")) {
            throw new ApiException("Customer not authorized");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (account.getIsActive()) {
            throw new ApiException("Account is already active");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public void blockAccount(Integer accountId, Integer customerId) {
        User user = userRepository.findUserById(customerId);
        if (user.getRole().equals("CUSTOMER")) {
            throw new ApiException("Customer not authorized");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.getIsActive()) {
            throw new ApiException("Account is already blocked or inactive");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }

}
