package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

/* JADX INFO: loaded from: classes.dex */
public final class Encoder {
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AztecCode encode(byte[] bArr, int i, int i2) {
        BitArray bitArrayStuffBits;
        int i3;
        int i4;
        BitArray bitArrayStuffBits2;
        boolean z;
        int iAbs;
        int i5;
        int i6;
        int[] iArr = WORD_SIZE;
        BitArray bitArrayEncode = new HighLevelEncoder(bArr).encode();
        int size = ((bitArrayEncode.getSize() * i) / 100) + 11;
        int size2 = bitArrayEncode.getSize() + size;
        int i7 = 32;
        int i8 = 4;
        int i9 = 0;
        if (i2 != 0) {
            boolean z2 = i2 < 0;
            iAbs = Math.abs(i2);
            if (iAbs > (z2 ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
            i5 = totalBitsInLayer(iAbs, z2);
            i4 = iArr[iAbs];
            int i10 = i5 - (i5 % i4);
            bitArrayStuffBits2 = stuffBits(bitArrayEncode, i4);
            if (bitArrayStuffBits2.getSize() + size > i10) {
                z = z2;
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            if (z2) {
                z = z2;
                if (bitArrayStuffBits2.getSize() > i4 * 64) {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            }
        } else {
            BitArray bitArray = null;
            int i11 = 0;
            int i12 = 0;
            while (true) {
                if (i11 > i7) {
                    throw new IllegalArgumentException("Data too large for an Aztec code");
                }
                boolean z3 = i11 <= 3 ? 1 : i9;
                int i13 = z3 != 0 ? i11 + 1 : i11;
                int i14 = totalBitsInLayer(i13, z3);
                if (size2 <= i14) {
                    if (i12 != iArr[i13]) {
                        i3 = iArr[i13];
                        bitArrayStuffBits = stuffBits(bitArrayEncode, i3);
                    } else {
                        int i15 = i12;
                        bitArrayStuffBits = bitArray;
                        i3 = i15;
                    }
                    int i16 = i14 - (i14 % i3);
                    if ((z3 == 0 || bitArrayStuffBits.getSize() <= i3 * 64) && bitArrayStuffBits.getSize() + size <= i16) {
                        i4 = i3;
                        bitArrayStuffBits2 = bitArrayStuffBits;
                        z = z3;
                        iAbs = i13;
                        i5 = i14;
                        break;
                    }
                    BitArray bitArray2 = bitArrayStuffBits;
                    i12 = i3;
                    bitArray = bitArray2;
                }
                i11++;
                i7 = 32;
                i8 = 4;
                i9 = 0;
            }
        }
        BitArray bitArrayGenerateCheckWords = generateCheckWords(bitArrayStuffBits2, i5, i4);
        int size3 = bitArrayStuffBits2.getSize() / i4;
        BitArray bitArrayGenerateModeMessage = generateModeMessage(z, iAbs, size3);
        int i17 = z ? (iAbs * 4) + 11 : (iAbs * 4) + 14;
        int[] iArr2 = new int[i17];
        int i18 = 2;
        if (z) {
            for (int i19 = i9; i19 < i17; i19++) {
                iArr2[i19] = i19;
            }
            i6 = i17;
        } else {
            int i20 = i17 / 2;
            i6 = i17 + 1 + (((i20 - 1) / 15) * 2);
            int i21 = i6 / 2;
            for (int i22 = i9; i22 < i20; i22++) {
                int i23 = (i22 / 15) + i22;
                iArr2[(i20 - i22) - 1] = (i21 - i23) - 1;
                iArr2[i20 + i22] = i23 + i21 + 1;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i6);
        int i24 = i9;
        int i25 = i24;
        while (i24 < iAbs) {
            int i26 = (iAbs - i24) * i8;
            int i27 = z ? i26 + 9 : i26 + 12;
            int i28 = i9;
            while (i28 < i27) {
                int i29 = i28 * 2;
                while (i9 < i18) {
                    if (bitArrayGenerateCheckWords.get(i25 + i29 + i9)) {
                        int i30 = i24 * 2;
                        bitMatrix.set(iArr2[i30 + i9], iArr2[i30 + i28]);
                    }
                    if (bitArrayGenerateCheckWords.get((i27 * 2) + i25 + i29 + i9)) {
                        int i31 = i24 * 2;
                        bitMatrix.set(iArr2[i31 + i28], iArr2[((i17 - 1) - i31) - i9]);
                    }
                    if (bitArrayGenerateCheckWords.get((i27 * 4) + i25 + i29 + i9)) {
                        int i32 = (i17 - 1) - (i24 * 2);
                        bitMatrix.set(iArr2[i32 - i9], iArr2[i32 - i28]);
                    }
                    if (bitArrayGenerateCheckWords.get((i27 * 6) + i25 + i29 + i9)) {
                        int i33 = i24 * 2;
                        bitMatrix.set(iArr2[((i17 - 1) - i33) - i28], iArr2[i33 + i9]);
                    }
                    i9++;
                    i18 = 2;
                }
                i28++;
                i9 = 0;
                i18 = 2;
            }
            i25 += i27 * 8;
            i24++;
            i8 = 4;
            i9 = 0;
            i18 = 2;
        }
        drawModeMessage(bitMatrix, z, i6, bitArrayGenerateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, i6 / 2, 5);
        } else {
            int i34 = i6 / 2;
            drawBullsEye(bitMatrix, i34, 7);
            int i35 = 0;
            int i36 = 0;
            while (i35 < (i17 / 2) - 1) {
                for (int i37 = i34 & 1; i37 < i6; i37 += 2) {
                    int i38 = i34 - i36;
                    bitMatrix.set(i38, i37);
                    int i39 = i34 + i36;
                    bitMatrix.set(i39, i37);
                    bitMatrix.set(i37, i38);
                    bitMatrix.set(i37, i39);
                }
                i35 += 15;
                i36 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i6);
        aztecCode.setLayers(iAbs);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    bitMatrix.set(i5, i4);
                    bitMatrix.set(i5, i6);
                    bitMatrix.set(i4, i5);
                    bitMatrix.set(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        bitMatrix.set(i7, i7);
        int i8 = i7 + 1;
        bitMatrix.set(i8, i7);
        bitMatrix.set(i7, i8);
        int i9 = i + i2;
        bitMatrix.set(i9, i7);
        bitMatrix.set(i9, i8);
        bitMatrix.set(i9, i9 - 1);
    }

    static BitArray generateModeMessage(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, 2);
            bitArray.appendBits(i2 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i - 1, 5);
        bitArray.appendBits(i2 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (bitArray.get(i3)) {
                    bitMatrix.set(i4, i2 - 5);
                }
                if (bitArray.get(i3 + 7)) {
                    bitMatrix.set(i2 + 5, i4);
                }
                if (bitArray.get(20 - i3)) {
                    bitMatrix.set(i4, i2 + 5);
                }
                if (bitArray.get(27 - i3)) {
                    bitMatrix.set(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (bitArray.get(i3)) {
                bitMatrix.set(i5, i2 - 7);
            }
            if (bitArray.get(i3 + 10)) {
                bitMatrix.set(i2 + 7, i5);
            }
            if (bitArray.get(29 - i3)) {
                bitMatrix.set(i5, i2 + 7);
            }
            if (bitArray.get(39 - i3)) {
                bitMatrix.set(i2 - 7, i5);
            }
            i3++;
        }
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        int size = bitArray.getSize() / i2;
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
        int i3 = i / i2;
        int[] iArrBitsToWords = bitsToWords(bitArray, i2, i3);
        reedSolomonEncoder.encode(iArrBitsToWords, i3 - size);
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        for (int i4 : iArrBitsToWords) {
            bitArray2.appendBits(i4, i2);
        }
        return bitArray2;
    }

    private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int size = bitArray.getSize() / i;
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static GenericGF getGF(int i) {
        if (i == 4) {
            return GenericGF.AZTEC_PARAM;
        }
        if (i == 6) {
            return GenericGF.AZTEC_DATA_6;
        }
        if (i == 8) {
            return GenericGF.AZTEC_DATA_8;
        }
        if (i == 10) {
            return GenericGF.AZTEC_DATA_10;
        }
        if (i != 12) {
            return null;
        }
        return GenericGF.AZTEC_DATA_12;
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= size || bitArray.get(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                bitArray2.appendBits(i7, i);
            } else {
                if (i7 == 0) {
                    bitArray2.appendBits(i4 | 1, i);
                } else {
                    bitArray2.appendBits(i4, i);
                }
                i3 += i;
            }
            i3--;
            i3 += i;
        }
        return bitArray2;
    }
}
