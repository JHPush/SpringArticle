package com.example.articleapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @ControllerAdvice - API 서버가 아닌경우 사용 + @ResponseBody 합쳐진게 RestContollerAdvice
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, Exception ex) {
        log.error("URI : {}, method : {}", req.getRequestURI(), req.getMethod());
        log.error("error : {} ", ex.getMessage());

        ErrorResponse res = ErrorResponse.builder()
                .code("500")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.ok().body(res);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, ArticleNotFoundException ex) {
        log.error("URI : {}, method : {}", req.getRequestURI(), req.getMethod());
        log.error("error : {} ", ex.getMessage());

        ErrorResponse res = ErrorResponse.builder()
                .code("400")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.ok().body(res);
    }

    // 유효성 검증 Exce
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest req, MethodArgumentNotValidException ex) {
        log.error("Exception occured at URL : [{}] : {}", req.getMethod(), req.getRequestURL());
        log.error("Error : {}", ex.getMessage());

        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(filedError -> {
            sb.append("[")
                    .append(filedError.getField())
                    .append("] : ")
                    .append(filedError.getDefaultMessage())
                    .append(", Provided Value : [ ")
                    .append(filedError.getRejectedValue())
                    .append("]")
                    .append("\n");
        });

        ErrorResponse errRes = ErrorResponse.builder().code("400").message(sb.toString()).build();
        // return ResponseEntity.ok().body(errRes);
        return new ResponseEntity<>(errRes, HttpStatus.OK);
    }
}
