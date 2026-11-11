package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class da extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekund", "sekunder"};
    private static final String[] MINUTES = {"minut", "minutter"};
    private static final String[] HOURS = {"time", "timer"};
    private static final String[] DAYS = {"dag", "dage"};
    private static final String[] WEEKS = {"uge", "uger"};
    private static final String[] MONTHS = {"måned", "måneder"};
    private static final String[] YEARS = {"år"};
    private static final da INSTANCE = new da();

    public static da getInstance() {
        return INSTANCE;
    }

    private da() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
