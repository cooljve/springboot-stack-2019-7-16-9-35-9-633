package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;

import java.util.List;

public interface CompanyRepository {
  List<Company> findAll();

  Company findByCompanyName(String companyName);

  Company add(Company company);

  Company delete(String companyName);

  Company update(Company company, String companyName);

  List<Company> findByPageAndPageSize(int page, int pageSize);

  List<Employee> findEmployeesByCompanyName(String companyName);
}
