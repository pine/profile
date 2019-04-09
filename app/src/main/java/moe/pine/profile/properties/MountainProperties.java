package moe.pine.profile.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.YearMonth;
import java.util.List;

@Data
@ConfigurationProperties("mountain")
public class MountainProperties {
    private List<Mountain> climbed;

    @Data
    public static class Mountain {
        private String name;
        private int height;
        private YearMonth climbedAt;
    }
}
