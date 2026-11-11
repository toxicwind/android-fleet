package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class et extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekund", "sekundit"};
    private static final String[] MINUTES = {"minut", "minutit"};
    private static final String[] HOURS = {"tund", "tundi"};
    private static final String[] DAYS = {"päev", "päeva"};
    private static final String[] WEEKS = {"nädal", "nädalat"};
    private static final String[] MONTHS = {"kuu", "kuud"};
    private static final String[] YEARS = {"aasta", "aastat"};
    private static final et INSTANCE = new et();

    public static et getInstance() {
        return INSTANCE;
    }

    private et() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
