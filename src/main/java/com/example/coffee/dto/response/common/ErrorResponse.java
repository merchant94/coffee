package com.example.coffee.dto.response.common;

import com.example.coffee.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;
    private String message;
    private List<FieldError> errors;

//    private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
//        this.status = code.getHttpStatusCode().value();
//        this.message = code.getMessage();
//        this.errors = errors;
//    }
//
//    private ErrorResponse(final ErrorCode code){
//
//    }
//
//    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult){
//        return new ErrorResponse(code, FieldError.of(bindingResult));
//    }
//    public static ErrorResponse of(final ErrorCode code) {
//        return new ErrorResponse(code);
//    }
//
//    public static ErrorResponse of(NotFoundException e){
//        final List<ErrorResponse.FieldError> errors = FieldError.of("Repository",e.getMessage(),e);
//    }
//
//
//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    public static class FieldError {
//        private String field;
//        private String value;
//        private String reason;
//
//        private FieldError(final String field,final String value, final String reason){
//            this.field = field;
//            this.value = value;
//            this.reason = reason;
//        }
//
//        public static List<FieldError> of(final String field, final String value, final String reason){
//            List<FieldError> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldError(field, value, reason));
//            return fieldErrors;
//        }
//
//        public static List<FieldError> of(final BindingResult bindingResult) {
//            final List<org.springframework.validation.FieldError> fieldErrors = new bindingResult.getFieldErrors();
//            return fieldErrors.stream()
//                    .map(error -> new FieldError(
//                            error.getField(),
//                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
//                            error.getDefaultMessage()))
//                    .collect(Collectors.toList());
//        }
//
//    }
}
