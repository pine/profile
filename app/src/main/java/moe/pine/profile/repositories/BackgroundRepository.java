package moe.pine.profile.repositories;

import com.google.common.annotations.VisibleForTesting;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkState;

@Repository
@Slf4j
@SuppressFBWarnings("DMI_RANDOM_USED_ONLY_ONCE")
public class BackgroundRepository {
    @VisibleForTesting
    static String LOCATION_PATTERN = "classpath:/static/images/bg/*.jpg";

    private final Random random;
    private final List<String> backgrounds;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public BackgroundRepository(
        final ResourcePatternResolver resolver,
        final Random random
    ) {
        Objects.requireNonNull(resolver);
        this.random = Objects.requireNonNull(random);

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
            throw new UncheckedIOException(e);
        }
    }

    public String choose() {
        checkState(CollectionUtils.isNotEmpty(backgrounds));

        final int index = random.nextInt(backgrounds.size());
        return backgrounds.get(index);
    }
}
