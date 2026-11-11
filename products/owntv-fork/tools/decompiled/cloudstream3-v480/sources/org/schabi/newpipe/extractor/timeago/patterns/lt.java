package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class lt extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekundes", "sekundę", "sekundžių"};
    private static final String[] MINUTES = {"minutes", "minutę", "minučių"};
    private static final String[] HOURS = {"valandas", "valandą", "valandų"};
    private static final String[] DAYS = {"dienas", "dieną"};
    private static final String[] WEEKS = {"savaites", "savaitę"};
    private static final String[] MONTHS = {"mėnesius", "mėnesių", "mėnesį"};
    private static final String[] YEARS = {"metus", "metų"};
    private static final lt INSTANCE = new lt();

    public static lt getInstance() {
        return INSTANCE;
    }

    private lt() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
