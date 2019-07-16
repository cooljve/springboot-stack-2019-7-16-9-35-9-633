package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
  private static Map<Integer, Employee> map = new HashMap<>();

  static {
    map.put(1, new Employee(1, "Joi", 22, "female"));
    map.put(2, new Employee(2, "Bob", 22, "male"));
    map.put(3, new Employee(3, "Max", 22, "male"));
  }

  @Override
  public List<Employee> findAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  public Employee findById(int employeeId) {
    return map.get(employeeId);
  }

  @Override
  public Employee add(Employee employee) {
    int id;
    id = new Random().nextInt(1000);
    while (true) {
      if (!map.containsKey(id)) {
        employee.setId(id);
        map.put(id, employee);
        return employee;
      }
    }
  }

  @Override
  public Employee delete(int employeeId) {
    return map.remove(employeeId);
  }

  @Override
  public Employee update(Employee employee, int employeeId) {
    if (map.containsValue(employeeId)) {
      map.replace(employeeId, employee);
    } else {
      employee.setId(employeeId);
      map.put(employeeId, employee);
    }
    return employee;
  }

  @Override
  public List<Employee> findByGender(String gender) {
    return new ArrayList<>(map.values()).stream()
        .filter(x -> x.getGender().equals(gender))
        .collect(Collectors.toList());
  }

  @Override
  public List<Employee> findByPageAndPageSize(int page, int pageSize) {
    return new ArrayList<>(map.values()).stream()
        .sorted(Comparator.comparing(Employee::getId))
        .limit(page * pageSize).collect(Collectors.toList());
  }
}
