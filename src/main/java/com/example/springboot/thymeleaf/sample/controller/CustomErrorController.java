package com.example.springboot.thymeleaf.sample.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author hrjin
 * @version 1.0
 * @since 2021.08.08
 **/
@Controller
public class CustomErrorController implements ErrorController {

    private static String IMAGE_PATH = "images/error/";

    @ExceptionHandler(Throwable.class)
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String statusMsg = status.toString();

        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(statusMsg));

        model.addAttribute("msg", statusMsg + " " + httpStatus.getReasonPhrase());
        model.addAttribute("src", IMAGE_PATH + statusMsg + ".jpg");

        return "error/error";
    }
}
