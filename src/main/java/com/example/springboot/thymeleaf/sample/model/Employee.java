package com.example.springboot.thymeleaf.sample.model;

import lombok.Data;

/**
 * @author hrjin
 * @version 1.0
 * @since 2021.08.08
 **/
@Data
public class Employee {
    private String id;
    private String employee_name;
    private String employee_salary;
    private String employee_age;
}
