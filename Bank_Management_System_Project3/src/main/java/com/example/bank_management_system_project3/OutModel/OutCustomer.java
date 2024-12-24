package com.example.bank_management_system_project3.OutModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OutCustomer {

    private Integer customer_No;
    private String customer_Name;
    private String customer_PhoneNumber;
    private String customer_Email;
}
