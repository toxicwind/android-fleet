package com.google.android.exoplayer.text.dvb;

import android.graphics.Bitmap;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DvbParser implements SubtitleParser {
    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/dvb".equals(str);
        MtkDebugLogUtil.log("DvbParser", "canParse mimeType = " + str + " can = " + zEquals, 16);
        return zEquals;
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        byte[] bArr2 = new byte[20480];
        if (i2 >= 20480) {
            throw new ParserException("parse stream data is too large");
        }
        System.arraycopy(bArr, i, bArr2, 0, i2);
        DvbParserFactory dvbParserFactory = new DvbParserFactory(Arrays.copyOfRange(bArr2, 0, i2));
        MtkDebugLogUtil.log("DvbParser", "parse read len  = " + i2 + " dpf = " + dvbParserFactory.cacheBmp + "off = " + dvbParserFactory.timeOff, 16);
        Bitmap bitmap = dvbParserFactory.cacheBmp;
        if (bitmap != null) {
            return new DvbSubtitle(bitmap, 0L, dvbParserFactory.timeOff);
        }
        return new DvbSubtitle();
    }
}
