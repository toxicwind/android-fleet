package org.schabi.newpipe.extractor.timeago.patterns;

import org.schabi.newpipe.extractor.timeago.PatternsHolder;

/* JADX INFO: loaded from: classes8.dex */
public class kn extends PatternsHolder {
    private static final String WORD_SEPARATOR = " ";
    private static final String[] SECONDS = {"ಸೆಕೆಂಡುಗಳ", "ಸೆಕೆಂಡ್"};
    private static final String[] MINUTES = {"ನಿಮಿಷಗಳ", "ನಿಮಿಷದ"};
    private static final String[] HOURS = {"ಗಂಟೆಗಳ", "ಗಂಟೆಯ"};
    private static final String[] DAYS = {"ದಿನಗಳ", "ದಿನದ"};
    private static final String[] WEEKS = {"ವಾರಗಳ", "ವಾರದ"};
    private static final String[] MONTHS = {"ತಿಂಗಳ", "ತಿಂಗಳುಗಳ"};
    private static final String[] YEARS = {"ವರ್ಷಗಳ", "ವರ್ಷದ"};
    private static final kn INSTANCE = new kn();

    public static kn getInstance() {
        return INSTANCE;
    }

    private kn() {
        super(" ", SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS);
    }
}
