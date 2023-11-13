package com.example.coffee.dto.common;

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

    public CommonResponse(T data) {
        this.status = 200;
        this.message = "OK";
        this.data = data;
    }

}
