package org.schabi.newpipe.extractor.timeago;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public class PatternsManager {
    @Nullable
    public static PatternsHolder getPatterns(@Nonnull String str, @Nullable String str2) {
        String str3;
        if (str2 == null || str2.isEmpty()) {
            str3 = "";
        } else {
            str3 = "_" + str2;
        }
        return PatternMap.getPattern(str + str3);
    }
}
