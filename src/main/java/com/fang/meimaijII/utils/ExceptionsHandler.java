package com.fang.meimaijII.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fang.meimaijII.enums.ResponseCode;

@ControllerAdvice
public class ExceptionsHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ( (FieldError) error ).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return ResponseEntity.badRequest().body(new ResponseSpec(ResponseCode.INPUT_ISSUE.getCode(), ResponseCode.INPUT_ISSUE.getMessage(), errors));
    }

    @ExceptionHandler(MeimaijIIException.class)
    public ResponseEntity<Object> handleMeimaijIIException(MeimaijIIException e){
//        return ResponseEntity.badRequest().body(new ResponseSpec(ResponseCode.LOGIC_ISSUE.getCode(), e.getMessage()));
        return ResponseEntity.badRequest().body(new ResponseSpec(ResponseCode.LOGIC_ISSUE.getCode(),ResponseCode.LOGIC_ISSUE.getMessage(), e.getMessage()));

    }
}
