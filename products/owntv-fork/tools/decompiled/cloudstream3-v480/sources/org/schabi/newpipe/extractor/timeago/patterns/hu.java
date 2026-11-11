package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class hu extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"másodperce"};
    private static final String[] MINUTES = {"perce"};
    private static final String[] HOURS = {"órája"};
    private static final String[] DAYS = {"napja"};
    private static final String[] WEEKS = {"hete"};
    private static final String[] MONTHS = {"hónapja"};
    private static final String[] YEARS = {"éve"};
    private static final hu INSTANCE = new hu();

    public static hu getInstance() {
        return INSTANCE;
    }

    private hu() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
