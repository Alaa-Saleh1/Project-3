package com.example.bank_management_system_project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "Account number is request")
//    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}",message = "Must follow a specific format (e.g., \"XXXX-XXXX-XXXX-XXXX\").")
    @Column(unique = true, nullable = false)
    private String accountNumber;

//    @NotNull(message = "balance is request")
//    @Positive(message = "Must be a non-negative decimal number.")
    @Column(columnDefinition = "Double not null")
    private Double balance;

//    @Column(columnDefinition = "BOOLEAN")
    private Boolean isActive;


    //Relation

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

}
