package com.google.android.exoplayer.smoothstreaming;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer.BehindLiveWindowException;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.chunk.Chunk;
import com.google.android.exoplayer.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer.chunk.ChunkOperationHolder;
import com.google.android.exoplayer.chunk.ChunkSource;
import com.google.android.exoplayer.chunk.ContainerMediaChunk;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.FormatEvaluator;
import com.google.android.exoplayer.chunk.MediaChunk;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.SyncFrameExtractor;
import com.google.android.exoplayer.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer.extractor.mp4.Track;
import com.google.android.exoplayer.extractor.mp4.TrackEncryptionBox;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.ManifestFetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SmoothStreamingChunkSource implements ChunkSource, SmoothStreamingTrackSelector.Output {
    private final FormatEvaluator adaptiveFormatEvaluator;
    private SmoothStreamingManifest currentManifest;
    private int currentManifestChunkOffset;
    private final DataSource dataSource;
    private final DrmInitData.Mapped drmInitData;
    private ExposedTrack enabledTrack;
    private final FormatEvaluator.Evaluation evaluation;
    private final SparseArray<ChunkExtractorWrapper> extractorWrappers;
    private IOException fatalError;
    private final boolean live;
    private final long liveEdgeLatencyUs;
    private final ManifestFetcher<SmoothStreamingManifest> manifestFetcher;
    private final SparseArray<MediaFormat> mediaFormats;
    private boolean needManifestRefresh;
    private boolean prepareCalled;
    private final TrackEncryptionBox[] trackEncryptionBoxes;
    private final SmoothStreamingTrackSelector trackSelector;
    private final ArrayList<ExposedTrack> tracks;

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void onChunkLoadError(Chunk chunk, Exception exc) {
    }

    public SmoothStreamingChunkSource(ManifestFetcher<SmoothStreamingManifest> manifestFetcher, SmoothStreamingTrackSelector smoothStreamingTrackSelector, DataSource dataSource, FormatEvaluator formatEvaluator, long j) {
        this(manifestFetcher, manifestFetcher.getManifest(), smoothStreamingTrackSelector, dataSource, formatEvaluator, j);
    }

    private SmoothStreamingChunkSource(ManifestFetcher<SmoothStreamingManifest> manifestFetcher, SmoothStreamingManifest smoothStreamingManifest, SmoothStreamingTrackSelector smoothStreamingTrackSelector, DataSource dataSource, FormatEvaluator formatEvaluator, long j) {
        this.manifestFetcher = manifestFetcher;
        this.currentManifest = smoothStreamingManifest;
        this.trackSelector = smoothStreamingTrackSelector;
        this.dataSource = dataSource;
        this.adaptiveFormatEvaluator = formatEvaluator;
        this.liveEdgeLatencyUs = j * 1000;
        this.evaluation = new FormatEvaluator.Evaluation();
        this.tracks = new ArrayList<>();
        this.extractorWrappers = new SparseArray<>();
        this.mediaFormats = new SparseArray<>();
        this.live = smoothStreamingManifest.isLive;
        SmoothStreamingManifest.ProtectionElement protectionElement = smoothStreamingManifest.protectionElement;
        if (protectionElement != null) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[]{new TrackEncryptionBox(true, 8, getProtectionElementKeyId(protectionElement.data))};
            DrmInitData.Mapped mapped = new DrmInitData.Mapped();
            this.drmInitData = mapped;
            mapped.put(protectionElement.uuid, new DrmInitData.SchemeInitData("video/mp4", protectionElement.data));
            return;
        }
        this.trackEncryptionBoxes = null;
        this.drmInitData = null;
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        this.manifestFetcher.maybeThrowError();
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public boolean prepare() {
        if (!this.prepareCalled) {
            this.prepareCalled = true;
            try {
                this.trackSelector.selectTracks(this.currentManifest, this);
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
        ManifestFetcher<SmoothStreamingManifest> manifestFetcher = this.manifestFetcher;
        if (manifestFetcher != null) {
            manifestFetcher.enable();
        }
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void continueBuffering(long j) {
        ManifestFetcher<SmoothStreamingManifest> manifestFetcher = this.manifestFetcher;
        if (manifestFetcher != null && this.currentManifest.isLive && this.fatalError == null) {
            SmoothStreamingManifest manifest = manifestFetcher.getManifest();
            SmoothStreamingManifest smoothStreamingManifest = this.currentManifest;
            if (smoothStreamingManifest != manifest && manifest != null) {
                SmoothStreamingManifest.StreamElement streamElement = smoothStreamingManifest.streamElements[this.enabledTrack.elementIndex];
                int i = streamElement.chunkCount;
                SmoothStreamingManifest.StreamElement streamElement2 = manifest.streamElements[this.enabledTrack.elementIndex];
                if (i == 0 || streamElement2.chunkCount == 0) {
                    this.currentManifestChunkOffset += i;
                } else {
                    int i2 = i - 1;
                    long startTimeUs = streamElement.getStartTimeUs(i2) + streamElement.getChunkDurationUs(i2);
                    long startTimeUs2 = streamElement2.getStartTimeUs(0);
                    if (startTimeUs <= startTimeUs2) {
                        this.currentManifestChunkOffset += i;
                    } else {
                        this.currentManifestChunkOffset += streamElement.getChunkIndex(startTimeUs2);
                    }
                }
                this.currentManifest = manifest;
                this.needManifestRefresh = false;
            }
            if (!this.needManifestRefresh || SystemClock.elapsedRealtime() <= this.manifestFetcher.getManifestLoadStartTimestamp() + 5000) {
                return;
            }
            this.manifestFetcher.requestRefresh();
        }
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public final void getChunkOperation(List<? extends MediaChunk> list, long j, ChunkOperationHolder chunkOperationHolder) {
        int chunkIndex;
        Chunk chunk;
        if (this.fatalError != null) {
            chunkOperationHolder.chunk = null;
            return;
        }
        this.evaluation.queueSize = list.size();
        if (this.enabledTrack.isAdaptive()) {
            this.adaptiveFormatEvaluator.evaluate(list, j, this.enabledTrack.adaptiveFormats, this.evaluation);
        } else {
            this.evaluation.format = this.enabledTrack.fixedFormat;
            this.evaluation.trigger = 2;
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
        SmoothStreamingManifest.StreamElement streamElement = this.currentManifest.streamElements[this.enabledTrack.elementIndex];
        if (streamElement.chunkCount == 0) {
            if (this.currentManifest.isLive) {
                this.needManifestRefresh = true;
                return;
            } else {
                chunkOperationHolder.endOfStream = true;
                return;
            }
        }
        if (list.isEmpty()) {
            chunkIndex = streamElement.getChunkIndex(this.live ? getLiveSeekPosition(this.currentManifest, this.liveEdgeLatencyUs) : j);
        } else {
            chunkIndex = (list.get(chunkOperationHolder.queueSize - 1).chunkIndex + 1) - this.currentManifestChunkOffset;
        }
        if (this.live && chunkIndex < 0) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        if (this.currentManifest.isLive) {
            int i2 = streamElement.chunkCount;
            if (chunkIndex >= i2) {
                this.needManifestRefresh = true;
                return;
            } else if (chunkIndex == i2 - 1) {
                this.needManifestRefresh = true;
            }
        } else if (chunkIndex >= streamElement.chunkCount) {
            chunkOperationHolder.endOfStream = true;
            return;
        }
        boolean z = !this.currentManifest.isLive && chunkIndex == streamElement.chunkCount - 1;
        long startTimeUs = streamElement.getStartTimeUs(chunkIndex);
        long chunkDurationUs = z ? -1L : streamElement.getChunkDurationUs(chunkIndex) + startTimeUs;
        int i3 = chunkIndex + this.currentManifestChunkOffset;
        int manifestTrackIndex = getManifestTrackIndex(streamElement, format);
        int manifestTrackKey = getManifestTrackKey(this.enabledTrack.elementIndex, manifestTrackIndex);
        chunkOperationHolder.chunk = newMediaChunk(format, streamElement.buildRequestUri(manifestTrackIndex, chunkIndex), null, this.extractorWrappers.get(manifestTrackKey), this.drmInitData, this.dataSource, i3, startTimeUs, chunkDurationUs, this.evaluation.trigger, this.mediaFormats.get(manifestTrackKey), this.enabledTrack.adaptiveMaxWidth, this.enabledTrack.adaptiveMaxHeight);
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void disable(List<? extends MediaChunk> list) {
        if (this.enabledTrack.isAdaptive()) {
            this.adaptiveFormatEvaluator.disable();
        }
        ManifestFetcher<SmoothStreamingManifest> manifestFetcher = this.manifestFetcher;
        if (manifestFetcher != null) {
            manifestFetcher.disable();
        }
        this.evaluation.format = null;
        this.fatalError = null;
    }

    @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingTrackSelector.Output
    public void adaptiveTrack(SmoothStreamingManifest smoothStreamingManifest, int i, int[] iArr) {
        if (this.adaptiveFormatEvaluator == null) {
            return;
        }
        SmoothStreamingManifest.StreamElement streamElement = smoothStreamingManifest.streamElements[i];
        int length = iArr.length;
        Format[] formatArr = new Format[length];
        int iMax = -1;
        int iMax2 = -1;
        MediaFormat mediaFormat = null;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            formatArr[i2] = streamElement.tracks[i3].format;
            MediaFormat mediaFormatInitManifestTrack = initManifestTrack(smoothStreamingManifest, i, i3);
            if (mediaFormat == null || mediaFormatInitManifestTrack.height > iMax2) {
                mediaFormat = mediaFormatInitManifestTrack;
            }
            iMax = Math.max(iMax, mediaFormatInitManifestTrack.width);
            iMax2 = Math.max(iMax2, mediaFormatInitManifestTrack.height);
        }
        Arrays.sort(formatArr, new Format.DecreasingBandwidthComparator());
        this.tracks.add(new ExposedTrack(mediaFormat.copyAsAdaptive(null), i, formatArr, iMax, iMax2));
    }

    @Override // com.google.android.exoplayer.smoothstreaming.SmoothStreamingTrackSelector.Output
    public void fixedTrack(SmoothStreamingManifest smoothStreamingManifest, int i, int i2) {
        this.tracks.add(new ExposedTrack(initManifestTrack(smoothStreamingManifest, i, i2), i, smoothStreamingManifest.streamElements[i].tracks[i2].format));
    }

    private MediaFormat initManifestTrack(SmoothStreamingManifest smoothStreamingManifest, int i, int i2) {
        List listSingletonList;
        MediaFormat mediaFormatCreateAudioFormat;
        int i3;
        int manifestTrackKey = getManifestTrackKey(i, i2);
        MediaFormat mediaFormat = this.mediaFormats.get(manifestTrackKey);
        if (mediaFormat != null) {
            return mediaFormat;
        }
        long j = this.live ? -1L : smoothStreamingManifest.durationUs;
        SmoothStreamingManifest.StreamElement streamElement = smoothStreamingManifest.streamElements[i];
        SmoothStreamingManifest.TrackElement[] trackElementArr = streamElement.tracks;
        Format format = trackElementArr[i2].format;
        byte[][] bArr = trackElementArr[i2].csd;
        int i4 = streamElement.type;
        if (i4 == 0) {
            if (bArr != null) {
                listSingletonList = Arrays.asList(bArr);
            } else {
                listSingletonList = Collections.singletonList(CodecSpecificDataUtil.buildAacAudioSpecificConfig(format.audioSamplingRate, format.audioChannels));
            }
            mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat(format.id, format.mimeType, format.bitrate, -1, j, format.audioChannels, format.audioSamplingRate, listSingletonList, format.language);
            i3 = Track.TYPE_soun;
        } else if (i4 == 1) {
            mediaFormatCreateAudioFormat = MediaFormat.createVideoFormat(format.id, format.mimeType, format.bitrate, -1, j, format.width, format.height, Arrays.asList(bArr));
            i3 = Track.TYPE_vide;
        } else if (i4 == 2) {
            mediaFormatCreateAudioFormat = MediaFormat.createTextFormat(format.id, format.mimeType, format.bitrate, j, format.language);
            i3 = Track.TYPE_text;
        } else {
            throw new IllegalStateException("Invalid type: " + streamElement.type);
        }
        MediaFormat mediaFormat2 = mediaFormatCreateAudioFormat;
        FragmentedMp4Extractor fragmentedMp4Extractor = new FragmentedMp4Extractor(3, new Track(i2, i3, streamElement.timescale, -1L, j, mediaFormat2, this.trackEncryptionBoxes, i3 == Track.TYPE_vide ? 4 : -1, null, null));
        this.mediaFormats.put(manifestTrackKey, mediaFormat2);
        this.extractorWrappers.put(manifestTrackKey, new ChunkExtractorWrapper(fragmentedMp4Extractor));
        return mediaFormat2;
    }

    private static long getLiveSeekPosition(SmoothStreamingManifest smoothStreamingManifest, long j) {
        long jMax = Long.MIN_VALUE;
        int i = 0;
        while (true) {
            SmoothStreamingManifest.StreamElement[] streamElementArr = smoothStreamingManifest.streamElements;
            if (i >= streamElementArr.length) {
                return jMax - j;
            }
            SmoothStreamingManifest.StreamElement streamElement = streamElementArr[i];
            int i2 = streamElement.chunkCount;
            if (i2 > 0) {
                jMax = Math.max(jMax, streamElement.getStartTimeUs(i2 - 1) + streamElement.getChunkDurationUs(streamElement.chunkCount - 1));
            }
            i++;
        }
    }

    private static int getManifestTrackIndex(SmoothStreamingManifest.StreamElement streamElement, Format format) {
        SmoothStreamingManifest.TrackElement[] trackElementArr = streamElement.tracks;
        for (int i = 0; i < trackElementArr.length; i++) {
            if (trackElementArr[i].format.equals(format)) {
                return i;
            }
        }
        throw new IllegalStateException("Invalid format: " + format);
    }

    private static MediaChunk newMediaChunk(Format format, Uri uri, String str, ChunkExtractorWrapper chunkExtractorWrapper, DrmInitData drmInitData, DataSource dataSource, int i, long j, long j2, int i2, MediaFormat mediaFormat, int i3, int i4) {
        return new ContainerMediaChunk(dataSource, new DataSpec(uri, 0L, -1L, str), i2, format, j, j2, i, j, chunkExtractorWrapper, mediaFormat, i3, i4, drmInitData, true, -1);
    }

    private static int getManifestTrackKey(int i, int i2) {
        Assertions.checkState(i <= 65536 && i2 <= 65536);
        return (i << 16) | i2;
    }

    private static byte[] getProtectionElementKeyId(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i += 2) {
            sb.append((char) bArr[i]);
        }
        String string = sb.toString();
        byte[] bArrDecode = Base64.decode(string.substring(string.indexOf("<KID>") + 5, string.indexOf("</KID>")), 0);
        if (bArrDecode.length == 0) {
            Log.e("SSChunkSource", "getProtectionElementKeyId keyId.length = 0");
            return null;
        }
        swap(bArrDecode, 0, 3);
        swap(bArrDecode, 1, 2);
        swap(bArrDecode, 4, 5);
        swap(bArrDecode, 6, 7);
        return bArrDecode;
    }

    private static void swap(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    private static final class ExposedTrack {
        private final Format[] adaptiveFormats;
        private final int adaptiveMaxHeight;
        private final int adaptiveMaxWidth;
        private final int elementIndex;
        private final Format fixedFormat;
        public final MediaFormat trackFormat;

        public ExposedTrack(MediaFormat mediaFormat, int i, Format format) {
            this.trackFormat = mediaFormat;
            this.elementIndex = i;
            this.fixedFormat = format;
            this.adaptiveFormats = null;
            this.adaptiveMaxWidth = -1;
            this.adaptiveMaxHeight = -1;
        }

        public ExposedTrack(MediaFormat mediaFormat, int i, Format[] formatArr, int i2, int i3) {
            this.trackFormat = mediaFormat;
            this.elementIndex = i;
            this.adaptiveFormats = formatArr;
            this.adaptiveMaxWidth = i2;
            this.adaptiveMaxHeight = i3;
            this.fixedFormat = null;
        }

        public boolean isAdaptive() {
            return this.adaptiveFormats != null;
        }
    }

    @Override // com.google.android.exoplayer.chunk.ChunkSource
    public void setTrickMode(boolean z) {
        for (int i = 0; i < this.extractorWrappers.size(); i++) {
            SparseArray<ChunkExtractorWrapper> sparseArray = this.extractorWrappers;
            Extractor extractor = sparseArray.get(sparseArray.keyAt(i)).getExtractor();
            if (extractor instanceof SyncFrameExtractor) {
                ((SyncFrameExtractor) extractor).enableSyncFrameMode(z);
            }
        }
    }
}
