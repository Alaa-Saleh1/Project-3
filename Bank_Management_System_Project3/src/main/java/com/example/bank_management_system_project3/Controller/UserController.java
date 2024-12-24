package com.example.bank_management_system_project3.Controller;

import com.example.bank_management_system_project3.ApiResponse.ApiResponse;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-system/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/activate/account-id/{accountId}")
    public ResponseEntity<?> activateAccount(@PathVariable Integer accountId, @AuthenticationPrincipal User user) {
         userService.activateAccount(accountId, user.getId());
         return ResponseEntity.status(200).body(new ApiResponse("account activated successfully"));
    }

    @PutMapping("/block/account-id/{accountId}")
    public ResponseEntity<?> blockAccount(@PathVariable Integer accountId, @AuthenticationPrincipal User user) {
        userService.blockAccount(accountId, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("account blocked successfully"));
    }
}
