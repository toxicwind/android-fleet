package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class fa extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"ثانیه"};
    private static final String[] MINUTES = {"دقیقه"};
    private static final String[] HOURS = {"ساعت"};
    private static final String[] DAYS = {"روز"};
    private static final String[] WEEKS = {"هفته"};
    private static final String[] MONTHS = {"ماه"};
    private static final String[] YEARS = {"سال"};
    private static final fa INSTANCE = new fa();

    public static fa getInstance() {
        return INSTANCE;
    }

    private fa() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
