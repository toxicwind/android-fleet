package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class sk extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekundami", "sekundou"};
    private static final String[] MINUTES = {"minútami", "minútou"};
    private static final String[] HOURS = {"hodinami", "hodinou"};
    private static final String[] DAYS = {"dňami", "dňom"};
    private static final String[] WEEKS = {"týždňami", "týždňom"};
    private static final String[] MONTHS = {"mesiacmi", "mesiacom"};
    private static final String[] YEARS = {"rokmi", "rokom"};
    private static final sk INSTANCE = new sk();

    public static sk getInstance() {
        return INSTANCE;
    }

    private sk() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
