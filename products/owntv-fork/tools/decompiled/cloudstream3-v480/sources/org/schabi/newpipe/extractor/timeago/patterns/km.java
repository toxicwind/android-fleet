package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class km extends PatternsHolder {
    private static final String WORD_SEPARATOR = "";
    private static final String[] SECONDS = {"វិនាទី\u200bមុន", "១វិនាទីមុន"};
    private static final String[] MINUTES = {"នាទីមុន", "១នាទីមុន"};
    private static final String[] HOURS = {"ម៉ោង\u200bមុន", "១ម៉ោង\u200bមុន"};
    private static final String[] DAYS = {"ថ្ងៃមុន", "១ថ្ងៃ\u200bមុន"};
    private static final String[] WEEKS = {"ស\u200bប្តា\u200bហ៍\u200bមុន", "១ស\u200bប្តា\u200bហ៍\u200bមុន"};
    private static final String[] MONTHS = {"ខែមុន", "១ខែមុន"};
    private static final String[] YEARS = {"ឆ្នាំ\u200bមុន", "១ឆ្នាំមុន"};
    private static final km INSTANCE = new km();

    public static km getInstance() {
        return INSTANCE;
    }

    private km() {
        super("", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
