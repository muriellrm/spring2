package academy.devdojo.springboot2essentials.handler;


import academy.devdojo.springboot2essentials.exception.BadRequestException;
import academy.devdojo.springboot2essentials.exception.BadRequestExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionModel> handlerBadRequestException (BadRequestException bre){
        return new ResponseEntity<>(
                BadRequestExceptionModel.builder()
                        .title("Bad Request Exception, Check the documentation")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(),HttpStatus.BAD_REQUEST
        );
    }
}
