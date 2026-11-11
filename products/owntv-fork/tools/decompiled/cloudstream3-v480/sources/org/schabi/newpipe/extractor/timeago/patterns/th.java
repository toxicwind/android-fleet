package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class th extends PatternsHolder {
    private static final String WORD_SEPARATOR = "";
    private static final String[] SECONDS = {"วินาทีที่ผ่านมา"};
    private static final String[] MINUTES = {"นาทีที่ผ่านมา"};
    private static final String[] HOURS = {"ชั่วโมงที่ผ่านมา"};
    private static final String[] DAYS = {"วันที่ผ่านมา"};
    private static final String[] WEEKS = {"สัปดาห์ที่ผ่านมา"};
    private static final String[] MONTHS = {"เดือนที่ผ่านมา"};
    private static final String[] YEARS = {"ปีที่ผ่านมา"};
    private static final th INSTANCE = new th();

    public static th getInstance() {
        return INSTANCE;
    }

    private th() {
        super("", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
