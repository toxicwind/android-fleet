package androidx.media3.exoplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaCodec;
import android.media.MediaDataSource;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.FileDescriptorDataSource;
import androidx.media3.datasource.MediaDataSourceAdapter;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.UnrecognizedInputFormatException;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackAwareSeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import com.google.android.gms.common.Scopes;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: loaded from: classes4.dex */
public class MediaExtractorCompatInternal {
    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    private static final String TAG = "MediaExtractorCompatInt";
    private DataSource currentDataSource;
    private Extractor currentExtractor;
    private ExtractorInput currentExtractorInput;
    private final DataSource.Factory dataSourceFactory;
    private final ExtractorsFactory extractorsFactory;
    private boolean hasBeenPrepared;
    private Map<String, String> httpRequestHeaders;
    private LogSessionId logSessionId;
    private long offsetInCurrentFile;
    private SeekPoint pendingSeek;
    private SeekMap seekMap;
    private boolean tracksEnded;
    private int upstreamFormatsCount;
    private final PositionHolder positionHolder = new PositionHolder();
    private final Allocator allocator = new DefaultAllocator(true, 65536);
    private final ArrayList<MediaExtractorTrack> tracks = new ArrayList<>();
    private final SparseArray<MediaExtractorSampleQueue> sampleQueues = new SparseArray<>();
    private final SampleMetadataQueue sampleMetadataQueue = new SampleMetadataQueue();
    private final FormatHolder formatHolder = new FormatHolder();
    private final DecoderInputBuffer sampleHolderWithBufferReplacementDisabled = DecoderInputBuffer.newNoDataInstance();
    private final DecoderInputBuffer sampleHolderWithBufferReplacementEnabled = new DecoderInputBuffer(2);
    private final Set<Integer> selectedTrackIndices = new HashSet();

    public MediaExtractorCompatInternal(ExtractorsFactory extractorsFactory, DataSource.Factory factory) {
        this.extractorsFactory = extractorsFactory;
        this.dataSourceFactory = factory;
    }

    public void setDataSource(Uri uri, long j) throws IOException {
        prepareDataSource(this.dataSourceFactory.createDataSource(), buildDataSpec(uri, j));
    }

    public void setDataSource(AssetFileDescriptor assetFileDescriptor) throws IOException {
        if (assetFileDescriptor.getDeclaredLength() == -1) {
            setDataSource(assetFileDescriptor.getFileDescriptor());
        } else {
            setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getDeclaredLength());
        }
    }

    public void setDataSource(FileDescriptor fileDescriptor) throws IOException {
        setDataSource(fileDescriptor, 0L, -1L);
    }

    public void setDataSource(FileDescriptor fileDescriptor, long j, long j2) throws IOException {
        prepareDataSource(new FileDescriptorDataSource(fileDescriptor, j, j2), buildDataSpec(Uri.EMPTY, 0L));
    }

    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException {
        if (Util.isLocalFileUri(uri)) {
            setDataSource((String) Preconditions.checkNotNull(uri.getPath()));
            return;
        }
        try {
            AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
            if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                try {
                    setDataSource(assetFileDescriptorOpenAssetFileDescriptor);
                    if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                        assetFileDescriptorOpenAssetFileDescriptor.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                        try {
                            assetFileDescriptorOpenAssetFileDescriptor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                assetFileDescriptorOpenAssetFileDescriptor.close();
            }
            setDataSource(uri.toString(), map);
        } catch (FileNotFoundException | SecurityException unused) {
        }
    }

    public void setDataSource(String str) throws IOException {
        setDataSource(str, (Map<String, String>) null);
    }

    public void setDataSource(String str, Map<String, String> map) throws IOException {
        this.httpRequestHeaders = map;
        prepareDataSource(this.dataSourceFactory.createDataSource(), buildDataSpec(Uri.parse(str), 0L));
    }

    public void setDataSource(MediaDataSource mediaDataSource) throws IOException {
        prepareDataSource(new MediaDataSourceAdapter(mediaDataSource, false), buildDataSpec(Uri.EMPTY, 0L));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void prepareDataSource(DataSource dataSource, DataSpec dataSpec) throws IOException {
        int i;
        String str;
        Preconditions.checkState(!this.hasBeenPrepared);
        this.hasBeenPrepared = true;
        this.offsetInCurrentFile = dataSpec.position;
        this.currentDataSource = dataSource;
        ExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.currentDataSource, 0L, dataSource.open(dataSpec));
        Extractor extractorSelectExtractor = selectExtractor(defaultExtractorInput);
        Throwable e = null;
        extractorSelectExtractor.init(new ExtractorOutputImpl());
        boolean z = true;
        while (z) {
            try {
                i = extractorSelectExtractor.read(defaultExtractorInput, this.positionHolder);
            } catch (Exception | OutOfMemoryError e2) {
                e = e2;
                i = -1;
            }
            boolean z2 = !this.tracksEnded || this.upstreamFormatsCount < this.sampleQueues.size() || this.seekMap == null;
            if (e != null || (z2 && i == -1)) {
                release();
                if (e != null) {
                    str = "Exception encountered while parsing input media.";
                } else {
                    str = "Reached end of input before preparation completed.";
                }
                throw ParserException.createForMalformedContainer(str, e);
            }
            if (i == 1) {
                defaultExtractorInput = reopenCurrentDataSource(this.positionHolder.position);
            }
            z = z2;
        }
        this.currentExtractorInput = defaultExtractorInput;
        this.currentExtractor = extractorSelectExtractor;
    }

    public void release() {
        SparseArray<MediaExtractorSampleQueue> sparseArray;
        int i = 0;
        while (true) {
            int size = this.sampleQueues.size();
            sparseArray = this.sampleQueues;
            if (i >= size) {
                break;
            }
            sparseArray.valueAt(i).release();
            i++;
        }
        sparseArray.clear();
        Extractor extractor = this.currentExtractor;
        if (extractor != null) {
            extractor.release();
            this.currentExtractor = null;
        }
        this.currentExtractorInput = null;
        this.pendingSeek = null;
        DataSourceUtil.closeQuietly(this.currentDataSource);
        this.currentDataSource = null;
    }

    public int getTrackCount() {
        return this.tracks.size();
    }

    public MediaFormat getTrackFormat(int i) {
        MediaExtractorTrack mediaExtractorTrack = this.tracks.get(i);
        MediaFormat mediaFormatCreateDownstreamMediaFormat = mediaExtractorTrack.createDownstreamMediaFormat(this.formatHolder, this.sampleHolderWithBufferReplacementDisabled);
        long j = mediaExtractorTrack.sampleQueue.trackDurationUs;
        if (j != C.TIME_UNSET) {
            mediaFormatCreateDownstreamMediaFormat.setLong("durationUs", j);
            return mediaFormatCreateDownstreamMediaFormat;
        }
        SeekMap seekMap = this.seekMap;
        if (seekMap != null && seekMap.getDurationUs() != C.TIME_UNSET) {
            mediaFormatCreateDownstreamMediaFormat.setLong("durationUs", this.seekMap.getDurationUs());
        }
        return mediaFormatCreateDownstreamMediaFormat;
    }

    public void selectTrack(int i) {
        this.selectedTrackIndices.add(Integer.valueOf(i));
    }

    public void unselectTrack(int i) {
        this.selectedTrackIndices.remove(Integer.valueOf(i));
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0042  */
    public void seekTo(long j, int i) {
        SeekMap.SeekPoints seekPoints;
        SeekPoint seekPoint;
        if (this.seekMap == null) {
            return;
        }
        if (this.selectedTrackIndices.size() == 1) {
            SeekMap seekMap = this.seekMap;
            if (seekMap instanceof TrackAwareSeekMap) {
                TrackAwareSeekMap trackAwareSeekMap = (TrackAwareSeekMap) seekMap;
                int idOfBackingTrack = this.tracks.get(this.selectedTrackIndices.iterator().next().intValue()).getIdOfBackingTrack();
                if (trackAwareSeekMap.isSeekable(idOfBackingTrack)) {
                    seekPoints = trackAwareSeekMap.getSeekPoints(j, idOfBackingTrack);
                } else {
                    seekPoints = trackAwareSeekMap.getSeekPoints(j);
                }
            } else {
                seekPoints = this.seekMap.getSeekPoints(j);
            }
        } else {
            seekPoints = this.seekMap.getSeekPoints(j);
        }
        if (i == 0) {
            seekPoint = seekPoints.first;
        } else if (i == 1) {
            seekPoint = seekPoints.second;
        } else {
            if (i != 2) {
                throw new IllegalArgumentException();
            }
            if (Math.abs(j - seekPoints.second.timeUs) < Math.abs(j - seekPoints.first.timeUs)) {
                seekPoint = seekPoints.second;
            } else {
                seekPoint = seekPoints.first;
            }
        }
        this.sampleMetadataQueue.clear();
        for (int i2 = 0; i2 < this.sampleQueues.size(); i2++) {
            this.sampleQueues.valueAt(i2).reset();
        }
        this.pendingSeek = seekPoint;
    }

    public boolean advance() {
        if (!advanceToSampleOrEndOfInput()) {
            return false;
        }
        skipOneSample();
        return advanceToSampleOrEndOfInput();
    }

    public int readSampleData(ByteBuffer byteBuffer, int i) {
        if (!advanceToSampleOrEndOfInput()) {
            return -1;
        }
        byteBuffer.position(i);
        byteBuffer.limit(byteBuffer.capacity());
        this.sampleHolderWithBufferReplacementDisabled.data = byteBuffer;
        peekNextSelectedTrackSample(this.sampleHolderWithBufferReplacementDisabled);
        byteBuffer.flip();
        byteBuffer.position(i);
        this.sampleHolderWithBufferReplacementDisabled.data = null;
        return byteBuffer.remaining();
    }

    public int getSampleTrackIndex() {
        if (advanceToSampleOrEndOfInput()) {
            return this.sampleMetadataQueue.peekFirst().trackIndex;
        }
        return -1;
    }

    public long getSampleSize() {
        if (!advanceToSampleOrEndOfInput()) {
            return -1L;
        }
        peekNextSelectedTrackSample(this.sampleHolderWithBufferReplacementEnabled);
        ByteBuffer byteBuffer = (ByteBuffer) Preconditions.checkNotNull(this.sampleHolderWithBufferReplacementEnabled.data);
        int iPosition = byteBuffer.position();
        byteBuffer.position(0);
        return iPosition;
    }

    public long getSampleTime() {
        if (advanceToSampleOrEndOfInput()) {
            return this.sampleMetadataQueue.peekFirst().timeUs;
        }
        return -1L;
    }

    public int getSampleFlags() {
        if (advanceToSampleOrEndOfInput()) {
            return this.sampleMetadataQueue.peekFirst().flags;
        }
        return -1;
    }

    public boolean getSampleCryptoInfo(MediaCodec.CryptoInfo cryptoInfo) {
        if (!advanceToSampleOrEndOfInput() || (this.sampleMetadataQueue.peekFirst().flags & 2) == 0) {
            return false;
        }
        peekNextSelectedTrackSample(this.sampleHolderWithBufferReplacementEnabled);
        populatePlatformCryptoInfoParameters(cryptoInfo);
        return true;
    }

    private void populatePlatformCryptoInfoParameters(MediaCodec.CryptoInfo cryptoInfo) {
        MediaCodec.CryptoInfo frameworkCryptoInfo = ((CryptoInfo) Preconditions.checkNotNull(this.sampleHolderWithBufferReplacementEnabled.cryptoInfo)).getFrameworkCryptoInfo();
        cryptoInfo.numSubSamples = frameworkCryptoInfo.numSubSamples;
        cryptoInfo.numBytesOfClearData = frameworkCryptoInfo.numBytesOfClearData;
        cryptoInfo.numBytesOfEncryptedData = frameworkCryptoInfo.numBytesOfEncryptedData;
        cryptoInfo.key = frameworkCryptoInfo.key;
        cryptoInfo.iv = frameworkCryptoInfo.iv;
        cryptoInfo.mode = frameworkCryptoInfo.mode;
    }

    public void setLogSessionId(LogSessionId logSessionId) {
        if (logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
            return;
        }
        this.logSessionId = logSessionId;
    }

    public LogSessionId getLogSessionId() {
        LogSessionId logSessionId = this.logSessionId;
        return logSessionId != null ? logSessionId : LogSessionId.LOG_SESSION_ID_NONE;
    }

    public DrmInitData getDrmInitData() {
        for (int i = 0; i < this.tracks.size(); i++) {
            Format format = this.tracks.get(i).getFormat(this.formatHolder, this.sampleHolderWithBufferReplacementDisabled);
            if (format.drmInitData != null) {
                return format.drmInitData;
            }
        }
        return null;
    }

    public long getCachedDuration() {
        if (!advanceToSampleOrEndOfInput()) {
            return 0L;
        }
        long jMax = Long.MIN_VALUE;
        long jMax2 = Long.MIN_VALUE;
        for (int i = 0; i < this.tracks.size(); i++) {
            MediaExtractorSampleQueue mediaExtractorSampleQueue = this.tracks.get(i).sampleQueue;
            jMax2 = Math.max(jMax2, mediaExtractorSampleQueue.getLargestReadTimestampUs());
            jMax = Math.max(jMax, mediaExtractorSampleQueue.getLargestQueuedTimestampUs());
        }
        Preconditions.checkState(jMax != Long.MIN_VALUE);
        if (jMax2 == jMax) {
            return 0L;
        }
        return (jMax - (jMax2 != Long.MIN_VALUE ? jMax2 : 0L)) + 10000;
    }

    public boolean hasCacheReachedEndOfStream() {
        return getCachedDuration() == 0;
    }

    public PersistableBundle getMetrics() {
        PersistableBundle persistableBundle = new PersistableBundle();
        Extractor extractor = this.currentExtractor;
        if (extractor != null) {
            persistableBundle.putString("android.media.mediaextractor.fmt", extractor.getUnderlyingImplementation().getClass().getSimpleName());
        }
        if (!this.tracks.isEmpty()) {
            Format format = this.tracks.get(0).getFormat(this.formatHolder, this.sampleHolderWithBufferReplacementDisabled);
            if (format.containerMimeType != null) {
                persistableBundle.putString("android.media.mediaextractor.mime", format.containerMimeType);
            }
        }
        persistableBundle.putInt("android.media.mediaextractor.ntrk", this.tracks.size());
        return persistableBundle;
    }

    public Map<UUID, byte[]> getPsshInfo() {
        PsshAtomUtil.PsshAtom psshAtom;
        DrmInitData drmInitData = getDrmInitData();
        if (drmInitData == null) {
            return null;
        }
        HashMap map = new HashMap();
        for (int i = 0; i < drmInitData.schemeDataCount; i++) {
            DrmInitData.SchemeData schemeData = drmInitData.get(i);
            if (schemeData.data != null && (psshAtom = PsshAtomUtil.parsePsshAtom(schemeData.data)) != null) {
                map.put(psshAtom.uuid, psshAtom.schemeData);
            }
        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    private void peekNextSelectedTrackSample(DecoderInputBuffer decoderInputBuffer) {
        MediaExtractorSampleQueue mediaExtractorSampleQueue = this.tracks.get(((SampleMetadataQueue.SampleMetadata) Preconditions.checkNotNull(this.sampleMetadataQueue.peekFirst())).trackIndex).sampleQueue;
        int i = mediaExtractorSampleQueue.read(this.formatHolder, decoderInputBuffer, 1, false);
        if (i == -5) {
            i = mediaExtractorSampleQueue.read(this.formatHolder, decoderInputBuffer, 1, false);
        }
        this.formatHolder.clear();
        Preconditions.checkState(i == -4);
    }

    private Extractor selectExtractor(ExtractorInput extractorInput) throws IOException {
        Extractor extractor;
        Extractor[] extractorArrCreateExtractors = this.extractorsFactory.createExtractors();
        int length = extractorArrCreateExtractors.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                extractor = null;
                break;
            }
            extractor = extractorArrCreateExtractors[i];
            try {
                if (extractor.sniff(extractorInput)) {
                    extractorInput.resetPeekPosition();
                    break;
                }
                extractorInput.resetPeekPosition();
                i++;
            } catch (EOFException unused) {
            } catch (Throwable th) {
                extractorInput.resetPeekPosition();
                throw th;
            }
        }
        if (extractor != null) {
            return extractor;
        }
        throw new UnrecognizedInputFormatException("None of the available extractors (" + Joiner.on(", ").join(Lists.transform(ImmutableList.copyOf(extractorArrCreateExtractors), new Function() { // from class: androidx.media3.exoplayer.MediaExtractorCompatInternal$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return ((Extractor) obj).getUnderlyingImplementation().getClass().getSimpleName();
            }
        })) + ") could read the stream.", (Uri) Preconditions.checkNotNull(((DataSource) Preconditions.checkNotNull(this.currentDataSource)).getUri()), ImmutableList.of());
    }

    @EnsuresNonNullIf(expression = {"sampleMetadataQueue.peekFirst()"}, result = true)
    private boolean advanceToSampleOrEndOfInput() {
        try {
            maybeResolvePendingSeek();
            boolean z = false;
            while (true) {
                if (this.sampleMetadataQueue.isEmpty()) {
                    if (z) {
                        return false;
                    }
                    try {
                        int i = ((Extractor) Preconditions.checkNotNull(this.currentExtractor)).read((ExtractorInput) Preconditions.checkNotNull(this.currentExtractorInput), this.positionHolder);
                        if (i == -1) {
                            z = true;
                        } else if (i == 1) {
                            this.currentExtractorInput = reopenCurrentDataSource(this.positionHolder.position);
                        }
                    } catch (Exception | OutOfMemoryError e) {
                        Log.w(TAG, "Treating exception as the end of input.", e);
                    }
                } else {
                    if (this.selectedTrackIndices.contains(Integer.valueOf(((SampleMetadataQueue.SampleMetadata) Preconditions.checkNotNull(this.sampleMetadataQueue.peekFirst())).trackIndex))) {
                        return true;
                    }
                    skipOneSample();
                }
            }
        } catch (IOException e2) {
            Log.w(TAG, "Treating exception as the end of input.", e2);
            return false;
        }
    }

    private void skipOneSample() {
        MediaExtractorTrack mediaExtractorTrack = this.tracks.get(this.sampleMetadataQueue.removeFirst().trackIndex);
        if (mediaExtractorTrack.isCompatibilityTrack) {
            return;
        }
        mediaExtractorTrack.discardFrontSample();
    }

    private ExtractorInput reopenCurrentDataSource(long j) throws IOException {
        DataSource dataSource = (DataSource) Preconditions.checkNotNull(this.currentDataSource);
        Uri uri = (Uri) Preconditions.checkNotNull(dataSource.getUri());
        DataSourceUtil.closeQuietly(dataSource);
        long jOpen = dataSource.open(buildDataSpec(uri, this.offsetInCurrentFile + j));
        if (jOpen != -1) {
            jOpen += j;
        }
        return new DefaultExtractorInput(dataSource, j, jOpen);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void onSampleQueueFormatInitialized(MediaExtractorSampleQueue mediaExtractorSampleQueue, Format format) {
        boolean z = true;
        this.upstreamFormatsCount++;
        mediaExtractorSampleQueue.setMainTrackIndex(this.tracks.size());
        Object[] objArr = 0;
        this.tracks.add(new MediaExtractorTrack(mediaExtractorSampleQueue, false, null));
        String alternativeCodecMimeType = MediaCodecUtil.getAlternativeCodecMimeType(format);
        if (alternativeCodecMimeType != null) {
            mediaExtractorSampleQueue.setCompatibilityTrackIndex(this.tracks.size());
            this.tracks.add(new MediaExtractorTrack(mediaExtractorSampleQueue, z, alternativeCodecMimeType));
        }
    }

    private void maybeResolvePendingSeek() throws IOException {
        SeekPoint seekPoint = this.pendingSeek;
        if (seekPoint == null) {
            return;
        }
        SeekPoint seekPoint2 = (SeekPoint) Preconditions.checkNotNull(seekPoint);
        ((Extractor) Preconditions.checkNotNull(this.currentExtractor)).seek(seekPoint2.position, seekPoint2.timeUs);
        this.currentExtractorInput = reopenCurrentDataSource(seekPoint2.position);
        this.pendingSeek = null;
    }

    private DataSpec buildDataSpec(Uri uri, long j) {
        DataSpec.Builder flags = new DataSpec.Builder().setUri(uri).setPosition(j).setFlags(6);
        Map<String, String> map = this.httpRequestHeaders;
        if (map != null) {
            flags.setHttpRequestHeaders(map);
        }
        return flags.build();
    }

    private final class ExtractorOutputImpl implements ExtractorOutput {
        private ExtractorOutputImpl() {
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public TrackOutput track(int i, int i2) {
            MediaExtractorSampleQueue mediaExtractorSampleQueue = (MediaExtractorSampleQueue) MediaExtractorCompatInternal.this.sampleQueues.get(i);
            if (mediaExtractorSampleQueue != null) {
                return mediaExtractorSampleQueue;
            }
            if (MediaExtractorCompatInternal.this.tracksEnded) {
                return new DiscardingTrackOutput();
            }
            MediaExtractorCompatInternal mediaExtractorCompatInternal = MediaExtractorCompatInternal.this;
            MediaExtractorSampleQueue mediaExtractorSampleQueue2 = mediaExtractorCompatInternal.new MediaExtractorSampleQueue(mediaExtractorCompatInternal.allocator, i);
            MediaExtractorCompatInternal.this.sampleQueues.put(i, mediaExtractorSampleQueue2);
            return mediaExtractorSampleQueue2;
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void endTracks() {
            MediaExtractorCompatInternal.this.tracksEnded = true;
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void seekMap(SeekMap seekMap) {
            MediaExtractorCompatInternal.this.seekMap = seekMap;
        }
    }

    private static final class MediaExtractorTrack {
        public final String compatibilityTrackMimeType;
        public final boolean isCompatibilityTrack;
        public final MediaExtractorSampleQueue sampleQueue;

        private MediaExtractorTrack(MediaExtractorSampleQueue mediaExtractorSampleQueue, boolean z, String str) {
            this.sampleQueue = mediaExtractorSampleQueue;
            this.isCompatibilityTrack = z;
            this.compatibilityTrackMimeType = str;
        }

        public MediaFormat createDownstreamMediaFormat(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer) {
            Format format = getFormat(formatHolder, decoderInputBuffer);
            MediaFormat mediaFormatCreateMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(format);
            if (this.compatibilityTrackMimeType != null) {
                if (Build.VERSION.SDK_INT >= 29) {
                    mediaFormatCreateMediaFormatFromFormat.removeKey("codecs-string");
                }
                mediaFormatCreateMediaFormatFromFormat.setString("mime", this.compatibilityTrackMimeType);
            }
            Pair<Integer, Integer> codecProfileAndLevel = CodecSpecificDataUtil.getCodecProfileAndLevel(format);
            if (codecProfileAndLevel != null) {
                mediaFormatCreateMediaFormatFromFormat.setInteger(Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
                mediaFormatCreateMediaFormatFromFormat.setInteger("level", ((Integer) codecProfileAndLevel.second).intValue());
            }
            return mediaFormatCreateMediaFormatFromFormat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Format getFormat(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer) {
            formatHolder.clear();
            this.sampleQueue.read(formatHolder, decoderInputBuffer, 2, false);
            Format format = (Format) Preconditions.checkNotNull(formatHolder.format);
            formatHolder.clear();
            return format;
        }

        public void discardFrontSample() {
            this.sampleQueue.skip(1);
            this.sampleQueue.discardToRead();
        }

        public int getIdOfBackingTrack() {
            return this.sampleQueue.trackId;
        }

        public String toString() {
            return String.format("MediaExtractorSampleQueue: %s, isCompatibilityTrack: %s, compatibilityTrackMimeType: %s", this.sampleQueue, Boolean.valueOf(this.isCompatibilityTrack), this.compatibilityTrackMimeType);
        }
    }

    private final class MediaExtractorSampleQueue extends SampleQueue {
        private int compatibilityTrackIndex;
        private int mainTrackIndex;
        public long trackDurationUs;
        public final int trackId;

        public MediaExtractorSampleQueue(Allocator allocator, int i) {
            super(allocator, null, null);
            this.trackId = i;
            this.trackDurationUs = C.TIME_UNSET;
            this.mainTrackIndex = -1;
            this.compatibilityTrackIndex = -1;
        }

        public void setMainTrackIndex(int i) {
            this.mainTrackIndex = i;
        }

        public void setCompatibilityTrackIndex(int i) {
            this.compatibilityTrackIndex = i;
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue, androidx.media3.extractor.TrackOutput
        public void durationUs(long j) {
            this.trackDurationUs = j;
            super.durationUs(j);
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue
        public Format getAdjustedUpstreamFormat(Format format) {
            if (getUpstreamFormat() == null) {
                MediaExtractorCompatInternal.this.onSampleQueueFormatInitialized(this, format);
            }
            return super.getAdjustedUpstreamFormat(format);
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue, androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            int i4 = i & (-536870913);
            Preconditions.checkState(this.mainTrackIndex != -1);
            int writeIndex = getWriteIndex();
            super.sampleMetadata(j, i4, i2, i3, cryptoData);
            if (getWriteIndex() == writeIndex + 1) {
                queueSampleMetadata(j, i4);
            }
        }

        public String toString() {
            return String.format("trackId: %s, mainTrackIndex: %s, compatibilityTrackIndex: %s", Integer.valueOf(this.trackId), Integer.valueOf(this.mainTrackIndex), Integer.valueOf(this.compatibilityTrackIndex));
        }

        private void queueSampleMetadata(long j, int i) {
            int i2 = ((1073741824 & i) != 0 ? 2 : 0) | ((i & 1) != 0 ? 1 : 0);
            if (this.compatibilityTrackIndex != -1) {
                MediaExtractorCompatInternal.this.sampleMetadataQueue.addLast(j, i2, this.compatibilityTrackIndex);
            }
            MediaExtractorCompatInternal.this.sampleMetadataQueue.addLast(j, i2, this.mainTrackIndex);
        }
    }

    private static final class SampleMetadataQueue {
        private final ArrayDeque<SampleMetadata> sampleMetadataPool = new ArrayDeque<>();
        private final ArrayDeque<SampleMetadata> sampleMetadataQueue = new ArrayDeque<>();

        public void addLast(long j, int i, int i2) {
            this.sampleMetadataQueue.addLast(obtainSampleMetadata(j, i, i2));
        }

        public SampleMetadata removeFirst() {
            SampleMetadata sampleMetadataRemoveFirst = this.sampleMetadataQueue.removeFirst();
            this.sampleMetadataPool.push(sampleMetadataRemoveFirst);
            return sampleMetadataRemoveFirst;
        }

        public SampleMetadata peekFirst() {
            return this.sampleMetadataQueue.peekFirst();
        }

        public void clear() {
            Iterator<SampleMetadata> it = this.sampleMetadataQueue.iterator();
            while (it.hasNext()) {
                this.sampleMetadataPool.push(it.next());
            }
            this.sampleMetadataQueue.clear();
        }

        public boolean isEmpty() {
            return this.sampleMetadataQueue.isEmpty();
        }

        private SampleMetadata obtainSampleMetadata(long j, int i, int i2) {
            SampleMetadata sampleMetadataPop;
            if (this.sampleMetadataPool.isEmpty()) {
                sampleMetadataPop = new SampleMetadata(j, i, i2);
            } else {
                sampleMetadataPop = this.sampleMetadataPool.pop();
            }
            sampleMetadataPop.set(j, i, i2);
            return sampleMetadataPop;
        }

        private static final class SampleMetadata {
            public int flags;
            public long timeUs;
            public int trackIndex;

            public SampleMetadata(long j, int i, int i2) {
                set(j, i, i2);
            }

            public void set(long j, int i, int i2) {
                this.timeUs = j;
                this.flags = i;
                this.trackIndex = i2;
            }
        }
    }
}
