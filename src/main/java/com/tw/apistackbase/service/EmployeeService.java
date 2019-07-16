package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  public Employee findById(int employeeId) {
    return employeeRepository.findById(employeeId);
  }

  public Employee add(Employee employee) {
    return employeeRepository.add(employee);
  }

  public Employee delete(int employeeId) {
    return employeeRepository.delete(employeeId);
  }

  public Employee update(Employee employee, int employeeId) {
    return employeeRepository.update(employee, employeeId);
  }

  public List<Employee> findByGender(String gender) {
    return employeeRepository.findByGender(gender);
  }

  public List<Employee> findByPageAndPageSize(int page, int pageSize) {
    return employeeRepository.findByPageAndPageSize(page, pageSize);
  }
}
