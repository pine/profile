package moe.pine.profile.controllers;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moe.pine.profile.models.Page;
import moe.pine.profile.services.AnimeService;
import moe.pine.profile.services.LayoutService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PageController {
    @Nonnull
    private final AnimeService animeService;

    @Nonnull
    private final LayoutService layoutService;

    @GetMapping("/")
    public ModelAndView index() {
        final var layout = layoutService.get(Page.INDEX);
        final var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("index", params);
    }

    @GetMapping("/anime")
    public ModelAndView anime() {
        final var layout = layoutService.get(Page.ANIME);
        final var animeGroups = animeService.getWatched();
        final var params = ImmutableMap.of(
            "layout", layout,
            "animeGroups", animeGroups
        );
        return new ModelAndView("anime", params);
    }

    @GetMapping("/light_novel")
    public ModelAndView lightNovel() {
        final var layout = layoutService.get(Page.LIGHT_NOVEL);
        final var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("light_novel", params);
    }

    @GetMapping("/link")
    public ModelAndView link() {
        final var layout = layoutService.get(Page.LINK);
        final var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("link", params);
    }

    @GetMapping("/mountain")
    public ModelAndView mountain() {
        final var layout = layoutService.get(Page.MOUNTAIN);
        final var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("mountain", params);
    }

    @GetMapping("/program")
    public ModelAndView program() {
        final var layout = layoutService.get(Page.PROGRAM);
        final var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("program", params);
    }

    @GetMapping("/site_info")
    public ModelAndView siteInfo() {
        final var layout = layoutService.get(Page.SITE_INFO);
        final var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("site_info", params);
    }
}
