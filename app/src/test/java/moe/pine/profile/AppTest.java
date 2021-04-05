package moe.pine.profile;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@SuppressWarnings({"unused", "NotNullFieldNotInitialized"})
class AppTest {
    @Autowired
    private App app;

    @Test
    void mainTest() {
        assertNotNull(app);
    }
}
