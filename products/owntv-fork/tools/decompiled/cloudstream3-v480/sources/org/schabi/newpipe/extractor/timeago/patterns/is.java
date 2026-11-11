package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class is extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekúndu", "sekúndum", "second", "seconds"};
    private static final String[] MINUTES = {"mínútu", "mínútum", "minute", "minutes"};
    private static final String[] HOURS = {"klukkustund", "klukkustundum", "hour", "hours"};
    private static final String[] DAYS = {"degi", "dögum", "day", "days"};
    private static final String[] WEEKS = {"viku", "vikum", "week", "weeks"};
    private static final String[] MONTHS = {"mánuði", "mánuðum"};
    private static final String[] YEARS = {"ári", "árum"};
    private static final is INSTANCE = new is();

    public static is getInstance() {
        return INSTANCE;
    }

    private is() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
