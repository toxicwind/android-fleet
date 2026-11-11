package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class tr extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"saniye"};
    private static final String[] MINUTES = {"dakika"};
    private static final String[] HOURS = {"saat"};
    private static final String[] DAYS = {"gün"};
    private static final String[] WEEKS = {"hafta"};
    private static final String[] MONTHS = {"ay"};
    private static final String[] YEARS = {"yıl"};
    private static final tr INSTANCE = new tr();

    public static tr getInstance() {
        return INSTANCE;
    }

    private tr() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
