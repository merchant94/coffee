package com.example.coffee.dto.response.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse<T> {

    private int status;
    private String message;
    private T data;

//    public CommonResponse(HttpStatus status, String message, T data) {
//        this.status = status;
//        this.message = message;
//        this.data = data;
//    }
//
//    public CommonaResponse(HttpStatus status, T data) {
//        this.status = status.value();
//        this.message =  status.getReasonPhrase();
//        this.data = data;
//    }
    public CommonResponse(T data) {
        this.status = 200;
        this.message = "OK";
        this.data = data;
    }

}
