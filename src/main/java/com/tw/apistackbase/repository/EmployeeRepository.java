package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;

import java.util.List;

public interface EmployeeRepository {
  List<Employee> findAll();

  Employee findById(int employeeId);

  Employee add(Employee employee);

  Employee delete(int employeeId);

  Employee update(Employee employee, int employeeId);

  List<Employee> findByGender(String gender);

  List<Employee> findByPageAndPageSize(int page, int pageSize);
}
