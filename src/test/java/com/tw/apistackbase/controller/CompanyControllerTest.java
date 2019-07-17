package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private CompanyService service;

  @Test
  void should_return_company_find_by_company_name() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    Company company = new Company("aaa", 10, Arrays.asList(employee));
    when(service.findByCompanyName(anyString())).thenReturn(company);

    ResultActions result = mvc.perform(get("/companies/{companyName}", company.getCompanyName()));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.employeesNumber", is(10)));
  }

  @Test
  void should_return_company_add_company() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    Company company = new Company("aaa", 10, Arrays.asList(employee));
    when(service.add(any())).thenReturn(company);
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("companyName", "aaa");

    ResultActions result = mvc.perform(post("/companies")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonObject.toString()));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.companyName", is("aaa")));
  }

  @Test
  void should_return_company_delete_company() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    Company company = new Company("aaa", 10, Arrays.asList(employee));
    when(service.delete(anyString())).thenReturn(company);

    ResultActions result = mvc.perform(delete("/companies/{companyName}", company.getCompanyName()));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.companyName", is("aaa")));
  }

}