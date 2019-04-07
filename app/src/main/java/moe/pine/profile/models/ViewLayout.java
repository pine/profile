package moe.pine.profile.models;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ViewLayout {
    private String siteTitle;
    private String siteTitleEn;
    private String background;

    private String pageTitle;
    private String pageTitleEn;
    private List<ViewPage> pages;
}
