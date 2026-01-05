package com.sample.EmployeeManagement.controller;

import com.sample.EmployeeManagement.dto.EmployeeRequest;
import com.sample.EmployeeManagement.dto.EmployeeResponse;
import com.sample.EmployeeManagement.entity.Employee;
import com.sample.EmployeeManagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create")
    public EmployeeResponse create(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.create(employeeRequest);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Long id){
        return employeeService.getById(id);
    }

    @GetMapping
    public List<EmployeeResponse> getAll(){
        return employeeService.getAll();
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@RequestBody EmployeeRequest employeeRequest, @PathVariable Long id){
        return employeeService.update(employeeRequest, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
