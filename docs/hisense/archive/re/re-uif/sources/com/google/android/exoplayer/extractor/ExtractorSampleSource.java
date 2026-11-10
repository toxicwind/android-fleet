package com.google.android.exoplayer.extractor;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.SparseArray;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.Loader;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ExtractorSampleSource implements SampleSource, SampleSource.SampleSourceReader, ExtractorOutput, Loader.Callback {
    private static final List<Class<? extends Extractor>> DEFAULT_EXTRACTOR_CLASSES;
    private final Allocator allocator;
    private IOException currentLoadableException;
    private int currentLoadableExceptionCount;
    private long currentLoadableExceptionTimestamp;
    private final DataSource dataSource;
    private long downstreamPositionUs;
    private volatile DrmInitData drmInitData;
    private int enabledTrackCount;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private final int eventSourceId;
    private int extractedSampleCount;
    private int extractedSampleCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    private boolean havePendingNextSampleUs;
    private long lastSeekPositionUs;
    private ExtractingLoadable loadable;
    private Loader loader;
    private boolean loadingFinished;
    private long maxTrackDurationUs;
    private MediaFormat[] mediaFormats;
    private final int minLoadableRetryCount;
    private boolean[] pendingDiscontinuities;
    private boolean[] pendingMediaFormat;
    private long pendingNextSampleUs;
    private long pendingResetPositionUs;
    private boolean prepared;
    private int remainingReleaseCount;
    private final int requestedBufferSize;
    private final SparseArray<InternalTrackOutput> sampleQueues;
    private long sampleTimeOffsetUs;
    private volatile SeekMap seekMap;
    private boolean[] trackEnabledStates;
    private volatile boolean tracksBuilt;
    private final Uri uri;

    public interface EventListener {
        void onLoadError(int i, IOException iOException);
    }

    static /* synthetic */ int access$308(ExtractorSampleSource extractorSampleSource) {
        int i = extractorSampleSource.extractedSampleCount;
        extractorSampleSource.extractedSampleCount = i + 1;
        return i;
    }

    public static final class UnrecognizedInputFormatException extends ParserException {
        public UnrecognizedInputFormatException(Extractor[] extractorArr) {
            super("None of the available extractors (" + Util.getCommaDelimitedSimpleClassNames(extractorArr) + ") could read the stream.");
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        DEFAULT_EXTRACTOR_CLASSES = arrayList;
        try {
            arrayList.add(Class.forName("com.google.android.exoplayer.extractor.webm.WebmExtractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.mp4.FragmentedMp4Extractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused2) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.mp4.Mp4Extractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused3) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.mp3.Mp3Extractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused4) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.ts.AdtsExtractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused5) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.ts.TsExtractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused6) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.flv.FlvExtractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused7) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.ogg.OggExtractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused8) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.ts.PsExtractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused9) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.extractor.wav.WavExtractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused10) {
        }
        try {
            DEFAULT_EXTRACTOR_CLASSES.add(Class.forName("com.google.android.exoplayer.ext.flac.FlacExtractor").asSubclass(Extractor.class));
        } catch (ClassNotFoundException unused11) {
        }
    }

    public ExtractorSampleSource(Uri uri, DataSource dataSource, Allocator allocator, int i, Handler handler, EventListener eventListener, int i2, Extractor... extractorArr) {
        this(uri, dataSource, allocator, i, -1, handler, eventListener, i2, extractorArr);
    }

    public ExtractorSampleSource(Uri uri, DataSource dataSource, Allocator allocator, int i, int i2, Handler handler, EventListener eventListener, int i3, Extractor... extractorArr) {
        this.uri = uri;
        this.dataSource = dataSource;
        this.eventListener = eventListener;
        this.eventHandler = handler;
        this.eventSourceId = i3;
        this.allocator = allocator;
        this.requestedBufferSize = i;
        this.minLoadableRetryCount = i2;
        if (extractorArr == null || extractorArr.length == 0) {
            int size = DEFAULT_EXTRACTOR_CLASSES.size();
            extractorArr = new Extractor[size];
            for (int i4 = 0; i4 < size; i4++) {
                try {
                    extractorArr[i4] = DEFAULT_EXTRACTOR_CLASSES.get(i4).newInstance();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Unexpected error creating default extractor", e);
                } catch (InstantiationException e2) {
                    throw new IllegalStateException("Unexpected error creating default extractor", e2);
                }
            }
        }
        this.extractorHolder = new ExtractorHolder(extractorArr, this);
        this.sampleQueues = new SparseArray<>();
        this.pendingResetPositionUs = Long.MIN_VALUE;
    }

    @Override // com.google.android.exoplayer.SampleSource
    public SampleSource.SampleSourceReader register() {
        this.remainingReleaseCount++;
        return this;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean prepare(long j) {
        if (this.prepared) {
            return true;
        }
        if (this.loader == null) {
            this.loader = new Loader("Loader:ExtractorSampleSource");
        }
        maybeStartLoading();
        if (this.seekMap == null || !this.tracksBuilt || !haveFormatsForAllTracks()) {
            return false;
        }
        int size = this.sampleQueues.size();
        this.trackEnabledStates = new boolean[size];
        this.pendingDiscontinuities = new boolean[size];
        this.pendingMediaFormat = new boolean[size];
        this.mediaFormats = new MediaFormat[size];
        this.maxTrackDurationUs = -1L;
        for (int i = 0; i < size; i++) {
            MediaFormat format = this.sampleQueues.valueAt(i).getFormat();
            this.mediaFormats[i] = format;
            long j2 = format.durationUs;
            if (j2 != -1 && j2 > this.maxTrackDurationUs) {
                this.maxTrackDurationUs = j2;
            }
        }
        this.prepared = true;
        return true;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int getTrackCount() {
        return this.sampleQueues.size();
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public MediaFormat getFormat(int i) {
        Assertions.checkState(this.prepared);
        return this.mediaFormats[i];
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void enable(int i, long j) {
        Assertions.checkState(this.prepared);
        Assertions.checkState(!this.trackEnabledStates[i]);
        int i2 = this.enabledTrackCount + 1;
        this.enabledTrackCount = i2;
        this.trackEnabledStates[i] = true;
        this.pendingMediaFormat[i] = true;
        this.pendingDiscontinuities[i] = false;
        if (i2 == 1) {
            if (!this.seekMap.isSeekable()) {
                j = 0;
            }
            this.downstreamPositionUs = j;
            this.lastSeekPositionUs = j;
            restartFrom(j);
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void disable(int i) {
        Assertions.checkState(this.prepared);
        Assertions.checkState(this.trackEnabledStates[i]);
        int i2 = this.enabledTrackCount - 1;
        this.enabledTrackCount = i2;
        this.trackEnabledStates[i] = false;
        if (i2 == 0) {
            this.downstreamPositionUs = Long.MIN_VALUE;
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            } else {
                clearState();
                this.allocator.trim(0);
            }
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public boolean continueBuffering(int i, long j) {
        Assertions.checkState(this.prepared);
        Assertions.checkState(this.trackEnabledStates[i]);
        this.downstreamPositionUs = j;
        discardSamplesForDisabledTracks(j);
        if (this.loadingFinished) {
            return true;
        }
        maybeStartLoading();
        if (isPendingReset()) {
            return false;
        }
        return !this.sampleQueues.valueAt(i).isEmpty();
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long readDiscontinuity(int i) {
        boolean[] zArr = this.pendingDiscontinuities;
        if (!zArr[i]) {
            return Long.MIN_VALUE;
        }
        zArr[i] = false;
        return this.lastSeekPositionUs;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public int readData(int i, long j, MediaFormatHolder mediaFormatHolder, SampleHolder sampleHolder) {
        this.downstreamPositionUs = j;
        if (!this.pendingDiscontinuities[i] && !isPendingReset()) {
            InternalTrackOutput internalTrackOutputValueAt = this.sampleQueues.valueAt(i);
            if (this.pendingMediaFormat[i]) {
                mediaFormatHolder.format = internalTrackOutputValueAt.getFormat();
                mediaFormatHolder.drmInitData = this.drmInitData;
                this.pendingMediaFormat[i] = false;
                return -4;
            }
            if (sampleHolder != null && internalTrackOutputValueAt.getSample(sampleHolder)) {
                sampleHolder.flags = (sampleHolder.timeUs < this.lastSeekPositionUs ? 134217728 : 0) | sampleHolder.flags;
                if (this.havePendingNextSampleUs) {
                    this.sampleTimeOffsetUs = this.pendingNextSampleUs - sampleHolder.timeUs;
                    this.havePendingNextSampleUs = false;
                }
                sampleHolder.timeUs += this.sampleTimeOffsetUs;
                return -3;
            }
            if (this.loadingFinished) {
                return -1;
            }
        }
        return -2;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void maybeThrowError() throws IOException {
        if (this.currentLoadableException == null) {
            return;
        }
        if (isCurrentLoadableExceptionFatal()) {
            throw this.currentLoadableException;
        }
        int i = this.minLoadableRetryCount;
        if (i == -1) {
            i = (this.seekMap == null || this.seekMap.isSeekable()) ? 3 : 6;
        }
        if (this.currentLoadableExceptionCount > i) {
            throw this.currentLoadableException;
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void seekToUs(long j) {
        Assertions.checkState(this.prepared);
        int i = 0;
        Assertions.checkState(this.enabledTrackCount > 0);
        if (!this.seekMap.isSeekable()) {
            j = 0;
        }
        long j2 = isPendingReset() ? this.pendingResetPositionUs : this.downstreamPositionUs;
        this.downstreamPositionUs = j;
        this.lastSeekPositionUs = j;
        if (j2 == j) {
            return;
        }
        boolean zSkipToKeyframeBefore = !isPendingReset();
        for (int i2 = 0; zSkipToKeyframeBefore && i2 < this.sampleQueues.size(); i2++) {
            zSkipToKeyframeBefore &= this.sampleQueues.valueAt(i2).skipToKeyframeBefore(j);
        }
        if (!zSkipToKeyframeBefore) {
            restartFrom(j);
        }
        while (true) {
            boolean[] zArr = this.pendingDiscontinuities;
            if (i >= zArr.length) {
                return;
            }
            zArr[i] = true;
            i++;
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return -3L;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long jMax = Long.MIN_VALUE;
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            jMax = Math.max(jMax, this.sampleQueues.valueAt(i).getLargestParsedTimestampUs());
        }
        return jMax == Long.MIN_VALUE ? this.downstreamPositionUs : jMax;
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void release() {
        Loader loader;
        Assertions.checkState(this.remainingReleaseCount > 0);
        int i = this.remainingReleaseCount - 1;
        this.remainingReleaseCount = i;
        if (i != 0 || (loader = this.loader) == null) {
            return;
        }
        loader.release(new Runnable() { // from class: com.google.android.exoplayer.extractor.ExtractorSampleSource.1
            @Override // java.lang.Runnable
            public void run() {
                ExtractorSampleSource.this.extractorHolder.release();
            }
        });
        this.loader = null;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCompleted(Loader.Loadable loadable) {
        this.loadingFinished = true;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadCanceled(Loader.Loadable loadable) {
        if (this.enabledTrackCount > 0) {
            restartFrom(this.pendingResetPositionUs);
        } else {
            clearState();
            this.allocator.trim(0);
        }
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Callback
    public void onLoadError(Loader.Loadable loadable, IOException iOException) {
        this.currentLoadableException = iOException;
        this.currentLoadableExceptionCount = this.extractedSampleCount <= this.extractedSampleCountAtStartOfLoad ? 1 + this.currentLoadableExceptionCount : 1;
        this.currentLoadableExceptionTimestamp = SystemClock.elapsedRealtime();
        notifyLoadError(iOException);
        maybeStartLoading();
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public TrackOutput track(int i) {
        InternalTrackOutput internalTrackOutput = this.sampleQueues.get(i);
        if (internalTrackOutput != null) {
            return internalTrackOutput;
        }
        InternalTrackOutput internalTrackOutput2 = new InternalTrackOutput(this.allocator);
        this.sampleQueues.put(i, internalTrackOutput2);
        return internalTrackOutput2;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void endTracks() {
        this.tracksBuilt = true;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void drmInitData(DrmInitData drmInitData) {
        this.drmInitData = drmInitData;
    }

    private void restartFrom(long j) {
        this.pendingResetPositionUs = j;
        this.loadingFinished = false;
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
        } else {
            clearState();
            maybeStartLoading();
        }
    }

    private void maybeStartLoading() {
        if (this.loadingFinished || this.loader.isLoading()) {
            return;
        }
        int i = 0;
        if (this.currentLoadableException != null) {
            if (isCurrentLoadableExceptionFatal()) {
                return;
            }
            Assertions.checkState(this.loadable != null);
            if (SystemClock.elapsedRealtime() - this.currentLoadableExceptionTimestamp >= getRetryDelayMillis(this.currentLoadableExceptionCount)) {
                this.currentLoadableException = null;
                if (!this.prepared) {
                    while (i < this.sampleQueues.size()) {
                        this.sampleQueues.valueAt(i).clear();
                        i++;
                    }
                    this.loadable = createLoadableFromStart();
                } else if (!this.seekMap.isSeekable() && this.maxTrackDurationUs == -1) {
                    while (i < this.sampleQueues.size()) {
                        this.sampleQueues.valueAt(i).clear();
                        i++;
                    }
                    this.loadable = createLoadableFromStart();
                    this.pendingNextSampleUs = this.downstreamPositionUs;
                    this.havePendingNextSampleUs = true;
                }
                this.extractedSampleCountAtStartOfLoad = this.extractedSampleCount;
                this.loader.startLoading(this.loadable, this);
                return;
            }
            return;
        }
        this.sampleTimeOffsetUs = 0L;
        this.havePendingNextSampleUs = false;
        if (!this.prepared) {
            this.loadable = createLoadableFromStart();
        } else {
            Assertions.checkState(isPendingReset());
            long j = this.maxTrackDurationUs;
            if (j != -1 && this.pendingResetPositionUs >= j) {
                this.loadingFinished = true;
                this.pendingResetPositionUs = Long.MIN_VALUE;
                return;
            } else {
                this.loadable = createLoadableFromPositionUs(this.pendingResetPositionUs);
                this.pendingResetPositionUs = Long.MIN_VALUE;
            }
        }
        this.extractedSampleCountAtStartOfLoad = this.extractedSampleCount;
        this.loader.startLoading(this.loadable, this);
    }

    private ExtractingLoadable createLoadableFromStart() {
        return new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this.allocator, this.requestedBufferSize, 0L);
    }

    private ExtractingLoadable createLoadableFromPositionUs(long j) {
        return new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this.allocator, this.requestedBufferSize, this.seekMap.getPosition(j));
    }

    private boolean haveFormatsForAllTracks() {
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            if (!this.sampleQueues.valueAt(i).hasFormat()) {
                return false;
            }
        }
        return true;
    }

    private void discardSamplesForDisabledTracks(long j) {
        int i = 0;
        while (true) {
            boolean[] zArr = this.trackEnabledStates;
            if (i >= zArr.length) {
                return;
            }
            if (!zArr[i]) {
                this.sampleQueues.valueAt(i).discardUntil(j);
            }
            i++;
        }
    }

    private void clearState() {
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            this.sampleQueues.valueAt(i).clear();
        }
        this.loadable = null;
        this.currentLoadableException = null;
        this.currentLoadableExceptionCount = 0;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != Long.MIN_VALUE;
    }

    private boolean isCurrentLoadableExceptionFatal() {
        return this.currentLoadableException instanceof UnrecognizedInputFormatException;
    }

    private long getRetryDelayMillis(long j) {
        return Math.min((j - 1) * 1000, 5000L);
    }

    private void notifyLoadError(final IOException iOException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.extractor.ExtractorSampleSource.2
            @Override // java.lang.Runnable
            public void run() {
                ExtractorSampleSource.this.eventListener.onLoadError(ExtractorSampleSource.this.eventSourceId, iOException);
            }
        });
    }

    private class InternalTrackOutput extends DefaultTrackOutput {
        public InternalTrackOutput(Allocator allocator) {
            super(allocator);
        }

        @Override // com.google.android.exoplayer.extractor.DefaultTrackOutput, com.google.android.exoplayer.extractor.TrackOutput
        public void sampleMetadata(long j, int i, int i2, int i3, byte[] bArr) {
            super.sampleMetadata(j, i, i2, i3, bArr);
            ExtractorSampleSource.access$308(ExtractorSampleSource.this);
        }
    }

    private static class ExtractingLoadable implements Loader.Loadable {
        private final Allocator allocator;
        private final DataSource dataSource;
        private final ExtractorHolder extractorHolder;
        private volatile boolean loadCanceled;
        private boolean pendingExtractorSeek;
        private final PositionHolder positionHolder;
        private final int requestedBufferSize;
        private final Uri uri;

        public ExtractingLoadable(Uri uri, DataSource dataSource, ExtractorHolder extractorHolder, Allocator allocator, int i, long j) {
            Assertions.checkNotNull(uri);
            this.uri = uri;
            Assertions.checkNotNull(dataSource);
            this.dataSource = dataSource;
            Assertions.checkNotNull(extractorHolder);
            this.extractorHolder = extractorHolder;
            Assertions.checkNotNull(allocator);
            this.allocator = allocator;
            this.requestedBufferSize = i;
            PositionHolder positionHolder = new PositionHolder();
            this.positionHolder = positionHolder;
            positionHolder.position = j;
            this.pendingExtractorSeek = true;
        }

        @Override // com.google.android.exoplayer.upstream.Loader.Loadable
        public void cancelLoad() {
            this.loadCanceled = true;
        }

        @Override // com.google.android.exoplayer.upstream.Loader.Loadable
        public boolean isLoadCanceled() {
            return this.loadCanceled;
        }

        @Override // com.google.android.exoplayer.upstream.Loader.Loadable
        public void load() throws Throwable {
            int i = 0;
            while (i == 0 && !this.loadCanceled) {
                DefaultExtractorInput defaultExtractorInput = null;
                try {
                    long j = this.positionHolder.position;
                    long jOpen = this.dataSource.open(new DataSpec(this.uri, j, -1L, null));
                    if (jOpen != -1) {
                        jOpen += j;
                    }
                    DefaultExtractorInput defaultExtractorInput2 = new DefaultExtractorInput(this.dataSource, j, jOpen);
                    try {
                        Extractor extractorSelectExtractor = this.extractorHolder.selectExtractor(defaultExtractorInput2);
                        if (this.pendingExtractorSeek) {
                            extractorSelectExtractor.seek();
                            this.pendingExtractorSeek = false;
                        }
                        while (i == 0 && !this.loadCanceled) {
                            this.allocator.blockWhileTotalBytesAllocatedExceeds(this.requestedBufferSize);
                            i = extractorSelectExtractor.read(defaultExtractorInput2, this.positionHolder);
                        }
                        if (i == 1) {
                            i = 0;
                        } else {
                            this.positionHolder.position = defaultExtractorInput2.getPosition();
                        }
                        Util.closeQuietly(this.dataSource);
                    } catch (Throwable th) {
                        th = th;
                        defaultExtractorInput = defaultExtractorInput2;
                        if (i != 1 && defaultExtractorInput != null) {
                            this.positionHolder.position = defaultExtractorInput.getPosition();
                        }
                        Util.closeQuietly(this.dataSource);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
    }

    private static final class ExtractorHolder {
        private Extractor extractor;
        private final ExtractorOutput extractorOutput;
        private final Extractor[] extractors;

        public ExtractorHolder(Extractor[] extractorArr, ExtractorOutput extractorOutput) {
            this.extractors = extractorArr;
            this.extractorOutput = extractorOutput;
        }

        public Extractor selectExtractor(ExtractorInput extractorInput) throws InterruptedException, IOException {
            Extractor extractor = this.extractor;
            if (extractor != null) {
                return extractor;
            }
            if (extractorInput == null) {
                return null;
            }
            for (Extractor extractor2 : this.extractors) {
                try {
                    if (extractor2.sniff(extractorInput)) {
                        this.extractor = extractor2;
                        extractorInput.resetPeekPosition();
                        break;
                    }
                    continue;
                } catch (EOFException unused) {
                } catch (Throwable th) {
                    extractorInput.resetPeekPosition();
                    throw th;
                }
                extractorInput.resetPeekPosition();
            }
            Extractor extractor3 = this.extractor;
            if (extractor3 == null) {
                throw new UnrecognizedInputFormatException(this.extractors);
            }
            extractor3.init(this.extractorOutput);
            return this.extractor;
        }

        public void release() {
            Extractor extractor = this.extractor;
            if (extractor != null) {
                extractor.release();
                this.extractor = null;
            }
        }
    }

    @Override // com.google.android.exoplayer.SampleSource.SampleSourceReader
    public void setTrickMode(boolean z) {
        Extractor extractorSelectExtractor = null;
        try {
            extractorSelectExtractor = this.extractorHolder.selectExtractor(null);
        } catch (IOException | InterruptedException unused) {
        }
        if (extractorSelectExtractor == null || !(extractorSelectExtractor instanceof SyncFrameExtractor)) {
            return;
        }
        ((SyncFrameExtractor) extractorSelectExtractor).enableSyncFrameMode(z);
    }
}
