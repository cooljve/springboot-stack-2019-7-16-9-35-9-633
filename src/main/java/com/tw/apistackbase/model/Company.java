package com.tw.apistackbase.model;

import java.util.List;

public class Company {
  private String companyName;
  private int employeesNumber;
  private List<Employee> employees;

  public Company() {
  }

  public Company(String companyName, int employeesNumber, List<Employee> employees) {
    this.companyName = companyName;
    this.employeesNumber = employeesNumber;
    this.employees = employees;
  }

  public String getCompanyName() {
    return companyName;
  }

  public int getEmployeesNumber() {
    return employeesNumber;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setEmployeesNumber(int employeesNumber) {
    this.employeesNumber = employeesNumber;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }
}
