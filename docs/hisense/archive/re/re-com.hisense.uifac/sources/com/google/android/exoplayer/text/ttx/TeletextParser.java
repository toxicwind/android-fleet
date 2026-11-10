package com.google.android.exoplayer.text.ttx;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TeletextParser implements SubtitleParser {
    private String privText = null;
    private int count = 0;

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        boolean zEquals = "text/teletext".equals(str);
        MtkDebugLogUtil.log("TeletextParser", "canParse mimeType = " + str + " can = " + zEquals, 16);
        return zEquals;
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        byte[] bArr2 = new byte[20480];
        if (i2 >= 20480) {
            throw new ParserException("parse stream data is too large");
        }
        System.arraycopy(bArr, i, bArr2, 0, i2);
        try {
            TeletextParserFactory teletextParserFactory = new TeletextParserFactory(Arrays.copyOfRange(bArr2, 0, i2));
            MtkDebugLogUtil.log("TeletextParser", "parse read len  = " + i2 + " tpf = " + teletextParserFactory.packetTxt, 16);
            if (teletextParserFactory.packetTxt != null && !teletextParserFactory.packetTxt.isEmpty()) {
                this.privText = teletextParserFactory.packetTxt;
                this.count = 0;
                Thread.sleep(2000L);
                MtkDebugLogUtil.log("TeletextParser", "parse success. txt: " + teletextParserFactory.packetTxt, 16);
                return new TeletextSubtitle(teletextParserFactory.packetTxt);
            }
            if (this.count >= 0 && this.count < 2 && this.privText != null) {
                return new TeletextSubtitle(this.privText);
            }
            this.count++;
            this.count = 0;
            return new TeletextSubtitle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
