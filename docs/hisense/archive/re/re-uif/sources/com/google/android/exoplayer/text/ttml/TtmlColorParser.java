package com.google.android.exoplayer.text.ttml;

import android.text.TextUtils;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
final class TtmlColorParser {
    private static final Map<String, Integer> COLOR_NAME_MAP;
    private static final Pattern RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGBA_PATTERN = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");

    private static int argb(int i, int i2, int i3, int i4) {
        return (i << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    static {
        HashMap map = new HashMap();
        COLOR_NAME_MAP = map;
        map.put("transparent", 0);
        COLOR_NAME_MAP.put("black", -16777216);
        COLOR_NAME_MAP.put("silver", -4144960);
        COLOR_NAME_MAP.put("gray", -8355712);
        COLOR_NAME_MAP.put("white", -1);
        COLOR_NAME_MAP.put("maroon", -8388608);
        COLOR_NAME_MAP.put("red", -65536);
        COLOR_NAME_MAP.put("purple", -8388480);
        COLOR_NAME_MAP.put("fuchsia", -65281);
        COLOR_NAME_MAP.put("magenta", -65281);
        COLOR_NAME_MAP.put("green", -16744448);
        COLOR_NAME_MAP.put("lime", -16711936);
        COLOR_NAME_MAP.put("olive", -8355840);
        COLOR_NAME_MAP.put("yellow", -256);
        COLOR_NAME_MAP.put("navy", -16777088);
        COLOR_NAME_MAP.put("blue", -16776961);
        COLOR_NAME_MAP.put("teal", -16744320);
        COLOR_NAME_MAP.put("aqua", 16777215);
        COLOR_NAME_MAP.put("cyan", -16711681);
    }

    public static int parseColor(String str) {
        Assertions.checkArgument(!TextUtils.isEmpty(str));
        String strReplace = str.replace(" ", "");
        if (strReplace.charAt(0) == '#') {
            int i = (int) Long.parseLong(strReplace.substring(1), 16);
            if (strReplace.length() == 7) {
                return (-16777216) | i;
            }
            if (strReplace.length() == 9) {
                return ((i & 255) << 24) | (i >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (strReplace.startsWith("rgba")) {
            Matcher matcher = RGBA_PATTERN.matcher(strReplace);
            if (matcher.matches()) {
                return argb(255 - Integer.parseInt(matcher.group(4), 10), Integer.parseInt(matcher.group(1), 10), Integer.parseInt(matcher.group(2), 10), Integer.parseInt(matcher.group(3), 10));
            }
        } else if (strReplace.startsWith("rgb")) {
            Matcher matcher2 = RGB_PATTERN.matcher(strReplace);
            if (matcher2.matches()) {
                return rgb(Integer.parseInt(matcher2.group(1), 10), Integer.parseInt(matcher2.group(2), 10), Integer.parseInt(matcher2.group(3), 10));
            }
        } else {
            Integer num = COLOR_NAME_MAP.get(Util.toLowerInvariant(strReplace));
            if (num != null) {
                return num.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    private static int rgb(int i, int i2, int i3) {
        return argb(255, i, i2, i3);
    }
}
