package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ur extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"سیکنڈ", "سیکنڈز"};
    private static final String[] MINUTES = {"منٹ", "منٹس"};
    private static final String[] HOURS = {"گھنٹہ", "گھنٹے"};
    private static final String[] DAYS = {"دن"};
    private static final String[] WEEKS = {"ہفتہ", "ہفتے"};
    private static final String[] MONTHS = {"ماہ"};
    private static final String[] YEARS = {"سال"};
    private static final ur INSTANCE = new ur();

    public static ur getInstance() {
        return INSTANCE;
    }

    private ur() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
