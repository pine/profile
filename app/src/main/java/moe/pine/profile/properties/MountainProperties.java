package moe.pine.profile.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.YearMonth;
import java.util.List;

@Data
@Validated
@ConfigurationProperties("mountain")
public class MountainProperties {
    private @NotNull List<Mountain> climbed;

    @Data
    @Validated
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Mountain {
        private @NotEmpty String name;
        private @NotNull Integer height;
        private @NotNull YearMonth climbedAt;
    }
}
