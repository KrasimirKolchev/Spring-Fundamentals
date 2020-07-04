package com.krasimirkolchev.linkedOut.services;

import com.krasimirkolchev.linkedOut.models.entity.Employee;
import com.krasimirkolchev.linkedOut.models.serviceModels.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {
    Employee findEmployeeById(String id);

    void addEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> getAllEmployees();

    void deleteEmployeeById(String id);
}
