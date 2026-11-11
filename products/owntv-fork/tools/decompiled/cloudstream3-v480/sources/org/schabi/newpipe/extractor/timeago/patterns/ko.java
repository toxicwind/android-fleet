package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ko extends PatternsHolder {
    private static final String WORD_SEPARATOR = "";
    private static final String[] SECONDS = {"초"};
    private static final String[] MINUTES = {"분"};
    private static final String[] HOURS = {"시간"};
    private static final String[] DAYS = {"일"};
    private static final String[] WEEKS = {"주"};
    private static final String[] MONTHS = {"개월"};
    private static final String[] YEARS = {"년"};
    private static final ko INSTANCE = new ko();

    public static ko getInstance() {
        return INSTANCE;
    }

    private ko() {
        super("", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
