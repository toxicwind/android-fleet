package com.google.android.exoplayer.text.srt;

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

/* JADX INFO: loaded from: classes.dex */
public class InternalSrtParser implements SubtitleParser {
    private final boolean filterTags;

    public InternalSrtParser() {
        this(true);
    }

    public InternalSrtParser(boolean z) {
        this.filterTags = z;
    }

    public static int bytesToInt(byte[] bArr, int i, int i2) {
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
        String str;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        byte[] bArr2 = new byte[8];
        byteArrayInputStream.mark(8);
        if (-1 == byteArrayInputStream.read(bArr2, 0, 8)) {
            Log.d("InternalSrtParser", "inputStream read is -1");
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
                        if (line != null) {
                            MtkDebugLogUtil.log("InternalSrtParser", "parse line = " + line, 16);
                            if (!line.isEmpty()) {
                                if (!str.isEmpty()) {
                                    str = str + "\n";
                                }
                                str = str + processCueText(line.trim());
                            }
                        } else {
                            try {
                                break;
                            } catch (IOException unused) {
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
        str = "";
        bufferedReader.close();
        if (!str.isEmpty()) {
            return new DefaultSubtitle(str, 0L, j);
        }
        return new DefaultSubtitle();
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/srt".equals(str);
        MtkDebugLogUtil.log("InternalSrtParser", "canParse  type = " + str + " can = " + zEquals, 16);
        return zEquals;
    }

    protected String processCueText(String str) {
        return this.filterTags ? str.replaceAll("</?[i|I|u|U|B|b]>", "").replaceAll("\\<.*?\\>", "").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&nbsp;", " ").replaceAll("&amp;", "&") : str;
    }
}
