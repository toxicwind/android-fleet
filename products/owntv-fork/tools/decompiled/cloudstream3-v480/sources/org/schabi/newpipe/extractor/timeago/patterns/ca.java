package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ca extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"segon", "segons"};
    private static final String[] MINUTES = {"minut", "minuts"};
    private static final String[] HOURS = {"hora", "hores"};
    private static final String[] DAYS = {"dia", "dies"};
    private static final String[] WEEKS = {"setmana", "setmanes"};
    private static final String[] MONTHS = {"mes", "mesos"};
    private static final String[] YEARS = {"any", "anys"};
    private static final ca INSTANCE = new ca();

    public static ca getInstance() {
        return INSTANCE;
    }

    private ca() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
