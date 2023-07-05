package academy.devdojo.springboot2essentials.service;

import academy.devdojo.springboot2essentials.domain.Anime;
import academy.devdojo.springboot2essentials.exception.BadRequestException;
import academy.devdojo.springboot2essentials.mapper.AnimeMapper;
import academy.devdojo.springboot2essentials.models.AnimePostRequest;
import academy.devdojo.springboot2essentials.models.AnimePutRequest;
import academy.devdojo.springboot2essentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> findAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findOrThrowException(Long id) {
        return animeRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException( "Anime not Found"));
    }

    @Transactional
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
