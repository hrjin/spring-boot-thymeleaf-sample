package com.example.springboot.thymeleaf.sample.service;

import com.example.springboot.thymeleaf.sample.common.RestTemplateService;
import com.example.springboot.thymeleaf.sample.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * @author hrjin
 * @version 1.0
 * @since 2021.08.06
 **/
@Service
public class EmployeeService {

    private final RestTemplateService restTemplateService;

    @Autowired
    public EmployeeService(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    public EmployeeList getEmployees() {
        return restTemplateService.send("/employees", HttpMethod.GET, null, EmployeeList.class);
    }

    public EmployeeInfo getEmployee(String id) {
        return restTemplateService.send("/employee/" + id, HttpMethod.GET, null, EmployeeInfo.class);
    }

    public NewEmployeeInfo createEmployee(NewEmployee employee) {
        return restTemplateService.send("/create", HttpMethod.POST, employee, NewEmployeeInfo.class);
    }
}
