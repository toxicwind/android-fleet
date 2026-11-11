package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class bg extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"секунда", "секунди"};
    private static final String[] MINUTES = {"минута", "минути"};
    private static final String[] HOURS = {"час", "часа"};
    private static final String[] DAYS = {"ден", "дни"};
    private static final String[] WEEKS = {"седмица", "седмици"};
    private static final String[] MONTHS = {"месец", "месеца"};
    private static final String[] YEARS = {"година", "години"};
    private static final bg INSTANCE = new bg();

    public static bg getInstance() {
        return INSTANCE;
    }

    private bg() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
