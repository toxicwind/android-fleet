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
public class TxtTypeParser implements TxtSuffixSubtitleParser {
    private static final Pattern txtTimestamp = Pattern.compile("(\\d+:)?[0-5]\\d:[0-5]\\d(:|=)");
    private final boolean filterTags;

    public TxtTypeParser() {
        this(true, true);
    }

    public TxtTypeParser(boolean z, boolean z2) {
        this.filterTags = z2;
    }

    @Override // com.google.android.exoplayer.text.txt.TxtSuffixSubtitleParser
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
                            if (txtTimestamp.matcher(line).find()) {
                                MtkDebugLogUtil.log("TxtParser", "txt type line = " + line, 16);
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

    @Override // com.google.android.exoplayer.text.txt.TxtSuffixSubtitleParser
    public Subtitle parse(InputStream inputStream, String str) throws IOException {
        int i;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
        while (true) {
            String line = bufferedReader.readLine();
            i = 0;
            if (line == null) {
                break;
            }
            if (!line.isEmpty()) {
                MtkDebugLogUtil.log("TxtParser", " body content found line = " + line, 16);
                String strTrim = line.trim();
                Matcher matcher = txtTimestamp.matcher(strTrim);
                if (!matcher.find()) {
                    MtkDebugLogUtil.log("TxtParser", "not match dialoguePattern ", 16);
                } else {
                    String strGroup = matcher.group();
                    arrayList.add(new TxtCue(parseTimestampUs(strGroup.substring(0, strGroup.length() - 1)) + 0, 0L, processCueText(strTrim.substring(strGroup.length()))));
                }
            }
        }
        bufferedReader.close();
        inputStream.close();
        if (arrayList.size() > 0) {
            String[] strArr = new String[arrayList.size()];
            long[] jArr = new long[arrayList.size() * 2];
            while (i < arrayList.size()) {
                int i2 = i * 2;
                TxtCue txtCue = (TxtCue) arrayList.get(i);
                jArr[i2] = txtCue.startTime;
                int i3 = i + 1;
                if (i3 >= arrayList.size()) {
                    jArr[i2 + 1] = txtCue.startTime + 1000000;
                } else {
                    jArr[i2 + 1] = ((TxtCue) arrayList.get(i3)).startTime;
                }
                strArr[i] = txtCue.text;
                i = i3;
            }
            return new DefaultSubtitle(strArr, 0L, jArr);
        }
        return new DefaultSubtitle();
    }

    protected String processCueText(String str) {
        MtkDebugLogUtil.log("TxtParser", "processCueText line = " + str, 16);
        return this.filterTags ? str.replaceAll("\\<.*?>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&").replace('|', '\n') : str;
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        MtkDebugLogUtil.log("TxtParser", "parseTimestampUs s = " + str, 16);
        long j = 0;
        for (String str2 : str.split(":")) {
            j = (j * 60) + Long.parseLong(str2);
        }
        return j * 1000000;
    }

    private static class TxtCue {
        public final long startTime;
        public final String text;

        public TxtCue(long j, long j2, String str) {
            this.startTime = j;
            this.text = str;
        }
    }
}
