package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class am extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"ሰኮንዶች", "ሴኮንድ"};
    private static final String[] MINUTES = {"ደቂቃ", "ደቂቃዎች"};
    private static final String[] HOURS = {"ሰዓት", "ሰዓቶች"};
    private static final String[] DAYS = {"ቀን", "ቀኖች"};
    private static final String[] WEEKS = {"ሳምንታት", "ሳምንት"};
    private static final String[] MONTHS = {"ወራት", "ወር"};
    private static final String[] YEARS = {"ዓመታት", "ዓመት"};
    private static final am INSTANCE = new am();

    public static am getInstance() {
        return INSTANCE;
    }

    private am() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
