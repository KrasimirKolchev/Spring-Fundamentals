package com.krasimirkolchev.linkedOut.services.impl;

import com.krasimirkolchev.linkedOut.models.entity.Employee;
import com.krasimirkolchev.linkedOut.models.serviceModels.EmployeeServiceModel;
import com.krasimirkolchev.linkedOut.repositories.EmployeeRepository;
import com.krasimirkolchev.linkedOut.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Employee findEmployeeById(String id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void addEmployee(EmployeeServiceModel employeeServiceModel) {
        this.employeeRepository.save(this.modelMapper.map(employeeServiceModel, Employee.class));
    }

    @Override
    public List<EmployeeServiceModel> getAllEmployees() {
        return this.employeeRepository.findAll()
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployeeById(String id) {
        this.employeeRepository.deleteById(id);
    }
}
