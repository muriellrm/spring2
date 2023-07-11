package academy.devdojo.springboot2essentials.repository;

import academy.devdojo.springboot2essentials.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Create new Anime and presists is sucessful")
    public void saveAnimeSuccessful() {
        var requestedAnime = createAnime();
        var newAnime = this.animeRepository.save(requestedAnime);
        Assertions.assertThat(newAnime).isNotNull();
        Assertions.assertThat(newAnime.getId()).isNotNull();
        Assertions.assertThat(newAnime.getName()).isEqualTo(requestedAnime.getName());
    }

    @Test
    @DisplayName("Update existent anime")
    public void updateExistentAnime() {
        var requestedAnime = createAnime();
        var newAnime = this.animeRepository.save(requestedAnime);

        newAnime.setName("Dbz");

        var updatedAnime = this.animeRepository.save(newAnime);

        Assertions.assertThat(updatedAnime).isNotNull();
        Assertions.assertThat(updatedAnime.getId()).isNotNull();
        Assertions.assertThat(updatedAnime.getName()).isEqualTo(newAnime.getName());
    }

    @Test
    @DisplayName("Delete existent Anime Sucessful")
    public void deleteExistentAnime() {
        var requestedAnime = createAnime();
        var newAnime = this.animeRepository.save(requestedAnime);

        this.animeRepository.delete(requestedAnime);

        var animeOptional = this.animeRepository.findById(newAnime.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by name return an Anime List")
    public void findByName() {
        var requestedAnime = createAnime();
        var newAnime = this.animeRepository.save(requestedAnime);
        var animes = this.animeRepository.findByName(newAnime.getName());

        Assertions.assertThat(animes)
                .isNotEmpty()
                .contains(newAnime);
    }

    @Test
    @DisplayName("Find by name return an Empty List when no Anime found")
    public void findByNameEmptyList() {
        var animes = this.animeRepository.findByName("test");

        Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("Throw ConstraintViolationException when anime name is empty")
    public void saveAnimeThrowException() {
        var anime = new Anime();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("The anime name cannot be empty");

    }


    private Anime createAnime() {
        return Anime.builder()
                .name("Yu-Gi-Oh!")
                .build();
    }

}