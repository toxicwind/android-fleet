package com.google.android.exoplayer.demo.player;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.google.android.exoplayer.DefaultLoadControl;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.chunk.ChunkSampleSource;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.FormatEvaluator;
import com.google.android.exoplayer.dash.DashChunkSource;
import com.google.android.exoplayer.dash.DefaultDashTrackSelector;
import com.google.android.exoplayer.dash.mpd.AdaptationSet;
import com.google.android.exoplayer.dash.mpd.ContentProtection;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescription;
import com.google.android.exoplayer.dash.mpd.MediaPresentationDescriptionParser;
import com.google.android.exoplayer.dash.mpd.Period;
import com.google.android.exoplayer.dash.mpd.Representation;
import com.google.android.exoplayer.dash.mpd.UtcTimingElement;
import com.google.android.exoplayer.dash.mpd.UtcTimingElementResolver;
import com.google.android.exoplayer.demo.ExoPlayerDemoApplication;
import com.google.android.exoplayer.demo.PlayreadyTestMediaDrmCallback;
import com.google.android.exoplayer.demo.WidevineTestMediaDrmCallback;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.drm.MediaDrmCallback;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.drm.UnsupportedDrmException;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.text.TextTrackRenderer;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.upstream.UriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class DashRendererBuilder implements DemoPlayer.RendererBuilder {
    private final byte[] actionToken;
    private final Context context;
    private AsyncRendererBuilder currentAsyncBuilder;
    private final String customerData = null;
    private final MediaDrmCallback drmCallback;
    private DefaultHttpDataSource.Callback httpDataSourceCallback;
    private final String url;
    private final String userAgent;

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public boolean sourceIsLocal() {
        return false;
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void buildRenderers(DemoPlayer demoPlayer) {
        AsyncRendererBuilder asyncRendererBuilder = new AsyncRendererBuilder(this.context, this.userAgent, this.url, this.drmCallback, demoPlayer, this.actionToken, this.customerData);
        this.currentAsyncBuilder = asyncRendererBuilder;
        asyncRendererBuilder.setHttpDataSourceCallback(this.httpDataSourceCallback);
        this.currentAsyncBuilder.init();
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void cancel() {
        AsyncRendererBuilder asyncRendererBuilder = this.currentAsyncBuilder;
        if (asyncRendererBuilder != null) {
            asyncRendererBuilder.cancel();
            this.currentAsyncBuilder = null;
        }
    }

    private static final class AsyncRendererBuilder implements ManifestFetcher.ManifestCallback<MediaPresentationDescription>, UtcTimingElementResolver.UtcTimingCallback {
        private final byte[] actionToken;
        private boolean canceled;
        private final Context context;
        private final String customerData;
        private MediaDrmCallback drmCallback;
        private long elapsedRealtimeOffset;
        private DefaultHttpDataSource.Callback httpDataSourceCallback;
        private MediaPresentationDescription manifest;
        private final UriDataSource manifestDataSource;
        private final ManifestFetcher<MediaPresentationDescription> manifestFetcher;
        private final DemoPlayer player;
        private final String userAgent;

        public AsyncRendererBuilder(Context context, String str, String str2, MediaDrmCallback mediaDrmCallback, DemoPlayer demoPlayer, byte[] bArr, String str3) {
            this.context = context;
            this.userAgent = str;
            this.drmCallback = mediaDrmCallback;
            this.player = demoPlayer;
            this.actionToken = bArr;
            this.customerData = str3;
            MediaPresentationDescriptionParser mediaPresentationDescriptionParser = new MediaPresentationDescriptionParser(null, bArr);
            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(str, null, null, 8000, 8000, false, this.httpDataSourceCallback);
            this.manifestDataSource = defaultHttpDataSource;
            this.manifestFetcher = new ManifestFetcher<>(str2, defaultHttpDataSource, mediaPresentationDescriptionParser);
        }

        public void init() {
            this.manifestFetcher.singleLoad(this.player.getMainHandler().getLooper(), this);
        }

        public void cancel() {
            this.canceled = true;
        }

        @Override // com.google.android.exoplayer.util.ManifestFetcher.ManifestCallback
        public void onSingleManifest(MediaPresentationDescription mediaPresentationDescription) {
            UtcTimingElement utcTimingElement;
            if (this.canceled) {
                return;
            }
            this.manifest = mediaPresentationDescription;
            if (mediaPresentationDescription.dynamic && (utcTimingElement = mediaPresentationDescription.utcTiming) != null) {
                UtcTimingElementResolver.resolveTimingElement(this.manifestDataSource, utcTimingElement, this.manifestFetcher.getManifestLoadCompleteTimestamp(), this);
            } else {
                buildRenderers();
            }
        }

        @Override // com.google.android.exoplayer.util.ManifestFetcher.ManifestCallback
        public void onSingleManifestError(IOException iOException) {
            if (this.canceled) {
                return;
            }
            this.player.onRenderersError(iOException);
        }

        @Override // com.google.android.exoplayer.dash.mpd.UtcTimingElementResolver.UtcTimingCallback
        public void onTimestampResolved(UtcTimingElement utcTimingElement, long j) {
            if (this.canceled) {
                return;
            }
            this.elapsedRealtimeOffset = j;
            buildRenderers();
        }

        @Override // com.google.android.exoplayer.dash.mpd.UtcTimingElementResolver.UtcTimingCallback
        public void onTimestampError(UtcTimingElement utcTimingElement, IOException iOException) {
            if (this.canceled) {
                return;
            }
            Log.e("DashRendererBuilder", "Failed to resolve UtcTiming element [" + utcTimingElement + "]", iOException);
            buildRenderers();
        }

        /* JADX WARN: Code duplicated, block: B:38:0x00cd A[PHI: r6
          0x00cd: PHI (r6v20 com.google.android.exoplayer.drm.StreamingDrmSessionManager<com.google.android.exoplayer.drm.FrameworkMediaCrypto>) = 
          (r6v19 com.google.android.exoplayer.drm.StreamingDrmSessionManager<com.google.android.exoplayer.drm.FrameworkMediaCrypto>)
          (r6v26 com.google.android.exoplayer.drm.StreamingDrmSessionManager<com.google.android.exoplayer.drm.FrameworkMediaCrypto>)
         binds: [B:34:0x00af, B:36:0x00c9] A[DONT_GENERATE, DONT_INLINE]] */
        private void buildRenderers() {
            StreamingDrmSessionManager<FrameworkMediaCrypto> streamingDrmSessionManager;
            boolean z;
            StreamingDrmSessionManager<FrameworkMediaCrypto> streamingDrmSessionManagerNewMarlinInstance;
            Period period = this.manifest.getPeriod(0);
            Handler mainHandler = this.player.getMainHandler();
            DefaultLoadControl defaultLoadControl = new DefaultLoadControl(new DefaultAllocator(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN));
            DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter(mainHandler, this.player);
            boolean zHasContentProtection = false;
            for (int i = 0; i < period.adaptationSets.size(); i++) {
                AdaptationSet adaptationSet = period.adaptationSets.get(i);
                if (adaptationSet.type != -1) {
                    zHasContentProtection |= adaptationSet.hasContentProtection();
                }
            }
            trackSelection(period, this.player);
            if (!zHasContentProtection) {
                streamingDrmSessionManager = null;
                z = false;
            } else {
                if (Util.SDK_INT < 18) {
                    this.player.onRenderersError(new UnsupportedDrmException(1));
                    return;
                }
                try {
                    if (this.drmCallback == null) {
                        for (int i2 = 0; i2 < period.adaptationSets.size(); i2++) {
                            AdaptationSet adaptationSet2 = period.adaptationSets.get(i2);
                            if (adaptationSet2.type != -1) {
                                this.drmCallback = judgeDrmCallback(adaptationSet2.contentProtections);
                            }
                        }
                        if (this.drmCallback == null) {
                            Log.e("DashRendererBuilder", "drmCallback is null!");
                            return;
                        }
                    }
                    StreamingDrmSessionManager<FrameworkMediaCrypto> streamingDrmSessionManagerNewPlayReadyInstance = this.drmCallback instanceof PlayreadyTestMediaDrmCallback ? StreamingDrmSessionManager.newPlayReadyInstance(this.player.getPlaybackLooper(), this.drmCallback, this.customerData, this.player.getMainHandler(), this.player) : null;
                    if (this.drmCallback instanceof WidevineTestMediaDrmCallback) {
                        streamingDrmSessionManagerNewPlayReadyInstance = StreamingDrmSessionManager.newWidevineInstance(this.player.getPlaybackLooper(), this.drmCallback, null, this.player.getMainHandler(), this.player);
                        if (getWidevineSecurityLevel(streamingDrmSessionManagerNewPlayReadyInstance) != 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                    Class<?> cls = (Class) ExoPlayerDemoApplication.vendorClassMap.get("marlinDrmCallBack");
                    if (cls != null) {
                        Log.d("DashRendererBuilder", cls.toString());
                    }
                    if (this.drmCallback.getClass() == cls) {
                        try {
                            Method declaredMethod = cls.getDeclaredMethod("setContentIds", String.class);
                            if (declaredMethod == null) {
                                streamingDrmSessionManagerNewMarlinInstance = StreamingDrmSessionManager.newMarlinInstance(this.player.getPlaybackLooper(), this.drmCallback, this.manifest.marlinContentIds, this.player.getMainHandler(), this.player);
                            } else {
                                declaredMethod.invoke(this.drmCallback, this.manifest.marlinContentIds.get(String.valueOf(0)));
                                cls.getDeclaredMethod("setMS3Url", String.class).invoke(this.drmCallback, new String(this.actionToken, "UTF-8"));
                                streamingDrmSessionManagerNewMarlinInstance = StreamingDrmSessionManager.newMarlinInstance(this.player.getPlaybackLooper(), this.drmCallback, this.manifest.marlinContentIds, this.player.getMainHandler(), this.player);
                            }
                            streamingDrmSessionManagerNewPlayReadyInstance = streamingDrmSessionManagerNewMarlinInstance;
                        } catch (Exception e) {
                            this.player.onRenderersError(e);
                        }
                    }
                    streamingDrmSessionManager = streamingDrmSessionManagerNewPlayReadyInstance;
                } catch (UnsupportedDrmException e2) {
                    this.player.onRenderersError(e2);
                    return;
                }
            }
            MediaCodecVideoTrackRenderer mediaCodecVideoTrackRenderer = new MediaCodecVideoTrackRenderer(this.context, new ChunkSampleSource(new DashChunkSource(this.manifestFetcher, DefaultDashTrackSelector.newVideoInstance(this.context, true, z), new DefaultUriDataSource(this.context, this.userAgent), startFormatEvaluator(period, defaultBandwidthMeter), 30000L, this.elapsedRealtimeOffset, mainHandler, this.player, 0), defaultLoadControl, 13107200, mainHandler, this.player, 0), MediaCodecSelector.DEFAULT, 1, 5000L, streamingDrmSessionManager, true, mainHandler, this.player, 50);
            MediaCodecAudioTrackRenderer mediaCodecAudioTrackRenderer = new MediaCodecAudioTrackRenderer((SampleSource) new ChunkSampleSource(new DashChunkSource(this.manifestFetcher, DefaultDashTrackSelector.newAudioInstance(), new DefaultUriDataSource(this.context, defaultBandwidthMeter, this.userAgent), new FormatEvaluator.AdaptiveEvaluator(defaultBandwidthMeter), 30000L, this.elapsedRealtimeOffset, mainHandler, this.player, 1), defaultLoadControl, 3538944, mainHandler, this.player, 1), MediaCodecSelector.DEFAULT, (DrmSessionManager) streamingDrmSessionManager, true, mainHandler, (MediaCodecAudioTrackRenderer.EventListener) this.player, AudioCapabilities.getCapabilities(this.context), 3);
            TextTrackRenderer textTrackRenderer = new TextTrackRenderer(new ChunkSampleSource(new DashChunkSource(this.manifestFetcher, DefaultDashTrackSelector.newTextInstance(), new DefaultUriDataSource(this.context, this.userAgent), null, 30000L, this.elapsedRealtimeOffset, mainHandler, this.player, 2), defaultLoadControl, 131072, mainHandler, this.player, 2), this.player, mainHandler.getLooper(), new SubtitleParser[0]);
            TrackRenderer[] trackRendererArr = new TrackRenderer[6];
            trackRendererArr[0] = mediaCodecVideoTrackRenderer;
            trackRendererArr[1] = mediaCodecAudioTrackRenderer;
            trackRendererArr[2] = textTrackRenderer;
            this.player.onRenderers(trackRendererArr, defaultBandwidthMeter);
        }

        private static int getWidevineSecurityLevel(StreamingDrmSessionManager streamingDrmSessionManager) {
            String propertyString = streamingDrmSessionManager.getPropertyString("securityLevel");
            if (propertyString.equals("L1")) {
                return 1;
            }
            return propertyString.equals("L3") ? 3 : -1;
        }

        private int startFormatBitrate(List<Representation> list) {
            int i;
            int i2 = Integer.MAX_VALUE;
            for (int i3 = 0; i3 < list.size(); i3++) {
                Format format = list.get(i3).format;
                if (format.width >= 960 && format.height >= 540 && i2 > (i = format.bitrate)) {
                    i2 = i;
                }
            }
            if (i2 == Integer.MAX_VALUE) {
                return 0;
            }
            return i2;
        }

        private FormatEvaluator.AdaptiveEvaluator startFormatEvaluator(Period period, DefaultBandwidthMeter defaultBandwidthMeter) {
            for (int i = 0; i < period.adaptationSets.size(); i++) {
                AdaptationSet adaptationSet = period.adaptationSets.get(i);
                if (adaptationSet.type == 0) {
                    FormatEvaluator.AdaptiveEvaluator adaptiveEvaluator = new FormatEvaluator.AdaptiveEvaluator(defaultBandwidthMeter, startFormatBitrate(adaptationSet.representations), 0, 25000, 25000, 0.75f);
                    adaptiveEvaluator.setSmoothChangeFormat(true);
                    return adaptiveEvaluator;
                }
            }
            return new FormatEvaluator.AdaptiveEvaluator(defaultBandwidthMeter);
        }

        public void setHttpDataSourceCallback(DefaultHttpDataSource.Callback callback) {
            this.httpDataSourceCallback = callback;
        }

        private void trackSelection(Period period, DemoPlayer demoPlayer) {
            Class cls;
            if (demoPlayer.getSelectedTrack(2) == -1 && (cls = (Class) ExoPlayerDemoApplication.vendorClassMap.get("trackSelection")) != null) {
                try {
                    cls.getMethod("trackSelection", Period.class, DemoPlayer.class).invoke(null, period, demoPlayer);
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        private MediaDrmCallback judgeDrmCallback(List<ContentProtection> list) {
            UUID uuid;
            UUID uuid2;
            for (int i = 0; i < list.size(); i++) {
                ContentProtection contentProtection = list.get(i);
                if (contentProtection.data != null && (uuid2 = contentProtection.uuid) != null && uuid2.compareTo(StreamingDrmSessionManager.PLAYREADY_UUID) == 0) {
                    return new PlayreadyTestMediaDrmCallback();
                }
                if (contentProtection.data != null && (uuid = contentProtection.uuid) != null && uuid.compareTo(StreamingDrmSessionManager.VERYMATRIX_UUID) == 0) {
                    return null;
                }
            }
            return null;
        }
    }

    @Override // com.google.android.exoplayer.demo.player.DemoPlayer.RendererBuilder
    public void setHttpDataSourceCallback(DefaultHttpDataSource.Callback callback) {
        this.httpDataSourceCallback = callback;
    }

    public DashRendererBuilder(Context context, String str, String str2, MediaDrmCallback mediaDrmCallback, byte[] bArr) {
        this.context = context;
        this.userAgent = str;
        this.url = str2;
        this.drmCallback = mediaDrmCallback;
        this.actionToken = bArr;
    }

    public boolean forceSubtitle(int i) {
        Period period = this.currentAsyncBuilder.manifest.getPeriod(0);
        try {
            return ((Boolean) ((Class) ExoPlayerDemoApplication.vendorClassMap.get("trackSelection")).getMethod("forceSubtitle", Period.class, Integer.TYPE).invoke(null, period, Integer.valueOf(i))).booleanValue();
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }
}
