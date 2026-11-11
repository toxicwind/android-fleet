package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class sw extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekunde"};
    private static final String[] MINUTES = {"dakika"};
    private static final String[] HOURS = {"saa"};
    private static final String[] DAYS = {"siku"};
    private static final String[] WEEKS = {"wiki"};
    private static final String[] MONTHS = {"Mwezi", "miezi"};
    private static final String[] YEARS = {"Miaka", "Mwaka"};
    private static final sw INSTANCE = new sw();

    public static sw getInstance() {
        return INSTANCE;
    }

    private sw() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
