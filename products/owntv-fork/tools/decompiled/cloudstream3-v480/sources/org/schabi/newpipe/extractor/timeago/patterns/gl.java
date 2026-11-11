package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class gl extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"segundo", "segundos"};
    private static final String[] MINUTES = {"minuto", "minutos"};
    private static final String[] HOURS = {"hora", "horas"};
    private static final String[] DAYS = {"día", "días"};
    private static final String[] WEEKS = {"semana", "semanas"};
    private static final String[] MONTHS = {"mes", "meses"};
    private static final String[] YEARS = {"ano", "anos"};
    private static final gl INSTANCE = new gl();

    public static gl getInstance() {
        return INSTANCE;
    }

    private gl() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
