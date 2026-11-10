package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class OggUtil {
    private static final int TYPE_OGGS = Util.getIntegerCodeForString("OggS");

    public static class PacketInfoHolder {
        public int segmentCount;
        public int size;
    }

    public static int readBits(byte b, int i, int i2) {
        return (b >> i2) & (255 >>> (8 - i));
    }

    public static void skipToNextPage(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i;
        int length = 2048;
        byte[] bArr = new byte[2048];
        while (true) {
            if (extractorInput.getLength() != -1 && extractorInput.getPosition() + ((long) length) > extractorInput.getLength() && (length = (int) (extractorInput.getLength() - extractorInput.getPosition())) < 4) {
                throw new EOFException();
            }
            int i2 = 0;
            extractorInput.peekFully(bArr, 0, length, false);
            while (true) {
                i = length - 3;
                if (i2 < i) {
                    if (bArr[i2] == 79 && bArr[i2 + 1] == 103 && bArr[i2 + 2] == 103 && bArr[i2 + 3] == 83) {
                        extractorInput.skipFully(i2);
                        return;
                    }
                    i2++;
                }
            }
            extractorInput.skipFully(i);
        }
    }

    public static boolean populatePageHeader(ExtractorInput extractorInput, PageHeader pageHeader, ParsableByteArray parsableByteArray, boolean z) throws InterruptedException, IOException {
        parsableByteArray.reset();
        pageHeader.reset();
        if (!(extractorInput.getLength() == -1 || extractorInput.getLength() - extractorInput.getPeekPosition() >= 27) || !extractorInput.peekFully(parsableByteArray.data, 0, 27, true)) {
            if (z) {
                return false;
            }
            throw new EOFException();
        }
        if (parsableByteArray.readUnsignedInt() != TYPE_OGGS) {
            if (z) {
                return false;
            }
            throw new ParserException("expected OggS capture pattern at begin of page");
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        pageHeader.revision = unsignedByte;
        if (unsignedByte != 0) {
            if (z) {
                return false;
            }
            throw new ParserException("unsupported bit stream revision");
        }
        pageHeader.type = parsableByteArray.readUnsignedByte();
        pageHeader.granulePosition = parsableByteArray.readLittleEndianLong();
        pageHeader.streamSerialNumber = parsableByteArray.readLittleEndianUnsignedInt();
        pageHeader.pageSequenceNumber = parsableByteArray.readLittleEndianUnsignedInt();
        pageHeader.pageChecksum = parsableByteArray.readLittleEndianUnsignedInt();
        pageHeader.pageSegmentCount = parsableByteArray.readUnsignedByte();
        parsableByteArray.reset();
        int i = pageHeader.pageSegmentCount;
        pageHeader.headerSize = i + 27;
        extractorInput.peekFully(parsableByteArray.data, 0, i);
        for (int i2 = 0; i2 < pageHeader.pageSegmentCount; i2++) {
            pageHeader.laces[i2] = parsableByteArray.readUnsignedByte();
            pageHeader.bodySize += pageHeader.laces[i2];
        }
        return true;
    }

    public static void calculatePacketSize(PageHeader pageHeader, int i, PacketInfoHolder packetInfoHolder) {
        int i2;
        packetInfoHolder.segmentCount = 0;
        packetInfoHolder.size = 0;
        do {
            int i3 = packetInfoHolder.segmentCount;
            if (i + i3 >= pageHeader.pageSegmentCount) {
                return;
            }
            int[] iArr = pageHeader.laces;
            packetInfoHolder.segmentCount = i3 + 1;
            i2 = iArr[i3 + i];
            packetInfoHolder.size += i2;
        } while (i2 == 255);
    }

    public static final class PageHeader {
        public int bodySize;
        public long granulePosition;
        public int headerSize;
        public final int[] laces = new int[255];
        public long pageChecksum;
        public int pageSegmentCount;
        public long pageSequenceNumber;
        public int revision;
        public long streamSerialNumber;
        public int type;

        public void reset() {
            this.revision = 0;
            this.type = 0;
            this.granulePosition = 0L;
            this.pageSegmentCount = 0;
            this.headerSize = 0;
            this.bodySize = 0;
        }
    }
}
