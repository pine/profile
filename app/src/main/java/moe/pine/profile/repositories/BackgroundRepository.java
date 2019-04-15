package moe.pine.profile.repositories;

import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

@Repository
@Slf4j
public class BackgroundRepository {
    @VisibleForTesting
    static String LOCATION_PATTERN = "classpath:/static/images/bg/*.jpg";

    @Nonnull
    private final Random random;

    @Nonnull
    private final List<String> backgrounds;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public BackgroundRepository(
        @Nonnull final ResourcePatternResolver resolver,
        @Nonnull final Random random
    ) {
        checkNotNull(resolver);
        this.random = checkNotNull(random);

        try {
            final var resources =
                resolver.getResources(LOCATION_PATTERN);
            final var filenames =
                Arrays.stream(resources)
                    .map(Resource::getFilename)
                    .filter(StringUtils::isNotEmpty)
                    .collect(Collectors.toUnmodifiableList());

            log.debug(
                "Found {} background images from {} :: {}",
                filenames.size(), LOCATION_PATTERN, filenames);

            if (filenames.isEmpty()) {
                throw new IllegalStateException(
                    String.format(
                        "Background image not found :: location-pattern=%s",
                        LOCATION_PATTERN));
            }
            this.backgrounds = filenames;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    public String choose() {
        checkState(CollectionUtils.isNotEmpty(backgrounds));

        final int index = random.nextInt(backgrounds.size());
        return backgrounds.get(index);
    }
}
