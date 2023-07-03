package academy.devdojo.springboot2essentials.service;

import academy.devdojo.springboot2essentials.domain.Anime;
import academy.devdojo.springboot2essentials.mapper.AnimeMapper;
import academy.devdojo.springboot2essentials.models.AnimePostRequest;
import academy.devdojo.springboot2essentials.models.AnimePutRequest;
import academy.devdojo.springboot2essentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findOrThrowException(Long id) {
        return animeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
    }

    public Anime save(AnimePostRequest animePostRequest) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequest));
    }

    public void delete(Long id) {
        animeRepository.delete(findOrThrowException(id));
    }

    public void update(AnimePutRequest animePutRequest) {
        findOrThrowException(animePutRequest.getId());
        animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePutRequest));
    }
}