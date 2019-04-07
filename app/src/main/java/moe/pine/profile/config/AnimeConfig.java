package moe.pine.profile.config;

import moe.pine.profile.properties.AnimeProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AnimeProperties.class)
public class AnimeConfig {
}
