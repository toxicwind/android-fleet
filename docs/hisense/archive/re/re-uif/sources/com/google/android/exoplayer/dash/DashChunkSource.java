package com.google.android.exoplayer.dash;

import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer.BehindLiveWindowException;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.TimeRange;
import com.google.android.exoplayer.chunk.Chunk;
import com.google.android.exoplayer.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer.chunk.ChunkOperationHolder;
import com.google.android.exoplayer.chunk.ChunkSource;
import com.google.android.exoplayer.chunk.ContainerMediaChunk;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.FormatEvaluator;
import com.google.android.exoplayer.chunk.InitializationChunk;
import com.google.android.exoplayer.chunk.MediaChunk;
import com.google.android.exoplayer.chunk.SingleSampleMediaChunk;
import com.google.android.exoplayer.dash.mpd.AdaptationSet;
import com.google.android.exoplayer.dash.mpd.ContentProtection;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import com.google.android.exoplayer.dash.mpd.Period;
import com.google.android.exoplayer.dash.mpd.RangedUri;
import com.google.android.exoplayer.dash.mpd.Representation;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.ChunkIndex;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.SyncFrameExtractor;
import com.google.android.exoplayer.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer.extractor.webm.WebmExtractor;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.util.Clock;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.SystemClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class DashChunkSource implements ChunkSource, DashTrackSelector.Output {
    private final FormatEvaluator adaptiveFormatEvaluator;
    private TimeRange availableRange;
    private final long[] availableRangeValues;
    private MediaPresentationDescription currentManifest;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetUs;
    private ExposedTrack enabledTrack;
    private final FormatEvaluator.Evaluation evaluation;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private final int eventSourceId;
    private IOException fatalError;
    private boolean lastChunkWasInitialization;
    private final boolean live;
    private final long liveEdgeLatencyUs;
    private final ManifestFetcher<MediaPresentationDescription> manifestFetcher;
    private int nextPeriodHolderIndex;
    private final SparseArray<PeriodHolder> periodHolders;
    private boolean prepareCalled;
    private MediaPresentationDescription processedManifest;
    private boolean startAtLiveEdge;
    private final Clock systemClock;
    private final DashTrackSelector trackSelector;
    private final ArrayList<ExposedTrack> tracks;

    public interface EventListener {
        void onAvailableRangeChanged(int i, TimeRange timeRange);

        void onDrmInitData(DrmInitData drmInitData);
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void onChunkLoadError(Chunk chunk, Exception exc) {
    }

    public DashChunkSource(ManifestFetcher<MediaPresentationDescription> manifestFetcher, DashTrackSelector dashTrackSelector, DataSource dataSource, FormatEvaluator formatEvaluator, long j, long j2, Handler handler, EventListener eventListener, int i) {
        this(manifestFetcher, manifestFetcher.getManifest(), dashTrackSelector, dataSource, formatEvaluator, new SystemClock(), j * 1000, j2 * 1000, true, handler, eventListener, i);
    }

    DashChunkSource(ManifestFetcher<MediaPresentationDescription> manifestFetcher, MediaPresentationDescription mediaPresentationDescription, DashTrackSelector dashTrackSelector, DataSource dataSource, FormatEvaluator formatEvaluator, Clock clock, long j, long j2, boolean z, Handler handler, EventListener eventListener, int i) {
        this.manifestFetcher = manifestFetcher;
        this.currentManifest = mediaPresentationDescription;
        this.trackSelector = dashTrackSelector;
        this.dataSource = dataSource;
        this.adaptiveFormatEvaluator = formatEvaluator;
        this.systemClock = clock;
        this.liveEdgeLatencyUs = j;
        this.elapsedRealtimeOffsetUs = j2;
        this.startAtLiveEdge = z;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.eventSourceId = i;
        this.evaluation = new FormatEvaluator.Evaluation();
        this.availableRangeValues = new long[2];
        this.periodHolders = new SparseArray<>();
        this.tracks = new ArrayList<>();
        this.live = mediaPresentationDescription.dynamic;
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        ManifestFetcher<MediaPresentationDescription> manifestFetcher = this.manifestFetcher;
        if (manifestFetcher != null) {
            manifestFetcher.maybeThrowError();
        }
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public boolean prepare() {
        if (!this.prepareCalled) {
            this.prepareCalled = true;
            try {
                this.trackSelector.selectTracks(this.currentManifest, 0, this);
            } catch (IOException e) {
                this.fatalError = e;
            }
        }
        return this.fatalError == null;
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public int getTrackCount() {
        return this.tracks.size();
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public final MediaFormat getFormat(int i) {
        return this.tracks.get(i).trackFormat;
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void enable(int i) {
        ExposedTrack exposedTrack = this.tracks.get(i);
        this.enabledTrack = exposedTrack;
        if (exposedTrack.isAdaptive()) {
            this.adaptiveFormatEvaluator.enable();
        }
        ManifestFetcher<MediaPresentationDescription> manifestFetcher = this.manifestFetcher;
        if (manifestFetcher != null) {
            manifestFetcher.enable();
            processManifest(this.manifestFetcher.getManifest());
        } else {
            processManifest(this.currentManifest);
        }
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void continueBuffering(long j) {
        ManifestFetcher<MediaPresentationDescription> manifestFetcher = this.manifestFetcher;
        if (manifestFetcher != null && this.currentManifest.dynamic && this.fatalError == null) {
            MediaPresentationDescription manifest = manifestFetcher.getManifest();
            if (manifest != null && manifest != this.processedManifest) {
                processManifest(manifest);
                this.processedManifest = manifest;
            }
            long j2 = this.currentManifest.minUpdatePeriod;
            if (j2 == 0) {
                j2 = 5000;
            }
            if (android.os.SystemClock.elapsedRealtime() > this.manifestFetcher.getManifestLoadStartTimestamp() + j2) {
                this.manifestFetcher.requestRefresh();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:72:0x016d  */
    /* JADX WARN: Code duplicated, block: B:73:0x0172  */
    /* JADX WARN: Code duplicated, block: B:76:0x0177  */
    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public final void getChunkOperation(List<? extends MediaChunk> list, long j, ChunkOperationHolder chunkOperationHolder) {
        PeriodHolder periodHolder;
        boolean z;
        PeriodHolder periodHolderValueAt;
        RepresentationHolder representationHolder;
        Representation representation;
        MediaFormat mediaFormat;
        RangedUri initializationUri;
        RangedUri indexUri;
        int nextChunkIndex;
        long jMax;
        Chunk chunk;
        long j2 = j;
        if (this.fatalError != null) {
            chunkOperationHolder.chunk = null;
            return;
        }
        this.evaluation.queueSize = list.size();
        if (this.evaluation.format == null || !this.lastChunkWasInitialization) {
            if (this.enabledTrack.isAdaptive()) {
                this.adaptiveFormatEvaluator.evaluate(list, j, this.enabledTrack.adaptiveFormats, this.evaluation);
            } else {
                this.evaluation.format = this.enabledTrack.fixedFormat;
                this.evaluation.trigger = 2;
            }
        }
        FormatEvaluator.Evaluation evaluation = this.evaluation;
        Format format = evaluation.format;
        int i = evaluation.queueSize;
        chunkOperationHolder.queueSize = i;
        if (format == null) {
            chunkOperationHolder.chunk = null;
            return;
        }
        if (i == list.size() && (chunk = chunkOperationHolder.chunk) != null && chunk.format.equals(format)) {
            return;
        }
        chunkOperationHolder.chunk = null;
        this.availableRange.getCurrentBoundsUs(this.availableRangeValues);
        if (list.isEmpty()) {
            if (this.live) {
                if (j2 != 0) {
                    this.startAtLiveEdge = false;
                }
                if (this.startAtLiveEdge) {
                    long[] jArr = this.availableRangeValues;
                    jMax = Math.max(jArr[0], jArr[1] - this.liveEdgeLatencyUs);
                } else {
                    jMax = Math.max(Math.min(j2, this.availableRangeValues[1] - 1), this.availableRangeValues[0]);
                }
            } else {
                jMax = j2;
            }
            periodHolderValueAt = findPeriodHolder(jMax);
            j2 = jMax;
        } else {
            if (this.startAtLiveEdge) {
                this.startAtLiveEdge = false;
            }
            MediaChunk mediaChunk = list.get(chunkOperationHolder.queueSize - 1);
            long j3 = mediaChunk.endTimeUs;
            if (this.live && j3 < this.availableRangeValues[0]) {
                this.fatalError = new BehindLiveWindowException();
                return;
            }
            if (this.currentManifest.dynamic && j3 >= this.availableRangeValues[1]) {
                return;
            }
            SparseArray<PeriodHolder> sparseArray = this.periodHolders;
            PeriodHolder periodHolderValueAt2 = sparseArray.valueAt(sparseArray.size() - 1);
            if (mediaChunk.parentId == periodHolderValueAt2.localIndex && periodHolderValueAt2.representationHolders.get(mediaChunk.format.id).isBeyondLastSegment(mediaChunk.getNextChunkIndex())) {
                if (this.currentManifest.dynamic) {
                    return;
                }
                chunkOperationHolder.endOfStream = true;
                return;
            }
            periodHolder = this.periodHolders.get(mediaChunk.parentId);
            if (periodHolder == null) {
                periodHolderValueAt = this.periodHolders.valueAt(0);
            } else {
                if (periodHolder.isIndexUnbounded() || !periodHolder.representationHolders.get(mediaChunk.format.id).isBeyondLastSegment(mediaChunk.getNextChunkIndex())) {
                    z = false;
                } else {
                    periodHolderValueAt = this.periodHolders.get(mediaChunk.parentId + 1);
                }
                representationHolder = periodHolder.representationHolders.get(format.id);
                representation = representationHolder.representation;
                mediaFormat = representationHolder.mediaFormat;
                if (mediaFormat == null) {
                    initializationUri = representation.getInitializationUri();
                } else {
                    initializationUri = null;
                }
                indexUri = representationHolder.segmentIndex == null ? representation.getIndexUri() : null;
                if (initializationUri == null || indexUri != null) {
                    Chunk chunkNewInitializationChunk = newInitializationChunk(initializationUri, indexUri, representation, representationHolder.extractorWrapper, this.dataSource, periodHolder.localIndex, this.evaluation.trigger);
                    this.lastChunkWasInitialization = true;
                    chunkOperationHolder.chunk = chunkNewInitializationChunk;
                }
                if (list.isEmpty()) {
                    nextChunkIndex = representationHolder.getSegmentNum(j2);
                } else if (z) {
                    nextChunkIndex = representationHolder.getFirstAvailableSegmentNum();
                } else {
                    nextChunkIndex = list.get(chunkOperationHolder.queueSize - 1).getNextChunkIndex();
                }
                Chunk chunkNewMediaChunk = newMediaChunk(periodHolder, representationHolder, this.dataSource, mediaFormat, this.enabledTrack, nextChunkIndex, this.evaluation.trigger, mediaFormat != null);
                this.lastChunkWasInitialization = false;
                chunkOperationHolder.chunk = chunkNewMediaChunk;
                return;
            }
        }
        periodHolder = periodHolderValueAt;
        z = true;
        representationHolder = periodHolder.representationHolders.get(format.id);
        representation = representationHolder.representation;
        mediaFormat = representationHolder.mediaFormat;
        if (mediaFormat == null) {
            initializationUri = representation.getInitializationUri();
        } else {
            initializationUri = null;
        }
        if (representationHolder.segmentIndex == null) {
        }
        if (initializationUri == null) {
        }
        Chunk chunkNewInitializationChunk2 = newInitializationChunk(initializationUri, indexUri, representation, representationHolder.extractorWrapper, this.dataSource, periodHolder.localIndex, this.evaluation.trigger);
        this.lastChunkWasInitialization = true;
        chunkOperationHolder.chunk = chunkNewInitializationChunk2;
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof InitializationChunk) {
            InitializationChunk initializationChunk = (InitializationChunk) chunk;
            String str = initializationChunk.format.id;
            PeriodHolder periodHolder = this.periodHolders.get(initializationChunk.parentId);
            if (periodHolder == null) {
                return;
            }
            RepresentationHolder representationHolder = periodHolder.representationHolders.get(str);
            if (initializationChunk.hasFormat()) {
                representationHolder.mediaFormat = initializationChunk.getFormat();
            }
            if (representationHolder.segmentIndex == null && initializationChunk.hasSeekMap()) {
                representationHolder.segmentIndex = new DashWrappingSegmentIndex((ChunkIndex) initializationChunk.getSeekMap(), initializationChunk.dataSpec.uri.toString());
            }
            if (periodHolder.drmInitData == null && initializationChunk.hasDrmInitData()) {
                periodHolder.drmInitData = initializationChunk.getDrmInitData();
                notifyDrmInitData(periodHolder.drmInitData);
            }
        }
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void disable(List<? extends MediaChunk> list) {
        if (this.enabledTrack.isAdaptive()) {
            this.adaptiveFormatEvaluator.disable();
        }
        ManifestFetcher<MediaPresentationDescription> manifestFetcher = this.manifestFetcher;
        if (manifestFetcher != null) {
            manifestFetcher.disable();
        }
        this.periodHolders.clear();
        this.evaluation.format = null;
        this.availableRange = null;
        this.fatalError = null;
        this.enabledTrack = null;
    }

    @Override // com.google.android.exoplayer.dash.DashTrackSelector.Output
    public void adaptiveTrack(MediaPresentationDescription mediaPresentationDescription, int i, int i2, int[] iArr) {
        if (this.adaptiveFormatEvaluator == null) {
            Log.w("DashChunkSource", "Skipping adaptive track (missing format evaluator)");
            return;
        }
        AdaptationSet adaptationSet = mediaPresentationDescription.getPeriod(i).adaptationSets.get(i2);
        int length = iArr.length;
        Format[] formatArr = new Format[length];
        Format format = null;
        int iMax = 0;
        int iMax2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            Format format2 = adaptationSet.representations.get(iArr[i3]).format;
            if (format == null || format2.height > iMax2) {
                format = format2;
            }
            iMax = Math.max(iMax, format2.width);
            iMax2 = Math.max(iMax2, format2.height);
            formatArr[i3] = format2;
        }
        Arrays.sort(formatArr, new Format.DecreasingBandwidthComparator());
        long j = this.live ? -1L : mediaPresentationDescription.duration * 1000;
        String mediaMimeType = getMediaMimeType(format);
        if (mediaMimeType == null) {
            Log.w("DashChunkSource", "Skipped adaptive track (unknown media mime type)");
            return;
        }
        MediaFormat trackFormat = getTrackFormat(adaptationSet.type, format, mediaMimeType, j);
        if (trackFormat == null) {
            Log.w("DashChunkSource", "Skipped adaptive track (unknown media format)");
        } else {
            this.tracks.add(new ExposedTrack(trackFormat.copyAsAdaptive(null), i2, formatArr, iMax, iMax2));
        }
    }

    @Override // com.google.android.exoplayer.dash.DashTrackSelector.Output
    public void fixedTrack(MediaPresentationDescription mediaPresentationDescription, int i, int i2, int i3) {
        AdaptationSet adaptationSet = mediaPresentationDescription.getPeriod(i).adaptationSets.get(i2);
        Format format = adaptationSet.representations.get(i3).format;
        String mediaMimeType = getMediaMimeType(format);
        if (mediaMimeType == null) {
            Log.w("DashChunkSource", "Skipped track " + format.id + " (unknown media mime type)");
            return;
        }
        MediaFormat trackFormat = getTrackFormat(adaptationSet.type, format, mediaMimeType, mediaPresentationDescription.dynamic ? -1L : mediaPresentationDescription.duration * 1000);
        if (trackFormat == null) {
            Log.w("DashChunkSource", "Skipped track " + format.id + " (unknown media format)");
            return;
        }
        this.tracks.add(new ExposedTrack(trackFormat, i2, format));
    }

    private static MediaFormat getTrackFormat(int i, Format format, String str, long j) {
        if (i == 0) {
            return MediaFormat.createVideoFormat(format.id, str, format.bitrate, -1, j, format.width, format.height, null);
        }
        if (i == 1) {
            return MediaFormat.createAudioFormat(format.id, str, format.bitrate, -1, j, format.audioChannels, format.audioSamplingRate, null, format.language);
        }
        if (i != 2) {
            return null;
        }
        return MediaFormat.createTextFormat(format.id, str, format.bitrate, j, format.language);
    }

    private static String getMediaMimeType(Format format) {
        String str = format.mimeType;
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(format.codecs);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(format.codecs);
        }
        if (mimeTypeIsRawText(str)) {
            return str;
        }
        if ("application/mp4".equals(str)) {
            if ("stpp".equals(format.codecs)) {
                return "application/ttml+xml";
            }
            if ("wvtt".equals(format.codecs)) {
                return "application/x-mp4vtt";
            }
            return null;
        }
        if (MimeTypes.TEXT_M4T.equals(str) && "smtt".equals(format.codecs)) {
            return "application/ttml+xml";
        }
        return null;
    }

    static boolean mimeTypeIsWebm(String str) {
        return str.startsWith("video/webm") || str.startsWith("audio/webm") || str.startsWith("application/webm");
    }

    static boolean mimeTypeIsRawText(String str) {
        return "text/vtt".equals(str) || "application/ttml+xml".equals(str);
    }

    private Chunk newInitializationChunk(RangedUri rangedUri, RangedUri rangedUri2, Representation representation, ChunkExtractorWrapper chunkExtractorWrapper, DataSource dataSource, int i, int i2) {
        if (rangedUri != null) {
            RangedUri rangedUriAttemptMerge = rangedUri.attemptMerge(rangedUri2, representation.baseUrl);
            if (rangedUriAttemptMerge != null) {
                rangedUri = rangedUriAttemptMerge;
            }
        } else {
            rangedUri = rangedUri2;
        }
        return new InitializationChunk(dataSource, new DataSpec(rangedUri.resolveUri(representation.baseUrl), rangedUri.start, rangedUri.length, representation.getCacheKey()), i2, representation.format, chunkExtractorWrapper, i, true);
    }

    protected Chunk newMediaChunk(PeriodHolder periodHolder, RepresentationHolder representationHolder, DataSource dataSource, MediaFormat mediaFormat, ExposedTrack exposedTrack, int i, int i2, boolean z) {
        Representation representation = representationHolder.representation;
        Format format = representation.format;
        long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(i);
        long segmentEndTimeUs = representationHolder.getSegmentEndTimeUs(i);
        RangedUri segmentUrl = representationHolder.getSegmentUrl(i);
        DataSpec dataSpec = new DataSpec(segmentUrl.resolveUri(representation.baseUrl), segmentUrl.start, segmentUrl.length, representation.getCacheKey());
        long j = periodHolder.startTimeUs - representation.presentationTimeOffsetUs;
        if (mimeTypeIsRawText(format.mimeType)) {
            return new SingleSampleMediaChunk(dataSource, dataSpec, 1, format, segmentStartTimeUs, segmentEndTimeUs, i, exposedTrack.trackFormat, null, periodHolder.localIndex);
        }
        return new ContainerMediaChunk(dataSource, dataSpec, i2, format, segmentStartTimeUs, segmentEndTimeUs, i, j, representationHolder.extractorWrapper, mediaFormat, exposedTrack.adaptiveMaxWidth, exposedTrack.adaptiveMaxHeight, periodHolder.drmInitData, z, periodHolder.localIndex);
    }

    private long getNowUnixTimeUs() {
        if (this.elapsedRealtimeOffsetUs != 0) {
            return (this.systemClock.elapsedRealtime() * 1000) + this.elapsedRealtimeOffsetUs;
        }
        return System.currentTimeMillis() * 1000;
    }

    private PeriodHolder findPeriodHolder(long j) {
        if (j < this.periodHolders.valueAt(0).getAvailableStartTimeUs()) {
            return this.periodHolders.valueAt(0);
        }
        for (int i = 0; i < this.periodHolders.size() - 1; i++) {
            PeriodHolder periodHolderValueAt = this.periodHolders.valueAt(i);
            if (j < periodHolderValueAt.getAvailableEndTimeUs()) {
                return periodHolderValueAt;
            }
        }
        SparseArray<PeriodHolder> sparseArray = this.periodHolders;
        return sparseArray.valueAt(sparseArray.size() - 1);
    }

    private void processManifest(MediaPresentationDescription mediaPresentationDescription) {
        Period period = mediaPresentationDescription.getPeriod(0);
        while (this.periodHolders.size() > 0 && this.periodHolders.valueAt(0).startTimeUs < period.startMs * 1000) {
            this.periodHolders.remove(this.periodHolders.valueAt(0).localIndex);
        }
        if (this.periodHolders.size() > mediaPresentationDescription.getPeriodCount()) {
            return;
        }
        try {
            int size = this.periodHolders.size();
            if (size > 0) {
                this.periodHolders.valueAt(0).updatePeriod(mediaPresentationDescription, 0, this.enabledTrack);
                if (size > 1) {
                    int i = size - 1;
                    this.periodHolders.valueAt(i).updatePeriod(mediaPresentationDescription, i, this.enabledTrack);
                }
            }
            for (int size2 = this.periodHolders.size(); size2 < mediaPresentationDescription.getPeriodCount(); size2++) {
                this.periodHolders.put(this.nextPeriodHolderIndex, new PeriodHolder(this.nextPeriodHolderIndex, mediaPresentationDescription, size2, this.enabledTrack));
                this.nextPeriodHolderIndex++;
            }
            TimeRange availableRange = getAvailableRange(getNowUnixTimeUs());
            TimeRange timeRange = this.availableRange;
            if (timeRange == null || !timeRange.equals(availableRange)) {
                this.availableRange = availableRange;
                notifyAvailableRangeChanged(availableRange);
            }
            this.currentManifest = mediaPresentationDescription;
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    private TimeRange getAvailableRange(long j) {
        PeriodHolder periodHolderValueAt = this.periodHolders.valueAt(0);
        SparseArray<PeriodHolder> sparseArray = this.periodHolders;
        PeriodHolder periodHolderValueAt2 = sparseArray.valueAt(sparseArray.size() - 1);
        if (!this.currentManifest.dynamic || periodHolderValueAt2.isIndexExplicit()) {
            return new TimeRange.StaticTimeRange(periodHolderValueAt.getAvailableStartTimeUs(), periodHolderValueAt2.getAvailableEndTimeUs());
        }
        long availableStartTimeUs = periodHolderValueAt.getAvailableStartTimeUs();
        long availableEndTimeUs = periodHolderValueAt2.isIndexUnbounded() ? LongCompanionObject.MAX_VALUE : periodHolderValueAt2.getAvailableEndTimeUs();
        long jElapsedRealtime = this.systemClock.elapsedRealtime() * 1000;
        MediaPresentationDescription mediaPresentationDescription = this.currentManifest;
        long j2 = jElapsedRealtime - (j - (mediaPresentationDescription.availabilityStartTime * 1000));
        long j3 = mediaPresentationDescription.timeShiftBufferDepth;
        return new TimeRange.DynamicTimeRange(availableStartTimeUs, availableEndTimeUs, j2, j3 == -1 ? -1L : j3 * 1000, this.systemClock);
    }

    private void notifyAvailableRangeChanged(final TimeRange timeRange) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.dash.DashChunkSource.1
            @Override // java.lang.Runnable
            public void run() {
                DashChunkSource.this.eventListener.onAvailableRangeChanged(DashChunkSource.this.eventSourceId, timeRange);
            }
        });
    }

    protected static final class ExposedTrack {
        private final int adaptationSetIndex;
        private final Format[] adaptiveFormats;
        public final int adaptiveMaxHeight;
        public final int adaptiveMaxWidth;
        private final Format fixedFormat;
        public final MediaFormat trackFormat;

        public ExposedTrack(MediaFormat mediaFormat, int i, Format format) {
            this.trackFormat = mediaFormat;
            this.adaptationSetIndex = i;
            this.fixedFormat = format;
            this.adaptiveFormats = null;
            this.adaptiveMaxWidth = -1;
            this.adaptiveMaxHeight = -1;
        }

        public ExposedTrack(MediaFormat mediaFormat, int i, Format[] formatArr, int i2, int i3) {
            this.trackFormat = mediaFormat;
            this.adaptationSetIndex = i;
            this.adaptiveFormats = formatArr;
            this.adaptiveMaxWidth = i2;
            this.adaptiveMaxHeight = i3;
            this.fixedFormat = null;
        }

        public boolean isAdaptive() {
            return this.adaptiveFormats != null;
        }
    }

    protected static final class RepresentationHolder {
        public final ChunkExtractorWrapper extractorWrapper;
        public MediaFormat mediaFormat;
        public final boolean mimeTypeIsRawText;
        private long periodDurationUs;
        private final long periodStartTimeUs;
        public Representation representation;
        public DashSegmentIndex segmentIndex;
        private int segmentNumShift;

        public RepresentationHolder(long j, long j2, Representation representation) {
            ChunkExtractorWrapper chunkExtractorWrapper;
            this.periodStartTimeUs = j;
            this.periodDurationUs = j2;
            this.representation = representation;
            String str = representation.format.mimeType;
            boolean zMimeTypeIsRawText = DashChunkSource.mimeTypeIsRawText(str);
            this.mimeTypeIsRawText = zMimeTypeIsRawText;
            if (zMimeTypeIsRawText) {
                chunkExtractorWrapper = null;
            } else {
                chunkExtractorWrapper = new ChunkExtractorWrapper(DashChunkSource.mimeTypeIsWebm(str) ? new WebmExtractor() : new FragmentedMp4Extractor(str));
            }
            this.extractorWrapper = chunkExtractorWrapper;
            this.segmentIndex = representation.getIndex();
        }

        public void updateRepresentation(long j, Representation representation) throws BehindLiveWindowException {
            DashSegmentIndex index = this.representation.getIndex();
            DashSegmentIndex index2 = representation.getIndex();
            this.periodDurationUs = j;
            this.representation = representation;
            if (index == null) {
                return;
            }
            this.segmentIndex = index2;
            if (index.isExplicit()) {
                int lastSegmentNum = index.getLastSegmentNum(this.periodDurationUs);
                long timeUs = index.getTimeUs(lastSegmentNum) + index.getDurationUs(lastSegmentNum, this.periodDurationUs);
                int firstSegmentNum = index2.getFirstSegmentNum();
                long timeUs2 = index2.getTimeUs(firstSegmentNum);
                if (timeUs == timeUs2) {
                    this.segmentNumShift += (index.getLastSegmentNum(this.periodDurationUs) + 1) - firstSegmentNum;
                } else {
                    if (timeUs < timeUs2) {
                        throw new BehindLiveWindowException();
                    }
                    this.segmentNumShift += index.getSegmentNum(timeUs2, this.periodDurationUs) - firstSegmentNum;
                }
            }
        }

        public int getSegmentNum(long j) {
            return this.segmentIndex.getSegmentNum(j - this.periodStartTimeUs, this.periodDurationUs) + this.segmentNumShift;
        }

        public long getSegmentStartTimeUs(int i) {
            return this.segmentIndex.getTimeUs(i - this.segmentNumShift) + this.periodStartTimeUs;
        }

        public long getSegmentEndTimeUs(int i) {
            return getSegmentStartTimeUs(i) + this.segmentIndex.getDurationUs(i - this.segmentNumShift, this.periodDurationUs);
        }

        public int getLastSegmentNum() {
            return this.segmentIndex.getLastSegmentNum(this.periodDurationUs);
        }

        public boolean isBeyondLastSegment(int i) {
            int lastSegmentNum = getLastSegmentNum();
            return lastSegmentNum != -1 && i > lastSegmentNum + this.segmentNumShift;
        }

        public int getFirstAvailableSegmentNum() {
            return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public RangedUri getSegmentUrl(int i) {
            return this.segmentIndex.getSegmentUrl(i - this.segmentNumShift);
        }
    }

    protected static final class PeriodHolder {
        private long availableEndTimeUs;
        private long availableStartTimeUs;
        private DrmInitData drmInitData;
        public List<Format> formats = new ArrayList();
        private boolean indexIsExplicit;
        private boolean indexIsUnbounded;
        public final int localIndex;
        public final HashMap<String, RepresentationHolder> representationHolders;
        private final int[] representationIndices;
        public final long startTimeUs;

        public PeriodHolder(int i, MediaPresentationDescription mediaPresentationDescription, int i2, ExposedTrack exposedTrack) {
            this.localIndex = i;
            Period period = mediaPresentationDescription.getPeriod(i2);
            long periodDurationUs = getPeriodDurationUs(mediaPresentationDescription, i2);
            AdaptationSet adaptationSet = period.adaptationSets.get(exposedTrack.adaptationSetIndex);
            List<Representation> list = adaptationSet.representations;
            this.startTimeUs = period.startMs * 1000;
            this.drmInitData = getDrmInitData(adaptationSet);
            if (exposedTrack.isAdaptive()) {
                this.representationIndices = new int[exposedTrack.adaptiveFormats.length];
                for (int i3 = 0; i3 < exposedTrack.adaptiveFormats.length; i3++) {
                    this.representationIndices[i3] = getRepresentationIndex(list, exposedTrack.adaptiveFormats[i3].id);
                }
            } else {
                this.representationIndices = new int[]{getRepresentationIndex(list, exposedTrack.fixedFormat.id)};
            }
            this.representationHolders = new HashMap<>();
            int i4 = 0;
            while (true) {
                int[] iArr = this.representationIndices;
                if (i4 < iArr.length) {
                    Representation representation = list.get(iArr[i4]);
                    this.representationHolders.put(representation.format.id, new RepresentationHolder(this.startTimeUs, periodDurationUs, representation));
                    this.formats.add(representation.format);
                    i4++;
                } else {
                    updateRepresentationIndependentProperties(periodDurationUs, list.get(iArr[0]));
                    return;
                }
            }
        }

        public void updatePeriod(MediaPresentationDescription mediaPresentationDescription, int i, ExposedTrack exposedTrack) throws BehindLiveWindowException {
            Period period = mediaPresentationDescription.getPeriod(i);
            long periodDurationUs = getPeriodDurationUs(mediaPresentationDescription, i);
            List<Representation> list = period.adaptationSets.get(exposedTrack.adaptationSetIndex).representations;
            int i2 = 0;
            while (true) {
                int[] iArr = this.representationIndices;
                if (i2 < iArr.length) {
                    Representation representation = list.get(iArr[i2]);
                    this.representationHolders.get(representation.format.id).updateRepresentation(periodDurationUs, representation);
                    i2++;
                } else {
                    updateRepresentationIndependentProperties(periodDurationUs, list.get(iArr[0]));
                    return;
                }
            }
        }

        public long getAvailableStartTimeUs() {
            return this.availableStartTimeUs;
        }

        public long getAvailableEndTimeUs() {
            if (isIndexUnbounded()) {
                throw new IllegalStateException("Period has unbounded index");
            }
            return this.availableEndTimeUs;
        }

        public boolean isIndexUnbounded() {
            return this.indexIsUnbounded;
        }

        public boolean isIndexExplicit() {
            return this.indexIsExplicit;
        }

        private void updateRepresentationIndependentProperties(long j, Representation representation) {
            DashSegmentIndex index = representation.getIndex();
            if (index != null) {
                int firstSegmentNum = index.getFirstSegmentNum();
                int lastSegmentNum = index.getLastSegmentNum(j);
                this.indexIsUnbounded = lastSegmentNum == -1;
                this.indexIsExplicit = index.isExplicit();
                this.availableStartTimeUs = this.startTimeUs + index.getTimeUs(firstSegmentNum);
                if (this.indexIsUnbounded) {
                    return;
                }
                this.availableEndTimeUs = this.startTimeUs + index.getTimeUs(lastSegmentNum) + index.getDurationUs(lastSegmentNum, j);
                return;
            }
            this.indexIsUnbounded = false;
            this.indexIsExplicit = true;
            long j2 = this.startTimeUs;
            this.availableStartTimeUs = j2;
            this.availableEndTimeUs = j2 + j;
        }

        private static int getRepresentationIndex(List<Representation> list, String str) {
            for (int i = 0; i < list.size(); i++) {
                if (str.equals(list.get(i).format.id)) {
                    return i;
                }
            }
            throw new IllegalStateException("Missing format id: " + str);
        }

        private static DrmInitData getDrmInitData(AdaptationSet adaptationSet) {
            DrmInitData.Mapped mapped = null;
            if (adaptationSet.contentProtections.isEmpty()) {
                return null;
            }
            for (int i = 0; i < adaptationSet.contentProtections.size(); i++) {
                ContentProtection contentProtection = adaptationSet.contentProtections.get(i);
                if (contentProtection.uuid != null && contentProtection.data != null) {
                    if (mapped == null) {
                        mapped = new DrmInitData.Mapped();
                    }
                    mapped.put(contentProtection.uuid, contentProtection.data);
                }
            }
            return mapped;
        }

        private static long getPeriodDurationUs(MediaPresentationDescription mediaPresentationDescription, int i) {
            long periodDuration = mediaPresentationDescription.getPeriodDuration(i);
            if (periodDuration == -1) {
                return -1L;
            }
            return periodDuration * 1000;
        }
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void setTrickMode(boolean z) {
        for (int i = 0; i < this.periodHolders.size(); i++) {
            PeriodHolder periodHolderValueAt = this.periodHolders.valueAt(i);
            for (int i2 = 0; i2 < periodHolderValueAt.formats.size(); i2++) {
                Extractor extractor = periodHolderValueAt.representationHolders.get(periodHolderValueAt.formats.get(i2).id).extractorWrapper.getExtractor();
                if (extractor instanceof SyncFrameExtractor) {
                    ((SyncFrameExtractor) extractor).enableSyncFrameMode(z);
                }
            }
        }
    }

    private void notifyDrmInitData(final DrmInitData drmInitData) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.dash.DashChunkSource.2
            @Override // java.lang.Runnable
            public void run() {
                DashChunkSource.this.eventListener.onDrmInitData(drmInitData);
            }
        });
    }
}
