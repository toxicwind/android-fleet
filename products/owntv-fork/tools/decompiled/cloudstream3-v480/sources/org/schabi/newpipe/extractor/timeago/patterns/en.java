package org.schabi.newpipe.extractor.timeago.patterns;

import androidx.media3.exoplayer.upstream.CmcdData;
import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class en extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"second", "seconds", "sec"};
    private static final String[] MINUTES = {"minute", "minutes", "min"};
    private static final String[] HOURS = {"hour", "hours", CmcdData.STREAMING_FORMAT_HLS};
    private static final String[] DAYS = {"day", "days", "d"};
    private static final String[] WEEKS = {"week", "weeks", "w"};
    private static final String[] MONTHS = {"month", "months", "mo"};
    private static final String[] YEARS = {"year", "years", "y"};
    private static final en INSTANCE = new en();

    public static en getInstance() {
        return INSTANCE;
    }

    private en() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
