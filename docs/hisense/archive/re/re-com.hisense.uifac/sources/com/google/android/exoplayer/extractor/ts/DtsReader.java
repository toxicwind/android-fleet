package com.google.android.exoplayer.extractor.ts;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.DtsUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class DtsReader extends ElementaryStreamReader {
    private int bytesRead;
    private final ParsableByteArray headerScratchBytes;
    private MediaFormat mediaFormat;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private int syncBytes;
    private long timeUs;

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public DtsReader(TrackOutput trackOutput) {
        super(trackOutput);
        this.headerScratchBytes = new ParsableByteArray(new byte[15]);
        this.state = 0;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.syncBytes = 0;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, boolean z) {
        this.timeUs = j;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, iMin);
                        int i2 = this.bytesRead + iMin;
                        this.bytesRead = i2;
                        int i3 = this.sampleSize;
                        if (i2 == i3) {
                            this.output.sampleMetadata(this.timeUs, 1, i3, 0, null);
                            this.timeUs += this.sampleDurationUs;
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 15)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 15);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.bytesRead = 4;
                this.state = 1;
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

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        if (this.syncBytes != 0) {
            this.syncBytes = 0;
        }
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.syncBytes << 8;
            this.syncBytes = i;
            int unsignedByte = i | parsableByteArray.readUnsignedByte();
            this.syncBytes = unsignedByte;
            if (unsignedByte == 2147385345) {
                byte[] bArr = this.headerScratchBytes.data;
                bArr[0] = ByteCompanionObject.MAX_VALUE;
                bArr[1] = -2;
                bArr[2] = ByteCompanionObject.MIN_VALUE;
                bArr[3] = 1;
                return true;
            }
            if (unsignedByte == 1683496997) {
                byte[] bArr2 = this.headerScratchBytes.data;
                bArr2[0] = 100;
                bArr2[1] = 88;
                bArr2[2] = 32;
                bArr2[3] = 37;
                return true;
            }
        }
        return false;
    }

    private void parseHeader() {
        byte[] bArr = this.headerScratchBytes.data;
        if (this.syncBytes == 2147385345) {
            if (this.mediaFormat == null) {
                MediaFormat dtsFormat = DtsUtil.parseDtsFormat(bArr, null, -1L, null);
                this.mediaFormat = dtsFormat;
                this.output.format(dtsFormat);
            }
            this.sampleSize = DtsUtil.getDtsFrameSize(bArr);
        }
        if (this.syncBytes == 1683496997) {
            if (this.mediaFormat == null) {
                MediaFormat dtsFormatSubStream = DtsUtil.parseDtsFormatSubStream(bArr, null, -1L, null);
                this.mediaFormat = dtsFormatSubStream;
                this.output.format(dtsFormatSubStream);
            }
            this.sampleSize = DtsUtil.getDtsFrameSizeSubStream(bArr);
        }
        this.sampleDurationUs = (int) ((((long) DtsUtil.parseDtsAudioSampleCount(bArr)) * 1000000) / ((long) this.mediaFormat.sampleRate));
    }
}
