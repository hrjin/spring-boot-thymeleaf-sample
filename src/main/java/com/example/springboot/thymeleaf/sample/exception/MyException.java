package com.example.springboot.thymeleaf.sample.exception;

/**
 * Base Biz Exception Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2021.08.10
 **/
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1032826776466587212L;

    private String errorCode;
    private String errorMessage;
    private int statusCode;
    private String detailMessage;

    public MyException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public MyException(int statusCode, String detailMessage) {
        super(detailMessage);
        this.statusCode = statusCode;
        this.detailMessage = detailMessage;
    };

    public MyException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode(){
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

}
