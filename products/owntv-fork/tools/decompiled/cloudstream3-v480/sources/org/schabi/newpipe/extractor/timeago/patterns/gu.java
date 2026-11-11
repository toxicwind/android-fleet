package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class gu extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"સેકંડ"};
    private static final String[] MINUTES = {"મિનિટ"};
    private static final String[] HOURS = {"કલાક"};
    private static final String[] DAYS = {"દિવસ"};
    private static final String[] WEEKS = {"અઠવાડિયા"};
    private static final String[] MONTHS = {"મહિના"};
    private static final String[] YEARS = {"વર્ષ"};
    private static final gu INSTANCE = new gu();

    public static gu getInstance() {
        return INSTANCE;
    }

    private gu() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
