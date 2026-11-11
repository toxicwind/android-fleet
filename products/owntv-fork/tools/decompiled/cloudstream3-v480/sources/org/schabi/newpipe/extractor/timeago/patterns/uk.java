package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class uk extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"секунд", "секунди", "секунду"};
    private static final String[] MINUTES = {"хвилин", "хвилини", "хвилину"};
    private static final String[] HOURS = {"годин", "години", "годину"};
    private static final String[] DAYS = {"день", "дні", "днів"};
    private static final String[] WEEKS = {"тиждень", "тижні"};
    private static final String[] MONTHS = {"місяць", "місяці", "місяців"};
    private static final String[] YEARS = {"роки", "років", "рік"};
    private static final uk INSTANCE = new uk();

    public static uk getInstance() {
        return INSTANCE;
    }

    private uk() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
