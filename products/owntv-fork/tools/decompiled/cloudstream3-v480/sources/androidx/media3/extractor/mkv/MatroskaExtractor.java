package androidx.media3.extractor.mkv;

import android.net.Uri;
import android.util.Pair;
import android.util.SparseArray;
import androidx.collection.SieveCacheKt;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.DolbyVisionConfig;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ChunkIndexProvider;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.HevcConfig;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackAwareSeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.metadata.ThumbnailMetadata;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import dev.whyoleg.cryptography.serialization.asn1.internal.DerTagKt;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes4.dex */
public class MatroskaExtractor implements Extractor {
    private static final int BLOCK_ADDITIONAL_ID_VP9_ITU_T_35 = 4;
    private static final int BLOCK_ADD_ID_TYPE_DVCC = 1685480259;
    private static final int BLOCK_ADD_ID_TYPE_DVVC = 1685485123;
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_AV1 = "V_AV1";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_FLOAT = "A_PCM/FLOAT/IEEE";
    private static final String CODEC_ID_PCM_INT_BIG = "A_PCM/INT/BIG";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SSA = "S_TEXT/SSA";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String CODEC_ID_VTT = "S_TEXT/WEBVTT";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 2;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_H263 = 859189832;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_ADDITIONAL = 165;
    private static final int ID_BLOCK_ADDITIONS = 30113;
    private static final int ID_BLOCK_ADDITION_MAPPING = 16868;
    private static final int ID_BLOCK_ADD_ID = 238;
    private static final int ID_BLOCK_ADD_ID_EXTRA_DATA = 16877;
    private static final int ID_BLOCK_ADD_ID_TYPE = 16871;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_BLOCK_MORE = 166;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_BITS_PER_CHANNEL = 21938;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_RELATIVE_POSITION = 240;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK = 247;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISCARD_PADDING = 30114;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_BLOCK_ADDITION_ID = 21998;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_POSE_PITCH = 30324;
    private static final int ID_PROJECTION_POSE_ROLL = 30325;
    private static final int ID_PROJECTION_POSE_YAW = 30323;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_PROJECTION_TYPE = 30321;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int MAX_CHUNKS_TO_SCAN_FOR_THUMBNAIL = 20;
    private static final long MAX_DURATION_US_TO_SCAN_FOR_THUMBNAIL = 10000000;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int VTT_PREFIX_END_TIMECODE_OFFSET = 25;
    private static final String VTT_TIMECODE_FORMAT = "%02d:%02d:%02d.%03d";
    private static final long VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private long blockGroupDiscardPaddingNs;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private long cuesContentPosition;
    private long currentCueClusterPosition;
    private long currentCueRelativePosition;
    private long currentCueTimeUs;
    private int currentCueTrackNumber;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private boolean inCuesElement;
    private boolean isWebm;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final boolean parseSubtitlesDuringExtraction;
    private boolean pendingEndTracks;
    private final SparseArray<List<MatroskaSeekMap.CuePointData>> perTrackCues;
    private int primarySeekTrackNumber;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Deprecated
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: androidx.media3.extractor.mkv.MatroskaExtractor$$ExternalSyntheticLambda1
        @Override // androidx.media3.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return MatroskaExtractor.lambda$static$1();
        }

        @Override // androidx.media3.extractor.ExtractorsFactory
        public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
            return createExtractors();
        }

        @Override // androidx.media3.extractor.ExtractorsFactory
        public /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i) {
            return ExtractorsFactory.CC.$default$experimentalSetCodecsToParseWithinGopSampleDependencies(this, i);
        }

        @Override // androidx.media3.extractor.ExtractorsFactory
        public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
            return ExtractorsFactory.CC.$default$experimentalSetTextTrackTranscodingEnabled(this, z);
        }

        @Override // androidx.media3.extractor.ExtractorsFactory
        public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            return ExtractorsFactory.CC.$default$setSubtitleParserFactory(this, factory);
        }
    };
    private static final byte[] SUBRIP_PREFIX = {49, 10, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 44, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 32, 45, 45, 62, 32, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 44, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 10};
    private static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 44, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 44};
    private static final byte[] VTT_PREFIX = {87, 69, 66, 86, 84, 84, 10, 10, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 46, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 32, 45, 45, 62, 32, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 46, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 10};
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    protected int getElementType(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case ID_TRACK_NUMBER /* 215 */:
            case ID_TIME_CODE /* 231 */:
            case 238:
            case 240:
            case 241:
            case ID_CUE_TRACK /* 247 */:
            case ID_REFERENCE_BLOCK /* 251 */:
            case ID_BLOCK_ADD_ID_TYPE /* 16871 */:
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
            case ID_EBML_READ_VERSION /* 17143 */:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
            case ID_CONTENT_ENCODING_ORDER /* 20529 */:
            case ID_CONTENT_ENCODING_SCOPE /* 20530 */:
            case ID_SEEK_POSITION /* 21420 */:
            case ID_STEREO_MODE /* 21432 */:
            case ID_DISPLAY_WIDTH /* 21680 */:
            case ID_DISPLAY_UNIT /* 21682 */:
            case ID_DISPLAY_HEIGHT /* 21690 */:
            case ID_FLAG_FORCED /* 21930 */:
            case ID_COLOUR_BITS_PER_CHANNEL /* 21938 */:
            case ID_COLOUR_RANGE /* 21945 */:
            case ID_COLOUR_TRANSFER /* 21946 */:
            case ID_COLOUR_PRIMARIES /* 21947 */:
            case ID_MAX_CLL /* 21948 */:
            case ID_MAX_FALL /* 21949 */:
            case ID_MAX_BLOCK_ADDITION_ID /* 21998 */:
            case ID_CODEC_DELAY /* 22186 */:
            case ID_SEEK_PRE_ROLL /* 22203 */:
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
            case ID_DISCARD_PADDING /* 30114 */:
            case ID_PROJECTION_TYPE /* 30321 */:
            case ID_DEFAULT_DURATION /* 2352003 */:
            case ID_TIMECODE_SCALE /* 2807729 */:
                return 2;
            case 134:
            case ID_DOC_TYPE /* 17026 */:
            case ID_NAME /* 21358 */:
            case ID_LANGUAGE /* 2274716 */:
                return 3;
            case 160:
            case 166:
            case 174:
            case 183:
            case 187:
            case 224:
            case ID_AUDIO /* 225 */:
            case ID_BLOCK_ADDITION_MAPPING /* 16868 */:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /* 18407 */:
            case ID_SEEK /* 19899 */:
            case ID_CONTENT_COMPRESSION /* 20532 */:
            case ID_CONTENT_ENCRYPTION /* 20533 */:
            case ID_COLOUR /* 21936 */:
            case ID_MASTERING_METADATA /* 21968 */:
            case ID_CONTENT_ENCODING /* 25152 */:
            case ID_CONTENT_ENCODINGS /* 28032 */:
            case ID_BLOCK_ADDITIONS /* 30113 */:
            case ID_PROJECTION /* 30320 */:
            case ID_SEEK_HEAD /* 290298740 */:
            case 357149030:
            case ID_TRACKS /* 374648427 */:
            case ID_SEGMENT /* 408125543 */:
            case ID_EBML /* 440786851 */:
            case ID_CUES /* 475249515 */:
            case ID_CLUSTER /* 524531317 */:
                return 1;
            case 161:
            case 163:
            case 165:
            case ID_BLOCK_ADD_ID_EXTRA_DATA /* 16877 */:
            case ID_CONTENT_COMPRESSION_SETTINGS /* 16981 */:
            case ID_CONTENT_ENCRYPTION_KEY_ID /* 18402 */:
            case ID_SEEK_ID /* 21419 */:
            case ID_CODEC_PRIVATE /* 25506 */:
            case ID_PROJECTION_PRIVATE /* 30322 */:
                return 4;
            case 181:
            case ID_DURATION /* 17545 */:
            case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
            case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
            case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
            case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
            case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
            case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
            case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
            case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
            case ID_LUMNINANCE_MAX /* 21977 */:
            case ID_LUMNINANCE_MIN /* 21978 */:
            case ID_PROJECTION_POSE_YAW /* 30323 */:
            case ID_PROJECTION_POSE_PITCH /* 30324 */:
            case ID_PROJECTION_POSE_ROLL /* 30325 */:
                return 5;
            default:
                return 0;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public /* synthetic */ List getSniffFailureDetails() {
        return ImmutableList.of();
    }

    @Override // androidx.media3.extractor.Extractor
    public /* synthetic */ Extractor getUnderlyingImplementation() {
        return Extractor.CC.$default$getUnderlyingImplementation(this);
    }

    protected boolean isLevel1Element(int i) {
        return i == 357149030 || i == ID_CLUSTER || i == ID_CUES || i == ID_TRACKS;
    }

    @Override // androidx.media3.extractor.Extractor
    public final void release() {
    }

    static /* synthetic */ Extractor[] lambda$newFactory$0(SubtitleParser.Factory factory) {
        return new Extractor[]{new MatroskaExtractor(factory)};
    }

    public static ExtractorsFactory newFactory(final SubtitleParser.Factory factory) {
        return new ExtractorsFactory() { // from class: androidx.media3.extractor.mkv.MatroskaExtractor$$ExternalSyntheticLambda0
            @Override // androidx.media3.extractor.ExtractorsFactory
            public final Extractor[] createExtractors() {
                return MatroskaExtractor.lambda$newFactory$0(factory);
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
                return createExtractors();
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ ExtractorsFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i) {
                return ExtractorsFactory.CC.$default$experimentalSetCodecsToParseWithinGopSampleDependencies(this, i);
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
                return ExtractorsFactory.CC.$default$experimentalSetTextTrackTranscodingEnabled(this, z);
            }

            @Override // androidx.media3.extractor.ExtractorsFactory
            public /* synthetic */ ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory2) {
                return ExtractorsFactory.CC.$default$setSubtitleParserFactory(this, factory2);
            }
        };
    }

    static {
        HashMap map = new HashMap();
        map.put("htc_video_rotA-000", 0);
        map.put("htc_video_rotA-090", 90);
        map.put("htc_video_rotA-180", 180);
        map.put("htc_video_rotA-270", 270);
        TRACK_NAME_TO_ROTATION_DEGREES = DesugarCollections.unmodifiableMap(map);
    }

    static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new MatroskaExtractor(SubtitleParser.Factory.UNSUPPORTED, 2)};
    }

    @Deprecated
    public MatroskaExtractor() {
        this(new DefaultEbmlReader(), 2, SubtitleParser.Factory.UNSUPPORTED);
    }

    @Deprecated
    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i | 2, SubtitleParser.Factory.UNSUPPORTED);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory) {
        this(new DefaultEbmlReader(), 0, factory);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory, int i) {
        this(new DefaultEbmlReader(), i, factory);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i, SubtitleParser.Factory factory) {
        this.segmentContentPosition = -1L;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.currentCueTimeUs = C.TIME_UNSET;
        this.currentCueTrackNumber = -1;
        this.currentCueClusterPosition = -1L;
        this.currentCueRelativePosition = -1L;
        this.primarySeekTrackNumber = -1;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.subtitleParserFactory = factory;
        this.perTrackCues = new SparseArray<>();
        this.seekForCuesEnabled = (i & 1) == 0;
        this.parseSubtitlesDuringExtraction = (i & 2) == 0;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
        this.pendingEndTracks = true;
    }

    @Override // androidx.media3.extractor.Extractor
    public final boolean sniff(ExtractorInput extractorInput) throws IOException {
        return new Sniffer().sniff(extractorInput);
    }

    @Override // androidx.media3.extractor.Extractor
    public final void init(ExtractorOutput extractorOutput) {
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j, long j2) {
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        this.inCuesElement = false;
        this.currentCueTimeUs = C.TIME_UNSET;
        this.currentCueTrackNumber = -1;
        this.currentCueClusterPosition = -1L;
        this.currentCueRelativePosition = -1L;
        if (!this.sentSeekMap) {
            this.perTrackCues.clear();
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).reset();
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            Track trackValueAt = this.tracks.valueAt(i);
            trackValueAt.assertOutputInitialized();
            trackValueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    protected void startMasterElement(int i, long j, long j2) throws ParserException {
        assertInitialized();
        if (i == 160) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0L;
            return;
        }
        if (i == 174) {
            Track track = new Track();
            this.currentTrack = track;
            track.isWebm = this.isWebm;
            return;
        }
        if (i == 183) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i);
            this.currentCueTrackNumber = -1;
            this.currentCueClusterPosition = -1L;
            this.currentCueRelativePosition = -1L;
            return;
        }
        if (i == 187) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i);
            this.currentCueTimeUs = C.TIME_UNSET;
            return;
        }
        if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1L;
            return;
        }
        if (i == ID_CONTENT_ENCRYPTION) {
            getCurrentTrack(i).hasContentEncryption = true;
            return;
        }
        if (i == ID_MASTERING_METADATA) {
            getCurrentTrack(i).hasColorInfo = true;
            return;
        }
        if (i == ID_SEGMENT) {
            long j3 = this.segmentContentPosition;
            if (j3 != -1 && j3 != j) {
                throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", null);
            }
            this.segmentContentPosition = j;
            this.segmentContentSize = j2;
            return;
        }
        if (i == ID_CUES) {
            if (this.sentSeekMap) {
                return;
            }
            this.inCuesElement = true;
        } else if (i == ID_CLUSTER && !this.sentSeekMap) {
            if (this.seekForCuesEnabled && this.cuesContentPosition != -1) {
                this.seekForCues = true;
            } else {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
            }
        }
    }

    protected void endMasterElement(int i) throws ParserException {
        int i2;
        assertInitialized();
        if (i == 160) {
            if (this.blockState != 2) {
                return;
            }
            Track track = this.tracks.get(this.blockTrackNumber);
            track.assertOutputInitialized();
            if (this.blockGroupDiscardPaddingNs > 0 && CODEC_ID_OPUS.equals(track.codecId)) {
                this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.blockSampleCount; i4++) {
                i3 += this.blockSampleSizes[i4];
            }
            int i5 = 0;
            while (i5 < this.blockSampleCount) {
                long j = this.blockTimeUs + ((long) ((track.defaultSampleDurationNs * i5) / 1000));
                int i6 = this.blockFlags;
                if (i5 == 0 && !this.blockHasReferenceBlock) {
                    i6 |= 1;
                }
                int i7 = this.blockSampleSizes[i5];
                int i8 = i3 - i7;
                commitSampleToOutput(track, j, i6, i7, i8);
                i5++;
                i3 = i8;
            }
            this.blockState = 0;
            return;
        }
        if (i == 174) {
            Track track2 = (Track) Preconditions.checkNotNull(this.currentTrack);
            if (track2.codecId == null) {
                throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", null);
            }
            if (isCodecSupported(track2.codecId)) {
                track2.initializeFormat(track2.number);
                track2.output = this.extractorOutput.track(track2.number, track2.type);
                this.tracks.put(track2.number, track2);
            }
            this.currentTrack = null;
            return;
        }
        if (i == 183) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i);
            if (this.currentCueTimeUs == C.TIME_UNSET || (i2 = this.currentCueTrackNumber) == -1 || this.currentCueClusterPosition == -1) {
                return;
            }
            List<MatroskaSeekMap.CuePointData> arrayList = this.perTrackCues.get(i2);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.perTrackCues.put(this.currentCueTrackNumber, arrayList);
            }
            arrayList.add(new MatroskaSeekMap.CuePointData(this.currentCueTimeUs, this.segmentContentPosition + this.currentCueClusterPosition, this.currentCueRelativePosition));
            return;
        }
        if (i == ID_SEEK) {
            int i9 = this.seekEntryId;
            if (i9 != -1) {
                long j2 = this.seekEntryPosition;
                if (j2 != -1) {
                    if (i9 == ID_CUES) {
                        this.cuesContentPosition = j2;
                        return;
                    }
                    return;
                }
            }
            throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", null);
        }
        if (i == ID_CONTENT_ENCODING) {
            assertInTrackEntry(i);
            if (this.currentTrack.hasContentEncryption) {
                if (this.currentTrack.cryptoData == null) {
                    throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", null);
                }
                this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, "video/webm", this.currentTrack.cryptoData.encryptionKey));
                return;
            }
            return;
        }
        if (i == ID_CONTENT_ENCODINGS) {
            assertInTrackEntry(i);
            if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", null);
            }
            return;
        }
        if (i == 357149030) {
            if (this.timecodeScale == C.TIME_UNSET) {
                this.timecodeScale = 1000000L;
            }
            long j3 = this.durationTimecode;
            if (j3 != C.TIME_UNSET) {
                this.durationUs = scaleTimecodeToUs(j3);
                return;
            }
            return;
        }
        if (i != ID_TRACKS) {
            if (i == ID_CUES && !this.sentSeekMap) {
                int i10 = 0;
                while (true) {
                    if (i10 < this.perTrackCues.size()) {
                        if (!this.perTrackCues.valueAt(i10).isEmpty()) {
                            if (this.durationUs != C.TIME_UNSET) {
                                for (int i11 = 0; i11 < this.perTrackCues.size(); i11++) {
                                    Collections.sort(this.perTrackCues.valueAt(i11));
                                }
                                this.extractorOutput.seekMap(new MatroskaSeekMap(this.perTrackCues, this.durationUs, this.primarySeekTrackNumber, this.segmentContentPosition, this.segmentContentSize));
                                break;
                            }
                            break;
                        }
                        i10++;
                    }
                    this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                    break;
                }
                this.sentSeekMap = true;
                this.inCuesElement = false;
                for (int i12 = 0; i12 < this.tracks.size(); i12++) {
                    Track trackValueAt = this.tracks.valueAt(i12);
                    trackValueAt.maybeAddThumbnailMetadata(this.perTrackCues, this.durationUs, this.segmentContentPosition, this.segmentContentSize);
                    if (!trackValueAt.waitingForDtsAnalysis) {
                        trackValueAt.assertOutputInitialized();
                        trackValueAt.output.format((Format) Preconditions.checkNotNull(trackValueAt.format));
                    }
                }
                maybeEndTracks();
                return;
            }
            return;
        }
        if (this.tracks.size() == 0) {
            throw ParserException.createForMalformedContainer("No valid tracks were found", null);
        }
        boolean z = !this.seekForCuesEnabled || this.cuesContentPosition == -1;
        int i13 = -1;
        int i14 = -1;
        int i15 = -1;
        int i16 = -1;
        for (int i17 = 0; i17 < this.tracks.size(); i17++) {
            Track trackValueAt2 = this.tracks.valueAt(i17);
            int i18 = trackValueAt2.type;
            if (i18 == 2) {
                if (trackValueAt2.flagDefault) {
                    i13 = trackValueAt2.number;
                }
                if (i14 == -1) {
                    i14 = trackValueAt2.number;
                }
            } else if (i18 == 1) {
                if (trackValueAt2.flagDefault) {
                    i15 = trackValueAt2.number;
                }
                if (i16 == -1) {
                    i16 = trackValueAt2.number;
                }
            }
            if (z) {
                trackValueAt2.assertOutputInitialized();
                if (!trackValueAt2.waitingForDtsAnalysis) {
                    trackValueAt2.output.format((Format) Preconditions.checkNotNull(trackValueAt2.format));
                }
            }
        }
        if (i13 != -1) {
            this.primarySeekTrackNumber = i13;
        } else if (i14 != -1) {
            this.primarySeekTrackNumber = i14;
        } else if (i15 != -1) {
            this.primarySeekTrackNumber = i15;
        } else if (i16 != -1) {
            this.primarySeekTrackNumber = i16;
        } else {
            this.primarySeekTrackNumber = this.tracks.size() > 0 ? this.tracks.valueAt(0).number : -1;
        }
        if (z) {
            maybeEndTracks();
        }
    }

    protected void integerElement(int i, long j) throws ParserException {
        if (i == 240) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i);
            if (this.currentCueRelativePosition == -1) {
                this.currentCueRelativePosition = j;
                return;
            }
            return;
        }
        if (i == 241) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(i);
            if (this.currentCueClusterPosition == -1) {
                this.currentCueClusterPosition = j;
                return;
            }
            return;
        }
        if (i == ID_CONTENT_ENCODING_ORDER) {
            if (j == 0) {
                return;
            }
            throw ParserException.createForMalformedContainer("ContentEncodingOrder " + j + " not supported", null);
        }
        if (i == ID_CONTENT_ENCODING_SCOPE) {
            if (j == 1) {
                return;
            }
            throw ParserException.createForMalformedContainer("ContentEncodingScope " + j + " not supported", null);
        }
        switch (i) {
            case 131:
                int i2 = (int) j;
                if (i2 == 1) {
                    getCurrentTrack(i).type = 2;
                    return;
                }
                if (i2 == 2) {
                    getCurrentTrack(i).type = 1;
                    return;
                }
                if (i2 == 17) {
                    getCurrentTrack(i).type = 3;
                    return;
                } else if (i2 == 33) {
                    getCurrentTrack(i).type = 5;
                    return;
                } else {
                    getCurrentTrack(i).type = -1;
                    return;
                }
            case 136:
                getCurrentTrack(i).flagDefault = j == 1;
                return;
            case 155:
                this.blockDurationUs = scaleTimecodeToUs(j);
                return;
            case 159:
                getCurrentTrack(i).channelCount = (int) j;
                return;
            case 176:
                getCurrentTrack(i).width = (int) j;
                return;
            case 179:
                if (this.sentSeekMap) {
                    return;
                }
                assertInCues(i);
                this.currentCueTimeUs = scaleTimecodeToUs(j);
                return;
            case 186:
                getCurrentTrack(i).height = (int) j;
                return;
            case ID_TRACK_NUMBER /* 215 */:
                getCurrentTrack(i).number = (int) j;
                return;
            case ID_TIME_CODE /* 231 */:
                this.clusterTimecodeUs = scaleTimecodeToUs(j);
                return;
            case 238:
                this.blockAdditionalId = (int) j;
                return;
            case ID_CUE_TRACK /* 247 */:
                if (this.sentSeekMap) {
                    return;
                }
                assertInCues(i);
                this.currentCueTrackNumber = (int) j;
                return;
            case ID_REFERENCE_BLOCK /* 251 */:
                this.blockHasReferenceBlock = true;
                return;
            case ID_BLOCK_ADD_ID_TYPE /* 16871 */:
                getCurrentTrack(i).blockAddIdType = (int) j;
                return;
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
                if (j == 3) {
                    return;
                }
                throw ParserException.createForMalformedContainer("ContentCompAlgo " + j + " not supported", null);
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
                if (j < 1 || j > 2) {
                    throw ParserException.createForMalformedContainer("DocTypeReadVersion " + j + " not supported", null);
                }
                return;
            case ID_EBML_READ_VERSION /* 17143 */:
                if (j == 1) {
                    return;
                }
                throw ParserException.createForMalformedContainer("EBMLReadVersion " + j + " not supported", null);
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
                if (j == 5) {
                    return;
                }
                throw ParserException.createForMalformedContainer("ContentEncAlgo " + j + " not supported", null);
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
                if (j == 1) {
                    return;
                }
                throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + j + " not supported", null);
            case ID_SEEK_POSITION /* 21420 */:
                this.seekEntryPosition = j + this.segmentContentPosition;
                return;
            case ID_STEREO_MODE /* 21432 */:
                int i3 = (int) j;
                assertInTrackEntry(i);
                if (i3 == 0) {
                    this.currentTrack.stereoMode = 0;
                    return;
                }
                if (i3 == 1) {
                    this.currentTrack.stereoMode = 2;
                    return;
                } else if (i3 == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else {
                    if (i3 != 15) {
                        return;
                    }
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case ID_DISPLAY_WIDTH /* 21680 */:
                getCurrentTrack(i).displayWidth = (int) j;
                return;
            case ID_DISPLAY_UNIT /* 21682 */:
                getCurrentTrack(i).displayUnit = (int) j;
                return;
            case ID_DISPLAY_HEIGHT /* 21690 */:
                getCurrentTrack(i).displayHeight = (int) j;
                return;
            case ID_FLAG_FORCED /* 21930 */:
                getCurrentTrack(i).flagForced = j == 1;
                return;
            case ID_COLOUR_BITS_PER_CHANNEL /* 21938 */:
                assertInTrackEntry(i);
                this.currentTrack.hasColorInfo = true;
                this.currentTrack.bitsPerChannel = (int) j;
                return;
            case ID_MAX_BLOCK_ADDITION_ID /* 21998 */:
                getCurrentTrack(i).maxBlockAdditionId = (int) j;
                return;
            case ID_CODEC_DELAY /* 22186 */:
                getCurrentTrack(i).codecDelayNs = j;
                return;
            case ID_SEEK_PRE_ROLL /* 22203 */:
                getCurrentTrack(i).seekPreRollNs = j;
                return;
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
                getCurrentTrack(i).audioBitDepth = (int) j;
                return;
            case ID_DISCARD_PADDING /* 30114 */:
                this.blockGroupDiscardPaddingNs = j;
                return;
            case ID_PROJECTION_TYPE /* 30321 */:
                assertInTrackEntry(i);
                int i4 = (int) j;
                if (i4 == 0) {
                    this.currentTrack.projectionType = 0;
                    return;
                }
                if (i4 == 1) {
                    this.currentTrack.projectionType = 1;
                    return;
                } else if (i4 == 2) {
                    this.currentTrack.projectionType = 2;
                    return;
                } else {
                    if (i4 != 3) {
                        return;
                    }
                    this.currentTrack.projectionType = 3;
                    return;
                }
            case ID_DEFAULT_DURATION /* 2352003 */:
                getCurrentTrack(i).defaultSampleDurationNs = (int) j;
                return;
            case ID_TIMECODE_SCALE /* 2807729 */:
                this.timecodeScale = j;
                return;
            default:
                switch (i) {
                    case ID_COLOUR_RANGE /* 21945 */:
                        assertInTrackEntry(i);
                        int i5 = (int) j;
                        if (i5 == 1) {
                            this.currentTrack.colorRange = 2;
                            return;
                        } else {
                            if (i5 != 2) {
                                return;
                            }
                            this.currentTrack.colorRange = 1;
                            return;
                        }
                    case ID_COLOUR_TRANSFER /* 21946 */:
                        assertInTrackEntry(i);
                        int iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) j);
                        if (iIsoTransferCharacteristicsToColorTransfer != -1) {
                            this.currentTrack.colorTransfer = iIsoTransferCharacteristicsToColorTransfer;
                            return;
                        }
                        return;
                    case ID_COLOUR_PRIMARIES /* 21947 */:
                        assertInTrackEntry(i);
                        this.currentTrack.hasColorInfo = true;
                        int iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) j);
                        if (iIsoColorPrimariesToColorSpace != -1) {
                            this.currentTrack.colorSpace = iIsoColorPrimariesToColorSpace;
                            return;
                        }
                        return;
                    case ID_MAX_CLL /* 21948 */:
                        getCurrentTrack(i).maxContentLuminance = (int) j;
                        return;
                    case ID_MAX_FALL /* 21949 */:
                        getCurrentTrack(i).maxFrameAverageLuminance = (int) j;
                        return;
                    default:
                        return;
                }
        }
    }

    protected void floatElement(int i, double d) throws ParserException {
        if (i == 181) {
            getCurrentTrack(i).sampleRate = (int) d;
            return;
        }
        if (i == ID_DURATION) {
            this.durationTimecode = (long) d;
            return;
        }
        switch (i) {
            case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
                getCurrentTrack(i).primaryRChromaticityX = (float) d;
                break;
            case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
                getCurrentTrack(i).primaryRChromaticityY = (float) d;
                break;
            case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
                getCurrentTrack(i).primaryGChromaticityX = (float) d;
                break;
            case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
                getCurrentTrack(i).primaryGChromaticityY = (float) d;
                break;
            case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
                getCurrentTrack(i).primaryBChromaticityX = (float) d;
                break;
            case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
                getCurrentTrack(i).primaryBChromaticityY = (float) d;
                break;
            case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
                getCurrentTrack(i).whitePointChromaticityX = (float) d;
                break;
            case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
                getCurrentTrack(i).whitePointChromaticityY = (float) d;
                break;
            case ID_LUMNINANCE_MAX /* 21977 */:
                getCurrentTrack(i).maxMasteringLuminance = (float) d;
                break;
            case ID_LUMNINANCE_MIN /* 21978 */:
                getCurrentTrack(i).minMasteringLuminance = (float) d;
                break;
            default:
                switch (i) {
                    case ID_PROJECTION_POSE_YAW /* 30323 */:
                        getCurrentTrack(i).projectionPoseYaw = (float) d;
                        break;
                    case ID_PROJECTION_POSE_PITCH /* 30324 */:
                        getCurrentTrack(i).projectionPosePitch = (float) d;
                        break;
                    case ID_PROJECTION_POSE_ROLL /* 30325 */:
                        getCurrentTrack(i).projectionPoseRoll = (float) d;
                        break;
                }
                break;
        }
    }

    protected void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            getCurrentTrack(i).codecId = str;
            return;
        }
        if (i != ID_DOC_TYPE) {
            if (i == ID_NAME) {
                getCurrentTrack(i).name = str;
                return;
            } else {
                if (i != ID_LANGUAGE) {
                    return;
                }
                getCurrentTrack(i).language = str;
                return;
            }
        }
        if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            throw ParserException.createForMalformedContainer("DocType " + str + " not supported", null);
        }
        this.isWebm = Objects.equals(str, DOC_TYPE_WEBM);
    }

    /* JADX WARN: Code duplicated, block: B:106:0x02a0  */
    protected void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        long j;
        int i8;
        int[] iArr;
        int i9;
        int i10;
        int i11;
        int i12 = 2;
        int i13 = 0;
        int i14 = 1;
        if (i != 161 && i != 163) {
            if (i == 165) {
                if (this.blockState != 2) {
                    return;
                }
                handleBlockAdditionalData(this.tracks.get(this.blockTrackNumber), this.blockAdditionalId, extractorInput, i2);
                return;
            }
            if (i == ID_BLOCK_ADD_ID_EXTRA_DATA) {
                handleBlockAddIDExtraData(getCurrentTrack(i), extractorInput, i2);
                return;
            }
            if (i == ID_CONTENT_COMPRESSION_SETTINGS) {
                assertInTrackEntry(i);
                this.currentTrack.sampleStrippedBytes = new byte[i2];
                extractorInput.readFully(this.currentTrack.sampleStrippedBytes, 0, i2);
                return;
            }
            if (i == ID_CONTENT_ENCRYPTION_KEY_ID) {
                byte[] bArr = new byte[i2];
                extractorInput.readFully(bArr, 0, i2);
                getCurrentTrack(i).cryptoData = new TrackOutput.CryptoData(1, bArr, 0, 0);
                return;
            }
            if (i == ID_SEEK_ID) {
                Arrays.fill(this.seekEntryIdBytes.getData(), (byte) 0);
                extractorInput.readFully(this.seekEntryIdBytes.getData(), 4 - i2, i2);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
                return;
            }
            if (i == ID_CODEC_PRIVATE) {
                assertInTrackEntry(i);
                this.currentTrack.codecPrivate = new byte[i2];
                extractorInput.readFully(this.currentTrack.codecPrivate, 0, i2);
                return;
            }
            if (i == ID_PROJECTION_PRIVATE) {
                assertInTrackEntry(i);
                this.currentTrack.projectionData = new byte[i2];
                extractorInput.readFully(this.currentTrack.projectionData, 0, i2);
                return;
            }
            throw ParserException.createForMalformedContainer("Unexpected id: " + i, null);
        }
        int i15 = 8;
        if (this.blockState == 0) {
            this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
            this.blockTrackNumberLength = this.varintReader.getLastLength();
            this.blockDurationUs = C.TIME_UNSET;
            this.blockState = 1;
            this.scratch.reset(0);
        }
        Track track = this.tracks.get(this.blockTrackNumber);
        if (track == null) {
            extractorInput.skipFully(i2 - this.blockTrackNumberLength);
            this.blockState = 0;
            return;
        }
        track.assertOutputInitialized();
        if (this.blockState == 1) {
            readScratch(extractorInput, 3);
            int i16 = (this.scratch.getData()[2] & 6) >> 1;
            if (i16 == 0) {
                this.blockSampleCount = 1;
                int[] iArrEnsureArrayCapacity = ensureArrayCapacity(this.blockSampleSizes, 1);
                this.blockSampleSizes = iArrEnsureArrayCapacity;
                iArrEnsureArrayCapacity[0] = (i2 - this.blockTrackNumberLength) - 3;
            } else {
                readScratch(extractorInput, 4);
                int i17 = (this.scratch.getData()[3] & 255) + 1;
                this.blockSampleCount = i17;
                int[] iArrEnsureArrayCapacity2 = ensureArrayCapacity(this.blockSampleSizes, i17);
                this.blockSampleSizes = iArrEnsureArrayCapacity2;
                if (i16 == 2) {
                    int i18 = (i2 - this.blockTrackNumberLength) - 4;
                    int i19 = this.blockSampleCount;
                    Arrays.fill(iArrEnsureArrayCapacity2, 0, i19, i18 / i19);
                } else if (i16 == 1) {
                    int i20 = 0;
                    int i21 = 0;
                    int i22 = 4;
                    while (true) {
                        i8 = this.blockSampleCount;
                        int i23 = i8 - 1;
                        i4 = i13;
                        iArr = this.blockSampleSizes;
                        if (i20 >= i23) {
                            break;
                        }
                        iArr[i20] = i4;
                        while (true) {
                            i9 = i22 + 1;
                            readScratch(extractorInput, i9);
                            int i24 = this.scratch.getData()[i22] & 255;
                            int[] iArr2 = this.blockSampleSizes;
                            i10 = iArr2[i20] + i24;
                            iArr2[i20] = i10;
                            if (i24 != 255) {
                                break;
                            } else {
                                i22 = i9;
                            }
                        }
                        i21 += i10;
                        i20++;
                        i22 = i9;
                        i13 = i4;
                    }
                    iArr[i8 - 1] = ((i2 - this.blockTrackNumberLength) - i22) - i21;
                    i5 = 1;
                } else {
                    i4 = 0;
                    if (i16 != 3) {
                        throw ParserException.createForMalformedContainer("Unexpected lacing value: " + i16, null);
                    }
                    int i25 = 4;
                    int i26 = 0;
                    int i27 = 0;
                    while (true) {
                        int i28 = this.blockSampleCount;
                        int i29 = i28 - 1;
                        i12 = i12;
                        int[] iArr3 = this.blockSampleSizes;
                        if (i26 < i29) {
                            iArr3[i26] = 0;
                            int i30 = i25 + 1;
                            readScratch(extractorInput, i30);
                            if (this.scratch.getData()[i25] == 0) {
                                throw ParserException.createForMalformedContainer("No valid varint length mask found", null);
                            }
                            int i31 = 0;
                            while (true) {
                                if (i31 >= i15) {
                                    i6 = i14;
                                    i7 = i15;
                                    j = 0;
                                    break;
                                }
                                int i32 = i14 << (7 - i31);
                                i7 = i15;
                                if ((this.scratch.getData()[i25] & i32) != 0) {
                                    i30 += i31;
                                    readScratch(extractorInput, i30);
                                    int i33 = i25 + 1;
                                    j = this.scratch.getData()[i25] & 255 & (~i32);
                                    int i34 = i33;
                                    while (i34 < i30) {
                                        j = (j << i7) | ((long) (this.scratch.getData()[i34] & 255));
                                        i34++;
                                        i14 = i14;
                                    }
                                    i6 = i14;
                                    if (i26 <= 0) {
                                        break;
                                    }
                                    j -= (1 << ((i31 * 7) + 6)) - 1;
                                    break;
                                }
                                i31++;
                                i15 = i7;
                            }
                            if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                throw ParserException.createForMalformedContainer("EBML lacing sample size out of range.", null);
                            }
                            int i35 = (int) j;
                            int[] iArr4 = this.blockSampleSizes;
                            if (i26 != 0) {
                                i35 += iArr4[i26 - 1];
                            }
                            iArr4[i26] = i35;
                            i27 += i35;
                            i26++;
                            i25 = i30;
                            i12 = i12;
                            i15 = i7;
                            i14 = i6;
                        } else {
                            i5 = i14;
                            iArr3[i28 - 1] = ((i2 - this.blockTrackNumberLength) - i25) - i27;
                            break;
                        }
                    }
                }
                this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((this.scratch.getData()[i4] << 8) | (this.scratch.getData()[i5] & 255));
                if (track.type != i5 || (i == 163 && (this.scratch.getData()[i12] & 128) == 128)) {
                    i11 = 1;
                } else {
                    i11 = i4;
                }
                this.blockFlags = i11;
                this.blockState = i12;
                this.blockSampleIndex = i4;
                i3 = 163;
            }
            i4 = 0;
            i5 = 1;
            this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((this.scratch.getData()[i4] << 8) | (this.scratch.getData()[i5] & 255));
            if (track.type != i5) {
                i11 = 1;
            } else {
                i11 = 1;
            }
            this.blockFlags = i11;
            this.blockState = i12;
            this.blockSampleIndex = i4;
            i3 = 163;
        } else {
            i3 = 163;
        }
        if (i == i3) {
            while (true) {
                int i36 = this.blockSampleIndex;
                if (i36 < this.blockSampleCount) {
                    int iWriteSampleData = writeSampleData(extractorInput, track, this.blockSampleSizes[i36], false);
                    Track track2 = track;
                    commitSampleToOutput(track2, this.blockTimeUs + ((long) ((this.blockSampleIndex * track.defaultSampleDurationNs) / 1000)), this.blockFlags, iWriteSampleData, 0);
                    this.blockSampleIndex++;
                    track = track2;
                } else {
                    this.blockState = 0;
                    return;
                }
            }
        } else {
            while (true) {
                int i37 = this.blockSampleIndex;
                if (i37 >= this.blockSampleCount) {
                    return;
                }
                int[] iArr5 = this.blockSampleSizes;
                iArr5[i37] = writeSampleData(extractorInput, track, iArr5[i37], true);
                this.blockSampleIndex++;
            }
        }
    }

    protected void handleBlockAddIDExtraData(Track track, ExtractorInput extractorInput, int i) throws IOException {
        if (track.blockAddIdType == 1685485123 || track.blockAddIdType == 1685480259) {
            track.dolbyVisionConfigBytes = new byte[i];
            extractorInput.readFully(track.dolbyVisionConfigBytes, 0, i);
        } else {
            extractorInput.skipFully(i);
        }
    }

    protected void handleBlockAdditionalData(Track track, int i, ExtractorInput extractorInput, int i2) throws IOException {
        if (i == 4 && CODEC_ID_VP9.equals(track.codecId)) {
            this.supplementalData.reset(i2);
            extractorInput.readFully(this.supplementalData.getData(), 0, i2);
        } else {
            extractorInput.skipFully(i2);
        }
    }

    @EnsuresNonNull({"currentTrack"})
    private void assertInTrackEntry(int i) throws ParserException {
        if (this.currentTrack != null) {
            return;
        }
        throw ParserException.createForMalformedContainer("Element " + i + " must be in a TrackEntry", null);
    }

    private void assertInCues(int i) throws ParserException {
        if (this.inCuesElement) {
            return;
        }
        throw ParserException.createForMalformedContainer("Element " + i + " must be in a Cues", null);
    }

    protected Track getCurrentTrack(int i) throws ParserException {
        assertInTrackEntry(i);
        return this.currentTrack;
    }

    @RequiresNonNull({"#1.output"})
    private void commitSampleToOutput(Track track, long j, int i, int i2, int i3) {
        int iLimit;
        if (track.trueHdSampleRechunker != null) {
            track.trueHdSampleRechunker.sampleMetadata(track.output, j, i, i2, i3, track.cryptoData);
        } else {
            if (CODEC_ID_SUBRIP.equals(track.codecId) || CODEC_ID_ASS.equals(track.codecId) || CODEC_ID_SSA.equals(track.codecId) || CODEC_ID_VTT.equals(track.codecId)) {
                if (this.blockSampleCount > 1) {
                    Log.w(TAG, "Skipping subtitle sample in laced block.");
                } else if (this.blockDurationUs == C.TIME_UNSET) {
                    Log.w(TAG, "Skipping subtitle sample with no duration.");
                } else {
                    setSubtitleEndTime(track.codecId, this.blockDurationUs, this.subtitleSample.getData());
                    for (int position = this.subtitleSample.getPosition(); position < this.subtitleSample.limit(); position++) {
                        if (this.subtitleSample.getData()[position] == 0) {
                            this.subtitleSample.setLimit(position);
                            break;
                        }
                    }
                    TrackOutput trackOutput = track.output;
                    ParsableByteArray parsableByteArray = this.subtitleSample;
                    trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
                    iLimit = i2 + this.subtitleSample.limit();
                }
                iLimit = i2;
            } else {
                iLimit = i2;
            }
            if ((i & 268435456) != 0) {
                int i4 = this.blockSampleCount;
                ParsableByteArray parsableByteArray2 = this.supplementalData;
                if (i4 > 1) {
                    parsableByteArray2.reset(0);
                } else {
                    int iLimit2 = parsableByteArray2.limit();
                    track.output.sampleData(this.supplementalData, iLimit2, 2);
                    iLimit += iLimit2;
                }
            }
            track.output.sampleMetadata(j, i, iLimit, i3, track.cryptoData);
        }
        this.haveOutputSample = true;
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException {
        if (this.scratch.limit() >= i) {
            return;
        }
        if (this.scratch.capacity() < i) {
            ParsableByteArray parsableByteArray = this.scratch;
            parsableByteArray.ensureCapacity(Math.max(parsableByteArray.capacity() * 2, i));
        }
        extractorInput.readFully(this.scratch.getData(), this.scratch.limit(), i - this.scratch.limit());
        this.scratch.setLimit(i);
    }

    @RequiresNonNull({"#2.output"})
    private int writeSampleData(ExtractorInput extractorInput, Track track, int i, boolean z) throws IOException {
        int i2;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
            return finishWriteSampleData();
        }
        if (CODEC_ID_ASS.equals(track.codecId) || CODEC_ID_SSA.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
            return finishWriteSampleData();
        }
        if (CODEC_ID_VTT.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, VTT_PREFIX, i);
            return finishWriteSampleData();
        }
        if (track.waitingForDtsAnalysis) {
            Preconditions.checkNotNull(track.format);
            if (DtsUtil.isSampleDtsHd(extractorInput, i)) {
                track.format = track.format.buildUpon().setSampleMimeType("audio/vnd.dts.hd").build();
            }
            track.output.format(track.format);
            track.waitingForDtsAnalysis = false;
            maybeEndTracks();
        }
        TrackOutput trackOutput = track.output;
        if (!this.sampleEncodingHandled) {
            if (track.hasContentEncryption) {
                this.blockFlags &= -1073741825;
                if (!this.sampleSignalByteRead) {
                    extractorInput.readFully(this.scratch.getData(), 0, 1);
                    this.sampleBytesRead++;
                    if ((this.scratch.getData()[0] & 128) == 128) {
                        throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", null);
                    }
                    this.sampleSignalByte = this.scratch.getData()[0];
                    this.sampleSignalByteRead = true;
                }
                byte b = this.sampleSignalByte;
                if ((b & 1) == 1) {
                    boolean z2 = (b & 2) == 2;
                    this.blockFlags |= 1073741824;
                    if (!this.sampleInitializationVectorRead) {
                        extractorInput.readFully(this.encryptionInitializationVector.getData(), 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        this.scratch.getData()[0] = (byte) ((z2 ? 128 : 0) | 8);
                        this.scratch.setPosition(0);
                        trackOutput.sampleData(this.scratch, 1, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        trackOutput.sampleData(this.encryptionInitializationVector, 8, 1);
                        this.sampleBytesWritten += 8;
                    }
                    if (z2) {
                        if (!this.samplePartitionCountRead) {
                            extractorInput.readFully(this.scratch.getData(), 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int i3 = this.samplePartitionCount * 4;
                        this.scratch.reset(i3);
                        extractorInput.readFully(this.scratch.getData(), 0, i3);
                        this.sampleBytesRead += i3;
                        short s = (short) ((this.samplePartitionCount / 2) + 1);
                        int i4 = (s * 6) + 2;
                        ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                        if (byteBuffer == null || byteBuffer.capacity() < i4) {
                            this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i4);
                        }
                        this.encryptionSubsampleDataBuffer.position(0);
                        this.encryptionSubsampleDataBuffer.putShort(s);
                        int i5 = 0;
                        int i6 = 0;
                        while (true) {
                            i2 = this.samplePartitionCount;
                            if (i5 >= i2) {
                                break;
                            }
                            int unsignedIntToInt = this.scratch.readUnsignedIntToInt();
                            int i7 = i5 % 2;
                            ByteBuffer byteBuffer2 = this.encryptionSubsampleDataBuffer;
                            if (i7 == 0) {
                                byteBuffer2.putShort((short) (unsignedIntToInt - i6));
                            } else {
                                byteBuffer2.putInt(unsignedIntToInt - i6);
                            }
                            i5++;
                            i6 = unsignedIntToInt;
                        }
                        int i8 = (i - this.sampleBytesRead) - i6;
                        int i9 = i2 % 2;
                        ByteBuffer byteBuffer3 = this.encryptionSubsampleDataBuffer;
                        if (i9 == 1) {
                            byteBuffer3.putInt(i8);
                        } else {
                            byteBuffer3.putShort((short) i8);
                            this.encryptionSubsampleDataBuffer.putInt(0);
                        }
                        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i4);
                        trackOutput.sampleData(this.encryptionSubsampleData, i4, 1);
                        this.sampleBytesWritten += i4;
                    }
                }
            } else if (track.sampleStrippedBytes != null) {
                this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
            }
            if (track.samplesHaveSupplementalData(z)) {
                this.blockFlags |= 268435456;
                this.supplementalData.reset(0);
                int iLimit = (this.sampleStrippedBytes.limit() + i) - this.sampleBytesRead;
                this.scratch.reset(4);
                this.scratch.getData()[0] = (byte) ((iLimit >> 24) & 255);
                this.scratch.getData()[1] = (byte) ((iLimit >> 16) & 255);
                this.scratch.getData()[2] = (byte) ((iLimit >> 8) & 255);
                this.scratch.getData()[3] = (byte) (iLimit & 255);
                trackOutput.sampleData(this.scratch, 4, 2);
                this.sampleBytesWritten += 4;
            }
            this.sampleEncodingHandled = true;
        }
        int iLimit2 = i + this.sampleStrippedBytes.limit();
        if (CODEC_ID_H264.equals(track.codecId) || CODEC_ID_H265.equals(track.codecId)) {
            byte[] data = this.nalLength.getData();
            data[0] = 0;
            data[1] = 0;
            data[2] = 0;
            int i10 = track.nalUnitLengthFieldLength;
            int i11 = 4 - track.nalUnitLengthFieldLength;
            while (this.sampleBytesRead < iLimit2) {
                int i12 = this.sampleCurrentNalBytesRemaining;
                if (i12 == 0) {
                    writeToTarget(extractorInput, data, i11, i10);
                    this.sampleBytesRead += i10;
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                } else {
                    int iWriteToOutput = writeToOutput(extractorInput, trackOutput, i12);
                    this.sampleBytesRead += iWriteToOutput;
                    this.sampleBytesWritten += iWriteToOutput;
                    this.sampleCurrentNalBytesRemaining -= iWriteToOutput;
                }
            }
        } else {
            if (track.trueHdSampleRechunker != null) {
                Preconditions.checkState(this.sampleStrippedBytes.limit() == 0);
                track.trueHdSampleRechunker.startSample(extractorInput);
            }
            while (true) {
                int i13 = this.sampleBytesRead;
                if (i13 >= iLimit2) {
                    break;
                }
                int iWriteToOutput2 = writeToOutput(extractorInput, trackOutput, iLimit2 - i13);
                this.sampleBytesRead += iWriteToOutput2;
                this.sampleBytesWritten += iWriteToOutput2;
            }
        }
        if (CODEC_ID_VORBIS.equals(track.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            trackOutput.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
        return finishWriteSampleData();
    }

    private int finishWriteSampleData() {
        int i = this.sampleBytesWritten;
        resetWriteSampleData();
        return i;
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = (byte) 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException {
        int length = bArr.length + i;
        int iCapacity = this.subtitleSample.capacity();
        ParsableByteArray parsableByteArray = this.subtitleSample;
        if (iCapacity < length) {
            parsableByteArray.reset(Arrays.copyOf(bArr, length + i));
        } else {
            System.arraycopy(bArr, 0, parsableByteArray.getData(), 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.getData(), bArr.length, i);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    private static void setSubtitleEndTime(String str, long j, byte[] bArr) {
        byte[] subtitleTimecode;
        int i;
        str.hashCode();
        switch (str) {
            case "S_TEXT/ASS":
            case "S_TEXT/SSA":
                subtitleTimecode = formatSubtitleTimecode(j, SSA_TIMECODE_FORMAT, 10000L);
                i = 21;
                break;
            case "S_TEXT/WEBVTT":
                subtitleTimecode = formatSubtitleTimecode(j, VTT_TIMECODE_FORMAT, 1000L);
                i = 25;
                break;
            case "S_TEXT/UTF8":
                subtitleTimecode = formatSubtitleTimecode(j, SUBRIP_TIMECODE_FORMAT, 1000L);
                i = 19;
                break;
            default:
                throw new IllegalArgumentException();
        }
        System.arraycopy(subtitleTimecode, 0, bArr, i, subtitleTimecode.length);
    }

    private static byte[] formatSubtitleTimecode(long j, String str, long j2) {
        Preconditions.checkArgument(j != C.TIME_UNSET);
        int i = (int) (j / 3600000000L);
        long j3 = j - (((long) i) * 3600000000L);
        int i2 = (int) (j3 / 60000000);
        long j4 = j3 - (((long) i2) * 60000000);
        int i3 = (int) (j4 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j4 - (((long) i3) * 1000000)) / j2))));
    }

    private void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException {
        int iMin = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + iMin, i2 - iMin);
        if (iMin > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, iMin);
        }
    }

    private int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException {
        int iBytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (iBytesLeft > 0) {
            int iMin = Math.min(i, iBytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, iMin);
            return iMin;
        }
        return trackOutput.sampleData((DataReader) extractorInput, i, false);
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1L;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 == C.TIME_UNSET) {
            throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", null);
        }
        return Util.scaleLargeTimestamp(j, j2, 1000L);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static boolean isCodecSupported(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2095576542:
                if (str.equals(CODEC_ID_MPEG4_AP)) {
                    b = 0;
                }
                break;
            case -2095575984:
                if (str.equals(CODEC_ID_MPEG4_SP)) {
                    b = 1;
                }
                break;
            case -1985379776:
                if (str.equals(CODEC_ID_ACM)) {
                    b = 2;
                }
                break;
            case -1784763192:
                if (str.equals(CODEC_ID_TRUEHD)) {
                    b = 3;
                }
                break;
            case -1730367663:
                if (str.equals(CODEC_ID_VORBIS)) {
                    b = 4;
                }
                break;
            case -1482641358:
                if (str.equals(CODEC_ID_MP2)) {
                    b = 5;
                }
                break;
            case -1482641357:
                if (str.equals(CODEC_ID_MP3)) {
                    b = 6;
                }
                break;
            case -1373388978:
                if (str.equals(CODEC_ID_FOURCC)) {
                    b = 7;
                }
                break;
            case -933872740:
                if (str.equals(CODEC_ID_DVBSUB)) {
                    b = 8;
                }
                break;
            case -538363189:
                if (str.equals(CODEC_ID_MPEG4_ASP)) {
                    b = 9;
                }
                break;
            case -538363109:
                if (str.equals(CODEC_ID_H264)) {
                    b = 10;
                }
                break;
            case -425012669:
                if (str.equals(CODEC_ID_VOBSUB)) {
                    b = 11;
                }
                break;
            case -356037306:
                if (str.equals(CODEC_ID_DTS_LOSSLESS)) {
                    b = Ascii.FF;
                }
                break;
            case 62923557:
                if (str.equals(CODEC_ID_AAC)) {
                    b = Ascii.CR;
                }
                break;
            case 62923603:
                if (str.equals(CODEC_ID_AC3)) {
                    b = Ascii.SO;
                }
                break;
            case 62927045:
                if (str.equals(CODEC_ID_DTS)) {
                    b = Ascii.SI;
                }
                break;
            case 82318131:
                if (str.equals(CODEC_ID_AV1)) {
                    b = Ascii.DLE;
                }
                break;
            case 82338133:
                if (str.equals(CODEC_ID_VP8)) {
                    b = 17;
                }
                break;
            case 82338134:
                if (str.equals(CODEC_ID_VP9)) {
                    b = Ascii.DC2;
                }
                break;
            case 99146302:
                if (str.equals(CODEC_ID_PGS)) {
                    b = 19;
                }
                break;
            case 444813526:
                if (str.equals(CODEC_ID_THEORA)) {
                    b = Ascii.DC4;
                }
                break;
            case 542569478:
                if (str.equals(CODEC_ID_DTS_EXPRESS)) {
                    b = Ascii.NAK;
                }
                break;
            case 635596514:
                if (str.equals(CODEC_ID_PCM_FLOAT)) {
                    b = Ascii.SYN;
                }
                break;
            case 725948237:
                if (str.equals(CODEC_ID_PCM_INT_BIG)) {
                    b = Ascii.ETB;
                }
                break;
            case 725957860:
                if (str.equals(CODEC_ID_PCM_INT_LIT)) {
                    b = Ascii.CAN;
                }
                break;
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    b = Ascii.EM;
                }
                break;
            case 738614379:
                if (str.equals(CODEC_ID_SSA)) {
                    b = Ascii.SUB;
                }
                break;
            case 855502857:
                if (str.equals(CODEC_ID_H265)) {
                    b = Ascii.ESC;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    b = Ascii.FS;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    b = Ascii.GS;
                }
                break;
            case 1809237540:
                if (str.equals(CODEC_ID_MPEG2)) {
                    b = Ascii.RS;
                }
                break;
            case 1950749482:
                if (str.equals(CODEC_ID_E_AC3)) {
                    b = Ascii.US;
                }
                break;
            case 1950789798:
                if (str.equals(CODEC_ID_FLAC)) {
                    b = 32;
                }
                break;
            case 1951062397:
                if (str.equals(CODEC_ID_OPUS)) {
                    b = 33;
                }
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
                return true;
            default:
                return false;
        }
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        return iArr.length >= i ? iArr : new int[Math.max(iArr.length * 2, i)];
    }

    @EnsuresNonNull({"extractorOutput"})
    private void assertInitialized() {
        Preconditions.checkNotNull(this.extractorOutput);
    }

    private void maybeEndTracks() {
        if (this.pendingEndTracks) {
            for (int i = 0; i < this.tracks.size(); i++) {
                if (this.tracks.valueAt(i).waitingForDtsAnalysis) {
                    return;
                }
            }
            ((ExtractorOutput) Preconditions.checkNotNull(this.extractorOutput)).endTracks();
            this.pendingEndTracks = false;
        }
    }

    private final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    protected static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        private int blockAddIdType;
        public String codecId;
        public byte[] codecPrivate;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagForced;
        public Format format;
        public boolean hasContentEncryption;
        public boolean isWebm;
        public int maxBlockAdditionId;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public byte[] sampleStrippedBytes;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public int width = -1;
        public int height = -1;
        public int bitsPerChannel = -1;
        public int displayWidth = -1;
        public int displayHeight = -1;
        public int displayUnit = 0;
        public int projectionType = -1;
        public float projectionPoseYaw = 0.0f;
        public float projectionPosePitch = 0.0f;
        public float projectionPoseRoll = 0.0f;
        public byte[] projectionData = null;
        public int stereoMode = -1;
        public boolean hasColorInfo = false;
        public int colorSpace = -1;
        public int colorTransfer = -1;
        public int colorRange = -1;
        public int maxContentLuminance = 1000;
        public int maxFrameAverageLuminance = 200;
        public float primaryRChromaticityX = -1.0f;
        public float primaryRChromaticityY = -1.0f;
        public float primaryGChromaticityX = -1.0f;
        public float primaryGChromaticityY = -1.0f;
        public float primaryBChromaticityX = -1.0f;
        public float primaryBChromaticityY = -1.0f;
        public float whitePointChromaticityX = -1.0f;
        public float whitePointChromaticityY = -1.0f;
        public float maxMasteringLuminance = -1.0f;
        public float minMasteringLuminance = -1.0f;
        public int channelCount = 1;
        public int audioBitDepth = -1;
        public int sampleRate = 8000;
        public long codecDelayNs = 0;
        public long seekPreRollNs = 0;
        public boolean waitingForDtsAnalysis = false;
        public boolean flagDefault = true;
        private String language = "eng";

        protected Track() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:222:0x0438  */
        /* JADX WARN: Code duplicated, block: B:227:0x0452  */
        /* JADX WARN: Code duplicated, block: B:228:0x0455  */
        /* JADX WARN: Code duplicated, block: B:231:0x0464  */
        /* JADX WARN: Code duplicated, block: B:232:0x0475  */
        /* JADX WARN: Code duplicated, block: B:234:0x047b  */
        /* JADX WARN: Code duplicated, block: B:236:0x047f  */
        /* JADX WARN: Code duplicated, block: B:238:0x0484  */
        /* JADX WARN: Code duplicated, block: B:241:0x048c  */
        /* JADX WARN: Code duplicated, block: B:243:0x0491  */
        /* JADX WARN: Code duplicated, block: B:246:0x0496  */
        /* JADX WARN: Code duplicated, block: B:249:0x04a4  */
        /* JADX WARN: Code duplicated, block: B:252:0x04aa  */
        /* JADX WARN: Code duplicated, block: B:255:0x04dd  */
        /* JADX WARN: Code duplicated, block: B:258:0x04fa  */
        /* JADX WARN: Code duplicated, block: B:261:0x04ff  */
        /* JADX WARN: Code duplicated, block: B:280:0x054c  */
        /* JADX WARN: Code duplicated, block: B:282:0x0571  */
        /* JADX WARN: Code duplicated, block: B:284:0x0577  */
        /* JADX WARN: Code duplicated, block: B:299:0x05a3  */
        /* JADX WARN: Code duplicated, block: B:304:0x05be  */
        /* JADX WARN: Code duplicated, block: B:305:0x05c2  */
        /* JADX WARN: Code duplicated, block: B:4:0x0018  */
        @RequiresNonNull({"codecId"})
        public void initializeFormat(int i) throws ParserException {
            byte b;
            int i2;
            List<byte[]> listSingletonList;
            String str;
            int i3;
            List<byte[]> list;
            String str2;
            String str3;
            int i4;
            Format.Builder builder;
            int i5;
            int i6;
            float f;
            int iIntValue;
            int i7;
            int i8;
            int i9;
            int i10;
            String str4;
            DolbyVisionConfig dolbyVisionConfig;
            String str5 = this.codecId;
            str5.hashCode();
            int pcmEncoding = 3;
            switch (str5) {
                case "V_MPEG4/ISO/AP":
                    b = 0;
                    break;
                case "V_MPEG4/ISO/SP":
                    b = 1;
                    break;
                case "A_MS/ACM":
                    b = 2;
                    break;
                case "A_TRUEHD":
                    b = 3;
                    break;
                case "A_VORBIS":
                    b = 4;
                    break;
                case "A_MPEG/L2":
                    b = 5;
                    break;
                case "A_MPEG/L3":
                    b = 6;
                    break;
                case "V_MS/VFW/FOURCC":
                    b = 7;
                    break;
                case "S_DVBSUB":
                    b = 8;
                    break;
                case "V_MPEG4/ISO/ASP":
                    b = 9;
                    break;
                case "V_MPEG4/ISO/AVC":
                    b = 10;
                    break;
                case "S_VOBSUB":
                    b = 11;
                    break;
                case "A_DTS/LOSSLESS":
                    b = Ascii.FF;
                    break;
                case "A_AAC":
                    b = Ascii.CR;
                    break;
                case "A_AC3":
                    b = Ascii.SO;
                    break;
                case "A_DTS":
                    b = Ascii.SI;
                    break;
                case "V_AV1":
                    b = 16;
                    break;
                case "V_VP8":
                    b = 17;
                    break;
                case "V_VP9":
                    b = Ascii.DC2;
                    break;
                case "S_HDMV/PGS":
                    b = 19;
                    break;
                case "V_THEORA":
                    b = Ascii.DC4;
                    break;
                case "A_DTS/EXPRESS":
                    b = Ascii.NAK;
                    break;
                case "A_PCM/FLOAT/IEEE":
                    b = Ascii.SYN;
                    break;
                case "A_PCM/INT/BIG":
                    b = Ascii.ETB;
                    break;
                case "A_PCM/INT/LIT":
                    b = 24;
                    break;
                case "S_TEXT/ASS":
                    b = Ascii.EM;
                    break;
                case "S_TEXT/SSA":
                    b = Ascii.SUB;
                    break;
                case "V_MPEGH/ISO/HEVC":
                    b = Ascii.ESC;
                    break;
                case "S_TEXT/WEBVTT":
                    b = Ascii.FS;
                    break;
                case "S_TEXT/UTF8":
                    b = Ascii.GS;
                    break;
                case "V_MPEG2":
                    b = Ascii.RS;
                    break;
                case "A_EAC3":
                    b = Ascii.US;
                    break;
                case "A_FLAC":
                    b = 32;
                    break;
                case "A_OPUS":
                    b = 33;
                    break;
                default:
                    b = -1;
                    break;
            }
            int i11 = 4096;
            String str6 = "audio/raw";
            switch (b) {
                case 0:
                case 1:
                case 9:
                    i2 = 0;
                    byte[] bArr = this.codecPrivate;
                    listSingletonList = bArr == null ? null : Collections.singletonList(bArr);
                    str6 = "video/mp4v-es";
                    str = null;
                    i3 = -1;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null && (dolbyVisionConfig = DolbyVisionConfig.parse(new ParsableByteArray(this.dolbyVisionConfigBytes))) != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i12 = (z ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5 || (i8 = this.displayHeight) == i5) {
                            f = -1.0f;
                        } else {
                            f = (this.height * i6) / (this.width * i8);
                        }
                        ColorInfo colorInfoBuild = this.hasColorInfo ? new ColorInfo.Builder().setColorSpace(this.colorSpace).setColorRange(this.colorRange).setColorTransfer(this.colorTransfer).setHdrStaticInfo(getHdrStaticInfo()).setLumaBitdepth(this.bitsPerChannel).setChromaBitdepth(this.bitsPerChannel).build() : null;
                        if (this.name == null && MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES.containsKey(this.name)) {
                            iIntValue = ((Integer) MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES.get(this.name)).intValue();
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0 || Float.compare(this.projectionPoseYaw, 0.0f) != 0 || Float.compare(this.projectionPosePitch, 0.0f) != 0) {
                            i7 = iIntValue;
                        } else if (Float.compare(this.projectionPoseRoll, 0.0f) == 0) {
                            i7 = i2;
                        } else if (Float.compare(this.projectionPoseRoll, 90.0f) == 0) {
                            i7 = 90;
                        } else if (Float.compare(this.projectionPoseRoll, -180.0f) == 0 || Float.compare(this.projectionPoseRoll, 180.0f) == 0) {
                            i7 = 180;
                        } else if (Float.compare(this.projectionPoseRoll, -90.0f) == 0) {
                            i7 = 270;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3) && !"text/x-ssa".equals(str3) && !"text/vtt".equals(str3) && !"application/vobsub".equals(str3) && !"application/pgs".equals(str3) && !"application/dvbsubs".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null && !MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES.containsKey(this.name)) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i12).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 2:
                    i2 = 0;
                    if (parseMsAcmCodecPrivate(new ParsableByteArray(getCodecPrivate(this.codecId)))) {
                        pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                        if (pcmEncoding == 0) {
                            Log.w(MatroskaExtractor.TAG, "Unsupported PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to audio/x-unknown");
                        } else {
                            listSingletonList = null;
                            str = null;
                            i3 = -1;
                        }
                        if (this.dolbyVisionConfigBytes != null) {
                            str = dolbyVisionConfig.codecs;
                            str6 = "video/dolby-vision";
                        }
                        str3 = str6;
                        boolean z2 = this.flagDefault;
                        if (this.flagForced) {
                            i4 = 2;
                        } else {
                            i4 = i2;
                        }
                        int i13 = (z2 ? 1 : 0) | i4;
                        builder = new Format.Builder();
                        if (MimeTypes.isAudio(str3)) {
                            builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                        } else if (MimeTypes.isVideo(str3)) {
                            if (this.displayUnit == 0) {
                                i9 = this.displayWidth;
                                i5 = -1;
                                if (i9 == -1) {
                                    i9 = this.width;
                                }
                                this.displayWidth = i9;
                                i10 = this.displayHeight;
                                if (i10 == -1) {
                                    i10 = this.height;
                                }
                                this.displayHeight = i10;
                            } else {
                                i5 = -1;
                            }
                            i6 = this.displayWidth;
                            if (i6 != i5) {
                                f = -1.0f;
                            } else {
                                f = -1.0f;
                            }
                            if (this.hasColorInfo) {
                            }
                            if (this.name == null) {
                                iIntValue = i5;
                            } else {
                                iIntValue = i5;
                            }
                            if (this.projectionType == 0) {
                                i7 = iIntValue;
                            } else {
                                i7 = iIntValue;
                            }
                            builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                        } else if (!"application/x-subrip".equals(str3)) {
                            throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                        }
                        if (this.name != null) {
                            builder.setLabel(this.name);
                        }
                        Format.Builder id2 = builder.setId(i);
                        if (this.isWebm) {
                            str4 = "video/webm";
                        } else {
                            str4 = "video/x-matroska";
                        }
                        this.format = id2.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i13).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                        return;
                    }
                    Log.w(MatroskaExtractor.TAG, "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown");
                    listSingletonList = null;
                    str = null;
                    str6 = "audio/x-unknown";
                    i3 = -1;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z3 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i14 = (z3 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id3 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id3.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i14).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 3:
                    i2 = 0;
                    this.trueHdSampleRechunker = new TrueHdSampleRechunker();
                    str6 = "audio/true-hd";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z4 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i15 = (z4 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id4 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id4.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i15).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 4:
                    i2 = 0;
                    listSingletonList = parseVorbisCodecPrivate(getCodecPrivate(this.codecId));
                    str6 = "audio/vorbis";
                    i11 = 8192;
                    str = null;
                    i3 = i11;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z5 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i16 = (z5 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id5 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id5.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i16).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 5:
                    i2 = 0;
                    str6 = "audio/mpeg-L2";
                    listSingletonList = null;
                    str = null;
                    i3 = i11;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z6 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i17 = (z6 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id6 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id6.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i17).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 6:
                    i2 = 0;
                    str6 = "audio/mpeg";
                    listSingletonList = null;
                    str = null;
                    i3 = i11;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z7 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i18 = (z7 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id7 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id7.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i18).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 7:
                    i2 = 0;
                    Pair<String, List<byte[]>> fourCcPrivate = parseFourCcPrivate(new ParsableByteArray(getCodecPrivate(this.codecId)));
                    str6 = (String) fourCcPrivate.first;
                    listSingletonList = (List) fourCcPrivate.second;
                    str = null;
                    i3 = -1;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z8 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i19 = (z8 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id8 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id8.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i19).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 8:
                    byte[] bArr2 = new byte[4];
                    i2 = 0;
                    System.arraycopy(getCodecPrivate(this.codecId), 0, bArr2, 0, 4);
                    listSingletonList = ImmutableList.of(bArr2);
                    str6 = "application/dvbsubs";
                    str = null;
                    i3 = -1;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z9 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i110 = (z9 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id9 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id9.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i110).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 10:
                    AvcConfig avcConfig = AvcConfig.parse(new ParsableByteArray(getCodecPrivate(this.codecId)));
                    list = avcConfig.initializationData;
                    this.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                    str2 = avcConfig.codecs;
                    str6 = "video/avc";
                    List<byte[]> list2 = list;
                    str = str2;
                    listSingletonList = list2;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z10 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i111 = (z10 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id10 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id10.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i111).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 11:
                    listSingletonList = ImmutableList.of(getCodecPrivate(this.codecId));
                    str = null;
                    str6 = "application/vobsub";
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z11 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i112 = (z11 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id11 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id11.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i112).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 12:
                    str6 = "audio/vnd.dts.hd";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z12 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i113 = (z12 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id12 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id12.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i113).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 13:
                    listSingletonList = Collections.singletonList(getCodecPrivate(this.codecId));
                    AacUtil.Config audioSpecificConfig = AacUtil.parseAudioSpecificConfig(this.codecPrivate);
                    this.sampleRate = audioSpecificConfig.sampleRateHz;
                    this.channelCount = audioSpecificConfig.channelCount;
                    str = audioSpecificConfig.codecs;
                    str6 = "audio/mp4a-latm";
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z13 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i114 = (z13 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id13 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id13.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i114).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 14:
                    str6 = "audio/ac3";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z14 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i115 = (z14 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id14 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id14.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i115).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 15:
                case 21:
                    this.waitingForDtsAnalysis = true;
                    str6 = "audio/vnd.dts";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z15 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i116 = (z15 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id15 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id15.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i116).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 16:
                    byte[] bArr3 = this.codecPrivate;
                    listSingletonList = bArr3 == null ? null : ImmutableList.of(bArr3);
                    str6 = "video/av01";
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z16 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i117 = (z16 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id16 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id16.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i117).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 17:
                    str6 = "video/x-vnd.on2.vp8";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z17 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i118 = (z17 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id17 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id17.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i118).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 18:
                    byte[] bArr4 = this.codecPrivate;
                    listSingletonList = bArr4 == null ? null : ImmutableList.of(bArr4);
                    str6 = "video/x-vnd.on2.vp9";
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z18 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i119 = (z18 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id18 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id18.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i119).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 19:
                    str6 = "application/pgs";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z19 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1110 = (z19 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id19 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id19.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1110).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 20:
                    str6 = "video/x-unknown";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z110 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1111 = (z110 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id110 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id110.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1111).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 22:
                    if (this.audioBitDepth == 32) {
                        listSingletonList = null;
                        str = null;
                        i3 = -1;
                        i2 = 0;
                        pcmEncoding = 4;
                    } else {
                        Log.w(MatroskaExtractor.TAG, "Unsupported floating point PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to audio/x-unknown");
                        listSingletonList = null;
                        str = null;
                        str6 = "audio/x-unknown";
                        i3 = -1;
                        i2 = 0;
                        pcmEncoding = -1;
                    }
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z111 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1112 = (z111 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id111 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id111.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1112).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 23:
                    int i20 = this.audioBitDepth;
                    if (i20 != 8) {
                        if (i20 == 16) {
                            pcmEncoding = 268435456;
                        } else if (i20 == 24) {
                            pcmEncoding = C.ENCODING_PCM_24BIT_BIG_ENDIAN;
                        } else {
                            if (i20 != 32) {
                                Log.w(MatroskaExtractor.TAG, "Unsupported big endian PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to audio/x-unknown");
                                listSingletonList = null;
                                str = null;
                                str6 = "audio/x-unknown";
                                i3 = -1;
                                i2 = 0;
                                pcmEncoding = -1;
                                if (this.dolbyVisionConfigBytes != null) {
                                    str = dolbyVisionConfig.codecs;
                                    str6 = "video/dolby-vision";
                                }
                                str3 = str6;
                                boolean z112 = this.flagDefault;
                                if (this.flagForced) {
                                    i4 = 2;
                                } else {
                                    i4 = i2;
                                }
                                int i1113 = (z112 ? 1 : 0) | i4;
                                builder = new Format.Builder();
                                if (MimeTypes.isAudio(str3)) {
                                    builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                                } else if (MimeTypes.isVideo(str3)) {
                                    if (this.displayUnit == 0) {
                                        i9 = this.displayWidth;
                                        i5 = -1;
                                        if (i9 == -1) {
                                            i9 = this.width;
                                        }
                                        this.displayWidth = i9;
                                        i10 = this.displayHeight;
                                        if (i10 == -1) {
                                            i10 = this.height;
                                        }
                                        this.displayHeight = i10;
                                    } else {
                                        i5 = -1;
                                    }
                                    i6 = this.displayWidth;
                                    if (i6 != i5) {
                                        f = -1.0f;
                                    } else {
                                        f = -1.0f;
                                    }
                                    if (this.hasColorInfo) {
                                    }
                                    if (this.name == null) {
                                        iIntValue = i5;
                                    } else {
                                        iIntValue = i5;
                                    }
                                    if (this.projectionType == 0) {
                                        i7 = iIntValue;
                                    } else {
                                        i7 = iIntValue;
                                    }
                                    builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                                } else if (!"application/x-subrip".equals(str3)) {
                                    throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                                }
                                if (this.name != null) {
                                    builder.setLabel(this.name);
                                }
                                Format.Builder id112 = builder.setId(i);
                                if (this.isWebm) {
                                    str4 = "video/webm";
                                } else {
                                    str4 = "video/x-matroska";
                                }
                                this.format = id112.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1113).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                                return;
                            }
                            pcmEncoding = C.ENCODING_PCM_32BIT_BIG_ENDIAN;
                        }
                    }
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z113 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1114 = (z113 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id113 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id113.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1114).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 24:
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        Log.w(MatroskaExtractor.TAG, "Unsupported little endian PCM bit depth: " + this.audioBitDepth + ". Setting mimeType to audio/x-unknown");
                        listSingletonList = null;
                        str = null;
                        str6 = "audio/x-unknown";
                        i3 = -1;
                        i2 = 0;
                        pcmEncoding = -1;
                    } else {
                        listSingletonList = null;
                        str = null;
                        i3 = -1;
                        i2 = 0;
                    }
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z114 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1115 = (z114 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id114 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id114.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1115).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 25:
                case 26:
                    listSingletonList = ImmutableList.of(MatroskaExtractor.SSA_DIALOGUE_FORMAT, getCodecPrivate(this.codecId));
                    str = null;
                    str6 = "text/x-ssa";
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z115 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1116 = (z115 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id115 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id115.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1116).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 27:
                    HevcConfig hevcConfig = HevcConfig.parse(new ParsableByteArray(getCodecPrivate(this.codecId)));
                    list = hevcConfig.initializationData;
                    this.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                    str2 = hevcConfig.codecs;
                    str6 = "video/hevc";
                    List<byte[]> list3 = list;
                    str = str2;
                    listSingletonList = list3;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z116 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1117 = (z116 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id116 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id116.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1117).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 28:
                    listSingletonList = null;
                    str = null;
                    str6 = "text/vtt";
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z117 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1118 = (z117 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id117 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id117.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1118).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 29:
                    str6 = "application/x-subrip";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z118 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i1119 = (z118 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id118 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id118.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i1119).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 30:
                    str6 = "video/mpeg2";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z119 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i11110 = (z119 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id119 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id119.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i11110).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 31:
                    str6 = "audio/eac3";
                    listSingletonList = null;
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z1110 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i11111 = (z1110 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id1110 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id1110.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i11111).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 32:
                    listSingletonList = Collections.singletonList(getCodecPrivate(this.codecId));
                    str6 = "audio/flac";
                    str = null;
                    i3 = -1;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z1111 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i11112 = (z1111 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id1111 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id1111.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i11112).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                case 33:
                    listSingletonList = new ArrayList<>(3);
                    listSingletonList.add(getCodecPrivate(this.codecId));
                    listSingletonList.add(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.codecDelayNs).array());
                    listSingletonList.add(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.seekPreRollNs).array());
                    str6 = "audio/opus";
                    str = null;
                    i3 = MatroskaExtractor.OPUS_MAX_INPUT_SIZE;
                    i2 = 0;
                    pcmEncoding = -1;
                    if (this.dolbyVisionConfigBytes != null) {
                        str = dolbyVisionConfig.codecs;
                        str6 = "video/dolby-vision";
                    }
                    str3 = str6;
                    boolean z1112 = this.flagDefault;
                    if (this.flagForced) {
                        i4 = 2;
                    } else {
                        i4 = i2;
                    }
                    int i11113 = (z1112 ? 1 : 0) | i4;
                    builder = new Format.Builder();
                    if (MimeTypes.isAudio(str3)) {
                        builder.setChannelCount(this.channelCount).setSampleRate(this.sampleRate).setPcmEncoding(pcmEncoding);
                    } else if (MimeTypes.isVideo(str3)) {
                        if (this.displayUnit == 0) {
                            i9 = this.displayWidth;
                            i5 = -1;
                            if (i9 == -1) {
                                i9 = this.width;
                            }
                            this.displayWidth = i9;
                            i10 = this.displayHeight;
                            if (i10 == -1) {
                                i10 = this.height;
                            }
                            this.displayHeight = i10;
                        } else {
                            i5 = -1;
                        }
                        i6 = this.displayWidth;
                        if (i6 != i5) {
                            f = -1.0f;
                        } else {
                            f = -1.0f;
                        }
                        if (this.hasColorInfo) {
                        }
                        if (this.name == null) {
                            iIntValue = i5;
                        } else {
                            iIntValue = i5;
                        }
                        if (this.projectionType == 0) {
                            i7 = iIntValue;
                        } else {
                            i7 = iIntValue;
                        }
                        builder.setWidth(this.width).setHeight(this.height).setPixelWidthHeightRatio(f).setRotationDegrees(i7).setProjectionData(this.projectionData).setStereoMode(this.stereoMode).setColorInfo(colorInfoBuild);
                    } else if (!"application/x-subrip".equals(str3)) {
                        throw ParserException.createForMalformedContainer("Unexpected MIME type.", null);
                    }
                    if (this.name != null) {
                        builder.setLabel(this.name);
                    }
                    Format.Builder id1112 = builder.setId(i);
                    if (this.isWebm) {
                        str4 = "video/webm";
                    } else {
                        str4 = "video/x-matroska";
                    }
                    this.format = id1112.setContainerMimeType(str4).setSampleMimeType(str3).setMaxInputSize(i3).setLanguage(this.language).setSelectionFlags(i11113).setInitializationData(listSingletonList).setCodecs(str).setDrmInitData(this.drmInitData).build();
                    return;
                default:
                    throw ParserException.createForMalformedContainer("Unrecognized codec identifier.", null);
            }
        }

        @RequiresNonNull({"output"})
        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.outputPendingSampleMetadata(this.output, this.cryptoData);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                trueHdSampleRechunker.reset();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean samplesHaveSupplementalData(boolean z) {
            if (MatroskaExtractor.CODEC_ID_OPUS.equals(this.codecId)) {
                return z;
            }
            return this.maxBlockAdditionId > 0;
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.put((byte) 0);
            byteBufferOrder.putShort((short) ((this.primaryRChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryRChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryGChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryGChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryBChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.primaryBChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.whitePointChromaticityX * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) ((this.whitePointChromaticityY * 50000.0f) + 0.5f));
            byteBufferOrder.putShort((short) (this.maxMasteringLuminance + 0.5f));
            byteBufferOrder.putShort((short) (this.minMasteringLuminance + 0.5f));
            byteBufferOrder.putShort((short) this.maxContentLuminance);
            byteBufferOrder.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void maybeAddThumbnailMetadata(SparseArray<List<MatroskaSeekMap.CuePointData>> sparseArray, long j, long j2, long j3) {
            List<MatroskaSeekMap.CuePointData> list;
            Metadata metadataCopyWithAppendedEntries;
            if (this.type != 2 || (list = sparseArray.get(this.number)) == null || list.isEmpty()) {
                return;
            }
            long jFindBestThumbnailPresentationTimeUs = findBestThumbnailPresentationTimeUs(list, j, j2, j3);
            if (jFindBestThumbnailPresentationTimeUs != C.TIME_UNSET) {
                Metadata metadata = ((Format) Preconditions.checkNotNull(this.format)).metadata;
                ThumbnailMetadata thumbnailMetadata = new ThumbnailMetadata(jFindBestThumbnailPresentationTimeUs);
                if (metadata == null) {
                    metadataCopyWithAppendedEntries = new Metadata(thumbnailMetadata);
                } else {
                    metadataCopyWithAppendedEntries = metadata.copyWithAppendedEntries(thumbnailMetadata);
                }
                this.format = this.format.buildUpon().setMetadata(metadataCopyWithAppendedEntries).build();
            }
        }

        private static long findBestThumbnailPresentationTimeUs(List<MatroskaSeekMap.CuePointData> list, long j, long j2, long j3) {
            long j4;
            long j5;
            if (list.isEmpty()) {
                return C.TIME_UNSET;
            }
            int iMin = Math.min(list.size(), 20);
            double d = 0.0d;
            int i = -1;
            for (int i2 = 0; i2 < iMin; i2++) {
                MatroskaSeekMap.CuePointData cuePointData = list.get(i2);
                if (cuePointData.timeUs > MatroskaExtractor.MAX_DURATION_US_TO_SCAN_FOR_THUMBNAIL) {
                    break;
                }
                if (i2 < list.size() - 1) {
                    MatroskaSeekMap.CuePointData cuePointData2 = list.get(i2 + 1);
                    j5 = (cuePointData2.clusterPosition + cuePointData2.relativePosition) - (cuePointData.clusterPosition + cuePointData.relativePosition);
                    j4 = cuePointData2.timeUs - cuePointData.timeUs;
                } else {
                    long j6 = (j2 + j3) - (cuePointData.clusterPosition + cuePointData.relativePosition);
                    j4 = j - cuePointData.timeUs;
                    j5 = j6;
                }
                if (j4 > 0) {
                    double d2 = j5 / j4;
                    if (d2 > d) {
                        i = i2;
                        d = d2;
                    }
                }
            }
            return i == -1 ? C.TIME_UNSET : list.get(i).timeUs;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long littleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (littleEndianUnsignedInt == 1482049860) {
                    return new Pair<>("video/divx", null);
                }
                if (littleEndianUnsignedInt == 859189832) {
                    return new Pair<>("video/3gpp", null);
                }
                if (littleEndianUnsignedInt == 826496599) {
                    byte[] data = parsableByteArray.getData();
                    for (int position = parsableByteArray.getPosition() + 20; position < data.length - 4; position++) {
                        if (data[position] == 0 && data[position + 1] == 0 && data[position + 2] == 1 && data[position + 3] == 15) {
                            return new Pair<>("video/wvc1", Collections.singletonList(Arrays.copyOfRange(data, position, data.length)));
                        }
                    }
                    throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", null);
                }
                Log.w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>("video/x-unknown", null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing FourCC private data", null);
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            int i;
            int i2;
            try {
                if (bArr[0] != 2) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                int i3 = 0;
                int i4 = 1;
                while (true) {
                    i = bArr[i4];
                    if ((i & 255) != 255) {
                        break;
                    }
                    i3 += 255;
                    i4++;
                }
                int i5 = i4 + 1;
                int i6 = i3 + (i & 255);
                int i7 = 0;
                while (true) {
                    i2 = bArr[i5];
                    if ((i2 & 255) != 255) {
                        break;
                    }
                    i7 += 255;
                    i5++;
                }
                int i8 = i5 + 1;
                int i9 = i7 + (i2 & 255);
                if (bArr[i8] != 1) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                byte[] bArr2 = new byte[i6];
                System.arraycopy(bArr, i8, bArr2, 0, i6);
                int i10 = i8 + i6;
                if (bArr[i10] != 3) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                int i11 = i10 + i9;
                if (bArr[i11] != 5) {
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                }
                byte[] bArr3 = new byte[bArr.length - i11];
                System.arraycopy(bArr, i11, bArr3, 0, bArr.length - i11);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int littleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (littleEndianUnsignedShort == 1) {
                    return true;
                }
                if (littleEndianUnsignedShort == 65534) {
                    parsableByteArray.setPosition(24);
                    if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                        return true;
                    }
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @EnsuresNonNull({"output"})
        public void assertOutputInitialized() {
            Preconditions.checkNotNull(this.output);
        }

        @EnsuresNonNull({"codecPrivate"})
        private byte[] getCodecPrivate(String str) throws ParserException {
            byte[] bArr = this.codecPrivate;
            if (bArr != null) {
                return bArr;
            }
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + str, null);
        }
    }

    private static final class MatroskaSeekMap implements TrackAwareSeekMap, ChunkIndexProvider {
        private final ChunkIndex chunkIndex;
        private final long durationUs;
        private final SparseArray<List<CuePointData>> perTrackCues;
        private final int primarySeekTrackNumber;

        @Override // androidx.media3.extractor.SeekMap
        public /* synthetic */ boolean isEstimated() {
            return SeekMap.CC.$default$isEstimated(this);
        }

        public MatroskaSeekMap(SparseArray<List<CuePointData>> sparseArray, long j, int i, long j2, long j3) {
            this.perTrackCues = sparseArray;
            this.durationUs = j;
            this.primarySeekTrackNumber = i;
            this.chunkIndex = buildChunkIndex(sparseArray, j, i, j2, j3);
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return isSeekable(this.primarySeekTrackNumber);
        }

        @Override // androidx.media3.extractor.TrackAwareSeekMap
        public boolean isSeekable(int i) {
            List<CuePointData> list = this.perTrackCues.get(i);
            return (list == null || list.isEmpty()) ? false : true;
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j) {
            ChunkIndex chunkIndex = this.chunkIndex;
            if (chunkIndex != null) {
                return chunkIndex.getSeekPoints(j);
            }
            return new SeekMap.SeekPoints(SeekPoint.START);
        }

        @Override // androidx.media3.extractor.TrackAwareSeekMap
        public SeekMap.SeekPoints getSeekPoints(long j, int i) {
            int i2;
            int i3;
            List<CuePointData> list = this.perTrackCues.get(i);
            if ((list == null || list.isEmpty()) && i != (i2 = this.primarySeekTrackNumber)) {
                list = this.perTrackCues.get(i2);
            }
            if (list == null || list.isEmpty()) {
                return new SeekMap.SeekPoints(SeekPoint.START);
            }
            int iBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super CuePointData>>) list, new CuePointData(j, -1L, -1L), true, false);
            if (iBinarySearchFloor != -1) {
                CuePointData cuePointData = list.get(iBinarySearchFloor);
                SeekPoint seekPoint = new SeekPoint(cuePointData.timeUs, cuePointData.clusterPosition);
                if (cuePointData.timeUs < j && (i3 = iBinarySearchFloor + 1) < list.size()) {
                    CuePointData cuePointData2 = list.get(i3);
                    return new SeekMap.SeekPoints(seekPoint, new SeekPoint(cuePointData2.timeUs, cuePointData2.clusterPosition));
                }
                return new SeekMap.SeekPoints(seekPoint);
            }
            CuePointData cuePointData3 = list.get(0);
            return new SeekMap.SeekPoints(new SeekPoint(cuePointData3.timeUs, cuePointData3.clusterPosition));
        }

        @Override // androidx.media3.extractor.ChunkIndexProvider
        public ChunkIndex getChunkIndex() {
            return this.chunkIndex;
        }

        private static ChunkIndex buildChunkIndex(SparseArray<List<CuePointData>> sparseArray, long j, int i, long j2, long j3) {
            int i2;
            List<CuePointData> list = sparseArray.get(i);
            if (list == null || list.isEmpty()) {
                return null;
            }
            int size = list.size();
            int[] iArrCopyOf = new int[size];
            long[] jArrCopyOf = new long[size];
            long[] jArrCopyOf2 = new long[size];
            long[] jArrCopyOf3 = new long[size];
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                CuePointData cuePointData = list.get(i4);
                jArrCopyOf3[i4] = cuePointData.timeUs;
                jArrCopyOf[i4] = cuePointData.clusterPosition;
            }
            while (true) {
                i2 = size - 1;
                if (i3 >= i2) {
                    break;
                }
                int i5 = i3 + 1;
                iArrCopyOf[i3] = (int) (jArrCopyOf[i5] - jArrCopyOf[i3]);
                jArrCopyOf2[i3] = jArrCopyOf3[i5] - jArrCopyOf3[i3];
                i3 = i5;
            }
            int i6 = i2;
            while (i6 > 0 && jArrCopyOf3[i6] >= j) {
                i6--;
            }
            iArrCopyOf[i6] = (int) ((j2 + j3) - jArrCopyOf[i6]);
            jArrCopyOf2[i6] = j - jArrCopyOf3[i6];
            if (i6 < i2) {
                Log.w(MatroskaExtractor.TAG, "Discarding trailing cue points with timestamps greater than total duration.");
                int i7 = i6 + 1;
                iArrCopyOf = Arrays.copyOf(iArrCopyOf, i7);
                jArrCopyOf = Arrays.copyOf(jArrCopyOf, i7);
                jArrCopyOf2 = Arrays.copyOf(jArrCopyOf2, i7);
                jArrCopyOf3 = Arrays.copyOf(jArrCopyOf3, i7);
            }
            return new ChunkIndex(iArrCopyOf, jArrCopyOf, jArrCopyOf2, jArrCopyOf3);
        }

        private static final class CuePointData implements Comparable<CuePointData> {
            private final long clusterPosition;
            private final long relativePosition;
            private final long timeUs;

            private CuePointData(long j, long j2, long j3) {
                this.timeUs = j;
                this.clusterPosition = j2;
                this.relativePosition = j3;
            }

            @Override // java.lang.Comparable
            public int compareTo(CuePointData cuePointData) {
                return Long.compare(this.timeUs, cuePointData.timeUs);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof CuePointData)) {
                    return false;
                }
                CuePointData cuePointData = (CuePointData) obj;
                return this.timeUs == cuePointData.timeUs && this.clusterPosition == cuePointData.clusterPosition && this.relativePosition == cuePointData.relativePosition;
            }

            public int hashCode() {
                return Objects.hash(Long.valueOf(this.timeUs), Long.valueOf(this.clusterPosition), Long.valueOf(this.relativePosition));
            }
        }
    }
}
