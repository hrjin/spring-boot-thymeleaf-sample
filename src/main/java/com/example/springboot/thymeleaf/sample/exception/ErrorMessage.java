package com.example.springboot.thymeleaf.sample.exception;

import lombok.Data;

/**
 * Error Message Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2021.08.08
 **/
@Data
public class ErrorMessage {
    // REST API 호출 시 에러
    private int httpStatusCode;
    private String resultMessage;

    public ErrorMessage(int httpStatusCode, String detailMessage) {
        this.httpStatusCode = httpStatusCode;
        this.resultMessage = detailMessage;
    }

}
