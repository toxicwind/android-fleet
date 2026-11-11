package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class it extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"secondi", "secondo"};
    private static final String[] MINUTES = {"minuti", "minuto"};
    private static final String[] HOURS = {"ora", "ore"};
    private static final String[] DAYS = {"giorni", "giorno"};
    private static final String[] WEEKS = {"settimana", "settimane"};
    private static final String[] MONTHS = {"mese", "mesi"};
    private static final String[] YEARS = {"anni", "anno"};
    private static final it INSTANCE = new it();

    public static it getInstance() {
        return INSTANCE;
    }

    private it() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
