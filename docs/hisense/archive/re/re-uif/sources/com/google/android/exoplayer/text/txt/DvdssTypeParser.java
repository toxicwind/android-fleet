package com.google.android.exoplayer.text.txt;

import com.google.android.exoplayer.text.DefaultSubtitle;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class DvdssTypeParser implements TxtSuffixSubtitleParser {
    private final boolean filterTags;
    private static final Pattern dvdssTimestamp = Pattern.compile("(\\d+:)?[0-5]\\d:[0-5]\\d:\\d{2}");
    private static final Pattern dvdssStamp = Pattern.compile("(\\d+:)?[0-5]\\d:[0-5]\\d:\\d{2} (\\d+:)?[0-5]\\d:[0-5]\\d:\\d{2}");

    public DvdssTypeParser() {
        this(true, true);
    }

    public DvdssTypeParser(boolean z, boolean z2) {
        this.filterTags = z2;
    }

    @Override // com.google.android.exoplayer.text.txt.TxtSuffixSubtitleParser
    public boolean preParse(InputStream inputStream, String str) {
        MtkDebugLogUtil.log("DvdssParser", "preParse encodingType = " + str, 16);
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
                            if (dvdssStamp.matcher(line).find()) {
                                MtkDebugLogUtil.log("DvdssParser", "dvdss type line = " + line, 16);
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
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            return false;
        } catch (Throwable th) {
            try {
                inputStream.reset();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    @Override // com.google.android.exoplayer.text.txt.TxtSuffixSubtitleParser
    public Subtitle parse(InputStream inputStream, String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            if (!line.isEmpty()) {
                MtkDebugLogUtil.log("DvdssParser", " body content found line = " + line, 16);
                String strTrim = line.trim();
                Matcher matcher = dvdssStamp.matcher(strTrim);
                if (matcher.find()) {
                    String strGroup = matcher.group();
                    Matcher matcher2 = dvdssTimestamp.matcher(strTrim);
                    if (!matcher2.find()) {
                        MtkDebugLogUtil.log("DvdssParser", "not match dialoguePattern ", 16);
                    } else {
                        long timestampUs = parseTimestampUs(matcher2.group()) + 0;
                        if (!matcher2.find()) {
                            MtkDebugLogUtil.log("DvdssParser", "not match dialoguePattern ", 16);
                        } else {
                            arrayList.add(new DvdssCue(timestampUs, parseTimestampUs(matcher2.group()) + 0, processCueText(strTrim.substring(strGroup.length()))));
                        }
                    }
                }
            }
        }
        bufferedReader.close();
        inputStream.close();
        if (arrayList.size() > 0) {
            String[] strArr = new String[arrayList.size()];
            long[] jArr = new long[arrayList.size() * 2];
            for (int i = 0; i < arrayList.size(); i++) {
                int i2 = i * 2;
                DvdssCue dvdssCue = (DvdssCue) arrayList.get(i);
                jArr[i2] = dvdssCue.startTime;
                jArr[i2 + 1] = dvdssCue.endTime;
                strArr[i] = dvdssCue.text;
            }
            return new DefaultSubtitle(strArr, 0L, jArr);
        }
        return new DefaultSubtitle();
    }

    protected String processCueText(String str) {
        MtkDebugLogUtil.log("DvdssParser", "processCueText line = " + str, 16);
        return this.filterTags ? str.replaceAll("\\<.*?>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&").replaceAll("//", "\n") : str;
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        MtkDebugLogUtil.log("DvdssParser", "parseTimestampUs s = " + str, 16);
        String[] strArrSplit = str.split(":");
        long j = 0;
        for (int i = 0; i < strArrSplit.length - 1; i++) {
            j = (j * 60) + Long.parseLong(strArrSplit[i]);
        }
        return (j * 1000000) + (Long.parseLong(strArrSplit[strArrSplit.length - 1]) * 10000);
    }

    private static class DvdssCue {
        public final long endTime;
        public final long startTime;
        public final String text;

        public DvdssCue(long j, long j2, String str) {
            this.startTime = j;
            this.endTime = j2;
            this.text = str;
        }
    }
}
