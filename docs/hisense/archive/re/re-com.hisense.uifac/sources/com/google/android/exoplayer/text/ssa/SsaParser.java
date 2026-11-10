package com.google.android.exoplayer.text.ssa;

import com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class SsaParser implements SubtitleParser, ExternalSubtitleCharsetEncoding {
    private String encoding;
    private final boolean filterTags;
    private static final Pattern eventPattern = Pattern.compile("^\\[Events\\]$");
    private static final Pattern formatPattern = Pattern.compile("Format:((.*?),)+(.*)");
    private static final Pattern commaPattern = Pattern.compile("((.*?),)+(.*)");

    public SsaParser() {
        this(true, true);
    }

    public SsaParser(boolean z, boolean z2) {
        this.filterTags = z2;
    }

    @Override // com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding
    public void setCharsetEncoding(String str) {
        this.encoding = str;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x01a8 */
    /* JADX WARN: Code duplicated, block: B:55:0x0170  */
    /* JADX WARN: Code duplicated, block: B:58:0x0184 A[LOOP:3: B:56:0x017e->B:58:0x0184, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:60:0x01a2  */
    @Override // com.google.android.exoplayer.text.SubtitleParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.android.exoplayer.text.Subtitle parse(byte[] r21, int r22, int r23) throws com.google.android.exoplayer.ParserException {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.text.ssa.SsaParser.parse(byte[], int, int):com.google.android.exoplayer.text.Subtitle");
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/ext/ssa".equals(str) | "text/ext/ass".equals(str);
        MtkDebugLogUtil.log("SsaParser", "canParse  type = " + str + " can = " + zEquals, 16);
        return zEquals;
    }

    protected String processCueText(String str) {
        MtkDebugLogUtil.log("SsaParser", "processCueText line = " + str, 16);
        if (!this.filterTags) {
            return str;
        }
        String strSplitString = splitString(str.replaceAll("\\{.*?\\}", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&"));
        MtkDebugLogUtil.log("SsaParser", "processCueText line end= " + strSplitString, 16);
        return strSplitString;
    }

    public String splitString(String str) {
        String[] strArrSplit = str.split("\\\\N|\\\\n");
        String str2 = "";
        for (int i = 0; i < strArrSplit.length; i++) {
            str2 = i < strArrSplit.length - 1 ? str2 + strArrSplit[i] + '\n' : str2 + strArrSplit[i];
        }
        return str2;
    }

    private int[] getStartAndEndIndex(String[] strArr) {
        int[] iArr = {-1, -1};
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].equals("Start")) {
                    iArr[0] = i;
                } else if (strArr[i].equals("End")) {
                    iArr[1] = i;
                }
            }
        }
        for (int i2 = 0; i2 < 2; i2++) {
            MtkDebugLogUtil.log("SsaParser", "getStartAndEndIndex i = " + iArr[i2], 16);
        }
        return iArr;
    }

    protected String[] processCueByComma(String str) {
        if (!str.matches("((.*?),)+(.*)")) {
            throw new NumberFormatException("has invalid format");
        }
        Matcher matcher = commaPattern.matcher(str);
        if (!matcher.find()) {
            return null;
        }
        String strGroup = matcher.group();
        MtkDebugLogUtil.log("SsaParser", "processCueByComma = " + strGroup, 16);
        String[] strArrSplit = strGroup.split(",", 10);
        int i = 0;
        for (String str2 : strArrSplit) {
            strArrSplit[i] = str2.trim();
            i++;
        }
        return strArrSplit;
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        if (!str.matches("(\\d+:)?[0-5]?\\d:[0-5]?\\d\\.\\d{2}")) {
            throw new NumberFormatException("has invalid format");
        }
        MtkDebugLogUtil.log("SsaParser", "parseTimestampUs s = " + str, 16);
        String[] strArrSplit = str.split("\\.", 2);
        long j = 0;
        for (String str2 : strArrSplit[0].split(":")) {
            j = (j * 60) + Long.parseLong(str2);
        }
        return ((j * 100) + Long.parseLong(strArrSplit[1])) * 10000;
    }

    private static class SsaCue {
        public final long endTime;
        public final long startTime;
        public final String text;

        public SsaCue(long j, long j2, String str) {
            this.startTime = j;
            this.endTime = j2;
            this.text = str;
        }
    }
}
