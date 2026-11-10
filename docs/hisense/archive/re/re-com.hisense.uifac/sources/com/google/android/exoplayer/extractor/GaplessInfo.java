package com.google.android.exoplayer.extractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class GaplessInfo {
    private static final Pattern GAPLESS_COMMENT_PATTERN = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public final int encoderDelay;
    public final int encoderPadding;

    public static GaplessInfo createFromComment(String str, String str2) {
        if (!"iTunSMPB".equals(str)) {
            return null;
        }
        Matcher matcher = GAPLESS_COMMENT_PATTERN.matcher(str2);
        if (!matcher.find()) {
            return null;
        }
        try {
            int i = Integer.parseInt(matcher.group(1), 16);
            int i2 = Integer.parseInt(matcher.group(2), 16);
            if (i == 0 && i2 == 0) {
                return null;
            }
            return new GaplessInfo(i, i2);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static GaplessInfo createFromXingHeaderValue(int i) {
        int i2 = i >> 12;
        int i3 = i & 4095;
        if (i2 == 0 && i3 == 0) {
            return null;
        }
        return new GaplessInfo(i2, i3);
    }

    private GaplessInfo(int i, int i2) {
        this.encoderDelay = i;
        this.encoderPadding = i2;
    }
}
