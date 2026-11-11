package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class id extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"detik"};
    private static final String[] MINUTES = {"menit"};
    private static final String[] HOURS = {"jam"};
    private static final String[] DAYS = {"hari"};
    private static final String[] WEEKS = {"minggu"};
    private static final String[] MONTHS = {"bulan"};
    private static final String[] YEARS = {"tahun"};
    private static final id INSTANCE = new id();

    public static id getInstance() {
        return INSTANCE;
    }

    private id() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
