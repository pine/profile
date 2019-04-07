package moe.pine.profile.models;

import lombok.Value;

@Value
public class ViewPage {
    private String path;
    private String title;
    private String titleEn;
    private boolean active;

    public ViewPage(final Page page, final boolean active) {
        this.path = page.getPath();
        this.title = page.getTitle();
        this.titleEn = page.getTitleEn();
        this.active = active;
    }
}
