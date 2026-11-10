package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class VorbisReader extends StreamReader implements SeekMap {
    private long audioStartPosition;
    private VorbisUtil.CommentHeader commentHeader;
    private long duration;
    private long elapsedSamples;
    private long inputLength;
    private int previousPacketBlockSize;
    private boolean seenFirstAudioPacket;
    private long totalSamples;
    private VorbisUtil.VorbisIdHeader vorbisIdHeader;
    private VorbisSetup vorbisSetup;
    private final OggSeeker oggSeeker = new OggSeeker();
    private long targetGranule = -1;

    VorbisReader() {
    }

    static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        try {
            return VorbisUtil.verifyVorbisHeaderCapturePattern(1, parsableByteArray, true);
        } catch (ParserException unused) {
            return false;
        }
    }

    @Override // com.google.android.exoplayer.extractor.ogg.StreamReader
    public void seek() {
        super.seek();
        this.previousPacketBlockSize = 0;
        this.elapsedSamples = 0L;
        this.seenFirstAudioPacket = false;
    }

    @Override // com.google.android.exoplayer.extractor.ogg.StreamReader
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        if (this.totalSamples == 0) {
            if (this.vorbisSetup == null) {
                this.inputLength = extractorInput.getLength();
                this.vorbisSetup = readSetupHeaders(extractorInput, this.scratch);
                this.audioStartPosition = extractorInput.getPosition();
                this.extractorOutput.seekMap(this);
                if (this.inputLength != -1) {
                    if (positionHolder != null) {
                        positionHolder.position = Math.max(0L, extractorInput.getLength() - 8000);
                    }
                    return 1;
                }
            }
            this.totalSamples = this.inputLength == -1 ? -1L : this.oggParser.readGranuleOfLastPage(extractorInput);
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.vorbisSetup.idHeader.data);
            arrayList.add(this.vorbisSetup.setupHeaderData);
            long j = this.inputLength == -1 ? -1L : (this.totalSamples * 1000000) / this.vorbisSetup.idHeader.sampleRate;
            this.duration = j;
            TrackOutput trackOutput = this.trackOutput;
            VorbisUtil.VorbisIdHeader vorbisIdHeader = this.vorbisSetup.idHeader;
            trackOutput.format(MediaFormat.createAudioFormat(null, "audio/vorbis", vorbisIdHeader.bitrateNominal, 65025, j, vorbisIdHeader.channels, (int) vorbisIdHeader.sampleRate, arrayList, null));
            long j2 = this.inputLength;
            if (j2 != -1) {
                this.oggSeeker.setup(j2 - this.audioStartPosition, this.totalSamples);
                if (positionHolder != null) {
                    positionHolder.position = this.audioStartPosition;
                }
                return 1;
            }
        }
        if (!this.seenFirstAudioPacket && this.targetGranule > -1) {
            OggUtil.skipToNextPage(extractorInput);
            long nextSeekPosition = this.oggSeeker.getNextSeekPosition(this.targetGranule, extractorInput);
            if (nextSeekPosition != -1) {
                if (positionHolder != null) {
                    positionHolder.position = nextSeekPosition;
                }
                return 1;
            }
            this.elapsedSamples = this.oggParser.skipToPageOfGranule(extractorInput, this.targetGranule);
            this.previousPacketBlockSize = this.vorbisIdHeader.blockSize0;
            this.seenFirstAudioPacket = true;
        }
        if (!this.oggParser.readPacket(extractorInput, this.scratch)) {
            return -1;
        }
        byte[] bArr = this.scratch.data;
        if ((bArr[0] & 1) != 1) {
            int iDecodeBlockSize = decodeBlockSize(bArr[0], this.vorbisSetup);
            long j3 = this.seenFirstAudioPacket ? (this.previousPacketBlockSize + iDecodeBlockSize) / 4 : 0;
            if (this.elapsedSamples + j3 >= this.targetGranule) {
                appendNumberOfSamples(this.scratch, j3);
                long j4 = (this.elapsedSamples * 1000000) / this.vorbisSetup.idHeader.sampleRate;
                TrackOutput trackOutput2 = this.trackOutput;
                ParsableByteArray parsableByteArray = this.scratch;
                trackOutput2.sampleData(parsableByteArray, parsableByteArray.limit());
                this.trackOutput.sampleMetadata(j4, 1, this.scratch.limit(), 0, null);
                this.targetGranule = -1L;
            }
            this.seenFirstAudioPacket = true;
            this.elapsedSamples += j3;
            this.previousPacketBlockSize = iDecodeBlockSize;
        }
        this.scratch.reset();
        return 0;
    }

    VorbisSetup readSetupHeaders(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws InterruptedException, IOException {
        if (this.vorbisIdHeader == null) {
            this.oggParser.readPacket(extractorInput, parsableByteArray);
            this.vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(parsableByteArray);
            parsableByteArray.reset();
        }
        if (this.commentHeader == null) {
            this.oggParser.readPacket(extractorInput, parsableByteArray);
            this.commentHeader = VorbisUtil.readVorbisCommentHeader(parsableByteArray);
            parsableByteArray.reset();
        }
        this.oggParser.readPacket(extractorInput, parsableByteArray);
        byte[] bArr = new byte[parsableByteArray.limit()];
        System.arraycopy(parsableByteArray.data, 0, bArr, 0, parsableByteArray.limit());
        VorbisUtil.Mode[] vorbisModes = VorbisUtil.readVorbisModes(parsableByteArray, this.vorbisIdHeader.channels);
        int iILog = VorbisUtil.iLog(vorbisModes.length - 1);
        parsableByteArray.reset();
        return new VorbisSetup(this.vorbisIdHeader, this.commentHeader, bArr, vorbisModes, iILog);
    }

    static void appendNumberOfSamples(ParsableByteArray parsableByteArray, long j) {
        parsableByteArray.setLimit(parsableByteArray.limit() + 4);
        parsableByteArray.data[parsableByteArray.limit() - 4] = (byte) (j & 255);
        parsableByteArray.data[parsableByteArray.limit() - 3] = (byte) ((j >>> 8) & 255);
        parsableByteArray.data[parsableByteArray.limit() - 2] = (byte) ((j >>> 16) & 255);
        parsableByteArray.data[parsableByteArray.limit() - 1] = (byte) ((j >>> 24) & 255);
    }

    private static int decodeBlockSize(byte b, VorbisSetup vorbisSetup) {
        if (!vorbisSetup.modes[OggUtil.readBits(b, vorbisSetup.iLogModes, 1)].blockFlag) {
            return vorbisSetup.idHeader.blockSize0;
        }
        return vorbisSetup.idHeader.blockSize1;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return (this.vorbisSetup == null || this.inputLength == -1) ? false : true;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        if (j == 0) {
            this.targetGranule = -1L;
            return this.audioStartPosition;
        }
        this.targetGranule = (this.vorbisSetup.idHeader.sampleRate * j) / 1000000;
        long j2 = this.audioStartPosition;
        return Math.max(j2, (((this.inputLength - j2) * j) / this.duration) - 4000);
    }

    static final class VorbisSetup {
        public final int iLogModes;
        public final VorbisUtil.VorbisIdHeader idHeader;
        public final VorbisUtil.Mode[] modes;
        public final byte[] setupHeaderData;

        public VorbisSetup(VorbisUtil.VorbisIdHeader vorbisIdHeader, VorbisUtil.CommentHeader commentHeader, byte[] bArr, VorbisUtil.Mode[] modeArr, int i) {
            this.idHeader = vorbisIdHeader;
            this.setupHeaderData = bArr;
            this.modes = modeArr;
            this.iLogModes = i;
        }
    }
}
