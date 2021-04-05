package moe.pine.profile.services;

import moe.pine.profile.models.ViewAnime;
import moe.pine.profile.models.ViewAnimeGroup;
import moe.pine.profile.properties.AnimeProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimeServiceTest {
    @Mock
    private AnimeProperties animeProperties;

    @InjectMocks
    private AnimeService animeService;

    @Test
    void getWatchedTest() {
        final Map<String, List<String>> animes =
            Map.of(
                "2018", List.of("minamike", "madomagi"),
                "2019", List.of("wataten"),
                "2020", List.of()
            );

        when(animeProperties.getWatched()).thenReturn(animes);

        final List<ViewAnimeGroup> expected =
            List.of(
                new ViewAnimeGroup("2018",
                    List.of(
                        new ViewAnime("minamike"),
                        new ViewAnime("madomagi")
                    )),
                new ViewAnimeGroup("2019",
                    List.of(
                        new ViewAnime("wataten")
                    ))
            );
        final List<ViewAnimeGroup> actual = animeService.getWatched();

        assertEquals(expected, actual);

        verify(animeProperties).getWatched();
    }
}
