package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class sq extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekonda", "sekondë"};
    private static final String[] MINUTES = {"minuta", "minutë"};
    private static final String[] HOURS = {"orë"};
    private static final String[] DAYS = {"ditë"};
    private static final String[] WEEKS = {"javë"};
    private static final String[] MONTHS = {"muaj"};
    private static final String[] YEARS = {"vit", "vjet"};
    private static final sq INSTANCE = new sq();

    public static sq getInstance() {
        return INSTANCE;
    }

    private sq() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
