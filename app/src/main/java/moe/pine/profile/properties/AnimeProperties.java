package moe.pine.profile.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties("anime")
public class AnimeProperties {
    private Map<String, List<String>> watched;
}
