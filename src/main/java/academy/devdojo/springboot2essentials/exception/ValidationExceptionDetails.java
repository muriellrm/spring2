package academy.devdojo.springboot2essentials.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private List<FieldsExceptionModel> errors;
}
