package com.activexsolutions.manishaagro.service;

import com.activexsolutions.manishaagro.model.Employee;
import com.activexsolutions.manishaagro.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        final List<Employee> result = (List<Employee>) employeeRepository.findAll();

        if (!result.isEmpty()) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public Employee getEmployeeById(final String id) throws Exception {
        final Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }

    public Employee createOrUpdateEmployee(Employee employee) {
        if (employee.getEmpId().isEmpty()) {
            Optional<Employee> employeeObject = employeeRepository.findById(employee.getEmpId());
            if (employeeObject.isPresent()) {
                employeeObject.get().setName(employee.getName());
                employeeObject.get().setDesignation(employee.getDesignation());
                employeeObject.get().setContactDetail(employee.getContactDetail());
                employeeObject.get().setEmailId(employee.getEmailId());
                employee = employeeRepository.save(employeeObject.get());
            } else {
                employee = employeeRepository.save(employee);
            }
        } else {
            employee = employeeRepository.save(employee);
        }
        return employee;
    }

    public void deleteEmployeeById(final String id) throws Exception {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }
}
