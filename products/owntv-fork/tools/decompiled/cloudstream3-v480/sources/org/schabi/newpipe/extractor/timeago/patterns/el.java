package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class el extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"δευτερόλεπτα", "δευτερόλεπτο"};
    private static final String[] MINUTES = {"λεπτά", "λεπτό"};
    private static final String[] HOURS = {"ώρα", "ώρες"};
    private static final String[] DAYS = {"ημέρα", "ημέρες"};
    private static final String[] WEEKS = {"εβδομάδα", "εβδομάδες"};
    private static final String[] MONTHS = {"μήνα", "μήνες"};
    private static final String[] YEARS = {"χρόνια", "χρόνο"};
    private static final el INSTANCE = new el();

    public static el getInstance() {
        return INSTANCE;
    }

    private el() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
