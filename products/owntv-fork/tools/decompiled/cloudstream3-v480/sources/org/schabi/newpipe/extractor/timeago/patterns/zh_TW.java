package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class zh_TW extends PatternsHolder {
    private static final String WORD_SEPARATOR = "";
    private static final String[] SECONDS = {"秒前"};
    private static final String[] MINUTES = {"分鐘前"};
    private static final String[] HOURS = {"小時前"};
    private static final String[] DAYS = {"天前"};
    private static final String[] WEEKS = {"週前"};
    private static final String[] MONTHS = {"個月前"};
    private static final String[] YEARS = {"年前"};
    private static final zh_TW INSTANCE = new zh_TW();

    public static zh_TW getInstance() {
        return INSTANCE;
    }

    private zh_TW() {
        super("", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
