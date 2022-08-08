package com.logobootcamp.adminprovinceservice.ExceptionHandling;

import org.springframework.http.HttpStatus;

import java.sql.Date;

public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private Date date;

    public ErrorResponse(){}

    public ErrorResponse(String message, HttpStatus httpStatus, Date date) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
