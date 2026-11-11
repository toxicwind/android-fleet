package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class bn extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"সেকেন্ড"};
    private static final String[] MINUTES = {"মিনিট"};
    private static final String[] HOURS = {"ঘণ্টা"};
    private static final String[] DAYS = {"দিন"};
    private static final String[] WEEKS = {"সপ্তাহ"};
    private static final String[] MONTHS = {"মাস"};
    private static final String[] YEARS = {"বছর"};
    private static final bn INSTANCE = new bn();

    public static bn getInstance() {
        return INSTANCE;
    }

    private bn() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
