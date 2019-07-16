package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

  private static Map<String, Company> map = new HashMap<>();

  static List<Employee> createEmployees() {
    Employee employee1 = new Employee(1, "Joi", 22, "female");
    Employee employee2 = new Employee(2, "Bob", 22, "male");
    Employee employee3 = new Employee(3, "Helen", 22, "female");
    return Arrays.asList(employee1, employee2, employee3);
  }

  static {
    map.put("abc", new Company("abc", 10, createEmployees()));
    map.put("def", new Company("def", 20, createEmployees()));
    map.put("ghi", new Company("ghi", 30, createEmployees()));
  }

  @Override
  public List<Company> findAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  public Company findByCompanyName(String companyName) {
    return map.get(companyName);
  }

  @Override
  public Company add(Company company) {
    map.put(company.getCompanyName(), company);
    return company;
  }

  @Override
  public Company delete(String companyName) {
    return map.remove(companyName);
  }

  @Override
  public Company update(Company company, String companyName) {
    if (map.containsKey(companyName)) {
      map.replace(companyName, company);
    } else {
      map.put(companyName, company);
    }
    return company;
  }

  @Override
  public List<Company> findByPageAndPageSize(int page, int pageSize) {
    return new ArrayList<>(map.values()).stream()
        .sorted(Comparator.comparing(Company::getCompanyName))
        .limit(page * pageSize).collect(Collectors.toList());
  }

  @Override
  public List<Employee> findEmployeesByCompanyName(String companyName) {
    return map.get(companyName).getEmployees();
  }
}
