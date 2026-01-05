package com.sample.EmployeeManagement.service;


import com.sample.EmployeeManagement.dto.EmployeeRequest;
import com.sample.EmployeeManagement.dto.EmployeeResponse;
import com.sample.EmployeeManagement.entity.Employee;
import com.sample.EmployeeManagement.exception.EntityNotFoundException;
import com.sample.EmployeeManagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeResponse create(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .email(employeeRequest.getEmail())
                .department(employeeRequest.getDepartment())
                .build();

        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    public EmployeeResponse getById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id not found"));

        return modelMapper.map(employee, EmployeeResponse.class);

    }

    public EmployeeResponse update(EmployeeRequest employeeRequest, Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id not found"));

        employee = Employee.builder()
                .id(employee.getId())
                .name(employeeRequest.getName())
                .email(employeeRequest.getEmail())
                .department(employeeRequest.getDepartment())
                .build();

        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id not found"));

        employeeRepository.delete(employee);
    }

    public List<EmployeeResponse> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .collect(Collectors.toList());
    }
}
