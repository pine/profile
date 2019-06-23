package moe.pine.profile.config;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import moe.pine.profile.properties.AppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {
    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public HtmlCompressor htmlCompressor() {
        final var htmlCompressor = new HtmlCompressor();
        htmlCompressor.setRemoveQuotes(true);
        htmlCompressor.setSimpleBooleanAttributes(true);

        return htmlCompressor;
    }
}
