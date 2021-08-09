package com.example.springboot.thymeleaf.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hrjin
 * @version 1.0
 * @since 2021.08.03
 **/
@Controller
public class GreetingController {

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "hrjin") String name, Model model) {
        model.addAttribute("name", name);
        return "contents/greeting";
    }
}
