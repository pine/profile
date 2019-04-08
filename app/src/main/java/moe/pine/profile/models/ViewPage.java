package moe.pine.profile.models;

import lombok.Value;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

@Value
public class ViewPage {
    private String path;
    private String title;
    private String titleEn;
    private boolean active;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public ViewPage(@Nonnull final Page page, final boolean active) {
        checkNotNull(page);

        this.path = page.getPath();
        this.title = page.getTitle();
        this.titleEn = page.getTitleEn();
        this.active = active;
    }
}
