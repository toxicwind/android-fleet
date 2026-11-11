package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class es_US extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"segundo", "segundos"};
    private static final String[] MINUTES = {"minuto", "minutos"};
    private static final String[] HOURS = {"hora", "horas"};
    private static final String[] DAYS = {"día", "días"};
    private static final String[] WEEKS = {"semana", "semanas"};
    private static final String[] MONTHS = {"mes", "meses"};
    private static final String[] YEARS = {"año", "años"};
    private static final es_US INSTANCE = new es_US();

    public static es_US getInstance() {
        return INSTANCE;
    }

    private es_US() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
