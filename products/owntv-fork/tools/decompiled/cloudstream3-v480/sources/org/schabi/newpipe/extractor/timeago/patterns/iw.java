package org.schabi.newpipe.extractor.timeago.patterns;

import j$.time.temporal.ChronoUnit;
import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class iw extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"שניות", "שנייה"};
    private static final String[] MINUTES = {"דקה", "דקות"};
    private static final String[] HOURS = {"שעה", "שעות"};
    private static final String[] DAYS = {"יום", "ימים"};
    private static final String[] WEEKS = {"שבוע", "שבועות"};
    private static final String[] MONTHS = {"חודש", "חודשים"};
    private static final String[] YEARS = {"שנה", "שנים"};
    private static final iw INSTANCE = new iw();

    public static iw getInstance() {
        return INSTANCE;
    }

    private iw() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
        putSpecialCase(ChronoUnit.HOURS, "שעתיים", 2);
        putSpecialCase(ChronoUnit.DAYS, "יומיים", 2);
        putSpecialCase(ChronoUnit.WEEKS, "שבועיים", 2);
        putSpecialCase(ChronoUnit.MONTHS, "חודשיים", 2);
        putSpecialCase(ChronoUnit.YEARS, "שנתיים", 2);
    }
}
