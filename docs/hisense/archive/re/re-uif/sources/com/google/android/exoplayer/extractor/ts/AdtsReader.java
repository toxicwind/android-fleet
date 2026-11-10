package com.google.android.exoplayer.extractor.ts;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
final class AdtsReader extends ElementaryStreamReader {
    private static final byte[] ID3_IDENTIFIER = {73, 68, 51};
    private final ParsableBitArray adtsScratch;
    private int bytesRead;
    private TrackOutput currentOutput;
    private long currentSampleDuration;
    private boolean hasCrc;
    private boolean hasOutputFormat;
    private final ParsableByteArray id3HeaderBuffer;
    private final TrackOutput id3Output;
    private int matchState;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public AdtsReader(TrackOutput trackOutput, TrackOutput trackOutput2) {
        super(trackOutput);
        this.id3Output = trackOutput2;
        trackOutput2.format(MediaFormat.createId3Format());
        this.adtsScratch = new ParsableBitArray(new byte[7]);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(ID3_IDENTIFIER, 10));
        setFindingSampleState();
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void seek() {
        setFindingSampleState();
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, boolean z) {
        this.timeUs = j;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i == 0) {
                findNextSample(parsableByteArray);
            } else if (i != 1) {
                if (i == 2) {
                    if (continueRead(parsableByteArray, this.adtsScratch.data, this.hasCrc ? 7 : 5)) {
                        parseAdtsHeader();
                    }
                } else if (i == 3) {
                    readSample(parsableByteArray);
                }
            } else if (continueRead(parsableByteArray, this.id3HeaderBuffer.data, 10)) {
                parseId3Header();
            }
        }
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        int i2 = this.bytesRead + iMin;
        this.bytesRead = i2;
        return i2 == i;
    }

    private void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = 256;
    }

    private void setReadingId3HeaderState() {
        this.state = 1;
        this.bytesRead = ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    private void setReadingSampleState(TrackOutput trackOutput, long j, int i, int i2) {
        this.state = 3;
        this.bytesRead = i;
        this.currentOutput = trackOutput;
        this.currentSampleDuration = j;
        this.sampleSize = i2;
    }

    private void setReadingAdtsHeaderState() {
        this.state = 2;
        this.bytesRead = 0;
    }

    private void findNextSample(ParsableByteArray parsableByteArray) {
        byte[] bArr = parsableByteArray.data;
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        while (position < iLimit) {
            int i = position + 1;
            int i2 = bArr[position] & 255;
            if (this.matchState == 512 && i2 >= 240 && i2 != 255) {
                this.hasCrc = (i2 & 1) == 0;
                setReadingAdtsHeaderState();
                parsableByteArray.setPosition(i);
                return;
            }
            int i3 = this.matchState;
            int i4 = i2 | i3;
            if (i4 == 329) {
                this.matchState = 768;
            } else if (i4 == 511) {
                this.matchState = 512;
            } else if (i4 == 836) {
                this.matchState = 1024;
            } else if (i4 == 1075) {
                setReadingId3HeaderState();
                parsableByteArray.setPosition(i);
                return;
            } else if (i3 != 256) {
                this.matchState = 256;
                i--;
            }
            position = i;
        }
        parsableByteArray.setPosition(position);
    }

    private void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        setReadingSampleState(this.id3Output, 0L, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    private void parseAdtsHeader() {
        this.adtsScratch.setPosition(0);
        if (!this.hasOutputFormat) {
            int bits = this.adtsScratch.readBits(2) + 1;
            if (bits != 2) {
                Log.w("AdtsReader", "Detected audio object type: " + bits + ", but assuming AAC LC.");
                bits = 2;
            }
            int bits2 = this.adtsScratch.readBits(4);
            this.adtsScratch.skipBits(1);
            byte[] bArrBuildAacAudioSpecificConfig = CodecSpecificDataUtil.buildAacAudioSpecificConfig(bits, bits2, this.adtsScratch.readBits(3));
            Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArrBuildAacAudioSpecificConfig);
            MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(null, "audio/mp4a-latm", -1, -1, -1L, ((Integer) aacAudioSpecificConfig.second).intValue(), ((Integer) aacAudioSpecificConfig.first).intValue(), Collections.singletonList(bArrBuildAacAudioSpecificConfig), null);
            this.sampleDurationUs = 1024000000 / ((long) mediaFormatCreateAudioFormat.sampleRate);
            this.output.format(mediaFormatCreateAudioFormat);
            this.hasOutputFormat = true;
        } else {
            this.adtsScratch.skipBits(10);
        }
        this.adtsScratch.skipBits(4);
        int bits3 = (this.adtsScratch.readBits(13) - 2) - 5;
        if (this.hasCrc) {
            bits3 -= 2;
        }
        setReadingSampleState(this.output, this.sampleDurationUs, 0, bits3);
    }

    private void readSample(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(parsableByteArray, iMin);
        int i = this.bytesRead + iMin;
        this.bytesRead = i;
        int i2 = this.sampleSize;
        if (i == i2) {
            this.currentOutput.sampleMetadata(this.timeUs, 1, i2, 0, null);
            this.timeUs += this.currentSampleDuration;
            setFindingSampleState();
        }
    }
}
