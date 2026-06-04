

import browser.NgordnetQuery;
import main.HistoryTextHandler;
import ngrams.NGramMap;

import org.junit.jupiter.api.Test;
import java.util.List;

import static utils.Utils.*;
import static com.google.common.truth.Truth.assertThat;


// uncomment this test whenever you are ready!
public class HistoryTextHandlerTest {

    @Test
    public void testHandle() {
        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);
        HistoryTextHandler handler = new HistoryTextHandler(ngm);
        NgordnetQuery query = new NgordnetQuery(List.of("request", "airport"), 2006, 2007, 0);
        String actual = handler.handle(query);
        String expected = """
                request: {2006=7.42341395046138E-5, 2007=6.951115066593374E-5}
                airport: {2006=1.379589151613091E-5, 2007=1.4777957271013364E-5}
                """;
        assertThat(actual).isEqualTo(expected);
    }

}
