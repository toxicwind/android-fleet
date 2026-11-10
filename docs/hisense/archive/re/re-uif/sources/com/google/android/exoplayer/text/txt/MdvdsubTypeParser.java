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
public class MdvdsubTypeParser implements TxtSuffixSubtitleParser {
    private static final Pattern mdvdsubTimestamp = Pattern.compile("\\d+");
    private static final Pattern mdvdsubpattern = Pattern.compile("((\\{|\\[)\\d+?(\\}|\\])( ){0,}){2}");
    private final boolean filterTags;

    public MdvdsubTypeParser() {
        this(true, true);
    }

    public MdvdsubTypeParser(boolean z, boolean z2) {
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
                            if (mdvdsubpattern.matcher(line).find()) {
                                MtkDebugLogUtil.log("MdvdsubParser", "mdvdsub type line = " + line, 16);
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
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            if (!line.isEmpty()) {
                MtkDebugLogUtil.log("MdvdsubParser", " body content found line = " + line, 16);
                String strTrim = line.trim();
                Matcher matcher = mdvdsubpattern.matcher(strTrim);
                if (matcher.find()) {
                    String strGroup = matcher.group();
                    Matcher matcher2 = mdvdsubTimestamp.matcher(strTrim);
                    if (!matcher2.find()) {
                        MtkDebugLogUtil.log("MdvdsubParser", "not match dialoguePattern ", 16);
                    } else {
                        long timestampUs = parseTimestampUs(matcher2.group()) + 0;
                        if (!matcher2.find()) {
                            MtkDebugLogUtil.log("MdvdsubParser", "not match dialoguePattern ", 16);
                        } else {
                            arrayList.add(new MdvdsubCue(timestampUs, parseTimestampUs(matcher2.group()) + 0, processCueText(strTrim.substring(strGroup.length()))));
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
                MdvdsubCue mdvdsubCue = (MdvdsubCue) arrayList.get(i);
                jArr[i2] = mdvdsubCue.startTime;
                jArr[i2 + 1] = mdvdsubCue.endTime;
                strArr[i] = mdvdsubCue.text;
            }
            return new DefaultSubtitle(strArr, 0L, jArr);
        }
        return new DefaultSubtitle();
    }

    protected String processCueText(String str) {
        MtkDebugLogUtil.log("MdvdsubParser", "processCueText line = " + str, 16);
        return this.filterTags ? str.replaceAll("\\<.*?>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&").replaceAll("\\|", "\n") : str;
    }

    private static long parseTimestampUs(String str) throws NumberFormatException {
        MtkDebugLogUtil.log("MdvdsubParser", "parseTimestampUs s = " + str, 16);
        return ((Long.parseLong(str) * 1) / 30) * 1000000;
    }

    private static class MdvdsubCue {
        public final long endTime;
        public final long startTime;
        public final String text;

        public MdvdsubCue(long j, long j2, String str) {
            this.startTime = j;
            this.endTime = j2;
            this.text = str;
        }
    }
}
