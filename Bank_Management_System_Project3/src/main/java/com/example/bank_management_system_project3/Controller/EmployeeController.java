package com.example.bank_management_system_project3.Controller;

import com.example.bank_management_system_project3.ApiResponse.ApiResponse;
import com.example.bank_management_system_project3.InputDTO.InputEmployee;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.OutModel.OutEmployee;
import com.example.bank_management_system_project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-system/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllEmployees(@AuthenticationPrincipal User user) {
        List<OutEmployee> employees = employeeService.getAllEmployee(user.getId());
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping("/register")
    public ResponseEntity<?> employeeRegister(@RequestBody @Valid InputEmployee employee){
        employeeService.employeeRegister(employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered successfully"));
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> employeeUpdateProfile(@AuthenticationPrincipal User user, @RequestBody @Valid InputEmployee employee){
        employeeService.updateEmployeeProfile(user.getId(), employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee profile updated successfully"));
    }

    @DeleteMapping("/delete-profile")
    public ResponseEntity<?> employeeDeleteProfile(@AuthenticationPrincipal User user){
        employeeService.deleteCustomerProfile(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Customer profile deleted successfully"));
    }

}
