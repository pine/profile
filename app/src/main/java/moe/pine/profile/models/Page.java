package moe.pine.profile.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Page {
    INDEX("/", "トップページ", "Top Page"),
    PROGRAM("/program", "プログラミング", "Programming"),
    ANIME("/anime", "アニメ", "Anime"),
    LIGHT_NOVEL("/light_novel", "ライトノベル", "Light novel"),
    MOUNTAIN("/mountain", "登山", "Mountain"),
    LINK("/link", "リンク", "Link"),
    SITE_INFO("/site_info", "サイト情報", "Site information"),
    ;

    @Getter
    private final String path;

    @Getter
    private final String title;

    @Getter
    private final String titleEn;
}
