package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class kk extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"секунд"};
    private static final String[] MINUTES = {"минут"};
    private static final String[] HOURS = {"сағат"};
    private static final String[] DAYS = {"күн"};
    private static final String[] WEEKS = {"апта"};
    private static final String[] MONTHS = {"ай"};
    private static final String[] YEARS = {"жыл"};
    private static final kk INSTANCE = new kk();

    public static kk getInstance() {
        return INSTANCE;
    }

    private kk() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
