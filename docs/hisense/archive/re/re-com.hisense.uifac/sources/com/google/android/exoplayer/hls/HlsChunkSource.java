package com.google.android.exoplayer.hls;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.BehindLiveWindowException;
import com.google.android.exoplayer.chunk.Chunk;
import com.google.android.exoplayer.chunk.ChunkOperationHolder;
import com.google.android.exoplayer.chunk.DataChunk;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer.extractor.ts.PtsTimestampAdjuster;
import com.google.android.exoplayer.extractor.ts.TsExtractor;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.HttpDataSource;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.UriUtil;
import com.google.android.exoplayer.util.Util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class HlsChunkSource implements HlsTrackSelector.Output {
    private final BandwidthMeter bandwidthMeter;
    private final String baseUri;
    private final DataSource dataSource;
    private long durationUs;
    private byte[] encryptionIv;
    private String encryptionIvString;
    private byte[] encryptionKey;
    private Uri encryptionKeyUri;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private IOException fatalError;
    private final boolean isMaster;
    private boolean live;
    private final HlsMasterPlaylist masterPlaylist;
    private final long maxBufferDurationToSwitchDownUs;
    private final long minBufferDurationToSwitchUpUs;
    private final HlsPlaylistParser playlistParser;
    private boolean prepareCalled;
    private byte[] scratchSpace;
    private int selectedTrackIndex;
    private int selectedVariantIndex;
    private final PtsTimestampAdjusterProvider timestampAdjusterProvider;
    private final HlsTrackSelector trackSelector;
    private final ArrayList<ExposedTrack> tracks;
    private long[] variantBlacklistTimes;
    private long[] variantLastPlaylistLoadTimesMs;
    private HlsMediaPlaylist[] variantPlaylists;
    private Variant[] variants;

    public interface EventListener {
        void onMediaPlaylistLoadCompleted(byte[] bArr);
    }

    public HlsChunkSource(boolean z, DataSource dataSource, HlsPlaylist hlsPlaylist, HlsTrackSelector hlsTrackSelector, BandwidthMeter bandwidthMeter, PtsTimestampAdjusterProvider ptsTimestampAdjusterProvider) {
        this(z, dataSource, hlsPlaylist, hlsTrackSelector, bandwidthMeter, ptsTimestampAdjusterProvider, 5000L, 20000L, null, null);
    }

    public HlsChunkSource(boolean z, DataSource dataSource, HlsPlaylist hlsPlaylist, HlsTrackSelector hlsTrackSelector, BandwidthMeter bandwidthMeter, PtsTimestampAdjusterProvider ptsTimestampAdjusterProvider, long j, long j2, Handler handler, EventListener eventListener) {
        this.isMaster = z;
        this.dataSource = dataSource;
        this.trackSelector = hlsTrackSelector;
        this.bandwidthMeter = bandwidthMeter;
        this.timestampAdjusterProvider = ptsTimestampAdjusterProvider;
        this.eventListener = eventListener;
        this.eventHandler = handler;
        this.minBufferDurationToSwitchUpUs = j * 1000;
        this.maxBufferDurationToSwitchDownUs = 1000 * j2;
        this.baseUri = hlsPlaylist.baseUri;
        this.playlistParser = new HlsPlaylistParser();
        this.tracks = new ArrayList<>();
        if (hlsPlaylist.type == 0) {
            this.masterPlaylist = (HlsMasterPlaylist) hlsPlaylist;
            return;
        }
        Format format = new Format("0", "application/x-mpegURL", -1, -1, -1.0f, -1, -1, -1, null, null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Variant(this.baseUri, format));
        this.masterPlaylist = new HlsMasterPlaylist(this.baseUri, arrayList, Collections.emptyList(), Collections.emptyList(), null, null);
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
    }

    public boolean prepare() {
        if (!this.prepareCalled) {
            this.prepareCalled = true;
            try {
                this.trackSelector.selectTracks(this.masterPlaylist, this);
                selectTrack(0);
            } catch (IOException e) {
                this.fatalError = e;
            }
        }
        return this.fatalError == null;
    }

    public boolean isLive() {
        return this.live;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public int getTrackCount() {
        return this.tracks.size();
    }

    public Variant getFixedTrackVariant(int i) {
        Variant[] variantArr = this.tracks.get(i).variants;
        if (variantArr.length == 1) {
            return variantArr[0];
        }
        return null;
    }

    public String getMuxedAudioLanguage() {
        return this.masterPlaylist.muxedAudioLanguage;
    }

    public String getMuxedCaptionLanguage() {
        return this.masterPlaylist.muxedCaptionLanguage;
    }

    public int getSelectedTrackIndex() {
        return this.selectedTrackIndex;
    }

    public void selectTrack(int i) {
        this.selectedTrackIndex = i;
        ExposedTrack exposedTrack = this.tracks.get(i);
        this.selectedVariantIndex = exposedTrack.defaultVariantIndex;
        Variant[] variantArr = exposedTrack.variants;
        this.variants = variantArr;
        this.variantPlaylists = new HlsMediaPlaylist[variantArr.length];
        this.variantLastPlaylistLoadTimesMs = new long[variantArr.length];
        this.variantBlacklistTimes = new long[variantArr.length];
    }

    public void seek() {
        if (this.isMaster) {
            this.timestampAdjusterProvider.reset();
        }
    }

    public void reset() {
        this.fatalError = null;
    }

    public void getChunkOperation(TsChunk tsChunk, long j, ChunkOperationHolder chunkOperationHolder) {
        int nextChunkIndex;
        int iBinarySearchFloor;
        int i;
        long j2;
        long j3;
        HlsExtractorWrapper hlsExtractorWrapper;
        HlsExtractorWrapper hlsExtractorWrapper2;
        int variantIndex = tsChunk == null ? -1 : getVariantIndex(tsChunk.format);
        int nextVariantIndex = getNextVariantIndex(tsChunk, j);
        boolean z = (tsChunk == null || variantIndex == nextVariantIndex) ? false : true;
        HlsMediaPlaylist hlsMediaPlaylist = this.variantPlaylists[nextVariantIndex];
        if (hlsMediaPlaylist == null) {
            chunkOperationHolder.chunk = newMediaPlaylistChunk(nextVariantIndex);
            return;
        }
        this.selectedVariantIndex = nextVariantIndex;
        long adjustedEndTimeUs = 0;
        if (!this.live) {
            if (tsChunk == null) {
                iBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) hlsMediaPlaylist.segments, Long.valueOf(j), true, true);
                i = hlsMediaPlaylist.mediaSequence;
            } else if (z) {
                iBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) hlsMediaPlaylist.segments, Long.valueOf(tsChunk.startTimeUs), true, true);
                i = hlsMediaPlaylist.mediaSequence;
            } else {
                nextChunkIndex = tsChunk.getNextChunkIndex();
            }
            nextChunkIndex = iBinarySearchFloor + i;
        } else if (tsChunk != null) {
            nextChunkIndex = getLiveNextChunkSequenceNumber(tsChunk.chunkIndex, variantIndex, nextVariantIndex);
            if (nextChunkIndex < hlsMediaPlaylist.mediaSequence) {
                this.fatalError = new BehindLiveWindowException();
                return;
            }
        } else if (j <= 0) {
            nextChunkIndex = getLiveStartChunkSequenceNumber(nextVariantIndex);
        } else {
            long j4 = j - hlsMediaPlaylist.seekableRangeStart;
            if (j4 < 0) {
                j4 = 0;
            } else {
                long j5 = hlsMediaPlaylist.seekableRangeEnd;
                if (j4 > j5) {
                    j4 = j5;
                }
            }
            iBinarySearchFloor = Util.binarySearchFloor((List<? extends Comparable<? super Long>>) hlsMediaPlaylist.segments, Long.valueOf(j4), true, true);
            i = hlsMediaPlaylist.mediaSequence;
            nextChunkIndex = iBinarySearchFloor + i;
        }
        int i2 = nextChunkIndex;
        int i3 = i2 - hlsMediaPlaylist.mediaSequence;
        if (i3 >= hlsMediaPlaylist.segments.size()) {
            if (!hlsMediaPlaylist.live) {
                chunkOperationHolder.endOfStream = true;
                return;
            } else {
                if (shouldRerequestLiveMediaPlaylist(this.selectedVariantIndex)) {
                    chunkOperationHolder.chunk = newMediaPlaylistChunk(this.selectedVariantIndex);
                    return;
                }
                return;
            }
        }
        HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i3);
        Uri uriResolveToUri = UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.url);
        Log.d("HlsChunkSource", "segmant[" + segment.mediaSequence + "],URL[" + uriResolveToUri.toString() + "]");
        if (segment.isEncrypted) {
            Uri uriResolveToUri2 = UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.encryptionKeyUri);
            if (!uriResolveToUri2.equals(this.encryptionKeyUri)) {
                chunkOperationHolder.chunk = newEncryptionKeyChunk(uriResolveToUri2, segment.encryptionIV, this.selectedVariantIndex);
                return;
            } else if (!Util.areEqual(segment.encryptionIV, this.encryptionIvString)) {
                setEncryptionData(uriResolveToUri2, segment.encryptionIV, this.encryptionKey);
            }
        } else {
            clearEncryptionData();
        }
        DataSpec dataSpec = new DataSpec(uriResolveToUri, segment.byterangeOffset, segment.byterangeLength, null);
        if (!this.live) {
            adjustedEndTimeUs = segment.startTimeUs;
        } else if (tsChunk != null) {
            adjustedEndTimeUs = tsChunk.getAdjustedEndTimeUs() - (z ? tsChunk.getDurationUs() : 0L);
        }
        long j6 = adjustedEndTimeUs;
        long j7 = j6 + ((long) (segment.durationSecs * 1000000.0d));
        Format format = this.variants[this.selectedVariantIndex].format;
        String lastPathSegment = uriResolveToUri.getLastPathSegment();
        if (lastPathSegment.endsWith(".aac")) {
            hlsExtractorWrapper = new HlsExtractorWrapper(0, format, j6, new AdtsExtractor(j6), z, -1, -1);
            j3 = j6;
        } else {
            if (lastPathSegment.endsWith(".mp3")) {
                j2 = j6;
                hlsExtractorWrapper2 = new HlsExtractorWrapper(0, format, j6, new Mp3Extractor(j6), z, -1, -1);
            } else {
                j2 = j6;
                if (lastPathSegment.endsWith(".webvtt") || lastPathSegment.endsWith(".vtt")) {
                    PtsTimestampAdjuster adjuster = this.timestampAdjusterProvider.getAdjuster(this.isMaster, segment.discontinuitySequenceNumber, j2);
                    if (adjuster == null) {
                        return;
                    }
                    j3 = j2;
                    hlsExtractorWrapper = new HlsExtractorWrapper(0, format, j2, new WebvttExtractor(adjuster), z, -1, -1);
                } else if (tsChunk == null || tsChunk.discontinuitySequenceNumber != segment.discontinuitySequenceNumber || !format.equals(tsChunk.format)) {
                    PtsTimestampAdjuster adjuster2 = this.timestampAdjusterProvider.getAdjuster(this.isMaster, segment.discontinuitySequenceNumber, j2);
                    if (adjuster2 == null) {
                        return;
                    }
                    int i4 = 16;
                    String str = format.codecs;
                    if (!TextUtils.isEmpty(str)) {
                        i4 = MimeTypes.getAudioMediaMimeType(str) != "audio/mp4a-latm" ? 18 : 16;
                        if (MimeTypes.getVideoMediaMimeType(str) != "video/avc") {
                            i4 |= 4;
                        }
                    }
                    TsExtractor tsExtractor = new TsExtractor(adjuster2, i4);
                    ExposedTrack exposedTrack = this.tracks.get(this.selectedTrackIndex);
                    hlsExtractorWrapper = new HlsExtractorWrapper(0, format, j2, tsExtractor, z, exposedTrack.adaptiveMaxWidth, exposedTrack.adaptiveMaxHeight);
                    j3 = j2;
                } else {
                    hlsExtractorWrapper2 = tsChunk.extractorWrapper;
                }
            }
            hlsExtractorWrapper = hlsExtractorWrapper2;
            j3 = j2;
        }
        chunkOperationHolder.chunk = new TsChunk(this.dataSource, dataSpec, 0, format, j3, j7, i2, segment.discontinuitySequenceNumber, hlsExtractorWrapper, this.encryptionKey, this.encryptionIv);
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof MediaPlaylistChunk) {
            MediaPlaylistChunk mediaPlaylistChunk = (MediaPlaylistChunk) chunk;
            this.scratchSpace = mediaPlaylistChunk.getDataHolder();
            setMediaPlaylist(mediaPlaylistChunk.variantIndex, mediaPlaylistChunk.getResult());
            if (this.eventHandler == null || this.eventListener == null) {
                return;
            }
            final byte[] rawResponse = mediaPlaylistChunk.getRawResponse();
            this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.hls.HlsChunkSource.1
                @Override // java.lang.Runnable
                public void run() {
                    HlsChunkSource.this.eventListener.onMediaPlaylistLoadCompleted(rawResponse);
                }
            });
            return;
        }
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            setEncryptionData(encryptionKeyChunk.dataSpec.uri, encryptionKeyChunk.iv, encryptionKeyChunk.getResult());
        }
    }

    public boolean onChunkLoadError(Chunk chunk, IOException iOException) {
        boolean z;
        int i;
        int variantIndex;
        if (chunk.bytesLoaded() == 0 && ((((z = chunk instanceof TsChunk)) || (chunk instanceof MediaPlaylistChunk) || (chunk instanceof EncryptionKeyChunk)) && (iOException instanceof HttpDataSource.InvalidResponseCodeException) && ((i = ((HttpDataSource.InvalidResponseCodeException) iOException).responseCode) == 404 || i == 410))) {
            if (z) {
                variantIndex = getVariantIndex(((TsChunk) chunk).format);
            } else if (chunk instanceof MediaPlaylistChunk) {
                variantIndex = ((MediaPlaylistChunk) chunk).variantIndex;
            } else {
                variantIndex = ((EncryptionKeyChunk) chunk).variantIndex;
            }
            boolean z2 = this.variantBlacklistTimes[variantIndex] != 0;
            this.variantBlacklistTimes[variantIndex] = SystemClock.elapsedRealtime();
            if (z2) {
                Log.w("HlsChunkSource", "Already blacklisted variant (" + i + "): " + chunk.dataSpec.uri);
                return false;
            }
            if (!allVariantsBlacklisted()) {
                Log.w("HlsChunkSource", "Blacklisted variant (" + i + "): " + chunk.dataSpec.uri);
                return true;
            }
            Log.w("HlsChunkSource", "Final variant not blacklisted (" + i + "): " + chunk.dataSpec.uri);
            this.variantBlacklistTimes[variantIndex] = 0;
        }
        return false;
    }

    @Override // com.google.android.exoplayer.hls.HlsTrackSelector.Output
    public void adaptiveTrack(HlsMasterPlaylist hlsMasterPlaylist, Variant[] variantArr) {
        Arrays.sort(variantArr, new Comparator<Variant>(this) { // from class: com.google.android.exoplayer.hls.HlsChunkSource.2
            private final Comparator<Format> formatComparator = new Format.DecreasingBandwidthComparator();

            @Override // java.util.Comparator
            public int compare(Variant variant, Variant variant2) {
                return this.formatComparator.compare(variant.format, variant2.format);
            }
        });
        int iComputeDefaultVariantIndex = computeDefaultVariantIndex(hlsMasterPlaylist, variantArr, this.bandwidthMeter);
        int iMax = -1;
        int iMax2 = -1;
        for (Variant variant : variantArr) {
            Format format = variant.format;
            iMax = Math.max(format.width, iMax);
            iMax2 = Math.max(format.height, iMax2);
        }
        if (iMax <= 0) {
            iMax = 1920;
        }
        if (iMax2 <= 0) {
            iMax2 = 1080;
        }
        this.tracks.add(new ExposedTrack(variantArr, iComputeDefaultVariantIndex, iMax, iMax2));
    }

    @Override // com.google.android.exoplayer.hls.HlsTrackSelector.Output
    public void fixedTrack(HlsMasterPlaylist hlsMasterPlaylist, Variant variant) {
        this.tracks.add(new ExposedTrack(variant));
    }

    protected int computeDefaultVariantIndex(HlsMasterPlaylist hlsMasterPlaylist, Variant[] variantArr, BandwidthMeter bandwidthMeter) {
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        for (int i3 = 0; i3 < variantArr.length; i3++) {
            int iIndexOf = hlsMasterPlaylist.variants.indexOf(variantArr[i3]);
            if (iIndexOf < i) {
                i2 = i3;
                i = iIndexOf;
            }
        }
        return i2;
    }

    private int getLiveStartChunkSequenceNumber(int i) {
        HlsMediaPlaylist hlsMediaPlaylist = this.variantPlaylists[i];
        return (hlsMediaPlaylist.segments.size() > 3 ? hlsMediaPlaylist.segments.size() - 3 : 0) + hlsMediaPlaylist.mediaSequence;
    }

    private int getLiveNextChunkSequenceNumber(int i, int i2, int i3) {
        if (i2 == i3) {
            return i + 1;
        }
        HlsMediaPlaylist[] hlsMediaPlaylistArr = this.variantPlaylists;
        HlsMediaPlaylist hlsMediaPlaylist = hlsMediaPlaylistArr[i2];
        HlsMediaPlaylist hlsMediaPlaylist2 = hlsMediaPlaylistArr[i3];
        int i4 = hlsMediaPlaylist.mediaSequence;
        if (i < i4) {
            return hlsMediaPlaylist2.mediaSequence - 1;
        }
        double d = 0.0d;
        for (int i5 = i - i4; i5 < hlsMediaPlaylist.segments.size(); i5++) {
            d += hlsMediaPlaylist.segments.get(i5).durationSecs;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long[] jArr = this.variantLastPlaylistLoadTimesMs;
        double d2 = ((d + ((jElapsedRealtime - jArr[i2]) / 1000.0d)) + 2.0d) - ((jElapsedRealtime - jArr[i3]) / 1000.0d);
        if (d2 < 0.0d) {
            return hlsMediaPlaylist2.mediaSequence + hlsMediaPlaylist2.segments.size() + 1;
        }
        for (int size = hlsMediaPlaylist2.segments.size() - 1; size >= 0; size--) {
            d2 -= hlsMediaPlaylist2.segments.get(size).durationSecs;
            if (d2 < 0.0d) {
                return hlsMediaPlaylist2.mediaSequence + size;
            }
        }
        return hlsMediaPlaylist2.mediaSequence - 1;
    }

    private int getNextVariantIndex(TsChunk tsChunk, long j) {
        clearStaleBlacklistedVariants();
        long bitrateEstimate = this.bandwidthMeter.getBitrateEstimate();
        long[] jArr = this.variantBlacklistTimes;
        int i = this.selectedVariantIndex;
        if (jArr[i] != 0) {
            return getVariantIndexForBandwidth(bitrateEstimate);
        }
        if (tsChunk == null) {
            return i;
        }
        Log.d("HlsChunkSource", "bitrateEstimate[" + bitrateEstimate + "]");
        if (bitrateEstimate == -1) {
            return this.selectedVariantIndex;
        }
        int variantIndexForBandwidth = getVariantIndexForBandwidth(bitrateEstimate);
        int i2 = this.selectedVariantIndex;
        if (variantIndexForBandwidth == i2) {
            return i2;
        }
        long adjustedEndTimeUs = (tsChunk.getAdjustedEndTimeUs() - tsChunk.getDurationUs()) - j;
        long[] jArr2 = this.variantBlacklistTimes;
        int i3 = this.selectedVariantIndex;
        return (jArr2[i3] != 0 || (variantIndexForBandwidth > i3 && adjustedEndTimeUs < this.maxBufferDurationToSwitchDownUs) || (variantIndexForBandwidth < this.selectedVariantIndex && adjustedEndTimeUs > this.minBufferDurationToSwitchUpUs)) ? variantIndexForBandwidth : this.selectedVariantIndex;
    }

    private int getVariantIndexForBandwidth(long j) {
        if (j == -1) {
            j = 0;
        }
        int i = (int) (j * 0.8f);
        int i2 = 0;
        int i3 = -1;
        while (true) {
            Variant[] variantArr = this.variants;
            if (i2 >= variantArr.length) {
                Assertions.checkState(i3 != -1);
                return i3;
            }
            if (this.variantBlacklistTimes[i2] == 0) {
                if (variantArr[i2].format.bitrate <= i) {
                    return i2;
                }
                i3 = i2;
            }
            i2++;
        }
    }

    private boolean shouldRerequestLiveMediaPlaylist(int i) {
        return SystemClock.elapsedRealtime() - this.variantLastPlaylistLoadTimesMs[i] >= ((long) ((this.variantPlaylists[i].targetDurationSecs * 1000) / 2));
    }

    private MediaPlaylistChunk newMediaPlaylistChunk(int i) {
        Uri uriResolveToUri = UriUtil.resolveToUri(this.baseUri, this.variants[i].url);
        return new MediaPlaylistChunk(this.dataSource, new DataSpec(uriResolveToUri, 0L, -1L, null, 1), this.scratchSpace, this.playlistParser, i, uriResolveToUri.toString());
    }

    private EncryptionKeyChunk newEncryptionKeyChunk(Uri uri, String str, int i) {
        return new EncryptionKeyChunk(this.dataSource, new DataSpec(uri, 0L, -1L, null, 1), this.scratchSpace, str, i);
    }

    private void setEncryptionData(Uri uri, String str, byte[] bArr) {
        byte[] byteArray = new BigInteger(str.toLowerCase(Locale.getDefault()).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        byte[] bArr2 = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr2, (16 - byteArray.length) + length, byteArray.length - length);
        this.encryptionKeyUri = uri;
        this.encryptionKey = bArr;
        this.encryptionIvString = str;
        this.encryptionIv = bArr2;
    }

    private void clearEncryptionData() {
        this.encryptionKeyUri = null;
        this.encryptionKey = null;
        this.encryptionIvString = null;
        this.encryptionIv = null;
    }

    private void setMediaPlaylist(int i, HlsMediaPlaylist hlsMediaPlaylist) {
        this.variantLastPlaylistLoadTimesMs[i] = SystemClock.elapsedRealtime();
        this.variantPlaylists[i] = hlsMediaPlaylist;
        boolean z = this.live | hlsMediaPlaylist.live;
        this.live = z;
        this.durationUs = z ? -1L : hlsMediaPlaylist.durationUs;
    }

    private boolean allVariantsBlacklisted() {
        for (long j : this.variantBlacklistTimes) {
            if (j == 0) {
                return false;
            }
        }
        return true;
    }

    private void clearStaleBlacklistedVariants() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int i = 0;
        while (true) {
            long[] jArr = this.variantBlacklistTimes;
            if (i >= jArr.length) {
                return;
            }
            if (jArr[i] != 0 && jElapsedRealtime - jArr[i] > 60000) {
                jArr[i] = 0;
            }
            i++;
        }
    }

    private int getVariantIndex(Format format) {
        int i = 0;
        while (true) {
            Variant[] variantArr = this.variants;
            if (i < variantArr.length) {
                if (variantArr[i].format.equals(format)) {
                    return i;
                }
                i++;
            } else {
                throw new IllegalStateException("Invalid format: " + format);
            }
        }
    }

    private static final class ExposedTrack {
        private final int adaptiveMaxHeight;
        private final int adaptiveMaxWidth;
        private final int defaultVariantIndex;
        private final Variant[] variants;

        public ExposedTrack(Variant variant) {
            this.variants = new Variant[]{variant};
            this.defaultVariantIndex = 0;
            this.adaptiveMaxWidth = -1;
            this.adaptiveMaxHeight = -1;
        }

        public ExposedTrack(Variant[] variantArr, int i, int i2, int i3) {
            this.variants = variantArr;
            this.defaultVariantIndex = i;
            this.adaptiveMaxWidth = i2;
            this.adaptiveMaxHeight = i3;
        }
    }

    private static final class MediaPlaylistChunk extends DataChunk {
        private final HlsPlaylistParser playlistParser;
        private final String playlistUrl;
        private byte[] rawResponse;
        private HlsMediaPlaylist result;
        public final int variantIndex;

        public MediaPlaylistChunk(DataSource dataSource, DataSpec dataSpec, byte[] bArr, HlsPlaylistParser hlsPlaylistParser, int i, String str) {
            super(dataSource, dataSpec, 4, 0, null, -1, bArr);
            this.variantIndex = i;
            this.playlistParser = hlsPlaylistParser;
            this.playlistUrl = str;
        }

        @Override // com.google.android.exoplayer.chunk.DataChunk
        protected void consume(byte[] bArr, int i) throws IOException {
            this.rawResponse = Arrays.copyOf(bArr, i);
            this.result = (HlsMediaPlaylist) this.playlistParser.parse(this.playlistUrl, (InputStream) new ByteArrayInputStream(this.rawResponse));
        }

        public byte[] getRawResponse() {
            return this.rawResponse;
        }

        public HlsMediaPlaylist getResult() {
            return this.result;
        }
    }

    private static final class EncryptionKeyChunk extends DataChunk {
        public final String iv;
        private byte[] result;
        public final int variantIndex;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, byte[] bArr, String str, int i) {
            super(dataSource, dataSpec, 3, 0, null, -1, bArr);
            this.iv = str;
            this.variantIndex = i;
        }

        @Override // com.google.android.exoplayer.chunk.DataChunk
        protected void consume(byte[] bArr, int i) throws IOException {
            this.result = Arrays.copyOf(bArr, i);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    public boolean livePlaylist() {
        return this.live;
    }
}
