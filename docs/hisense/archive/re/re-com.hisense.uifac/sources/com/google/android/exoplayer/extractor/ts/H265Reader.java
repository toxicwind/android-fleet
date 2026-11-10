package com.google.android.exoplayer.extractor.ts;

import android.util.Log;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.Collections;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class H265Reader extends ElementaryStreamReader {
    private boolean hasOutputFormat;
    private long pesTimeUs;
    private final NalUnitTargetBuffer pps;
    private final boolean[] prefixFlags;
    private final NalUnitTargetBuffer prefixSei;
    private final SampleReader sampleReader;
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper;
    private final NalUnitTargetBuffer sps;
    private final NalUnitTargetBuffer suffixSei;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer vps;

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public H265Reader(TrackOutput trackOutput, SeiReader seiReader) {
        super(trackOutput);
        this.seiReader = seiReader;
        this.prefixFlags = new boolean[3];
        this.vps = new NalUnitTargetBuffer(32, 128);
        this.sps = new NalUnitTargetBuffer(33, 128);
        this.pps = new NalUnitTargetBuffer(34, 128);
        this.prefixSei = new NalUnitTargetBuffer(39, 128);
        this.suffixSei = new NalUnitTargetBuffer(40, 128);
        this.sampleReader = new SampleReader(trackOutput);
        this.seiWrapper = new ParsableByteArray();
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        this.sampleReader.reset();
        this.totalBytesWritten = 0L;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, boolean z) {
        this.pesTimeUs = j;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int position = parsableByteArray.getPosition();
            int iLimit = parsableByteArray.limit();
            byte[] bArr = parsableByteArray.data;
            this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
            while (position < iLimit) {
                int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, position, iLimit, this.prefixFlags);
                if (iFindNalUnit == iLimit) {
                    nalUnitData(bArr, position, iLimit);
                    return;
                }
                int h265NalUnitType = NalUnitUtil.getH265NalUnitType(bArr, iFindNalUnit);
                int i = iFindNalUnit - position;
                if (i > 0) {
                    nalUnitData(bArr, position, iFindNalUnit);
                }
                int i2 = iLimit - iFindNalUnit;
                long j = this.totalBytesWritten - ((long) i2);
                endNalUnit(j, i2, i < 0 ? -i : 0, this.pesTimeUs);
                startNalUnit(j, i2, h265NalUnitType, this.pesTimeUs);
                position = iFindNalUnit + 3;
            }
        }
    }

    private void startNalUnit(long j, int i, int i2, long j2) {
        if (this.hasOutputFormat) {
            this.sampleReader.startNalUnit(j, i, i2, j2);
        } else {
            this.vps.startNalUnit(i2);
            this.sps.startNalUnit(i2);
            this.pps.startNalUnit(i2);
        }
        this.prefixSei.startNalUnit(i2);
        this.suffixSei.startNalUnit(i2);
    }

    private void nalUnitData(byte[] bArr, int i, int i2) {
        if (this.hasOutputFormat) {
            this.sampleReader.readNalUnitData(bArr, i, i2);
        } else {
            this.vps.appendToNalUnit(bArr, i, i2);
            this.sps.appendToNalUnit(bArr, i, i2);
            this.pps.appendToNalUnit(bArr, i, i2);
        }
        this.prefixSei.appendToNalUnit(bArr, i, i2);
        this.suffixSei.appendToNalUnit(bArr, i, i2);
    }

    private void endNalUnit(long j, int i, int i2, long j2) {
        if (this.hasOutputFormat) {
            this.sampleReader.endNalUnit(j, i);
        } else {
            this.vps.endNalUnit(i2);
            this.sps.endNalUnit(i2);
            this.pps.endNalUnit(i2);
            if (this.vps.isCompleted() && this.sps.isCompleted() && this.pps.isCompleted()) {
                this.output.format(parseMediaFormat(this.vps, this.sps, this.pps));
                this.hasOutputFormat = true;
            }
        }
        if (this.prefixSei.endNalUnit(i2)) {
            NalUnitTargetBuffer nalUnitTargetBuffer = this.prefixSei;
            this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer.nalData, nalUnitTargetBuffer.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j2, this.seiWrapper);
        }
        if (this.suffixSei.endNalUnit(i2)) {
            NalUnitTargetBuffer nalUnitTargetBuffer2 = this.suffixSei;
            this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j2, this.seiWrapper);
        }
    }

    private static MediaFormat parseMediaFormat(NalUnitTargetBuffer nalUnitTargetBuffer, NalUnitTargetBuffer nalUnitTargetBuffer2, NalUnitTargetBuffer nalUnitTargetBuffer3) {
        float f;
        int i = nalUnitTargetBuffer.nalLength;
        byte[] bArr = new byte[nalUnitTargetBuffer2.nalLength + i + nalUnitTargetBuffer3.nalLength];
        System.arraycopy(nalUnitTargetBuffer.nalData, 0, bArr, 0, i);
        System.arraycopy(nalUnitTargetBuffer2.nalData, 0, bArr, nalUnitTargetBuffer.nalLength, nalUnitTargetBuffer2.nalLength);
        System.arraycopy(nalUnitTargetBuffer3.nalData, 0, bArr, nalUnitTargetBuffer.nalLength + nalUnitTargetBuffer2.nalLength, nalUnitTargetBuffer3.nalLength);
        NalUnitUtil.unescapeStream(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength);
        ParsableBitArray parsableBitArray = new ParsableBitArray(nalUnitTargetBuffer2.nalData);
        parsableBitArray.skipBits(44);
        int bits = parsableBitArray.readBits(3);
        parsableBitArray.skipBits(1);
        parsableBitArray.skipBits(88);
        parsableBitArray.skipBits(8);
        int i2 = 0;
        for (int i3 = 0; i3 < bits; i3++) {
            if (parsableBitArray.readBit()) {
                i2 += 89;
            }
            if (parsableBitArray.readBit()) {
                i2 += 8;
            }
        }
        parsableBitArray.skipBits(i2);
        if (bits > 0) {
            parsableBitArray.skipBits((8 - bits) * 2);
        }
        parsableBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt = parsableBitArray.readUnsignedExpGolombCodedInt();
        if (unsignedExpGolombCodedInt == 3) {
            parsableBitArray.skipBits(1);
        }
        int unsignedExpGolombCodedInt2 = parsableBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt3 = parsableBitArray.readUnsignedExpGolombCodedInt();
        if (parsableBitArray.readBit()) {
            int unsignedExpGolombCodedInt4 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt5 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt6 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt7 = parsableBitArray.readUnsignedExpGolombCodedInt();
            unsignedExpGolombCodedInt2 -= ((unsignedExpGolombCodedInt == 1 || unsignedExpGolombCodedInt == 2) ? 2 : 1) * (unsignedExpGolombCodedInt4 + unsignedExpGolombCodedInt5);
            unsignedExpGolombCodedInt3 -= (unsignedExpGolombCodedInt == 1 ? 2 : 1) * (unsignedExpGolombCodedInt6 + unsignedExpGolombCodedInt7);
        }
        int i4 = unsignedExpGolombCodedInt2;
        int i5 = unsignedExpGolombCodedInt3;
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt8 = parsableBitArray.readUnsignedExpGolombCodedInt();
        for (int i6 = parsableBitArray.readBit() ? 0 : bits; i6 <= bits; i6++) {
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.readUnsignedExpGolombCodedInt();
        }
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.readUnsignedExpGolombCodedInt();
        if (parsableBitArray.readBit() && parsableBitArray.readBit()) {
            skipScalingList(parsableBitArray);
        }
        parsableBitArray.skipBits(2);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(8);
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.skipBits(1);
        }
        skipShortTermRefPicSets(parsableBitArray);
        if (parsableBitArray.readBit()) {
            for (int i7 = 0; i7 < parsableBitArray.readUnsignedExpGolombCodedInt(); i7++) {
                parsableBitArray.skipBits(unsignedExpGolombCodedInt8 + 4 + 1);
            }
        }
        parsableBitArray.skipBits(2);
        float f2 = 1.0f;
        if (parsableBitArray.readBit() && parsableBitArray.readBit()) {
            int bits2 = parsableBitArray.readBits(8);
            if (bits2 == 255) {
                int bits3 = parsableBitArray.readBits(16);
                int bits4 = parsableBitArray.readBits(16);
                if (bits3 != 0 && bits4 != 0) {
                    f2 = bits3 / bits4;
                }
            } else {
                float[] fArr = NalUnitUtil.ASPECT_RATIO_IDC_VALUES;
                if (bits2 < fArr.length) {
                    f = fArr[bits2];
                } else {
                    Log.w("H265Reader", "Unexpected aspect_ratio_idc value: " + bits2);
                }
            }
            f = f2;
        } else {
            f = f2;
        }
        return MediaFormat.createVideoFormat(null, "video/hevc", -1, -1, -1L, i4, i5, Collections.singletonList(bArr), -1, f);
    }

    private static void skipScalingList(ParsableBitArray parsableBitArray) {
        for (int i = 0; i < 4; i++) {
            int i2 = 0;
            while (i2 < 6) {
                int i3 = 1;
                if (!parsableBitArray.readBit()) {
                    parsableBitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int iMin = Math.min(64, 1 << ((i << 1) + 4));
                    if (i > 1) {
                        parsableBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i4 = 0; i4 < iMin; i4++) {
                        parsableBitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (i == 3) {
                    i3 = 3;
                }
                i2 += i3;
            }
        }
    }

    private static void skipShortTermRefPicSets(ParsableBitArray parsableBitArray) {
        int unsignedExpGolombCodedInt = parsableBitArray.readUnsignedExpGolombCodedInt();
        boolean bit = false;
        int i = 0;
        for (int i2 = 0; i2 < unsignedExpGolombCodedInt; i2++) {
            if (i2 != 0) {
                bit = parsableBitArray.readBit();
            }
            if (bit) {
                parsableBitArray.skipBits(1);
                parsableBitArray.readUnsignedExpGolombCodedInt();
                for (int i3 = 0; i3 <= i; i3++) {
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(1);
                    }
                }
            } else {
                int unsignedExpGolombCodedInt2 = parsableBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt3 = parsableBitArray.readUnsignedExpGolombCodedInt();
                int i4 = unsignedExpGolombCodedInt2 + unsignedExpGolombCodedInt3;
                for (int i5 = 0; i5 < unsignedExpGolombCodedInt2; i5++) {
                    parsableBitArray.readUnsignedExpGolombCodedInt();
                    parsableBitArray.skipBits(1);
                }
                for (int i6 = 0; i6 < unsignedExpGolombCodedInt3; i6++) {
                    parsableBitArray.readUnsignedExpGolombCodedInt();
                    parsableBitArray.skipBits(1);
                }
                i = i4;
            }
        }
    }

    private static final class SampleReader {
        private boolean isFirstParameterSet;
        private boolean isFirstSlice;
        private boolean lookingForFirstSliceFlag;
        private int nalUnitBytesRead;
        private boolean nalUnitHasKeyframeData;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private final TrackOutput output;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private boolean writingParameterSets;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.isFirstSlice = false;
            this.isFirstParameterSet = false;
            this.readingSample = false;
            this.writingParameterSets = false;
        }

        public void startNalUnit(long j, int i, int i2, long j2) {
            this.isFirstSlice = false;
            this.isFirstParameterSet = false;
            this.nalUnitTimeUs = j2;
            this.nalUnitBytesRead = 0;
            this.nalUnitStartPosition = j;
            if (i2 >= 32) {
                if (!this.writingParameterSets && this.readingSample) {
                    outputSample(i);
                    this.readingSample = false;
                }
                if (i2 <= 34) {
                    this.isFirstParameterSet = !this.writingParameterSets;
                    this.writingParameterSets = true;
                }
            }
            boolean z = i2 >= 16 && i2 <= 21;
            this.nalUnitHasKeyframeData = z;
            this.lookingForFirstSliceFlag = z || i2 <= 9;
        }

        public void readNalUnitData(byte[] bArr, int i, int i2) {
            if (this.lookingForFirstSliceFlag) {
                int i3 = this.nalUnitBytesRead;
                int i4 = (i + 2) - i3;
                if (i4 < i2) {
                    this.isFirstSlice = (bArr[i4] & ByteCompanionObject.MIN_VALUE) != 0;
                    this.lookingForFirstSliceFlag = false;
                } else {
                    this.nalUnitBytesRead = i3 + (i2 - i);
                }
            }
        }

        public void endNalUnit(long j, int i) {
            if (this.writingParameterSets && this.isFirstSlice) {
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.writingParameterSets = false;
            } else if (this.isFirstParameterSet || this.isFirstSlice) {
                if (this.readingSample) {
                    outputSample(i + ((int) (j - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.readingSample = true;
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
            }
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        private void outputSample(int i) {
            boolean z = this.sampleIsKeyframe;
            this.output.sampleMetadata(this.sampleTimeUs, z ? 1 : 0, (int) (this.nalUnitStartPosition - this.samplePosition), i, null);
        }
    }
}
