package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class OggParser {
    private long elapsedSamples;
    private final OggUtil.PageHeader pageHeader = new OggUtil.PageHeader();
    private final ParsableByteArray headerArray = new ParsableByteArray(282);
    private final OggUtil.PacketInfoHolder holder = new OggUtil.PacketInfoHolder();
    private int currentSegmentIndex = -1;

    OggParser() {
    }

    public void reset() {
        this.pageHeader.reset();
        this.headerArray.reset();
        this.currentSegmentIndex = -1;
    }

    public boolean readPacket(ExtractorInput extractorInput, ParsableByteArray parsableByteArray) throws InterruptedException, IOException {
        int i;
        Assertions.checkState((extractorInput == null || parsableByteArray == null) ? false : true);
        boolean z = false;
        while (!z) {
            if (this.currentSegmentIndex < 0) {
                if (!OggUtil.populatePageHeader(extractorInput, this.pageHeader, this.headerArray, true)) {
                    return false;
                }
                OggUtil.PageHeader pageHeader = this.pageHeader;
                int i2 = pageHeader.headerSize;
                if ((pageHeader.type & 1) == 1 && parsableByteArray.limit() == 0) {
                    OggUtil.calculatePacketSize(this.pageHeader, 0, this.holder);
                    OggUtil.PacketInfoHolder packetInfoHolder = this.holder;
                    i = packetInfoHolder.segmentCount + 0;
                    i2 += packetInfoHolder.size;
                } else {
                    i = 0;
                }
                extractorInput.skipFully(i2);
                this.currentSegmentIndex = i;
            }
            OggUtil.calculatePacketSize(this.pageHeader, this.currentSegmentIndex, this.holder);
            int i3 = this.currentSegmentIndex;
            OggUtil.PacketInfoHolder packetInfoHolder2 = this.holder;
            int i4 = i3 + packetInfoHolder2.segmentCount;
            if (packetInfoHolder2.size > 0) {
                extractorInput.readFully(parsableByteArray.data, parsableByteArray.limit(), this.holder.size);
                parsableByteArray.setLimit(parsableByteArray.limit() + this.holder.size);
                z = this.pageHeader.laces[i4 + (-1)] != 255;
            }
            if (i4 == this.pageHeader.pageSegmentCount) {
                i4 = -1;
            }
            this.currentSegmentIndex = i4;
        }
        return true;
    }

    public long readGranuleOfLastPage(ExtractorInput extractorInput) throws InterruptedException, IOException {
        Assertions.checkArgument(extractorInput.getLength() != -1);
        OggUtil.skipToNextPage(extractorInput);
        this.pageHeader.reset();
        while ((this.pageHeader.type & 4) != 4 && extractorInput.getPosition() < extractorInput.getLength()) {
            OggUtil.populatePageHeader(extractorInput, this.pageHeader, this.headerArray, false);
            OggUtil.PageHeader pageHeader = this.pageHeader;
            extractorInput.skipFully(pageHeader.headerSize + pageHeader.bodySize);
        }
        return this.pageHeader.granulePosition;
    }

    public long skipToPageOfGranule(ExtractorInput extractorInput, long j) throws InterruptedException, IOException {
        OggUtil.skipToNextPage(extractorInput);
        OggUtil.populatePageHeader(extractorInput, this.pageHeader, this.headerArray, false);
        while (true) {
            OggUtil.PageHeader pageHeader = this.pageHeader;
            if (pageHeader.granulePosition >= j) {
                break;
            }
            extractorInput.skipFully(pageHeader.headerSize + pageHeader.bodySize);
            OggUtil.PageHeader pageHeader2 = this.pageHeader;
            this.elapsedSamples = pageHeader2.granulePosition;
            OggUtil.populatePageHeader(extractorInput, pageHeader2, this.headerArray, false);
        }
        if (this.elapsedSamples == 0) {
            throw new ParserException();
        }
        extractorInput.resetPeekPosition();
        long j2 = this.elapsedSamples;
        this.elapsedSamples = 0L;
        this.currentSegmentIndex = -1;
        return j2;
    }
}
