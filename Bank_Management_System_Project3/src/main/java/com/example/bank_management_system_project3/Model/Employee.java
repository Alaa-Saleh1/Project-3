package com.example.bank_management_system_project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {

    @Id
    private Integer id;

//    @NotEmpty(message = "Position is request. ")
    @Column(nullable = false)
    private String position;

//    @NotNull(message = "Salary is request.")
    @Positive(message = "Must be a non-negative decimal number.")
    @Column(nullable = false)
    private Double salary;


    //Relation
    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

}
