package academy.devdojo.springboot2essentials.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimePostRequest {
    @NotEmpty(message = "The anime name cannot be empty")
    private String name;
}
