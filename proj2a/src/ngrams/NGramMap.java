package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private Map<String, TimeSeries> wordHistories;
    private TimeSeries totalCounts;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help
        wordHistories = new HashMap<>();
        totalCounts = new TimeSeries();

        In wordsInput =  new In(wordsFilename);
        while(wordsInput.hasNextLine()) {
            String line = wordsInput.readLine();
            String[] parts = line.split("\t");

            String word = parts[0];
            int year = Integer.parseInt(parts[1]);
            double count = Double.parseDouble(parts[2]);

            if (!wordHistories.containsKey(word)) {
                wordHistories.put(word, new TimeSeries());
            }
            wordHistories.get(word).put(year, count);
        }

        In countsInput = new In(countsFilename);
        while (countsInput.hasNextLine()) {
            String line = countsInput.readLine();
            String[] parts = line.split(",");

            int year = Integer.parseInt(parts[0]);
            double count = Double.parseDouble(parts[1]);

            totalCounts.put(year, count);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordHistories.containsKey(word)){
            return new TimeSeries();
        }
        return new TimeSeries(wordHistories.get(word), startYear, endYear);
        //return null;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        return countHistory(word, MIN_YEAR, MAX_YEAR);
        //return null;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return new TimeSeries(totalCounts, MIN_YEAR, MAX_YEAR);
        //return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries counts = countHistory(word, startYear, endYear);
        TimeSeries totals = new TimeSeries(totalCounts, startYear, endYear);
        return counts.dividedBy(totals);
        //return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return weightHistory(word, MIN_YEAR, MAX_YEAR);
        //return null;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries sum = new TimeSeries();
        for (String word : words) {
            TimeSeries weight = weightHistory(word, startYear, endYear);
            sum = sum.plus(weight);
        }
        return sum;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
        //return null;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
