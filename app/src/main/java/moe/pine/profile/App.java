package moe.pine.profile;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        final String port = System.getenv("PORT");
        if (StringUtils.isNotEmpty(port)) {
            System.setProperty("server.port", port);
        }

        SpringApplication.run(App.class, args);
    }
}
