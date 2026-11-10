package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = {Util.getIntegerCodeForString("isom"), Util.getIntegerCodeForString("iso2"), Util.getIntegerCodeForString("iso3"), Util.getIntegerCodeForString("iso4"), Util.getIntegerCodeForString("iso5"), Util.getIntegerCodeForString("iso6"), Util.getIntegerCodeForString("avc1"), Util.getIntegerCodeForString("hvc1"), Util.getIntegerCodeForString("hev1"), Util.getIntegerCodeForString("mp41"), Util.getIntegerCodeForString("mp42"), Util.getIntegerCodeForString("3g2a"), Util.getIntegerCodeForString("3g2b"), Util.getIntegerCodeForString("3gr6"), Util.getIntegerCodeForString("3gs6"), Util.getIntegerCodeForString("3ge6"), Util.getIntegerCodeForString("3gg6"), Util.getIntegerCodeForString("M4V "), Util.getIntegerCodeForString("M4A "), Util.getIntegerCodeForString("f4v "), Util.getIntegerCodeForString("kddi"), Util.getIntegerCodeForString("M4VP"), Util.getIntegerCodeForString("qt  "), Util.getIntegerCodeForString("MSNV")};

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return sniffInternal(extractorInput, true);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return sniffInternal(extractorInput, false);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z) throws InterruptedException, IOException {
        boolean z2;
        boolean z3;
        int i;
        long length = extractorInput.getLength();
        if (length == -1 || length > 4096) {
            length = 4096;
        }
        int i2 = (int) length;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        int i3 = 0;
        boolean z4 = false;
        while (true) {
            if (i3 < i2) {
                extractorInput.peekFully(parsableByteArray.data, 0, 8);
                parsableByteArray.setPosition(0);
                long unsignedInt = parsableByteArray.readUnsignedInt();
                int i4 = parsableByteArray.readInt();
                if (unsignedInt == 1) {
                    extractorInput.peekFully(parsableByteArray.data, 8, 8);
                    i = 16;
                    unsignedInt = parsableByteArray.readUnsignedLongToLong();
                } else {
                    i = 8;
                }
                long j = i;
                if (unsignedInt < j) {
                    return false;
                }
                i3 += i;
                if (i4 != Atom.TYPE_moov) {
                    if (i4 == Atom.TYPE_moof || i4 == Atom.TYPE_mvex) {
                        z2 = true;
                        z3 = true;
                        if (!z4 && z == z3) {
                            return z2;
                        }
                    }
                    if ((((long) i3) + unsignedInt) - j < i2) {
                        int i5 = (int) (unsignedInt - j);
                        i3 += i5;
                        if (i4 == Atom.TYPE_ftyp) {
                            if (i5 < 8) {
                                return false;
                            }
                            if (parsableByteArray.capacity() < i5) {
                                parsableByteArray.reset(new byte[i5], i5);
                            }
                            extractorInput.peekFully(parsableByteArray.data, 0, i5);
                            int i6 = i5 / 4;
                            for (int i7 = 0; i7 < i6; i7++) {
                                if (i7 == 1) {
                                    parsableByteArray.skipBytes(4);
                                } else if (isCompatibleBrand(parsableByteArray.readInt())) {
                                    z4 = true;
                                    break;
                                }
                            }
                            if (!z4) {
                                return false;
                            }
                        } else if (i5 != 0) {
                            extractorInput.advancePeekPosition(i5);
                        }
                    }
                }
            }
            z2 = true;
            z3 = false;
            return !z4 ? false : false;
        }
    }

    private static boolean isCompatibleBrand(int i) {
        if ((i >>> 8) == Util.getIntegerCodeForString("3gp")) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
