package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class be extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"секунд", "секунду", "секунды"};
    private static final String[] MINUTES = {"хвілін", "хвіліну", "хвіліны"};
    private static final String[] HOURS = {"гадзін", "гадзіну", "гадзіны"};
    private static final String[] DAYS = {"дзень", "дзён", "дня", "дні"};
    private static final String[] WEEKS = {"тыдзень", "тыдня", "тыдні"};
    private static final String[] MONTHS = {"месяц", "месяца", "месяцы", "месяцаў"};
    private static final String[] YEARS = {"год", "года", "гады", "гадоў"};
    private static final be INSTANCE = new be();

    public static be getInstance() {
        return INSTANCE;
    }

    private be() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
