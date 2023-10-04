package com.example.blog.springbootblogrestapi.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends Throwable {

    private HttpStatus status;
    private String message;
    public BlogAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;

    }

    public BlogAPIException(HttpStatus status, String message,String message1) {
        this.status = status;
        this.message = message;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
