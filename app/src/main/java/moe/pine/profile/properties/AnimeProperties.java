package moe.pine.profile.properties;

import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Value
@NonFinal
@ConstructorBinding
@Validated
@ConfigurationProperties("anime")
public class AnimeProperties {
    @NotNull Map<String, List<String>> watched;

    @PostConstruct
    public void postConstruct() {
        watched.keySet().forEach(Objects::requireNonNull);
        watched.values().stream()
            .flatMap(List::stream)
            .forEach(Objects::requireNonNull);
    }
}
