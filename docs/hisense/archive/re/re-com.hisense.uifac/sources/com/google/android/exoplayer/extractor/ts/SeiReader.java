package com.google.android.exoplayer.extractor.ts;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.text.eia608.Eia608Parser;
import com.google.android.exoplayer.util.ParsableByteArray;

/* JADX INFO: loaded from: classes.dex */
final class SeiReader {
    private final TrackOutput output;

    public SeiReader(TrackOutput trackOutput) {
        this.output = trackOutput;
        trackOutput.format(MediaFormat.createTextFormat(null, "application/eia-608", -1, -1L, null));
    }

    public void consume(long j, ParsableByteArray parsableByteArray) {
        int unsignedByte;
        int i;
        while (parsableByteArray.bytesLeft() > 1) {
            int i2 = 0;
            int i3 = 0;
            do {
                unsignedByte = parsableByteArray.readUnsignedByte();
                i3 += unsignedByte;
            } while (unsignedByte == 255);
            while (true) {
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                i = i2 + unsignedByte2;
                if (unsignedByte2 != 255) {
                    break;
                } else {
                    i2 = i;
                }
            }
            if (Eia608Parser.isSeiMessageEia608(i3, i, parsableByteArray)) {
                this.output.sampleData(parsableByteArray, i);
                this.output.sampleMetadata(j, 1, i, 0, null);
            } else {
                parsableByteArray.skipBytes(i);
            }
        }
    }
}
