package com.google.android.exoplayer.extractor.flv;

import android.util.Pair;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
final class AudioTagPayloadReader extends TagPayloadReader {
    private static final int[] AUDIO_SAMPLING_RATE_TABLE = {5500, 11000, 22000, 44000};
    private boolean hasOutputFormat;
    private boolean hasParsedAudioDataHeader;

    public AudioTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
    }

    @Override // com.google.android.exoplayer.extractor.flv.TagPayloadReader
    protected boolean parseHeader(ParsableByteArray parsableByteArray) throws TagPayloadReader.UnsupportedFormatException {
        if (!this.hasParsedAudioDataHeader) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int i = (unsignedByte >> 4) & 15;
            int i2 = (unsignedByte >> 2) & 3;
            if (i2 < 0 || i2 >= AUDIO_SAMPLING_RATE_TABLE.length) {
                throw new TagPayloadReader.UnsupportedFormatException("Invalid sample rate index: " + i2);
            }
            if (i != 10) {
                throw new TagPayloadReader.UnsupportedFormatException("Audio format not supported: " + i);
            }
            this.hasParsedAudioDataHeader = true;
        } else {
            parsableByteArray.skipBytes(1);
        }
        return true;
    }

    @Override // com.google.android.exoplayer.extractor.flv.TagPayloadReader
    protected void parsePayload(ParsableByteArray parsableByteArray, long j) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if (unsignedByte != 0 || this.hasOutputFormat) {
            if (unsignedByte == 1) {
                int iBytesLeft = parsableByteArray.bytesLeft();
                this.output.sampleData(parsableByteArray, iBytesLeft);
                this.output.sampleMetadata(j, 1, iBytesLeft, 0, null);
                return;
            }
            return;
        }
        int iBytesLeft2 = parsableByteArray.bytesLeft();
        byte[] bArr = new byte[iBytesLeft2];
        parsableByteArray.readBytes(bArr, 0, iBytesLeft2);
        Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
        this.output.format(MediaFormat.createAudioFormat(null, "audio/mp4a-latm", -1, -1, getDurationUs(), ((Integer) aacAudioSpecificConfig.second).intValue(), ((Integer) aacAudioSpecificConfig.first).intValue(), Collections.singletonList(bArr), null));
        this.hasOutputFormat = true;
    }
}
