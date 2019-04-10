package moe.pine.profile.services;

import lombok.RequiredArgsConstructor;
import moe.pine.profile.models.Page;
import moe.pine.profile.models.ViewLayout;
import moe.pine.profile.models.ViewPage;
import moe.pine.profile.properties.AppProperties;
import moe.pine.profile.repositories.BackgroundRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@RequiredArgsConstructor
@Service
public class LayoutService {
    @Nonnull
    private final AppProperties appProperties;

    @Nonnull
    private final BackgroundRepository backgroundRepository;

    @Nonnull
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public ViewLayout get(@Nonnull final Page page) {
        checkNotNull(page);

        final List<ViewPage> pages =
            Arrays.stream(Page.values())
                .map(v -> new ViewPage(v, v == page))
                .collect(Collectors.toUnmodifiableList());

        return ViewLayout.builder()
            .siteTitle(appProperties.getSite().getTitle())
            .siteTitleEn(appProperties.getSite().getTitleEn())
            .background(backgroundRepository.choose())
            .pageTitle(page.getTitle())
            .pageTitleEn(page.getTitleEn())
            .pages(pages)
            .build();
    }
}
