package com.example.bank_management_system_project3.Service;

import com.example.bank_management_system_project3.ApiResponse.ApiException;
import com.example.bank_management_system_project3.InputDTO.InputEmployee;
import com.example.bank_management_system_project3.Model.Customer;
import com.example.bank_management_system_project3.Model.Employee;
import com.example.bank_management_system_project3.Model.User;
import com.example.bank_management_system_project3.OutModel.OutCustomer;
import com.example.bank_management_system_project3.OutModel.OutEmployee;
import com.example.bank_management_system_project3.Repository.EmployeeRepository;
import com.example.bank_management_system_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;


    public void employeeRegister(InputEmployee inputEmployee){
        //add user
        User user = new User();
        user.setRole("EMPLOYEE");
        user.setUsername(inputEmployee.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(inputEmployee.getPassword());
        user.setPassword(hashPassword);
        user.setName(inputEmployee.getName());
        user.setEmail(inputEmployee.getEmail());

        //add employee
        Employee employee = new Employee(null,inputEmployee.getPosition(),inputEmployee.getSalary(),user);

        //save
        employeeRepository.save(employee);
        userRepository.save(user);

    }

    public List<OutEmployee> getAllEmployee(Integer id){
        User user = userRepository.findUserById(id);
        if(!user.getRole().equals("ADMIN")){
            throw new ApiException("Unauthorized access");
        }
        List<Employee> employees= employeeRepository.findAll();
        List<OutEmployee> outEmployees = new ArrayList<>();
        for(Employee employee:employees){
            OutEmployee outEmployee = convertEmployeeToOutEmployee(employee);
            outEmployees.add(outEmployee);
        }
        return outEmployees;
    }

    public void updateEmployeeProfile(Integer employee_id , InputEmployee inputEmployee) {
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        if (employee == null) {
            throw new ApiException("Employee not found");
        }
        employee.setPosition(employee.getPosition());
        employee.setSalary(inputEmployee.getSalary());
        if (employee.getUser() != null) {
            User user = employee.getUser();
            user.setUsername(inputEmployee.getUsername());
            String hashPassword = new BCryptPasswordEncoder().encode(inputEmployee.getPassword());
            user.setPassword(hashPassword);
            user.setName(inputEmployee.getName());
            user.setEmail(inputEmployee.getEmail());
            userRepository.save(user);
        }
        employeeRepository.save(employee);
    }

    public void deleteCustomerProfile(Integer employee_id) {
        User user = userRepository.findUserById(employee_id);
        if (user == null) {
            throw new ApiException("employee not found");
        }
        userRepository.delete(user);
    }

    public OutEmployee convertEmployeeToOutEmployee(Employee employee) {
        User user = employee.getUser();
        AtomicInteger counter = new AtomicInteger(1);
        return new OutEmployee(counter.getAndIncrement(),user.getName(),user.getEmail(),employee.getPosition(),employee.getSalary());
    }
}
