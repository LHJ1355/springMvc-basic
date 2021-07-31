package hello.springmvc.exception.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//exception resolver에 의해 수행되나 일반적인 컨트롤러 처럼 viewName, ResponseEntity 등을 return 할 수 있음
//ControllerAdivce 범위
//@ControllerAdvice(annotations = RestController.class)
//@ControllerAdvice(assignableTypes = {AbstractController.class})
@ControllerAdvice("hello.springmvc.controller")
public class ExControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResult> IllegalArgumentExceptionHandle(IllegalArgumentException e) {
        return new ResponseEntity<>(new ErrorResult("IllegalArgumentException", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class, NullPointerException.class})
    public ResponseEntity<ErrorResult> RuntimeExceptionHandle(Exception e){
        return new ResponseEntity<>(new ErrorResult("RuntimeException", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResult ExceptionHandle(Exception e) {
        return new ErrorResult("Exception", e.getMessage());
    }


    @Data
    @AllArgsConstructor
    static class ErrorResult {
        private String ex;
        private String message;
    }
}
