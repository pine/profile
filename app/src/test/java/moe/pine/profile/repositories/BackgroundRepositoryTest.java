package moe.pine.profile.repositories;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.reflect.Whitebox;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.List;
import java.util.Random;

import static moe.pine.profile.repositories.BackgroundRepository.LOCATION_PATTERN;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BackgroundRepositoryTest {
    @Mock
    private ResourcePatternResolver resolver;

    @Mock
    private Random random;

    @Test
    void constructorTest() throws Exception {
        final Resource resource1 = mock(Resource.class);
        when(resource1.getFilename()).thenReturn("resource1.jpg");

        final Resource resource2 = mock(Resource.class);
        when(resource2.getFilename()).thenReturn("resource2.jpg");

        final Resource resource3 = mock(Resource.class);
        when(resource3.getFilename()).thenReturn(null);

        final Resource[] resources = new Resource[]{
            resource1,
            resource2,
            resource3
        };
        when(resolver.getResources(anyString())).thenReturn(resources);

        final var backgroundRepository = new BackgroundRepository(resolver, random);

        final List<String> expected =
            ImmutableList.of(
                "resource1.jpg",
                "resource2.jpg"
            );
        final List<String> actual =
            Whitebox.getInternalState(backgroundRepository, "backgrounds");
        assertEquals(expected, actual);
    }

    @Test
    void constructorTest_noImages() throws Exception {
        when(resolver.getResources(anyString())).thenReturn(new Resource[0]);

        assertThatThrownBy(() -> new BackgroundRepository(resolver, random))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Background image not found :: location-pattern=" + LOCATION_PATTERN);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void constructorTest_nullResolver() {
        assertThatThrownBy(() -> new BackgroundRepository(null, random))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void constructorTest_nullRandom() {
        assertThatThrownBy(() -> new BackgroundRepository(resolver, null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void chooseTest() throws Exception {
        final Resource resource1 = mock(Resource.class);
        when(resource1.getFilename()).thenReturn("resource1.jpg");

        final Resource resource2 = mock(Resource.class);
        when(resource2.getFilename()).thenReturn("resource2.jpg");

        final Resource resource3 = mock(Resource.class);
        when(resource3.getFilename()).thenReturn("resource3.jpg");

        final Resource[] resources = new Resource[]{
            resource1,
            resource2,
            resource3
        };
        when(resolver.getResources(anyString())).thenReturn(resources);
        when(random.nextInt(resources.length))
            .thenReturn(2)
            .thenReturn(0)
            .thenReturn(1);

        final var backgroundRepository = new BackgroundRepository(resolver, random);
        assertEquals("resource3.jpg", backgroundRepository.choose());
        assertEquals("resource1.jpg", backgroundRepository.choose());
        assertEquals("resource2.jpg", backgroundRepository.choose());
    }
}
