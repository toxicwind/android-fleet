package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class vi extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"giây"};
    private static final String[] MINUTES = {"phút"};
    private static final String[] HOURS = {"giờ", "tiếng"};
    private static final String[] DAYS = {"ngày"};
    private static final String[] WEEKS = {"tuần"};
    private static final String[] MONTHS = {"tháng"};
    private static final String[] YEARS = {"năm"};
    private static final vi INSTANCE = new vi();

    public static vi getInstance() {
        return INSTANCE;
    }

    private vi() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
