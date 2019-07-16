package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/companies")
public class CompanyController {
  @Autowired
  private CompanyService companyService;

  @GetMapping
  public List<Company> findAll() {
    return companyService.findAll();
  }

  @GetMapping("{companyName}")
  public Company findByCompanyName(@PathVariable String companyName) {
    return companyService.findByCompanyName(companyName);
  }

  @GetMapping("{companyName}/employees")
  public List<Employee> findEmployeesByCompanyName(@PathVariable String companyName) {
    return companyService.findEmployeesByCompanyName(companyName);
  }

  @GetMapping("page")
  public List<Company> findByPageAndPageSize(@RequestParam int page, @RequestParam int pageSize) {
    return companyService.findByPageAndPageSize(page, pageSize);
  }

  @PostMapping
  public Company addCompany(@RequestBody Company company) {
    return companyService.add(company);
  }

  @PutMapping("{companyName}")
  public Company updateEmployee(@RequestBody Company company, @PathVariable String companyName) {
    return companyService.update(company, companyName);
  }

  @DeleteMapping("{companyName}")
  public Company deleteEmployee(@PathVariable String companyName) {
    return companyService.delete(companyName);
  }
}
