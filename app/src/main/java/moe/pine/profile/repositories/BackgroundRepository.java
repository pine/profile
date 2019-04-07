package moe.pine.profile.repositories;

import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
@RequiredArgsConstructor
public class BackgroundRepository {
    private final Random random;

    @VisibleForTesting
    final static String[] BACKGROUNDS =
        {
            "yun_1077.jpg",
            "yun_1375.jpg",
            "yun_3218.jpg",
            "yun_3281.jpg",
            "yun_7604.jpg"
        };

    public String choose() {
        final int index = random.nextInt(BACKGROUNDS.length);
        return BACKGROUNDS[index];
    }
}
