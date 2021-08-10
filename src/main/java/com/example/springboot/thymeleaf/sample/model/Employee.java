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
    private int employee_salary;
    private int employee_age;
}
