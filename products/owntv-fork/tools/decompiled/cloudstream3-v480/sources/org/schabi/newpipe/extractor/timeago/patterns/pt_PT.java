package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class pt_PT extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"segundo", "segundos"};
    private static final String[] MINUTES = {"minuto", "minutos"};
    private static final String[] HOURS = {"hora", "horas"};
    private static final String[] DAYS = {"dia", "dias"};
    private static final String[] WEEKS = {"semana", "semanas"};
    private static final String[] MONTHS = {"meses", "mês"};
    private static final String[] YEARS = {"ano", "anos"};
    private static final pt_PT INSTANCE = new pt_PT();

    public static pt_PT getInstance() {
        return INSTANCE;
    }

    private pt_PT() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
