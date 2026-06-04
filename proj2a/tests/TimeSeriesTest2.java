import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Extra unit tests for the TimeSeries class using the generated ngrams data values. */
public class TimeSeriesTest2 {
    @Test
    public void testPlusWithGeneratedCatDogData() {
        TimeSeries catCounts = new TimeSeries();
        catCounts.put(2005, 480103.0);
        catCounts.put(2006, 502012.0);
        catCounts.put(2007, 549499.0);

        TimeSeries dogCounts = new TimeSeries();
        dogCounts.put(2005, 998683.0);
        dogCounts.put(2006, 1037004.0);
        dogCounts.put(2007, 1128658.0);

        TimeSeries combinedCounts = catCounts.plus(dogCounts);

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(2005);
        expectedYears.add(2006);
        expectedYears.add(2007);

        assertThat(combinedCounts.years()).isEqualTo(expectedYears);

        List<Double> expectedData = new ArrayList<>();
        expectedData.add(1478786.0);
        expectedData.add(1539016.0);
        expectedData.add(1678157.0);

        for (int i = 0; i < expectedData.size(); i += 1) {
            assertThat(combinedCounts.data().get(i)).isWithin(1E-10).of(expectedData.get(i));
        }
    }

    @Test
    public void testCopyConstructorWithGeneratedDataRange() {
        TimeSeries catCounts = new TimeSeries();
        catCounts.put(2005, 480103.0);
        catCounts.put(2006, 502012.0);
        catCounts.put(2007, 549499.0);

        TimeSeries copy = new TimeSeries(catCounts, 2006, 2007);

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(2006);
        expectedYears.add(2007);

        assertThat(copy.years()).isEqualTo(expectedYears);

        List<Double> expectedData = new ArrayList<>();
        expectedData.add(502012.0);
        expectedData.add(549499.0);

        for (int i = 0; i < expectedData.size(); i += 1) {
            assertThat(copy.data().get(i)).isWithin(1E-10).of(expectedData.get(i));
        }
    }

    @Test
    public void testDividedByWithGeneratedTotalCounts() {
        TimeSeries catCounts = new TimeSeries();
        catCounts.put(2005, 480103.0);
        catCounts.put(2006, 502012.0);
        catCounts.put(2007, 549499.0);

        TimeSeries totalCounts = new TimeSeries();
        totalCounts.put(2005, 26609986084.0);
        totalCounts.put(2006, 27695491774.0);
        totalCounts.put(2007, 28307904288.0);

        TimeSeries catWeights = catCounts.dividedBy(totalCounts);

        assertThat(catWeights.get(2005)).isWithin(1E-10).of(480103.0 / 26609986084.0);
        assertThat(catWeights.get(2006)).isWithin(1E-10).of(502012.0 / 27695491774.0);
        assertThat(catWeights.get(2007)).isWithin(1E-10).of(549499.0 / 28307904288.0);
    }
}
