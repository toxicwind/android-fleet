package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ky extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"секунд"};
    private static final String[] MINUTES = {"мүнөт"};
    private static final String[] HOURS = {"саат"};
    private static final String[] DAYS = {"күн"};
    private static final String[] WEEKS = {"апта"};
    private static final String[] MONTHS = {"ай"};
    private static final String[] YEARS = {"жыл"};
    private static final ky INSTANCE = new ky();

    public static ky getInstance() {
        return INSTANCE;
    }

    private ky() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
