package moe.pine.profile.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app")
public class AppProperties {
    private Site site;
    private Page page;

    @Data
    public static class Site {
        private String title;
        private String titleEn;
    }

    @Data
    public static class Page {
        private long maxAge;
    }
}
