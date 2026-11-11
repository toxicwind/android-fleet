package org.schabi.newpipe.extractor.timeago.patterns;

import androidx.core.text.util.LocalePreferences;
import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class hr extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekunde", "sekundi", "sekundu"};
    private static final String[] MINUTES = {"minuta", "minute", "minutu"};
    private static final String[] HOURS = {LocalePreferences.FirstDayOfWeek.SATURDAY, "sata", "sati"};
    private static final String[] DAYS = {"dan", "dana"};
    private static final String[] WEEKS = {"tjedan", "tjedna"};
    private static final String[] MONTHS = {"mjesec", "mjeseca", "mjeseci"};
    private static final String[] YEARS = {"godina", "godine", "godinu"};
    private static final hr INSTANCE = new hr();

    public static hr getInstance() {
        return INSTANCE;
    }

    private hr() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
