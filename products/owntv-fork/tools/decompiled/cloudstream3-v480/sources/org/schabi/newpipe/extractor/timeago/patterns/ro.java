package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ro extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"secunde", "secundă"};
    private static final String[] MINUTES = {"minut", "minute"};
    private static final String[] HOURS = {"ore", "oră"};
    private static final String[] DAYS = {"zi", "zile"};
    private static final String[] WEEKS = {"săptămâni", "săptămână"};
    private static final String[] MONTHS = {"luni", "lună"};
    private static final String[] YEARS = {"an", "ani"};
    private static final ro INSTANCE = new ro();

    public static ro getInstance() {
        return INSTANCE;
    }

    private ro() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
