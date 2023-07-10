package academy.devdojo.springboot2essentials.repository;

import academy.devdojo.springboot2essentials.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Anime save Sucessful")
    public void saveAnimeSuccessful() {
        var requestedAnime = createAnime();
        var newAnime = this.animeRepository.save(requestedAnime);
        Assertions.assertThat(newAnime).isNotNull();
        Assertions.assertThat(newAnime.getId()).isNotNull();
        Assertions.assertThat(newAnime.getName()).isEqualTo(requestedAnime.getName());
    }


    private Anime createAnime() {
        return Anime.builder()
                .name("Yu-Gi-Oh!")
                .build();
    }

}