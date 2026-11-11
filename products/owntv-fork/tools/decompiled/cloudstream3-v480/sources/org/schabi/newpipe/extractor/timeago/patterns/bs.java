package org.schabi.newpipe.extractor.timeago.patterns;

import androidx.core.text.util.LocalePreferences;
import androidx.media3.exoplayer.upstream.CmcdData;
import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class bs extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekundi", "sekunde", "sekundu"};
    private static final String[] MINUTES = {"minuta", "minute", "minutu"};
    private static final String[] HOURS = {CmcdData.STREAMING_FORMAT_HLS, LocalePreferences.FirstDayOfWeek.SATURDAY, "sata", "sati"};
    private static final String[] DAYS = {"dan", "dana"};
    private static final String[] WEEKS = {"sedm."};
    private static final String[] MONTHS = {"mj.", "mjesec", "mjeseca", "mjeseci"};
    private static final String[] YEARS = {"godina", "godine", "godinu"};
    private static final bs INSTANCE = new bs();

    public static bs getInstance() {
        return INSTANCE;
    }

    private bs() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
