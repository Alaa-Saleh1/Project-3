package com.example.bank_management_system_project3.InputDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InputEmployee {

    @NotEmpty (message = "Username is request.")
    @Size(min = 4, max = 10, message = "Length must be between 4 and 10 characters.")
    private String username;

    @NotEmpty(message = "Password is request.")
    @Size(min = 8, max = 18, message = "Length must be between 8 and 18 characters.")
    private String password;

    @NotEmpty(message = "Name is request.")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 characters.")
    private String name;

    @NotEmpty(message = "Email is request.")
    @Email(message = "Must be a valid email format.")
    private String email;

////    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN")
//    private String role= "EMPLOYEE";

    @Column(nullable = false)
    private String position;

    @NotNull(message = "Salary is request.")
    @Positive(message = "Must be a non-negative decimal number.")
    private Double salary;


}
