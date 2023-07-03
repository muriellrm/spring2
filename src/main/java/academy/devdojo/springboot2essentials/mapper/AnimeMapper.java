package academy.devdojo.springboot2essentials.mapper;

import academy.devdojo.springboot2essentials.domain.Anime;
import academy.devdojo.springboot2essentials.models.AnimePostRequest;
import academy.devdojo.springboot2essentials.models.AnimePutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimePutRequest animePutRequest);
    public abstract Anime toAnime(AnimePostRequest animePostRequest);
}
