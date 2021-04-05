package moe.pine.profile.services;

import moe.pine.profile.models.Page;
import moe.pine.profile.models.ViewLayout;
import moe.pine.profile.models.ViewPage;
import moe.pine.profile.properties.AppProperties;
import moe.pine.profile.repositories.BackgroundRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LayoutServiceTest {
    @Mock
    private AppProperties appProperties;

    @Mock
    private BackgroundRepository backgroundRepository;

    @InjectMocks
    private LayoutService layoutService;

    @Test
    void getTest() {
        when(appProperties.getSite())
            .thenReturn(new AppProperties.Site("siteTitle", "siteTitleEn"));
        when(backgroundRepository.choose())
            .thenReturn("background.jpg");

        final ViewLayout actual = layoutService.get(Page.INDEX);
        assertEquals("siteTitle", actual.getSiteTitle());
        assertEquals("siteTitleEn", actual.getSiteTitleEn());
        assertEquals("background.jpg", actual.getBackground());

        assertEquals(Page.INDEX.getTitle(), actual.getPageTitle());
        assertEquals(Page.INDEX.getTitleEn(), actual.getPageTitleEn());

        final List<ViewPage> pages = actual.getPages();
        assertTrue(pages.contains(new ViewPage(Page.INDEX, true)));
        assertTrue(pages.contains(new ViewPage(Page.PROGRAM, false)));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void getTest_null() {
        assertThatThrownBy(() -> layoutService.get(null))
            .isInstanceOf(NullPointerException.class);
    }
}
