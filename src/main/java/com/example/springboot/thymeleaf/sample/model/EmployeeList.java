package com.example.springboot.thymeleaf.sample.model;

import lombok.Data;
import java.util.List;

/**
 * @author hrjin
 * @version 1.0
 * @since 2021.08.08
 **/
@Data
public class EmployeeList {
    private String status;
    private List<Employee> data;
    private String message;

    private int totalCnt;
}
