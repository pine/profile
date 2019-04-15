package moe.pine.profile.services;

import lombok.RequiredArgsConstructor;
import moe.pine.profile.models.ViewAnime;
import moe.pine.profile.models.ViewAnimeGroup;
import moe.pine.profile.properties.AnimeProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimeService {
    @Nonnull
    private final AnimeProperties animeProperties;

    @Nonnull
    public List<ViewAnimeGroup> getWatched() {
        final var watched = animeProperties.getWatched();

        return watched.entrySet()
            .stream()
            .filter(v -> !v.getValue().isEmpty())
            .sorted(Comparator.comparing(Map.Entry::getKey))
            .map(v -> {
                final var items = v.getValue()
                    .stream()
                    .map(ViewAnime::new)
                    .collect(Collectors.toUnmodifiableList());
                return new ViewAnimeGroup(v.getKey(), items);
            })
            .collect(Collectors.toUnmodifiableList());
    }
}
