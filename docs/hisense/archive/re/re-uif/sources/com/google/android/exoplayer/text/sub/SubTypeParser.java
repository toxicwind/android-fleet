package com.google.android.exoplayer.text.sub;

import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class SubTypeParser implements SubSuffixSubtitleParser {
    private final boolean filterTags;
    private static final Pattern subTimestamp = Pattern.compile("(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{2}");
    private static final Pattern subTimeLine = Pattern.compile("(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{2},(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{2}");

    public SubTypeParser() {
        this(true, true);
    }

    public SubTypeParser(boolean z, boolean z2) {
        this.filterTags = z2;
    }

    protected String processCueText(String str) {
        MtkDebugLogUtil.log("SubTypeParser", "processCueText line = " + str, 16);
        return this.filterTags ? str.replaceAll("\\<.*?>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&").replaceAll("\\[br\\]", "\n") : str;
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        if (!str.matches("(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{2}")) {
            throw new NumberFormatException("has invalid format");
        }
        MtkDebugLogUtil.log("SubTypeParser", "parseTimestampUs s = " + str, 16);
        String[] strArrSplit = str.split("\\.", 2);
        long j = 0;
        for (String str2 : strArrSplit[0].split(":")) {
            j = (j * 60) + Long.parseLong(str2);
        }
        return ((j * 100) + Long.parseLong(strArrSplit[1])) * 10000;
    }

    private static class SubCue {
        public final long endTime;
        public final long startTime;
        public final String text;

        public SubCue(long j, long j2, String str) {
            this.startTime = j;
            this.endTime = j2;
            this.text = str;
        }
    }

    @Override // com.google.android.exoplayer.text.sub.SubSuffixSubtitleParser
    public boolean preParse(InputStream inputStream, String str) {
        try {
            try {
                try {
                    inputStream.mark(20480);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
                    int i = 0;
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null || i >= 100) {
                            break;
                        }
                        if (!line.isEmpty()) {
                            if (subTimeLine.matcher(line).find()) {
                                MtkDebugLogUtil.log("SubTypeParser", "not match start time", 16);
                                try {
                                    inputStream.reset();
                                    return true;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return true;
                                }
                            }
                            i++;
                        }
                    }
                    inputStream.reset();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    inputStream.reset();
                }
            } catch (Throwable th) {
                try {
                    inputStream.reset();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return false;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x002f */
    /* JADX WARN: Code duplicated, block: B:17:0x0035 A[DONT_INVERT, EDGE_INSN: B:17:0x0035->B:67:0x0037 BREAK  A[LOOP:0: B:7:0x0024->B:80:0x0024, LOOP_LABEL: LOOP:0: B:7:0x0024->B:80:0x0024], PHI: r5
      0x0035: PHI (r5v8 java.lang.String) = (r5v2 java.lang.String), (r5v3 java.lang.String) binds: [B:9:0x0026, B:11:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:28:0x0078 A[Catch: all -> 0x002f, IOException -> 0x0032, TRY_ENTER, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x007f A[Catch: all -> 0x002f, IOException -> 0x0032, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00ac A[Catch: all -> 0x002f, IOException -> 0x0032, TRY_LEAVE, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00c1 A[Catch: all -> 0x002f, IOException -> 0x0032, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00d6 A[Catch: all -> 0x002f, IOException -> 0x0032, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00e5 A[Catch: all -> 0x002f, IOException -> 0x0032, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00eb A[Catch: all -> 0x002f, IOException -> 0x0032, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00f3 A[Catch: all -> 0x002f, IOException -> 0x0032, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x010c A[Catch: all -> 0x002f, IOException -> 0x0032, TryCatch #3 {IOException -> 0x0032, blocks: (B:10:0x0028, B:28:0x0078, B:31:0x007f, B:33:0x00a5, B:34:0x00ac, B:37:0x00bc, B:38:0x00c1, B:40:0x00d1, B:41:0x00d6, B:43:0x00e5, B:45:0x00eb, B:48:0x00f3, B:51:0x0106, B:53:0x010c, B:54:0x011d, B:57:0x0137, B:59:0x013d), top: B:73:0x0028, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x00a5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00bc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00d1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x007e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0103 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0133 A[EDGE_INSN: B:93:0x0133->B:55:0x0133 BREAK  A[LOOP:2: B:42:0x00e3->B:96:0x00e3], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0106 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x00f1 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:31:0x007f, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:53:0x010c, please report this as an issue */
    @Override // com.google.android.exoplayer.text.sub.SubSuffixSubtitleParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.android.exoplayer.text.Subtitle parse(java.io.InputStream r22, java.lang.String r23) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.text.sub.SubTypeParser.parse(java.io.InputStream, java.lang.String):com.google.android.exoplayer.text.Subtitle");
    }
}
