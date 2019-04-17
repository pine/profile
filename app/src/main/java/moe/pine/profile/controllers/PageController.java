package moe.pine.profile.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moe.pine.profile.models.Page;
import moe.pine.profile.models.PageAttributes;
import moe.pine.profile.services.AnimeService;
import moe.pine.profile.services.LayoutService;
import moe.pine.profile.services.MountainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Nonnull;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PageController {
    @Nonnull
    private final AnimeService animeService;

    @Nonnull
    private final LayoutService layoutService;

    @Nonnull
    private final MountainService mountainService;

    @GetMapping("/")
    public String index(final Model model) {
        final var layout = layoutService.get(Page.INDEX);
        model.addAttribute(PageAttributes.LAYOUT, layout);
        return Page.INDEX.getTemplate();
    }

    @GetMapping("/anime")
    public String anime(final Model model) {
        final var layout = layoutService.get(Page.ANIME);
        final var animeGroups = animeService.getWatched();
        model.addAttribute(PageAttributes.LAYOUT, layout);
        model.addAttribute(PageAttributes.ANIME_GROUPS, animeGroups);
        return Page.ANIME.getTemplate();
    }

    @GetMapping("/light_novel")
    public String lightNovel(final Model model) {
        final var layout = layoutService.get(Page.LIGHT_NOVEL);
        model.addAttribute(PageAttributes.LAYOUT, layout);
        return Page.LIGHT_NOVEL.getTemplate();
    }

    @GetMapping("/link")
    public String link(final Model model) {
        final var layout = layoutService.get(Page.LINK);
        model.addAttribute(PageAttributes.LAYOUT, layout);
        return Page.LINK.getTemplate();
    }

    @GetMapping("/mountain")
    public String mountain(final Model model) {
        final var layout = layoutService.get(Page.MOUNTAIN);
        final var mountains = mountainService.getClimbed();
        model.addAttribute(PageAttributes.LAYOUT, layout);
        model.addAttribute(PageAttributes.MOUNTAINS, mountains);
        return Page.MOUNTAIN.getTemplate();
    }

    @GetMapping("/program")
    public String program(final Model model) {
        final var layout = layoutService.get(Page.PROGRAM);
        model.addAttribute(PageAttributes.LAYOUT, layout);
        return Page.PROGRAM.getTemplate();
    }

    @GetMapping("/site_info")
    public String siteInfo(final Model model) {
        final var layout = layoutService.get(Page.SITE_INFO);
        model.addAttribute(PageAttributes.LAYOUT, layout);
        return Page.SITE_INFO.getTemplate();
    }
}
