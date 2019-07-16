package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping
  public List<Employee> findAll() {
    return employeeService.findAll();
  }

  @GetMapping("{employeeId}")
  public Employee findById(@PathVariable int employeeId) {
    return employeeService.findById(employeeId);
  }

  @GetMapping("page")
  public List<Employee> findByPageAndPageSize(@RequestParam int page, @RequestParam int pageSize) {
    return employeeService.findByPageAndPageSize(page, pageSize);
  }

  @GetMapping("gender")
  public List<Employee> findByGender(@RequestParam String gender) {
    return employeeService.findByGender(gender);
  }

  @PostMapping
  public Employee addEmployee(@RequestBody Employee employee) {
    return employeeService.add(employee);
  }

  @PutMapping("{employeeId}")
  public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int employeeId) {
    return employeeService.update(employee, employeeId);
  }

  @DeleteMapping("{employeeId}")
  public Employee deleteEmployee(@PathVariable int employeeId) {
    return employeeService.delete(employeeId);
  }
}

