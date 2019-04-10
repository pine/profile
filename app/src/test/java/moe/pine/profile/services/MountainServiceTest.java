package moe.pine.profile.services;

import com.google.common.collect.ImmutableList;
import moe.pine.profile.models.ViewMountain;
import moe.pine.profile.properties.MountainProperties;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.text.NumberFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MountainServiceTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private MountainProperties mountainProperties;

    private MountainService mountainService;

    @Before
    public void setUp() {
        final var numberFormat = NumberFormat.getNumberInstance();
        final var dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY/MM");
        mountainService = new MountainService(mountainProperties, numberFormat, dateTimeFormatter);
    }

    @Test
    public void getClimbedTest() {
        final List<MountainProperties.Mountain> mountains =
            ImmutableList.of(
                new MountainProperties.Mountain("homu", 2345, YearMonth.of(2019, 1)),
                new MountainProperties.Mountain("homuhomu", 999, YearMonth.of(2020, 12))
            );

        when(mountainProperties.getClimbed()).thenReturn(mountains);

        final List<ViewMountain> expected =
            ImmutableList.of(
                new ViewMountain("homu", "2,345m", "2019/01"),
                new ViewMountain("homuhomu", "999m", "2020/12")
            );
        final List<ViewMountain> actual = mountainService.getClimbed();
        assertEquals(expected, actual);

        //noinspection ResultOfMethodCallIgnored
        verify(mountainProperties).getClimbed();
    }
}
