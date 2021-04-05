package moe.pine.profile.controllers;

import moe.pine.profile.models.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class PageControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void indexTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(Page.INDEX.getTitle())));
    }

    @Test
    void animeTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/anime"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(Page.ANIME.getTitle())));
    }

    @Test
    void mountainTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/mountain"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(Page.MOUNTAIN.getTitle())));
    }
}
