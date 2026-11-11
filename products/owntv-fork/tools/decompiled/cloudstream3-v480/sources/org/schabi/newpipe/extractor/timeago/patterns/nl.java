package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class nl extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"seconde", "seconden"};
    private static final String[] MINUTES = {"minuten", "minuut"};
    private static final String[] HOURS = {"uur"};
    private static final String[] DAYS = {"dag", "dagen"};
    private static final String[] WEEKS = {"week", "weken"};
    private static final String[] MONTHS = {"maand", "maanden"};
    private static final String[] YEARS = {"jaar"};
    private static final nl INSTANCE = new nl();

    public static nl getInstance() {
        return INSTANCE;
    }

    private nl() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
