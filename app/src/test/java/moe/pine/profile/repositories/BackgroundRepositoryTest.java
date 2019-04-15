package moe.pine.profile.repositories;

import com.google.common.collect.ImmutableList;
import lombok.SneakyThrows;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.reflect.Whitebox;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.List;
import java.util.Random;

import static moe.pine.profile.repositories.BackgroundRepository.LOCATION_PATTERN;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BackgroundRepositoryTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ResourcePatternResolver resolver;

    @Mock
    private Random random;

    @Test
    public void constructorTest() throws Exception {
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
    @SneakyThrows
    public void constructorTest_noImages() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(
            "Background image not found :: location-pattern=" + LOCATION_PATTERN);

        when(resolver.getResources(anyString())).thenReturn(new Resource[0]);
        new BackgroundRepository(resolver, random);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void constructorTest_nullResolver() {
        expectedException.expect(NullPointerException.class);
        new BackgroundRepository(null, random);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void constructorTest_nullRandom() {
        expectedException.expect(NullPointerException.class);
        new BackgroundRepository(resolver, null);
    }

    @Test
    @SneakyThrows
    public void chooseTest() {
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
