package moe.pine.profile.models;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ViewPageTest {
    @Test
    void constructorTest() {
        final Page page = Page.INDEX;
        final ViewPage viewPage = new ViewPage(page, true);

        assertEquals(page.getPath(), viewPage.getPath());
        assertEquals(page.getTitle(), viewPage.getTitle());
        assertEquals(page.getTitleEn(), viewPage.getTitleEn());
        assertTrue(viewPage.isActive());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void constructorTest_null() {
        assertThatThrownBy(() -> new ViewPage(null, true))
            .isInstanceOf(NullPointerException.class);
    }
}
