package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class pt extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"segundo", "segundos"};
    private static final String[] MINUTES = {"minuto", "minutos"};
    private static final String[] HOURS = {"hora", "horas"};
    private static final String[] DAYS = {"dia", "dias"};
    private static final String[] WEEKS = {"semana", "semanas"};
    private static final String[] MONTHS = {"meses", "mês"};
    private static final String[] YEARS = {"ano", "anos"};
    private static final pt INSTANCE = new pt();

    public static pt getInstance() {
        return INSTANCE;
    }

    private pt() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
