package moe.pine.profile.services;

import moe.pine.profile.models.Page;
import moe.pine.profile.models.ViewLayout;
import moe.pine.profile.models.ViewPage;
import moe.pine.profile.properties.AppProperties;
import moe.pine.profile.repositories.BackgroundRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LayoutServiceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private AppProperties appProperties;

    @Mock
    private BackgroundRepository backgroundRepository;

    @InjectMocks
    private LayoutService layoutService;

    @Test
    public void getTest() {
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
    public void getTest_null() {
        expectedException.expect(NullPointerException.class);

        //noinspection ConstantConditions
        layoutService.get(null);
    }
}
