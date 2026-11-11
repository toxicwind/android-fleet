package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class fil extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"segundo"};
    private static final String[] MINUTES = {"minuto"};
    private static final String[] HOURS = {"oras"};
    private static final String[] DAYS = {"araw"};
    private static final String[] WEEKS = {"linggo"};
    private static final String[] MONTHS = {"buwan"};
    private static final String[] YEARS = {"taon"};
    private static final fil INSTANCE = new fil();

    public static fil getInstance() {
        return INSTANCE;
    }

    private fil() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
