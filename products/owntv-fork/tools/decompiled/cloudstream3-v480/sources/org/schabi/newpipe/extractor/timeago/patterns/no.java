package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class no extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekund", "sekunder"};
    private static final String[] MINUTES = {"minutt", "minutter"};
    private static final String[] HOURS = {"time", "timer"};
    private static final String[] DAYS = {"dag", "dager"};
    private static final String[] WEEKS = {"uke", "uker"};
    private static final String[] MONTHS = {"md."};
    private static final String[] YEARS = {"år"};
    private static final no INSTANCE = new no();

    public static no getInstance() {
        return INSTANCE;
    }

    private no() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
