package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class my extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"စက္ကန့်"};
    private static final String[] MINUTES = {"မိနစ်"};
    private static final String[] HOURS = {"နာရီ"};
    private static final String[] DAYS = {"ရက်"};
    private static final String[] WEEKS = {"ပတ်"};
    private static final String[] MONTHS = {"လ"};
    private static final String[] YEARS = {"နှစ်"};
    private static final my INSTANCE = new my();

    public static my getInstance() {
        return INSTANCE;
    }

    private my() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
