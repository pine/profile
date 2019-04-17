package moe.pine.profile.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Page {
    INDEX("index", "/", "トップページ", "Top Page"),
    PROGRAM("program", "/program", "プログラミング", "Programming"),
    ANIME("anime", "/anime", "アニメ", "Anime"),
    LIGHT_NOVEL("light_novel", "/light_novel", "ライトノベル", "Light novel"),
    MOUNTAIN("mountain", "/mountain", "登山", "Mountain"),
    LINK("link", "/link", "リンク", "Link"),
    SITE_INFO("site_info", "/site_info", "サイト情報", "Site information"),
    ;

    @Getter
    private final String template;

    @Getter
    private final String path;

    @Getter
    private final String title;

    @Getter
    private final String titleEn;
}
