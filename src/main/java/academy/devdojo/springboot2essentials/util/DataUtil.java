package academy.devdojo.springboot2essentials.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataUtil {
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime data) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(data);
    }
}
