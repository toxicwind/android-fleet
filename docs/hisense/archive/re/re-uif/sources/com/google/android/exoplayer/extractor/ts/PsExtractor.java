package com.google.android.exoplayer.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class PsExtractor implements Extractor {
    private boolean foundAllTracks;
    private boolean foundAudioTrack;
    private boolean foundVideoTrack;
    private ExtractorOutput output;
    private final ParsableByteArray psPacketBuffer;
    private final SparseArray<PesReader> psPayloadReaders;
    private final PtsTimestampAdjuster ptsTimestampAdjuster;

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    public PsExtractor() {
        this(new PtsTimestampAdjuster(0L));
    }

    public PsExtractor(PtsTimestampAdjuster ptsTimestampAdjuster) {
        this.ptsTimestampAdjuster = ptsTimestampAdjuster;
        this.psPacketBuffer = new ParsableByteArray(4096);
        this.psPayloadReaders = new SparseArray<>();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        byte[] bArr = new byte[14];
        extractorInput.peekFully(bArr, 0, 14);
        if (442 != (((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255)) || (bArr[4] & 196) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        extractorInput.advancePeekPosition(bArr[13] & 7);
        extractorInput.peekFully(bArr, 0, 3);
        return 1 == ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8)) | (bArr[2] & 255));
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
        extractorOutput.seekMap(SeekMap.UNSEEKABLE);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.ptsTimestampAdjuster.reset();
        for (int i = 0; i < this.psPayloadReaders.size(); i++) {
            this.psPayloadReaders.valueAt(i).seek();
        }
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        if (!extractorInput.peekFully(this.psPacketBuffer.data, 0, 4, true)) {
            return -1;
        }
        this.psPacketBuffer.setPosition(0);
        int i = this.psPacketBuffer.readInt();
        if (i == 441) {
            return -1;
        }
        if (i == 442) {
            extractorInput.peekFully(this.psPacketBuffer.data, 0, 10);
            this.psPacketBuffer.setPosition(0);
            this.psPacketBuffer.skipBytes(9);
            extractorInput.skipFully((this.psPacketBuffer.readUnsignedByte() & 7) + 14);
            return 0;
        }
        if (i == 443) {
            extractorInput.peekFully(this.psPacketBuffer.data, 0, 2);
            this.psPacketBuffer.setPosition(0);
            extractorInput.skipFully(this.psPacketBuffer.readUnsignedShort() + 6);
            return 0;
        }
        if (((i & (-256)) >> 8) != 1) {
            extractorInput.skipFully(1);
            return 0;
        }
        int i2 = i & 255;
        PesReader pesReader = this.psPayloadReaders.get(i2);
        if (!this.foundAllTracks) {
            if (pesReader == null) {
                ElementaryStreamReader h262Reader = null;
                if (!this.foundAudioTrack && i2 == 189) {
                    h262Reader = new Ac3Reader(this.output.track(i2), false);
                    this.foundAudioTrack = true;
                } else if (!this.foundAudioTrack && (i2 & MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN) == 192) {
                    h262Reader = new MpegAudioReader(this.output.track(i2));
                    this.foundAudioTrack = true;
                } else if (!this.foundVideoTrack && (i2 & 240) == 224) {
                    h262Reader = new H262Reader(this.output.track(i2));
                    this.foundVideoTrack = true;
                }
                if (h262Reader != null) {
                    pesReader = new PesReader(h262Reader, this.ptsTimestampAdjuster);
                    this.psPayloadReaders.put(i2, pesReader);
                }
            }
            if ((this.foundAudioTrack && this.foundVideoTrack) || extractorInput.getPosition() > 1048576) {
                this.foundAllTracks = true;
                this.output.endTracks();
            }
        }
        extractorInput.peekFully(this.psPacketBuffer.data, 0, 2);
        this.psPacketBuffer.setPosition(0);
        int unsignedShort = this.psPacketBuffer.readUnsignedShort() + 6;
        if (pesReader == null) {
            extractorInput.skipFully(unsignedShort);
        } else {
            if (this.psPacketBuffer.capacity() < unsignedShort) {
                this.psPacketBuffer.reset(new byte[unsignedShort], unsignedShort);
            }
            extractorInput.readFully(this.psPacketBuffer.data, 0, unsignedShort);
            this.psPacketBuffer.setPosition(6);
            this.psPacketBuffer.setLimit(unsignedShort);
            pesReader.consume(this.psPacketBuffer, this.output);
            ParsableByteArray parsableByteArray = this.psPacketBuffer;
            parsableByteArray.setLimit(parsableByteArray.capacity());
        }
        return 0;
    }

    private static final class PesReader {
        private boolean dtsFlag;
        private int extendedHeaderLength;
        private final ElementaryStreamReader pesPayloadReader;
        private final ParsableBitArray pesScratch = new ParsableBitArray(new byte[64]);
        private boolean ptsFlag;
        private final PtsTimestampAdjuster ptsTimestampAdjuster;
        private boolean seenFirstDts;
        private long timeUs;

        public PesReader(ElementaryStreamReader elementaryStreamReader, PtsTimestampAdjuster ptsTimestampAdjuster) {
            this.pesPayloadReader = elementaryStreamReader;
            this.ptsTimestampAdjuster = ptsTimestampAdjuster;
        }

        public void seek() {
            this.seenFirstDts = false;
            this.pesPayloadReader.seek();
        }

        public void consume(ParsableByteArray parsableByteArray, ExtractorOutput extractorOutput) {
            parsableByteArray.readBytes(this.pesScratch.data, 0, 3);
            this.pesScratch.setPosition(0);
            parseHeader();
            parsableByteArray.readBytes(this.pesScratch.data, 0, this.extendedHeaderLength);
            this.pesScratch.setPosition(0);
            parseHeaderExtension();
            this.pesPayloadReader.packetStarted(this.timeUs, true);
            this.pesPayloadReader.consume(parsableByteArray);
            this.pesPayloadReader.packetFinished();
        }

        private void parseHeader() {
            this.pesScratch.skipBits(8);
            this.ptsFlag = this.pesScratch.readBit();
            this.dtsFlag = this.pesScratch.readBit();
            this.pesScratch.skipBits(6);
            this.extendedHeaderLength = this.pesScratch.readBits(8);
        }

        private void parseHeaderExtension() {
            this.timeUs = 0L;
            if (this.ptsFlag) {
                this.pesScratch.skipBits(4);
                long bits = ((long) this.pesScratch.readBits(3)) << 30;
                this.pesScratch.skipBits(1);
                long bits2 = bits | (((long) this.pesScratch.readBits(15)) << 15);
                this.pesScratch.skipBits(1);
                long bits3 = bits2 | ((long) this.pesScratch.readBits(15));
                this.pesScratch.skipBits(1);
                if (!this.seenFirstDts && this.dtsFlag) {
                    this.pesScratch.skipBits(4);
                    long bits4 = ((long) this.pesScratch.readBits(3)) << 30;
                    this.pesScratch.skipBits(1);
                    long bits5 = bits4 | (((long) this.pesScratch.readBits(15)) << 15);
                    this.pesScratch.skipBits(1);
                    long bits6 = bits5 | ((long) this.pesScratch.readBits(15));
                    this.pesScratch.skipBits(1);
                    this.ptsTimestampAdjuster.adjustTimestamp(bits6);
                    this.seenFirstDts = true;
                }
                this.timeUs = this.ptsTimestampAdjuster.adjustTimestamp(bits3);
            }
        }
    }
}
