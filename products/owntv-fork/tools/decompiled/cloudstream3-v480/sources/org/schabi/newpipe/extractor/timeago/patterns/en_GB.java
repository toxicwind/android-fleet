package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class en_GB extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"second", "seconds", "sec"};
    private static final String[] MINUTES = {"minute", "minutes", "min"};
    private static final String[] HOURS = {"hour", "hours", "hr"};
    private static final String[] DAYS = {"day", "days"};
    private static final String[] WEEKS = {"week", "weeks", "wk"};
    private static final String[] MONTHS = {"month", "months", "mo"};
    private static final String[] YEARS = {"year", "years", "yr"};
    private static final en_GB INSTANCE = new en_GB();

    public static en_GB getInstance() {
        return INSTANCE;
    }

    private en_GB() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
