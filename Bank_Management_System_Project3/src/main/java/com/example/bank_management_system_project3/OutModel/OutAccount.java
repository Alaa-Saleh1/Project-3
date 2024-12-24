package com.example.bank_management_system_project3.OutModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OutAccount {

    private Integer account_no;
    private String customerName;
    private String accountNumber;
    private Double balance;
    private Boolean isActive;
}
