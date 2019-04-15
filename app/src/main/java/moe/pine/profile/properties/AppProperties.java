package moe.pine.profile.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app")
public class AppProperties {
    private Site site;
    private Page page;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Site {
        private String title;
        private String titleEn;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Page {
        private long maxAge;
    }
}
