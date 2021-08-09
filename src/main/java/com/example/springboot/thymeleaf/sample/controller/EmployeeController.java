package com.example.springboot.thymeleaf.sample.controller;

import com.example.springboot.thymeleaf.sample.model.EmployeeInfo;
import com.example.springboot.thymeleaf.sample.model.EmployeeList;
import com.example.springboot.thymeleaf.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hrjin
 * @version 1.0
 * @since 2021.08.06
 **/
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 직원 목록 조회 페이지(ajax)
     *
     * @return
     */
    @GetMapping("/employees1")
    public String moveEmployeesPage1() {
        return "contents/employee/list1";
    }


    /**
     * 직원 목록 조회 페이지(타임리프 태그 사용)
     *
     * @param model
     * @return
     */
    @GetMapping("/employees2")
    public String moveEmployeesPage2(Model model) {
        model.addAttribute("employeeList", employeeService.getEmployees());
        return "contents/employee/list2";
    }


    /**
     * 직원 목록 조회
     *
     * @return
     */
    @GetMapping("/emps")
    @ResponseBody
    public EmployeeList getEmployees() {
        return employeeService.getEmployees();
    }


    /**
     * 직원 상세 정보 조회 및 페이지 이동
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/employees/{id:.+}")
    public String moveEmployeeInfoPage(@PathVariable String id, Model model) {
        EmployeeInfo employeeInfo = employeeService.getEmployee(id);
        model.addAttribute("employee", employeeInfo.getData());
        return "contents/employee/info";
    }
}
