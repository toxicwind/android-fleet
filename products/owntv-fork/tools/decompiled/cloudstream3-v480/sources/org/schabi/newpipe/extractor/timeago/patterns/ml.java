package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class ml extends PatternsHolder {
    private static final String WORD_SEPARATOR = "";
    private static final String[] SECONDS = {"സെക്കന്റ്", "സെക്കൻഡ്"};
    private static final String[] MINUTES = {"മിനിറ്റ്"};
    private static final String[] HOURS = {"മണിക്കൂർ"};
    private static final String[] DAYS = {"ദിവസം"};
    private static final String[] WEEKS = {"ആഴ്ച", "ആഴ്\u200cച"};
    private static final String[] MONTHS = {"മാസം"};
    private static final String[] YEARS = {"വർഷം"};
    private static final ml INSTANCE = new ml();

    public static ml getInstance() {
        return INSTANCE;
    }

    private ml() {
        super("", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
