package com.example.bank_management_system_project3.Controller;

import com.example.bank_management_system_project3.ApiResponse.ApiResponse;
import com.example.bank_management_system_project3.InputDTO.InputCustomer;
import com.example.bank_management_system_project3.InputDTO.InputEmployee;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.OutModel.OutCustomer;
import com.example.bank_management_system_project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-system/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> customerRegister(@RequestBody @Valid InputCustomer inputCustomer) {
        customerService.customerRegister(inputCustomer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCustomers(@AuthenticationPrincipal User user) {
        List<OutCustomer> customers = customerService.getAllCustomers(user.getId());
        return ResponseEntity.status(200).body(customers);
    }

    @GetMapping("/my-info")
    public ResponseEntity<?> getCustomerInfo(@AuthenticationPrincipal User user) {
        OutCustomer customer = customerService.getCustomerById(user.getId());
        return ResponseEntity.status(200).body(customer);
    }
    @PutMapping("/update-profile")
    public ResponseEntity<?> customerUpdateProfile(@AuthenticationPrincipal User user, @RequestBody @Valid InputCustomer inputCustomer) {
        customerService.updateCustomerProfile(user.getId(), inputCustomer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer profile updated successfully"));
    }

    @DeleteMapping("/delete-profile")
    public ResponseEntity<?> customerDeleteProfile(@AuthenticationPrincipal User user) {
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Customer profile deleted successfully"));
    }


}
