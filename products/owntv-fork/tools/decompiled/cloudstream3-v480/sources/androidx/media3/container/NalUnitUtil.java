package androidx.media3.container;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.DoubleMath;
import j$.util.Objects;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    public static final int H264_NAL_UNIT_TYPE_AUD = 9;
    public static final int H264_NAL_UNIT_TYPE_IDR = 5;
    public static final int H264_NAL_UNIT_TYPE_NON_IDR = 1;
    public static final int H264_NAL_UNIT_TYPE_PARTITION_A = 2;
    public static final int H264_NAL_UNIT_TYPE_PPS = 8;
    public static final int H264_NAL_UNIT_TYPE_PREFIX = 14;
    public static final int H264_NAL_UNIT_TYPE_SEI = 6;
    public static final int H264_NAL_UNIT_TYPE_SPS = 7;
    public static final int H264_NAL_UNIT_TYPE_UNSPECIFIED = 24;
    public static final int H265_NAL_UNIT_TYPE_AUD = 35;
    public static final int H265_NAL_UNIT_TYPE_BLA_W_LP = 16;
    public static final int H265_NAL_UNIT_TYPE_CRA = 21;
    public static final int H265_NAL_UNIT_TYPE_PPS = 34;
    public static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final int H265_NAL_UNIT_TYPE_RASL_R = 9;
    public static final int H265_NAL_UNIT_TYPE_SPS = 33;
    public static final int H265_NAL_UNIT_TYPE_SUFFIX_SEI = 40;
    public static final int H265_NAL_UNIT_TYPE_UNSPECIFIED = 48;
    public static final int H265_NAL_UNIT_TYPE_VPS = 32;
    private static final int INVALID_ID = -1;

    @Deprecated
    public static final int NAL_UNIT_TYPE_AUD = 9;

    @Deprecated
    public static final int NAL_UNIT_TYPE_IDR = 5;

    @Deprecated
    public static final int NAL_UNIT_TYPE_NON_IDR = 1;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PARTITION_A = 2;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PPS = 8;

    @Deprecated
    public static final int NAL_UNIT_TYPE_PREFIX = 14;

    @Deprecated
    public static final int NAL_UNIT_TYPE_SEI = 6;

    @Deprecated
    public static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    private static int applyConformanceWindowToHeight(int i, int i2, int i3, int i4) {
        return i - ((i2 == 1 ? 2 : 1) * (i3 + i4));
    }

    private static int applyConformanceWindowToWidth(int i, int i2, int i3, int i4) {
        int i5 = 2;
        if (i2 != 1 && i2 != 2) {
            i5 = 1;
        }
        return i - (i5 * (i3 + i4));
    }

    public static boolean isH264NalUnitDependedOn(byte b) {
        if (((b & 96) >> 5) != 0) {
            return true;
        }
        int i = b & Ascii.US;
        return (i == 1 || i == 9 || i == 14) ? false : true;
    }

    public static final class SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int maxNumReorderFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, int i8, int i9, boolean z, boolean z2, int i10, int i11, int i12, boolean z3, int i13, int i14, int i15, int i16) {
            this.profileIdc = i;
            this.constraintsFlagsAndReservedZero2Bits = i2;
            this.levelIdc = i3;
            this.seqParameterSetId = i4;
            this.maxNumRefFrames = i5;
            this.width = i6;
            this.height = i7;
            this.pixelWidthHeightRatio = f;
            this.bitDepthLumaMinus8 = i8;
            this.bitDepthChromaMinus8 = i9;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i10;
            this.picOrderCountType = i11;
            this.picOrderCntLsbLength = i12;
            this.deltaPicOrderAlwaysZeroFlag = z3;
            this.colorSpace = i13;
            this.colorRange = i14;
            this.colorTransfer = i15;
            this.maxNumReorderFrames = i16;
        }
    }

    public static final class H265NalHeader {
        public final int layerId;
        public final int nalUnitType;
        public final int temporalId;

        public H265NalHeader(int i, int i2, int i3) {
            this.nalUnitType = i;
            this.layerId = i2;
            this.temporalId = i3;
        }
    }

    public static final class H265LayerInfo {
        public final int layerIdInVps;
        public final int viewId;

        public H265LayerInfo(int i, int i2) {
            this.layerIdInVps = i;
            this.viewId = i2;
        }
    }

    public static final class H265ProfileTierLevel {
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;

        public H265ProfileTierLevel(int i, boolean z, int i2, int i3, int[] iArr, int i4) {
            this.generalProfileSpace = i;
            this.generalTierFlag = z;
            this.generalProfileIdc = i2;
            this.generalProfileCompatibilityFlags = i3;
            this.constraintBytes = iArr;
            this.generalLevelIdc = i4;
        }
    }

    public static final class H265ProfileTierLevelsAndIndices {
        public final int[] indices;
        public final ImmutableList<H265ProfileTierLevel> profileTierLevels;

        public H265ProfileTierLevelsAndIndices(List<H265ProfileTierLevel> list, int[] iArr) {
            this.profileTierLevels = ImmutableList.copyOf((Collection) list);
            this.indices = iArr;
        }
    }

    public static final class H265RepFormat {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int height;
        public final int width;

        public H265RepFormat(int i, int i2, int i3, int i4, int i5) {
            this.chromaFormatIdc = i;
            this.bitDepthLumaMinus8 = i2;
            this.bitDepthChromaMinus8 = i3;
            this.width = i4;
            this.height = i5;
        }
    }

    public static final class H265RepFormatsAndIndices {
        public final int[] indices;
        public final ImmutableList<H265RepFormat> repFormats;

        public H265RepFormatsAndIndices(List<H265RepFormat> list, int[] iArr) {
            this.repFormats = ImmutableList.copyOf((Collection) list);
            this.indices = iArr;
        }
    }

    public static final class H265VideoSignalInfo {
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;

        public H265VideoSignalInfo(int i, int i2, int i3) {
            this.colorSpace = i;
            this.colorRange = i2;
            this.colorTransfer = i3;
        }
    }

    public static final class H265VideoSignalInfosAndIndices {
        public final int[] indices;
        public final ImmutableList<H265VideoSignalInfo> videoSignalInfos;

        public H265VideoSignalInfosAndIndices(List<H265VideoSignalInfo> list, int[] iArr) {
            this.videoSignalInfos = ImmutableList.copyOf((Collection) list);
            this.indices = iArr;
        }
    }

    public static final class H265VpsData {
        public final ImmutableList<H265LayerInfo> layerInfos;
        public final H265NalHeader nalHeader;
        public final H265ProfileTierLevelsAndIndices profileTierLevelsAndIndices;
        public final H265RepFormatsAndIndices repFormatsAndIndices;
        public final H265VideoSignalInfosAndIndices videoSignalInfosAndIndices;

        public H265VpsData(H265NalHeader h265NalHeader, List<H265LayerInfo> list, H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices, H265RepFormatsAndIndices h265RepFormatsAndIndices, H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices) {
            this.nalHeader = h265NalHeader;
            this.layerInfos = list != null ? ImmutableList.copyOf((Collection) list) : ImmutableList.of();
            this.profileTierLevelsAndIndices = h265ProfileTierLevelsAndIndices;
            this.repFormatsAndIndices = h265RepFormatsAndIndices;
            this.videoSignalInfosAndIndices = h265VideoSignalInfosAndIndices;
        }
    }

    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int decodedHeight;
        public final int decodedWidth;
        public final int height;
        public final int maxNumReorderPics;
        public final int maxSubLayersMinus1;
        public final H265NalHeader nalHeader;
        public final float pixelWidthHeightRatio;
        public final H265ProfileTierLevel profileTierLevel;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(H265NalHeader h265NalHeader, int i, H265ProfileTierLevel h265ProfileTierLevel, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, float f, int i10, int i11, int i12, int i13) {
            this.nalHeader = h265NalHeader;
            this.maxSubLayersMinus1 = i;
            this.profileTierLevel = h265ProfileTierLevel;
            this.chromaFormatIdc = i2;
            this.bitDepthLumaMinus8 = i3;
            this.bitDepthChromaMinus8 = i4;
            this.seqParameterSetId = i5;
            this.width = i6;
            this.height = i7;
            this.pixelWidthHeightRatio = f;
            this.maxNumReorderPics = i10;
            this.colorSpace = i11;
            this.colorRange = i12;
            this.colorTransfer = i13;
            this.decodedWidth = i8;
            this.decodedHeight = i9;
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

    public static final class H265Sei3dRefDisplayInfoData {
        public final int exponentRefDisplayWidth;
        public final int exponentRefViewingDist;
        public final int leftViewId;
        public final int mantissaRefDisplayWidth;
        public final int mantissaRefViewingDist;
        public final int numRefDisplays;
        public final int precRefDisplayWidth;
        public final int precRefViewingDist;
        public final int rightViewId;

        public H265Sei3dRefDisplayInfoData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.precRefDisplayWidth = i;
            this.precRefViewingDist = i2;
            this.numRefDisplays = i3;
            this.leftViewId = i4;
            this.rightViewId = i5;
            this.exponentRefDisplayWidth = i6;
            this.mantissaRefDisplayWidth = i7;
            this.exponentRefViewingDist = i8;
            this.mantissaRefViewingDist = i9;
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
                        int[] iArr = scratchEscapePositions;
                        if (iArr.length <= i3) {
                            scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
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
                i4 = i8 + 2;
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
                    if (i4 == 1 && (byteBuffer.get(i3) & Ascii.US) == 7) {
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

    @Deprecated
    public static boolean isNalUnitSei(String str, byte b) {
        return ("video/avc".equals(str) && (b & Ascii.US) == 6) || ("video/hevc".equals(str) && ((b & 126) >> 1) == 39);
    }

    public static boolean isNalUnitSei(Format format, byte b) {
        String nalStructureMimeType = getNalStructureMimeType(format);
        return (Objects.equals(nalStructureMimeType, "video/avc") && (b & Ascii.US) == 6) || (Objects.equals(nalStructureMimeType, "video/hevc") && ((b & 126) >> 1) == 39);
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & Ascii.US;
    }

    public static int numberOfBytesInNalUnitHeader(Format format) {
        String nalStructureMimeType = getNalStructureMimeType(format);
        if (Objects.equals(nalStructureMimeType, "video/avc")) {
            return 1;
        }
        return Objects.equals(nalStructureMimeType, "video/hevc") ? 2 : 0;
    }

    public static boolean isDependedOn(byte[] bArr, int i, int i2, Format format) {
        if (Objects.equals(format.sampleMimeType, "video/avc")) {
            return isH264NalUnitDependedOn(bArr[i]);
        }
        if (Objects.equals(format.sampleMimeType, "video/hevc")) {
            return isH265NalUnitDependedOn(bArr, i, i2, format);
        }
        return true;
    }

    private static boolean isH265NalUnitDependedOn(byte[] bArr, int i, int i2, Format format) {
        H265NalHeader h265NalHeader = parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i, i2 + i));
        if (h265NalHeader.nalUnitType == 35) {
            return false;
        }
        return (h265NalHeader.nalUnitType <= 14 && h265NalHeader.nalUnitType % 2 == 0 && h265NalHeader.temporalId == format.maxSubLayers - 1) ? false : true;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    public static SpsData parseSpsNalUnit(byte[] bArr, int i, int i2) {
        return parseSpsNalUnitPayload(bArr, i + 1, i2);
    }

    /* JADX WARN: Code duplicated, block: B:112:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:115:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:118:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:121:0x0206  */
    /* JADX WARN: Code duplicated, block: B:124:0x020d  */
    /* JADX WARN: Code duplicated, block: B:127:0x0219  */
    public static SpsData parseSpsNalUnitPayload(byte[] bArr, int i, int i2) {
        int unsignedExpGolombCodedInt;
        boolean bit;
        int unsignedExpGolombCodedInt2;
        int i3;
        int i4;
        boolean z;
        int unsignedExpGolombCodedInt3;
        int i5;
        float f;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean bit2;
        boolean bit3;
        int i12;
        int i13;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        int bits = parsableNalUnitBitArray.readBits(8);
        int bits2 = parsableNalUnitBitArray.readBits(8);
        int bits3 = parsableNalUnitBitArray.readBits(8);
        int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (bits == 100 || bits == 110 || bits == 122 || bits == 244 || bits == 44 || bits == 83 || bits == 86 || bits == 118 || bits == 128 || bits == 138) {
            unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            bit = unsignedExpGolombCodedInt == 3 ? parsableNalUnitBitArray.readBit() : false;
            unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                int i14 = unsignedExpGolombCodedInt != 3 ? 8 : 12;
                i3 = 16;
                int i15 = 0;
                while (i15 < i14) {
                    if (parsableNalUnitBitArray.readBit()) {
                        skipScalingList(parsableNalUnitBitArray, i15 < 6 ? 16 : 64);
                    }
                    i15++;
                }
            } else {
                i3 = 16;
            }
            i4 = unsignedExpGolombCodedInt5;
        } else {
            unsignedExpGolombCodedInt = 1;
            i3 = 16;
            i4 = 0;
            bit = false;
            unsignedExpGolombCodedInt2 = 0;
        }
        int unsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
        int unsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (unsignedExpGolombCodedInt7 == 0) {
            unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
            bits = bits;
            unsignedExpGolombCodedInt7 = unsignedExpGolombCodedInt7;
            z = false;
        } else {
            if (unsignedExpGolombCodedInt7 == 1) {
                boolean bit4 = parsableNalUnitBitArray.readBit();
                parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                long unsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                for (int i16 = 0; i16 < unsignedExpGolombCodedInt8; i16++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                z = bit4;
            } else {
                z = false;
            }
            unsignedExpGolombCodedInt3 = 0;
        }
        int unsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        int unsignedExpGolombCodedInt10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        int unsignedExpGolombCodedInt11 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        boolean bit5 = parsableNalUnitBitArray.readBit();
        int i17 = (2 - (bit5 ? 1 : 0)) * unsignedExpGolombCodedInt11;
        if (!bit5) {
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBit();
        int i18 = unsignedExpGolombCodedInt10 * 16;
        int i19 = i17 * 16;
        if (parsableNalUnitBitArray.readBit()) {
            int unsignedExpGolombCodedInt12 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt13 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt14 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt15 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (unsignedExpGolombCodedInt == 0) {
                i13 = 2 - (bit5 ? 1 : 0);
                i12 = 1;
            } else {
                i12 = unsignedExpGolombCodedInt == 3 ? 1 : 2;
                i13 = (unsignedExpGolombCodedInt == 1 ? 2 : 1) * (2 - (bit5 ? 1 : 0));
            }
            i18 -= (unsignedExpGolombCodedInt12 + unsignedExpGolombCodedInt13) * i12;
            i19 -= (unsignedExpGolombCodedInt14 + unsignedExpGolombCodedInt15) * i13;
        }
        int i20 = i18;
        int i21 = bits;
        int unsignedExpGolombCodedInt16 = ((i21 == 44 || i21 == 86 || i21 == 100 || i21 == 110 || i21 == 122 || i21 == 244) && (bits2 & 16) != 0) ? 0 : i3;
        float f2 = 1.0f;
        if (parsableNalUnitBitArray.readBit()) {
            if (parsableNalUnitBitArray.readBit()) {
                int bits4 = parsableNalUnitBitArray.readBits(8);
                if (bits4 == 255) {
                    int i22 = i3;
                    int bits5 = parsableNalUnitBitArray.readBits(i22);
                    int bits6 = parsableNalUnitBitArray.readBits(i22);
                    if (bits5 != 0 && bits6 != 0) {
                        f2 = bits5 / bits6;
                    }
                } else {
                    float[] fArr = ASPECT_RATIO_IDC_VALUES;
                    if (bits4 < fArr.length) {
                        f2 = fArr[bits4];
                    } else {
                        Log.w(TAG, "Unexpected aspect_ratio_idc value: " + bits4);
                    }
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBit();
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBits(3);
                i10 = parsableNalUnitBitArray.readBit() ? 1 : 2;
                if (parsableNalUnitBitArray.readBit()) {
                    int bits7 = parsableNalUnitBitArray.readBits(8);
                    int bits8 = parsableNalUnitBitArray.readBits(8);
                    parsableNalUnitBitArray.skipBits(8);
                    int iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(bits7);
                    int iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(bits8);
                    i11 = iIsoColorPrimariesToColorSpace;
                    i9 = iIsoTransferCharacteristicsToColorTransfer;
                } else {
                    i9 = -1;
                }
                if (parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                if (parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.skipBits(65);
                }
                bit2 = parsableNalUnitBitArray.readBit();
                if (bit2) {
                    skipHrdParameters(parsableNalUnitBitArray);
                }
                bit3 = parsableNalUnitBitArray.readBit();
                if (bit3) {
                    skipHrdParameters(parsableNalUnitBitArray);
                }
                if (!bit2 || bit3) {
                    parsableNalUnitBitArray.skipBit();
                }
                parsableNalUnitBitArray.skipBit();
                if (parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.skipBit();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    unsignedExpGolombCodedInt16 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                i8 = i9;
                i7 = i10;
                i5 = unsignedExpGolombCodedInt16;
                f = f2;
                i6 = i11;
            } else {
                i9 = -1;
                i10 = -1;
            }
            i11 = -1;
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBits(65);
            }
            bit2 = parsableNalUnitBitArray.readBit();
            if (bit2) {
                skipHrdParameters(parsableNalUnitBitArray);
            }
            bit3 = parsableNalUnitBitArray.readBit();
            if (bit3) {
                skipHrdParameters(parsableNalUnitBitArray);
            }
            if (!bit2) {
                parsableNalUnitBitArray.skipBit();
            } else {
                parsableNalUnitBitArray.skipBit();
            }
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBit();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                unsignedExpGolombCodedInt16 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            i8 = i9;
            i7 = i10;
            i5 = unsignedExpGolombCodedInt16;
            f = f2;
            i6 = i11;
        } else {
            i5 = unsignedExpGolombCodedInt16;
            f = 1.0f;
            i6 = -1;
            i7 = -1;
            i8 = -1;
        }
        return new SpsData(i21, bits2, bits3, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt9, i20, i19, f, unsignedExpGolombCodedInt2, i4, bit, bit5, unsignedExpGolombCodedInt6, unsignedExpGolombCodedInt7, unsignedExpGolombCodedInt3, z, i6, i7, i8, i5);
    }

    public static H265VpsData parseH265VpsNalUnit(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        return parseH265VpsNalUnitPayload(parsableNalUnitBitArray, parseH265NalHeader(parsableNalUnitBitArray));
    }

    private static H265NalHeader parseH265NalHeader(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBit();
        return new H265NalHeader(parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(3) - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static H265VpsData parseH265VpsNalUnitPayload(ParsableNalUnitBitArray parsableNalUnitBitArray, H265NalHeader h265NalHeader) {
        int[] iArr;
        int i;
        int i2;
        H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices;
        int i3;
        int i4;
        int i5;
        boolean[][] zArr;
        ImmutableList immutableList;
        int i6;
        boolean[][] zArr2;
        int[] iArr2;
        int i7;
        parsableNalUnitBitArray.skipBits(4);
        boolean bit = parsableNalUnitBitArray.readBit();
        boolean bit2 = parsableNalUnitBitArray.readBit();
        int bits = parsableNalUnitBitArray.readBits(6);
        int i8 = bits + 1;
        int bits2 = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBits(17);
        H265ProfileTierLevel h265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, bits2, null);
        for (int i9 = parsableNalUnitBitArray.readBit() ? 0 : bits2; i9 <= bits2; i9++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        }
        int bits3 = parsableNalUnitBitArray.readBits(6);
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices = new H265ProfileTierLevelsAndIndices(ImmutableList.of(h265ProfileTierLevel), new int[1]);
        Object[] objArr = i8 >= 2 && unsignedExpGolombCodedInt >= 2;
        Object[] objArr2 = bit && bit2;
        int i10 = bits3 + 1;
        Object[] objArr3 = i10 >= i8;
        if (objArr == false || objArr2 == false || objArr3 == false) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[][] iArr3 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, unsignedExpGolombCodedInt, i10);
        int[] iArr4 = new int[unsignedExpGolombCodedInt];
        int i11 = 1;
        int[] iArr5 = new int[unsignedExpGolombCodedInt];
        iArr3[0][0] = 0;
        iArr4[0] = 1;
        iArr5[0] = 0;
        for (int i12 = 1; i12 < unsignedExpGolombCodedInt; i12++) {
            int i13 = 0;
            for (int i14 = 0; i14 <= bits3; i14++) {
                if (parsableNalUnitBitArray.readBit()) {
                    iArr3[i12][i13] = i14;
                    iArr5[i12] = i14;
                    i13++;
                }
                iArr4[i12] = i13;
            }
        }
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(64);
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int i15 = 0;
            while (i15 < unsignedExpGolombCodedInt2) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                skipH265HrdParameters(parsableNalUnitBitArray, i15 == 0 || parsableNalUnitBitArray.readBit(), bits2);
                i15++;
            }
        }
        if (!parsableNalUnitBitArray.readBit()) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        parsableNalUnitBitArray.byteAlign();
        H265ProfileTierLevel h265ProfileTierLevel2 = parseH265ProfileTierLevel(parsableNalUnitBitArray, false, bits2, h265ProfileTierLevel);
        boolean bit3 = parsableNalUnitBitArray.readBit();
        int i16 = 6;
        boolean[] zArr3 = new boolean[16];
        int i17 = 0;
        for (int i18 = 0; i18 < 16; i18++) {
            boolean bit4 = parsableNalUnitBitArray.readBit();
            zArr3[i18] = bit4;
            if (bit4) {
                i17++;
            }
        }
        if (i17 == 0 || !zArr3[1]) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr6 = new int[i17];
        for (int i19 = 0; i19 < i17 - (bit3 ? 1 : 0); i19++) {
            iArr6[i19] = parsableNalUnitBitArray.readBits(3);
        }
        int[] iArr7 = new int[i17 + 1];
        if (bit3) {
            int i20 = 1;
            while (i20 < i17) {
                int[] iArr8 = iArr7;
                for (int i21 = 0; i21 < i20; i21++) {
                    iArr8[i20] = iArr8[i20] + iArr6[i21] + 1;
                }
                i20++;
                iArr7 = iArr8;
            }
            iArr = iArr7;
            iArr[i17] = 6;
        } else {
            iArr = iArr7;
        }
        int[][] iArr9 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i8, i17);
        int[] iArr10 = new int[i8];
        iArr10[0] = 0;
        boolean bit5 = parsableNalUnitBitArray.readBit();
        int i22 = 1;
        while (i22 < i8) {
            if (bit5) {
                i7 = i22;
                iArr10[i7] = parsableNalUnitBitArray.readBits(i16);
            } else {
                i7 = i22;
                iArr10[i7] = i7;
            }
            if (bit3) {
                int i23 = 0;
                while (i23 < i17) {
                    int i24 = i23 + 1;
                    iArr9[i7][i23] = (iArr10[i7] & ((1 << iArr[i24]) - 1)) >> iArr[i23];
                    i23 = i24;
                }
            } else {
                int i25 = 0;
                while (i25 < i17) {
                    int i26 = i25;
                    iArr9[i7][i26] = parsableNalUnitBitArray.readBits(iArr6[i25] + 1);
                    i25 = i26 + 1;
                }
            }
            i22 = i7 + 1;
            i16 = 6;
        }
        int[] iArr11 = new int[i10];
        int i27 = 1;
        int i28 = 0;
        while (i28 < i8) {
            iArr11[iArr10[i28]] = -1;
            int[] iArr12 = iArr11;
            int i29 = 0;
            int i30 = 0;
            while (i29 < 16) {
                if (zArr3[i29]) {
                    if (i29 == i11) {
                        iArr12[iArr10[i28]] = iArr9[i28][i30];
                    }
                    i30++;
                }
                i29++;
                i11 = 1;
            }
            if (i28 > 0) {
                int i31 = 0;
                while (true) {
                    if (i31 >= i28) {
                        i27++;
                        break;
                    }
                    int i32 = i31;
                    if (iArr12[iArr10[i28]] == iArr12[iArr10[i31]]) {
                        break;
                    }
                    i31 = i32 + 1;
                }
            }
            i28++;
            iArr11 = iArr12;
            i11 = 1;
        }
        int[] iArr13 = iArr11;
        int bits4 = parsableNalUnitBitArray.readBits(4);
        if (i27 < 2 || bits4 == 0) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr14 = new int[i27];
        for (int i33 = 0; i33 < i27; i33++) {
            iArr14[i33] = parsableNalUnitBitArray.readBits(bits4);
        }
        int[] iArr15 = new int[i10];
        int i34 = 0;
        while (i34 < i8) {
            int[] iArr16 = iArr15;
            iArr16[Math.min(iArr10[i34], bits3)] = i34;
            i34++;
            iArr15 = iArr16;
        }
        int[] iArr17 = iArr15;
        ImmutableList.Builder builder = ImmutableList.builder();
        int i35 = 0;
        while (i35 <= bits3) {
            int i36 = i27;
            int[] iArr18 = iArr14;
            int iMin = Math.min(iArr13[i35], i36 - 1);
            builder.add(new H265LayerInfo(iArr17[i35], iMin >= 0 ? iArr18[iMin] : -1));
            i35++;
            i27 = i36;
            iArr14 = iArr18;
            iArr5 = iArr5;
        }
        int[] iArr19 = iArr5;
        ImmutableList immutableListBuild = builder.build();
        if (((H265LayerInfo) immutableListBuild.get(0)).viewId == -1) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int i37 = 1;
        while (true) {
            if (i37 > bits3) {
                i = -1;
                i2 = -1;
                break;
            }
            i = -1;
            if (((H265LayerInfo) immutableListBuild.get(i37)).viewId != -1) {
                i2 = i37;
                break;
            }
            i37++;
        }
        if (i2 == i) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        boolean[][] zArr4 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, i8, i8);
        boolean[][] zArr5 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, i8, i8);
        for (int i38 = 1; i38 < i8; i38++) {
            for (int i39 = 0; i39 < i38; i39++) {
                boolean[] zArr6 = zArr4[i38];
                boolean[] zArr7 = zArr5[i38];
                boolean bit6 = parsableNalUnitBitArray.readBit();
                zArr7[i39] = bit6;
                zArr6[i39] = bit6;
            }
        }
        for (int i40 = 1; i40 < i8; i40++) {
            int i41 = 0;
            while (i41 < bits) {
                boolean[][] zArr8 = zArr5;
                for (int i42 = 0; i42 < i40; i42++) {
                    boolean[] zArr9 = zArr8[i40];
                    if (zArr9[i42] && zArr8[i42][i41]) {
                        zArr9[i41] = true;
                        break;
                    }
                }
                i41++;
                zArr5 = zArr8;
            }
        }
        boolean[][] zArr10 = zArr5;
        int[] iArr20 = new int[i10];
        for (int i43 = 0; i43 < i8; i43++) {
            int i44 = 0;
            for (int i45 = 0; i45 < i43; i45++) {
                i44 += zArr4[i43][i45] ? 1 : 0;
            }
            iArr20[iArr10[i43]] = i44;
        }
        int i46 = 0;
        for (int i47 = 0; i47 < i8; i47++) {
            if (iArr20[iArr10[i47]] == 0) {
                i46++;
            }
        }
        if (i46 > 1) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int[] iArr21 = new int[i8];
        int[] iArr22 = new int[unsignedExpGolombCodedInt];
        if (parsableNalUnitBitArray.readBit()) {
            int i48 = 0;
            while (i48 < i8) {
                int i49 = i48;
                iArr21[i49] = parsableNalUnitBitArray.readBits(3);
                i48 = i49 + 1;
            }
        } else {
            Arrays.fill(iArr21, 0, i8, bits2);
        }
        int i50 = 0;
        while (i50 < unsignedExpGolombCodedInt) {
            int i51 = i50;
            int[] iArr23 = iArr10;
            int[] iArr24 = iArr21;
            int iMax = 0;
            for (int i52 = 0; i52 < iArr4[i51]; i52++) {
                iMax = Math.max(iMax, iArr24[((H265LayerInfo) immutableListBuild.get(iArr3[i51][i52])).layerIdInVps]);
            }
            iArr22[i51] = iMax + 1;
            i50 = i51 + 1;
            iArr10 = iArr23;
            iArr21 = iArr24;
        }
        int[] iArr25 = iArr10;
        if (parsableNalUnitBitArray.readBit()) {
            int i53 = 0;
            while (i53 < bits) {
                int i54 = i53 + 1;
                int i55 = i54;
                while (i55 < i8) {
                    if (zArr4[i55][i53]) {
                        parsableNalUnitBitArray.skipBits(3);
                    }
                    i55++;
                    i53 = i53;
                }
                i53 = i54;
            }
        }
        parsableNalUnitBitArray.skipBit();
        int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        ImmutableList.Builder builder2 = ImmutableList.builder();
        builder2.add(h265ProfileTierLevel);
        if (unsignedExpGolombCodedInt3 > 1) {
            builder2.add(h265ProfileTierLevel2);
            for (int i56 = 2; i56 < unsignedExpGolombCodedInt3; i56++) {
                h265ProfileTierLevel2 = parseH265ProfileTierLevel(parsableNalUnitBitArray, parsableNalUnitBitArray.readBit(), bits2, h265ProfileTierLevel2);
                builder2.add(h265ProfileTierLevel2);
            }
        }
        ImmutableList immutableListBuild2 = builder2.build();
        int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + unsignedExpGolombCodedInt;
        if (unsignedExpGolombCodedInt4 > unsignedExpGolombCodedInt) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        int bits5 = parsableNalUnitBitArray.readBits(2);
        boolean[][] zArr11 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, unsignedExpGolombCodedInt4, i10);
        int[] iArr26 = new int[unsignedExpGolombCodedInt4];
        int i57 = 0;
        int[] iArr27 = new int[unsignedExpGolombCodedInt4];
        int i58 = 0;
        while (i58 < unsignedExpGolombCodedInt) {
            iArr26[i58] = i57;
            iArr27[i58] = iArr19[i58];
            if (bits5 == 0) {
                i6 = i58;
                zArr2 = zArr11;
                immutableList = immutableListBuild2;
                iArr2 = iArr26;
                Arrays.fill(zArr11[i6], i57, iArr4[i6], true);
                iArr2[i6] = iArr4[i6];
            } else {
                immutableList = immutableListBuild2;
                i6 = i58;
                zArr2 = zArr11;
                iArr2 = iArr26;
                if (bits5 == 1) {
                    int i59 = iArr19[i6];
                    for (int i60 = 0; i60 < iArr4[i6]; i60++) {
                        zArr2[i6][i60] = iArr3[i6][i60] == i59;
                    }
                    iArr2[i6] = 1;
                } else {
                    i57 = 0;
                    zArr2[0][0] = true;
                    iArr2[0] = 1;
                }
                i58 = i6 + 1;
                zArr11 = zArr2;
                iArr26 = iArr2;
                immutableListBuild2 = immutableList;
            }
            i57 = 0;
            i58 = i6 + 1;
            zArr11 = zArr2;
            iArr26 = iArr2;
            immutableListBuild2 = immutableList;
        }
        ImmutableList immutableList2 = immutableListBuild2;
        boolean[][] zArr12 = zArr11;
        int[] iArr28 = iArr26;
        int[] iArr29 = new int[i10];
        int i61 = 2;
        int[] iArr30 = new int[2];
        iArr30[1] = i10;
        iArr30[i57] = unsignedExpGolombCodedInt4;
        boolean[][] zArr13 = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, iArr30);
        int i62 = 1;
        int i63 = 0;
        while (i62 < unsignedExpGolombCodedInt4) {
            int i64 = bits5;
            if (bits5 == i61) {
                for (int i65 = 0; i65 < iArr4[i62]; i65++) {
                    zArr12[i62][i65] = parsableNalUnitBitArray.readBit();
                    int i66 = iArr28[i62];
                    boolean z = zArr12[i62][i65];
                    iArr28[i62] = i66 + (z ? 1 : 0);
                    if (z) {
                        iArr27[i62] = iArr3[i62][i65];
                    }
                }
            }
            if (i63 == 0) {
                i3 = 0;
                if (iArr3[i62][0] == 0 && zArr12[i62][0]) {
                    for (int i67 = 1; i67 < iArr4[i62]; i67++) {
                        if (iArr3[i62][i67] == i2 && zArr12[i62][i2]) {
                            i63 = i62;
                        }
                    }
                }
            } else {
                i3 = 0;
            }
            int i68 = i3;
            while (i68 < iArr4[i62]) {
                if (unsignedExpGolombCodedInt3 > 1) {
                    zArr13[i62][i68] = zArr12[i62][i68];
                    zArr = zArr4;
                    i4 = unsignedExpGolombCodedInt3;
                    int iLog2 = DoubleMath.log2(unsignedExpGolombCodedInt3, RoundingMode.CEILING);
                    if (zArr13[i62][i68]) {
                        i5 = i2;
                        break;
                    }
                    int i69 = ((H265LayerInfo) immutableListBuild.get(iArr3[i62][i68])).layerIdInVps;
                    int i70 = i3;
                    while (true) {
                        if (i70 >= i68) {
                            i5 = i2;
                            break;
                        }
                        i5 = i2;
                        if (zArr10[i69][((H265LayerInfo) immutableListBuild.get(iArr3[i62][i70])).layerIdInVps]) {
                            zArr13[i62][i68] = true;
                            break;
                        }
                        i70++;
                        i2 = i5;
                    }
                    if (zArr13[i62][i68]) {
                        if (i63 > 0 && i62 == i63) {
                            iArr29[i68] = parsableNalUnitBitArray.readBits(iLog2);
                        } else {
                            parsableNalUnitBitArray.skipBits(iLog2);
                        }
                    }
                } else {
                    i4 = unsignedExpGolombCodedInt3;
                    i5 = i2;
                    zArr = zArr4;
                }
                i68++;
                zArr4 = zArr;
                unsignedExpGolombCodedInt3 = i4;
                i2 = i5;
            }
            int i71 = unsignedExpGolombCodedInt3;
            int i72 = i2;
            boolean[][] zArr14 = zArr4;
            if (iArr28[i62] == 1 && iArr20[iArr27[i62]] > 0) {
                parsableNalUnitBitArray.skipBit();
            }
            i62++;
            bits5 = i64;
            zArr4 = zArr14;
            unsignedExpGolombCodedInt3 = i71;
            i2 = i72;
            i61 = 2;
        }
        boolean[][] zArr15 = zArr4;
        if (i63 == 0) {
            return new H265VpsData(h265NalHeader, null, h265ProfileTierLevelsAndIndices, null, null);
        }
        H265RepFormatsAndIndices h265RepFormatsAndIndices = parseH265RepFormatsAndIndices(parsableNalUnitBitArray, i8);
        parsableNalUnitBitArray.skipBits(2);
        for (int i73 = 1; i73 < i8; i73++) {
            if (iArr20[iArr25[i73]] == 0) {
                parsableNalUnitBitArray.skipBit();
            }
        }
        skipH265DpbSize(parsableNalUnitBitArray, unsignedExpGolombCodedInt4, iArr22, iArr4, zArr13);
        skipToH265VuiPresentFlagAfterDpbSize(parsableNalUnitBitArray, i8, zArr15);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.byteAlign();
            h265VideoSignalInfosAndIndices = parseH265VideoSignalInfosAndIndices(parsableNalUnitBitArray, i8, unsignedExpGolombCodedInt, iArr22);
        } else {
            h265VideoSignalInfosAndIndices = null;
        }
        return new H265VpsData(h265NalHeader, immutableListBuild, new H265ProfileTierLevelsAndIndices(immutableList2, iArr29), h265RepFormatsAndIndices, h265VideoSignalInfosAndIndices);
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] bArr, int i, int i2, H265VpsData h265VpsData) {
        return parseH265SpsNalUnitPayload(bArr, i + 2, i2, parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i, i2)), h265VpsData);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:86:0x01d9  */
    public static H265SpsData parseH265SpsNalUnitPayload(byte[] bArr, int i, int i2, H265NalHeader h265NalHeader, H265VpsData h265VpsData) {
        int i3;
        int unsignedExpGolombCodedInt;
        int iApplyConformanceWindowToHeight;
        int iApplyConformanceWindowToWidth;
        int unsignedExpGolombCodedInt2;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int iMax;
        float f;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iIsoColorPrimariesToColorSpace;
        int iIsoTransferCharacteristicsToColorTransfer;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        parsableNalUnitBitArray.skipBits(4);
        int bits = parsableNalUnitBitArray.readBits(3);
        boolean z = h265NalHeader.layerId != 0 && bits == 7;
        int i14 = (h265VpsData == null || h265VpsData.layerInfos.isEmpty()) ? 0 : h265VpsData.layerInfos.get(Math.min(h265NalHeader.layerId, h265VpsData.layerInfos.size() - 1)).layerIdInVps;
        H265ProfileTierLevel h265ProfileTierLevel = null;
        if (!z) {
            parsableNalUnitBitArray.skipBit();
            h265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, bits, null);
        } else if (h265VpsData != null && h265VpsData.profileTierLevelsAndIndices.profileTierLevels.size() > (i3 = h265VpsData.profileTierLevelsAndIndices.indices[i14])) {
            h265ProfileTierLevel = h265VpsData.profileTierLevelsAndIndices.profileTierLevels.get(i3);
        }
        int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (z) {
            int bits2 = parsableNalUnitBitArray.readBit() ? parsableNalUnitBitArray.readBits(8) : -1;
            if (h265VpsData == null || h265VpsData.repFormatsAndIndices == null) {
                i5 = 0;
                i4 = 0;
                i7 = 0;
                i8 = 0;
                unsignedExpGolombCodedInt2 = 0;
                unsignedExpGolombCodedInt = 0;
                i6 = 0;
            } else {
                if (bits2 == -1) {
                    bits2 = h265VpsData.repFormatsAndIndices.indices[i14];
                }
                if (bits2 == -1 || h265VpsData.repFormatsAndIndices.repFormats.size() <= bits2) {
                    i5 = 0;
                    i4 = 0;
                    i7 = 0;
                    i8 = 0;
                    unsignedExpGolombCodedInt2 = 0;
                    unsignedExpGolombCodedInt = 0;
                    i6 = 0;
                } else {
                    H265RepFormat h265RepFormat = h265VpsData.repFormatsAndIndices.repFormats.get(bits2);
                    unsignedExpGolombCodedInt = h265RepFormat.chromaFormatIdc;
                    i6 = h265RepFormat.width;
                    i4 = h265RepFormat.height;
                    i7 = h265RepFormat.bitDepthLumaMinus8;
                    unsignedExpGolombCodedInt2 = h265RepFormat.bitDepthChromaMinus8;
                    i5 = i4;
                    i8 = i6;
                }
            }
        } else {
            unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (unsignedExpGolombCodedInt == 3) {
                parsableNalUnitBitArray.skipBit();
            }
            int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (parsableNalUnitBitArray.readBit()) {
                int unsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                iApplyConformanceWindowToWidth = applyConformanceWindowToWidth(unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt, unsignedExpGolombCodedInt6, unsignedExpGolombCodedInt7);
                iApplyConformanceWindowToHeight = applyConformanceWindowToHeight(unsignedExpGolombCodedInt5, unsignedExpGolombCodedInt, unsignedExpGolombCodedInt8, unsignedExpGolombCodedInt9);
            } else {
                iApplyConformanceWindowToHeight = unsignedExpGolombCodedInt5;
                iApplyConformanceWindowToWidth = unsignedExpGolombCodedInt4;
            }
            int unsignedExpGolombCodedInt10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            i4 = iApplyConformanceWindowToHeight;
            i5 = unsignedExpGolombCodedInt5;
            i6 = iApplyConformanceWindowToWidth;
            i7 = unsignedExpGolombCodedInt10;
            i8 = unsignedExpGolombCodedInt4;
        }
        int unsignedExpGolombCodedInt11 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (z) {
            iMax = -1;
        } else {
            int i15 = parsableNalUnitBitArray.readBit() ? 0 : bits;
            iMax = -1;
            while (i15 <= bits) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                iMax = Math.max(parsableNalUnitBitArray.readUnsignedExpGolombCodedInt(), iMax);
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                i15++;
                i5 = i5;
            }
        }
        int i16 = i5;
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (parsableNalUnitBitArray.readBit()) {
            if (z ? parsableNalUnitBitArray.readBit() : false) {
                parsableNalUnitBitArray.skipBits(6);
            } else if (parsableNalUnitBitArray.readBit()) {
                skipH265ScalingList(parsableNalUnitBitArray);
            }
        }
        int i17 = 2;
        parsableNalUnitBitArray.skipBits(2);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(8);
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        skipH265ShortTermReferencePictureSets(parsableNalUnitBitArray);
        if (parsableNalUnitBitArray.readBit()) {
            int unsignedExpGolombCodedInt12 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int i18 = 0;
            while (i18 < unsignedExpGolombCodedInt12) {
                parsableNalUnitBitArray.skipBits(unsignedExpGolombCodedInt11 + 5);
                i18++;
                i17 = 2;
            }
        }
        parsableNalUnitBitArray.skipBits(i17);
        if (parsableNalUnitBitArray.readBit()) {
            if (parsableNalUnitBitArray.readBit()) {
                int bits3 = parsableNalUnitBitArray.readBits(8);
                if (bits3 == 255) {
                    int bits4 = parsableNalUnitBitArray.readBits(16);
                    int bits5 = parsableNalUnitBitArray.readBits(16);
                    if (bits4 == 0 || bits5 == 0) {
                        f = 1.0f;
                    } else {
                        f = bits4 / bits5;
                    }
                } else {
                    float[] fArr = ASPECT_RATIO_IDC_VALUES;
                    if (bits3 < fArr.length) {
                        f = fArr[bits3];
                    } else {
                        Log.w(TAG, "Unexpected aspect_ratio_idc value: " + bits3);
                        f = 1.0f;
                    }
                }
            } else {
                f = 1.0f;
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBit();
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBits(3);
                i12 = parsableNalUnitBitArray.readBit() ? 1 : 2;
                if (parsableNalUnitBitArray.readBit()) {
                    int bits6 = parsableNalUnitBitArray.readBits(8);
                    int bits7 = parsableNalUnitBitArray.readBits(8);
                    parsableNalUnitBitArray.skipBits(8);
                    iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(bits6);
                    iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(bits7);
                } else {
                    iIsoColorPrimariesToColorSpace = -1;
                    iIsoTransferCharacteristicsToColorTransfer = -1;
                }
            } else if (h265VpsData == null || h265VpsData.videoSignalInfosAndIndices == null || h265VpsData.videoSignalInfosAndIndices.videoSignalInfos.size() <= (i13 = h265VpsData.videoSignalInfosAndIndices.indices[i14])) {
                i12 = -1;
                iIsoColorPrimariesToColorSpace = -1;
                iIsoTransferCharacteristicsToColorTransfer = -1;
            } else {
                H265VideoSignalInfo h265VideoSignalInfo = h265VpsData.videoSignalInfosAndIndices.videoSignalInfos.get(i13);
                iIsoColorPrimariesToColorSpace = h265VideoSignalInfo.colorSpace;
                int i19 = h265VideoSignalInfo.colorRange;
                iIsoTransferCharacteristicsToColorTransfer = h265VideoSignalInfo.colorTransfer;
                i12 = i19;
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                i4 *= 2;
            }
            i11 = iIsoTransferCharacteristicsToColorTransfer;
            i10 = i12;
            i9 = iIsoColorPrimariesToColorSpace;
        } else {
            f = 1.0f;
            i9 = -1;
            i10 = -1;
            i11 = -1;
        }
        return new H265SpsData(h265NalHeader, bits, h265ProfileTierLevel, unsignedExpGolombCodedInt, i7, unsignedExpGolombCodedInt2, unsignedExpGolombCodedInt3, i6, i4, i8, i16, f, iMax, i9, i10, i11);
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i, int i2) {
        return parsePpsNalUnitPayload(bArr, i + 1, i2);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    public static H265Sei3dRefDisplayInfoData parseH265Sei3dRefDisplayInfo(byte[] bArr, int i, int i2) {
        byte b;
        int iMax;
        int iMax2;
        int i3 = i + 2;
        int i4 = i2 - 1;
        while (true) {
            b = bArr[i4];
            if (b != 0 || i4 <= i3) {
                break;
            }
            i4--;
        }
        if (b != 0 && i4 > i3) {
            ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i3, i4 + 1);
            while (parsableNalUnitBitArray.canReadBits(16)) {
                int bits = parsableNalUnitBitArray.readBits(8);
                int i5 = 0;
                while (bits == 255) {
                    i5 += 255;
                    bits = parsableNalUnitBitArray.readBits(8);
                }
                int i6 = i5 + bits;
                int bits2 = parsableNalUnitBitArray.readBits(8);
                int i7 = 0;
                while (bits2 == 255) {
                    i7 += 255;
                    bits2 = parsableNalUnitBitArray.readBits(8);
                }
                int i8 = i7 + bits2;
                if (i8 == 0 || !parsableNalUnitBitArray.canReadBits(i8)) {
                    break;
                }
                if (i6 == 176) {
                    int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    boolean bit = parsableNalUnitBitArray.readBit();
                    int unsignedExpGolombCodedInt2 = bit ? parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() : 0;
                    int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    int unsignedExpGolombCodedInt4 = -1;
                    int unsignedExpGolombCodedInt5 = -1;
                    int bits3 = -1;
                    int bits4 = -1;
                    int i9 = -1;
                    int bits5 = -1;
                    for (int i10 = 0; i10 <= unsignedExpGolombCodedInt3; i10++) {
                        unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        unsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        bits3 = parsableNalUnitBitArray.readBits(6);
                        if (bits3 == 63) {
                            return null;
                        }
                        if (bits3 == 0) {
                            iMax = Math.max(0, unsignedExpGolombCodedInt - 30);
                        } else {
                            iMax = Math.max(0, (bits3 + unsignedExpGolombCodedInt) - 31);
                        }
                        bits4 = parsableNalUnitBitArray.readBits(iMax);
                        if (bit) {
                            int bits6 = parsableNalUnitBitArray.readBits(6);
                            if (bits6 == 63) {
                                return null;
                            }
                            if (bits6 == 0) {
                                iMax2 = Math.max(0, unsignedExpGolombCodedInt2 - 30);
                            } else {
                                iMax2 = Math.max(0, (bits6 + unsignedExpGolombCodedInt2) - 31);
                            }
                            i9 = bits6;
                            bits5 = parsableNalUnitBitArray.readBits(iMax2);
                        }
                        if (parsableNalUnitBitArray.readBit()) {
                            parsableNalUnitBitArray.skipBits(10);
                        }
                    }
                    return new H265Sei3dRefDisplayInfoData(unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2, unsignedExpGolombCodedInt3 + 1, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, bits3, bits4, i9, bits5);
                }
                parsableNalUnitBitArray.skipBits(i8 * 8);
            }
        }
        return null;
    }

    public static int findNalUnit(byte[] bArr, int i, int i2, boolean[] zArr) {
        int i3 = i2 - i;
        Preconditions.checkState(i3 >= 0);
        if (i3 == 0) {
            return i2;
        }
        if (zArr[0]) {
            clearPrefixFlags(zArr);
            return i - 3;
        }
        if (i3 > 1 && zArr[1] && bArr[i] == 1) {
            clearPrefixFlags(zArr);
            return i - 2;
        }
        if (i3 > 2 && zArr[2] && bArr[i] == 0 && bArr[i + 1] == 1) {
            clearPrefixFlags(zArr);
            return i - 1;
        }
        int i4 = i2 - 1;
        int i5 = i + 2;
        while (i5 < i4) {
            byte b = bArr[i5];
            if ((b & 254) == 0) {
                int i6 = i5 - 2;
                if (bArr[i6] == 0 && bArr[i5 - 1] == 0 && b == 1) {
                    clearPrefixFlags(zArr);
                    return i6;
                }
                i5 -= 2;
            }
            i5 += 3;
        }
        zArr[0] = i3 <= 2 ? !(i3 != 2 ? !(zArr[1] && bArr[i4] == 1) : !(zArr[2] && bArr[i2 + (-2)] == 0 && bArr[i4] == 1)) : bArr[i2 + (-3)] == 0 && bArr[i2 + (-2)] == 0 && bArr[i4] == 1;
        zArr[1] = i3 <= 1 ? zArr[2] && bArr[i4] == 0 : bArr[i2 + (-2)] == 0 && bArr[i4] == 0;
        zArr[2] = bArr[i4] == 0;
        return i2;
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static String getH265BaseLayerCodecsString(List<byte[]> list) {
        for (int i = 0; i < list.size(); i++) {
            byte[] bArr = list.get(i);
            int length = bArr.length;
            if (length > 3) {
                ImmutableList<Integer> immutableListFindNalUnitPositions = findNalUnitPositions(bArr);
                for (int i2 = 0; i2 < immutableListFindNalUnitPositions.size(); i2++) {
                    if (immutableListFindNalUnitPositions.get(i2).intValue() + 3 < length) {
                        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, immutableListFindNalUnitPositions.get(i2).intValue() + 3, length);
                        H265NalHeader h265NalHeader = parseH265NalHeader(parsableNalUnitBitArray);
                        if (h265NalHeader.nalUnitType == 33 && h265NalHeader.layerId == 0) {
                            return createCodecStringFromH265SpsPalyoad(parsableNalUnitBitArray);
                        }
                    }
                }
            }
        }
        return null;
    }

    private static ImmutableList<Integer> findNalUnitPositions(byte[] bArr) {
        boolean[] zArr = new boolean[3];
        ImmutableList.Builder builder = ImmutableList.builder();
        int i = 0;
        while (i < bArr.length) {
            int iFindNalUnit = findNalUnit(bArr, i, bArr.length, zArr);
            if (iFindNalUnit != bArr.length) {
                builder.add(Integer.valueOf(iFindNalUnit));
            }
            i = iFindNalUnit + 3;
        }
        return builder.build();
    }

    private static String createCodecStringFromH265SpsPalyoad(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(4);
        int bits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        H265ProfileTierLevel h265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, bits, null);
        return CodecSpecificDataUtil.buildHevcCodecString(h265ProfileTierLevel.generalProfileSpace, h265ProfileTierLevel.generalTierFlag, h265ProfileTierLevel.generalProfileIdc, h265ProfileTierLevel.generalProfileCompatibilityFlags, h265ProfileTierLevel.constraintBytes, h265ProfileTierLevel.generalLevelIdc);
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    private static void skipH265HrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray, boolean z, int i) {
        ?? r9;
        ?? r1;
        boolean bit;
        boolean bit2;
        if (z) {
            boolean bit3 = parsableNalUnitBitArray.readBit();
            boolean bit4 = parsableNalUnitBitArray.readBit();
            if (bit3 || bit4) {
                bit = parsableNalUnitBitArray.readBit();
                if (bit) {
                    parsableNalUnitBitArray.skipBits(19);
                }
                parsableNalUnitBitArray.skipBits(8);
                if (bit) {
                    parsableNalUnitBitArray.skipBits(4);
                }
                parsableNalUnitBitArray.skipBits(15);
                r1 = bit4;
                r9 = bit3;
            } else {
                bit = false;
                r1 = bit4;
                r9 = bit3;
            }
        } else {
            r9 = 0;
            r1 = 0;
            bit = false;
        }
        for (int i2 = 0; i2 <= i; i2++) {
            boolean bit5 = parsableNalUnitBitArray.readBit();
            if (!bit5) {
                bit5 = parsableNalUnitBitArray.readBit();
            }
            if (bit5) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                bit2 = false;
            } else {
                bit2 = parsableNalUnitBitArray.readBit();
            }
            int unsignedExpGolombCodedInt = !bit2 ? parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() : 0;
            int i3 = r9 + r1;
            for (int i4 = 0; i4 < i3; i4++) {
                for (int i5 = 0; i5 <= unsignedExpGolombCodedInt; i5++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    if (bit) {
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    }
                    parsableNalUnitBitArray.skipBit();
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x005e  */
    /* JADX WARN: Code duplicated, block: B:23:0x0064  */
    /* JADX WARN: Code duplicated, block: B:26:0x006c  */
    /* JADX WARN: Code duplicated, block: B:30:0x0076  */
    /* JADX WARN: Code duplicated, block: B:39:0x006e A[SYNTHETIC] */
    private static H265ProfileTierLevel parseH265ProfileTierLevel(ParsableNalUnitBitArray parsableNalUnitBitArray, boolean z, int i, H265ProfileTierLevel h265ProfileTierLevel) {
        int[] iArr;
        int i2;
        boolean z2;
        int i3;
        int i4;
        boolean bit;
        int bits;
        int i5;
        int i6;
        int[] iArr2 = new int[6];
        if (z) {
            int bits2 = parsableNalUnitBitArray.readBits(2);
            bit = parsableNalUnitBitArray.readBit();
            bits = parsableNalUnitBitArray.readBits(5);
            i5 = 0;
            for (int i7 = 0; i7 < 32; i7++) {
                if (parsableNalUnitBitArray.readBit()) {
                    i5 |= 1 << i7;
                }
            }
            for (int i8 = 0; i8 < 6; i8++) {
                iArr2[i8] = parsableNalUnitBitArray.readBits(8);
            }
            i2 = bits2;
        } else {
            if (h265ProfileTierLevel != null) {
                int i9 = h265ProfileTierLevel.generalProfileSpace;
                bit = h265ProfileTierLevel.generalTierFlag;
                bits = h265ProfileTierLevel.generalProfileIdc;
                i5 = h265ProfileTierLevel.generalProfileCompatibilityFlags;
                iArr2 = h265ProfileTierLevel.constraintBytes;
                i2 = i9;
            } else {
                iArr = iArr2;
                i2 = 0;
                z2 = false;
                i3 = 0;
                i4 = 0;
            }
            int bits3 = parsableNalUnitBitArray.readBits(8);
            i6 = 0;
            for (int i10 = 0; i10 < i; i10++) {
                if (parsableNalUnitBitArray.readBit()) {
                    i6 += 88;
                }
                if (parsableNalUnitBitArray.readBit()) {
                    i6 += 8;
                }
            }
            parsableNalUnitBitArray.skipBits(i6);
            if (i > 0) {
                parsableNalUnitBitArray.skipBits((8 - i) * 2);
            }
            return new H265ProfileTierLevel(i2, z2, i3, i4, iArr, bits3);
        }
        iArr = iArr2;
        z2 = bit;
        i3 = bits;
        i4 = i5;
        int bits4 = parsableNalUnitBitArray.readBits(8);
        i6 = 0;
        while (i10 < i) {
            if (parsableNalUnitBitArray.readBit()) {
                i6 += 88;
            }
            if (parsableNalUnitBitArray.readBit()) {
                i6 += 8;
            }
        }
        parsableNalUnitBitArray.skipBits(i6);
        if (i > 0) {
            parsableNalUnitBitArray.skipBits((8 - i) * 2);
        }
        return new H265ProfileTierLevel(i2, z2, i3, i4, iArr, bits4);
    }

    private static H265RepFormatsAndIndices parseH265RepFormatsAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int i2 = unsignedExpGolombCodedInt + 1;
        ImmutableList.Builder builderBuilderWithExpectedSize = ImmutableList.builderWithExpectedSize(i2);
        int[] iArr = new int[i];
        for (int i3 = 0; i3 < i2; i3++) {
            builderBuilderWithExpectedSize.add(parseH265RepFormat(parsableNalUnitBitArray));
        }
        int i4 = 1;
        if (i2 <= 1 || !parsableNalUnitBitArray.readBit()) {
            while (i4 < i) {
                iArr[i4] = Math.min(i4, unsignedExpGolombCodedInt);
                i4++;
            }
        } else {
            int iLog2 = DoubleMath.log2(i2, RoundingMode.CEILING);
            while (i4 < i) {
                iArr[i4] = parsableNalUnitBitArray.readBits(iLog2);
                i4++;
            }
        }
        return new H265RepFormatsAndIndices(builderBuilderWithExpectedSize.build(), iArr);
    }

    private static H265RepFormat parseH265RepFormat(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int i;
        int i2;
        int bits;
        int bits2 = parsableNalUnitBitArray.readBits(16);
        int bits3 = parsableNalUnitBitArray.readBits(16);
        if (parsableNalUnitBitArray.readBit()) {
            int bits4 = parsableNalUnitBitArray.readBits(2);
            if (bits4 == 3) {
                parsableNalUnitBitArray.skipBit();
            }
            int bits5 = parsableNalUnitBitArray.readBits(4);
            bits = parsableNalUnitBitArray.readBits(4);
            i2 = bits5;
            i = bits4;
        } else {
            i = 0;
            i2 = 0;
            bits = 0;
        }
        if (parsableNalUnitBitArray.readBit()) {
            int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            bits2 = applyConformanceWindowToWidth(bits2, i, unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2);
            bits3 = applyConformanceWindowToHeight(bits3, i, unsignedExpGolombCodedInt3, unsignedExpGolombCodedInt4);
        }
        return new H265RepFormat(i, i2, bits, bits2, bits3);
    }

    private static void skipH265DpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, int[] iArr, int[] iArr2, boolean[][] zArr) {
        boolean bit;
        for (int i2 = 1; i2 < i; i2++) {
            boolean bit2 = parsableNalUnitBitArray.readBit();
            int i3 = 0;
            while (i3 < iArr[i2]) {
                if (i3 <= 0 || !bit2) {
                    bit = i3 == 0;
                } else {
                    bit = parsableNalUnitBitArray.readBit();
                }
                if (bit) {
                    for (int i4 = 0; i4 < iArr2[i2]; i4++) {
                        if (zArr[i2][i4]) {
                            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        }
                    }
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                i3++;
            }
        }
    }

    private static void skipToH265VuiPresentFlagAfterDpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, boolean[][] zArr) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 2;
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(unsignedExpGolombCodedInt);
        } else {
            for (int i2 = 1; i2 < i; i2++) {
                for (int i3 = 0; i3 < i2; i3++) {
                    if (zArr[i2][i3]) {
                        parsableNalUnitBitArray.skipBits(unsignedExpGolombCodedInt);
                    }
                }
            }
        }
        int unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int i4 = 1; i4 <= unsignedExpGolombCodedInt2; i4++) {
            parsableNalUnitBitArray.skipBits(8);
        }
    }

    private static H265VideoSignalInfosAndIndices parseH265VideoSignalInfosAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, int i2, int[] iArr) {
        if (!parsableNalUnitBitArray.readBit() ? parsableNalUnitBitArray.readBit() : true) {
            parsableNalUnitBitArray.skipBit();
        }
        boolean bit = parsableNalUnitBitArray.readBit();
        boolean bit2 = parsableNalUnitBitArray.readBit();
        if (bit || bit2) {
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < iArr[i3]; i4++) {
                    boolean bit3 = bit ? parsableNalUnitBitArray.readBit() : false;
                    boolean bit4 = bit2 ? parsableNalUnitBitArray.readBit() : false;
                    if (bit3) {
                        parsableNalUnitBitArray.skipBits(32);
                    }
                    if (bit4) {
                        parsableNalUnitBitArray.skipBits(18);
                    }
                }
            }
        }
        boolean bit5 = parsableNalUnitBitArray.readBit();
        int bits = bit5 ? parsableNalUnitBitArray.readBits(4) + 1 : i;
        ImmutableList.Builder builderBuilderWithExpectedSize = ImmutableList.builderWithExpectedSize(bits);
        int[] iArr2 = new int[i];
        for (int i5 = 0; i5 < bits; i5++) {
            builderBuilderWithExpectedSize.add(parseH265VideoSignalInfo(parsableNalUnitBitArray));
        }
        if (bit5 && bits > 1) {
            for (int i6 = 0; i6 < i; i6++) {
                iArr2[i6] = parsableNalUnitBitArray.readBits(4);
            }
        }
        return new H265VideoSignalInfosAndIndices(builderBuilderWithExpectedSize.build(), iArr2);
    }

    private static H265VideoSignalInfo parseH265VideoSignalInfo(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(3);
        int i = parsableNalUnitBitArray.readBit() ? 1 : 2;
        int iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(parsableNalUnitBitArray.readBits(8));
        int iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(parsableNalUnitBitArray.readBits(8));
        parsableNalUnitBitArray.skipBits(8);
        return new H265VideoSignalInfo(iIsoColorPrimariesToColorSpace, i, iIsoTransferCharacteristicsToColorTransfer);
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int signedExpGolombCodedInt = 8;
        int i2 = 8;
        for (int i3 = 0; i3 < i; i3++) {
            if (signedExpGolombCodedInt != 0) {
                signedExpGolombCodedInt = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i2) + 256) % 256;
            }
            if (signedExpGolombCodedInt != 0) {
                i2 = signedExpGolombCodedInt;
            }
        }
    }

    private static void skipHrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        parsableNalUnitBitArray.skipBits(8);
        for (int i = 0; i < unsignedExpGolombCodedInt; i++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBits(20);
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int i = 0; i < 4; i++) {
            int i2 = 0;
            while (i2 < 6) {
                int i3 = 1;
                if (!parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int iMin = Math.min(64, 1 << ((i << 1) + 4));
                    if (i > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i4 = 0; i4 < iMin; i4++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (i == 3) {
                    i3 = 3;
                }
                i2 += i3;
            }
        }
    }

    private static void skipH265ShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int[] iArr = new int[0];
        int[] iArrCopyOf = new int[0];
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < unsignedExpGolombCodedInt; i3++) {
            if (i3 != 0 && parsableNalUnitBitArray.readBit()) {
                int i4 = i + i2;
                int unsignedExpGolombCodedInt2 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * 2)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                int i5 = i4 + 1;
                boolean[] zArr = new boolean[i5];
                for (int i6 = 0; i6 <= i4; i6++) {
                    if (!parsableNalUnitBitArray.readBit()) {
                        zArr[i6] = parsableNalUnitBitArray.readBit();
                    } else {
                        zArr[i6] = true;
                    }
                }
                int[] iArr2 = new int[i5];
                int[] iArr3 = new int[i5];
                int i7 = 0;
                for (int i8 = i2 - 1; i8 >= 0; i8--) {
                    int i9 = iArrCopyOf[i8] + unsignedExpGolombCodedInt2;
                    if (i9 < 0 && zArr[i + i8]) {
                        iArr2[i7] = i9;
                        i7++;
                    }
                }
                if (unsignedExpGolombCodedInt2 < 0 && zArr[i4]) {
                    iArr2[i7] = unsignedExpGolombCodedInt2;
                    i7++;
                }
                for (int i10 = 0; i10 < i; i10++) {
                    int i11 = iArr[i10] + unsignedExpGolombCodedInt2;
                    if (i11 < 0 && zArr[i10]) {
                        iArr2[i7] = i11;
                        i7++;
                    }
                }
                int[] iArrCopyOf2 = Arrays.copyOf(iArr2, i7);
                int i12 = 0;
                for (int i13 = i - 1; i13 >= 0; i13--) {
                    int i14 = iArr[i13] + unsignedExpGolombCodedInt2;
                    if (i14 > 0 && zArr[i13]) {
                        iArr3[i12] = i14;
                        i12++;
                    }
                }
                if (unsignedExpGolombCodedInt2 > 0 && zArr[i4]) {
                    iArr3[i12] = unsignedExpGolombCodedInt2;
                    i12++;
                }
                for (int i15 = 0; i15 < i2; i15++) {
                    int i16 = iArrCopyOf[i15] + unsignedExpGolombCodedInt2;
                    if (i16 > 0 && zArr[i + i15]) {
                        iArr3[i12] = i16;
                        i12++;
                    }
                }
                iArrCopyOf = Arrays.copyOf(iArr3, i12);
                iArr = iArrCopyOf2;
                i = i7;
                i2 = i12;
            } else {
                int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int[] iArr4 = new int[unsignedExpGolombCodedInt3];
                int i17 = 0;
                while (i17 < unsignedExpGolombCodedInt3) {
                    iArr4[i17] = (i17 > 0 ? iArr4[i17 - 1] : 0) - (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                    parsableNalUnitBitArray.skipBit();
                    i17++;
                }
                int[] iArr5 = new int[unsignedExpGolombCodedInt4];
                int i18 = 0;
                while (i18 < unsignedExpGolombCodedInt4) {
                    iArr5[i18] = (i18 > 0 ? iArr5[i18 - 1] : 0) + parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                    parsableNalUnitBitArray.skipBit();
                    i18++;
                }
                i = unsignedExpGolombCodedInt3;
                iArr = iArr4;
                i2 = unsignedExpGolombCodedInt4;
                iArrCopyOf = iArr5;
            }
        }
    }

    private static String getNalStructureMimeType(Format format) {
        if (Objects.equals(format.sampleMimeType, "video/dolby-vision") && format.codecs != null) {
            if (format.codecs.startsWith("dva1") || format.codecs.startsWith("dvav")) {
                return "video/avc";
            }
            if (format.codecs.startsWith("dvh1") || format.codecs.startsWith("dvhe")) {
                return "video/hevc";
            }
        }
        return format.sampleMimeType;
    }

    private NalUnitUtil() {
    }
}
