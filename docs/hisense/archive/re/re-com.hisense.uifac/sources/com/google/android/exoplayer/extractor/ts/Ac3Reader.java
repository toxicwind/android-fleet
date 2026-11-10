package com.google.android.exoplayer.extractor.ts;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;

/* JADX INFO: loaded from: classes.dex */
final class Ac3Reader extends ElementaryStreamReader {
    private int bytesRead;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private final boolean isEac3;
    private boolean lastByteWas0B;
    private MediaFormat mediaFormat;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public Ac3Reader(TrackOutput trackOutput, boolean z) {
        super(trackOutput);
        this.isEac3 = z;
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[8]);
        this.headerScratchBits = parsableBitArray;
        this.headerScratchBytes = new ParsableByteArray(parsableBitArray.data);
        this.state = 0;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWas0B = false;
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
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 8)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 8);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
                byte[] bArr = this.headerScratchBytes.data;
                bArr[0] = 11;
                bArr[1] = 119;
                this.bytesRead = 2;
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
        while (true) {
            if (parsableByteArray.bytesLeft() <= 0) {
                return false;
            }
            if (!this.lastByteWas0B) {
                this.lastByteWas0B = parsableByteArray.readUnsignedByte() == 11;
            } else {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                if (unsignedByte == 119) {
                    this.lastByteWas0B = false;
                    return true;
                }
                this.lastByteWas0B = unsignedByte == 11;
            }
        }
    }

    private void parseHeader() {
        int ac3SyncframeAudioSampleCount;
        MediaFormat ac3SyncframeFormat;
        if (this.mediaFormat == null) {
            if (this.isEac3) {
                ac3SyncframeFormat = Ac3Util.parseEac3SyncframeFormat(this.headerScratchBits, null, -1L, null);
            } else {
                ac3SyncframeFormat = Ac3Util.parseAc3SyncframeFormat(this.headerScratchBits, null, -1L, null);
            }
            this.mediaFormat = ac3SyncframeFormat;
            this.output.format(ac3SyncframeFormat);
        }
        this.sampleSize = this.isEac3 ? Ac3Util.parseEAc3SyncframeSize(this.headerScratchBits.data) : Ac3Util.parseAc3SyncframeSize(this.headerScratchBits.data);
        if (this.isEac3) {
            ac3SyncframeAudioSampleCount = Ac3Util.parseEAc3SyncframeAudioSampleCount(this.headerScratchBits.data);
        } else {
            ac3SyncframeAudioSampleCount = Ac3Util.getAc3SyncframeAudioSampleCount();
        }
        this.sampleDurationUs = (int) ((((long) ac3SyncframeAudioSampleCount) * 1000000) / ((long) this.mediaFormat.sampleRate));
    }
}
