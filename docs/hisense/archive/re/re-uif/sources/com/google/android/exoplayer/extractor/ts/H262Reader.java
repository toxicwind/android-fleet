package com.google.android.exoplayer.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
final class H262Reader extends ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private final CsdBuffer csdBuffer;
    private boolean foundFirstFrameInGroup;
    private long frameDurationUs;
    private long framePosition;
    private long frameTimeUs;
    private boolean hasOutputFormat;
    private boolean isKeyframe;
    private boolean pesPtsUsAvailable;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private long totalBytesWritten;

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public H262Reader(TrackOutput trackOutput) {
        super(trackOutput);
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        this.pesPtsUsAvailable = false;
        this.foundFirstFrameInGroup = false;
        this.totalBytesWritten = 0L;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, boolean z) {
        boolean z2 = j != -1;
        this.pesPtsUsAvailable = z2;
        if (z2) {
            this.pesTimeUs = j;
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
    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        int i;
        int i2;
        if (parsableByteArray.bytesLeft() > 0) {
            int position = parsableByteArray.getPosition();
            int iLimit = parsableByteArray.limit();
            byte[] bArr = parsableByteArray.data;
            this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
            int i3 = position;
            while (true) {
                int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, position, iLimit, this.prefixFlags);
                if (iFindNalUnit == iLimit) {
                    break;
                }
                int i4 = iFindNalUnit + 3;
                int i5 = parsableByteArray.data[i4] & 255;
                if (!this.hasOutputFormat) {
                    int i6 = iFindNalUnit - i3;
                    if (i6 > 0) {
                        this.csdBuffer.onData(bArr, i3, iFindNalUnit);
                    }
                    if (this.csdBuffer.onStartCode(i5, i6 < 0 ? -i6 : 0)) {
                        Pair<MediaFormat, Long> csdBuffer = parseCsdBuffer(this.csdBuffer);
                        this.output.format((MediaFormat) csdBuffer.first);
                        this.frameDurationUs = ((Long) csdBuffer.second).longValue();
                        this.hasOutputFormat = true;
                    }
                }
                if (this.hasOutputFormat && (i5 == 184 || i5 == 0)) {
                    int i7 = iLimit - iFindNalUnit;
                    if (this.foundFirstFrameInGroup) {
                        this.output.sampleMetadata(this.frameTimeUs, this.isKeyframe ? 1 : 0, ((int) (this.totalBytesWritten - this.framePosition)) - i7, i7, null);
                        this.isKeyframe = false;
                        i2 = i5;
                        i = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRVDATA_ACCESS_FAIL;
                    } else {
                        i = 184;
                        i2 = i5;
                    }
                    if (i2 == i) {
                        this.foundFirstFrameInGroup = false;
                        this.isKeyframe = true;
                    } else {
                        this.frameTimeUs = this.pesPtsUsAvailable ? this.pesTimeUs : this.frameTimeUs + this.frameDurationUs;
                        this.framePosition = this.totalBytesWritten - ((long) i7);
                        this.pesPtsUsAvailable = false;
                        this.foundFirstFrameInGroup = true;
                    }
                }
                i3 = iFindNalUnit;
                position = i4;
            }
            if (this.hasOutputFormat) {
                return;
            }
            this.csdBuffer.onData(bArr, i3, iLimit);
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0066  */
    /* JADX WARN: Code duplicated, block: B:17:0x006b  */
    /* JADX WARN: Code duplicated, block: B:19:0x007c  */
    private static Pair<MediaFormat, Long> parseCsdBuffer(CsdBuffer csdBuffer) {
        float f;
        int i;
        float f2;
        long j;
        int i2;
        double[] dArr;
        double d;
        int i3;
        int i4;
        byte[] bArrCopyOf = Arrays.copyOf(csdBuffer.data, csdBuffer.length);
        int i5 = bArrCopyOf[4] & 255;
        int i6 = bArrCopyOf[5] & 255;
        int i7 = (i5 << 4) | (i6 >> 4);
        int i8 = ((i6 & 15) << 8) | (bArrCopyOf[6] & 255);
        int i9 = (bArrCopyOf[7] & 240) >> 4;
        if (i9 == 2) {
            f = i8 * 4;
            i = i7 * 3;
        } else {
            if (i9 != 3) {
                if (i9 != 4) {
                    f2 = 1.0f;
                } else {
                    f = i8 * MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_VIDEO_PRESENT;
                    i = i7 * 100;
                }
                MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(null, "video/mpeg2", -1, -1, -1L, i7, i8, Collections.singletonList(bArrCopyOf), -1, f2);
                j = 0;
                i2 = (bArrCopyOf[7] & 15) - 1;
                if (i2 >= 0) {
                    dArr = FRAME_RATE_VALUES;
                    if (i2 < dArr.length) {
                        d = dArr[i2];
                        int i10 = csdBuffer.sequenceExtensionPosition + 9;
                        i3 = (bArrCopyOf[i10] & 96) >> 5;
                        i4 = bArrCopyOf[i10] & 31;
                        if (i3 != i4) {
                            d *= (((double) i3) + 1.0d) / ((double) (i4 + 1));
                        }
                        j = (long) (1000000.0d / d);
                    }
                }
                return Pair.create(mediaFormatCreateVideoFormat, Long.valueOf(j));
            }
            f = i8 * 16;
            i = i7 * 9;
        }
        f2 = f / i;
        MediaFormat mediaFormatCreateVideoFormat2 = MediaFormat.createVideoFormat(null, "video/mpeg2", -1, -1, -1L, i7, i8, Collections.singletonList(bArrCopyOf), -1, f2);
        j = 0;
        i2 = (bArrCopyOf[7] & 15) - 1;
        if (i2 >= 0) {
            dArr = FRAME_RATE_VALUES;
            if (i2 < dArr.length) {
                d = dArr[i2];
                int i11 = csdBuffer.sequenceExtensionPosition + 9;
                i3 = (bArrCopyOf[i11] & 96) >> 5;
                i4 = bArrCopyOf[i11] & 31;
                if (i3 != i4) {
                    d *= (((double) i3) + 1.0d) / ((double) (i4 + 1));
                }
                j = (long) (1000000.0d / d);
            }
        }
        return Pair.create(mediaFormatCreateVideoFormat2, Long.valueOf(j));
    }

    private static final class CsdBuffer {
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int i) {
            this.data = new byte[i];
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }

        public boolean onStartCode(int i, int i2) {
            if (this.isFilling) {
                if (this.sequenceExtensionPosition == 0 && i == 181) {
                    this.sequenceExtensionPosition = this.length;
                } else {
                    this.length -= i2;
                    this.isFilling = false;
                    return true;
                }
            } else if (i == 179) {
                this.isFilling = true;
            }
            return false;
        }

        public void onData(byte[] bArr, int i, int i2) {
            if (this.isFilling) {
                int i3 = i2 - i;
                byte[] bArr2 = this.data;
                int length = bArr2.length;
                int i4 = this.length;
                if (length < i4 + i3) {
                    this.data = Arrays.copyOf(bArr2, (i4 + i3) * 2);
                }
                System.arraycopy(bArr, i, this.data, this.length, i3);
                this.length += i3;
            }
        }
    }
}
