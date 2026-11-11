package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class pl extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekund", "sekundy", "sekundę"};
    private static final String[] MINUTES = {"minut", "minuty", "minutę"};
    private static final String[] HOURS = {"godzin", "godziny", "godzinę"};
    private static final String[] DAYS = {"dni", "dzień"};
    private static final String[] WEEKS = {"tydzień", "tygodnie"};
    private static final String[] MONTHS = {"miesiąc", "miesiące", "miesięcy"};
    private static final String[] YEARS = {"lat", "lata", "rok"};
    private static final pl INSTANCE = new pl();

    public static pl getInstance() {
        return INSTANCE;
    }

    private pl() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
