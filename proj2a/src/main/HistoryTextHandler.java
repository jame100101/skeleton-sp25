package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap ngm;

    public HistoryTextHandler(NGramMap ngm) {
        this.ngm = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        String response = "";

        for (String word : q.words()) {
            TimeSeries ts = ngm.weightHistory(word, q.startYear(), q.endYear());
            response += word + ": " + ts + "\n";
        }

        return response;
    }
}