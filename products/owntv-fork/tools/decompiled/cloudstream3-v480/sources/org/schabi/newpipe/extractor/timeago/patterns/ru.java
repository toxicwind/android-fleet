package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ru extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"секунд", "секунду", "секунды", "только что"};
    private static final String[] MINUTES = {"минут", "минуту", "минуты"};
    private static final String[] HOURS = {"час", "часа", "часов"};
    private static final String[] DAYS = {"день", "дней", "дня"};
    private static final String[] WEEKS = {"Неделю", "недели"};
    private static final String[] MONTHS = {"месяц", "месяца", "месяцев"};
    private static final String[] YEARS = {"Год", "года", "лет"};
    private static final ru INSTANCE = new ru();

    public static ru getInstance() {
        return INSTANCE;
    }

    private ru() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
