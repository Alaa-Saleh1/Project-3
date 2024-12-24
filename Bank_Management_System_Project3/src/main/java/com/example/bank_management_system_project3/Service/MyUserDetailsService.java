package com.example.bank_management_system_project3.Service;

import com.example.bank_management_system_project3.ApiResponse.ApiException;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            throw new ApiException("Wrong username or password");
        }
        return user;
    }


}
