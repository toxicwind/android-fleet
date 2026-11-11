package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class zu extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"amasekhondi", "isekhondi"};
    private static final String[] MINUTES = {"amaminithi", "iminithi"};
    private static final String[] HOURS = {"amahora", "ihora"};
    private static final String[] DAYS = {"izinsuku", "usuku"};
    private static final String[] WEEKS = {"amaviki", "iviki"};
    private static final String[] MONTHS = {"inyanga", "izinyanga"};
    private static final String[] YEARS = {"iminyaka", "unyaka"};
    private static final zu INSTANCE = new zu();

    public static zu getInstance() {
        return INSTANCE;
    }

    private zu() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
