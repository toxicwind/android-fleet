package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class eu extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"segundo"};
    private static final String[] MINUTES = {"minutu"};
    private static final String[] HOURS = {"ordu", "ordubete"};
    private static final String[] DAYS = {"egun"};
    private static final String[] WEEKS = {"aste", "astebete"};
    private static final String[] MONTHS = {"hilabete"};
    private static final String[] YEARS = {"urte", "urtebete"};
    private static final eu INSTANCE = new eu();

    public static eu getInstance() {
        return INSTANCE;
    }

    private eu() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
