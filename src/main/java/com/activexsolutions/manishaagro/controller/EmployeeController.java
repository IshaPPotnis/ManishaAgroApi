package com.activexsolutions.manishaagro.controller;

import com.activexsolutions.manishaagro.model.Employee;
import com.activexsolutions.manishaagro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping ("/")
public class EmployeeController {

    @Autowired
    private
    EmployeeService employeeService;

    @RequestMapping (method = RequestMethod.GET, value = "/getEmployeeDetails/{empId}")
    public List<Employee> getEmployeeDetails(@RequestParam final String empId) {
        return employeeService.getAllEmployees();
    }

    @RequestMapping
    public String getEmployee(final Model model) {
        final List<Employee> all = employeeService.getAllEmployees();
        model.addAttribute("employees", all);
        return "list-employees";
    }

    @RequestMapping (path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(final Model model, @PathVariable ("id") final Optional<String> id) throws Exception {
        if (id.isPresent()) {
            final Employee entity = employeeService.getEmployeeById(id.get());
            model.addAttribute("employee", entity);
        }
        return "add-edit-employee";
    }

    @RequestMapping (path = "/delete/{id}")
    public String deleteEmployeeById(final Model model, @PathVariable ("id") final String id) throws Exception {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @RequestMapping (path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateEmployee(final Employee employee) {
        employeeService.createOrUpdateEmployee(employee);
        return "redirect:/";
    }
}
