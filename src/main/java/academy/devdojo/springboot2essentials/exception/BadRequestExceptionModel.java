package academy.devdojo.springboot2essentials.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionModel {

    private String title;
    private Integer status;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;

}
