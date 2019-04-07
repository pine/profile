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

@Controller
@RequiredArgsConstructor
@Slf4j
public class PageController {
    private final AnimeService animeService;
    private final LayoutService layoutService;

    @GetMapping("/")
    public ModelAndView index() {
        var layout = layoutService.get(Page.INDEX);
        var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("index", params);
    }

    @GetMapping("/anime")
    public ModelAndView anime() {
        var layout = layoutService.get(Page.ANIME);
        var animeGroups = animeService.getWatched();
        var params = ImmutableMap.of(
            "layout", layout,
            "animeGroups", animeGroups
        );
        return new ModelAndView("anime", params);
    }

    @GetMapping("/light_novel")
    public ModelAndView lightNovel() {
        var layout = layoutService.get(Page.LIGHT_NOVEL);
        var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("light_novel", params);
    }

    @GetMapping("/link")
    public ModelAndView link() {
        var layout = layoutService.get(Page.LINK);
        var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("link", params);
    }

    @GetMapping("/mountain")
    public ModelAndView mountain() {
        var layout = layoutService.get(Page.MOUNTAIN);
        var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("mountain", params);
    }

    @GetMapping("/program")
    public ModelAndView program() {
        var layout = layoutService.get(Page.PROGRAM);
        var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("program", params);
    }

    @GetMapping("/site_info")
    public ModelAndView siteInfo() {
        var layout = layoutService.get(Page.SITE_INFO);
        var params = ImmutableMap.of("layout", layout);
        return new ModelAndView("site_info", params);
    }
}
