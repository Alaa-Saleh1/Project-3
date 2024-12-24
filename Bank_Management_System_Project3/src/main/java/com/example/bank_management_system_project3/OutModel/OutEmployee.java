package com.example.bank_management_system_project3.OutModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OutEmployee {

    private Integer employee_No;
    private String employee_Name;
    private String employee_Email;
    private String employee_Position;
    private Double employee_Salary;


}
