package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class sv extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekund", "sekunder"};
    private static final String[] MINUTES = {"minut", "minuter"};
    private static final String[] HOURS = {"timmar", "timme"};
    private static final String[] DAYS = {"dag", "dagar"};
    private static final String[] WEEKS = {"vecka", "veckor"};
    private static final String[] MONTHS = {"månad", "månader"};
    private static final String[] YEARS = {"år"};
    private static final sv INSTANCE = new sv();

    public static sv getInstance() {
        return INSTANCE;
    }

    private sv() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
