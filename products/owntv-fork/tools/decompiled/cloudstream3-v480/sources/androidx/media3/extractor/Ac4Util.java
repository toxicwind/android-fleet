package androidx.media3.extractor;

import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.google.common.primitives.SignedBytes;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
public final class Ac4Util {
    public static final int AC40_SYNCWORD = 44096;
    public static final int AC41_SYNCWORD = 44097;
    private static final int CHANNEL_COUNT_2 = 2;
    private static final int CHANNEL_MODE_22_2 = 15;
    private static final int CHANNEL_MODE_3_0 = 2;
    private static final int CHANNEL_MODE_5_0 = 3;
    private static final int CHANNEL_MODE_5_1 = 4;
    private static final int CHANNEL_MODE_7_0_322 = 9;
    private static final int CHANNEL_MODE_7_0_34 = 5;
    private static final int CHANNEL_MODE_7_0_4 = 11;
    private static final int CHANNEL_MODE_7_0_52 = 7;
    private static final int CHANNEL_MODE_7_1_322 = 10;
    private static final int CHANNEL_MODE_7_1_34 = 6;
    private static final int CHANNEL_MODE_7_1_4 = 12;
    private static final int CHANNEL_MODE_7_1_52 = 8;
    private static final int CHANNEL_MODE_9_0_4 = 13;
    private static final int CHANNEL_MODE_9_1_4 = 14;
    private static final int CHANNEL_MODE_MONO = 0;
    private static final int CHANNEL_MODE_STEREO = 1;
    private static final int CHANNEL_MODE_UNKNOWN = -1;
    public static final int HEADER_SIZE_FOR_PARSER = 16;
    public static final int MAX_RATE_BYTES_PER_SECOND = 336000;
    private static final int[] SAMPLE_COUNT = {2002, 2000, 1920, 1601, 1600, 1001, 1000, 960, 800, 800, 480, 400, 400, 2048};
    public static final int SAMPLE_HEADER_SIZE = 7;
    private static final String TAG = "Ac4Util";

    private static int getChannelCountFromChannelMode(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 5;
            case 4:
                return 6;
            case 5:
            case 7:
            case 9:
                return 7;
            case 6:
            case 8:
            case 10:
                return 8;
            case 11:
                return 11;
            case 12:
                return 12;
            case 13:
                return 13;
            case 14:
                return 14;
            case 15:
                return 24;
            default:
                return -1;
        }
    }

    public static final class SyncFrameInfo {
        public final int bitstreamVersion;
        public final int channelCount;
        public final int frameSize;
        public final int sampleCount;
        public final int sampleRate;

        private SyncFrameInfo(int i, int i2, int i3, int i4, int i5) {
            this.bitstreamVersion = i;
            this.channelCount = i2;
            this.sampleRate = i3;
            this.frameSize = i4;
            this.sampleCount = i5;
        }
    }

    /* JADX WARN: Code duplicated, block: B:62:0x0118  */
    /* JADX WARN: Code duplicated, block: B:71:0x0139  */
    public static Format parseAc4AnnexEFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) throws ParserException {
        int i;
        int i2;
        int adjustedChannelCount;
        boolean bit;
        int bits;
        int bits2;
        int bits3;
        int i3;
        boolean z;
        boolean bit2;
        int i4;
        int bits4;
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int iBitsLeft = parsableBitArray.bitsLeft();
        int bits5 = parsableBitArray.readBits(3);
        if (bits5 > 1) {
            throw ParserException.createForUnsupportedContainerFeature("Unsupported AC-4 DSI version: " + bits5);
        }
        int bits6 = parsableBitArray.readBits(7);
        int i5 = parsableBitArray.readBit() ? OpusUtil.SAMPLE_RATE : 44100;
        parsableBitArray.skipBits(4);
        int bits7 = parsableBitArray.readBits(9);
        if (bits6 > 1) {
            if (bits5 == 0) {
                throw ParserException.createForUnsupportedContainerFeature("Invalid AC-4 DSI version: " + bits5);
            }
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(16);
                if (parsableBitArray.readBit()) {
                    parsableBitArray.skipBits(128);
                }
            }
        }
        if (bits5 == 1) {
            if (!skipDsiBitrate(parsableBitArray)) {
                throw ParserException.createForUnsupportedContainerFeature("Invalid AC-4 DSI bitrate.");
            }
            parsableBitArray.byteAlign();
        }
        Ac4Presentation ac4Presentation = new Ac4Presentation();
        int i6 = 0;
        while (true) {
            if (i6 < bits7) {
                if (bits5 == 0) {
                    bit = parsableBitArray.readBit();
                    bits = parsableBitArray.readBits(5);
                    bits2 = parsableBitArray.readBits(5);
                    bits3 = 0;
                    i3 = 0;
                    z = false;
                } else {
                    int bits8 = parsableBitArray.readBits(8);
                    bits3 = parsableBitArray.readBits(8);
                    if (bits3 == 255) {
                        bits3 += parsableBitArray.readBits(16);
                    }
                    if (bits8 > 2) {
                        parsableBitArray.skipBits(bits3 * 8);
                        i6++;
                    } else {
                        int iBitsLeft2 = (iBitsLeft - parsableBitArray.bitsLeft()) / 8;
                        int bits9 = parsableBitArray.readBits(5);
                        bits2 = bits8;
                        bits = bits9;
                        z = bits9 == 31;
                        i3 = iBitsLeft2;
                        bit = false;
                    }
                }
                ac4Presentation.version = bits2;
                if (bit || z || bits != 6) {
                    ac4Presentation.level = parsableBitArray.readBits(3);
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(5);
                    }
                    parsableBitArray.skipBits(2);
                    int i7 = 1;
                    if (bits5 == 1 && (bits2 == 1 || bits2 == 2)) {
                        parsableBitArray.skipBits(2);
                    }
                    parsableBitArray.skipBits(5);
                    parsableBitArray.skipBits(10);
                    if (bits5 == 1) {
                        if (bits2 > 0) {
                            ac4Presentation.isChannelCoded = parsableBitArray.readBit();
                        }
                        if (ac4Presentation.isChannelCoded) {
                            if (bits2 != 1) {
                                i4 = 2;
                                if (bits2 == 2) {
                                    bits4 = parsableBitArray.readBits(5);
                                    if (bits4 >= 0 && bits4 <= 15) {
                                        ac4Presentation.channelMode = bits4;
                                    }
                                    if (bits4 >= 11 || bits4 > 14) {
                                        i4 = 2;
                                    } else {
                                        ac4Presentation.hasBackChannels = parsableBitArray.readBit();
                                        i4 = 2;
                                        ac4Presentation.topChannelPairs = parsableBitArray.readBits(2);
                                    }
                                }
                            } else {
                                bits4 = parsableBitArray.readBits(5);
                                if (bits4 >= 0) {
                                    ac4Presentation.channelMode = bits4;
                                }
                                if (bits4 >= 11) {
                                    i4 = 2;
                                } else {
                                    i4 = 2;
                                }
                            }
                            parsableBitArray.skipBits(24);
                            i7 = 1;
                        } else {
                            i4 = 2;
                        }
                        if (bits2 == i7 || bits2 == i4) {
                            if (parsableBitArray.readBit() && parsableBitArray.readBit()) {
                                parsableBitArray.skipBits(i4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray.skipBit();
                                int i8 = 8;
                                int bits10 = parsableBitArray.readBits(8);
                                int i9 = 0;
                                while (i9 < bits10) {
                                    parsableBitArray.skipBits(i8);
                                    i9++;
                                    i8 = 8;
                                }
                            }
                        }
                    }
                    if (!bit && !z) {
                        parsableBitArray.skipBit();
                        if (bits == 0 || bits == 1 || bits == 2) {
                            if (bits2 == 0) {
                                for (int i10 = 0; i10 < 2; i10++) {
                                    parseDsiSubstream(parsableBitArray, ac4Presentation);
                                }
                            } else {
                                for (int i11 = 0; i11 < 2; i11++) {
                                    parseDsiSubstreamGroup(parsableBitArray, ac4Presentation);
                                }
                            }
                        } else if (bits == 3 || bits == 4) {
                            if (bits2 == 0) {
                                for (int i12 = 0; i12 < 3; i12++) {
                                    parseDsiSubstream(parsableBitArray, ac4Presentation);
                                }
                            } else {
                                for (int i13 = 0; i13 < 3; i13++) {
                                    parseDsiSubstreamGroup(parsableBitArray, ac4Presentation);
                                }
                            }
                        } else if (bits != 5) {
                            int bits11 = parsableBitArray.readBits(7);
                            for (int i14 = 0; i14 < bits11; i14++) {
                                parsableBitArray.skipBits(8);
                            }
                        } else if (bits2 == 0) {
                            parseDsiSubstream(parsableBitArray, ac4Presentation);
                        } else {
                            int bits12 = parsableBitArray.readBits(3);
                            for (int i15 = 0; i15 < bits12 + 2; i15++) {
                                parseDsiSubstreamGroup(parsableBitArray, ac4Presentation);
                            }
                        }
                    } else if (bits2 == 0) {
                        parseDsiSubstream(parsableBitArray, ac4Presentation);
                    } else {
                        parseDsiSubstreamGroup(parsableBitArray, ac4Presentation);
                    }
                    parsableBitArray.skipBit();
                    bit2 = parsableBitArray.readBit();
                } else {
                    bit2 = true;
                }
                if (bit2) {
                    int bits13 = parsableBitArray.readBits(7);
                    for (int i16 = 0; i16 < bits13; i16++) {
                        parsableBitArray.skipBits(15);
                    }
                }
                if (bits2 > 0) {
                    if (parsableBitArray.readBit() && !skipDsiBitrate(parsableBitArray)) {
                        throw ParserException.createForUnsupportedContainerFeature("Can't parse bitrate DSI.");
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray.byteAlign();
                        parsableBitArray.skipBytes(parsableBitArray.readBits(16));
                        int bits14 = parsableBitArray.readBits(5);
                        for (int i17 = 0; i17 < bits14; i17++) {
                            parsableBitArray.skipBits(3);
                            parsableBitArray.skipBits(8);
                        }
                    }
                }
                i = 8;
                parsableBitArray.byteAlign();
                if (bits5 == 1) {
                    int iBitsLeft3 = ((iBitsLeft - parsableBitArray.bitsLeft()) / 8) - i3;
                    if (bits3 < iBitsLeft3) {
                        throw ParserException.createForUnsupportedContainerFeature("pres_bytes is smaller than presentation bytes read.");
                    }
                    parsableBitArray.skipBytes(bits3 - iBitsLeft3);
                }
                if (ac4Presentation.isChannelCoded && ac4Presentation.channelMode == -1) {
                    throw ParserException.createForUnsupportedContainerFeature("Can't determine channel mode of presentation " + i6);
                }
            } else {
                i = 8;
            }
            if (ac4Presentation.isChannelCoded) {
                adjustedChannelCount = getAdjustedChannelCount(ac4Presentation.channelMode, ac4Presentation.hasBackChannels, ac4Presentation.topChannelPairs);
            } else if (ac4Presentation.numOfUmxObjects > 0) {
                int i18 = ac4Presentation.numOfUmxObjects + 1;
                if (ac4Presentation.level == 4 && i18 == 17) {
                    i18 = 21;
                }
                adjustedChannelCount = i18;
            } else {
                int i19 = ac4Presentation.level;
                if (i19 == 0) {
                    i2 = 2;
                } else if (i19 != 1) {
                    i2 = 2;
                    if (i19 == 2) {
                        adjustedChannelCount = i;
                    } else if (i19 == 3) {
                        adjustedChannelCount = 10;
                    } else if (i19 != 4) {
                        Log.w(TAG, "AC-4 level " + ac4Presentation.level + " has not been defined.");
                    } else {
                        adjustedChannelCount = 12;
                    }
                } else {
                    adjustedChannelCount = 6;
                }
                adjustedChannelCount = i2;
            }
            if (adjustedChannelCount <= 0) {
                throw ParserException.createForUnsupportedContainerFeature("Cannot determine channel count of presentation.");
            }
            return new Format.Builder().setId(str).setSampleMimeType("audio/ac4").setChannelCount(adjustedChannelCount).setSampleRate(i5).setDrmInitData(drmInitData).setLanguage(str2).setCodecs(createCodecsString(bits6, ac4Presentation.version, ac4Presentation.level)).build();
        }
    }

    private static void parseDsiSubstream(ParsableBitArray parsableBitArray, Ac4Presentation ac4Presentation) throws ParserException {
        int bits = parsableBitArray.readBits(5);
        parsableBitArray.skipBits(2);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(5);
        }
        if (bits >= 7 && bits <= 10) {
            parsableBitArray.skipBit();
        }
        if (parsableBitArray.readBit()) {
            int bits2 = parsableBitArray.readBits(3);
            if (ac4Presentation.channelMode == -1 && bits >= 0 && bits <= 15 && (bits2 == 0 || bits2 == 1)) {
                ac4Presentation.channelMode = bits;
            }
            if (parsableBitArray.readBit()) {
                skipDsiLanguage(parsableBitArray);
            }
        }
    }

    private static void parseDsiSubstreamGroup(ParsableBitArray parsableBitArray, Ac4Presentation ac4Presentation) throws ParserException {
        parsableBitArray.skipBits(2);
        boolean bit = parsableBitArray.readBit();
        int bits = parsableBitArray.readBits(8);
        for (int i = 0; i < bits; i++) {
            parsableBitArray.skipBits(2);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(5);
            }
            if (bit) {
                parsableBitArray.skipBits(24);
            } else {
                if (parsableBitArray.readBit()) {
                    if (!parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(4);
                    }
                    ac4Presentation.numOfUmxObjects = parsableBitArray.readBits(6) + 1;
                }
                parsableBitArray.skipBits(4);
            }
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(3);
            if (parsableBitArray.readBit()) {
                skipDsiLanguage(parsableBitArray);
            }
        }
    }

    private static void skipDsiLanguage(ParsableBitArray parsableBitArray) throws ParserException {
        int bits = parsableBitArray.readBits(6);
        if (bits < 2 || bits > 42) {
            throw ParserException.createForUnsupportedContainerFeature(String.format("Invalid language tag bytes number: %d. Must be between 2 and 42.", Integer.valueOf(bits)));
        }
        parsableBitArray.skipBits(bits * 8);
    }

    private static boolean skipDsiBitrate(ParsableBitArray parsableBitArray) {
        if (parsableBitArray.bitsLeft() < 66) {
            return false;
        }
        parsableBitArray.skipBits(66);
        return true;
    }

    private static int getAdjustedChannelCount(int i, boolean z, int i2) {
        int channelCountFromChannelMode = getChannelCountFromChannelMode(i);
        if (i != 11 && i != 12 && i != 13 && i != 14) {
            return channelCountFromChannelMode;
        }
        if (!z) {
            channelCountFromChannelMode -= 2;
        }
        if (i2 != 0) {
            return i2 != 1 ? channelCountFromChannelMode : channelCountFromChannelMode - 2;
        }
        return channelCountFromChannelMode - 4;
    }

    private static String createCodecsString(int i, int i2, int i3) {
        return Util.formatInvariant("ac-4.%02d.%02d.%02d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0089  */
    /* JADX WARN: Code duplicated, block: B:46:0x008e  */
    /* JADX WARN: Code duplicated, block: B:47:0x0090  */
    /* JADX WARN: Code duplicated, block: B:48:0x0093  */
    public static SyncFrameInfo parseAc4SyncframeInfo(ParsableBitArray parsableBitArray) {
        int i;
        int i2;
        int i3;
        int bits = parsableBitArray.readBits(16);
        int bits2 = parsableBitArray.readBits(16);
        if (bits2 == 65535) {
            bits2 = parsableBitArray.readBits(24);
            i = 7;
        } else {
            i = 4;
        }
        int i4 = bits2 + i;
        if (bits == 44097) {
            i4 += 2;
        }
        int i5 = i4;
        int bits3 = parsableBitArray.readBits(2);
        if (bits3 == 3) {
            bits3 += readVariableBits(parsableBitArray, 2);
        }
        int i6 = bits3;
        int bits4 = parsableBitArray.readBits(10);
        if (parsableBitArray.readBit() && parsableBitArray.readBits(3) > 0) {
            parsableBitArray.skipBits(2);
        }
        int i7 = 44100;
        if (parsableBitArray.readBit()) {
            i7 = 48000;
        }
        int bits5 = parsableBitArray.readBits(4);
        if (i7 == 44100 && bits5 == 13) {
            i2 = SAMPLE_COUNT[bits5];
        } else {
            if (i7 == 48000) {
                int[] iArr = SAMPLE_COUNT;
                if (bits5 < iArr.length) {
                    int i8 = iArr[bits5];
                    int i9 = bits4 % 5;
                    if (i9 == 1) {
                        if (bits5 != 3 || bits5 == 8) {
                            i2 = i8 + 1;
                        } else {
                            i3 = i8;
                        }
                    } else if (i9 != 2) {
                        if (i9 == 3) {
                            if (bits5 != 3) {
                            }
                            i2 = i8 + 1;
                        } else if (i9 == 4 && (bits5 == 3 || bits5 == 8 || bits5 == 11)) {
                            i2 = i8 + 1;
                        } else {
                            i3 = i8;
                        }
                    } else if (bits5 == 8 || bits5 == 11) {
                        i2 = i8 + 1;
                    } else {
                        i3 = i8;
                    }
                } else {
                    i2 = 0;
                }
            } else {
                i2 = 0;
            }
            return new SyncFrameInfo(i6, 2, i7, i5, i3);
        }
        i3 = i2;
        return new SyncFrameInfo(i6, 2, i7, i5, i3);
    }

    public static int parseAc4SyncframeSize(byte[] bArr, int i) {
        int i2 = 7;
        if (bArr.length < 7) {
            return -1;
        }
        int i3 = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        if (i3 == 65535) {
            i3 = ((bArr[4] & 255) << 16) | ((bArr[5] & 255) << 8) | (bArr[6] & 255);
        } else {
            i2 = 4;
        }
        if (i == 44097) {
            i2 += 2;
        }
        return i3 + i2;
    }

    public static int parseAc4SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[16];
        int iPosition = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(iPosition);
        return parseAc4SyncframeInfo(new ParsableBitArray(bArr)).sampleCount;
    }

    public static void getAc4SampleHeader(int i, ParsableByteArray parsableByteArray) {
        parsableByteArray.reset(7);
        byte[] data = parsableByteArray.getData();
        data[0] = -84;
        data[1] = SignedBytes.MAX_POWER_OF_TWO;
        data[2] = -1;
        data[3] = -1;
        data[4] = (byte) ((i >> 16) & 255);
        data[5] = (byte) ((i >> 8) & 255);
        data[6] = (byte) (i & 255);
    }

    private static int readVariableBits(ParsableBitArray parsableBitArray, int i) {
        int i2 = 0;
        while (true) {
            int bits = i2 + parsableBitArray.readBits(i);
            if (!parsableBitArray.readBit()) {
                return bits;
            }
            i2 = (bits + 1) << i;
        }
    }

    private static final class Ac4Presentation {
        public int channelMode;
        public boolean hasBackChannels;
        public boolean isChannelCoded;
        public int level;
        public int numOfUmxObjects;
        public int topChannelPairs;
        public int version;

        private Ac4Presentation() {
            this.isChannelCoded = true;
            this.channelMode = -1;
            this.numOfUmxObjects = -1;
            this.hasBackChannels = true;
            this.topChannelPairs = 2;
            this.version = 1;
            this.level = 0;
        }
    }

    private Ac4Util() {
    }
}
