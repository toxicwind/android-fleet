package androidx.media3.extractor.mp4;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.Mp4Box;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SniffFailure;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Mp4Box.TYPE_avc1, Mp4Box.TYPE_hvc1, Mp4Box.TYPE_hev1, Mp4Box.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    public static SniffFailure sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    public static SniffFailure sniffUnfragmented(ExtractorInput extractorInput, boolean z) throws IOException {
        return sniffInternal(extractorInput, false, z);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0173  */
    /* JADX WARN: Code duplicated, block: B:103:0x0176 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:94:0x0167  */
    /* JADX WARN: Code duplicated, block: B:96:0x016a  */
    /* JADX WARN: Code duplicated, block: B:98:0x016e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:99:0x0170  */
    /* JADX WARN: Multi-variable type inference failed */
    private static SniffFailure sniffInternal(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException {
        SniffFailure sniffFailure;
        int i;
        long j;
        int i2;
        long j2;
        int i3;
        int[] iArr;
        long length = extractorInput.getLength();
        long j3 = -1;
        int i4 = (length > (-1L) ? 1 : (length == (-1L) ? 0 : -1));
        long j4 = 4096;
        if (i4 != 0 && length <= 4096) {
            j4 = length;
        }
        int i5 = (int) j4;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i6 = 0;
        int i7 = 0;
        boolean z3 = false;
        while (true) {
            if (i7 < i5) {
                parsableByteArray.reset(8);
                boolean z4 = true;
                if (extractorInput.peekFully(parsableByteArray.getData(), i6, 8, true)) {
                    long unsignedInt = parsableByteArray.readUnsignedInt();
                    int i8 = parsableByteArray.readInt();
                    if (unsignedInt == 1) {
                        j3 = j3;
                        extractorInput.peekFully(parsableByteArray.getData(), 8, 8);
                        i2 = 16;
                        parsableByteArray.setLimit(16);
                        j = parsableByteArray.readLong();
                    } else {
                        j3 = j3;
                        if (unsignedInt == 0) {
                            long length2 = extractorInput.getLength();
                            if (length2 != j3) {
                                unsignedInt = (length2 - extractorInput.getPeekPosition()) + 8;
                            }
                        }
                        j = unsignedInt;
                        i2 = 8;
                    }
                    long j5 = i2;
                    if (j < j5) {
                        sniffFailure = null;
                        if (i8 != 1718773093 || i2 != 8) {
                            return new AtomSizeTooSmallSniffFailure(i8, j, i2);
                        }
                        j = j5;
                    } else {
                        sniffFailure = null;
                    }
                    int i9 = i7 + i2;
                    if (i8 == 1836019574) {
                        i5 += (int) j;
                        if (i4 != 0 && i5 > length) {
                            i5 = (int) length;
                        }
                        i7 = i9;
                        i6 = 0;
                    } else {
                        if (i8 == 1953653099 || i8 == 1835297121 || i8 == 1835626086) {
                            j2 = length;
                            i3 = 0;
                            i7 = i9;
                        } else if (i8 == 1836019558 || i8 == 1836475768) {
                            i = 1;
                        } else {
                            if (i8 == 1835295092) {
                                z3 = true;
                            }
                            if (i8 != 1937007212 || j <= 1000000) {
                                j2 = length;
                                if ((((long) i9) + j) - j5 < i5) {
                                    int i10 = (int) (j - j5);
                                    i7 = i9 + i10;
                                    if (i8 != 1718909296) {
                                        i3 = 0;
                                        if (i10 != 0) {
                                            extractorInput.advancePeekPosition(i10);
                                        }
                                    } else {
                                        if (i10 < 8) {
                                            return new AtomSizeTooSmallSniffFailure(i8, i10, 8);
                                        }
                                        parsableByteArray.reset(i10);
                                        i3 = 0;
                                        extractorInput.peekFully(parsableByteArray.getData(), 0, i10);
                                        int i11 = parsableByteArray.readInt();
                                        if (isCompatibleBrand(i11, z2)) {
                                            z3 = true;
                                        }
                                        parsableByteArray.skipBytes(4);
                                        int iBytesLeft = parsableByteArray.bytesLeft() / 4;
                                        if (!z3 && iBytesLeft > 0) {
                                            iArr = new int[iBytesLeft];
                                            int i12 = 0;
                                            while (true) {
                                                if (i12 >= iBytesLeft) {
                                                    z4 = z3;
                                                    break;
                                                }
                                                int i13 = parsableByteArray.readInt();
                                                iArr[i12] = i13;
                                                if (isCompatibleBrand(i13, z2)) {
                                                    break;
                                                }
                                                i12++;
                                            }
                                        } else {
                                            z4 = z3;
                                            iArr = sniffFailure;
                                        }
                                        if (!z4) {
                                            return new UnsupportedBrandsSniffFailure(i11, iArr);
                                        }
                                        z3 = z4;
                                    }
                                }
                            }
                            i = 0;
                        }
                        i6 = i3;
                        length = j2;
                    }
                }
                if (!z3) {
                    return NoDeclaredBrandSniffFailure.INSTANCE;
                }
                if (z != i) {
                    return sniffFailure;
                }
                if (i != 0) {
                    return IncorrectFragmentationSniffFailure.FILE_FRAGMENTED;
                }
                return IncorrectFragmentationSniffFailure.FILE_NOT_FRAGMENTED;
            }
            sniffFailure = null;
            i = i6;
            if (!z3) {
                return NoDeclaredBrandSniffFailure.INSTANCE;
            }
            if (z != i) {
                return sniffFailure;
            }
            if (i != 0) {
                return IncorrectFragmentationSniffFailure.FILE_FRAGMENTED;
            }
            return IncorrectFragmentationSniffFailure.FILE_NOT_FRAGMENTED;
        }
    }

    private static boolean isCompatibleBrand(int i, boolean z) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        if (i == 1751476579 && z) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
