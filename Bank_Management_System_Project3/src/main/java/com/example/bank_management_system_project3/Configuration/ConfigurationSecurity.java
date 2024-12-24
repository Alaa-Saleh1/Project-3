package com.example.bank_management_system_project3.Configuration;

import com.example.bank_management_system_project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/bank-system/customer/register").permitAll()
                .requestMatchers("/api/v1/bank-system/employee/register").permitAll()
                .requestMatchers("/api/v1/bank-system/employee/delete-profile","/api/v1/bank-system/employee/update-profile").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/bank-system/customer/delete-profile","/api/v1/bank-system/customer/update-profile","/api/v1/bank-system/customer/my-info","/api/v1/bank-system/account/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/bank-system/employee/get-all","/api/v1/bank-system/user/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/bank-system/customer/get-all").hasAnyAuthority("EMPLOYEE","ADMIN")

                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
