package com.google.android.exoplayer.extractor.ts;

import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.IOException;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class AdtsExtractor implements Extractor {
    private static final int ID3_TAG = Util.getIntegerCodeForString("ID3");
    private AdtsReader adtsReader;
    private final long firstSampleTimestampUs;
    private final ParsableByteArray packetBuffer;
    private boolean startedPacket;

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    public AdtsExtractor() {
        this(0L);
    }

    public AdtsExtractor(long j) {
        this.firstSampleTimestampUs = j;
        this.packetBuffer = new ParsableByteArray(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.data);
        int i = 0;
        while (true) {
            extractorInput.peekFully(parsableByteArray.data, 0, 10);
            parsableByteArray.setPosition(0);
            if (parsableByteArray.readUnsignedInt24() != ID3_TAG) {
                break;
            }
            byte[] bArr = parsableByteArray.data;
            int i2 = (bArr[9] & ByteCompanionObject.MAX_VALUE) | ((bArr[6] & ByteCompanionObject.MAX_VALUE) << 21) | ((bArr[7] & ByteCompanionObject.MAX_VALUE) << 14) | ((bArr[8] & ByteCompanionObject.MAX_VALUE) << 7);
            i += i2 + 10;
            extractorInput.advancePeekPosition(i2);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        int i3 = 0;
        int i4 = 0;
        int i5 = i;
        while (true) {
            extractorInput.peekFully(parsableByteArray.data, 0, 2);
            parsableByteArray.setPosition(0);
            if ((parsableByteArray.readUnsignedShort() & 65526) != 65520) {
                extractorInput.resetPeekPosition();
                i5++;
                if (i5 - i >= 8192) {
                    return false;
                }
                extractorInput.advancePeekPosition(i5);
                i3 = 0;
                i4 = 0;
            } else {
                i3++;
                if (i3 >= 4 && i4 > 188) {
                    return true;
                }
                extractorInput.peekFully(parsableByteArray.data, 0, 4);
                parsableBitArray.setPosition(14);
                int bits = parsableBitArray.readBits(13);
                if (bits <= 6) {
                    return false;
                }
                extractorInput.advancePeekPosition(bits - 6);
                i4 += bits;
            }
        }
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.adtsReader = new AdtsReader(extractorOutput.track(0), extractorOutput.track(1));
        extractorOutput.endTracks();
        extractorOutput.seekMap(SeekMap.UNSEEKABLE);
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.startedPacket = false;
        this.adtsReader.seek();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        int i = extractorInput.read(this.packetBuffer.data, 0, MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR);
        if (i == -1) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(i);
        if (!this.startedPacket) {
            this.adtsReader.packetStarted(this.firstSampleTimestampUs, true);
            this.startedPacket = true;
        }
        this.adtsReader.consume(this.packetBuffer);
        return 0;
    }
}
