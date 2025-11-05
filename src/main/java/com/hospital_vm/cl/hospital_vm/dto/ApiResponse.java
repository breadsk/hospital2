package com.hospital_vm.cl.hospital_vm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean ok;
    private Integer statusCode;
    private String message;
    private T data;
    private Long count;

    // constructores
    public ApiResponse() {
    }

    public ApiResponse(boolean ok, Integer statusCode, String message, T data) {
        this.ok = ok;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(boolean ok, Integer statusCode, String message, T data, Long count) {
        this.ok = ok;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    // Getters y Setters
    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
