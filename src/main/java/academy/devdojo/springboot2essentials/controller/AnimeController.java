package academy.devdojo.springboot2essentials.controller;

import academy.devdojo.springboot2essentials.domain.Anime;
import academy.devdojo.springboot2essentials.util.DataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("anime")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final DataUtil dataUtil;
    @GetMapping(path="list")
    public List<Anime> getAnimeList() {
        log.info(dataUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new Anime("DB Super"), new Anime("Naruto"));
    }

}
