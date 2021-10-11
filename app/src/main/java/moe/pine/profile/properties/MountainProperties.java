package moe.pine.profile.properties;

import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.YearMonth;
import java.util.List;

@Value
@NonFinal
@ConstructorBinding
@Validated
@ConfigurationProperties("mountain")
public class MountainProperties {
    @NotNull List<Mountain> climbed;

    @Value
    @NonFinal
    @ConstructorBinding
    @Validated
    public static class Mountain {
        @NotEmpty String name;
        @NotNull Integer height;
        @NotNull YearMonth climbedAt;
    }
}
