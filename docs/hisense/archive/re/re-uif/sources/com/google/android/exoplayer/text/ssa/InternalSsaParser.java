package com.google.android.exoplayer.text.ssa;

import android.util.Log;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.DefaultSubtitle;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class InternalSsaParser implements SubtitleParser {
    private final boolean filterTags;
    private static final Pattern beforePattern = Pattern.compile("((.*?),){8}");
    private static final String SSA_ENTER_TAG_STRING = Pattern.quote("\\N");
    private static final String SSA_H_TAG_STRING = Pattern.quote("\\h");

    public InternalSsaParser() {
        this(true, true);
    }

    public InternalSsaParser(boolean z, boolean z2) {
        this.filterTags = z2;
    }

    private int bytesToInt(byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        int i4 = 0;
        for (int i5 = i; i5 < i + i2; i5++) {
            i4 += (bArr[i5] & 255) << (i3 * 8);
            i3--;
        }
        return i4;
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        long jBytesToInt;
        BufferedReader bufferedReader;
        String strProcessCueText;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        byte[] bArr2 = new byte[8];
        byteArrayInputStream.mark(8);
        if (-1 == byteArrayInputStream.read(bArr2, 0, 8)) {
            Log.d("SsaParser", "inputStream read is -1");
        }
        if (new String(bArr2).startsWith("dftT")) {
            jBytesToInt = bytesToInt(bArr2, 4, 4);
        } else {
            jBytesToInt = 1000000;
            byteArrayInputStream.reset();
        }
        long j = jBytesToInt;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream, "UTF-8"));
            while (true) {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            try {
                                break;
                            } catch (IOException unused) {
                            }
                        } else if (!line.isEmpty()) {
                            MtkDebugLogUtil.log("SsaParser", " Event start fount  line =" + line, 16);
                            String strTrim = line.trim();
                            Matcher matcher = beforePattern.matcher(strTrim);
                            if (matcher.find()) {
                                String strSubstring = strTrim.substring(matcher.group().length());
                                MtkDebugLogUtil.log("SsaParser", " Event start fount  content =" + strSubstring, 16);
                                strProcessCueText = processCueText(strSubstring);
                            } else {
                                continue;
                            }
                        }
                    } catch (IOException e) {
                        throw new ParserException(e);
                    }
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            }
        } catch (UnsupportedEncodingException unused3) {
            bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        }
        strProcessCueText = null;
        bufferedReader.close();
        if (strProcessCueText != null) {
            return new DefaultSubtitle(strProcessCueText, 0L, j);
        }
        return new DefaultSubtitle();
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/ssa".equals(str) | "text/ass".equals(str);
        MtkDebugLogUtil.log("SsaParser", "canParse  type = " + str + " can = " + zEquals, 16);
        return zEquals;
    }

    protected String processCueText(String str) {
        MtkDebugLogUtil.log("SsaParser", "processCueText line = " + str, 16);
        if (this.filterTags) {
            str = str.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&").replaceAll(SSA_H_TAG_STRING, "").replaceAll(SSA_ENTER_TAG_STRING, "\n").replaceAll("\\{.*?\\}", "");
        }
        return str.replace("\\n", " ");
    }
}
