package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class mn extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"секундын"};
    private static final String[] MINUTES = {"минутын"};
    private static final String[] HOURS = {"цагийн"};
    private static final String[] DAYS = {"өдрийн"};
    private static final String[] WEEKS = {"долоо", "хоногийн"};
    private static final String[] MONTHS = {"сарын"};
    private static final String[] YEARS = {"жилийн"};
    private static final mn INSTANCE = new mn();

    public static mn getInstance() {
        return INSTANCE;
    }

    private mn() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
