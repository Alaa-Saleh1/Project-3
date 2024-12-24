package com.example.bank_management_system_project3.Controller;

import com.example.bank_management_system_project3.ApiResponse.ApiResponse;
import com.example.bank_management_system_project3.InputDTO.InputAccount;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.OutModel.OutAccount;
import com.example.bank_management_system_project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-system/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal User user , @RequestBody @Valid InputAccount account) {
        accountService.createAccount(user.getId(),account);
        return ResponseEntity.status(200).body(new ApiResponse("Account created successfully"));
    }

    @GetMapping("/details/account-id/{accountId}")
    public ResponseEntity<?> getAccountDetails(@AuthenticationPrincipal User user , @PathVariable int accountId) {
        OutAccount outAccount = accountService.getMyAccountDetails(accountId, user.getId());
        return ResponseEntity.status(200).body(outAccount);
    }

    @GetMapping("/get-my-accounts")
    public ResponseEntity<?> getMyAccounts(@AuthenticationPrincipal User user) {
        List<OutAccount> accounts = accountService.getAllMyAccounts(user.getId());
        return ResponseEntity.status(200).body(accounts);
    }

    @PutMapping("/deposit/account-id/{accountId}/amount/{amount}")
    public ResponseEntity<?> depositMoney(@AuthenticationPrincipal User user , @PathVariable Double amount, @PathVariable Integer accountId) {
        String message = accountService.depositMoney(user.getId(),accountId,amount);
        return ResponseEntity.status(200).body(message);
    }

    @PutMapping("/withdraw/account-id/{accountId}/amount/{amount}")
    public ResponseEntity<?> withdrawMoney(@AuthenticationPrincipal User user , @PathVariable Double amount, @PathVariable Integer accountId) {
        String message = accountService.withdrawMoney(user.getId(),accountId,amount);
        return ResponseEntity.status(200).body(message);
    }

    @PutMapping("/transfer/from-account/{fromAccount}/to-account/{toAccount}/amount/{amount}")
    public ResponseEntity<?> transferMoney(@AuthenticationPrincipal User user , @PathVariable Integer fromAccount,@PathVariable Integer toAccount,@PathVariable Double amount) {
        accountService.transferMoney(user.getId(),fromAccount,toAccount,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Transfer successful"));
    }

}

