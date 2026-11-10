package com.google.android.exoplayer.util;

import android.util.Log;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class NalUnitUtil {
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    public static final class SpsData {
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthAspectRatio;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, float f, boolean z, boolean z2, int i4, int i5, int i6, boolean z3) {
            this.seqParameterSetId = i;
            this.width = i2;
            this.height = i3;
            this.pixelWidthAspectRatio = f;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i4;
            this.picOrderCountType = i5;
            this.picOrderCntLsbLength = i6;
            this.deltaPicOrderAlwaysZeroFlag = z3;
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i, int i2, boolean z) {
            this.picParameterSetId = i;
            this.seqParameterSetId = i2;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    public static int unescapeStream(byte[] bArr, int i) {
        int i2;
        synchronized (scratchEscapePositionsLock) {
            int iFindNextUnescapeIndex = 0;
            int i3 = 0;
            while (iFindNextUnescapeIndex < i) {
                try {
                    iFindNextUnescapeIndex = findNextUnescapeIndex(bArr, iFindNextUnescapeIndex, i);
                    if (iFindNextUnescapeIndex < i) {
                        if (scratchEscapePositions.length <= i3) {
                            scratchEscapePositions = Arrays.copyOf(scratchEscapePositions, scratchEscapePositions.length * 2);
                        }
                        scratchEscapePositions[i3] = iFindNextUnescapeIndex;
                        iFindNextUnescapeIndex += 3;
                        i3++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            i2 = i - i3;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                int i7 = scratchEscapePositions[i6] - i5;
                System.arraycopy(bArr, i5, bArr, i4, i7);
                int i8 = i4 + i7;
                int i9 = i8 + 1;
                bArr[i8] = 0;
                i4 = i9 + 1;
                bArr[i9] = 0;
                i5 += i7 + 3;
            }
            System.arraycopy(bArr, i5, bArr, i4, i2 - i4);
        }
        return i2;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < iPosition) {
                int i4 = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (i4 == 1 && (byteBuffer.get(i3) & 31) == 7) {
                        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
                        byteBufferDuplicate.position(i - 3);
                        byteBufferDuplicate.limit(iPosition);
                        byteBuffer.position(0);
                        byteBuffer.put(byteBufferDuplicate);
                        return;
                    }
                } else if (i4 == 0) {
                    i2++;
                }
                if (i4 != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static byte[] parseChildNalUnit(ParsableByteArray parsableByteArray) {
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(unsignedShort);
        return CodecSpecificDataUtil.buildNalUnit(parsableByteArray.data, position, unsignedShort);
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    public static SpsData parseSpsNalUnit(ParsableBitArray parsableBitArray) {
        int unsignedExpGolombCodedInt;
        boolean z;
        int unsignedExpGolombCodedInt2;
        boolean z2;
        float f;
        int i;
        int bits = parsableBitArray.readBits(8);
        parsableBitArray.skipBits(16);
        int unsignedExpGolombCodedInt3 = parsableBitArray.readUnsignedExpGolombCodedInt();
        int i2 = 1;
        if (bits == 100 || bits == 110 || bits == 122 || bits == 244 || bits == 44 || bits == 83 || bits == 86 || bits == 118 || bits == 128 || bits == 138) {
            unsignedExpGolombCodedInt = parsableBitArray.readUnsignedExpGolombCodedInt();
            boolean bit = unsignedExpGolombCodedInt == 3 ? parsableBitArray.readBit() : false;
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                int i3 = unsignedExpGolombCodedInt != 3 ? 8 : 12;
                int i4 = 0;
                while (i4 < i3) {
                    if (parsableBitArray.readBit()) {
                        skipScalingList(parsableBitArray, i4 < 6 ? 16 : 64);
                    }
                    i4++;
                }
            }
            z = bit;
        } else {
            z = false;
            unsignedExpGolombCodedInt = 1;
        }
        int unsignedExpGolombCodedInt4 = parsableBitArray.readUnsignedExpGolombCodedInt() + 4;
        int unsignedExpGolombCodedInt5 = parsableBitArray.readUnsignedExpGolombCodedInt();
        if (unsignedExpGolombCodedInt5 == 0) {
            z2 = false;
            unsignedExpGolombCodedInt2 = parsableBitArray.readUnsignedExpGolombCodedInt() + 4;
        } else if (unsignedExpGolombCodedInt5 == 1) {
            boolean bit2 = parsableBitArray.readBit();
            parsableBitArray.readSignedExpGolombCodedInt();
            parsableBitArray.readSignedExpGolombCodedInt();
            long unsignedExpGolombCodedInt6 = parsableBitArray.readUnsignedExpGolombCodedInt();
            for (int i5 = 0; i5 < unsignedExpGolombCodedInt6; i5++) {
                parsableBitArray.readUnsignedExpGolombCodedInt();
            }
            unsignedExpGolombCodedInt2 = 0;
            z2 = bit2;
        } else {
            unsignedExpGolombCodedInt2 = 0;
            z2 = false;
        }
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.skipBits(1);
        int unsignedExpGolombCodedInt7 = parsableBitArray.readUnsignedExpGolombCodedInt() + 1;
        int unsignedExpGolombCodedInt8 = parsableBitArray.readUnsignedExpGolombCodedInt() + 1;
        boolean bit3 = parsableBitArray.readBit();
        int i6 = (2 - (bit3 ? 1 : 0)) * unsignedExpGolombCodedInt8;
        if (!bit3) {
            parsableBitArray.skipBits(1);
        }
        parsableBitArray.skipBits(1);
        int i7 = unsignedExpGolombCodedInt7 * 16;
        int i8 = i6 * 16;
        if (parsableBitArray.readBit()) {
            int unsignedExpGolombCodedInt9 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt10 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt11 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt12 = parsableBitArray.readUnsignedExpGolombCodedInt();
            if (unsignedExpGolombCodedInt == 0) {
                i = 2 - (bit3 ? 1 : 0);
            } else {
                int i9 = unsignedExpGolombCodedInt == 3 ? 1 : 2;
                i = (2 - (bit3 ? 1 : 0)) * (unsignedExpGolombCodedInt == 1 ? 2 : 1);
                i2 = i9;
            }
            i7 -= (unsignedExpGolombCodedInt9 + unsignedExpGolombCodedInt10) * i2;
            i8 -= (unsignedExpGolombCodedInt11 + unsignedExpGolombCodedInt12) * i;
        }
        int i10 = i8;
        int i11 = i7;
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
                float[] fArr = ASPECT_RATIO_IDC_VALUES;
                if (bits2 < fArr.length) {
                    f = fArr[bits2];
                } else {
                    Log.w("NalUnitUtil", "Unexpected aspect_ratio_idc value: " + bits2);
                }
            }
            f = f2;
        } else {
            f = f2;
        }
        return new SpsData(unsignedExpGolombCodedInt3, i11, i10, f, z, bit3, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, unsignedExpGolombCodedInt2, z2);
    }

    public static PpsData parsePpsNalUnit(ParsableBitArray parsableBitArray) {
        int unsignedExpGolombCodedInt = parsableBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt2 = parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.skipBits(1);
        return new PpsData(unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2, parsableBitArray.readBit());
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0097, code lost:
    
        r8 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int findNalUnit(byte[] r7, int r8, int r9, boolean[] r10) {
        /*
            int r0 = r9 - r8
            r1 = 0
            r2 = 1
            if (r0 < 0) goto L8
            r3 = r2
            goto L9
        L8:
            r3 = r1
        L9:
            com.google.android.exoplayer.util.Assertions.checkState(r3)
            if (r0 != 0) goto Lf
            return r9
        Lf:
            r3 = 2
            if (r10 == 0) goto L40
            boolean r4 = r10[r1]
            if (r4 == 0) goto L1c
            clearPrefixFlags(r10)
            int r8 = r8 + (-3)
            return r8
        L1c:
            if (r0 <= r2) goto L2b
            boolean r4 = r10[r2]
            if (r4 == 0) goto L2b
            r4 = r7[r8]
            if (r4 != r2) goto L2b
            clearPrefixFlags(r10)
            int r8 = r8 - r3
            return r8
        L2b:
            if (r0 <= r3) goto L40
            boolean r4 = r10[r3]
            if (r4 == 0) goto L40
            r4 = r7[r8]
            if (r4 != 0) goto L40
            int r4 = r8 + 1
            r4 = r7[r4]
            if (r4 != r2) goto L40
            clearPrefixFlags(r10)
            int r8 = r8 - r2
            return r8
        L40:
            int r4 = r9 + (-1)
            int r8 = r8 + r3
        L43:
            if (r8 >= r4) goto L67
            r5 = r7[r8]
            r5 = r5 & 254(0xfe, float:3.56E-43)
            if (r5 == 0) goto L4c
            goto L64
        L4c:
            int r5 = r8 + (-2)
            r6 = r7[r5]
            if (r6 != 0) goto L62
            int r6 = r8 + (-1)
            r6 = r7[r6]
            if (r6 != 0) goto L62
            r6 = r7[r8]
            if (r6 != r2) goto L62
            if (r10 == 0) goto L61
            clearPrefixFlags(r10)
        L61:
            return r5
        L62:
            int r8 = r8 + (-2)
        L64:
            int r8 = r8 + 3
            goto L43
        L67:
            if (r10 == 0) goto Lbb
            if (r0 <= r3) goto L7e
            int r8 = r9 + (-3)
            r8 = r7[r8]
            if (r8 != 0) goto L7c
            int r8 = r9 + (-2)
            r8 = r7[r8]
            if (r8 != 0) goto L7c
            r8 = r7[r4]
            if (r8 != r2) goto L7c
            goto L97
        L7c:
            r8 = r1
            goto L98
        L7e:
            if (r0 != r3) goto L8f
            boolean r8 = r10[r3]
            if (r8 == 0) goto L7c
            int r8 = r9 + (-2)
            r8 = r7[r8]
            if (r8 != 0) goto L7c
            r8 = r7[r4]
            if (r8 != r2) goto L7c
            goto L97
        L8f:
            boolean r8 = r10[r2]
            if (r8 == 0) goto L7c
            r8 = r7[r4]
            if (r8 != r2) goto L7c
        L97:
            r8 = r2
        L98:
            r10[r1] = r8
            if (r0 <= r2) goto La7
            int r8 = r9 + (-2)
            r8 = r7[r8]
            if (r8 != 0) goto Lb1
            r8 = r7[r4]
            if (r8 != 0) goto Lb1
            goto Laf
        La7:
            boolean r8 = r10[r3]
            if (r8 == 0) goto Lb1
            r8 = r7[r4]
            if (r8 != 0) goto Lb1
        Laf:
            r8 = r2
            goto Lb2
        Lb1:
            r8 = r1
        Lb2:
            r10[r2] = r8
            r7 = r7[r4]
            if (r7 != 0) goto Lb9
            r1 = r2
        Lb9:
            r10[r3] = r1
        Lbb:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.util.NalUnitUtil.findNalUnit(byte[], int, int, boolean[]):int");
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static void skipScalingList(ParsableBitArray parsableBitArray, int i) {
        int signedExpGolombCodedInt = 8;
        int i2 = 8;
        for (int i3 = 0; i3 < i; i3++) {
            if (signedExpGolombCodedInt != 0) {
                signedExpGolombCodedInt = ((parsableBitArray.readSignedExpGolombCodedInt() + i2) + 256) % 256;
            }
            if (signedExpGolombCodedInt != 0) {
                i2 = signedExpGolombCodedInt;
            }
        }
    }
}
