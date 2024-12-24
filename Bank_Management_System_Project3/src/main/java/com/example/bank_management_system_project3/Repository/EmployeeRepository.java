package com.example.bank_management_system_project3.Repository;

import com.example.bank_management_system_project3.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findEmployeeById(Integer employeeId);
}
