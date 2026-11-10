package com.google.android.exoplayer.text.webvtt;

import android.util.Log;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class Mp4WebvttParser implements SubtitleParser {
    private static final int TYPE_payl = Util.getIntegerCodeForString("payl");
    private static final int TYPE_sttg = Util.getIntegerCodeForString("sttg");
    private static final int TYPE_vttc = Util.getIntegerCodeForString("vttc");
    private final ParsableByteArray sampleData = new ParsableByteArray();
    private final WebvttCue.Builder builder = new WebvttCue.Builder();

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        Log.d("Mp4WebvttParser", "mp4vtt canParse" + "application/x-mp4vtt".equals(str));
        return "application/x-mp4vtt".equals(str);
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Mp4WebvttSubtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        this.sampleData.reset(bArr, i2 + i);
        this.sampleData.setPosition(i);
        ArrayList arrayList = new ArrayList();
        while (this.sampleData.bytesLeft() > 0) {
            if (this.sampleData.bytesLeft() < 8) {
                throw new ParserException("Incomplete Mp4Webvtt Top Level box header found.");
            }
            int i3 = this.sampleData.readInt();
            if (this.sampleData.readInt() == TYPE_vttc) {
                arrayList.add(parseVttCueBox(this.sampleData, this.builder, i3 - 8));
            } else {
                this.sampleData.skipBytes(i3 - 8);
            }
        }
        return new Mp4WebvttSubtitle(arrayList);
    }

    private static Cue parseVttCueBox(ParsableByteArray parsableByteArray, WebvttCue.Builder builder, int i) throws ParserException {
        builder.reset();
        while (i > 0) {
            if (i < 8) {
                throw new ParserException("Incomplete vtt cue box header found.");
            }
            int i2 = parsableByteArray.readInt();
            int i3 = parsableByteArray.readInt();
            int i4 = i2 - 8;
            String str = new String(parsableByteArray.data, parsableByteArray.getPosition(), i4);
            parsableByteArray.skipBytes(i4);
            i = (i - 8) - i4;
            if (i3 == TYPE_sttg) {
                WebvttCueParser.parseCueSettingsList(str, builder);
            } else if (i3 == TYPE_payl) {
                WebvttCueParser.parseCueText(str.trim(), builder);
            }
        }
        return builder.build();
    }
}
