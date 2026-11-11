package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class af extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekonde", "sekondes"};
    private static final String[] MINUTES = {"minute", "minuut"};
    private static final String[] HOURS = {"ure", "uur"};
    private static final String[] DAYS = {"dae", "dag"};
    private static final String[] WEEKS = {"week", "weke"};
    private static final String[] MONTHS = {"maand", "maande"};
    private static final String[] YEARS = {"jaar"};
    private static final af INSTANCE = new af();

    public static af getInstance() {
        return INSTANCE;
    }

    private af() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
