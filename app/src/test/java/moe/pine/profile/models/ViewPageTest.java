package moe.pine.profile.models;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ViewPageTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void constructorTest() {
        final Page page = Page.INDEX;
        final ViewPage viewPage = new ViewPage(page, true);

        assertEquals(page.getPath(), viewPage.getPath());
        assertEquals(page.getTitle(), viewPage.getTitle());
        assertEquals(page.getTitleEn(), viewPage.getTitleEn());
        assertTrue(viewPage.isActive());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void constructorTest_null() {
        expectedException.expect(NullPointerException.class);
        new ViewPage(null, true);
    }
}
