package com.example.bank_management_system_project3.InputDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class InputCustomer {



    @NotEmpty(message = "Username is request.")
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

//    private String role= "CUSTOMER";

    @NotEmpty(message = "Phone number is request. ")
    @Pattern(regexp = "^\\+9665[0-9]{8}$", message = "Phone number must be a valid Saudi number starting with +9665 and followed by 8 digits")
    private String phoneNumber;

}
