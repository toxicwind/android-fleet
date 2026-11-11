package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class cs extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekundami", "sekundou"};
    private static final String[] MINUTES = {"minutami", "minutou"};
    private static final String[] HOURS = {"hodinami", "hodinou"};
    private static final String[] DAYS = {"dny", "včera"};
    private static final String[] WEEKS = {"týdnem", "týdny"};
    private static final String[] MONTHS = {"měsícem", "měsíci"};
    private static final String[] YEARS = {"rokem", "roky", "lety"};
    private static final cs INSTANCE = new cs();

    public static cs getInstance() {
        return INSTANCE;
    }

    private cs() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
