package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class az extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"saniyə"};
    private static final String[] MINUTES = {"dəqiqə"};
    private static final String[] HOURS = {"saat"};
    private static final String[] DAYS = {"gün"};
    private static final String[] WEEKS = {"həftə"};
    private static final String[] MONTHS = {"ay"};
    private static final String[] YEARS = {"il"};
    private static final az INSTANCE = new az();

    public static az getInstance() {
        return INSTANCE;
    }

    private az() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
