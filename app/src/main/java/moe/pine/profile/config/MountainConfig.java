package moe.pine.profile.config;

import moe.pine.profile.properties.MountainProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableConfigurationProperties(MountainProperties.class)
public class MountainConfig {
    @Bean
    public NumberFormat mountainNumberFormat() {
        return NumberFormat.getNumberInstance();
    }

    @Bean
    public DateTimeFormatter mountainDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("YYYY/MM");
    }
}
