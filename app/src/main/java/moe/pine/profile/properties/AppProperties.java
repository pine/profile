package moe.pine.profile.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app")
public class AppProperties {
    private String siteTitle;
    private String siteTitleEn;
}
