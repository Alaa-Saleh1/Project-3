package com.example.bank_management_system_project3.Service;

import com.example.bank_management_system_project3.ApiResponse.ApiException;
import com.example.bank_management_system_project3.InputDTO.InputAccount;
import com.example.bank_management_system_project3.Model.Account;
import com.example.bank_management_system_project3.Model.Customer;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.OutModel.OutAccount;
import com.example.bank_management_system_project3.Repository.AccountRepository;
import com.example.bank_management_system_project3.Repository.CustomerRepository;
import com.example.bank_management_system_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;


    public OutAccount getMyAccountDetails(Integer accountId, Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        Account account = accountRepository.findAccountById(accountId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (customer.getId()!=account.getCustomer().getId()) {
            throw new ApiException("Unauthorized, customer id mismatch");
        }
        return convertAccountToOutAccount(account);
    }


    public List<OutAccount> getAllMyAccounts(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("There are no accounts for you");
        }
        List<Account> accounts = accountRepository.findAccountsByCustomer(customer);
        List<OutAccount> outAccounts = new ArrayList<>();
        for (Account account : accounts) {
            OutAccount out = convertAccountToOutAccount(account);
            outAccounts.add(out);
        }

        return outAccounts;
    }

    public void createAccount(Integer customerId, InputAccount inputAccount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = new Account();
        account.setAccountNumber(inputAccount.getAccountNumber());
        account.setBalance(inputAccount.getBalance());
        account.setIsActive(false);
        account.setCustomer(customer);
        accountRepository.save(account);
    }


    public String depositMoney(Integer customerId, Integer accountId, Double amount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        Account account = accountRepository.findAccountById(accountId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.getIsActive()) {
            throw new ApiException("Account is not active");
        }
        if (customer.getId()!=account.getCustomer().getId()) {
            throw new ApiException("Unauthorized, customer id mismatch");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return "Deposit successful."+"\nNew balance: "+account.getBalance();
    }

    public String withdrawMoney(Integer customerId, Integer accountId, Double amount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.getIsActive()) {
            throw new ApiException("Account is not active");
        }
        if (customer.getId()!=account.getCustomer().getId()) {
            throw new ApiException("Unauthorized, customer id mismatch");
        }
        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return "Withdraw successful."+"\nNew balance: "+account.getBalance();
    }

    public void transferMoney(Integer customerId, Integer fromAccountId,Integer toAccountId, Double amount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        Account toAccount = accountRepository.findAccountById(toAccountId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        if (fromAccount == null) {
            throw new ApiException("Source account not found");
        }
        if (customer.getId()!=fromAccount.getCustomer().getId()) {
            throw new ApiException("Unauthorized, customer id mismatch");
        }
        if (toAccount == null) {
            throw new ApiException("Destination account not found");
        }
        if (!fromAccount.getIsActive()) {
            throw new ApiException("Source account is not active");
        }
        if (!toAccount.getIsActive()) {
            throw new ApiException("Destination account is not active");
        }
        if (fromAccount.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);

        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);
    }



    public OutAccount convertAccountToOutAccount(Account account) {
        Customer customer = account.getCustomer();
        AtomicInteger counter = new AtomicInteger(1);
        OutAccount outAccount = new OutAccount(counter.getAndIncrement(),customer.getUser().getName(),account.getAccountNumber(),account.getBalance(),account.getIsActive());
        return outAccount;
    }





}
