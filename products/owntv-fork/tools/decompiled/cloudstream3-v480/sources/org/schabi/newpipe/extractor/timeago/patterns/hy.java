package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class hy extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"վայրկյան"};
    private static final String[] MINUTES = {"րոպե"};
    private static final String[] HOURS = {"ժամ"};
    private static final String[] DAYS = {"օր"};
    private static final String[] WEEKS = {"շաբաթ"};
    private static final String[] MONTHS = {"ամիս"};
    private static final String[] YEARS = {"տարի"};
    private static final hy INSTANCE = new hy();

    public static hy getInstance() {
        return INSTANCE;
    }

    private hy() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
