package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }

//    @ExceptionHandler(value = Exception.class)
//    public ResponseDto<String> handleException(Exception e) {
//
//        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                e.getMessage());
//    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<ResponseDto<String>> handleException(Exception e) {
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
