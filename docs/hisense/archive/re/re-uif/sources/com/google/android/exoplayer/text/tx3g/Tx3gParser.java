package com.google.android.exoplayer.text.tx3g;

import android.util.Log;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.ParsableByteArray;

/* JADX INFO: loaded from: classes.dex */
public final class Tx3gParser implements SubtitleParser {
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("tx3g canParse");
        sb.append("text/3gpp-tt".equals(str) || "application/x-quicktime-tx3g".equals(str));
        Log.d("Tx3gParser", sb.toString());
        return "text/3gpp-tt".equals(str) || "application/x-quicktime-tx3g".equals(str);
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) {
        this.parsableByteArray.reset(bArr, i2);
        int unsignedShort = this.parsableByteArray.readUnsignedShort();
        if (unsignedShort == 0) {
            return Tx3gSubtitle.EMPTY;
        }
        return new Tx3gSubtitle(new Cue(this.parsableByteArray.readString(unsignedShort)));
    }
}
