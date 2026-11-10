package com.google.android.exoplayer.text.srt;

import com.google.android.exoplayer.text.CaptionStyleCompat;
import com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class SrtParser implements SubtitleParser, ExternalSubtitleCharsetEncoding {
    private static final Pattern SRT_CUE_IDENTIFIER = Pattern.compile("^(?!.*(-->)).*$");
    private static final Pattern SRT_TIMESTAMP = Pattern.compile("(\\d+:)?[0-5]\\d:[0-5]\\d(\\,\\d{0,3})?");
    private String encoding;
    private final boolean filterTags;

    public SrtParser() {
        this(true, true, false);
    }

    public SrtParser(boolean z, boolean z2, boolean z3) {
        this.filterTags = z2;
    }

    @Override // com.google.android.exoplayer.text.ExternalSubtitleCharsetEncoding
    public void setCharsetEncoding(String str) {
        this.encoding = str;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x01bc */
    @Override // com.google.android.exoplayer.text.SubtitleParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.android.exoplayer.text.Subtitle parse(byte[] r26, int r27, int r28) throws com.google.android.exoplayer.ParserException {
        /*
            Method dump skipped, instruction units count: 457
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.text.srt.SrtParser.parse(byte[], int, int):com.google.android.exoplayer.text.Subtitle");
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/ext/srt".equals(str);
        MtkDebugLogUtil.log("SrtParser", "canParse  type = " + str + " can = " + zEquals, 16);
        return zEquals;
    }

    protected String processCueText(String str) {
        return this.filterTags ? str.replaceAll("</?[i|I|u|U|B|b]>", "").replaceAll("\\{.*?\\}", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&") : str;
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        if (!str.matches("(\\d+:)?[0-5]\\d:[0-5]\\d(\\,\\d{0,3})?")) {
            throw new NumberFormatException("has invalid format");
        }
        String[] strArrSplit = str.split("\\,", 2);
        long j = 0;
        for (String str2 : strArrSplit[0].split(":")) {
            j = (j * 60) + Long.parseLong(str2);
        }
        if (strArrSplit.length <= 1) {
            return j * 1000000;
        }
        return (long) ((j * 1000000) + (Long.parseLong(strArrSplit[1]) * Math.pow(10.0d, 6 - strArrSplit[1].length())));
    }

    private static class SrtCue {
        public final long endTime;
        public final long startTime;
        public final String text;

        public SrtCue(long j, long j2, String str) {
            this.startTime = j;
            this.endTime = j2;
            this.text = str;
        }

        public SrtCue(long j, long j2, String str, CaptionStyleCompat captionStyleCompat) {
            this(j, j2, str);
        }
    }
}
