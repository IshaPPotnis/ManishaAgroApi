package com.activexsolutions.manishaagro.controller;

import com.activexsolutions.manishaagro.model.Employee;
import com.activexsolutions.manishaagro.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/getEmployeeDetails/{empId}")
    public Employee getEmployeeDetails(@RequestParam String empId) {
        return employeeRepository.findById(empId).orElse(null);
    }
}
