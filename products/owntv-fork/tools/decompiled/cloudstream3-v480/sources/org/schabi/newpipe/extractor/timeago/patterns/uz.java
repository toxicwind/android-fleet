package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class uz extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"soniya"};
    private static final String[] MINUTES = {"daqiqa"};
    private static final String[] HOURS = {"soat"};
    private static final String[] DAYS = {"kun"};
    private static final String[] WEEKS = {"hafta"};
    private static final String[] MONTHS = {"oy"};
    private static final String[] YEARS = {"yil"};
    private static final uz INSTANCE = new uz();

    public static uz getInstance() {
        return INSTANCE;
    }

    private uz() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
