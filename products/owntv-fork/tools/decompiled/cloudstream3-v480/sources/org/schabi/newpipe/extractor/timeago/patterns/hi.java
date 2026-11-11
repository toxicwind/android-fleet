package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class hi extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"सेकंड"};
    private static final String[] MINUTES = {"मिनट"};
    private static final String[] HOURS = {"घंटा", "घंटे"};
    private static final String[] DAYS = {"दिन"};
    private static final String[] WEEKS = {"सप्ताह", "हफ़्ते"};
    private static final String[] MONTHS = {"महीना", "महीने"};
    private static final String[] YEARS = {"वर्ष"};
    private static final hi INSTANCE = new hi();

    public static hi getInstance() {
        return INSTANCE;
    }

    private hi() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
