package com.example.springboot.thymeleaf.sample.service;

import com.example.springboot.thymeleaf.sample.common.RestTemplateService;
import com.example.springboot.thymeleaf.sample.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public EmployeeList getEmployees(int page) {
        int startNum = 1;
        int limit = 6;
        int totalPage;

        EmployeeList pagingList = new EmployeeList();
        List<Employee> pagingEmployeeList = new ArrayList<>();

        EmployeeList employeeList = restTemplateService.send("/employees", HttpMethod.GET, null, EmployeeList.class);

        if (employeeList.getData().size() > 0) {
            if((employeeList.getData().size() % 5) == 0) {
                totalPage = (employeeList.getData().size() / 5);
            } else {
                totalPage = (employeeList.getData().size() / 5) + 1;
            }


            // 시작 번호
            // 1page -> 1 ~ 5, 2page -> 6 ~ 10, 3page -> 11 ~ 15
            if ((page != 1) && (page == totalPage)) {
                startNum = ((page - 1) * 5) + 1;
                limit = employeeList.getData().size() + 1;
            } else if (page != 1){
                startNum = ((page - 1) * 5) + 1;
                limit = startNum + 5;
            }

            for (int i = startNum; i < limit; i++) {
                pagingEmployeeList.add(employeeList.getData().get(i - 1));
            }

            pagingList.setData(pagingEmployeeList);
            pagingList.setTotalCnt(totalPage);
        }

        return pagingList;
    }

    public EmployeeInfo getEmployee(String id) {
        return restTemplateService.send("/employee/" + id, HttpMethod.GET, null, EmployeeInfo.class);
    }

    public NewEmployeeInfo createEmployee(NewEmployee employee) {
        return restTemplateService.send("/create", HttpMethod.POST, employee, NewEmployeeInfo.class);
    }
}
