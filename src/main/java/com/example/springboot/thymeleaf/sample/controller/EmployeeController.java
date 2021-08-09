package com.example.springboot.thymeleaf.sample.controller;

import com.example.springboot.thymeleaf.sample.model.EmployeeList;
import com.example.springboot.thymeleaf.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * ajax로 데이터 값 뿌려주는 화면
     *
     * @return
     */
    @GetMapping("/employees1")
    public String moveEmployeesPage1() {
        return "contents/employee/list1";
    }


    /**
     * 타임리프 태그 사용해서 데이터 값 뿌려주는 화면
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
     * 데이터 값 조회
     *
     * @return
     */
    @GetMapping("/emps")
    @ResponseBody
    public EmployeeList getEmployees() {
        return employeeService.getEmployees();
    }
}
