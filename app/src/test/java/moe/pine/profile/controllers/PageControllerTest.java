package moe.pine.profile.controllers;

import lombok.SneakyThrows;
import moe.pine.profile.models.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PageControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    public void indexTest() {
        mvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(Page.INDEX.getTitle())));
    }

    @Test
    @SneakyThrows
    public void animeTest() {
        mvc.perform(MockMvcRequestBuilders.get("/anime"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(Page.ANIME.getTitle())));
    }
}
