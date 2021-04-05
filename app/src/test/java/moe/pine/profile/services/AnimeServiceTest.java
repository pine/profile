package moe.pine.profile.services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import moe.pine.profile.models.ViewAnime;
import moe.pine.profile.models.ViewAnimeGroup;
import moe.pine.profile.properties.AnimeProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
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
            ImmutableMap.of(
                "2018", ImmutableList.of("minamike", "madomagi"),
                "2019", ImmutableList.of("wataten"),
                "2020", Collections.emptyList()
            );

        when(animeProperties.getWatched()).thenReturn(animes);

        final List<ViewAnimeGroup> expected =
            ImmutableList.of(
                new ViewAnimeGroup("2018",
                    ImmutableList.of(
                        new ViewAnime("minamike"),
                        new ViewAnime("madomagi")
                    )),
                new ViewAnimeGroup("2019",
                    ImmutableList.of(
                        new ViewAnime("wataten")
                    ))
            );
        final List<ViewAnimeGroup> actual = animeService.getWatched();

        assertEquals(expected, actual);

        verify(animeProperties).getWatched();
    }
}
