package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class fr extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"seconde", "secondes"};
    private static final String[] MINUTES = {"minute", "minutes"};
    private static final String[] HOURS = {"heure", "heures"};
    private static final String[] DAYS = {"jour", "jours"};
    private static final String[] WEEKS = {"semaine", "semaines"};
    private static final String[] MONTHS = {"mois"};
    private static final String[] YEARS = {"an", "ans"};
    private static final fr INSTANCE = new fr();

    public static fr getInstance() {
        return INSTANCE;
    }

    private fr() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
