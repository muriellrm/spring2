package academy.devdojo.springboot2essentials.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class MethodArgumentNotValidExceptionModel {
    private String field;
    private String message;
}
