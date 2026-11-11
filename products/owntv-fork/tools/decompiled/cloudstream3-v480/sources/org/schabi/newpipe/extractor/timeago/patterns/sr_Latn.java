package org.schabi.newpipe.extractor.timeago.patterns;

import androidx.core.text.util.LocalePreferences;
import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class sr_Latn extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"sekunde", "sekundi"};
    private static final String[] MINUTES = {"minuta"};
    private static final String[] HOURS = {LocalePreferences.FirstDayOfWeek.SATURDAY, "sati", "sata"};
    private static final String[] DAYS = {"Pre 1 dan", "Pre 2 dana", "Pre 3 dana", "Pre 4 dana", "Pre 5 dana", "Pre 6 dana"};
    private static final String[] WEEKS = {"nedelja", "nedelje", "nedelju"};
    private static final String[] MONTHS = {"mesec", "meseci", "meseca"};
    private static final String[] YEARS = {"godine", "godina", "godinu"};
    private static final sr_Latn INSTANCE = new sr_Latn();

    public static sr_Latn getInstance() {
        return INSTANCE;
    }

    private sr_Latn() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
