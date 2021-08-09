package com.example.springboot.thymeleaf.sample.model;

import lombok.Data;

/**
 * @author hrjin
 * @version 1.0
 * @since 2021.08.09
 **/
@Data
public class EmployeeInfo {
    private String status;
    private Employee data;
}
