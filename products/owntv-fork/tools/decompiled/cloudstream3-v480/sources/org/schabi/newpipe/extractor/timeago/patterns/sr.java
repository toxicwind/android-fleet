package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class sr extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"секунде", "секунди"};
    private static final String[] MINUTES = {"минута"};
    private static final String[] HOURS = {"сат", "сата", "сати"};
    private static final String[] DAYS = {"Пре 1 дан", "Пре 2 дана", "Пре 3 дана", "Пре 4 дана", "Пре 5 дана", "Пре 6 дана"};
    private static final String[] WEEKS = {"недеље", "недељу"};
    private static final String[] MONTHS = {"месец", "месеца", "месеци"};
    private static final String[] YEARS = {"година", "године", "годину"};
    private static final sr INSTANCE = new sr();

    public static sr getInstance() {
        return INSTANCE;
    }

    private sr() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
