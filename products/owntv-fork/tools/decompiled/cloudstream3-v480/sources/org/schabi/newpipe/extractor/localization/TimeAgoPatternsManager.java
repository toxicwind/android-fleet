package org.schabi.newpipe.extractor.localization;

import j$.time.LocalDateTime;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.timeago.PatternsHolder;
import org.schabi.newpipe.extractor.timeago.PatternsManager;

/* JADX INFO: loaded from: classes8.dex */
public final class TimeAgoPatternsManager {
    private TimeAgoPatternsManager() {
    }

    @Nullable
    private static PatternsHolder getPatternsFor(@Nonnull Localization localization) {
        return PatternsManager.getPatterns(localization.getLanguageCode(), localization.getCountryCode());
    }

    @Nullable
    public static TimeAgoParser getTimeAgoParserFor(@Nonnull Localization localization) {
        return getTimeAgoParserFor(localization, LocalDateTime.now());
    }

    @Nullable
    public static TimeAgoParser getTimeAgoParserFor(@Nonnull Localization localization, @Nonnull LocalDateTime localDateTime) {
        PatternsHolder patternsFor = getPatternsFor(localization);
        if (patternsFor == null) {
            return null;
        }
        return new TimeAgoParser(patternsFor, localDateTime);
    }
}
