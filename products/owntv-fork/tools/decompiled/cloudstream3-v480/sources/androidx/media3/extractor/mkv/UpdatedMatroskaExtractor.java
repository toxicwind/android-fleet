package androidx.media3.extractor.mkv;

import android.net.Uri;
import android.util.Pair;
import android.util.SparseArray;
import androidx.collection.SieveCacheKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.ChunkIndexProvider;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackAwareSeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.metadata.ThumbnailMetadata;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import dev.whyoleg.cryptography.serialization.asn1.internal.DerTagKt;
import io.ktor.http.ContentDisposition;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u000e\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 \u009a\u00012\u00020\u0001:\n\u0097\u0001\u0098\u0001\u0099\u0001\u009a\u0001\u009b\u0001B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\t\b\u0017¢\u0006\u0004\b\b\u0010\nB\u0011\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\fB\u0019\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\rJ\u0010\u0010^\u001a\u00020\u00142\u0006\u0010_\u001a\u00020`H\u0016J\u0010\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020]H\u0016J\u0018\u0010d\u001a\u00020b2\u0006\u0010e\u001a\u00020$2\u0006\u0010f\u001a\u00020$H\u0017J\b\u0010g\u001a\u00020bH\u0016J\u0018\u0010h\u001a\u00020\u00052\u0006\u0010_\u001a\u00020`2\u0006\u0010i\u001a\u00020jH\u0016J\u0010\u0010k\u001a\u00020\u00052\u0006\u0010l\u001a\u00020\u0005H\u0005J\u0010\u0010m\u001a\u00020\u00142\u0006\u0010l\u001a\u00020\u0005H\u0005J \u0010n\u001a\u00020b2\u0006\u0010l\u001a\u00020\u00052\u0006\u0010o\u001a\u00020$2\u0006\u0010p\u001a\u00020$H\u0005J\u0010\u0010q\u001a\u00020b2\u0006\u0010l\u001a\u00020\u0005H\u0005J\u0018\u0010r\u001a\u00020b2\u0006\u0010l\u001a\u00020\u00052\u0006\u0010s\u001a\u00020$H\u0005J\u0018\u0010t\u001a\u00020b2\u0006\u0010l\u001a\u00020\u00052\u0006\u0010s\u001a\u00020uH\u0005J\u0018\u0010v\u001a\u00020b2\u0006\u0010l\u001a\u00020\u00052\u0006\u0010s\u001a\u00020wH\u0005J \u0010x\u001a\u00020b2\u0006\u0010l\u001a\u00020\u00052\u0006\u0010p\u001a\u00020\u00052\u0006\u0010_\u001a\u00020`H\u0005J \u0010y\u001a\u00020b2\u0006\u0010z\u001a\u00020\u00122\u0006\u0010_\u001a\u00020`2\u0006\u0010p\u001a\u00020\u0005H\u0004J(\u0010{\u001a\u00020b2\u0006\u0010z\u001a\u00020\u00122\u0006\u0010O\u001a\u00020\u00052\u0006\u0010_\u001a\u00020`2\u0006\u0010p\u001a\u00020\u0005H\u0004J\u0010\u0010|\u001a\u00020b2\u0006\u0010l\u001a\u00020\u0005H\u0002J\u0010\u0010}\u001a\u00020b2\u0006\u0010l\u001a\u00020\u0005H\u0002J\u0010\u0010~\u001a\u00020\u00122\u0006\u0010\u007f\u001a\u00020\u0005H\u0004J3\u0010\u0080\u0001\u001a\u00020b2\u0006\u0010z\u001a\u00020\u00122\u0006\u0010f\u001a\u00020$2\u0006\u0010\u0004\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u00020\u00052\u0007\u0010\u0082\u0001\u001a\u00020\u0005H\u0002J\u001a\u0010\u0083\u0001\u001a\u00020b2\u0006\u0010_\u001a\u00020`2\u0007\u0010\u0084\u0001\u001a\u00020\u0005H\u0002J+\u0010\u0085\u0001\u001a\u00020\u00052\u0006\u0010_\u001a\u00020`2\u0006\u0010z\u001a\u00020\u00122\u0007\u0010\u0081\u0001\u001a\u00020\u00052\u0007\u0010\u0086\u0001\u001a\u00020\u0014H\u0002J\t\u0010\u0087\u0001\u001a\u00020\u0005H\u0002J\t\u0010\u0088\u0001\u001a\u00020bH\u0002J$\u0010\u0089\u0001\u001a\u00020b2\u0006\u0010_\u001a\u00020`2\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\u0007\u0010\u0081\u0001\u001a\u00020\u0005H\u0002J-\u0010\u008c\u0001\u001a\u00020b2\u0006\u0010_\u001a\u00020`2\b\u0010\u008d\u0001\u001a\u00030\u008b\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u00052\u0007\u0010\u008e\u0001\u001a\u00020\u0005H\u0002J#\u0010\u008f\u0001\u001a\u00020\u00052\u0006\u0010_\u001a\u00020`2\u0007\u0010c\u001a\u00030\u0090\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u0005H\u0002J\u001a\u0010\u0091\u0001\u001a\u00020\u00142\u0006\u0010i\u001a\u00020j2\u0007\u0010\u0092\u0001\u001a\u00020$H\u0002J\u0012\u0010\u0093\u0001\u001a\u00020$2\u0007\u0010\u0094\u0001\u001a\u00020$H\u0002J\t\u0010\u0095\u0001\u001a\u00020bH\u0002J\t\u0010\u0096\u0001\u001a\u00020bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000201000\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010:\u001a\u0012\u0012\u0004\u0012\u00020$0;j\b\u0012\u0004\u0012\u00020$`<X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010=\u001a\u0012\u0012\u0004\u0012\u00020$0>j\b\u0012\u0004\u0012\u00020$`?X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020KX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020ZX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\\\u001a\u0004\u0018\u00010]X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u009c\u0001"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor;", "Landroidx/media3/extractor/Extractor;", "reader", "Landroidx/media3/extractor/mkv/EbmlReader;", "flags", "", "subtitleParserFactory", "Landroidx/media3/extractor/text/SubtitleParser$Factory;", "<init>", "(Landroidx/media3/extractor/mkv/EbmlReader;ILandroidx/media3/extractor/text/SubtitleParser$Factory;)V", "()V", "(I)V", "(Landroidx/media3/extractor/text/SubtitleParser$Factory;)V", "(Landroidx/media3/extractor/text/SubtitleParser$Factory;I)V", "varintReader", "Landroidx/media3/extractor/mkv/VarintReader;", "tracks", "Landroid/util/SparseArray;", "Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$Track;", "seekForCuesEnabled", "", "parseSubtitlesDuringExtraction", "nalStartCode", "Landroidx/media3/common/util/ParsableByteArray;", "nalLength", "scratch", "vorbisNumPageSamples", "seekEntryIdBytes", "sampleStrippedBytes", "subtitleSample", "encryptionInitializationVector", "encryptionSubsampleData", "supplementalData", "encryptionSubsampleDataBuffer", "Ljava/nio/ByteBuffer;", "segmentContentSize", "", "segmentContentPosition", "timecodeScale", "durationTimecode", "durationUs", "isWebm", "pendingEndTracks", "currentTrack", "sentSeekMap", "seekEntryId", "seekEntryPosition", "perTrackCues", "", "Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$MatroskaSeekMap$CuePointData;", "inCuesElement", "currentCueTimeUs", "currentCueTrackNumber", "currentCueClusterPosition", "currentCueRelativePosition", "primarySeekTrackNumber", "seekForCues", "seekForSeekContent", "visitedSeekHeads", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "pendingSeekHeads", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "seekPositionAfterSeekingForHead", "cuesContentPosition", "seekPositionAfterBuildingCues", "clusterTimecodeUs", "haveOutputSample", "blockState", "blockTimeUs", "blockDurationUs", "blockSampleIndex", "blockSampleCount", "blockSampleSizes", "", "blockTrackNumber", "blockTrackNumberLength", "blockFlags", "blockAdditionalId", "blockHasReferenceBlock", "blockGroupDiscardPaddingNs", "sampleBytesRead", "sampleBytesWritten", "sampleCurrentNalBytesRemaining", "sampleEncodingHandled", "sampleSignalByteRead", "samplePartitionCountRead", "samplePartitionCount", "sampleSignalByte", "", "sampleInitializationVectorRead", "extractorOutput", "Landroidx/media3/extractor/ExtractorOutput;", "sniff", TvContractCompat.PARAM_INPUT, "Landroidx/media3/extractor/ExtractorInput;", "init", "", "output", "seek", "position", "timeUs", "release", "read", "seekPosition", "Landroidx/media3/extractor/PositionHolder;", "getElementType", "id", "isLevel1Element", "startMasterElement", "contentPosition", "contentSize", "endMasterElement", "integerElement", ES6Iterator.VALUE_PROPERTY, "floatElement", "", "stringElement", "", "binaryElement", "handleBlockAddIDExtraData", "track", "handleBlockAdditionalData", "assertInTrackEntry", "assertInCues", "getCurrentTrack", "currentElementId", "commitSampleToOutput", ContentDisposition.Parameters.Size, TypedValues.CycleType.S_WAVE_OFFSET, "readScratch", "requiredLength", "writeSampleData", "isBlockGroup", "finishWriteSampleData", "resetWriteSampleData", "writeSubtitleSampleData", "samplePrefix", "", "writeToTarget", TypedValues.AttributesType.S_TARGET, "length", "writeToOutput", "Landroidx/media3/extractor/TrackOutput;", "maybeSeekForCues", "currentPosition", "scaleTimecodeToUs", "unscaledTimecode", "assertInitialized", "maybeEndTracks", "Flags", "InnerEbmlProcessor", "Track", "Companion", "MatroskaSeekMap", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UpdatedMatroskaExtractor implements Extractor {
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
    private static final byte[] SSA_DIALOGUE_FORMAT;
    private static final byte[] SSA_PREFIX;
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final byte[] VTT_PREFIX;
    private static final int VTT_PREFIX_END_TIMECODE_OFFSET = 25;
    private static final String VTT_TIMECODE_FORMAT = "%02d:%02d:%02d.%03d";
    private static final long VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private static final UUID WAVE_SUBFORMAT_PCM;
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
    private ArrayList<Long> pendingSeekHeads;
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
    private boolean seekForSeekContent;
    private long seekPositionAfterBuildingCues;
    private long seekPositionAfterSeekingForHead;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private HashSet<Long> visitedSeekHeads;
    private final ParsableByteArray vorbisNumPageSamples;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: androidx.media3.extractor.mkv.UpdatedMatroskaExtractor$$ExternalSyntheticLambda0
        @Override // androidx.media3.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return UpdatedMatroskaExtractor.FACTORY$lambda$0();
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

    /* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
    @Target({ElementType.TYPE, ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.TYPE, AnnotationTarget.TYPE_PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @MustBeDocumented
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$Flags;", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Documented
    public @interface Flags {
    }

    protected final int getElementType(int id) {
        switch (id) {
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

    protected final boolean isLevel1Element(int id) {
        return id == 357149030 || id == ID_CLUSTER || id == ID_CUES || id == ID_TRACKS;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    private UpdatedMatroskaExtractor(EbmlReader ebmlReader, int i, SubtitleParser.Factory factory) {
        this.reader = ebmlReader;
        this.segmentContentPosition = -1L;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.currentCueTimeUs = C.TIME_UNSET;
        this.currentCueTrackNumber = -1;
        this.currentCueClusterPosition = -1L;
        this.currentCueRelativePosition = -1L;
        this.primarySeekTrackNumber = -1;
        this.visitedSeekHeads = new HashSet<>();
        this.pendingSeekHeads = new ArrayList<>();
        this.seekPositionAfterSeekingForHead = -1L;
        this.cuesContentPosition = -1L;
        this.seekPositionAfterBuildingCues = -1L;
        this.clusterTimecodeUs = C.TIME_UNSET;
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

    @Deprecated(message = "Use {@link #MatroskaExtractor(SubtitleParser.Factory)} instead.")
    public UpdatedMatroskaExtractor() {
        DefaultEbmlReader defaultEbmlReader = new DefaultEbmlReader();
        SubtitleParser.Factory UNSUPPORTED = SubtitleParser.Factory.UNSUPPORTED;
        Intrinsics.checkNotNullExpressionValue(UNSUPPORTED, "UNSUPPORTED");
        this(defaultEbmlReader, 2, UNSUPPORTED);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated(message = "Use {@link #MatroskaExtractor(SubtitleParser.Factory, int)} instead.")
    public UpdatedMatroskaExtractor(int i) {
        SubtitleParser.Factory UNSUPPORTED = SubtitleParser.Factory.UNSUPPORTED;
        Intrinsics.checkNotNullExpressionValue(UNSUPPORTED, "UNSUPPORTED");
        this(new DefaultEbmlReader(), i | 2, UNSUPPORTED);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UpdatedMatroskaExtractor(SubtitleParser.Factory subtitleParserFactory) {
        this(new DefaultEbmlReader(), 0, subtitleParserFactory);
        Intrinsics.checkNotNullParameter(subtitleParserFactory, "subtitleParserFactory");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UpdatedMatroskaExtractor(SubtitleParser.Factory subtitleParserFactory, int i) {
        this(new DefaultEbmlReader(), i, subtitleParserFactory);
        Intrinsics.checkNotNullParameter(subtitleParserFactory, "subtitleParserFactory");
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput input) throws IOException {
        Intrinsics.checkNotNullParameter(input, "input");
        return new Sniffer().sniff(input);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput output) {
        Intrinsics.checkNotNullParameter(output, "output");
        if (this.parseSubtitlesDuringExtraction) {
            output = new SubtitleTranscodingExtractorOutput(output, this.subtitleParserFactory);
        }
        this.extractorOutput = output;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long position, long timeUs) {
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
        int size = this.tracks.size();
        for (int i = 0; i < size; i++) {
            this.tracks.valueAt(i).reset();
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(seekPosition, "seekPosition");
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(input);
            if (maybeSeekForCues(seekPosition, input.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        int size = this.tracks.size();
        for (int i = 0; i < size; i++) {
            Track trackValueAt = this.tracks.valueAt(i);
            trackValueAt.assertOutputInitialized();
            trackValueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    protected final void startMasterElement(int id, long contentPosition, long contentSize) throws ParserException {
        assertInitialized();
        if (id == 160) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0L;
            return;
        }
        if (id == 174) {
            Track track = new Track();
            this.currentTrack = track;
            Intrinsics.checkNotNull(track);
            track.setWebm(this.isWebm);
            return;
        }
        if (id == 183) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(id);
            this.currentCueTrackNumber = -1;
            this.currentCueClusterPosition = -1L;
            this.currentCueRelativePosition = -1L;
            return;
        }
        if (id == 187) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(id);
            this.currentCueTimeUs = C.TIME_UNSET;
            return;
        }
        if (id == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1L;
            return;
        }
        if (id == ID_CONTENT_ENCRYPTION) {
            getCurrentTrack(id).setHasContentEncryption(true);
            return;
        }
        if (id == ID_MASTERING_METADATA) {
            getCurrentTrack(id).setHasColorInfo(true);
            return;
        }
        if (id == ID_SEGMENT) {
            long j = this.segmentContentPosition;
            if (j != -1 && j != contentPosition) {
                ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Multiple Segment elements not supported", null);
                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
                throw parserExceptionCreateForMalformedContainer;
            }
            this.segmentContentPosition = contentPosition;
            this.segmentContentSize = contentSize;
            return;
        }
        if (id == ID_CUES) {
            if (this.sentSeekMap) {
                return;
            }
            this.inCuesElement = true;
            return;
        }
        if (id == ID_CLUSTER && !this.sentSeekMap) {
            boolean z = this.seekForCuesEnabled;
            if (z && this.cuesContentPosition != -1) {
                this.seekForCues = true;
                return;
            }
            if (z && !this.pendingSeekHeads.isEmpty()) {
                this.seekForSeekContent = true;
                return;
            }
            ExtractorOutput extractorOutput = this.extractorOutput;
            Intrinsics.checkNotNull(extractorOutput);
            extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
            this.sentSeekMap = true;
        }
    }

    protected final void endMasterElement(int id) throws ParserException {
        int i;
        assertInitialized();
        int number = -1;
        switch (id) {
            case 160:
                if (this.blockState != 2) {
                    return;
                }
                Track track = this.tracks.get(this.blockTrackNumber);
                track.assertOutputInitialized();
                if (this.blockGroupDiscardPaddingNs > 0 && Intrinsics.areEqual(CODEC_ID_OPUS, track.getCodecId())) {
                    this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
                }
                int i2 = 0;
                for (int i3 = 0; i3 < this.blockSampleCount; i3++) {
                    i2 += this.blockSampleSizes[i3];
                }
                int i4 = 0;
                while (i4 < this.blockSampleCount) {
                    long defaultSampleDurationNs = this.blockTimeUs + ((long) ((track.getDefaultSampleDurationNs() * i4) / 1000));
                    int i5 = this.blockFlags;
                    if (i4 == 0 && !this.blockHasReferenceBlock) {
                        i5 |= 1;
                    }
                    int i6 = this.blockSampleSizes[i4];
                    int i7 = i2 - i6;
                    Intrinsics.checkNotNull(track);
                    commitSampleToOutput(track, defaultSampleDurationNs, i5, i6, i7);
                    i4++;
                    i2 = i7;
                }
                this.blockState = 0;
                return;
            case 174:
                Track track2 = (Track) Preconditions.checkNotNull(this.currentTrack);
                if (track2.getCodecId() == null) {
                    ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", null);
                    Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
                    throw parserExceptionCreateForMalformedContainer;
                }
                Companion companion = INSTANCE;
                String codecId = track2.getCodecId();
                Intrinsics.checkNotNull(codecId);
                if (companion.isCodecSupported(codecId)) {
                    track2.initializeFormat(track2.getNumber());
                    ExtractorOutput extractorOutput = this.extractorOutput;
                    Intrinsics.checkNotNull(extractorOutput);
                    track2.setOutput(extractorOutput.track(track2.getNumber(), track2.getType()));
                    this.tracks.put(track2.getNumber(), track2);
                }
                this.currentTrack = null;
                return;
            case 183:
                if (this.sentSeekMap) {
                    return;
                }
                assertInCues(id);
                if (this.currentCueTimeUs == C.TIME_UNSET || (i = this.currentCueTrackNumber) == -1 || this.currentCueClusterPosition == -1) {
                    return;
                }
                ArrayList arrayList = this.perTrackCues.get(i);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.perTrackCues.put(this.currentCueTrackNumber, arrayList);
                }
                arrayList.add(new MatroskaSeekMap.CuePointData(this.currentCueTimeUs, this.segmentContentPosition + this.currentCueClusterPosition, this.currentCueRelativePosition));
                return;
            case ID_SEEK /* 19899 */:
                int i8 = this.seekEntryId;
                if (i8 != -1) {
                    long j = this.seekEntryPosition;
                    if (j != -1) {
                        if (i8 == ID_SEEK_HEAD) {
                            if (this.visitedSeekHeads.add(Long.valueOf(j))) {
                                this.pendingSeekHeads.add(Long.valueOf(this.seekEntryPosition));
                                return;
                            }
                            return;
                        } else {
                            if (i8 == ID_CUES) {
                                this.cuesContentPosition = j;
                                if (!this.seekForCuesEnabled || this.seekPositionAfterSeekingForHead == -1) {
                                    return;
                                }
                                this.seekForCues = true;
                                return;
                            }
                            return;
                        }
                    }
                }
                ParserException parserExceptionCreateForMalformedContainer2 = ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", null);
                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer2, "createForMalformedContainer(...)");
                throw parserExceptionCreateForMalformedContainer2;
            case ID_CONTENT_ENCODING /* 25152 */:
                assertInTrackEntry(id);
                Track track3 = this.currentTrack;
                Intrinsics.checkNotNull(track3);
                if (track3.getHasContentEncryption()) {
                    Track track4 = this.currentTrack;
                    Intrinsics.checkNotNull(track4);
                    if (track4.getCryptoData() == null) {
                        ParserException parserExceptionCreateForMalformedContainer3 = ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", null);
                        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer3, "createForMalformedContainer(...)");
                        throw parserExceptionCreateForMalformedContainer3;
                    }
                    Track track5 = this.currentTrack;
                    Intrinsics.checkNotNull(track5);
                    UUID uuid = C.UUID_NIL;
                    Track track6 = this.currentTrack;
                    Intrinsics.checkNotNull(track6);
                    TrackOutput.CryptoData cryptoData = track6.getCryptoData();
                    Intrinsics.checkNotNull(cryptoData);
                    track5.setDrmInitData(new DrmInitData(new DrmInitData.SchemeData(uuid, "video/webm", cryptoData.encryptionKey)));
                    return;
                }
                return;
            case ID_CONTENT_ENCODINGS /* 28032 */:
                assertInTrackEntry(id);
                Track track7 = this.currentTrack;
                Intrinsics.checkNotNull(track7);
                if (track7.getHasContentEncryption()) {
                    Track track8 = this.currentTrack;
                    Intrinsics.checkNotNull(track8);
                    if (track8.getSampleStrippedBytes() == null) {
                        return;
                    }
                    ParserException parserExceptionCreateForMalformedContainer4 = ParserException.createForMalformedContainer("Combining encryption and compression is not supported", null);
                    Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer4, "createForMalformedContainer(...)");
                    throw parserExceptionCreateForMalformedContainer4;
                }
                return;
            case 357149030:
                if (this.timecodeScale == C.TIME_UNSET) {
                    this.timecodeScale = 1000000L;
                }
                long j2 = this.durationTimecode;
                if (j2 != C.TIME_UNSET) {
                    this.durationUs = scaleTimecodeToUs(j2);
                    return;
                }
                return;
            case ID_TRACKS /* 374648427 */:
                if (this.tracks.size() == 0) {
                    ParserException parserExceptionCreateForMalformedContainer5 = ParserException.createForMalformedContainer("No valid tracks were found", null);
                    Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer5, "createForMalformedContainer(...)");
                    throw parserExceptionCreateForMalformedContainer5;
                }
                boolean z = !this.seekForCuesEnabled || this.cuesContentPosition == -1;
                int size = this.tracks.size();
                int number2 = -1;
                int number3 = -1;
                int number4 = -1;
                int number5 = -1;
                for (int i9 = 0; i9 < size; i9++) {
                    Track trackValueAt = this.tracks.valueAt(i9);
                    Intrinsics.checkNotNullExpressionValue(trackValueAt, "valueAt(...)");
                    Track track9 = trackValueAt;
                    int type = track9.getType();
                    if (type == 1) {
                        if (track9.getFlagDefault()) {
                            number4 = track9.getNumber();
                        }
                        if (number5 == -1) {
                            number5 = track9.getNumber();
                        }
                    } else if (type == 2) {
                        if (track9.getFlagDefault()) {
                            number2 = track9.getNumber();
                        }
                        if (number3 == -1) {
                            number3 = track9.getNumber();
                        }
                    }
                    if (z) {
                        track9.assertOutputInitialized();
                        if (!track9.getWaitingForDtsAnalysis()) {
                            TrackOutput output = track9.getOutput();
                            Intrinsics.checkNotNull(output);
                            output.format((Format) Preconditions.checkNotNull(track9.getFormat()));
                        }
                    }
                }
                if (number2 != -1) {
                    number = number2;
                } else if (number3 != -1) {
                    number = number3;
                } else if (number4 != -1) {
                    number = number4;
                } else if (number5 != -1) {
                    number = number5;
                } else if (this.tracks.size() > 0) {
                    number = this.tracks.valueAt(0).getNumber();
                }
                this.primarySeekTrackNumber = number;
                if (z) {
                    maybeEndTracks();
                    return;
                }
                return;
            case ID_SEGMENT /* 408125543 */:
                if (this.sentSeekMap) {
                    return;
                }
                if (!this.pendingSeekHeads.isEmpty() && this.seekForCuesEnabled) {
                    this.seekForSeekContent = true;
                    return;
                }
                ExtractorOutput extractorOutput2 = this.extractorOutput;
                Intrinsics.checkNotNull(extractorOutput2);
                extractorOutput2.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                return;
            case ID_CUES /* 475249515 */:
                if (this.sentSeekMap) {
                    return;
                }
                int size2 = this.perTrackCues.size();
                int i10 = 0;
                while (true) {
                    if (i10 < size2) {
                        List<MatroskaSeekMap.CuePointData> listValueAt = this.perTrackCues.valueAt(i10);
                        Intrinsics.checkNotNullExpressionValue(listValueAt, "valueAt(...)");
                        if (listValueAt.isEmpty()) {
                            i10++;
                        } else if (this.durationUs != C.TIME_UNSET) {
                            int size3 = this.perTrackCues.size();
                            for (int i11 = 0; i11 < size3; i11++) {
                                List<MatroskaSeekMap.CuePointData> listValueAt2 = this.perTrackCues.valueAt(i11);
                                Intrinsics.checkNotNullExpressionValue(listValueAt2, "valueAt(...)");
                                CollectionsKt.sort(listValueAt2);
                            }
                            MatroskaSeekMap matroskaSeekMap = new MatroskaSeekMap(this.perTrackCues, this.durationUs, this.primarySeekTrackNumber, this.segmentContentPosition, this.segmentContentSize);
                            ExtractorOutput extractorOutput3 = this.extractorOutput;
                            Intrinsics.checkNotNull(extractorOutput3);
                            extractorOutput3.seekMap(matroskaSeekMap);
                        }
                    }
                    ExtractorOutput extractorOutput4 = this.extractorOutput;
                    Intrinsics.checkNotNull(extractorOutput4);
                    extractorOutput4.seekMap(new SeekMap.Unseekable(this.durationUs));
                }
                this.sentSeekMap = true;
                this.inCuesElement = false;
                int size4 = this.tracks.size();
                for (int i12 = 0; i12 < size4; i12++) {
                    Track trackValueAt2 = this.tracks.valueAt(i12);
                    Intrinsics.checkNotNullExpressionValue(trackValueAt2, "valueAt(...)");
                    Track track10 = trackValueAt2;
                    track10.maybeAddThumbnailMetadata(this.perTrackCues, this.durationUs, this.segmentContentPosition, this.segmentContentSize);
                    if (!track10.getWaitingForDtsAnalysis()) {
                        track10.assertOutputInitialized();
                        TrackOutput output2 = track10.getOutput();
                        Intrinsics.checkNotNull(output2);
                        Format format = track10.getFormat();
                        if (format == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        output2.format(format);
                    }
                }
                maybeEndTracks();
                return;
            default:
                return;
        }
    }

    protected final void integerElement(int id, long value) throws ParserException {
        if (id == 240) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(id);
            if (this.currentCueRelativePosition == -1) {
                this.currentCueRelativePosition = value;
                return;
            }
            return;
        }
        if (id == 241) {
            if (this.sentSeekMap) {
                return;
            }
            assertInCues(id);
            if (this.currentCueClusterPosition == -1) {
                this.currentCueClusterPosition = value;
                return;
            }
            return;
        }
        if (id == ID_CONTENT_ENCODING_ORDER) {
            if (value == 0) {
                return;
            }
            ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("ContentEncodingOrder " + value + " not supported", null);
            Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
            throw parserExceptionCreateForMalformedContainer;
        }
        if (id == ID_CONTENT_ENCODING_SCOPE) {
            if (value == 1) {
                return;
            }
            ParserException parserExceptionCreateForMalformedContainer2 = ParserException.createForMalformedContainer("ContentEncodingScope " + value + " not supported", null);
            Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer2, "createForMalformedContainer(...)");
            throw parserExceptionCreateForMalformedContainer2;
        }
        int i = -1;
        switch (id) {
            case 131:
                int i2 = (int) value;
                Track currentTrack = getCurrentTrack(id);
                if (i2 == 1) {
                    i = 2;
                } else if (i2 == 2) {
                    i = 1;
                } else if (i2 == 17) {
                    i = 3;
                } else if (i2 == 33) {
                    i = 5;
                }
                currentTrack.setType(i);
                return;
            case 136:
                getCurrentTrack(id).setFlagDefault(value == 1);
                return;
            case 155:
                this.blockDurationUs = scaleTimecodeToUs(value);
                return;
            case 159:
                getCurrentTrack(id).setChannelCount((int) value);
                return;
            case 176:
                getCurrentTrack(id).setWidth((int) value);
                return;
            case 179:
                if (this.sentSeekMap) {
                    return;
                }
                assertInCues(id);
                this.currentCueTimeUs = scaleTimecodeToUs(value);
                return;
            case 186:
                getCurrentTrack(id).setHeight((int) value);
                return;
            case ID_TRACK_NUMBER /* 215 */:
                getCurrentTrack(id).setNumber((int) value);
                return;
            case ID_TIME_CODE /* 231 */:
                this.clusterTimecodeUs = scaleTimecodeToUs(value);
                return;
            case 238:
                this.blockAdditionalId = (int) value;
                return;
            case ID_CUE_TRACK /* 247 */:
                if (this.sentSeekMap) {
                    return;
                }
                assertInCues(id);
                this.currentCueTrackNumber = (int) value;
                return;
            case ID_REFERENCE_BLOCK /* 251 */:
                this.blockHasReferenceBlock = true;
                return;
            case ID_BLOCK_ADD_ID_TYPE /* 16871 */:
                getCurrentTrack(id).setBlockAddIdType((int) value);
                return;
            case ID_CONTENT_COMPRESSION_ALGORITHM /* 16980 */:
                if (value == 3) {
                    return;
                }
                ParserException parserExceptionCreateForMalformedContainer3 = ParserException.createForMalformedContainer("ContentCompAlgo " + value + " not supported", null);
                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer3, "createForMalformedContainer(...)");
                throw parserExceptionCreateForMalformedContainer3;
            case ID_DOC_TYPE_READ_VERSION /* 17029 */:
                if (value < 1 || value > 2) {
                    ParserException parserExceptionCreateForMalformedContainer4 = ParserException.createForMalformedContainer("DocTypeReadVersion " + value + " not supported", null);
                    Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer4, "createForMalformedContainer(...)");
                    throw parserExceptionCreateForMalformedContainer4;
                }
                return;
            case ID_EBML_READ_VERSION /* 17143 */:
                if (value == 1) {
                    return;
                }
                ParserException parserExceptionCreateForMalformedContainer5 = ParserException.createForMalformedContainer("EBMLReadVersion " + value + " not supported", null);
                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer5, "createForMalformedContainer(...)");
                throw parserExceptionCreateForMalformedContainer5;
            case ID_CONTENT_ENCRYPTION_ALGORITHM /* 18401 */:
                if (value == 5) {
                    return;
                }
                ParserException parserExceptionCreateForMalformedContainer6 = ParserException.createForMalformedContainer("ContentEncAlgo " + value + " not supported", null);
                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer6, "createForMalformedContainer(...)");
                throw parserExceptionCreateForMalformedContainer6;
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /* 18408 */:
                if (value == 1) {
                    return;
                }
                ParserException parserExceptionCreateForMalformedContainer7 = ParserException.createForMalformedContainer("AESSettingsCipherMode " + value + " not supported", null);
                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer7, "createForMalformedContainer(...)");
                throw parserExceptionCreateForMalformedContainer7;
            case ID_SEEK_POSITION /* 21420 */:
                this.seekEntryPosition = value + this.segmentContentPosition;
                return;
            case ID_STEREO_MODE /* 21432 */:
                int i3 = (int) value;
                assertInTrackEntry(id);
                if (i3 == 0) {
                    Track track = this.currentTrack;
                    Intrinsics.checkNotNull(track);
                    track.setStereoMode(0);
                    return;
                }
                if (i3 == 1) {
                    Track track2 = this.currentTrack;
                    Intrinsics.checkNotNull(track2);
                    track2.setStereoMode(2);
                    return;
                } else if (i3 == 3) {
                    Track track3 = this.currentTrack;
                    Intrinsics.checkNotNull(track3);
                    track3.setStereoMode(1);
                    return;
                } else {
                    if (i3 != 15) {
                        return;
                    }
                    Track track4 = this.currentTrack;
                    Intrinsics.checkNotNull(track4);
                    track4.setStereoMode(3);
                    return;
                }
            case ID_DISPLAY_WIDTH /* 21680 */:
                getCurrentTrack(id).setDisplayWidth((int) value);
                return;
            case ID_DISPLAY_UNIT /* 21682 */:
                getCurrentTrack(id).setDisplayUnit((int) value);
                return;
            case ID_DISPLAY_HEIGHT /* 21690 */:
                getCurrentTrack(id).setDisplayHeight((int) value);
                return;
            case ID_FLAG_FORCED /* 21930 */:
                getCurrentTrack(id).setFlagForced(value == 1);
                return;
            case ID_COLOUR_BITS_PER_CHANNEL /* 21938 */:
                assertInTrackEntry(id);
                Track track5 = this.currentTrack;
                Intrinsics.checkNotNull(track5);
                track5.setHasColorInfo(true);
                Track track6 = this.currentTrack;
                Intrinsics.checkNotNull(track6);
                track6.setBitsPerChannel((int) value);
                return;
            case ID_MAX_BLOCK_ADDITION_ID /* 21998 */:
                getCurrentTrack(id).setMaxBlockAdditionId((int) value);
                return;
            case ID_CODEC_DELAY /* 22186 */:
                getCurrentTrack(id).setCodecDelayNs(value);
                return;
            case ID_SEEK_PRE_ROLL /* 22203 */:
                getCurrentTrack(id).setSeekPreRollNs(value);
                return;
            case ID_AUDIO_BIT_DEPTH /* 25188 */:
                getCurrentTrack(id).setAudioBitDepth((int) value);
                return;
            case ID_DISCARD_PADDING /* 30114 */:
                this.blockGroupDiscardPaddingNs = value;
                return;
            case ID_PROJECTION_TYPE /* 30321 */:
                assertInTrackEntry(id);
                int i4 = (int) value;
                if (i4 == 0) {
                    Track track7 = this.currentTrack;
                    Intrinsics.checkNotNull(track7);
                    track7.setProjectionType(0);
                    return;
                }
                if (i4 == 1) {
                    Track track8 = this.currentTrack;
                    Intrinsics.checkNotNull(track8);
                    track8.setProjectionType(1);
                    return;
                } else if (i4 == 2) {
                    Track track9 = this.currentTrack;
                    Intrinsics.checkNotNull(track9);
                    track9.setProjectionType(2);
                    return;
                } else {
                    if (i4 != 3) {
                        return;
                    }
                    Track track10 = this.currentTrack;
                    Intrinsics.checkNotNull(track10);
                    track10.setProjectionType(3);
                    return;
                }
            case ID_DEFAULT_DURATION /* 2352003 */:
                getCurrentTrack(id).setDefaultSampleDurationNs((int) value);
                return;
            case ID_TIMECODE_SCALE /* 2807729 */:
                this.timecodeScale = value;
                return;
            default:
                switch (id) {
                    case ID_COLOUR_RANGE /* 21945 */:
                        assertInTrackEntry(id);
                        int i5 = (int) value;
                        if (i5 == 1) {
                            Track track11 = this.currentTrack;
                            Intrinsics.checkNotNull(track11);
                            track11.setColorRange(2);
                            return;
                        } else {
                            if (i5 != 2) {
                                return;
                            }
                            Track track12 = this.currentTrack;
                            Intrinsics.checkNotNull(track12);
                            track12.setColorRange(1);
                            return;
                        }
                    case ID_COLOUR_TRANSFER /* 21946 */:
                        assertInTrackEntry(id);
                        int iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) value);
                        if (iIsoTransferCharacteristicsToColorTransfer != -1) {
                            Track track13 = this.currentTrack;
                            Intrinsics.checkNotNull(track13);
                            track13.setColorTransfer(iIsoTransferCharacteristicsToColorTransfer);
                            return;
                        }
                        return;
                    case ID_COLOUR_PRIMARIES /* 21947 */:
                        assertInTrackEntry(id);
                        Track track14 = this.currentTrack;
                        Intrinsics.checkNotNull(track14);
                        track14.setHasColorInfo(true);
                        int iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) value);
                        if (iIsoColorPrimariesToColorSpace != -1) {
                            Track track15 = this.currentTrack;
                            Intrinsics.checkNotNull(track15);
                            track15.setColorSpace(iIsoColorPrimariesToColorSpace);
                            return;
                        }
                        return;
                    case ID_MAX_CLL /* 21948 */:
                        getCurrentTrack(id).setMaxContentLuminance((int) value);
                        return;
                    case ID_MAX_FALL /* 21949 */:
                        getCurrentTrack(id).setMaxFrameAverageLuminance((int) value);
                        return;
                    default:
                        return;
                }
        }
    }

    protected final void floatElement(int id, double value) throws ParserException {
        if (id == 181) {
            getCurrentTrack(id).setSampleRate((int) value);
            return;
        }
        if (id == ID_DURATION) {
            this.durationTimecode = (long) value;
            return;
        }
        switch (id) {
            case ID_PRIMARY_R_CHROMATICITY_X /* 21969 */:
                getCurrentTrack(id).setPrimaryRChromaticityX((float) value);
                break;
            case ID_PRIMARY_R_CHROMATICITY_Y /* 21970 */:
                getCurrentTrack(id).setPrimaryRChromaticityY((float) value);
                break;
            case ID_PRIMARY_G_CHROMATICITY_X /* 21971 */:
                getCurrentTrack(id).setPrimaryGChromaticityX((float) value);
                break;
            case ID_PRIMARY_G_CHROMATICITY_Y /* 21972 */:
                getCurrentTrack(id).setPrimaryGChromaticityY((float) value);
                break;
            case ID_PRIMARY_B_CHROMATICITY_X /* 21973 */:
                getCurrentTrack(id).setPrimaryBChromaticityX((float) value);
                break;
            case ID_PRIMARY_B_CHROMATICITY_Y /* 21974 */:
                getCurrentTrack(id).setPrimaryBChromaticityY((float) value);
                break;
            case ID_WHITE_POINT_CHROMATICITY_X /* 21975 */:
                getCurrentTrack(id).setWhitePointChromaticityX((float) value);
                break;
            case ID_WHITE_POINT_CHROMATICITY_Y /* 21976 */:
                getCurrentTrack(id).setWhitePointChromaticityY((float) value);
                break;
            case ID_LUMNINANCE_MAX /* 21977 */:
                getCurrentTrack(id).setMaxMasteringLuminance((float) value);
                break;
            case ID_LUMNINANCE_MIN /* 21978 */:
                getCurrentTrack(id).setMinMasteringLuminance((float) value);
                break;
            default:
                switch (id) {
                    case ID_PROJECTION_POSE_YAW /* 30323 */:
                        getCurrentTrack(id).setProjectionPoseYaw((float) value);
                        break;
                    case ID_PROJECTION_POSE_PITCH /* 30324 */:
                        getCurrentTrack(id).setProjectionPosePitch((float) value);
                        break;
                    case ID_PROJECTION_POSE_ROLL /* 30325 */:
                        getCurrentTrack(id).setProjectionPoseRoll((float) value);
                        break;
                }
                break;
        }
    }

    protected final void stringElement(int id, String value) throws ParserException {
        Intrinsics.checkNotNullParameter(value, "value");
        if (id == 134) {
            getCurrentTrack(id).setCodecId(value);
            return;
        }
        if (id != ID_DOC_TYPE) {
            if (id == ID_NAME) {
                getCurrentTrack(id).setName(value);
                return;
            } else {
                if (id != ID_LANGUAGE) {
                    return;
                }
                getCurrentTrack(id).setLanguage(value);
                return;
            }
        }
        if (Intrinsics.areEqual(DOC_TYPE_WEBM, value) || Intrinsics.areEqual(DOC_TYPE_MATROSKA, value)) {
            return;
        }
        ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("DocType " + value + " not supported", null);
        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
        throw parserExceptionCreateForMalformedContainer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r23v0, types: [androidx.media3.extractor.mkv.UpdatedMatroskaExtractor] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v6 */
    protected final void binaryElement(int id, int contentSize, ExtractorInput input) throws IOException {
        ?? r6;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        int i5;
        int i6;
        int i7;
        int i8;
        long j;
        Intrinsics.checkNotNullParameter(input, "input");
        int i9 = 2;
        int i10 = 1;
        if (id != 161 && id != 163) {
            if (id == 165) {
                if (this.blockState != 2) {
                    return;
                }
                Track track = this.tracks.get(this.blockTrackNumber);
                Intrinsics.checkNotNullExpressionValue(track, "get(...)");
                handleBlockAdditionalData(track, this.blockAdditionalId, input, contentSize);
                return;
            }
            if (id == ID_BLOCK_ADD_ID_EXTRA_DATA) {
                handleBlockAddIDExtraData(getCurrentTrack(id), input, contentSize);
                return;
            }
            if (id == ID_CONTENT_COMPRESSION_SETTINGS) {
                assertInTrackEntry(id);
                Track track2 = this.currentTrack;
                Intrinsics.checkNotNull(track2);
                track2.setSampleStrippedBytes(new byte[contentSize]);
                Track track3 = this.currentTrack;
                Intrinsics.checkNotNull(track3);
                byte[] sampleStrippedBytes = track3.getSampleStrippedBytes();
                Intrinsics.checkNotNull(sampleStrippedBytes);
                input.readFully(sampleStrippedBytes, 0, contentSize);
                return;
            }
            if (id == ID_CONTENT_ENCRYPTION_KEY_ID) {
                byte[] bArr = new byte[contentSize];
                input.readFully(bArr, 0, contentSize);
                getCurrentTrack(id).setCryptoData(new TrackOutput.CryptoData(1, bArr, 0, 0));
                return;
            }
            if (id == ID_SEEK_ID) {
                Arrays.fill(this.seekEntryIdBytes.getData(), (byte) 0);
                input.readFully(this.seekEntryIdBytes.getData(), 4 - contentSize, contentSize);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
                return;
            }
            if (id == ID_CODEC_PRIVATE) {
                assertInTrackEntry(id);
                Track track4 = this.currentTrack;
                Intrinsics.checkNotNull(track4);
                track4.setCodecPrivate(new byte[contentSize]);
                Track track5 = this.currentTrack;
                Intrinsics.checkNotNull(track5);
                byte[] codecPrivate = track5.getCodecPrivate();
                Intrinsics.checkNotNull(codecPrivate);
                input.readFully(codecPrivate, 0, contentSize);
                return;
            }
            if (id == ID_PROJECTION_PRIVATE) {
                assertInTrackEntry(id);
                Track track6 = this.currentTrack;
                Intrinsics.checkNotNull(track6);
                track6.setProjectionData(new byte[contentSize]);
                Track track7 = this.currentTrack;
                Intrinsics.checkNotNull(track7);
                byte[] projectionData = track7.getProjectionData();
                Intrinsics.checkNotNull(projectionData);
                input.readFully(projectionData, 0, contentSize);
                return;
            }
            ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Unexpected id: " + id, null);
            Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
            throw parserExceptionCreateForMalformedContainer;
        }
        int i11 = 8;
        if (this.blockState == 0) {
            this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(input, false, true, 8);
            this.blockTrackNumberLength = this.varintReader.getLastLength();
            this.blockDurationUs = C.TIME_UNSET;
            this.blockState = 1;
            this.scratch.reset(0);
        }
        Track track8 = this.tracks.get(this.blockTrackNumber);
        if (track8 == null) {
            input.skipFully(contentSize - this.blockTrackNumberLength);
            this.blockState = 0;
            return;
        }
        track8.assertOutputInitialized();
        if (this.blockState == 1) {
            readScratch(input, 3);
            int i12 = (this.scratch.getData()[2] & 6) >> 1;
            byte b = 255;
            if (i12 == 0) {
                this.blockSampleCount = 1;
                int[] iArrEnsureArrayCapacity = INSTANCE.ensureArrayCapacity(this.blockSampleSizes, 1);
                this.blockSampleSizes = iArrEnsureArrayCapacity;
                iArrEnsureArrayCapacity[0] = (contentSize - this.blockTrackNumberLength) - 3;
                i2 = 2;
                i3 = 1;
            } else {
                readScratch(input, 4);
                int i13 = (this.scratch.getData()[3] & 255) + 1;
                this.blockSampleCount = i13;
                int[] iArrEnsureArrayCapacity2 = INSTANCE.ensureArrayCapacity(this.blockSampleSizes, i13);
                this.blockSampleSizes = iArrEnsureArrayCapacity2;
                if (i12 == 1) {
                    i2 = 2;
                    i3 = 1;
                    int i14 = 0;
                    int i15 = 0;
                    int i16 = 0;
                    int i17 = 4;
                    while (true) {
                        i4 = this.blockSampleCount;
                        int i18 = i4 - 1;
                        iArr = this.blockSampleSizes;
                        if (i15 >= i18) {
                            break;
                        }
                        iArr[i15] = i14;
                        while (true) {
                            i5 = i17 + 1;
                            readScratch(input, i5);
                            int i19 = this.scratch.getData()[i17] & 255;
                            int[] iArr2 = this.blockSampleSizes;
                            i6 = iArr2[i15] + i19;
                            iArr2[i15] = i6;
                            if (i19 != 255) {
                                break;
                            } else {
                                i17 = i5;
                            }
                        }
                        i16 += i6;
                        i15++;
                        i17 = i5;
                        i14 = 0;
                    }
                    iArr[i4 - 1] = ((contentSize - this.blockTrackNumberLength) - i17) - i16;
                } else if (i12 == 2) {
                    i2 = 2;
                    i3 = 1;
                    int i20 = (contentSize - this.blockTrackNumberLength) - 4;
                    int i21 = this.blockSampleCount;
                    Arrays.fill(iArrEnsureArrayCapacity2, 0, i21, i20 / i21);
                } else {
                    if (i12 != 3) {
                        ParserException parserExceptionCreateForMalformedContainer2 = ParserException.createForMalformedContainer("Unexpected lacing value: " + i12, null);
                        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer2, "createForMalformedContainer(...)");
                        throw parserExceptionCreateForMalformedContainer2;
                    }
                    int i22 = 4;
                    int i23 = 0;
                    int i24 = 0;
                    while (true) {
                        int i25 = this.blockSampleCount;
                        int i26 = i25 - 1;
                        i2 = i9;
                        int[] iArr3 = this.blockSampleSizes;
                        if (i23 < i26) {
                            iArr3[i23] = 0;
                            int i27 = i22 + 1;
                            readScratch(input, i27);
                            if (this.scratch.getData()[i22] == 0) {
                                ParserException parserExceptionCreateForMalformedContainer3 = ParserException.createForMalformedContainer("No valid varint length mask found", null);
                                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer3, "createForMalformedContainer(...)");
                                throw parserExceptionCreateForMalformedContainer3;
                            }
                            int i28 = 0;
                            while (true) {
                                if (i28 >= i11) {
                                    i7 = i10;
                                    i8 = i11;
                                    j = 0;
                                    break;
                                }
                                int i29 = i10 << (7 - i28);
                                i8 = i11;
                                if ((this.scratch.getData()[i22] & i29) != 0) {
                                    i27 += i28;
                                    readScratch(input, i27);
                                    int i30 = i22 + 1;
                                    i7 = i10;
                                    j = this.scratch.getData()[i22] & b & (~i29);
                                    while (true) {
                                        int i31 = i30;
                                        if (i31 >= i27) {
                                            break;
                                        }
                                        i30 = i31 + 1;
                                        j = (j << i8) | ((long) (this.scratch.getData()[i31] & b));
                                        b = 255;
                                    }
                                    if (i23 <= 0) {
                                        break;
                                    }
                                    j -= (1 << ((i28 * 7) + 6)) - 1;
                                    break;
                                }
                                i28++;
                                i11 = i8;
                                b = 255;
                            }
                            i22 = i27;
                            if (j < SieveCacheKt.NodeMetaAndPreviousMask || j > SieveCacheKt.NodeLinkMask) {
                                ParserException parserExceptionCreateForMalformedContainer4 = ParserException.createForMalformedContainer("EBML lacing sample size out of range.", null);
                                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer4, "createForMalformedContainer(...)");
                                throw parserExceptionCreateForMalformedContainer4;
                            }
                            int i32 = (int) j;
                            int[] iArr4 = this.blockSampleSizes;
                            if (i23 != 0) {
                                i32 += iArr4[i23 - 1];
                            }
                            iArr4[i23] = i32;
                            i24 += i32;
                            i23++;
                            i9 = i2;
                            i11 = i8;
                            i10 = i7;
                            b = 255;
                        } else {
                            i3 = i10;
                            iArr3[i25 - 1] = ((contentSize - this.blockTrackNumberLength) - i22) - i24;
                            break;
                        }
                    }
                }
            }
            this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((this.scratch.getData()[0] << 8) | (this.scratch.getData()[i3] & 255));
            this.blockFlags = (track8.getType() == i3 || (id == 163 && (this.scratch.getData()[i2] & 128) == 128)) ? 1 : 0;
            this.blockState = i2;
            r6 = 0;
            this.blockSampleIndex = 0;
            i = 163;
        } else {
            r6 = 0;
            i = 163;
        }
        if (id == i) {
            while (true) {
                int i33 = this.blockSampleIndex;
                if (i33 < this.blockSampleCount) {
                    int iWriteSampleData = writeSampleData(input, track8, this.blockSampleSizes[i33], r6);
                    Track track9 = track8;
                    commitSampleToOutput(track9, this.blockTimeUs + ((long) ((this.blockSampleIndex * track8.getDefaultSampleDurationNs()) / 1000)), this.blockFlags, iWriteSampleData, 0);
                    this.blockSampleIndex++;
                    track8 = track9;
                    r6 = 0;
                } else {
                    this.blockState = r6;
                    return;
                }
            }
        } else {
            while (true) {
                int i34 = this.blockSampleIndex;
                if (i34 >= this.blockSampleCount) {
                    return;
                }
                int[] iArr5 = this.blockSampleSizes;
                iArr5[i34] = writeSampleData(input, track8, iArr5[i34], true);
                this.blockSampleIndex++;
            }
        }
    }

    protected final void handleBlockAddIDExtraData(Track track, ExtractorInput input, int contentSize) throws IOException {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(input, "input");
        if (track.getBlockAddIdType() == 1685485123 || track.getBlockAddIdType() == 1685480259) {
            track.setDolbyVisionConfigBytes(new byte[contentSize]);
            byte[] dolbyVisionConfigBytes = track.getDolbyVisionConfigBytes();
            Intrinsics.checkNotNull(dolbyVisionConfigBytes);
            input.readFully(dolbyVisionConfigBytes, 0, contentSize);
            return;
        }
        input.skipFully(contentSize);
    }

    protected final void handleBlockAdditionalData(Track track, int blockAdditionalId, ExtractorInput input, int contentSize) throws IOException {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(input, "input");
        if (blockAdditionalId == 4 && Intrinsics.areEqual(CODEC_ID_VP9, track.getCodecId())) {
            this.supplementalData.reset(contentSize);
            input.readFully(this.supplementalData.getData(), 0, contentSize);
        } else {
            input.skipFully(contentSize);
        }
    }

    private final void assertInTrackEntry(int id) throws ParserException {
        if (this.currentTrack != null) {
            return;
        }
        ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Element " + id + " must be in a TrackEntry", null);
        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
        throw parserExceptionCreateForMalformedContainer;
    }

    private final void assertInCues(int id) throws ParserException {
        if (this.inCuesElement) {
            return;
        }
        ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Element " + id + " must be in a Cues", null);
        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
        throw parserExceptionCreateForMalformedContainer;
    }

    protected final Track getCurrentTrack(int currentElementId) throws ParserException {
        assertInTrackEntry(currentElementId);
        Track track = this.currentTrack;
        Intrinsics.checkNotNull(track);
        return track;
    }

    private final void commitSampleToOutput(Track track, long timeUs, int flags, int size, int offset) {
        int iLimit;
        if (track.getTrueHdSampleRechunker() != null) {
            TrueHdSampleRechunker trueHdSampleRechunker = track.getTrueHdSampleRechunker();
            Intrinsics.checkNotNull(trueHdSampleRechunker);
            TrackOutput output = track.getOutput();
            Intrinsics.checkNotNull(output);
            trueHdSampleRechunker.sampleMetadata(output, timeUs, flags, size, offset, track.getCryptoData());
        } else {
            if (Intrinsics.areEqual(CODEC_ID_SUBRIP, track.getCodecId()) || Intrinsics.areEqual(CODEC_ID_ASS, track.getCodecId()) || Intrinsics.areEqual(CODEC_ID_SSA, track.getCodecId()) || Intrinsics.areEqual(CODEC_ID_VTT, track.getCodecId())) {
                if (this.blockSampleCount > 1) {
                    Log.w(TAG, "Skipping subtitle sample in laced block.");
                } else if (this.blockDurationUs == C.TIME_UNSET) {
                    Log.w(TAG, "Skipping subtitle sample with no duration.");
                } else {
                    Companion companion = INSTANCE;
                    String codecId = track.getCodecId();
                    Intrinsics.checkNotNull(codecId);
                    long j = this.blockDurationUs;
                    byte[] data = this.subtitleSample.getData();
                    Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                    companion.setSubtitleEndTime(codecId, j, data);
                    int iLimit2 = this.subtitleSample.limit();
                    for (int position = this.subtitleSample.getPosition(); position < iLimit2; position++) {
                        if (this.subtitleSample.getData()[position] == 0) {
                            this.subtitleSample.setLimit(position);
                            break;
                        }
                    }
                    TrackOutput output2 = track.getOutput();
                    Intrinsics.checkNotNull(output2);
                    ParsableByteArray parsableByteArray = this.subtitleSample;
                    output2.sampleData(parsableByteArray, parsableByteArray.limit());
                    iLimit = size + this.subtitleSample.limit();
                }
                iLimit = size;
            } else {
                iLimit = size;
            }
            if ((flags & 268435456) != 0) {
                int i = this.blockSampleCount;
                ParsableByteArray parsableByteArray2 = this.supplementalData;
                if (i > 1) {
                    parsableByteArray2.reset(0);
                } else {
                    int iLimit3 = parsableByteArray2.limit();
                    TrackOutput output3 = track.getOutput();
                    Intrinsics.checkNotNull(output3);
                    output3.sampleData(this.supplementalData, iLimit3, 2);
                    iLimit += iLimit3;
                }
            }
            TrackOutput output4 = track.getOutput();
            Intrinsics.checkNotNull(output4);
            output4.sampleMetadata(timeUs, flags, iLimit, offset, track.getCryptoData());
        }
        this.haveOutputSample = true;
    }

    private final void readScratch(ExtractorInput input, int requiredLength) throws IOException {
        if (this.scratch.limit() >= requiredLength) {
            return;
        }
        if (this.scratch.capacity() < requiredLength) {
            ParsableByteArray parsableByteArray = this.scratch;
            parsableByteArray.ensureCapacity((int) Math.max(parsableByteArray.capacity() * 2, requiredLength));
        }
        input.readFully(this.scratch.getData(), this.scratch.limit(), requiredLength - this.scratch.limit());
        this.scratch.setLimit(requiredLength);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x018b  */
    private final int writeSampleData(ExtractorInput input, Track track, int size, boolean isBlockGroup) throws IOException {
        if (Intrinsics.areEqual(CODEC_ID_SUBRIP, track.getCodecId())) {
            writeSubtitleSampleData(input, SUBRIP_PREFIX, size);
            return finishWriteSampleData();
        }
        if (Intrinsics.areEqual(CODEC_ID_ASS, track.getCodecId()) || Intrinsics.areEqual(CODEC_ID_SSA, track.getCodecId())) {
            writeSubtitleSampleData(input, SSA_PREFIX, size);
            return finishWriteSampleData();
        }
        if (Intrinsics.areEqual(CODEC_ID_VTT, track.getCodecId())) {
            writeSubtitleSampleData(input, VTT_PREFIX, size);
            return finishWriteSampleData();
        }
        if (track.getWaitingForDtsAnalysis()) {
            Preconditions.checkNotNull(track.getFormat());
            if (DtsUtil.isSampleDtsHd(input, size)) {
                Format format = track.getFormat();
                Intrinsics.checkNotNull(format);
                track.setFormat(format.buildUpon().setSampleMimeType("audio/vnd.dts.hd").build());
            }
            TrackOutput output = track.getOutput();
            Intrinsics.checkNotNull(output);
            Format format2 = track.getFormat();
            Intrinsics.checkNotNull(format2);
            output.format(format2);
            track.setWaitingForDtsAnalysis(false);
            maybeEndTracks();
        }
        TrackOutput output2 = track.getOutput();
        if (!this.sampleEncodingHandled) {
            if (track.getHasContentEncryption()) {
                this.blockFlags &= -1073741825;
                if (!this.sampleSignalByteRead) {
                    input.readFully(this.scratch.getData(), 0, 1);
                    this.sampleBytesRead++;
                    if ((this.scratch.getData()[0] & 128) == 128) {
                        ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Extension bit is set in signal byte", null);
                        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
                        throw parserExceptionCreateForMalformedContainer;
                    }
                    this.sampleSignalByte = this.scratch.getData()[0];
                    this.sampleSignalByteRead = true;
                }
                byte b = this.sampleSignalByte;
                if ((b & 1) == 1) {
                    boolean z = (b & 2) == 2;
                    this.blockFlags |= 1073741824;
                    if (!this.sampleInitializationVectorRead) {
                        input.readFully(this.encryptionInitializationVector.getData(), 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        this.scratch.getData()[0] = (byte) ((z ? 128 : 0) | 8);
                        this.scratch.setPosition(0);
                        Intrinsics.checkNotNull(output2);
                        output2.sampleData(this.scratch, 1, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        output2.sampleData(this.encryptionInitializationVector, 8, 1);
                        this.sampleBytesWritten += 8;
                    }
                    if (z) {
                        if (!this.samplePartitionCountRead) {
                            input.readFully(this.scratch.getData(), 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int i = this.samplePartitionCount * 4;
                        this.scratch.reset(i);
                        input.readFully(this.scratch.getData(), 0, i);
                        this.sampleBytesRead += i;
                        short s = (short) ((this.samplePartitionCount / 2) + 1);
                        int i2 = (s * 6) + 2;
                        ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                        if (byteBuffer != null) {
                            Intrinsics.checkNotNull(byteBuffer);
                            if (byteBuffer.capacity() < i2) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i2);
                            }
                        } else {
                            this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i2);
                        }
                        ByteBuffer byteBuffer2 = this.encryptionSubsampleDataBuffer;
                        Intrinsics.checkNotNull(byteBuffer2);
                        byteBuffer2.position(0);
                        ByteBuffer byteBuffer3 = this.encryptionSubsampleDataBuffer;
                        Intrinsics.checkNotNull(byteBuffer3);
                        byteBuffer3.putShort(s);
                        int i3 = this.samplePartitionCount;
                        int i4 = 0;
                        int i5 = 0;
                        while (i4 < i3) {
                            int unsignedIntToInt = this.scratch.readUnsignedIntToInt();
                            int i6 = i4 % 2;
                            ByteBuffer byteBuffer4 = this.encryptionSubsampleDataBuffer;
                            if (i6 == 0) {
                                Intrinsics.checkNotNull(byteBuffer4);
                                byteBuffer4.putShort((short) (unsignedIntToInt - i5));
                            } else {
                                Intrinsics.checkNotNull(byteBuffer4);
                                byteBuffer4.putInt(unsignedIntToInt - i5);
                            }
                            i4++;
                            i5 = unsignedIntToInt;
                        }
                        int i7 = (size - this.sampleBytesRead) - i5;
                        int i8 = this.samplePartitionCount % 2;
                        ByteBuffer byteBuffer5 = this.encryptionSubsampleDataBuffer;
                        if (i8 == 1) {
                            Intrinsics.checkNotNull(byteBuffer5);
                            byteBuffer5.putInt(i7);
                        } else {
                            Intrinsics.checkNotNull(byteBuffer5);
                            byteBuffer5.putShort((short) i7);
                            ByteBuffer byteBuffer6 = this.encryptionSubsampleDataBuffer;
                            Intrinsics.checkNotNull(byteBuffer6);
                            byteBuffer6.putInt(0);
                        }
                        ParsableByteArray parsableByteArray = this.encryptionSubsampleData;
                        ByteBuffer byteBuffer7 = this.encryptionSubsampleDataBuffer;
                        Intrinsics.checkNotNull(byteBuffer7);
                        parsableByteArray.reset(byteBuffer7.array(), i2);
                        Intrinsics.checkNotNull(output2);
                        output2.sampleData(this.encryptionSubsampleData, i2, 1);
                        this.sampleBytesWritten += i2;
                    }
                }
            } else if (track.getSampleStrippedBytes() != null) {
                ParsableByteArray parsableByteArray2 = this.sampleStrippedBytes;
                byte[] sampleStrippedBytes = track.getSampleStrippedBytes();
                Intrinsics.checkNotNull(sampleStrippedBytes);
                byte[] sampleStrippedBytes2 = track.getSampleStrippedBytes();
                Intrinsics.checkNotNull(sampleStrippedBytes2);
                parsableByteArray2.reset(sampleStrippedBytes, sampleStrippedBytes2.length);
            }
            if (track.samplesHaveSupplementalData(isBlockGroup)) {
                this.blockFlags |= 268435456;
                this.supplementalData.reset(0);
                int iLimit = (this.sampleStrippedBytes.limit() + size) - this.sampleBytesRead;
                this.scratch.reset(4);
                this.scratch.getData()[0] = (byte) ((iLimit >> 24) & 255);
                this.scratch.getData()[1] = (byte) ((iLimit >> 16) & 255);
                this.scratch.getData()[2] = (byte) ((iLimit >> 8) & 255);
                this.scratch.getData()[3] = (byte) (iLimit & 255);
                Intrinsics.checkNotNull(output2);
                output2.sampleData(this.scratch, 4, 2);
                this.sampleBytesWritten += 4;
            }
            this.sampleEncodingHandled = true;
        }
        int iLimit2 = size + this.sampleStrippedBytes.limit();
        if (Intrinsics.areEqual(CODEC_ID_H264, track.getCodecId()) || Intrinsics.areEqual(CODEC_ID_H265, track.getCodecId())) {
            byte[] data = this.nalLength.getData();
            Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
            data[0] = 0;
            data[1] = 0;
            data[2] = 0;
            int nalUnitLengthFieldLength = track.getNalUnitLengthFieldLength();
            int nalUnitLengthFieldLength2 = 4 - track.getNalUnitLengthFieldLength();
            while (this.sampleBytesRead < iLimit2) {
                if (this.sampleCurrentNalBytesRemaining == 0) {
                    writeToTarget(input, data, nalUnitLengthFieldLength2, nalUnitLengthFieldLength);
                    this.sampleBytesRead += nalUnitLengthFieldLength;
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    Intrinsics.checkNotNull(output2);
                    output2.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                } else {
                    Intrinsics.checkNotNull(output2);
                    int iWriteToOutput = writeToOutput(input, output2, this.sampleCurrentNalBytesRemaining);
                    this.sampleBytesRead += iWriteToOutput;
                    this.sampleBytesWritten += iWriteToOutput;
                    this.sampleCurrentNalBytesRemaining -= iWriteToOutput;
                }
            }
        } else {
            if (track.getTrueHdSampleRechunker() != null) {
                Preconditions.checkState(this.sampleStrippedBytes.limit() == 0);
                TrueHdSampleRechunker trueHdSampleRechunker = track.getTrueHdSampleRechunker();
                Intrinsics.checkNotNull(trueHdSampleRechunker);
                trueHdSampleRechunker.startSample(input);
            }
            while (this.sampleBytesRead < iLimit2) {
                Intrinsics.checkNotNull(output2);
                int iWriteToOutput2 = writeToOutput(input, output2, iLimit2 - this.sampleBytesRead);
                this.sampleBytesRead += iWriteToOutput2;
                this.sampleBytesWritten += iWriteToOutput2;
            }
        }
        if (Intrinsics.areEqual(CODEC_ID_VORBIS, track.getCodecId())) {
            this.vorbisNumPageSamples.setPosition(0);
            Intrinsics.checkNotNull(output2);
            output2.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
        return finishWriteSampleData();
    }

    private final int finishWriteSampleData() {
        int i = this.sampleBytesWritten;
        resetWriteSampleData();
        return i;
    }

    private final void resetWriteSampleData() {
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

    private final void writeSubtitleSampleData(ExtractorInput input, byte[] samplePrefix, int size) throws IOException {
        int length = samplePrefix.length + size;
        int iCapacity = this.subtitleSample.capacity();
        ParsableByteArray parsableByteArray = this.subtitleSample;
        if (iCapacity < length) {
            byte[] bArrCopyOf = Arrays.copyOf(samplePrefix, length + size);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            parsableByteArray.reset(bArrCopyOf);
        } else {
            System.arraycopy(samplePrefix, 0, parsableByteArray.getData(), 0, samplePrefix.length);
        }
        input.readFully(this.subtitleSample.getData(), samplePrefix.length, size);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    private final void writeToTarget(ExtractorInput input, byte[] target, int offset, int length) throws IOException {
        int iMin = (int) Math.min(length, this.sampleStrippedBytes.bytesLeft());
        input.readFully(target, offset + iMin, length - iMin);
        if (iMin > 0) {
            this.sampleStrippedBytes.readBytes(target, offset, iMin);
        }
    }

    private final int writeToOutput(ExtractorInput input, TrackOutput output, int length) throws IOException {
        int iBytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (iBytesLeft > 0) {
            int iMin = (int) Math.min(length, iBytesLeft);
            output.sampleData(this.sampleStrippedBytes, iMin);
            return iMin;
        }
        return output.sampleData((DataReader) input, length, false);
    }

    private final boolean maybeSeekForCues(PositionHolder seekPosition, long currentPosition) {
        if (this.seekForSeekContent) {
            Preconditions.checkArgument(!this.pendingSeekHeads.isEmpty(), "Illegal value of seekForSeekContent", new Object[0]);
            ArrayList<Long> arrayList = this.pendingSeekHeads;
            Long lRemove = arrayList.remove(arrayList.size() - 1);
            Intrinsics.checkNotNullExpressionValue(lRemove, "removeAt(...)");
            seekPosition.position = lRemove.longValue();
            this.seekForSeekContent = false;
            if (this.seekPositionAfterSeekingForHead == -1) {
                this.seekPositionAfterSeekingForHead = currentPosition;
            }
            return true;
        }
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = currentPosition;
            seekPosition.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        boolean z = this.sentSeekMap;
        if (z) {
            long j = this.seekPositionAfterBuildingCues;
            if (j != -1) {
                seekPosition.position = j;
                this.seekPositionAfterBuildingCues = -1L;
                return true;
            }
        }
        if (z) {
            long j2 = this.seekPositionAfterSeekingForHead;
            if (j2 != -1) {
                seekPosition.position = j2;
                this.seekPositionAfterSeekingForHead = -1L;
                return true;
            }
        }
        return false;
    }

    private final long scaleTimecodeToUs(long unscaledTimecode) throws ParserException {
        long j = this.timecodeScale;
        if (j == C.TIME_UNSET) {
            ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", null);
            Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
            throw parserExceptionCreateForMalformedContainer;
        }
        return Util.scaleLargeTimestamp(unscaledTimecode, j, 1000L);
    }

    private final void assertInitialized() {
        Preconditions.checkNotNull(this.extractorOutput);
    }

    private final void maybeEndTracks() {
        if (this.pendingEndTracks) {
            int size = this.tracks.size();
            for (int i = 0; i < size; i++) {
                if (this.tracks.valueAt(i).getWaitingForDtsAnalysis()) {
                    return;
                }
            }
            ((ExtractorOutput) Preconditions.checkNotNull(this.extractorOutput)).endTracks();
            this.pendingEndTracks = false;
        }
    }

    /* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¨\u0006\u0019"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$InnerEbmlProcessor;", "Landroidx/media3/extractor/mkv/EbmlProcessor;", "<init>", "(Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor;)V", "getElementType", "", "id", "isLevel1Element", "", "startMasterElement", "", "contentPosition", "", "contentSize", "endMasterElement", "integerElement", ES6Iterator.VALUE_PROPERTY, "floatElement", "", "stringElement", "", "binaryElement", "contentsSize", TvContractCompat.PARAM_INPUT, "Landroidx/media3/extractor/ExtractorInput;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class InnerEbmlProcessor implements EbmlProcessor {
        public InnerEbmlProcessor() {
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public int getElementType(int id) {
            return UpdatedMatroskaExtractor.this.getElementType(id);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public boolean isLevel1Element(int id) {
            return UpdatedMatroskaExtractor.this.isLevel1Element(id);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void startMasterElement(int id, long contentPosition, long contentSize) throws ParserException {
            UpdatedMatroskaExtractor.this.startMasterElement(id, contentPosition, contentSize);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void endMasterElement(int id) throws ParserException {
            UpdatedMatroskaExtractor.this.endMasterElement(id);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void integerElement(int id, long value) throws ParserException {
            UpdatedMatroskaExtractor.this.integerElement(id, value);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void floatElement(int id, double value) throws ParserException {
            UpdatedMatroskaExtractor.this.floatElement(id, value);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void stringElement(int id, String value) throws ParserException {
            Intrinsics.checkNotNullParameter(value, "value");
            UpdatedMatroskaExtractor.this.stringElement(id, value);
        }

        @Override // androidx.media3.extractor.mkv.EbmlProcessor
        public void binaryElement(int id, int contentsSize, ExtractorInput input) throws IOException {
            Intrinsics.checkNotNullParameter(input, "input");
            UpdatedMatroskaExtractor.this.binaryElement(id, contentsSize, input);
        }
    }

    /* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
    @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0007\n\u0002\bM\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\b\u0004\u0018\u0000 Ý\u00012\u00020\u0001:\u0002Ý\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010É\u0001\u001a\u00030Ê\u00012\u0007\u0010Ë\u0001\u001a\u00020\u0013J\b\u0010Ì\u0001\u001a\u00030Ê\u0001J\b\u0010Í\u0001\u001a\u00030Ê\u0001J\u0010\u0010Î\u0001\u001a\u00020\u00052\u0007\u0010Ï\u0001\u001a\u00020\u0005J>\u0010Ò\u0001\u001a\u00030Ê\u00012\u0016\u0010Ó\u0001\u001a\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u00030Ö\u00010Õ\u00010Ô\u00012\b\u0010×\u0001\u001a\u00030 \u00012\b\u0010Ø\u0001\u001a\u00030 \u00012\b\u0010Ù\u0001\u001a\u00030 \u0001J9\u0010Ú\u0001\u001a\u00030 \u00012\u000f\u0010Û\u0001\u001a\n\u0012\u0005\u0012\u00030Ö\u00010Õ\u00012\b\u0010×\u0001\u001a\u00030 \u00012\b\u0010Ø\u0001\u001a\u00030 \u00012\b\u0010Ù\u0001\u001a\u00030 \u0001H\u0002J\b\u0010Ü\u0001\u001a\u00030Ê\u0001J\u0010\u00104\u001a\u00020(2\u0006\u0010\u000f\u001a\u00020\nH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\u001e\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\u001a\u0010!\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\u001a\u0010$\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u00103\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010*\"\u0004\b5\u0010,R\u001c\u00106\u001a\u0004\u0018\u000107X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0015\"\u0004\b>\u0010\u0017R\u001a\u0010?\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0015\"\u0004\bA\u0010\u0017R\u001a\u0010B\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0015\"\u0004\bD\u0010\u0017R\u001a\u0010E\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0015\"\u0004\bG\u0010\u0017R\u001a\u0010H\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0015\"\u0004\bJ\u0010\u0017R\u001a\u0010K\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0015\"\u0004\bM\u0010\u0017R\u001a\u0010N\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0015\"\u0004\bP\u0010\u0017R\u001a\u0010Q\u001a\u00020RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001a\u0010W\u001a\u00020RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010T\"\u0004\bY\u0010VR\u001a\u0010Z\u001a\u00020RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010T\"\u0004\b\\\u0010VR\u001c\u0010]\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010*\"\u0004\b_\u0010,R\u001a\u0010`\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\u0015\"\u0004\bb\u0010\u0017R\u001a\u0010c\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010\u0006\"\u0004\be\u0010\bR\u001a\u0010f\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010\u0015\"\u0004\bh\u0010\u0017R\u001a\u0010i\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010\u0015\"\u0004\bk\u0010\u0017R\u001a\u0010l\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u0015\"\u0004\bn\u0010\u0017R\u001a\u0010o\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\u0015\"\u0004\bq\u0010\u0017R\u001a\u0010r\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\u0015\"\u0004\bt\u0010\u0017R\u001a\u0010u\u001a\u00020RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010T\"\u0004\bw\u0010VR\u001a\u0010x\u001a\u00020RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010T\"\u0004\bz\u0010VR\u001a\u0010{\u001a\u00020RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010T\"\u0004\b}\u0010VR\u001b\u0010~\u001a\u00020RX\u0086\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010T\"\u0005\b\u0080\u0001\u0010VR\u001d\u0010\u0081\u0001\u001a\u00020RX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010T\"\u0005\b\u0083\u0001\u0010VR\u001d\u0010\u0084\u0001\u001a\u00020RX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010T\"\u0005\b\u0086\u0001\u0010VR\u001d\u0010\u0087\u0001\u001a\u00020RX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010T\"\u0005\b\u0089\u0001\u0010VR\u001d\u0010\u008a\u0001\u001a\u00020RX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010T\"\u0005\b\u008c\u0001\u0010VR\u001d\u0010\u008d\u0001\u001a\u00020RX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010T\"\u0005\b\u008f\u0001\u0010VR\u001d\u0010\u0090\u0001\u001a\u00020RX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010T\"\u0005\b\u0092\u0001\u0010VR\u001f\u0010\u0093\u0001\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010*\"\u0005\b\u0095\u0001\u0010,R\u001d\u0010\u0096\u0001\u001a\u00020\u0013X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0015\"\u0005\b\u0098\u0001\u0010\u0017R\u001d\u0010\u0099\u0001\u001a\u00020\u0013X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010\u0015\"\u0005\b\u009b\u0001\u0010\u0017R\u001d\u0010\u009c\u0001\u001a\u00020\u0013X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010\u0015\"\u0005\b\u009e\u0001\u0010\u0017R \u0010\u009f\u0001\u001a\u00030 \u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¡\u0001\u0010¢\u0001\"\u0006\b£\u0001\u0010¤\u0001R \u0010¥\u0001\u001a\u00030 \u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¦\u0001\u0010¢\u0001\"\u0006\b§\u0001\u0010¤\u0001R\"\u0010¨\u0001\u001a\u0005\u0018\u00010©\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bª\u0001\u0010«\u0001\"\u0006\b¬\u0001\u0010\u00ad\u0001R\u001d\u0010®\u0001\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¯\u0001\u0010\u0006\"\u0005\b°\u0001\u0010\bR\u001d\u0010±\u0001\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b²\u0001\u0010\u0006\"\u0005\b³\u0001\u0010\bR\u001d\u0010´\u0001\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bµ\u0001\u0010\u0006\"\u0005\b¶\u0001\u0010\bR\u001d\u0010·\u0001\u001a\u00020\nX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¸\u0001\u0010\f\"\u0005\b¹\u0001\u0010\u000eR\"\u0010º\u0001\u001a\u0005\u0018\u00010»\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¼\u0001\u0010½\u0001\"\u0006\b¾\u0001\u0010¿\u0001R\"\u0010À\u0001\u001a\u0005\u0018\u00010Á\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÂ\u0001\u0010Ã\u0001\"\u0006\bÄ\u0001\u0010Å\u0001R\u001d\u0010Æ\u0001\u001a\u00020\u0013X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÇ\u0001\u0010\u0015\"\u0005\bÈ\u0001\u0010\u0017R\u0018\u0010Ð\u0001\u001a\u0004\u0018\u00010(8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÑ\u0001\u0010*¨\u0006Þ\u0001"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$Track;", "", "<init>", "()V", "isWebm", "", "()Z", "setWebm", "(Z)V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "codecId", "getCodecId", "setCodecId", "number", "", "getNumber", "()I", "setNumber", "(I)V", "type", "getType", "setType", "defaultSampleDurationNs", "getDefaultSampleDurationNs", "setDefaultSampleDurationNs", "maxBlockAdditionId", "getMaxBlockAdditionId", "setMaxBlockAdditionId", "blockAddIdType", "getBlockAddIdType", "setBlockAddIdType", "hasContentEncryption", "getHasContentEncryption", "setHasContentEncryption", "sampleStrippedBytes", "", "getSampleStrippedBytes", "()[B", "setSampleStrippedBytes", "([B)V", "cryptoData", "Landroidx/media3/extractor/TrackOutput$CryptoData;", "getCryptoData", "()Landroidx/media3/extractor/TrackOutput$CryptoData;", "setCryptoData", "(Landroidx/media3/extractor/TrackOutput$CryptoData;)V", "codecPrivate", "getCodecPrivate", "setCodecPrivate", "drmInitData", "Landroidx/media3/common/DrmInitData;", "getDrmInitData", "()Landroidx/media3/common/DrmInitData;", "setDrmInitData", "(Landroidx/media3/common/DrmInitData;)V", "width", "getWidth", "setWidth", "height", "getHeight", "setHeight", "bitsPerChannel", "getBitsPerChannel", "setBitsPerChannel", "displayWidth", "getDisplayWidth", "setDisplayWidth", "displayHeight", "getDisplayHeight", "setDisplayHeight", "displayUnit", "getDisplayUnit", "setDisplayUnit", "projectionType", "getProjectionType", "setProjectionType", "projectionPoseYaw", "", "getProjectionPoseYaw", "()F", "setProjectionPoseYaw", "(F)V", "projectionPosePitch", "getProjectionPosePitch", "setProjectionPosePitch", "projectionPoseRoll", "getProjectionPoseRoll", "setProjectionPoseRoll", "projectionData", "getProjectionData", "setProjectionData", "stereoMode", "getStereoMode", "setStereoMode", "hasColorInfo", "getHasColorInfo", "setHasColorInfo", "colorSpace", "getColorSpace", "setColorSpace", "colorTransfer", "getColorTransfer", "setColorTransfer", "colorRange", "getColorRange", "setColorRange", "maxContentLuminance", "getMaxContentLuminance", "setMaxContentLuminance", "maxFrameAverageLuminance", "getMaxFrameAverageLuminance", "setMaxFrameAverageLuminance", "primaryRChromaticityX", "getPrimaryRChromaticityX", "setPrimaryRChromaticityX", "primaryRChromaticityY", "getPrimaryRChromaticityY", "setPrimaryRChromaticityY", "primaryGChromaticityX", "getPrimaryGChromaticityX", "setPrimaryGChromaticityX", "primaryGChromaticityY", "getPrimaryGChromaticityY", "setPrimaryGChromaticityY", "primaryBChromaticityX", "getPrimaryBChromaticityX", "setPrimaryBChromaticityX", "primaryBChromaticityY", "getPrimaryBChromaticityY", "setPrimaryBChromaticityY", "whitePointChromaticityX", "getWhitePointChromaticityX", "setWhitePointChromaticityX", "whitePointChromaticityY", "getWhitePointChromaticityY", "setWhitePointChromaticityY", "maxMasteringLuminance", "getMaxMasteringLuminance", "setMaxMasteringLuminance", "minMasteringLuminance", "getMinMasteringLuminance", "setMinMasteringLuminance", "dolbyVisionConfigBytes", "getDolbyVisionConfigBytes", "setDolbyVisionConfigBytes", "channelCount", "getChannelCount", "setChannelCount", "audioBitDepth", "getAudioBitDepth", "setAudioBitDepth", "sampleRate", "getSampleRate", "setSampleRate", "codecDelayNs", "", "getCodecDelayNs", "()J", "setCodecDelayNs", "(J)V", "seekPreRollNs", "getSeekPreRollNs", "setSeekPreRollNs", "trueHdSampleRechunker", "Landroidx/media3/extractor/TrueHdSampleRechunker;", "getTrueHdSampleRechunker", "()Landroidx/media3/extractor/TrueHdSampleRechunker;", "setTrueHdSampleRechunker", "(Landroidx/media3/extractor/TrueHdSampleRechunker;)V", "waitingForDtsAnalysis", "getWaitingForDtsAnalysis", "setWaitingForDtsAnalysis", "flagForced", "getFlagForced", "setFlagForced", "flagDefault", "getFlagDefault", "setFlagDefault", "language", "getLanguage", "setLanguage", "output", "Landroidx/media3/extractor/TrackOutput;", "getOutput", "()Landroidx/media3/extractor/TrackOutput;", "setOutput", "(Landroidx/media3/extractor/TrackOutput;)V", "format", "Landroidx/media3/common/Format;", "getFormat", "()Landroidx/media3/common/Format;", "setFormat", "(Landroidx/media3/common/Format;)V", "nalUnitLengthFieldLength", "getNalUnitLengthFieldLength", "setNalUnitLengthFieldLength", "initializeFormat", "", "trackId", "outputPendingSampleMetadata", "reset", "samplesHaveSupplementalData", "isBlockGroup", "hdrStaticInfo", "getHdrStaticInfo", "maybeAddThumbnailMetadata", "perTrackCues", "Landroid/util/SparseArray;", "", "Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$MatroskaSeekMap$CuePointData;", "durationUs", "segmentContentPosition", "segmentContentSize", "findBestThumbnailPresentationTimeUs", "cuePoints", "assertOutputInitialized", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    protected static final class Track {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        private int blockAddIdType;
        private long codecDelayNs;
        private String codecId;
        private byte[] codecPrivate;
        private TrackOutput.CryptoData cryptoData;
        private int defaultSampleDurationNs;
        private int displayUnit;
        private byte[] dolbyVisionConfigBytes;
        private DrmInitData drmInitData;
        private boolean flagForced;
        private Format format;
        private boolean hasColorInfo;
        private boolean hasContentEncryption;
        private boolean isWebm;
        private int maxBlockAdditionId;
        private int nalUnitLengthFieldLength;
        private String name;
        private int number;
        private TrackOutput output;
        private byte[] projectionData;
        private float projectionPosePitch;
        private float projectionPoseRoll;
        private float projectionPoseYaw;
        private byte[] sampleStrippedBytes;
        private long seekPreRollNs;
        private TrueHdSampleRechunker trueHdSampleRechunker;
        private int type;
        private boolean waitingForDtsAnalysis;
        private int width = -1;
        private int height = -1;
        private int bitsPerChannel = -1;
        private int displayWidth = -1;
        private int displayHeight = -1;
        private int projectionType = -1;
        private int stereoMode = -1;
        private int colorSpace = -1;
        private int colorTransfer = -1;
        private int colorRange = -1;
        private int maxContentLuminance = 1000;
        private int maxFrameAverageLuminance = 200;
        private float primaryRChromaticityX = -1.0f;
        private float primaryRChromaticityY = -1.0f;
        private float primaryGChromaticityX = -1.0f;
        private float primaryGChromaticityY = -1.0f;
        private float primaryBChromaticityX = -1.0f;
        private float primaryBChromaticityY = -1.0f;
        private float whitePointChromaticityX = -1.0f;
        private float whitePointChromaticityY = -1.0f;
        private float maxMasteringLuminance = -1.0f;
        private float minMasteringLuminance = -1.0f;
        private int channelCount = 1;
        private int audioBitDepth = -1;
        private int sampleRate = 8000;
        private boolean flagDefault = true;
        private String language = "eng";

        /* JADX INFO: renamed from: isWebm, reason: from getter */
        public final boolean getIsWebm() {
            return this.isWebm;
        }

        public final void setWebm(boolean z) {
            this.isWebm = z;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getCodecId() {
            return this.codecId;
        }

        public final void setCodecId(String str) {
            this.codecId = str;
        }

        public final int getNumber() {
            return this.number;
        }

        public final void setNumber(int i) {
            this.number = i;
        }

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }

        public final int getDefaultSampleDurationNs() {
            return this.defaultSampleDurationNs;
        }

        public final void setDefaultSampleDurationNs(int i) {
            this.defaultSampleDurationNs = i;
        }

        public final int getMaxBlockAdditionId() {
            return this.maxBlockAdditionId;
        }

        public final void setMaxBlockAdditionId(int i) {
            this.maxBlockAdditionId = i;
        }

        public final int getBlockAddIdType() {
            return this.blockAddIdType;
        }

        public final void setBlockAddIdType(int i) {
            this.blockAddIdType = i;
        }

        public final boolean getHasContentEncryption() {
            return this.hasContentEncryption;
        }

        public final void setHasContentEncryption(boolean z) {
            this.hasContentEncryption = z;
        }

        public final byte[] getSampleStrippedBytes() {
            return this.sampleStrippedBytes;
        }

        public final void setSampleStrippedBytes(byte[] bArr) {
            this.sampleStrippedBytes = bArr;
        }

        public final TrackOutput.CryptoData getCryptoData() {
            return this.cryptoData;
        }

        public final void setCryptoData(TrackOutput.CryptoData cryptoData) {
            this.cryptoData = cryptoData;
        }

        public final byte[] getCodecPrivate() {
            return this.codecPrivate;
        }

        public final void setCodecPrivate(byte[] bArr) {
            this.codecPrivate = bArr;
        }

        public final DrmInitData getDrmInitData() {
            return this.drmInitData;
        }

        public final void setDrmInitData(DrmInitData drmInitData) {
            this.drmInitData = drmInitData;
        }

        public final int getWidth() {
            return this.width;
        }

        public final void setWidth(int i) {
            this.width = i;
        }

        public final int getHeight() {
            return this.height;
        }

        public final void setHeight(int i) {
            this.height = i;
        }

        public final int getBitsPerChannel() {
            return this.bitsPerChannel;
        }

        public final void setBitsPerChannel(int i) {
            this.bitsPerChannel = i;
        }

        public final int getDisplayWidth() {
            return this.displayWidth;
        }

        public final void setDisplayWidth(int i) {
            this.displayWidth = i;
        }

        public final int getDisplayHeight() {
            return this.displayHeight;
        }

        public final void setDisplayHeight(int i) {
            this.displayHeight = i;
        }

        public final int getDisplayUnit() {
            return this.displayUnit;
        }

        public final void setDisplayUnit(int i) {
            this.displayUnit = i;
        }

        public final int getProjectionType() {
            return this.projectionType;
        }

        public final void setProjectionType(int i) {
            this.projectionType = i;
        }

        public final float getProjectionPoseYaw() {
            return this.projectionPoseYaw;
        }

        public final void setProjectionPoseYaw(float f) {
            this.projectionPoseYaw = f;
        }

        public final float getProjectionPosePitch() {
            return this.projectionPosePitch;
        }

        public final void setProjectionPosePitch(float f) {
            this.projectionPosePitch = f;
        }

        public final float getProjectionPoseRoll() {
            return this.projectionPoseRoll;
        }

        public final void setProjectionPoseRoll(float f) {
            this.projectionPoseRoll = f;
        }

        public final byte[] getProjectionData() {
            return this.projectionData;
        }

        public final void setProjectionData(byte[] bArr) {
            this.projectionData = bArr;
        }

        public final int getStereoMode() {
            return this.stereoMode;
        }

        public final void setStereoMode(int i) {
            this.stereoMode = i;
        }

        public final boolean getHasColorInfo() {
            return this.hasColorInfo;
        }

        public final void setHasColorInfo(boolean z) {
            this.hasColorInfo = z;
        }

        public final int getColorSpace() {
            return this.colorSpace;
        }

        public final void setColorSpace(int i) {
            this.colorSpace = i;
        }

        public final int getColorTransfer() {
            return this.colorTransfer;
        }

        public final void setColorTransfer(int i) {
            this.colorTransfer = i;
        }

        public final int getColorRange() {
            return this.colorRange;
        }

        public final void setColorRange(int i) {
            this.colorRange = i;
        }

        public final int getMaxContentLuminance() {
            return this.maxContentLuminance;
        }

        public final void setMaxContentLuminance(int i) {
            this.maxContentLuminance = i;
        }

        public final int getMaxFrameAverageLuminance() {
            return this.maxFrameAverageLuminance;
        }

        public final void setMaxFrameAverageLuminance(int i) {
            this.maxFrameAverageLuminance = i;
        }

        public final float getPrimaryRChromaticityX() {
            return this.primaryRChromaticityX;
        }

        public final void setPrimaryRChromaticityX(float f) {
            this.primaryRChromaticityX = f;
        }

        public final float getPrimaryRChromaticityY() {
            return this.primaryRChromaticityY;
        }

        public final void setPrimaryRChromaticityY(float f) {
            this.primaryRChromaticityY = f;
        }

        public final float getPrimaryGChromaticityX() {
            return this.primaryGChromaticityX;
        }

        public final void setPrimaryGChromaticityX(float f) {
            this.primaryGChromaticityX = f;
        }

        public final float getPrimaryGChromaticityY() {
            return this.primaryGChromaticityY;
        }

        public final void setPrimaryGChromaticityY(float f) {
            this.primaryGChromaticityY = f;
        }

        public final float getPrimaryBChromaticityX() {
            return this.primaryBChromaticityX;
        }

        public final void setPrimaryBChromaticityX(float f) {
            this.primaryBChromaticityX = f;
        }

        public final float getPrimaryBChromaticityY() {
            return this.primaryBChromaticityY;
        }

        public final void setPrimaryBChromaticityY(float f) {
            this.primaryBChromaticityY = f;
        }

        public final float getWhitePointChromaticityX() {
            return this.whitePointChromaticityX;
        }

        public final void setWhitePointChromaticityX(float f) {
            this.whitePointChromaticityX = f;
        }

        public final float getWhitePointChromaticityY() {
            return this.whitePointChromaticityY;
        }

        public final void setWhitePointChromaticityY(float f) {
            this.whitePointChromaticityY = f;
        }

        public final float getMaxMasteringLuminance() {
            return this.maxMasteringLuminance;
        }

        public final void setMaxMasteringLuminance(float f) {
            this.maxMasteringLuminance = f;
        }

        public final float getMinMasteringLuminance() {
            return this.minMasteringLuminance;
        }

        public final void setMinMasteringLuminance(float f) {
            this.minMasteringLuminance = f;
        }

        public final byte[] getDolbyVisionConfigBytes() {
            return this.dolbyVisionConfigBytes;
        }

        public final void setDolbyVisionConfigBytes(byte[] bArr) {
            this.dolbyVisionConfigBytes = bArr;
        }

        public final int getChannelCount() {
            return this.channelCount;
        }

        public final void setChannelCount(int i) {
            this.channelCount = i;
        }

        public final int getAudioBitDepth() {
            return this.audioBitDepth;
        }

        public final void setAudioBitDepth(int i) {
            this.audioBitDepth = i;
        }

        public final int getSampleRate() {
            return this.sampleRate;
        }

        public final void setSampleRate(int i) {
            this.sampleRate = i;
        }

        public final long getCodecDelayNs() {
            return this.codecDelayNs;
        }

        public final void setCodecDelayNs(long j) {
            this.codecDelayNs = j;
        }

        public final long getSeekPreRollNs() {
            return this.seekPreRollNs;
        }

        public final void setSeekPreRollNs(long j) {
            this.seekPreRollNs = j;
        }

        public final TrueHdSampleRechunker getTrueHdSampleRechunker() {
            return this.trueHdSampleRechunker;
        }

        public final void setTrueHdSampleRechunker(TrueHdSampleRechunker trueHdSampleRechunker) {
            this.trueHdSampleRechunker = trueHdSampleRechunker;
        }

        public final boolean getWaitingForDtsAnalysis() {
            return this.waitingForDtsAnalysis;
        }

        public final void setWaitingForDtsAnalysis(boolean z) {
            this.waitingForDtsAnalysis = z;
        }

        public final boolean getFlagForced() {
            return this.flagForced;
        }

        public final void setFlagForced(boolean z) {
            this.flagForced = z;
        }

        public final boolean getFlagDefault() {
            return this.flagDefault;
        }

        public final void setFlagDefault(boolean z) {
            this.flagDefault = z;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final void setLanguage(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.language = str;
        }

        public final TrackOutput getOutput() {
            return this.output;
        }

        public final void setOutput(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public final Format getFormat() {
            return this.format;
        }

        public final void setFormat(Format format) {
            this.format = format;
        }

        public final int getNalUnitLengthFieldLength() {
            return this.nalUnitLengthFieldLength;
        }

        public final void setNalUnitLengthFieldLength(int i) {
            this.nalUnitLengthFieldLength = i;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:162:0x0423  */
        /* JADX WARN: Code duplicated, block: B:164:0x0433  */
        /* JADX WARN: Code duplicated, block: B:167:0x0440  */
        /* JADX WARN: Code duplicated, block: B:168:0x0442  */
        /* JADX WARN: Code duplicated, block: B:171:0x044f  */
        /* JADX WARN: Code duplicated, block: B:172:0x0464  */
        /* JADX WARN: Code duplicated, block: B:174:0x046a  */
        /* JADX WARN: Code duplicated, block: B:176:0x046e  */
        /* JADX WARN: Code duplicated, block: B:178:0x0473  */
        /* JADX WARN: Code duplicated, block: B:181:0x047b  */
        /* JADX WARN: Code duplicated, block: B:183:0x0480  */
        /* JADX WARN: Code duplicated, block: B:186:0x0485  */
        /* JADX WARN: Code duplicated, block: B:189:0x0493  */
        /* JADX WARN: Code duplicated, block: B:192:0x0499  */
        /* JADX WARN: Code duplicated, block: B:193:0x04c9  */
        /* JADX WARN: Code duplicated, block: B:196:0x04ce  */
        /* JADX WARN: Code duplicated, block: B:201:0x04f1  */
        /* JADX WARN: Code duplicated, block: B:207:0x050a  */
        /* JADX WARN: Code duplicated, block: B:208:0x050c  */
        /* JADX WARN: Code duplicated, block: B:210:0x0516  */
        /* JADX WARN: Code duplicated, block: B:211:0x0519  */
        /* JADX WARN: Code duplicated, block: B:213:0x0523  */
        /* JADX WARN: Code duplicated, block: B:219:0x053b  */
        /* JADX WARN: Code duplicated, block: B:221:0x0565  */
        /* JADX WARN: Code duplicated, block: B:223:0x056b  */
        /* JADX WARN: Code duplicated, block: B:238:0x059d  */
        /* JADX WARN: Code duplicated, block: B:243:0x05b8  */
        /* JADX WARN: Code duplicated, block: B:244:0x05bc  */
        /* JADX WARN: Code restructure failed: missing block: B:151:0x03ff, code lost:
        
            if (r1.equals(androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.CODEC_ID_MPEG4_SP) != false) goto L155;
         */
        /* JADX WARN: Code restructure failed: missing block: B:154:0x040b, code lost:
        
            if (r1.equals(androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.CODEC_ID_MPEG4_AP) != false) goto L155;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x010d, code lost:
        
            if (r1.equals(androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.CODEC_ID_SSA) != false) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0118, code lost:
        
            if (r1.equals(androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.CODEC_ID_ASS) != false) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x011a, code lost:
        
            r1 = androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.SSA_DIALOGUE_FORMAT;
            r3 = r19.codecId;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3);
            r3 = com.google.common.collect.ImmutableList.of(r1, getCodecPrivate(r3));
            r16 = "text/x-ssa";
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x01cf, code lost:
        
            if (r1.equals(androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.CODEC_ID_DTS_EXPRESS) != false) goto L91;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x0243, code lost:
        
            if (r1.equals(androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.CODEC_ID_DTS) != false) goto L91;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0245, code lost:
        
            r19.waitingForDtsAnalysis = true;
            r16 = "audio/vnd.dts";
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void initializeFormat(int r20) throws androidx.media3.common.ParserException {
            /*
                Method dump skipped, instruction units count: 1666
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.Track.initializeFormat(int):void");
        }

        public final void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                Intrinsics.checkNotNull(trueHdSampleRechunker);
                TrackOutput trackOutput = this.output;
                Intrinsics.checkNotNull(trackOutput);
                trueHdSampleRechunker.outputPendingSampleMetadata(trackOutput, this.cryptoData);
            }
        }

        public final void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker != null) {
                Intrinsics.checkNotNull(trueHdSampleRechunker);
                trueHdSampleRechunker.reset();
            }
        }

        public final boolean samplesHaveSupplementalData(boolean isBlockGroup) {
            if (Intrinsics.areEqual(UpdatedMatroskaExtractor.CODEC_ID_OPUS, this.codecId)) {
                return isBlockGroup;
            }
            return this.maxBlockAdditionId > 0;
        }

        private final byte[] getHdrStaticInfo() {
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

        public final void maybeAddThumbnailMetadata(SparseArray<List<MatroskaSeekMap.CuePointData>> perTrackCues, long durationUs, long segmentContentPosition, long segmentContentSize) {
            androidx.media3.common.Metadata metadataCopyWithAppendedEntries;
            Intrinsics.checkNotNullParameter(perTrackCues, "perTrackCues");
            if (this.type != 2) {
                return;
            }
            List<MatroskaSeekMap.CuePointData> list = perTrackCues.get(this.number);
            List<MatroskaSeekMap.CuePointData> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            long jFindBestThumbnailPresentationTimeUs = findBestThumbnailPresentationTimeUs(list, durationUs, segmentContentPosition, segmentContentSize);
            if (jFindBestThumbnailPresentationTimeUs != C.TIME_UNSET) {
                Format format = this.format;
                if (format == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                androidx.media3.common.Metadata metadata = format.metadata;
                ThumbnailMetadata thumbnailMetadata = new ThumbnailMetadata(jFindBestThumbnailPresentationTimeUs);
                if (metadata == null) {
                    metadataCopyWithAppendedEntries = new androidx.media3.common.Metadata(thumbnailMetadata);
                } else {
                    metadataCopyWithAppendedEntries = metadata.copyWithAppendedEntries(thumbnailMetadata);
                    Intrinsics.checkNotNull(metadataCopyWithAppendedEntries);
                }
                this.format = format.buildUpon().setMetadata(metadataCopyWithAppendedEntries).build();
            }
        }

        private final long findBestThumbnailPresentationTimeUs(List<MatroskaSeekMap.CuePointData> cuePoints, long durationUs, long segmentContentPosition, long segmentContentSize) {
            long timeUs;
            long clusterPosition;
            if (cuePoints.isEmpty()) {
                return C.TIME_UNSET;
            }
            int iMin = Math.min(cuePoints.size(), 20);
            double d = 0.0d;
            int i = -1;
            for (int i2 = 0; i2 < iMin; i2++) {
                MatroskaSeekMap.CuePointData cuePointData = cuePoints.get(i2);
                if (cuePointData.getTimeUs() > UpdatedMatroskaExtractor.MAX_DURATION_US_TO_SCAN_FOR_THUMBNAIL) {
                    break;
                }
                if (i2 < cuePoints.size() - 1) {
                    MatroskaSeekMap.CuePointData cuePointData2 = cuePoints.get(i2 + 1);
                    clusterPosition = (cuePointData2.getClusterPosition() + cuePointData2.getRelativePosition()) - (cuePointData.getClusterPosition() + cuePointData.getRelativePosition());
                    timeUs = cuePointData2.getTimeUs() - cuePointData.getTimeUs();
                } else {
                    long clusterPosition2 = (segmentContentPosition + segmentContentSize) - (cuePointData.getClusterPosition() + cuePointData.getRelativePosition());
                    timeUs = durationUs - cuePointData.getTimeUs();
                    clusterPosition = clusterPosition2;
                }
                if (timeUs > 0) {
                    double d2 = clusterPosition / timeUs;
                    if (d2 > d) {
                        i = i2;
                        d = d2;
                    }
                }
            }
            return i == -1 ? C.TIME_UNSET : cuePoints.get(i).getTimeUs();
        }

        public final void assertOutputInitialized() {
            Preconditions.checkNotNull(this.output);
        }

        private final byte[] getCodecPrivate(String codecId) throws ParserException {
            byte[] bArr = this.codecPrivate;
            if (bArr == null) {
                ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + codecId, null);
                Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
                throw parserExceptionCreateForMalformedContainer;
            }
            Intrinsics.checkNotNull(bArr);
            return bArr;
        }

        /* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0011\u001a\u00020\rH\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$Track$Companion;", "", "<init>", "()V", "DISPLAY_UNIT_PIXELS", "", "MAX_CHROMATICITY", "DEFAULT_MAX_CLL", "DEFAULT_MAX_FALL", "parseFourCcPrivate", "Landroid/util/Pair;", "", "", "", "buffer", "Landroidx/media3/common/util/ParsableByteArray;", "parseVorbisCodecPrivate", "codecPrivate", "parseMsAcmCodecPrivate", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray buffer) throws ParserException {
                try {
                    buffer.skipBytes(16);
                    long littleEndianUnsignedInt = buffer.readLittleEndianUnsignedInt();
                    if (littleEndianUnsignedInt == 1482049860) {
                        return new Pair<>("video/divx", null);
                    }
                    if (littleEndianUnsignedInt == 859189832) {
                        return new Pair<>("video/3gpp", null);
                    }
                    if (littleEndianUnsignedInt == 826496599) {
                        byte[] data = buffer.getData();
                        Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                        int length = data.length - 4;
                        for (int position = buffer.getPosition() + 20; position < length; position++) {
                            if (data[position] == 0 && data[position + 1] == 0 && data[position + 2] == 1 && data[position + 3] == 15) {
                                return new Pair<>("video/wvc1", CollectionsKt.listOf(Arrays.copyOfRange(data, position, data.length)));
                            }
                        }
                        ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", null);
                        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
                        throw parserExceptionCreateForMalformedContainer;
                    }
                    Log.w(UpdatedMatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                    return new Pair<>("video/x-unknown", null);
                } catch (ArrayIndexOutOfBoundsException unused) {
                    ParserException parserExceptionCreateForMalformedContainer2 = ParserException.createForMalformedContainer("Error parsing FourCC private data", null);
                    Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer2, "createForMalformedContainer(...)");
                    throw parserExceptionCreateForMalformedContainer2;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final List<byte[]> parseVorbisCodecPrivate(byte[] codecPrivate) throws ParserException {
                int i;
                int i2;
                try {
                    if (codecPrivate[0] != 2) {
                        ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
                        throw parserExceptionCreateForMalformedContainer;
                    }
                    int i3 = 0;
                    int i4 = 1;
                    while (true) {
                        i = codecPrivate[i4];
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
                        i2 = codecPrivate[i5];
                        if ((i2 & 255) != 255) {
                            break;
                        }
                        i7 += 255;
                        i5++;
                    }
                    int i8 = i5 + 1;
                    int i9 = i7 + (i2 & 255);
                    if (codecPrivate[i8] != 1) {
                        ParserException parserExceptionCreateForMalformedContainer2 = ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer2, "createForMalformedContainer(...)");
                        throw parserExceptionCreateForMalformedContainer2;
                    }
                    byte[] bArr = new byte[i6];
                    System.arraycopy(codecPrivate, i8, bArr, 0, i6);
                    int i10 = i8 + i6;
                    if (codecPrivate[i10] != 3) {
                        ParserException parserExceptionCreateForMalformedContainer3 = ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer3, "createForMalformedContainer(...)");
                        throw parserExceptionCreateForMalformedContainer3;
                    }
                    int i11 = i10 + i9;
                    if (codecPrivate[i11] != 5) {
                        ParserException parserExceptionCreateForMalformedContainer4 = ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                        Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer4, "createForMalformedContainer(...)");
                        throw parserExceptionCreateForMalformedContainer4;
                    }
                    byte[] bArr2 = new byte[codecPrivate.length - i11];
                    System.arraycopy(codecPrivate, i11, bArr2, 0, codecPrivate.length - i11);
                    ArrayList arrayList = new ArrayList(2);
                    arrayList.add(bArr);
                    arrayList.add(bArr2);
                    return arrayList;
                } catch (ArrayIndexOutOfBoundsException unused) {
                    ParserException parserExceptionCreateForMalformedContainer5 = ParserException.createForMalformedContainer("Error parsing vorbis codec private", null);
                    Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer5, "createForMalformedContainer(...)");
                    throw parserExceptionCreateForMalformedContainer5;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final boolean parseMsAcmCodecPrivate(ParsableByteArray buffer) throws ParserException {
                try {
                    int littleEndianUnsignedShort = buffer.readLittleEndianUnsignedShort();
                    if (littleEndianUnsignedShort == 1) {
                        return true;
                    }
                    if (littleEndianUnsignedShort != 65534) {
                        return false;
                    }
                    buffer.setPosition(24);
                    return buffer.readLong() == UpdatedMatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && buffer.readLong() == UpdatedMatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits();
                } catch (ArrayIndexOutOfBoundsException unused) {
                    ParserException parserExceptionCreateForMalformedContainer = ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", null);
                    Intrinsics.checkNotNullExpressionValue(parserExceptionCreateForMalformedContainer, "createForMalformedContainer(...)");
                    throw parserExceptionCreateForMalformedContainer;
                }
            }
        }
    }

    /* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
    @Metadata(d1 = {"\u0000Y\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0003\b\u0098\u0001\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J'\u0010¿\u0001\u001a\u00030À\u00012\u0007\u0010Á\u0001\u001a\u00020\u00102\b\u0010Â\u0001\u001a\u00030©\u00012\b\u0010Ã\u0001\u001a\u00030«\u0001H\u0002J'\u0010Ä\u0001\u001a\u00030«\u00012\b\u0010Å\u0001\u001a\u00030©\u00012\u0007\u0010Æ\u0001\u001a\u00020\u00102\b\u0010Ç\u0001\u001a\u00030©\u0001H\u0002J\u0013\u0010È\u0001\u001a\u00030É\u00012\u0007\u0010Á\u0001\u001a\u00020\u0010H\u0002J\u001f\u0010Ê\u0001\u001a\u00030Ë\u00012\n\u0010Ì\u0001\u001a\u0005\u0018\u00010Ë\u00012\u0007\u0010Í\u0001\u001a\u00020\tH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010}\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u007f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0080\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0081\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0082\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0083\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0084\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0085\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0086\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0087\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0088\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0089\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u008a\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u008b\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u008c\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u008d\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u008e\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u008f\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0090\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0091\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0092\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0093\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0094\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0095\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0096\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0097\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0098\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u0099\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u009a\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u009b\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u009c\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u009d\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u009e\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010\u009f\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010 \u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010¡\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010¢\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010£\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010¤\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010¥\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010¦\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010§\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010¨\u0001\u001a\u00030©\u0001X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010ª\u0001\u001a\u00030«\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010¬\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u00ad\u0001\u001a\u00030©\u0001X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010®\u0001\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010¯\u0001\u001a\u00030«\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010°\u0001\u001a\u00030«\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010±\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010²\u0001\u001a\u00030©\u0001X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010³\u0001\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010´\u0001\u001a\u00030«\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010µ\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010¶\u0001\u001a\u00030©\u0001X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010·\u0001\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010¸\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010¹\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000f\u0010º\u0001\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010»\u0001\u001a\u00030¼\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010½\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0¾\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Î\u0001"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$Companion;", "", "<init>", "()V", "newFactory", "Landroidx/media3/extractor/ExtractorsFactory;", "subtitleParserFactory", "Landroidx/media3/extractor/text/SubtitleParser$Factory;", "FLAG_DISABLE_SEEK_FOR_CUES", "", "FLAG_EMIT_RAW_SUBTITLE_DATA", "FACTORY", "getFACTORY$annotations", "getFACTORY", "()Landroidx/media3/extractor/ExtractorsFactory;", "TAG", "", "UNSET_ENTRY_ID", "BLOCK_STATE_START", "BLOCK_STATE_HEADER", "BLOCK_STATE_DATA", "DOC_TYPE_MATROSKA", "DOC_TYPE_WEBM", "CODEC_ID_VP8", "CODEC_ID_VP9", "CODEC_ID_AV1", "CODEC_ID_MPEG2", "CODEC_ID_MPEG4_SP", "CODEC_ID_MPEG4_ASP", "CODEC_ID_MPEG4_AP", "CODEC_ID_H264", "CODEC_ID_H265", "CODEC_ID_FOURCC", "CODEC_ID_THEORA", "CODEC_ID_VORBIS", "CODEC_ID_OPUS", "CODEC_ID_AAC", "CODEC_ID_MP2", "CODEC_ID_MP3", "CODEC_ID_AC3", "CODEC_ID_E_AC3", "CODEC_ID_TRUEHD", "CODEC_ID_DTS", "CODEC_ID_DTS_EXPRESS", "CODEC_ID_DTS_LOSSLESS", "CODEC_ID_FLAC", "CODEC_ID_ACM", "CODEC_ID_PCM_INT_LIT", "CODEC_ID_PCM_INT_BIG", "CODEC_ID_PCM_FLOAT", "CODEC_ID_SUBRIP", "CODEC_ID_ASS", "CODEC_ID_SSA", "CODEC_ID_VTT", "CODEC_ID_VOBSUB", "CODEC_ID_PGS", "CODEC_ID_DVBSUB", "VORBIS_MAX_INPUT_SIZE", "OPUS_MAX_INPUT_SIZE", "ENCRYPTION_IV_SIZE", "TRACK_TYPE_AUDIO", "ID_EBML", "ID_EBML_READ_VERSION", "ID_DOC_TYPE", "ID_DOC_TYPE_READ_VERSION", "ID_SEGMENT", "ID_SEGMENT_INFO", "ID_SEEK_HEAD", "ID_SEEK", "ID_SEEK_ID", "ID_SEEK_POSITION", "ID_INFO", "ID_TIMECODE_SCALE", "ID_DURATION", "ID_CLUSTER", "ID_TIME_CODE", "ID_SIMPLE_BLOCK", "ID_BLOCK_GROUP", "ID_BLOCK", "ID_BLOCK_DURATION", "ID_BLOCK_ADDITIONS", "ID_BLOCK_MORE", "ID_BLOCK_ADD_ID", "ID_BLOCK_ADDITIONAL", "ID_REFERENCE_BLOCK", "ID_TRACKS", "ID_TRACK_ENTRY", "ID_TRACK_NUMBER", "ID_TRACK_TYPE", "ID_FLAG_DEFAULT", "ID_FLAG_FORCED", "ID_DEFAULT_DURATION", "ID_MAX_BLOCK_ADDITION_ID", "ID_BLOCK_ADDITION_MAPPING", "ID_BLOCK_ADD_ID_TYPE", "ID_BLOCK_ADD_ID_EXTRA_DATA", "ID_NAME", "ID_CODEC_ID", "ID_CODEC_PRIVATE", "ID_CODEC_DELAY", "ID_SEEK_PRE_ROLL", "ID_DISCARD_PADDING", "ID_VIDEO", "ID_PIXEL_WIDTH", "ID_PIXEL_HEIGHT", "ID_DISPLAY_WIDTH", "ID_DISPLAY_HEIGHT", "ID_DISPLAY_UNIT", "ID_AUDIO", "ID_CHANNELS", "ID_AUDIO_BIT_DEPTH", "ID_SAMPLING_FREQUENCY", "ID_CONTENT_ENCODINGS", "ID_CONTENT_ENCODING", "ID_CONTENT_ENCODING_ORDER", "ID_CONTENT_ENCODING_SCOPE", "ID_CONTENT_COMPRESSION", "ID_CONTENT_COMPRESSION_ALGORITHM", "ID_CONTENT_COMPRESSION_SETTINGS", "ID_CONTENT_ENCRYPTION", "ID_CONTENT_ENCRYPTION_ALGORITHM", "ID_CONTENT_ENCRYPTION_KEY_ID", "ID_CONTENT_ENCRYPTION_AES_SETTINGS", "ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE", "ID_CUES", "ID_CUE_POINT", "ID_CUE_TIME", "ID_CUE_TRACK", "ID_CUE_TRACK_POSITIONS", "ID_CUE_CLUSTER_POSITION", "ID_CUE_RELATIVE_POSITION", "ID_LANGUAGE", "ID_PROJECTION", "ID_PROJECTION_TYPE", "ID_PROJECTION_PRIVATE", "ID_PROJECTION_POSE_YAW", "ID_PROJECTION_POSE_PITCH", "ID_PROJECTION_POSE_ROLL", "ID_STEREO_MODE", "ID_COLOUR", "ID_COLOUR_RANGE", "ID_COLOUR_BITS_PER_CHANNEL", "ID_COLOUR_TRANSFER", "ID_COLOUR_PRIMARIES", "ID_MAX_CLL", "ID_MAX_FALL", "ID_MASTERING_METADATA", "ID_PRIMARY_R_CHROMATICITY_X", "ID_PRIMARY_R_CHROMATICITY_Y", "ID_PRIMARY_G_CHROMATICITY_X", "ID_PRIMARY_G_CHROMATICITY_Y", "ID_PRIMARY_B_CHROMATICITY_X", "ID_PRIMARY_B_CHROMATICITY_Y", "ID_WHITE_POINT_CHROMATICITY_X", "ID_WHITE_POINT_CHROMATICITY_Y", "ID_LUMNINANCE_MAX", "ID_LUMNINANCE_MIN", "BLOCK_ADDITIONAL_ID_VP9_ITU_T_35", "BLOCK_ADD_ID_TYPE_DVCC", "BLOCK_ADD_ID_TYPE_DVVC", "LACING_NONE", "LACING_XIPH", "LACING_FIXED_SIZE", "LACING_EBML", "FOURCC_COMPRESSION_DIVX", "FOURCC_COMPRESSION_H263", "FOURCC_COMPRESSION_VC1", "MAX_CHUNKS_TO_SCAN_FOR_THUMBNAIL", "MAX_DURATION_US_TO_SCAN_FOR_THUMBNAIL", "", "SUBRIP_PREFIX", "", "SUBRIP_PREFIX_END_TIMECODE_OFFSET", "SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR", "SUBRIP_TIMECODE_FORMAT", "SSA_DIALOGUE_FORMAT", "SSA_PREFIX", "SSA_PREFIX_END_TIMECODE_OFFSET", "SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR", "SSA_TIMECODE_FORMAT", "VTT_PREFIX", "VTT_PREFIX_END_TIMECODE_OFFSET", "VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR", "VTT_TIMECODE_FORMAT", "WAVE_FORMAT_SIZE", "WAVE_FORMAT_EXTENSIBLE", "WAVE_FORMAT_PCM", "WAVE_SUBFORMAT_PCM", "Ljava/util/UUID;", "TRACK_NAME_TO_ROTATION_DEGREES", "", "setSubtitleEndTime", "", "codecId", "durationUs", "subtitleData", "formatSubtitleTimecode", "timeUs", "timecodeFormat", "lastTimecodeValueScalingFactor", "isCodecSupported", "", "ensureArrayCapacity", "", "array", "length", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "Use {@link #newFactory(SubtitleParser.Factory)} instead.")
        public static /* synthetic */ void getFACTORY$annotations() {
        }

        private Companion() {
        }

        public final ExtractorsFactory newFactory(final SubtitleParser.Factory subtitleParserFactory) {
            Intrinsics.checkNotNullParameter(subtitleParserFactory, "subtitleParserFactory");
            return new ExtractorsFactory() { // from class: androidx.media3.extractor.mkv.UpdatedMatroskaExtractor$Companion$$ExternalSyntheticLambda0
                @Override // androidx.media3.extractor.ExtractorsFactory
                public final Extractor[] createExtractors() {
                    return UpdatedMatroskaExtractor.Companion.newFactory$lambda$0(subtitleParserFactory);
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
        }

        static final Extractor[] newFactory$lambda$0(SubtitleParser.Factory factory) {
            return new Extractor[]{new UpdatedMatroskaExtractor(factory)};
        }

        public final ExtractorsFactory getFACTORY() {
            return UpdatedMatroskaExtractor.FACTORY;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x003a, code lost:
        
            if (r7.equals(androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.CODEC_ID_SSA) != false) goto L16;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
        
            if (r7.equals(androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.CODEC_ID_ASS) != false) goto L16;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
        
            r6 = formatSubtitleTimecode(r8, androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.SSA_TIMECODE_FORMAT, 10000);
            r7 = 21;
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void setSubtitleEndTime(java.lang.String r7, long r8, byte[] r10) {
            /*
                r6 = this;
                int r0 = r7.hashCode()
                switch(r0) {
                    case 738597099: goto L3d;
                    case 738614379: goto L32;
                    case 1045209816: goto L1d;
                    case 1422270023: goto L8;
                    default: goto L7;
                }
            L7:
                goto L57
            L8:
                java.lang.String r0 = "S_TEXT/UTF8"
                boolean r7 = r7.equals(r0)
                if (r7 == 0) goto L57
                java.lang.String r3 = "%02d:%02d:%02d,%03d"
                r4 = 1000(0x3e8, double:4.94E-321)
                r0 = r6
                r1 = r8
                byte[] r6 = r0.formatSubtitleTimecode(r1, r3, r4)
                r7 = 19
                goto L51
            L1d:
                r0 = r6
                r1 = r8
                java.lang.String r6 = "S_TEXT/WEBVTT"
                boolean r6 = r7.equals(r6)
                if (r6 == 0) goto L57
                java.lang.String r3 = "%02d:%02d:%02d.%03d"
                r4 = 1000(0x3e8, double:4.94E-321)
                byte[] r6 = r0.formatSubtitleTimecode(r1, r3, r4)
                r7 = 25
                goto L51
            L32:
                r0 = r6
                r1 = r8
                java.lang.String r6 = "S_TEXT/SSA"
                boolean r6 = r7.equals(r6)
                if (r6 == 0) goto L57
                goto L47
            L3d:
                r0 = r6
                r1 = r8
                java.lang.String r6 = "S_TEXT/ASS"
                boolean r6 = r7.equals(r6)
                if (r6 == 0) goto L57
            L47:
                java.lang.String r3 = "%01d:%02d:%02d:%02d"
                r4 = 10000(0x2710, double:4.9407E-320)
                byte[] r6 = r0.formatSubtitleTimecode(r1, r3, r4)
                r7 = 21
            L51:
                r8 = 0
                int r9 = r6.length
                java.lang.System.arraycopy(r6, r8, r10, r7, r9)
                return
            L57:
                java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
                r6.<init>()
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.UpdatedMatroskaExtractor.Companion.setSubtitleEndTime(java.lang.String, long, byte[]):void");
        }

        private final byte[] formatSubtitleTimecode(long timeUs, String timecodeFormat, long lastTimecodeValueScalingFactor) {
            Preconditions.checkArgument(timeUs != C.TIME_UNSET);
            int i = (int) (timeUs / 3600000000L);
            long j = timeUs - (((long) i) * 3600000000L);
            int i2 = (int) (j / 60000000);
            long j2 = j - (((long) i2) * 60000000);
            int i3 = (int) (j2 / 1000000);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(Locale.US, timecodeFormat, Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j2 - (((long) i3) * 1000000)) / lastTimecodeValueScalingFactor))}, 4));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            byte[] utf8Bytes = Util.getUtf8Bytes(str);
            Intrinsics.checkNotNullExpressionValue(utf8Bytes, "getUtf8Bytes(...)");
            return utf8Bytes;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isCodecSupported(String codecId) {
            switch (codecId.hashCode()) {
                case -2095576542:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_MPEG4_AP);
                case -2095575984:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_MPEG4_SP);
                case -1985379776:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_ACM);
                case -1784763192:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_TRUEHD);
                case -1730367663:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_VORBIS);
                case -1482641358:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_MP2);
                case -1482641357:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_MP3);
                case -1373388978:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_FOURCC);
                case -933872740:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_DVBSUB);
                case -538363189:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_MPEG4_ASP);
                case -538363109:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_H264);
                case -425012669:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_VOBSUB);
                case -356037306:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_DTS_LOSSLESS);
                case 62923557:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_AAC);
                case 62923603:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_AC3);
                case 62927045:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_DTS);
                case 82318131:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_AV1);
                case 82338133:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_VP8);
                case 82338134:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_VP9);
                case 99146302:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_PGS);
                case 444813526:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_THEORA);
                case 542569478:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_DTS_EXPRESS);
                case 635596514:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_PCM_FLOAT);
                case 725948237:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_PCM_INT_BIG);
                case 725957860:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_PCM_INT_LIT);
                case 738597099:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_ASS);
                case 738614379:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_SSA);
                case 855502857:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_H265);
                case 1045209816:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_VTT);
                case 1422270023:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_SUBRIP);
                case 1809237540:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_MPEG2);
                case 1950749482:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_E_AC3);
                case 1950789798:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_FLAC);
                case 1951062397:
                    return codecId.equals(UpdatedMatroskaExtractor.CODEC_ID_OPUS);
                default:
                    return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int[] ensureArrayCapacity(int[] array, int length) {
            if (array == null) {
                return new int[length];
            }
            return array.length >= length ? array : new int[(int) Math.max(array.length * 2, length)];
        }
    }

    static {
        byte[] utf8Bytes = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
        Intrinsics.checkNotNullExpressionValue(utf8Bytes, "getUtf8Bytes(...)");
        SSA_DIALOGUE_FORMAT = utf8Bytes;
        SSA_PREFIX = new byte[]{68, 105, 97, 108, 111, 103, 117, 101, 58, 32, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 44, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 44};
        VTT_PREFIX = new byte[]{87, 69, 66, 86, 84, 84, 10, 10, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 46, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 32, 45, 45, 62, 32, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 58, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 46, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 10};
        WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
        HashMap map = new HashMap();
        map.put("htc_video_rotA-000", 0);
        map.put("htc_video_rotA-090", 90);
        map.put("htc_video_rotA-180", 180);
        map.put("htc_video_rotA-270", 270);
        Map<String, Integer> mapUnmodifiableMap = DesugarCollections.unmodifiableMap(map);
        Intrinsics.checkNotNullExpressionValue(mapUnmodifiableMap, "unmodifiableMap(...)");
        TRACK_NAME_TO_ROTATION_DEGREES = mapUnmodifiableMap;
    }

    static final Extractor[] FACTORY$lambda$0() {
        SubtitleParser.Factory UNSUPPORTED = SubtitleParser.Factory.UNSUPPORTED;
        Intrinsics.checkNotNullExpressionValue(UNSUPPORTED, "UNSUPPORTED");
        return new Extractor[]{new UpdatedMatroskaExtractor(UNSUPPORTED, 2)};
    }

    /* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u00012\u00020\u0002:\u0002\u0019\u001aB;\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\bH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0010H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$MatroskaSeekMap;", "Landroidx/media3/extractor/TrackAwareSeekMap;", "Landroidx/media3/extractor/ChunkIndexProvider;", "perTrackCues", "Landroid/util/SparseArray;", "", "Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$MatroskaSeekMap$CuePointData;", "durationUs", "", "primarySeekTrackNumber", "", "segmentContentPosition", "segmentContentSize", "<init>", "(Landroid/util/SparseArray;JIJJ)V", "chunkIndex", "Landroidx/media3/extractor/ChunkIndex;", "isSeekable", "", "trackId", "getDurationUs", "getSeekPoints", "Landroidx/media3/extractor/SeekMap$SeekPoints;", "timeUs", "getChunkIndex", "Companion", "CuePointData", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class MatroskaSeekMap implements TrackAwareSeekMap, ChunkIndexProvider {
        private static final Companion Companion = new Companion(null);
        private final ChunkIndex chunkIndex;
        private final long durationUs;
        private final SparseArray<List<CuePointData>> perTrackCues;
        private final int primarySeekTrackNumber;

        @Override // androidx.media3.extractor.SeekMap
        public /* synthetic */ boolean isEstimated() {
            return SeekMap.CC.$default$isEstimated(this);
        }

        public MatroskaSeekMap(SparseArray<List<CuePointData>> perTrackCues, long j, int i, long j2, long j3) {
            Intrinsics.checkNotNullParameter(perTrackCues, "perTrackCues");
            this.perTrackCues = perTrackCues;
            this.durationUs = j;
            this.primarySeekTrackNumber = i;
            this.chunkIndex = Companion.buildChunkIndex(perTrackCues, j, i, j2, j3);
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return isSeekable(this.primarySeekTrackNumber);
        }

        @Override // androidx.media3.extractor.TrackAwareSeekMap
        public boolean isSeekable(int trackId) {
            List<CuePointData> list = this.perTrackCues.get(trackId);
            return !(list == null || list.isEmpty());
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long timeUs) {
            SeekMap.SeekPoints seekPoints;
            ChunkIndex chunkIndex = this.chunkIndex;
            return (chunkIndex == null || (seekPoints = chunkIndex.getSeekPoints(timeUs)) == null) ? new SeekMap.SeekPoints(SeekPoint.START) : seekPoints;
        }

        @Override // androidx.media3.extractor.TrackAwareSeekMap
        public SeekMap.SeekPoints getSeekPoints(long timeUs, int trackId) {
            int i;
            int i2;
            List<CuePointData> list = this.perTrackCues.get(trackId);
            List<CuePointData> list2 = list;
            if ((list2 == null || list2.isEmpty()) && trackId != (i = this.primarySeekTrackNumber)) {
                list = this.perTrackCues.get(i);
            }
            List<CuePointData> list3 = list;
            if (list3 == null || list3.isEmpty()) {
                return new SeekMap.SeekPoints(SeekPoint.START);
            }
            int iBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super CuePointData>>) list, new CuePointData(timeUs, -1L, -1L), true, false);
            if (iBinarySearchFloor != -1) {
                CuePointData cuePointData = list.get(iBinarySearchFloor);
                SeekPoint seekPoint = new SeekPoint(cuePointData.getTimeUs(), cuePointData.getClusterPosition());
                if (cuePointData.getTimeUs() < timeUs && (i2 = iBinarySearchFloor + 1) < list.size()) {
                    CuePointData cuePointData2 = list.get(i2);
                    return new SeekMap.SeekPoints(seekPoint, new SeekPoint(cuePointData2.getTimeUs(), cuePointData2.getClusterPosition()));
                }
                return new SeekMap.SeekPoints(seekPoint);
            }
            CuePointData cuePointData3 = list.get(0);
            return new SeekMap.SeekPoints(new SeekPoint(cuePointData3.getTimeUs(), cuePointData3.getClusterPosition()));
        }

        @Override // androidx.media3.extractor.ChunkIndexProvider
        public ChunkIndex getChunkIndex() {
            return this.chunkIndex;
        }

        /* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J>\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002¨\u0006\u0010"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$MatroskaSeekMap$Companion;", "", "<init>", "()V", "buildChunkIndex", "Landroidx/media3/extractor/ChunkIndex;", "perTrackCues", "Landroid/util/SparseArray;", "", "Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$MatroskaSeekMap$CuePointData;", "durationUs", "", "primarySeekTrackNumber", "", "segmentContentPosition", "segmentContentSize", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        private static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final ChunkIndex buildChunkIndex(SparseArray<List<CuePointData>> perTrackCues, long durationUs, int primarySeekTrackNumber, long segmentContentPosition, long segmentContentSize) {
                List<CuePointData> list = perTrackCues.get(primarySeekTrackNumber);
                if (list == null || list.isEmpty()) {
                    return null;
                }
                int size = list.size();
                int[] iArrCopyOf = new int[size];
                long[] jArrCopyOf = new long[size];
                long[] jArrCopyOf2 = new long[size];
                long[] jArrCopyOf3 = new long[size];
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    CuePointData cuePointData = list.get(i2);
                    jArrCopyOf3[i2] = cuePointData.getTimeUs();
                    jArrCopyOf[i2] = cuePointData.getClusterPosition();
                }
                int i3 = size - 1;
                while (i < i3) {
                    int i4 = i + 1;
                    iArrCopyOf[i] = (int) (jArrCopyOf[i4] - jArrCopyOf[i]);
                    jArrCopyOf2[i] = jArrCopyOf3[i4] - jArrCopyOf3[i];
                    i = i4;
                }
                int i5 = i3;
                while (i5 > 0 && jArrCopyOf3[i5] >= durationUs) {
                    i5--;
                }
                iArrCopyOf[i5] = (int) ((segmentContentPosition + segmentContentSize) - jArrCopyOf[i5]);
                jArrCopyOf2[i5] = durationUs - jArrCopyOf3[i5];
                if (i5 < i3) {
                    Log.w(UpdatedMatroskaExtractor.TAG, "Discarding trailing cue points with timestamps greater than total duration.");
                    int i6 = i5 + 1;
                    iArrCopyOf = Arrays.copyOf(iArrCopyOf, i6);
                    Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
                    jArrCopyOf = Arrays.copyOf(jArrCopyOf, i6);
                    Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
                    jArrCopyOf2 = Arrays.copyOf(jArrCopyOf2, i6);
                    Intrinsics.checkNotNullExpressionValue(jArrCopyOf2, "copyOf(...)");
                    jArrCopyOf3 = Arrays.copyOf(jArrCopyOf3, i6);
                    Intrinsics.checkNotNullExpressionValue(jArrCopyOf3, "copyOf(...)");
                }
                return new ChunkIndex(iArrCopyOf, jArrCopyOf, jArrCopyOf2, jArrCopyOf3);
            }
        }

        /* JADX INFO: compiled from: UpdatedMatroskaExtractor.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0000H\u0096\u0082\u0004J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u0011H\u0096\u0082\u0004J\n\u0010\u0012\u001a\u00020\rH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/media3/extractor/mkv/UpdatedMatroskaExtractor$MatroskaSeekMap$CuePointData;", "", "timeUs", "", "clusterPosition", "relativePosition", "<init>", "(JJJ)V", "getTimeUs", "()J", "getClusterPosition", "getRelativePosition", "compareTo", "", "other", "equals", "", "", "hashCode", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class CuePointData implements Comparable<CuePointData> {
            private final long clusterPosition;
            private final long relativePosition;
            private final long timeUs;

            public CuePointData(long j, long j2, long j3) {
                this.timeUs = j;
                this.clusterPosition = j2;
                this.relativePosition = j3;
            }

            public final long getTimeUs() {
                return this.timeUs;
            }

            public final long getClusterPosition() {
                return this.clusterPosition;
            }

            public final long getRelativePosition() {
                return this.relativePosition;
            }

            @Override // java.lang.Comparable
            public int compareTo(CuePointData other) {
                Intrinsics.checkNotNullParameter(other, "other");
                return Intrinsics.compare(this.timeUs, other.timeUs);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof CuePointData)) {
                    return false;
                }
                CuePointData cuePointData = (CuePointData) other;
                return this.timeUs == cuePointData.timeUs && this.clusterPosition == cuePointData.clusterPosition && this.relativePosition == cuePointData.relativePosition;
            }

            public int hashCode() {
                return Objects.hash(Long.valueOf(this.timeUs), Long.valueOf(this.clusterPosition), Long.valueOf(this.relativePosition));
            }
        }
    }
}
