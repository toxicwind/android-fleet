package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class si extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"තත්පර"};
    private static final String[] MINUTES = {"මිනිත්තු"};
    private static final String[] HOURS = {"පැය"};
    private static final String[] DAYS = {"දින"};
    private static final String[] WEEKS = {"සති"};
    private static final String[] MONTHS = {"මාස"};
    private static final String[] YEARS = {"වසර"};
    private static final si INSTANCE = new si();

    public static si getInstance() {
        return INSTANCE;
    }

    private si() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
