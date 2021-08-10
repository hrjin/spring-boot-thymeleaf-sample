package com.example.springboot.thymeleaf.sample.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

/**
 * GlobalException Handler 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2021.08.08
 **/
@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static String IMAGE_PATH = "/images/error/error.jpg";

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseBody
    public ErrorMessage handleException(HttpClientErrorException ex) {
        LOGGER.info("HttpClientErrorException >>> " + ex.getStatusText());
        return new ErrorMessage(ex.getRawStatusCode(), ex.getResponseBodyAsString());
    }

    @ExceptionHandler({Exception.class})
    public ErrorMessage handleAll(final Exception ex) {
        LOGGER.info("Exception >>> {}", ex.getLocalizedMessage());
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ErrorMessage handleException(HttpMessageNotReadableException ex) {
        return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getLocalizedMessage());
    }


    @ExceptionHandler({NullPointerException.class})
    public String nullException(NullPointerException ex, Model model) {
        LOGGER.info("NullPointerException >>> " + ex);
        model.addAttribute("src", IMAGE_PATH);
        model.addAttribute("msg", HttpStatus.NOT_FOUND.value() + " : " + ex.getMessage());
        return "error/error";
    }

    @ExceptionHandler({MyException.class})
    public String myException(MyException ex, Model model) {
        LOGGER.info("MyException >>> " + ex);
        model.addAttribute("src", IMAGE_PATH);
        model.addAttribute("msg", ex.getStatusCode() + " : " + ex.getDetailMessage());
        return "error/error";
    }
}
