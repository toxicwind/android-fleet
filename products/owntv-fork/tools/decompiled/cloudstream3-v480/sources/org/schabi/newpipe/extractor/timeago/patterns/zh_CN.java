package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class zh_CN extends PatternsHolder {
    private static final String WORD_SEPARATOR = "";
    private static final String[] SECONDS = {"秒前"};
    private static final String[] MINUTES = {"分钟前"};
    private static final String[] HOURS = {"小时前"};
    private static final String[] DAYS = {"天前"};
    private static final String[] WEEKS = {"周前"};
    private static final String[] MONTHS = {"个月前"};
    private static final String[] YEARS = {"年前"};
    private static final zh_CN INSTANCE = new zh_CN();

    public static zh_CN getInstance() {
        return INSTANCE;
    }

    private zh_CN() {
        super("", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
