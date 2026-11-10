package com.google.android.exoplayer.extractor.flv;

import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class FlvExtractor implements Extractor, SeekMap {
    private static final int FLV_TAG = Util.getIntegerCodeForString("FLV");
    private AudioTagPayloadReader audioReader;
    private int bytesToNextTagHeader;
    private ExtractorOutput extractorOutput;
    private ScriptTagPayloadReader metadataReader;
    public int tagDataSize;
    public long tagTimestampUs;
    public int tagType;
    private VideoTagPayloadReader videoReader;
    private final ParsableByteArray scratch = new ParsableByteArray(4);
    private final ParsableByteArray headerBuffer = new ParsableByteArray(9);
    private final ParsableByteArray tagHeaderBuffer = new ParsableByteArray(11);
    private final ParsableByteArray tagData = new ParsableByteArray();
    private int parserState = 1;

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        return 0L;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return false;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        extractorInput.peekFully(this.scratch.data, 0, 3);
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt24() != FLV_TAG) {
            return false;
        }
        extractorInput.peekFully(this.scratch.data, 0, 2);
        this.scratch.setPosition(0);
        if ((this.scratch.readUnsignedShort() & 250) != 0) {
            return false;
        }
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        int i = this.scratch.readInt();
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        extractorInput.peekFully(this.scratch.data, 0, 4);
        this.scratch.setPosition(0);
        return this.scratch.readInt() == 0;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.parserState = 1;
        this.bytesToNextTagHeader = 0;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        while (true) {
            int i = this.parserState;
            if (i != 1) {
                if (i == 2) {
                    skipToTagHeader(extractorInput);
                } else if (i == 3) {
                    if (!readTagHeader(extractorInput)) {
                        return -1;
                    }
                } else if (i == 4 && readTagData(extractorInput)) {
                    return 0;
                }
            } else if (!readFlvHeader(extractorInput)) {
                return -1;
            }
        }
    }

    private boolean readFlvHeader(ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (!extractorInput.readFully(this.headerBuffer.data, 0, 9, true)) {
            return false;
        }
        this.headerBuffer.setPosition(0);
        this.headerBuffer.skipBytes(4);
        int unsignedByte = this.headerBuffer.readUnsignedByte();
        boolean z = (unsignedByte & 4) != 0;
        boolean z2 = (unsignedByte & 1) != 0;
        if (z && this.audioReader == null) {
            this.audioReader = new AudioTagPayloadReader(this.extractorOutput.track(8));
        }
        if (z2 && this.videoReader == null) {
            this.videoReader = new VideoTagPayloadReader(this.extractorOutput.track(9));
        }
        if (this.metadataReader == null) {
            this.metadataReader = new ScriptTagPayloadReader(null);
        }
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
        this.bytesToNextTagHeader = (this.headerBuffer.readInt() - 9) + 4;
        this.parserState = 2;
        return true;
    }

    private void skipToTagHeader(ExtractorInput extractorInput) throws InterruptedException, IOException {
        extractorInput.skipFully(this.bytesToNextTagHeader);
        this.bytesToNextTagHeader = 0;
        this.parserState = 3;
    }

    private boolean readTagHeader(ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (!extractorInput.readFully(this.tagHeaderBuffer.data, 0, 11, true)) {
            return false;
        }
        this.tagHeaderBuffer.setPosition(0);
        this.tagType = this.tagHeaderBuffer.readUnsignedByte();
        this.tagDataSize = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = (((long) (this.tagHeaderBuffer.readUnsignedByte() << 24)) | this.tagTimestampUs) * 1000;
        this.tagHeaderBuffer.skipBytes(3);
        this.parserState = 4;
        return true;
    }

    private boolean readTagData(ExtractorInput extractorInput) throws InterruptedException, IOException {
        boolean z;
        ScriptTagPayloadReader scriptTagPayloadReader;
        VideoTagPayloadReader videoTagPayloadReader;
        AudioTagPayloadReader audioTagPayloadReader;
        if (this.tagType == 8 && (audioTagPayloadReader = this.audioReader) != null) {
            audioTagPayloadReader.consume(prepareTagData(extractorInput), this.tagTimestampUs);
        } else if (this.tagType == 9 && (videoTagPayloadReader = this.videoReader) != null) {
            videoTagPayloadReader.consume(prepareTagData(extractorInput), this.tagTimestampUs);
        } else {
            if (this.tagType == 18 && (scriptTagPayloadReader = this.metadataReader) != null) {
                scriptTagPayloadReader.consume(prepareTagData(extractorInput), this.tagTimestampUs);
                if (this.metadataReader.getDurationUs() != -1) {
                    AudioTagPayloadReader audioTagPayloadReader2 = this.audioReader;
                    if (audioTagPayloadReader2 != null) {
                        audioTagPayloadReader2.setDurationUs(this.metadataReader.getDurationUs());
                    }
                    VideoTagPayloadReader videoTagPayloadReader2 = this.videoReader;
                    if (videoTagPayloadReader2 != null) {
                        videoTagPayloadReader2.setDurationUs(this.metadataReader.getDurationUs());
                    }
                }
            } else {
                extractorInput.skipFully(this.tagDataSize);
                z = false;
            }
            this.bytesToNextTagHeader = 4;
            this.parserState = 2;
            return z;
        }
        z = true;
        this.bytesToNextTagHeader = 4;
        this.parserState = 2;
        return z;
    }

    private ParsableByteArray prepareTagData(ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (this.tagDataSize > this.tagData.capacity()) {
            ParsableByteArray parsableByteArray = this.tagData;
            parsableByteArray.reset(new byte[Math.max(parsableByteArray.capacity() * 2, this.tagDataSize)], 0);
        } else {
            this.tagData.setPosition(0);
        }
        this.tagData.setLimit(this.tagDataSize);
        extractorInput.readFully(this.tagData.data, 0, this.tagDataSize);
        return this.tagData;
    }
}
