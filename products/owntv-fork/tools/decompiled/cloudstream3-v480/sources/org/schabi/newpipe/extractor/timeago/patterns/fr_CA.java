package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class fr_CA extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"seconde", "secondes"};
    private static final String[] MINUTES = {"minute", "minutes"};
    private static final String[] HOURS = {"heures", "heure"};
    private static final String[] DAYS = {"jour", "jours"};
    private static final String[] WEEKS = {"semaine", "semaines"};
    private static final String[] MONTHS = {"mois"};
    private static final String[] YEARS = {"an", "ans"};
    private static final fr_CA INSTANCE = new fr_CA();

    public static fr_CA getInstance() {
        return INSTANCE;
    }

    private fr_CA() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
