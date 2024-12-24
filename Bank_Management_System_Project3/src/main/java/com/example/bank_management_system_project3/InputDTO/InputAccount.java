package com.example.bank_management_system_project3.InputDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InputAccount {


    //    @NotEmpty(message = "Account number is request")
    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}",message = "Must follow a specific format (e.g., \"XXXX-XXXX-XXXX-XXXX\").")
    private String accountNumber;

    //    @NotNull(message = "balance is request")
    @Positive(message = "Must be a non-negative decimal number.")
    private Double balance;
}
