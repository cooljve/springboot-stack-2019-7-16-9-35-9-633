package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  public List<Company> findAll() {
    return companyRepository.findAll();
  }

  public Company findByCompanyName(String companyName) {
    return companyRepository.findByCompanyName(companyName);
  }

  public List<Employee> findEmployeesByCompanyName(String companyName) {
    return companyRepository.findEmployeesByCompanyName(companyName);
  }

  public List<Company> findByPageAndPageSize(int page, int pageSize) {
    return companyRepository.findByPageAndPageSize(page, pageSize);
  }

  public Company add(Company company) {
    return companyRepository.add(company);
  }

  public Company update(Company company, String companyName) {
    return companyRepository.update(company, companyName);
  }

  public Company delete(String companyName) {
    return companyRepository.delete(companyName);
  }
}
