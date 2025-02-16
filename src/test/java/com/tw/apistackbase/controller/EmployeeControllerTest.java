package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private EmployeeService service;

  @Test
  void shoule_return_employee_find_by_id() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    when(service.findById(anyInt())).thenReturn(employee);

    ResultActions result = mvc.perform(get("/employees/{employeeId}", employee.getId()));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Joi")))
        .andExpect(jsonPath("$.age", is(22)))
        .andExpect(jsonPath("$.gender", is("female")));
  }

  @Test
  void shoule_return_employee_delete() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    when(service.delete(anyInt())).thenReturn(employee);

    ResultActions result = mvc.perform(delete("/employees/{employeeId}", employee.getId()));

    result.andExpect(status().isOk());
  }

  @Test
  void shoule_return_employees_find_all() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    Employee employee1 = new Employee(2, "Jo", 20, "female");
    Employee employee2 = new Employee(3, "J", 21, "male");
    when(service.findAll()).thenReturn(Arrays.asList(employee, employee1, employee2));

    ResultActions result = mvc.perform(get("/employees"));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].name", is("Joi")))
        .andExpect(jsonPath("$.[1].name", is("Jo")))
        .andExpect(jsonPath("$.[2].name", is("J")));
  }

  @Test
  void shoule_return_employees_find_by_page_and_pageSize() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    Employee employee1 = new Employee(2, "Jo", 20, "female");
    Employee employee2 = new Employee(3, "J", 21, "male");
    when(service.findByPageAndPageSize(anyInt(), anyInt())).thenReturn(Arrays.asList(employee, employee1, employee2));

    ResultActions result = mvc.perform(get("/employees?page={page}&pageSize={pageSize}", 1, 1));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].age", is(22)))
        .andExpect(jsonPath("$.[1].age", is(20)))
        .andExpect(jsonPath("$.[2].age", is(21)));
  }

  @Test
  void shoule_return_employees_find_by_gender() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    Employee employee1 = new Employee(2, "Jo", 20, "female");
    when(service.findByGender(anyString())).thenReturn(Arrays.asList(employee, employee1));

    ResultActions result = mvc.perform(get("/employees?gender={gender}", "female"));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].gender", is("female")))
        .andExpect(jsonPath("$.[1].gender", is("female")));
  }

  @Test
  void should_return_employee_add_employee() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    when(service.add(any())).thenReturn(employee);
    ObjectMapper mapper = new ObjectMapper();
    String employeeJson = mapper.writeValueAsString(employee);

    ResultActions result = mvc.perform(post("/employees")
        .contentType(MediaType.APPLICATION_JSON)
        .content(employeeJson));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Joi")))
        .andExpect(jsonPath("$.age", is(22)))
        .andExpect(jsonPath("$.gender", is("female")));
  }

  @Test
  void should_return_employee_update_employee() throws Exception {
    Employee employee = new Employee(1, "Joi", 22, "female");
    when(service.update(any(), anyInt())).thenReturn(employee);
    ObjectMapper mapper = new ObjectMapper();
    String employeeJson = mapper.writeValueAsString(employee);

    ResultActions result = mvc.perform(put("/employees/{employeeId}", 1)
        .contentType(MediaType.APPLICATION_JSON)
        .content(employeeJson));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Joi")))
        .andExpect(jsonPath("$.age", is(22)))
        .andExpect(jsonPath("$.gender", is("female")));
  }


}