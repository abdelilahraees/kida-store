package com.kida.kidastore.shared;

import lombok.Getter;

import java.util.List;

@Getter
public class GlobalResponse<T> {
//    private final static String SUCCESS = "success";
//    private final static String ERROR = "error";

    private GloabalResponseStatus status;
    private List<ErrorMessage> errors;
    private T data;


    public GlobalResponse(List<ErrorMessage> errors) {
        this.status = GloabalResponseStatus.ERROR;
        this.errors = errors;
        this.data = null;
    }

    public GlobalResponse(T data) {
        this.status = GloabalResponseStatus.SUCCESS;
        this.errors = null;
        this.data = data;
    }


}
