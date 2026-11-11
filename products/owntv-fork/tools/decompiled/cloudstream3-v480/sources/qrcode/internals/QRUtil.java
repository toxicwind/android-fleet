package qrcode.internals;

import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.ConnectionResult;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.mozilla.classfile.ByteCode;
import org.mozilla.universalchardet.prober.HebrewProber;
import org.mozilla.universalchardet.prober.contextanalysis.SJISContextAnalysis;
import qrcode.raw.ErrorCorrectionLevel;
import qrcode.raw.MaskPattern;
import qrcode.raw.QRCodeDataType;

/* JADX INFO: compiled from: QRUtil.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001e\u0010\r\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0007J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007J\u000e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u000e\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007J\u000e\u0010%\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007J\u0010\u0010&\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0002R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010 \u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lqrcode/internals/QRUtil;", "", "<init>", "()V", "getPatternPosition", "", "typeNumber", "", "PATTERN_POSITION_TABLE", "", "[[I", "MAX_LENGTH", "[[[I", "getMaxLength", "dataType", "Lqrcode/raw/QRCodeDataType;", "errorCorrectionLevel", "Lqrcode/raw/ErrorCorrectionLevel;", "getErrorCorrectPolynomial", "Lqrcode/internals/Polynomial;", "errorCorrectLength", "getMask", "", "maskPattern", "Lqrcode/raw/MaskPattern;", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "j", "getDataType", CmcdData.STREAMING_FORMAT_SS, "", "isNumber", "isAlphaNum", "G15", "G18", "G15_MASK", "getBCHTypeInfo", "data", "getBCHTypeNumber", "getBCHDigit", "qrcode-kotlin_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class QRUtil {
    private static final int G15 = 1335;
    private static final int G15_MASK = 21522;
    private static final int G18 = 7973;
    public static final QRUtil INSTANCE = new QRUtil();
    private static final int[][] PATTERN_POSITION_TABLE = {new int[0], new int[]{6, 18}, new int[]{6, 22}, new int[]{6, 26}, new int[]{6, 30}, new int[]{6, 34}, new int[]{6, 22, 38}, new int[]{6, 24, 42}, new int[]{6, 26, 46}, new int[]{6, 28, 50}, new int[]{6, 30, 54}, new int[]{6, 32, 58}, new int[]{6, 34, 62}, new int[]{6, 26, 46, 66}, new int[]{6, 26, 48, 70}, new int[]{6, 26, 50, 74}, new int[]{6, 30, 54, 78}, new int[]{6, 30, 56, 82}, new int[]{6, 30, 58, 86}, new int[]{6, 34, 62, 90}, new int[]{6, 28, 50, 72, 94}, new int[]{6, 26, 50, 74, 98}, new int[]{6, 30, 54, 78, 102}, new int[]{6, 28, 54, 80, 106}, new int[]{6, 32, 58, 84, 110}, new int[]{6, 30, 58, 86, 114}, new int[]{6, 34, 62, 90, 118}, new int[]{6, 26, 50, 74, 98, 122}, new int[]{6, 30, 54, 78, 102, 126}, new int[]{6, 26, 52, 78, 104, 130}, new int[]{6, 30, 56, 82, 108, 134}, new int[]{6, 34, 60, 86, 112, 138}, new int[]{6, 30, 58, 86, 114, 142}, new int[]{6, 34, 62, 90, 118, 146}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][][] MAX_LENGTH = {new int[][]{new int[]{41, 25, 17, 10}, new int[]{34, 20, 14, 8}, new int[]{27, 16, 11, 7}, new int[]{17, 10, 7, 4}}, new int[][]{new int[]{77, 47, 32, 20}, new int[]{63, 38, 26, 16}, new int[]{48, 29, 20, 12}, new int[]{34, 20, 14, 8}}, new int[][]{new int[]{127, 77, 53, 32}, new int[]{101, 61, 42, 26}, new int[]{77, 47, 32, 20}, new int[]{58, 35, 24, 15}}, new int[][]{new int[]{187, 114, 78, 48}, new int[]{149, 90, 62, 38}, new int[]{111, 67, 46, 28}, new int[]{82, 50, 34, 21}}, new int[][]{new int[]{255, 154, 106, 65}, new int[]{202, 122, 84, 52}, new int[]{144, 87, 60, 37}, new int[]{106, 64, 44, 27}}, new int[][]{new int[]{MediaError.DetailedErrorCode.DASH_NO_INIT, ByteCode.MONITOREXIT, 134, 82}, new int[]{255, 154, 106, 65}, new int[]{178, 108, 74, 45}, new int[]{139, 84, 58, 36}}, new int[][]{new int[]{370, 224, 154, 95}, new int[]{293, 178, 122, 75}, new int[]{ComposerKt.reuseKey, 125, 86, 53}, new int[]{154, 93, 64, 39}}, new int[][]{new int[]{461, 279, 192, 118}, new int[]{365, 221, 152, 93}, new int[]{GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, 157, 108, 66}, new int[]{202, 122, 84, 52}}, new int[][]{new int[]{552, 335, 230, 141}, new int[]{432, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED, 180, 111}, new int[]{312, 189, 130, 80}, new int[]{HebrewProber.NORMAL_KAF, 143, 98, 60}}, new int[][]{new int[]{652, 395, 271, 167}, new int[]{513, 311, 213, 131}, new int[]{364, 221, 151, 93}, new int[]{288, 174, 119, 74}}, new int[][]{new int[]{772, 468, MediaError.DetailedErrorCode.DASH_NETWORK, ByteCode.IFNULL}, new int[]{TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR, 366, 251, 155}, new int[]{427, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, 177, 109}, new int[]{MediaError.DetailedErrorCode.SMOOTH_NETWORK, 200, 137, 85}}, new int[][]{new int[]{883, 535, 367, 226}, new int[]{691, 419, 287, 177}, new int[]{489, 296, 203, 125}, new int[]{374, 227, 155, 96}}, new int[][]{new int[]{AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED, 619, TypedValues.CycleType.TYPE_WAVE_PHASE, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_SELECTED}, new int[]{796, 483, MediaError.DetailedErrorCode.SMOOTH_NETWORK, ComposerKt.providerMapsKey}, new int[]{580, 352, SJISContextAnalysis.HIRAGANA_LOWBYTE_END, 149}, new int[]{427, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED, 177, 109}}, new int[][]{new int[]{1101, 667, 458, 282}, new int[]{871, 528, 362, 223}, new int[]{621, 376, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED, 159}, new int[]{468, 283, ByteCode.MONITORENTER, 120}}, new int[][]{new int[]{1250, 758, 520, 320}, new int[]{991, 600, MediaError.DetailedErrorCode.HLS_MANIFEST_PLAYLIST, 254}, new int[]{703, 426, 292, 180}, new int[]{530, MediaError.DetailedErrorCode.DASH_NETWORK, 220, 136}}, new int[][]{new int[]{1408, 854, 586, 361}, new int[]{1082, 656, 450, 277}, new int[]{775, 470, MediaError.DetailedErrorCode.DASH_NO_INIT, ByteCode.IFNULL}, new int[]{TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, 365, 250, 154}}, new int[][]{new int[]{1548, 938, 644, 397}, new int[]{1212, 734, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, 310}, new int[]{876, 531, 364, 224}, new int[]{674, 408, 280, 173}}, new int[][]{new int[]{1725, 1046, 718, 442}, new int[]{1346, 816, 560, 345}, new int[]{948, 574, 394, 243}, new int[]{746, 452, 310, ByteCode.ATHROW}}, new int[][]{new int[]{1903, 1153, 792, 488}, new int[]{ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED, 909, 624, RendererCapabilities.DECODER_SUPPORT_MASK}, new int[]{1063, 644, 442, 272}, new int[]{813, 493, 338, 208}}, new int[][]{new int[]{2061, 1249, 858, 528}, new int[]{1600, 970, 666, 410}, new int[]{1159, TypedValues.TransitionType.TYPE_TO, 482, 297}, new int[]{919, 557, 382, HebrewProber.NORMAL_KAF}}, new int[][]{new int[]{2232, 1352, 929, 572}, new int[]{1708, 1035, 711, 438}, new int[]{1224, 742, 509, 314}, new int[]{969, 587, TypedValues.CycleType.TYPE_ALPHA, 248}}, new int[][]{new int[]{2409, 1460, 1003, 618}, new int[]{1872, 1134, 779, 480}, new int[]{1358, 823, 565, 348}, new int[]{1056, 640, 439, 270}}, new int[][]{new int[]{2620, 1588, 1091, 672}, new int[]{2059, 1248, 857, 528}, new int[]{1468, 890, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_TYPE, 376}, new int[]{1108, 672, 461, 284}}, new int[][]{new int[]{2812, 1704, 1171, 721}, new int[]{2188, 1326, 911, 561}, new int[]{1588, 963, 661, 407}, new int[]{1228, 744, 511, 315}}, new int[][]{new int[]{3057, 1853, 1273, 784}, new int[]{2395, 1451, 997, 614}, new int[]{1718, 1041, 715, 440}, new int[]{1286, 779, 535, 330}}, new int[][]{new int[]{3283, 1990, 1367, 842}, new int[]{2544, 1542, 1059, 652}, new int[]{1804, 1094, 751, 462}, new int[]{1425, 864, 593, 365}}, new int[][]{new int[]{3517, 2132, 1465, 902}, new int[]{2701, 1637, 1125, 692}, new int[]{1933, 1172, 805, 496}, new int[]{1501, 910, 625, 385}}, new int[][]{new int[]{3669, 2223, 1528, 940}, new int[]{2857, 1732, 1190, 732}, new int[]{2085, 1263, 868, 534}, new int[]{1581, 958, 658, 405}}, new int[][]{new int[]{3909, 2369, 1628, 1002}, new int[]{3035, 1839, 1264, 778}, new int[]{2181, 1322, 908, 559}, new int[]{1677, 1016, 698, 430}}, new int[][]{new int[]{4158, 2520, 1732, 1066}, new int[]{3289, 1994, 1370, 843}, new int[]{2358, 1429, 982, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR}, new int[]{1782, 1080, 742, 457}}, new int[][]{new int[]{4417, 2677, 1840, 1132}, new int[]{3486, CastStatusCodes.ERROR_CAST_PLATFORM_NOT_CONNECTED, 1452, 894}, new int[]{2473, 1499, AnalyticsListener.EVENT_VIDEO_CODEC_ERROR, 634}, new int[]{1897, 1150, 790, 486}}, new int[][]{new int[]{4686, 2840, 1952, 1201}, new int[]{3693, 2238, 1538, 947}, new int[]{2670, 1618, 1112, 684}, new int[]{2022, 1226, 842, 518}}, new int[][]{new int[]{4965, 3009, 2068, 1273}, new int[]{3909, 2369, 1628, 1002}, new int[]{2805, 1700, 1168, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD}, new int[]{2157, 1307, 898, 553}}, new int[][]{new int[]{5253, 3183, 2188, 1347}, new int[]{4134, 2506, 1722, 1060}, new int[]{2949, 1787, 1228, 756}, new int[]{2301, 1394, 958, 590}}};

    /* JADX INFO: compiled from: QRUtil.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MaskPattern.values().length];
            try {
                iArr[MaskPattern.PATTERN000.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MaskPattern.PATTERN001.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MaskPattern.PATTERN010.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MaskPattern.PATTERN011.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[MaskPattern.PATTERN100.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[MaskPattern.PATTERN101.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[MaskPattern.PATTERN110.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[MaskPattern.PATTERN111.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final int getBCHDigit(int data) {
        int i = 0;
        while (data != 0) {
            i++;
            data >>>= 1;
        }
        return i;
    }

    private QRUtil() {
    }

    public final int[] getPatternPosition(int typeNumber) {
        return PATTERN_POSITION_TABLE[typeNumber - 1];
    }

    public final int getMaxLength(int typeNumber, QRCodeDataType dataType, ErrorCorrectionLevel errorCorrectionLevel) {
        Intrinsics.checkNotNullParameter(dataType, "dataType");
        Intrinsics.checkNotNullParameter(errorCorrectionLevel, "errorCorrectionLevel");
        return MAX_LENGTH[typeNumber - 1][errorCorrectionLevel.ordinal()][dataType.ordinal()];
    }

    public final Polynomial getErrorCorrectPolynomial(int errorCorrectLength) {
        Polynomial polynomial = new Polynomial(new int[]{1}, 0, 2, null);
        for (int i = 0; i < errorCorrectLength; i++) {
            polynomial = polynomial.multiply(new Polynomial(new int[]{1, QRMath.INSTANCE.gexp(i)}, 0, 2, null));
        }
        return polynomial;
    }

    public final boolean getMask(MaskPattern maskPattern, int i, int j) {
        Intrinsics.checkNotNullParameter(maskPattern, "maskPattern");
        switch (WhenMappings.$EnumSwitchMapping$0[maskPattern.ordinal()]) {
            case 1:
                return (i + j) % 2 == 0;
            case 2:
                return i % 2 == 0;
            case 3:
                return j % 3 == 0;
            case 4:
                return (i + j) % 3 == 0;
            case 5:
                return ((i / 2) + (j / 3)) % 2 == 0;
            case 6:
                int i2 = i * j;
                return (i2 % 2) + (i2 % 3) == 0;
            case 7:
                int i3 = i * j;
                return ((i3 % 2) + (i3 % 3)) % 2 == 0;
            case 8:
                return (((i * j) % 3) + ((i + j) % 2)) % 2 == 0;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final QRCodeDataType getDataType(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        if (isAlphaNum(s)) {
            if (isNumber(s)) {
                return QRCodeDataType.NUMBERS;
            }
            return QRCodeDataType.UPPER_ALPHA_NUM;
        }
        return QRCodeDataType.DEFAULT;
    }

    private final boolean isNumber(String s) {
        return new Regex("^\\d+$").matches(s);
    }

    private final boolean isAlphaNum(String s) {
        return new Regex("^[0-9A-Z $%*+\\-./:]+$").matches(s);
    }

    public final int getBCHTypeInfo(int data) {
        int i = data << 10;
        int bCHDigit = i;
        while (getBCHDigit(bCHDigit) - getBCHDigit(G15) >= 0) {
            bCHDigit ^= G15 << (getBCHDigit(bCHDigit) - getBCHDigit(G15));
        }
        return (i | bCHDigit) ^ G15_MASK;
    }

    public final int getBCHTypeNumber(int data) {
        int i = data << 12;
        int bCHDigit = i;
        while (getBCHDigit(bCHDigit) - getBCHDigit(G18) >= 0) {
            bCHDigit ^= G18 << (getBCHDigit(bCHDigit) - getBCHDigit(G18));
        }
        return i | bCHDigit;
    }
}
