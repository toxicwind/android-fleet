package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ms extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"saat"};
    private static final String[] MINUTES = {"minit"};
    private static final String[] HOURS = {"jam"};
    private static final String[] DAYS = {"hari"};
    private static final String[] WEEKS = {"minggu"};
    private static final String[] MONTHS = {"bulan"};
    private static final String[] YEARS = {"tahun"};
    private static final ms INSTANCE = new ms();

    public static ms getInstance() {
        return INSTANCE;
    }

    private ms() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
