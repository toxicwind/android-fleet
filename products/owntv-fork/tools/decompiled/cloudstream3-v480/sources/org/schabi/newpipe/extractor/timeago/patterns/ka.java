package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ka extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"წამის"};
    private static final String[] MINUTES = {"წუთის"};
    private static final String[] HOURS = {"საათის"};
    private static final String[] DAYS = {"დღის"};
    private static final String[] WEEKS = {"კვირის"};
    private static final String[] MONTHS = {"თვის"};
    private static final String[] YEARS = {"წლის"};
    private static final ka INSTANCE = new ka();

    public static ka getInstance() {
        return INSTANCE;
    }

    private ka() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
