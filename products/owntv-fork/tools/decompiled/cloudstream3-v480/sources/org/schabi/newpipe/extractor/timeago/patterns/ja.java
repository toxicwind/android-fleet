package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ja extends PatternsHolder {
    private static final String WORD_SEPARATOR = "";
    private static final String[] SECONDS = {"秒前"};
    private static final String[] MINUTES = {"分前"};
    private static final String[] HOURS = {"時間前"};
    private static final String[] DAYS = {"日前"};
    private static final String[] WEEKS = {"週間前"};
    private static final String[] MONTHS = {"か月前"};
    private static final String[] YEARS = {"年前"};
    private static final ja INSTANCE = new ja();

    public static ja getInstance() {
        return INSTANCE;
    }

    private ja() {
        super("", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
