package com.google.android.exoplayer.extractor.mp3;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.GaplessInfo;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.MpegAudioHeader;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class Mp3Extractor implements Extractor {
    private long basisTimeUs;
    private ExtractorOutput extractorOutput;
    private final long forcedFirstSampleTimestampUs;
    private GaplessInfo gaplessInfo;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private Seeker seeker;
    private final MpegAudioHeader synchronizedHeader;
    private int synchronizedHeaderData;
    private TrackOutput trackOutput;
    private static final int XING_HEADER = Util.getIntegerCodeForString("Xing");
    private static final int INFO_HEADER = Util.getIntegerCodeForString("Info");
    private static final int VBRI_HEADER = Util.getIntegerCodeForString("VBRI");

    interface Seeker extends SeekMap {
        long getDurationUs();

        long getTimeUs(long j);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    public Mp3Extractor() {
        this(-1L);
    }

    public Mp3Extractor(long j) {
        this.forcedFirstSampleTimestampUs = j;
        this.scratch = new ParsableByteArray(4);
        this.synchronizedHeader = new MpegAudioHeader();
        this.basisTimeUs = -1L;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return synchronize(extractorInput, true);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0);
        extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.synchronizedHeaderData = 0;
        this.samplesRead = 0L;
        this.basisTimeUs = -1L;
        this.sampleBytesRemaining = 0;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        if (this.synchronizedHeaderData == 0 && !synchronizeCatchingEndOfInput(extractorInput)) {
            return -1;
        }
        if (this.seeker == null) {
            setupSeeker(extractorInput);
            this.extractorOutput.seekMap(this.seeker);
            String str = this.synchronizedHeader.mimeType;
            long durationUs = this.seeker.getDurationUs();
            MpegAudioHeader mpegAudioHeader = this.synchronizedHeader;
            MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(null, str, -1, 4096, durationUs, mpegAudioHeader.channels, mpegAudioHeader.sampleRate, null, null);
            GaplessInfo gaplessInfo = this.gaplessInfo;
            if (gaplessInfo != null) {
                mediaFormatCreateAudioFormat = mediaFormatCreateAudioFormat.copyWithGaplessInfo(gaplessInfo.encoderDelay, gaplessInfo.encoderPadding);
            }
            this.trackOutput.format(mediaFormatCreateAudioFormat);
        }
        return readSample(extractorInput);
    }

    private int readSample(ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (this.sampleBytesRemaining == 0) {
            if (!maybeResynchronize(extractorInput)) {
                return -1;
            }
            if (this.basisTimeUs == -1) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != -1) {
                    this.basisTimeUs += this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0L);
                }
            }
            this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
        }
        int iSampleData = this.trackOutput.sampleData(extractorInput, this.sampleBytesRemaining, true);
        if (iSampleData == -1) {
            return -1;
        }
        int i = this.sampleBytesRemaining - iSampleData;
        this.sampleBytesRemaining = i;
        if (i > 0) {
            return 0;
        }
        long j = this.basisTimeUs;
        long j2 = this.samplesRead * 1000000;
        MpegAudioHeader mpegAudioHeader = this.synchronizedHeader;
        this.trackOutput.sampleMetadata(j + (j2 / ((long) mpegAudioHeader.sampleRate)), 1, mpegAudioHeader.frameSize, 0, null);
        this.samplesRead += (long) this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean maybeResynchronize(ExtractorInput extractorInput) throws InterruptedException, IOException {
        extractorInput.resetPeekPosition();
        if (!extractorInput.peekFully(this.scratch.data, 0, 4, true)) {
            return false;
        }
        this.scratch.setPosition(0);
        int i = this.scratch.readInt();
        if ((i & (-128000)) == ((-128000) & this.synchronizedHeaderData) && MpegAudioHeader.getFrameSize(i) != -1) {
            MpegAudioHeader.populateHeader(i, this.synchronizedHeader);
            return true;
        }
        this.synchronizedHeaderData = 0;
        extractorInput.skipFully(1);
        return synchronizeCatchingEndOfInput(extractorInput);
    }

    private boolean synchronizeCatchingEndOfInput(ExtractorInput extractorInput) throws InterruptedException, IOException {
        try {
            return synchronize(extractorInput, false);
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean synchronize(ExtractorInput extractorInput, boolean z) throws InterruptedException, IOException {
        int peekPosition;
        int i;
        int frameSize;
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            this.gaplessInfo = Id3Util.parseId3(extractorInput);
            peekPosition = (int) extractorInput.getPeekPosition();
            if (!z) {
                extractorInput.skipFully(peekPosition);
            }
            i = 0;
        } else {
            peekPosition = 0;
            i = 0;
        }
        int i2 = i;
        while (true) {
            int i3 = i2;
            while (true) {
                if (z && i == 4096) {
                    return false;
                }
                if (!z && i == 131072) {
                    throw new ParserException("Searched too many bytes.");
                }
                if (!extractorInput.peekFully(this.scratch.data, 0, 4, true)) {
                    return false;
                }
                this.scratch.setPosition(0);
                int i4 = this.scratch.readInt();
                if ((i2 != 0 && (i4 & (-128000)) != ((-128000) & i2)) || (frameSize = MpegAudioHeader.getFrameSize(i4)) == -1) {
                    break;
                }
                i3++;
                if (i3 == 1) {
                    MpegAudioHeader.populateHeader(i4, this.synchronizedHeader);
                    i2 = i4;
                } else if (i3 == 4) {
                    if (z) {
                        extractorInput.skipFully(peekPosition + i);
                    } else {
                        extractorInput.resetPeekPosition();
                    }
                    this.synchronizedHeaderData = i2;
                    return true;
                }
                extractorInput.advancePeekPosition(frameSize - 4);
            }
            i++;
            if (z) {
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(peekPosition + i);
            } else {
                extractorInput.skipFully(1);
            }
            i2 = 0;
        }
    }

    private void setupSeeker(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i;
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.synchronizedHeader.frameSize);
        extractorInput.peekFully(parsableByteArray.data, 0, this.synchronizedHeader.frameSize);
        long position = extractorInput.getPosition();
        long length = extractorInput.getLength();
        MpegAudioHeader mpegAudioHeader = this.synchronizedHeader;
        int i2 = 21;
        if ((mpegAudioHeader.version & 1) != 0) {
            if (mpegAudioHeader.channels != 1) {
                i2 = 36;
            }
        } else if (mpegAudioHeader.channels == 1) {
            i2 = 13;
        }
        if (parsableByteArray.limit() >= i2 + 4) {
            parsableByteArray.setPosition(i2);
            i = parsableByteArray.readInt();
        } else {
            i = 0;
        }
        if (i == XING_HEADER || i == INFO_HEADER) {
            XingSeeker xingSeekerCreate = XingSeeker.create(this.synchronizedHeader, parsableByteArray, position, length);
            this.seeker = xingSeekerCreate;
            if (xingSeekerCreate != null && this.gaplessInfo == null) {
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(i2 + MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BRDCSTING_SVC);
                extractorInput.peekFully(this.scratch.data, 0, 3);
                this.scratch.setPosition(0);
                this.gaplessInfo = GaplessInfo.createFromXingHeaderValue(this.scratch.readUnsignedInt24());
            }
            extractorInput.skipFully(this.synchronizedHeader.frameSize);
        } else if (parsableByteArray.limit() >= 40) {
            parsableByteArray.setPosition(36);
            if (parsableByteArray.readInt() == VBRI_HEADER) {
                this.seeker = VbriSeeker.create(this.synchronizedHeader, parsableByteArray, position, length);
                extractorInput.skipFully(this.synchronizedHeader.frameSize);
            }
        }
        if (this.seeker == null) {
            extractorInput.resetPeekPosition();
            extractorInput.peekFully(this.scratch.data, 0, 4);
            this.scratch.setPosition(0);
            MpegAudioHeader.populateHeader(this.scratch.readInt(), this.synchronizedHeader);
            this.seeker = new ConstantBitrateSeeker(extractorInput.getPosition(), this.synchronizedHeader.bitrate, length);
        }
    }
}
