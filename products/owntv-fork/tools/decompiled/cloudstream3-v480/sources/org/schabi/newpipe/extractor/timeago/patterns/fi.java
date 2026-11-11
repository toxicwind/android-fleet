package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class fi extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekunti", "sekuntia"};
    private static final String[] MINUTES = {"minuutti", "minuuttia"};
    private static final String[] HOURS = {"tunti", "tuntia"};
    private static final String[] DAYS = {"päivä", "päivää"};
    private static final String[] WEEKS = {"viikko", "viikkoa"};
    private static final String[] MONTHS = {"kuukausi", "kuukautta"};
    private static final String[] YEARS = {"vuosi", "vuotta"};
    private static final fi INSTANCE = new fi();

    public static fi getInstance() {
        return INSTANCE;
    }

    private fi() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
