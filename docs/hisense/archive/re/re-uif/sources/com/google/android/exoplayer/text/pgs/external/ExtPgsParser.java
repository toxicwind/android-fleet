package com.google.android.exoplayer.text.pgs.external;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.DefaultSubtitle;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.pgs.PgsFactory;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class ExtPgsParser implements SubtitleParser {
    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        try {
            return new PgsSubtitle(new PgsFactory((InputStream) byteArrayInputStream, false), 0L);
        } catch (Exception e) {
            e.printStackTrace();
            return new DefaultSubtitle();
        } finally {
            try {
                byteArrayInputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/ext/pgs".equals(str);
        MtkDebugLogUtil.log("ExtPgsParser", "canParse  type = " + str + " can = " + zEquals, 16);
        return zEquals;
    }
}
