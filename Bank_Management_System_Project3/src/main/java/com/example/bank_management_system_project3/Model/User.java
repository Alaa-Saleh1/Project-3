package com.example.bank_management_system_project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty (message = "Username is request.")
//    @Size(min = 4, max = 10, message = "Length must be between 4 and 10 characters.")
//    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

//    @NotEmpty(message = "Password is request.")
//    @Size(min = 8, max = 18, message = "Length must be between 8 and 18 characters.")
//    @Column(columnDefinition = "varchar(18) not null")
    private String password;

//    @NotEmpty(message = "Name is request.")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 characters.")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

//    @NotEmpty(message = "Email is request.")
    @Email(message = "Must be a valid email format.")
    @Column(columnDefinition = "varchar(80) not null unique")
    private String email;

    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN")
    @Column(columnDefinition = "varchar(8)")
    private String role;

    //Relation

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
