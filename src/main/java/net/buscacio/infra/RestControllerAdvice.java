package net.buscacio.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestControllerAdvice {

     @ExceptionHandler(IllegalArgumentException.class)
     public ResponseEntity<String> handleIllegalException(IllegalArgumentException ex) {
         log.error(ex.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
     }
}
