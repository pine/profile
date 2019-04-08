package moe.pine.profile.repositories;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Repository
@Slf4j
public class BackgroundRepository {
    @Nonnull
    private final Random random;

    @Nonnull
    private final ArrayList<String> backgrounds = Lists.newArrayList();

    public BackgroundRepository(@Nonnull final Random random) {
        this.random = checkNotNull(random);

        try {
            final var resolver = new PathMatchingResourcePatternResolver();
            final var resources =
                resolver.getResources("classpath:/static/images/bg/*.jpg");
            final var filenames =
                Arrays.stream(resources)
                    .map(Resource::getFilename)
                    .collect(Collectors.toList());

            log.debug("Found {} background images :: {}", filenames.size(), filenames);

            this.backgrounds.addAll(filenames);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String choose() {
        final int index = random.nextInt(backgrounds.size());
        return backgrounds.get(index);
    }
}
