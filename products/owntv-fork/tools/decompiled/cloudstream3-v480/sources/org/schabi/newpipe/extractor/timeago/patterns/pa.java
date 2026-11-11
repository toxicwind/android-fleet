package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class pa extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"ਸਕਿੰਟ"};
    private static final String[] MINUTES = {"ਮਿੰਟ"};
    private static final String[] HOURS = {"ਘੰਟਾ", "ਘੰਟੇ"};
    private static final String[] DAYS = {"ਦਿਨ"};
    private static final String[] WEEKS = {"ਹਫ਼ਤਾ", "ਹਫ਼ਤੇ"};
    private static final String[] MONTHS = {"ਮਹੀਨਾ", "ਮਹੀਨੇ"};
    private static final String[] YEARS = {"ਸਾਲ"};
    private static final pa INSTANCE = new pa();

    public static pa getInstance() {
        return INSTANCE;
    }

    private pa() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
