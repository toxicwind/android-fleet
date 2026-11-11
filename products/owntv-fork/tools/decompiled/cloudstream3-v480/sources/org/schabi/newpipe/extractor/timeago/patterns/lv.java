package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class lv extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekundes", "sekundēm"};
    private static final String[] MINUTES = {"minūtes", "minūtēm", "minūtes"};
    private static final String[] HOURS = {"stundas", "stundām"};
    private static final String[] DAYS = {"dienas", "dienām"};
    private static final String[] WEEKS = {"nedēļas", "nedēļām"};
    private static final String[] MONTHS = {"mēneša", "mēnešiem"};
    private static final String[] YEARS = {"gada", "gadiem"};
    private static final lv INSTANCE = new lv();

    public static lv getInstance() {
        return INSTANCE;
    }

    private lv() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
