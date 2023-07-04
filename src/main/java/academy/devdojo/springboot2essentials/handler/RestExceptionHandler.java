package academy.devdojo.springboot2essentials.handler;


import academy.devdojo.springboot2essentials.exception.BadRequestException;
import academy.devdojo.springboot2essentials.exception.BadRequestExceptionModel;
import academy.devdojo.springboot2essentials.exception.MethodArgumentNotValidExceptionModel;
import academy.devdojo.springboot2essentials.exception.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionModel> handlerBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                BadRequestExceptionModel.builder()
                        .title("Bad Request Exception, Check the documentation")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        var errors = fieldErrors.stream()
                .map(error ->
                        MethodArgumentNotValidExceptionModel
                                .builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .build());

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Bad Request Exception, Check the documentation")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details("Check the Field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .errors(errors.collect(Collectors.toList()))
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}
