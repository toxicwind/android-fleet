package com.mediatek;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.TimeRange;
import com.google.android.exoplayer.audio.AudioCapabilities;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.demo.DemoUtil;
import com.google.android.exoplayer.demo.MarlinIntertrustTestMediaDrmCallback;
import com.google.android.exoplayer.demo.MarlinNautilusTestMediaDrmCallback;
import com.google.android.exoplayer.demo.PlayreadyTestMediaDrmCallback;
import com.google.android.exoplayer.demo.SmoothStreamingTestMediaDrmCallback;
import com.google.android.exoplayer.demo.WidevineTestMediaDrmCallback;
import com.google.android.exoplayer.demo.player.DashRendererBuilder;
import com.google.android.exoplayer.demo.player.DefaultRendererBuilder;
import com.google.android.exoplayer.demo.player.DemoPlayer;
import com.google.android.exoplayer.demo.player.ExtractorRendererBuilder;
import com.google.android.exoplayer.demo.player.HlsRendererBuilder;
import com.google.android.exoplayer.demo.player.SmoothStreamingRendererBuilder;
import com.google.android.exoplayer.drm.UnsupportedDrmException;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.upstream.FrameworkDataSource;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.MtkPlayerControl;
import com.google.android.exoplayer.util.Util;
import com.mediatek.mmp.util.PcmMediaInfo;
import com.mediatek.twoworlds.tv.MtkTvScanCeBase;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ExoMediaPlayer extends MtkMediaPlayer {
    private boolean asyncPreparing;
    private int audioIndicator;
    private double bitRate;
    private boolean buffering;
    private int contentType;
    private Uri contentUri;
    private Context context;
    private String cookie;
    private List<Cue> cues;
    private int currentSelectedProgramIndex;
    private String customData;
    private FrameworkDataSource.StreamDataSource dataSource;
    private int defaultRotationDegrees;
    private String drmHeader;
    private int drmType;
    private Handler eventHandler;
    private List<String> externalSubtitlePathList;
    private boolean hasExternalSubtitle;
    private boolean isSeeking;
    private String laUrl;
    private LastMemoryFilePosition mLastMemoryInfo;
    private boolean mScreenOnWhilePlaying;
    private int mSeekType;
    private boolean mStallingSupport;
    private volatile boolean mStayAwake;
    private SurfaceHolder mSurfaceHolder;
    private int mTunnelingMode;
    private MtkMediaPlayer.WebInterface mWebInterface;
    private String mimeType;
    private Looper myLooper;
    private MediaPlayer.OnCompletionListener onCompletionListener;
    private MediaPlayer.OnErrorListener onErrorListener;
    private MediaPlayer.OnInfoListener onInfoListener;
    private MediaPlayer.OnPreparedListener onPreparedlistener;
    private MediaPlayer.OnSeekCompleteListener onSeekCompleteListener;
    private MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener;
    private String output;
    private PcmMediaInfo pcmMediaInfo;
    private int pendingSeekPosition;
    private DemoPlayer player;
    private boolean prepared;
    private boolean renderStartInfoPending;
    private boolean renderStartInfoSent;
    private boolean renderingStart;
    private int seekMode;
    private int state;
    private Surface surface;
    private SurfaceHolder.Callback surfaceHolderCallback;
    private int[][] trackIndexMap;
    private String userAgent;
    private int videoHeight;
    private int videoWidth;
    private int mDataSourceType = 0;
    private PowerManager.WakeLock mWakeLock = null;

    public static class LastMemoryFilePosition {
        public int ui2_aud_idx;
        public int ui2_sub_idx;
        public int ui2_vid_idx;
        public long ui8_clip_id;
        public long ui8_frame_position;
    }

    @Override // android.media.MediaPlayer
    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
    }

    public ExoMediaPlayer() {
        int i = 0;
        new ArrayList();
        this.mStallingSupport = true;
        this.mSeekType = 3;
        this.output = "OUTPUT_VIDEO_MAIN";
        this.defaultRotationDegrees = 0;
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.prepared = false;
        this.asyncPreparing = false;
        this.isSeeking = false;
        this.pendingSeekPosition = -1;
        this.buffering = false;
        this.renderStartInfoPending = false;
        this.renderStartInfoSent = false;
        this.mTunnelingMode = 1;
        this.currentSelectedProgramIndex = 0;
        this.seekMode = 0;
        this.audioIndicator = -1;
        Log.i("ExoMediaPlayer", "[Performance]ExoMediaPlayer() Enter");
        ExoHanlderCallback exoHanlderCallback = new ExoHanlderCallback();
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.eventHandler = new Handler(looperMyLooper, exoHanlderCallback);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.eventHandler = new Handler(mainLooper, exoHanlderCallback);
            } else {
                this.eventHandler = null;
            }
        }
        this.state = 0;
        try {
            i = Integer.parseInt(Util.getProp("mtk.exoplayer.debug.level", "0"));
        } catch (NumberFormatException e) {
            Log.e("ExoMediaPlayer", "Invalid debug level string. " + e.toString());
        }
        MtkDebugLogUtil.setDebugLogType(i);
        Log.i("ExoMediaPlayer", "ExoMediaPlayer(): Leave");
    }

    private void updateSurfaceScreenOn() {
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.setKeepScreenOn(this.mScreenOnWhilePlaying && this.mStayAwake);
        }
    }

    private void stayAwake(boolean z) {
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (z && !wakeLock.isHeld()) {
                this.mWakeLock.acquire();
            } else if (!z && this.mWakeLock.isHeld()) {
                this.mWakeLock.release();
            }
        }
        this.mStayAwake = z;
        updateSurfaceScreenOn();
    }

    @Override // com.mediatek.MtkMediaPlayer, android.media.MediaPlayer
    public void setDataSource(Context context, Uri uri) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Log.d("ExoMediaPlayer", "setDataSource(Context context, Uri uri) called");
        if (this.state != 0) {
            throw new IllegalStateException("current state is " + this.state);
        }
        setDataSource(context, uri, (Map<String, String>) null);
        this.state = 1;
    }

    @Override // com.mediatek.MtkMediaPlayer, android.media.MediaPlayer
    public void setDataSource(Context context, Uri uri, final Map<String, String> map) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Log.d("ExoMediaPlayer", "setDataSource(Context context, Uri uri, Map<String, String> headers) called");
        if (this.state != 0) {
            throw new IllegalStateException("current state is " + this.state);
        }
        if (uri == null) {
            Log.d("ExoMediaPlayer", "If crash, need APP add try catch");
            throw new IllegalArgumentException("uri is null");
        }
        Log.d("ExoMediaPlayer", "setDataSource, uri=" + uri.toString());
        if (this.eventHandler != null) {
            Log.d("ExoMediaPlayer", "setDataSource: remove all messages");
            this.eventHandler.removeCallbacksAndMessages(null);
        }
        this.contentUri = uri;
        this.context = context;
        String scheme = uri.getScheme();
        if ("http".equals(scheme) || "https".equals(scheme)) {
            this.contentUri.toString();
            Thread thread = new Thread() { // from class: com.mediatek.ExoMediaPlayer.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    ExoMediaPlayer exoMediaPlayer = ExoMediaPlayer.this;
                    exoMediaPlayer.mimeType = exoMediaPlayer.getMimeHeader(exoMediaPlayer.contentUri, map);
                    Log.d("ExoMediaPlayer", "mimeType=" + ExoMediaPlayer.this.mimeType);
                    ExoMediaPlayer exoMediaPlayer2 = ExoMediaPlayer.this;
                    exoMediaPlayer2.contentType = exoMediaPlayer2.getContentType(exoMediaPlayer2.contentUri, ExoMediaPlayer.this.mimeType);
                    Log.d("ExoMediaPlayer", "contentType=" + ExoMediaPlayer.this.contentType);
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.contentType = MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG;
        }
        this.state = 1;
    }

    @Override // com.mediatek.MtkMediaPlayer, android.media.MediaPlayer
    @Deprecated
    public void setDataSource(String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Log.d("ExoMediaPlayer", "setDataSource(String path) called");
        throw new IllegalArgumentException("context can't be null");
    }

    @Override // com.mediatek.MtkMediaPlayer
    @Deprecated
    public void setDataSource(String str, Map<String, String> map) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Log.d("ExoMediaPlayer", "setDataSource(String path, Map<String, String> headers) called");
        throw new IllegalArgumentException("context can't be null");
    }

    @Override // com.mediatek.MtkMediaPlayer
    public boolean setSubtitleDataSource(String str) {
        Log.d("ExoMediaPlayer", "setSubtitleDataSource called. uri=" + str);
        int i = this.state;
        if (i != 0 && i != 1) {
            Log.e("ExoMediaPlayer", "setSubtitleDataSource() is called in wrong state. Current state is " + this.state);
            return false;
        }
        if (str == null || "".equals(str)) {
            Log.e("ExoMediaPlayer", "Illegal argument! Uri is null or empty.");
            return false;
        }
        this.hasExternalSubtitle = true;
        if (this.externalSubtitlePathList == null) {
            this.externalSubtitlePathList = new ArrayList();
        }
        this.externalSubtitlePathList.add(str);
        return true;
    }

    private int getAllVideoTrackCount() throws IllegalStateException {
        Log.d("ExoMediaPlayer", "getAllVideoTrackCount");
        int i = 0;
        try {
            if (this.player != null && this.player.getMtkPlayerControl() != null) {
                List<Integer> programList = this.player.getMtkPlayerControl().getProgramList();
                int i2 = 0;
                for (int i3 = 0; i3 < this.player.getMtkPlayerControl().getTrackCount(0); i3++) {
                    try {
                        MediaFormat trackFormat = this.player.getMtkPlayerControl().getTrackFormat(0, i3);
                        if (programList == null || programList.size() == 0 || trackFormat.group == programList.get(this.currentSelectedProgramIndex).intValue()) {
                            i2++;
                        }
                    } catch (Exception e) {
                        e = e;
                        i = i2;
                        Log.d("ExoMediaPlayer", e.toString());
                    }
                }
                i = i2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        Log.d("ExoMediaPlayer", "getAllVideoTrackCount:" + i);
        return i;
    }

    public int getAllAudioTrackCount() throws IllegalStateException {
        Log.d("ExoMediaPlayer", "getAllAudioTrackCount() called");
        int i = this.state;
        if (i == 0 || i == 1 || i == 2 || i == 6 || i == 8 || i == 9) {
            throw new IllegalStateException("current state is " + this.state);
        }
        int i2 = 0;
        try {
            if (this.player != null && this.player.getMtkPlayerControl() != null) {
                List<Integer> programList = this.player.getMtkPlayerControl().getProgramList();
                int trackCount = this.player.getMtkPlayerControl().getTrackCount(1);
                if (trackCount > 0 && this.trackIndexMap[1] == null) {
                    this.trackIndexMap[1] = new int[trackCount];
                    Arrays.fill(this.trackIndexMap[1], -1);
                }
                int i3 = 0;
                int i4 = 0;
                while (i2 < trackCount) {
                    try {
                        MediaFormat trackFormat = this.player.getMtkPlayerControl().getTrackFormat(1, i2);
                        if (programList == null || programList.size() == 0 || trackFormat.group == programList.get(this.currentSelectedProgramIndex).intValue()) {
                            if (this.trackIndexMap[1][i4] == -1) {
                                this.trackIndexMap[1][i4] = i2;
                            }
                            i4++;
                            i3++;
                        }
                        i2++;
                    } catch (Exception e) {
                        e = e;
                        i2 = i3;
                        Log.d("ExoMediaPlayer", e.toString());
                        Log.d("ExoMediaPlayer", "getAllAudioTrackCount:" + i2);
                        return i2;
                    }
                }
                i2 = i3;
            }
        } catch (Exception e2) {
            e = e2;
        }
        Log.d("ExoMediaPlayer", "getAllAudioTrackCount:" + i2);
        return i2;
    }

    @Override // android.media.MediaPlayer
    public void start() throws IllegalStateException {
        Log.d("ExoMediaPlayer", "start() called");
        int i = this.state;
        if (i != 3 && i != 4 && i != 5 && i != 7) {
            if (i == 6 || i == 9) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 6007;
                messageObtain.arg1 = -38;
                messageObtain.arg2 = 0;
                this.eventHandler.sendMessage(messageObtain);
                return;
            }
            throw new IllegalStateException("Current state is " + this.state);
        }
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer != null && demoPlayer.getMtkPlayerControl() != null) {
            if (this.state == 7) {
                this.player.getMtkPlayerControl().seekTo(0);
            }
            this.player.getMtkPlayerControl().start();
            if (this.renderStartInfoPending) {
                Log.d("ExoMediaPlayer", "onInfo send MEDIA_INFO_VIDEO_RENDERING_START");
                this.renderStartInfoPending = false;
                Message messageObtain2 = Message.obtain();
                messageObtain2.what = 6002;
                messageObtain2.arg1 = 3;
                messageObtain2.arg2 = 0;
                this.eventHandler.sendMessage(messageObtain2);
                this.renderStartInfoSent = true;
            }
        }
        this.state = 4;
    }

    private void releasePlayer() {
        SurfaceHolder.Callback callback;
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
        if (surfaceHolder != null && (callback = this.surfaceHolderCallback) != null) {
            surfaceHolder.removeCallback(callback);
            this.surfaceHolderCallback = null;
            this.mSurfaceHolder = null;
        }
        Handler handler = this.eventHandler;
        if (handler != null) {
            handler.removeMessages(6003);
        }
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer != null) {
            demoPlayer.release();
            this.player = null;
        }
        List<String> list = this.externalSubtitlePathList;
        if (list != null) {
            list.clear();
            this.externalSubtitlePathList = null;
        }
        this.defaultRotationDegrees = 0;
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.renderStartInfoPending = false;
        this.renderStartInfoSent = false;
        this.pendingSeekPosition = -1;
        AudioCapabilities.enablePassThrough();
    }

    @Override // android.media.MediaPlayer
    public void release() {
        Log.d("ExoMediaPlayer", "release() called");
        reset();
        super.release();
        this.state = 8;
    }

    @Override // android.media.MediaPlayer
    public void reset() {
        Log.d("ExoMediaPlayer", "reset() called");
        synchronized (this) {
            while (this.asyncPreparing) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        releasePlayer();
        Looper looper = this.myLooper;
        if (looper != null) {
            looper.quitSafely();
            this.myLooper = null;
        }
        this.prepared = false;
        this.dataSource = null;
        this.contentUri = null;
        super.reset();
        this.state = 0;
    }

    @Override // android.media.MediaPlayer
    public void setDisplay(SurfaceHolder surfaceHolder) {
        Log.d("ExoMediaPlayer", "setDisplay");
        if (surfaceHolder != null) {
            this.mSurfaceHolder = surfaceHolder;
            this.surface = surfaceHolder.getSurface();
            if (this.surfaceHolderCallback == null) {
                this.surfaceHolderCallback = new SurfaceHolder.Callback() { // from class: com.mediatek.ExoMediaPlayer.3
                    @Override // android.view.SurfaceHolder.Callback
                    public void surfaceChanged(SurfaceHolder surfaceHolder2, int i, int i2, int i3) {
                    }

                    @Override // android.view.SurfaceHolder.Callback
                    public void surfaceCreated(SurfaceHolder surfaceHolder2) {
                    }

                    @Override // android.view.SurfaceHolder.Callback
                    public void surfaceDestroyed(SurfaceHolder surfaceHolder2) {
                        if (ExoMediaPlayer.this.player != null) {
                            ExoMediaPlayer.this.player.blockingClearSurface();
                        }
                    }
                };
            }
            surfaceHolder.addCallback(this.surfaceHolderCallback);
            DemoPlayer demoPlayer = this.player;
            if (demoPlayer != null) {
                demoPlayer.setSurface(this.surface);
            }
            if (this.mDataSourceType != 0) {
                updateSurfaceScreenOn();
            }
        }
    }

    @Override // android.media.MediaPlayer
    public void setSurface(Surface surface) {
        Log.d("ExoMediaPlayer", "setSurface");
        this.mSurfaceHolder = null;
        this.surface = surface;
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer != null) {
            demoPlayer.setSurface(surface);
        }
        if (this.mDataSourceType != 0) {
            updateSurfaceScreenOn();
        }
    }

    @Override // android.media.MediaPlayer
    public void prepare() throws IllegalStateException, IOException {
        Uri uri;
        Log.d("ExoMediaPlayer", "prepare() called");
        int i = this.state;
        if (i != 1 && i != 6) {
            throw new IllegalStateException("current state is " + this.state);
        }
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer != null) {
            demoPlayer.release();
            this.player = null;
        }
        DemoPlayer.RendererBuilder rendererBuilder = getRendererBuilder();
        if ((rendererBuilder instanceof DefaultRendererBuilder) && this.hasExternalSubtitle && (uri = this.contentUri) != null) {
            ((DefaultRendererBuilder) rendererBuilder).addExternalSubtitle(DemoUtil.scanExternalSubtitle(uri));
        }
        DemoPlayer demoPlayer2 = new DemoPlayer(rendererBuilder);
        this.player = demoPlayer2;
        demoPlayer2.addListener(new DemoPlayer.Listener() { // from class: com.mediatek.ExoMediaPlayer.4
            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.Listener
            public void onVideoSizeChanged(int i2, int i3, int i4, float f) {
                ExoMediaPlayer.this.videoWidth = i2;
                ExoMediaPlayer.this.videoHeight = i3;
                if (ExoMediaPlayer.this.eventHandler != null) {
                    ExoMediaPlayer.this.eventHandler.sendEmptyMessage(6004);
                }
            }

            /* JADX WARN: Code duplicated, block: B:72:0x014c  */
            /* JADX WARN: Code duplicated, block: B:76:? A[RETURN, SYNTHETIC] */
            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.Listener
            public void onError(Exception exc) {
                int i2;
                boolean[] renderersUnsupported;
                int i3 = 4000;
                String str = "error";
                if (!(exc instanceof UnsupportedDrmException)) {
                    boolean z = exc instanceof ExoPlaybackException;
                    if (z && (exc.getCause() instanceof IllegalArgumentException)) {
                        i3 = -5003;
                    } else if (z) {
                        i2 = ((ExoPlaybackException) exc).codecErrorType;
                        if (i2 != 1) {
                            if (i2 != 0) {
                                if (i2 == 3) {
                                    Log.e("ExoMediaPlayer", "MEDIA_ERROR_RESOURCE_INTERRUPT 1");
                                } else if (!(exc.getCause() instanceof IOException)) {
                                    if (!(exc.getCause() instanceof IllegalStateException)) {
                                        if (ExoMediaPlayer.this.player == null || ExoMediaPlayer.this.player.getMtkPlayerControl() == null || ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(0) != 0 || ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(1) != 0) {
                                            str = "error";
                                            i3 = 1;
                                        }
                                    } else {
                                        Log.e("ExoMediaPlayer", "MEDIA_ERROR_RESOURCE_INTERRUPT 2");
                                    }
                                }
                                str = "error";
                            } else if (!ExoMediaPlayer.this.prepared || ExoMediaPlayer.this.player.getTrackCount(1) != 0) {
                                i3 = -5000;
                                str = "info";
                            }
                            str = "error";
                            i3 = -5002;
                        } else if (ExoMediaPlayer.this.prepared && ExoMediaPlayer.this.player.getTrackCount(0) == 0) {
                            str = "error";
                            i3 = -5002;
                        } else {
                            i3 = -5001;
                            str = "info";
                        }
                    } else {
                        i3 = exc instanceof IOException ? -5002 : 1;
                        i2 = 0;
                    }
                    if (!"error".equals(str) && ExoMediaPlayer.this.onErrorListener != null) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 6007;
                        messageObtain.arg1 = i3;
                        messageObtain.arg2 = 0;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
                        return;
                    }
                    if ("info".equals(str) && ExoMediaPlayer.this.onInfoListener != null) {
                        Message messageObtain2 = Message.obtain();
                        messageObtain2.what = 6002;
                        messageObtain2.arg1 = i3;
                        messageObtain2.arg2 = 0;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain2);
                    }
                    if (i2 != 1 || i2 == 0) {
                        renderersUnsupported = ExoMediaPlayer.this.player.getRenderersUnsupported();
                        if (renderersUnsupported[1] || !renderersUnsupported[0]) {
                            Log.i("ExoMediaPlayer", "Catch MediaCodec error, try to replay it!");
                            ExoMediaPlayer.this.player.setOutputPath(ExoMediaPlayer.this.output);
                            ExoMediaPlayer.this.player.prepare();
                            if (ExoMediaPlayer.this.player != null) {
                                ExoMediaPlayer.this.player.setSurface(ExoMediaPlayer.this.surface);
                                ExoMediaPlayer.this.player.setPlayWhenReady(false);
                            }
                        }
                        return;
                    }
                    return;
                }
                i3 = 263;
                i2 = 0;
                if (!"error".equals(str)) {
                }
                if ("info".equals(str)) {
                    Message messageObtain3 = Message.obtain();
                    messageObtain3.what = 6002;
                    messageObtain3.arg1 = i3;
                    messageObtain3.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain3);
                }
                if (i2 != 1) {
                }
                renderersUnsupported = ExoMediaPlayer.this.player.getRenderersUnsupported();
                if (renderersUnsupported[1]) {
                }
                Log.i("ExoMediaPlayer", "Catch MediaCodec error, try to replay it!");
                ExoMediaPlayer.this.player.setOutputPath(ExoMediaPlayer.this.output);
                ExoMediaPlayer.this.player.prepare();
                if (ExoMediaPlayer.this.player != null) {
                    ExoMediaPlayer.this.player.setSurface(ExoMediaPlayer.this.surface);
                    ExoMediaPlayer.this.player.setPlayWhenReady(false);
                }
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.Listener
            public void onStateChanged(boolean z, int i2) {
                if (i2 == 3) {
                    if (ExoMediaPlayer.this.eventHandler == null || ExoMediaPlayer.this.isTrickingExo()) {
                        return;
                    }
                    ExoMediaPlayer.this.buffering = true;
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 6002;
                    messageObtain.arg1 = 701;
                    messageObtain.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
                    return;
                }
                if (i2 != 4) {
                    if (i2 == 5 && ExoMediaPlayer.this.eventHandler != null) {
                        if (ExoMediaPlayer.this.player != null && ExoMediaPlayer.this.player.getMtkPlayerControl() != null && ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(0) == 0 && ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(1) == 0) {
                            Message messageObtain2 = Message.obtain();
                            messageObtain2.what = 6007;
                            messageObtain2.arg1 = -5002;
                            messageObtain2.arg2 = 0;
                            ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain2);
                            return;
                        }
                        Message messageObtain3 = Message.obtain();
                        messageObtain3.what = 6003;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain3);
                        return;
                    }
                    return;
                }
                if (ExoMediaPlayer.this.eventHandler == null || !ExoMediaPlayer.this.prepared) {
                    return;
                }
                if (ExoMediaPlayer.this.buffering) {
                    ExoMediaPlayer.this.buffering = false;
                    Message messageObtain4 = Message.obtain();
                    messageObtain4.what = 6002;
                    messageObtain4.arg1 = 702;
                    messageObtain4.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain4);
                }
                if (ExoMediaPlayer.this.isSeeking) {
                    ExoMediaPlayer.this.eventHandler.sendEmptyMessage(6006);
                    ExoMediaPlayer.this.isSeeking = false;
                }
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.Listener
            public void onInfo(int i2) {
                int trackCount;
                int trackCount2;
                int trackCount3;
                MediaFormat trackFormat;
                if (i2 != 3) {
                    return;
                }
                if (ExoMediaPlayer.this.player == null || ExoMediaPlayer.this.player.getMtkPlayerControl() == null) {
                    trackCount = 0;
                    trackCount2 = 0;
                    trackCount3 = 0;
                } else {
                    trackCount = ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(0);
                    trackCount2 = ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(1);
                    trackCount3 = ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(2);
                    if (ExoMediaPlayer.this.player.getMtkPlayerControl().getSelectedTrack(0) >= 0 && (trackFormat = ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackFormat(0, ExoMediaPlayer.this.player.getMtkPlayerControl().getSelectedTrack(0))) != null) {
                        ExoMediaPlayer.this.videoWidth = trackFormat.width;
                        ExoMediaPlayer.this.videoHeight = trackFormat.height;
                    }
                }
                Log.d("ExoMediaPlayer", "videoTrackCount=" + trackCount + ", audioTrackCount=" + trackCount2 + ", textTrackCount=" + trackCount3 + ", videoWidth=" + ExoMediaPlayer.this.videoWidth + ", videoHeight=" + ExoMediaPlayer.this.videoHeight);
                ExoMediaPlayer.this.prepared = true;
                ExoMediaPlayer.this.eventHandler.sendEmptyMessage(6001);
                if (trackCount3 == 0) {
                    if (trackCount == 0 && trackCount2 != 0) {
                        Log.d("ExoMediaPlayer", "send audio only message");
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 6002;
                        messageObtain.arg1 = -5008;
                        messageObtain.arg2 = 0;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
                    } else if (trackCount2 == 0 && trackCount != 0) {
                        Log.d("ExoMediaPlayer", "send video only message");
                        Message messageObtain2 = Message.obtain();
                        messageObtain2.what = 6002;
                        messageObtain2.arg1 = -5009;
                        messageObtain2.arg2 = 0;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain2);
                    }
                }
                if (ExoMediaPlayer.this.player != null && ExoMediaPlayer.this.player.getMtkPlayerControl() != null && !ExoMediaPlayer.this.player.getMtkPlayerControl().canSeekForward() && !ExoMediaPlayer.this.player.getMtkPlayerControl().canSeekBackward()) {
                    Message messageObtain3 = Message.obtain();
                    messageObtain3.what = 6002;
                    messageObtain3.arg1 = 801;
                    messageObtain3.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain3);
                }
                Message messageObtain4 = Message.obtain();
                messageObtain4.what = 6002;
                messageObtain4.arg1 = 3;
                messageObtain4.arg2 = 0;
                ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain4);
            }
        });
        this.player.setInfoListener(new DemoPlayer.InfoListener() { // from class: com.mediatek.ExoMediaPlayer.5
            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onAudioFormatEnabled(Format format, int i2, long j) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onAvailableRangeChanged(int i2, TimeRange timeRange) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onDecoderInitialized(String str, long j, long j2) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onDroppedFrames(int i2, long j) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onLoadCompleted(int i2, long j, int i3, int i4, Format format, long j2, long j3, long j4, long j5) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onLoadStarted(int i2, long j, int i3, int i4, Format format, long j2, long j3) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onVideoFormatEnabled(Format format, int i2, long j) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onBandwidthSample(int i2, long j, long j2) {
                ExoMediaPlayer.this.bitRate = j2;
            }
        });
        this.player.getMtkPlayerControl().setCaptionListener(new MtkPlayerControl.CaptionListener() { // from class: com.mediatek.ExoMediaPlayer.6
            @Override // com.google.android.exoplayer.util.MtkPlayerControl.CaptionListener
            public void onCues(List<Cue> list) {
                Log.d("ExoMediaPlayer", "onCues called");
                if (!ExoMediaPlayer.this.isTrickingExo()) {
                    ExoMediaPlayer.this.cues = list;
                    if (list != null && list.size() > 0) {
                        Log.d("ExoMediaPlayer", "cues != null; cues.size = " + list.size());
                        Iterator<Cue> it = list.iterator();
                        while (it.hasNext()) {
                            Log.d("ExoMediaPlayer", "cue.text=" + ((Object) it.next().text));
                        }
                    }
                    if (ExoMediaPlayer.this.eventHandler != null) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 6005;
                        messageObtain.obj = list;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
                        return;
                    }
                    return;
                }
                Log.v("ExoMediaPlayer", "Tricking...don't show subtitles");
            }
        });
        this.player.getMtkPlayerControl().addListener(new MtkPlayerControl.Listener() { // from class: com.mediatek.ExoMediaPlayer.7
            @Override // com.google.android.exoplayer.util.MtkPlayerControl.Listener
            public void onPrepared() {
            }

            @Override // com.google.android.exoplayer.util.MtkPlayerControl.Listener
            public void onSeekReadyInTrick() {
            }

            @Override // com.google.android.exoplayer.util.MtkPlayerControl.Listener
            public void onTrackStateChanged(int i2, int i3, boolean z) {
            }

            @Override // com.google.android.exoplayer.util.MtkPlayerControl.Listener
            public void onTrickEnded(int i2, int i3) {
                if (ExoMediaPlayer.this.player == null || ExoMediaPlayer.this.player.getMtkPlayerControl() == null) {
                    return;
                }
                Message messageObtain = Message.obtain();
                if (i3 == 0) {
                    messageObtain.what = 6002;
                    if (ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(0) > 0) {
                        messageObtain.arg1 = -5100;
                    } else {
                        messageObtain.arg1 = -5006;
                    }
                    messageObtain.arg2 = 0;
                } else {
                    messageObtain.what = 6003;
                }
                ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
            }
        });
        this.player.setOutputPath(this.output);
        this.player.prepare();
        DemoPlayer demoPlayer3 = this.player;
        if (demoPlayer3 != null) {
            demoPlayer3.setSurface(this.surface);
            this.player.setPlayWhenReady(false);
        }
        this.state = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareExo() {
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer != null) {
            demoPlayer.release();
            this.player = null;
        }
        this.trackIndexMap = new int[3][];
        DemoPlayer.RendererBuilder rendererBuilder = getRendererBuilder();
        if (rendererBuilder instanceof DefaultRendererBuilder) {
            FrameworkDataSource.StreamDataSource streamDataSource = this.dataSource;
            if (streamDataSource != null) {
                DLNAFrameworkDataSource dLNAFrameworkDataSource = new DLNAFrameworkDataSource(streamDataSource, this.pcmMediaInfo);
                DefaultRendererBuilder defaultRendererBuilder = (DefaultRendererBuilder) rendererBuilder;
                defaultRendererBuilder.setDataSource(dLNAFrameworkDataSource);
                if (this.hasExternalSubtitle) {
                    String[] strArr = new String[this.externalSubtitlePathList.size()];
                    this.externalSubtitlePathList.toArray(strArr);
                    defaultRendererBuilder.addExternalSubtitle(strArr);
                }
            } else {
                Uri uri = this.contentUri;
                if (uri != null && this.hasExternalSubtitle) {
                    if ("http".equals(uri.getScheme()) || "https".equals(this.contentUri.getScheme())) {
                        String[] strArr2 = new String[this.externalSubtitlePathList.size()];
                        this.externalSubtitlePathList.toArray(strArr2);
                        ((DefaultRendererBuilder) rendererBuilder).addExternalSubtitle(strArr2);
                    } else {
                        ((DefaultRendererBuilder) rendererBuilder).addExternalSubtitle(DemoUtil.scanExternalSubtitle(this.contentUri));
                    }
                }
            }
        }
        DemoPlayer demoPlayer2 = new DemoPlayer(rendererBuilder);
        this.player = demoPlayer2;
        demoPlayer2.addListener(new DemoPlayer.Listener() { // from class: com.mediatek.ExoMediaPlayer.8
            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.Listener
            public void onVideoSizeChanged(int i, int i2, int i3, float f) {
                ExoMediaPlayer.this.videoWidth = i;
                ExoMediaPlayer.this.videoHeight = i2;
                if (ExoMediaPlayer.this.eventHandler != null) {
                    ExoMediaPlayer.this.eventHandler.sendEmptyMessage(6004);
                }
            }

            /* JADX WARN: Code duplicated, block: B:76:0x0170  */
            /* JADX WARN: Code duplicated, block: B:78:0x018e  */
            /* JADX WARN: Code duplicated, block: B:83:? A[RETURN, SYNTHETIC] */
            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.Listener
            public void onError(Exception exc) {
                int i;
                boolean[] renderersUnsupported;
                int i2 = 4000;
                String str = "error";
                if (!(exc instanceof UnsupportedDrmException)) {
                    boolean z = exc instanceof ExoPlaybackException;
                    if (z && (exc.getCause() instanceof IllegalArgumentException)) {
                        i2 = -5003;
                    } else {
                        if (z) {
                            i = ((ExoPlaybackException) exc).codecErrorType;
                            Log.d("ExoMediaPlayer", "onError: codecErrorType:" + i + ", prepared:" + ExoMediaPlayer.this.prepared);
                            if (i != 1) {
                                if (i == 0) {
                                    if (!ExoMediaPlayer.this.prepared || ExoMediaPlayer.this.player.getTrackCount(1) != 0) {
                                        i2 = -5000;
                                        str = "info";
                                    }
                                } else if (i != 2) {
                                    if (i == 3) {
                                        Log.e("ExoMediaPlayer", "MEDIA_ERROR_RESOURCE_INTERRUPT 1");
                                    } else if (!(exc.getCause() instanceof IOException)) {
                                        if (!(exc.getCause() instanceof IllegalStateException)) {
                                            if (ExoMediaPlayer.this.player == null || ExoMediaPlayer.this.player.getMtkPlayerControl() == null || ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(0) != 0 || ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(1) != 0) {
                                            }
                                        } else {
                                            Log.e("ExoMediaPlayer", "MEDIA_ERROR_RESOURCE_INTERRUPT 2");
                                        }
                                    }
                                    str = "error";
                                }
                                str = "error";
                                i2 = -5002;
                            } else if (ExoMediaPlayer.this.prepared && ExoMediaPlayer.this.player.getTrackCount(0) == 0) {
                                str = "error";
                                i2 = -5002;
                            } else {
                                i2 = -5001;
                                str = "info";
                            }
                        } else if (exc instanceof IOException) {
                            i2 = -5002;
                            i = 0;
                        } else {
                            i = 0;
                        }
                        i2 = 1;
                    }
                    if (!"error".equals(str) && ExoMediaPlayer.this.onErrorListener != null) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 6007;
                        messageObtain.arg1 = i2;
                        messageObtain.arg2 = 0;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
                        return;
                    }
                    if ("info".equals(str) && ExoMediaPlayer.this.onInfoListener != null) {
                        Message messageObtain2 = Message.obtain();
                        messageObtain2.what = 6002;
                        messageObtain2.arg1 = i2;
                        messageObtain2.arg2 = 0;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain2);
                    }
                    if (i != 1 || i == 0) {
                        renderersUnsupported = ExoMediaPlayer.this.player.getRenderersUnsupported();
                        if (renderersUnsupported[1] || !renderersUnsupported[0]) {
                            Log.i("ExoMediaPlayer", "Catch MediaCodec error, try to replay it!");
                            ExoMediaPlayer.this.player.setOutputPath(ExoMediaPlayer.this.output);
                            ExoMediaPlayer.this.player.prepare();
                            if (ExoMediaPlayer.this.player != null) {
                                ExoMediaPlayer.this.player.setSurface(ExoMediaPlayer.this.surface);
                                ExoMediaPlayer.this.player.setPlayWhenReady(ExoMediaPlayer.this.state == 4);
                            }
                        }
                        return;
                    }
                    return;
                }
                i2 = 263;
                i = 0;
                if (!"error".equals(str)) {
                }
                if ("info".equals(str)) {
                    Message messageObtain3 = Message.obtain();
                    messageObtain3.what = 6002;
                    messageObtain3.arg1 = i2;
                    messageObtain3.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain3);
                }
                if (i != 1) {
                }
                renderersUnsupported = ExoMediaPlayer.this.player.getRenderersUnsupported();
                if (renderersUnsupported[1]) {
                }
                Log.i("ExoMediaPlayer", "Catch MediaCodec error, try to replay it!");
                ExoMediaPlayer.this.player.setOutputPath(ExoMediaPlayer.this.output);
                ExoMediaPlayer.this.player.prepare();
                if (ExoMediaPlayer.this.player != null) {
                    ExoMediaPlayer.this.player.setSurface(ExoMediaPlayer.this.surface);
                    ExoMediaPlayer.this.player.setPlayWhenReady(ExoMediaPlayer.this.state == 4);
                }
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.Listener
            public void onStateChanged(boolean z, int i) {
                Log.d("ExoMediaPlayer", "play state change: playWhenReady=" + z + ", plabackState=" + i);
                if (i == 3) {
                    if (ExoMediaPlayer.this.eventHandler == null || ExoMediaPlayer.this.isTrickingExo()) {
                        return;
                    }
                    ExoMediaPlayer.this.buffering = true;
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 6002;
                    messageObtain.arg1 = 701;
                    messageObtain.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
                    return;
                }
                if (i != 4) {
                    if (i != 5 || ExoMediaPlayer.this.player == null || ExoMediaPlayer.this.eventHandler == null) {
                        return;
                    }
                    Message messageObtain2 = Message.obtain();
                    if (ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(0) == 0 && ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(1) == 0) {
                        messageObtain2.what = 6007;
                        messageObtain2.arg1 = -5002;
                        messageObtain2.arg2 = 0;
                    } else {
                        messageObtain2.what = 6003;
                    }
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain2);
                    return;
                }
                if (ExoMediaPlayer.this.eventHandler == null || !ExoMediaPlayer.this.prepared) {
                    return;
                }
                if (ExoMediaPlayer.this.buffering) {
                    ExoMediaPlayer.this.buffering = false;
                    Message messageObtain3 = Message.obtain();
                    messageObtain3.what = 6002;
                    messageObtain3.arg1 = 702;
                    messageObtain3.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain3);
                }
                if (ExoMediaPlayer.this.isSeeking) {
                    ExoMediaPlayer.this.eventHandler.sendEmptyMessage(6006);
                    ExoMediaPlayer.this.isSeeking = false;
                }
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.Listener
            public void onInfo(int i) {
                MediaFormat trackFormat;
                if (i == 2) {
                    Log.d("ExoMediaPlayer", "Receive event:MTK_INFO_TYPE_RENDER_FIRST_FRAME");
                    if (!ExoMediaPlayer.this.player.getPlayWhenReady() || ExoMediaPlayer.this.isTrickingExo() || (ExoMediaPlayer.this.isSeeking && ExoMediaPlayer.this.renderStartInfoSent)) {
                        if (ExoMediaPlayer.this.player.getPlayWhenReady() || ExoMediaPlayer.this.state < 3 || !ExoMediaPlayer.this.player.isTunnlingMode()) {
                            return;
                        }
                        Log.d("ExoMediaPlayer", "render start info pending...");
                        ExoMediaPlayer.this.renderStartInfoPending = true;
                        return;
                    }
                    Log.d("ExoMediaPlayer", "onInfo2 send MEDIA_INFO_VIDEO_RENDERING_START");
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 6002;
                    messageObtain.arg1 = 3;
                    messageObtain.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
                    ExoMediaPlayer.this.renderingStart = true;
                    ExoMediaPlayer.this.renderStartInfoSent = true;
                    return;
                }
                if (i != 3) {
                    return;
                }
                Log.d("ExoMediaPlayer", "[Performance]onInfo recive MTK_INFO_TYPE_PREPARED");
                ExoMediaPlayer.this.prepared = true;
                if (ExoMediaPlayer.this.player == null || ExoMediaPlayer.this.player.getMtkPlayerControl() == null) {
                    return;
                }
                int trackCount = ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(0);
                int trackCount2 = ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(1);
                int trackCount3 = ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(2);
                if (trackCount != 0 || trackCount2 != 0) {
                    ExoMediaPlayer.this.eventHandler.sendEmptyMessage(6001);
                }
                if (ExoMediaPlayer.this.player.getMtkPlayerControl().getSelectedTrack(0) >= 0 && (trackFormat = ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackFormat(0, ExoMediaPlayer.this.player.getMtkPlayerControl().getSelectedTrack(0))) != null) {
                    ExoMediaPlayer.this.defaultRotationDegrees = trackFormat.rotationDegrees;
                    ExoMediaPlayer.this.videoWidth = trackFormat.width;
                    ExoMediaPlayer.this.videoHeight = trackFormat.height;
                }
                Log.d("ExoMediaPlayer", "videoTrackCount=" + trackCount + ", audioTrackCount=" + trackCount2 + ", textTrackCount=" + trackCount3 + ", defaultRotationDegrees=" + ExoMediaPlayer.this.defaultRotationDegrees + ", videoWidth=" + ExoMediaPlayer.this.videoWidth + ", videoHeight=" + ExoMediaPlayer.this.videoHeight);
                if ((trackCount == 0 && trackCount2 != 0) || ExoMediaPlayer.this.player.getRenderersUnsupported()[0]) {
                    if (trackCount == 0) {
                        Log.d("ExoMediaPlayer", "send audio only message");
                        Message messageObtain2 = Message.obtain();
                        messageObtain2.what = 6002;
                        messageObtain2.arg1 = -5008;
                        messageObtain2.arg2 = 0;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain2);
                    }
                    Log.d("ExoMediaPlayer", "onInfo1 send MEDIA_INFO_VIDEO_RENDERING_START");
                    Message messageObtain3 = Message.obtain();
                    messageObtain3.what = 6002;
                    messageObtain3.arg1 = 3;
                    messageObtain3.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain3);
                    ExoMediaPlayer.this.renderingStart = true;
                } else if (trackCount2 == 0 && trackCount != 0) {
                    Log.d("ExoMediaPlayer", "send video only message");
                    Message messageObtain4 = Message.obtain();
                    messageObtain4.what = 6002;
                    messageObtain4.arg1 = -5009;
                    messageObtain4.arg2 = 0;
                    ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain4);
                }
                if (ExoMediaPlayer.this.player.getMtkPlayerControl().canSeekForward() || ExoMediaPlayer.this.player.getMtkPlayerControl().canSeekBackward()) {
                    return;
                }
                Message messageObtain5 = Message.obtain();
                messageObtain5.what = 6002;
                messageObtain5.arg1 = 801;
                messageObtain5.arg2 = 0;
                ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain5);
            }
        });
        this.player.setInfoListener(new DemoPlayer.InfoListener() { // from class: com.mediatek.ExoMediaPlayer.9
            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onAudioFormatEnabled(Format format, int i, long j) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onAvailableRangeChanged(int i, TimeRange timeRange) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onDecoderInitialized(String str, long j, long j2) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onDroppedFrames(int i, long j) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onLoadCompleted(int i, long j, int i2, int i3, Format format, long j2, long j3, long j4, long j5) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onLoadStarted(int i, long j, int i2, int i3, Format format, long j2, long j3) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onVideoFormatEnabled(Format format, int i, long j) {
            }

            @Override // com.google.android.exoplayer.demo.player.DemoPlayer.InfoListener
            public void onBandwidthSample(int i, long j, long j2) {
                ExoMediaPlayer.this.bitRate = j2;
            }
        });
        this.player.getMtkPlayerControl().setCaptionListener(new MtkPlayerControl.CaptionListener() { // from class: com.mediatek.ExoMediaPlayer.10
            @Override // com.google.android.exoplayer.util.MtkPlayerControl.CaptionListener
            public void onCues(List<Cue> list) {
                Log.d("ExoMediaPlayer", "onCues called");
                if (!ExoMediaPlayer.this.isTrickingExo()) {
                    ExoMediaPlayer.this.cues = list;
                    if (list != null && list.size() > 0) {
                        Log.d("ExoMediaPlayer", "cues != null; cues.size = " + list.size());
                        Iterator<Cue> it = list.iterator();
                        while (it.hasNext()) {
                            Log.d("ExoMediaPlayer", "cue.text=" + ((Object) it.next().text));
                        }
                    }
                    if (ExoMediaPlayer.this.eventHandler != null) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 6005;
                        messageObtain.obj = list;
                        ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
                        return;
                    }
                    return;
                }
                Log.v("ExoMediaPlayer", "Tricking...don't show subtitles");
            }
        });
        this.player.getMtkPlayerControl().addListener(new MtkPlayerControl.Listener() { // from class: com.mediatek.ExoMediaPlayer.11
            @Override // com.google.android.exoplayer.util.MtkPlayerControl.Listener
            public void onPrepared() {
            }

            @Override // com.google.android.exoplayer.util.MtkPlayerControl.Listener
            public void onSeekReadyInTrick() {
            }

            @Override // com.google.android.exoplayer.util.MtkPlayerControl.Listener
            public void onTrackStateChanged(int i, int i2, boolean z) {
            }

            @Override // com.google.android.exoplayer.util.MtkPlayerControl.Listener
            public void onTrickEnded(int i, int i2) {
                if (ExoMediaPlayer.this.player == null || ExoMediaPlayer.this.player.getMtkPlayerControl() == null) {
                    return;
                }
                Message messageObtain = Message.obtain();
                if (i2 == 0) {
                    messageObtain.what = 6002;
                    if (ExoMediaPlayer.this.player.getMtkPlayerControl().getTrackCount(0) > 0) {
                        messageObtain.arg1 = -5100;
                    } else {
                        messageObtain.arg1 = -5006;
                    }
                    messageObtain.arg2 = 0;
                } else {
                    messageObtain.what = 6003;
                }
                ExoMediaPlayer.this.eventHandler.sendMessage(messageObtain);
            }
        });
        this.player.setOutputPath(this.output);
        this.player.setMediaTunneling(this.mTunnelingMode, (AudioManager) this.context.getSystemService("audio"));
        this.player.setSeekMode(this.seekMode);
        this.player.prepare();
        DemoPlayer demoPlayer3 = this.player;
        if (demoPlayer3 != null) {
            demoPlayer3.setSurface(this.surface);
            this.player.setPlayWhenReady(false);
        }
    }

    @Override // android.media.MediaPlayer
    public void prepareAsync() throws IllegalStateException {
        Log.d("ExoMediaPlayer", "[Performance]prepareAsync");
        int i = this.state;
        if (i != 1 && i != 6) {
            throw new IllegalStateException("current state is " + this.state);
        }
        this.asyncPreparing = true;
        new Thread() { // from class: com.mediatek.ExoMediaPlayer.12
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Looper.prepare();
                ExoMediaPlayer.this.myLooper = Looper.myLooper();
                ExoMediaPlayer.this.prepareExo();
                synchronized (ExoMediaPlayer.this) {
                    ExoMediaPlayer.this.asyncPreparing = false;
                    ExoMediaPlayer.this.notifyAll();
                }
                ExoMediaPlayer.this.asyncPreparing = false;
                Looper.loop();
            }
        }.start();
        this.state = 2;
    }

    @Override // android.media.MediaPlayer
    public void stop() throws IllegalStateException {
        Log.d("ExoMediaPlayer", "stop() called");
        int i = this.state;
        if (i == 4 || i == 6 || i == 5 || i == 7) {
            releasePlayer();
            Looper looper = this.myLooper;
            if (looper != null) {
                looper.quitSafely();
                this.myLooper = null;
            }
            this.state = 6;
            return;
        }
        if (i == 9) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 6007;
            messageObtain.arg1 = -38;
            messageObtain.arg2 = 0;
            this.eventHandler.sendMessage(messageObtain);
            return;
        }
        throw new IllegalStateException("Current state is " + this.state);
    }

    @Override // com.mediatek.MtkMediaPlayer, android.media.MediaPlayer
    public void pause() throws IllegalStateException {
        Log.d("ExoMediaPlayer", "pause() called");
        int i = this.state;
        if (i != 4 && i != 5) {
            if (i == 6 || i == 9) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 6007;
                messageObtain.arg1 = -38;
                messageObtain.arg2 = 0;
                this.eventHandler.sendMessage(messageObtain);
                return;
            }
            throw new IllegalStateException("Current state is " + this.state);
        }
        if (1 == this.mDataSourceType) {
            Log.i("ExoMediaPlayer", "mStallingSupport " + this.mStallingSupport);
            if (!this.mStallingSupport && this.mSeekType == 0) {
                Log.i("ExoMediaPlayer", "pauseExo Exception");
                throw new IllegalStateException("pauseExo Exception");
            }
        }
        stayAwake(false);
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer != null && demoPlayer.getMtkPlayerControl() != null) {
            this.player.getMtkPlayerControl().pause();
        }
        this.state = 5;
    }

    @Override // android.media.MediaPlayer
    public void setWakeMode(Context context, int i) {
        boolean z;
        Log.i("ExoMediaPlayer", "setWakeMode(): Enter");
        if (this.mDataSourceType != 0) {
            Log.i("ExoMediaPlayer", "setWakeMode(): mode=" + i);
            PowerManager.WakeLock wakeLock = this.mWakeLock;
            if (wakeLock != null) {
                if (wakeLock.isHeld()) {
                    z = true;
                    this.mWakeLock.release();
                } else {
                    z = false;
                }
                this.mWakeLock = null;
            } else {
                z = false;
            }
            PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(i | 536870912, MtkMediaPlayer.class.getName());
            this.mWakeLock = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            if (z) {
                this.mWakeLock.acquire();
            }
        }
        Log.i("ExoMediaPlayer", "setWakeMode(): Leave");
    }

    @Override // android.media.MediaPlayer
    public void setScreenOnWhilePlaying(boolean z) {
        Log.i("ExoMediaPlayer", "setScreenOnWhilePlaying(): Enter");
        if (this.mDataSourceType != 0) {
            Log.i("ExoMediaPlayer", "setScreenOnWhilePlaying(): screenOn=" + z);
            if (this.mScreenOnWhilePlaying != z) {
                if (z && this.mSurfaceHolder == null) {
                    Log.w("ExoMediaPlayer", "setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder");
                }
                this.mScreenOnWhilePlaying = z;
                updateSurfaceScreenOn();
            }
        }
        Log.i("ExoMediaPlayer", "setScreenOnWhilePlaying(): Leave");
    }

    @Override // android.media.MediaPlayer
    public int getVideoWidth() {
        Log.d("ExoMediaPlayer", "getVideoWidth() called, videoWidth=" + this.videoWidth);
        return this.videoWidth;
    }

    @Override // android.media.MediaPlayer
    public int getVideoHeight() {
        Log.d("ExoMediaPlayer", "getVideoHeight() called, videoHeight=" + this.videoHeight);
        return this.videoHeight;
    }

    @Override // android.media.MediaPlayer
    public boolean isPlaying() {
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer != null) {
            return demoPlayer.getMtkPlayerControl().isPlaying() || (isTrickingExo() && this.player.getMtkPlayerControl().getCurrentSpeed() != 2);
        }
        return false;
    }

    @Override // android.media.MediaPlayer
    public synchronized void seekTo(int i) throws IllegalStateException {
        Log.d("ExoMediaPlayer", "seekTo() called, msec=" + i);
        if (this.state != 3 && this.state != 4 && this.state != 5 && this.state != 7) {
            throw new IllegalStateException("Current state is " + this.state);
        }
        if (this.player != null) {
            if (!this.isSeeking) {
                this.isSeeking = true;
                this.player.getMtkPlayerControl().seekTo(i);
            } else {
                this.pendingSeekPosition = i;
                Log.i("ExoMediaPlayer", "Already in seeking, update pendingSeekPosition: " + this.pendingSeekPosition);
            }
        }
    }

    @Override // android.media.MediaPlayer
    public int getCurrentPosition() {
        Log.d("ExoMediaPlayer", "getCurrentPositionExo called");
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer == null || demoPlayer.getMtkPlayerControl() == null) {
            return -1;
        }
        int currentPosition = this.player.getMtkPlayerControl().getCurrentPosition();
        if (!this.renderStartInfoSent) {
            return currentPosition;
        }
        showLogo(false);
        return currentPosition;
    }

    @Override // android.media.MediaPlayer
    public int getDuration() {
        Log.d("ExoMediaPlayer", "getDuration called");
        Log.d("ExoMediaPlayer", "getDurationExo called");
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer == null || demoPlayer.getMtkPlayerControl() == null) {
            return 0;
        }
        return this.player.getMtkPlayerControl().getDuration();
    }

    @Override // android.media.MediaPlayer
    public int getSelectedTrack(int i) throws IllegalStateException {
        int i2;
        Log.d("ExoMediaPlayer", "getSelectedTrack() called, trackType=" + i);
        if (this.state == 8) {
            throw new IllegalStateException("Current state is " + this.state);
        }
        int i3 = -1;
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer == null || demoPlayer.getMtkPlayerControl() == null || this.trackIndexMap[i] == null) {
            return -1;
        }
        int i4 = 0;
        if (i == 1) {
            i2 = 0;
        } else if (i == 2) {
            i2 = 1;
        } else {
            if (i != 3 && i != 4) {
                Log.e("ExoMediaPlayer", "Illegal track type!");
                return -1;
            }
            i2 = 2;
        }
        int selectedTrack = this.player.getMtkPlayerControl().getSelectedTrack(i2);
        while (true) {
            int[][] iArr = this.trackIndexMap;
            if (i4 >= iArr[i2].length) {
                break;
            }
            if (iArr[i2][i4] == selectedTrack) {
                i3 = i4;
            }
            i4++;
        }
        if (i2 == 1) {
            return i3 + getAllVideoTrackCount();
        }
        return i2 == 2 ? i3 + getAllVideoTrackCount() + getAllAudioTrackCount() : i3;
    }

    @Override // android.media.MediaPlayer
    public void selectTrack(int i) throws IllegalStateException {
        Log.d("ExoMediaPlayer", "selectTrack called. index=" + i);
        int i2 = this.state;
        if (i2 != 3 && i2 != 4 && i2 != 5 && i2 != 7) {
            throw new IllegalStateException("Current state is " + this.state);
        }
        if (i < 0) {
            Log.e("ExoMediaPlayer", "Illegal track index!");
            return;
        }
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer != null && demoPlayer.getMtkPlayerControl() != null) {
            for (int i3 = 0; i3 < 3; i3++) {
                int[] iArr = this.trackIndexMap[i3];
                if (iArr != null) {
                    for (int i4 = 0; i4 < iArr.length && iArr[i4] >= 0; i4++) {
                        if (i == 0) {
                            this.player.getMtkPlayerControl().setSelectedTrack(i3, iArr[i4]);
                            return;
                        }
                        i--;
                    }
                }
            }
        }
        Log.e("ExoMediaPlayer", "Illegal track index!");
    }

    @Override // android.media.MediaPlayer
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.onPreparedlistener = onPreparedListener;
    }

    @Override // android.media.MediaPlayer
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    @Override // android.media.MediaPlayer
    public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.onSeekCompleteListener = onSeekCompleteListener;
    }

    @Override // android.media.MediaPlayer
    public void setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.onVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    @Override // android.media.MediaPlayer
    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    @Override // com.mediatek.MtkMediaPlayer, android.media.MediaPlayer
    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.onInfoListener = onInfoListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTrickingExo() {
        DemoPlayer demoPlayer = this.player;
        if (demoPlayer == null || demoPlayer.getMtkPlayerControl() == null) {
            return false;
        }
        int currentSpeed = this.player.getMtkPlayerControl().getCurrentSpeed();
        return currentSpeed == 2 || (currentSpeed >= 4 && currentSpeed <= 23);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:30:0x007e  */
    public String getMimeHeader(Uri uri, Map<String, String> map) throws Throwable {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        strTrim = null;
        String strTrim = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
            try {
                try {
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setReadTimeout(3000);
                    if (this.mWebInterface != null) {
                        this.userAgent = this.mWebInterface.getUserAgent();
                        this.cookie = this.mWebInterface.getCookie(uri.toString());
                    }
                    if (!TextUtils.isEmpty(this.cookie)) {
                        httpURLConnection.addRequestProperty("Cookie", this.cookie);
                        httpURLConnection.addRequestProperty("User-Agent", this.userAgent);
                    }
                    httpURLConnection.connect();
                    String contentType = httpURLConnection.getContentType();
                    strTrim = TextUtils.isEmpty(contentType) ? null : contentType.split(";")[0].trim();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (IOException e) {
                    e = e;
                    Log.d("MyTest", e.toString());
                    if (httpURLConnection != null) {
                    }
                    return strTrim;
                }
            } catch (Throwable th) {
                th = th;
                httpURLConnection2 = httpURLConnection;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            httpURLConnection = null;
        } catch (Throwable th2) {
            th = th2;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
        return strTrim;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getContentType(Uri uri, String str) {
        if (uri == null) {
            return MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG;
        }
        if (!"application/dash+xml".equals(str) && !"application/vnd.marlin.drm.actiontoken+xml".equals(str)) {
            if (!"application/vnd.ms-sstr+xml".equals(str)) {
                if (!"application/vnd.apple.mpegurl".equals(str)) {
                    if ("application/vnd.ms-playready.initiator+xml".equals(str) || "application/xml".equals(str) || uri.toString().contains("webinife")) {
                        String str2 = null;
                        if (TextUtils.isEmpty(null)) {
                            return MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG;
                        }
                        if (str2.contains(".mpd")) {
                            this.contentUri = Uri.parse(null);
                        } else {
                            if (!str2.contains(".ism") && ((!str2.contains(".ssm") || !str2.contains("Manifest")) && (!str2.contains(".smil") || !str2.contains("Manifest")))) {
                                return -1;
                            }
                            this.contentUri = Uri.parse(null);
                        }
                    } else if (!uri.toString().contains(".m3u8")) {
                        if (!uri.toString().contains(".mpd")) {
                            if (!uri.toString().contains(".ism") && (!uri.toString().contains(".ssm") || !uri.toString().contains("Manifest"))) {
                                return MtkTvScanCeBase.OPERATOR_GUANGZHOU_SHENGWANG;
                            }
                        }
                    }
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }

    private DemoPlayer.RendererBuilder getRendererBuilder() {
        if (TextUtils.isEmpty(this.userAgent)) {
            this.userAgent = Util.getUserAgent(this.context, "ExoPlayer");
        }
        int i = this.contentType;
        if (i != 0) {
            if (i == 1) {
                return new SmoothStreamingRendererBuilder(this.context, this.userAgent, this.contentUri.toString(), new SmoothStreamingTestMediaDrmCallback(this.laUrl), this.drmHeader, this.customData);
            }
            if (i == 2) {
                return new HlsRendererBuilder(this.context, this.userAgent, this.contentUri.toString());
            }
            if (i == 3) {
                return new ExtractorRendererBuilder(this.context, this.userAgent, this.contentUri);
            }
            if (i == 1001) {
                return new DefaultRendererBuilder(this.context, this.contentUri);
            }
            Log.d("ExoMediaPlayer", "If crash, need APP add try catch");
            throw new IllegalStateException("Unsupported type: " + this.contentType);
        }
        switch (this.drmType) {
            case 0:
                return new DashRendererBuilder(this.context, this.userAgent, this.contentUri.toString(), null, null);
            case 1:
                return new DashRendererBuilder(this.context, this.userAgent, this.contentUri.toString(), new WidevineTestMediaDrmCallback("", ""), null);
            case 2:
                Context context = this.context;
                String str = this.userAgent;
                String string = this.contentUri.toString();
                PlayreadyTestMediaDrmCallback playreadyTestMediaDrmCallback = new PlayreadyTestMediaDrmCallback();
                String str2 = this.drmHeader;
                return new DashRendererBuilder(context, str, string, playreadyTestMediaDrmCallback, str2 == null ? null : StringToByteArray(str2));
            case 3:
                return new DashRendererBuilder(this.context, this.userAgent, this.contentUri.toString(), new MarlinNautilusTestMediaDrmCallback(), this.drmHeader.getBytes());
            case 4:
                return new DashRendererBuilder(this.context, this.userAgent, this.contentUri.toString(), new MarlinNautilusTestMediaDrmCallback(), this.contentUri.toString().getBytes());
            case 5:
                return new DashRendererBuilder(this.context, this.userAgent, this.contentUri.toString(), new MarlinIntertrustTestMediaDrmCallback(), this.drmHeader.getBytes());
            case 6:
                return new DashRendererBuilder(this.context, this.userAgent, this.contentUri.toString(), new MarlinIntertrustTestMediaDrmCallback(), this.contentUri.toString().getBytes());
            default:
                Log.d("ExoMediaPlayer", "If crash, need APP add try catch");
                throw new IllegalStateException("Unsupported drm type: " + this.drmType);
        }
    }

    private static byte[] StringToByteArray(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[str.length() * 2];
        for (int i = 0; i < str.length(); i++) {
            int i2 = i * 2;
            bArr[i2] = bytes[i];
            bArr[i2 + 1] = 0;
        }
        return bArr;
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00da  */
    /* JADX WARN: Code duplicated, block: B:58:0x00dd  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void showLogo(boolean z) {
        DemoPlayer demoPlayer;
        boolean z2;
        int i;
        int i2;
        if (this.context == null || (demoPlayer = this.player) == null) {
            return;
        }
        String str = null;
        byte b = 0;
        MediaFormat trackFormat = this.player.getTrackFormat(0, demoPlayer.getSelectedTrack(0));
        int i3 = 1;
        if (trackFormat == null || !"video/dolby-vision".equals(trackFormat.mimeType)) {
            z2 = false;
            i = -1;
        } else {
            str = "mtk.intent.action.dolby.version";
            i = 3;
            z2 = true;
        }
        String parameters = ((AudioManager) this.context.getSystemService("audio")).getParameters("audioindicator=");
        if (TextUtils.isEmpty(parameters)) {
            i3 = i;
        } else if (parameters.toLowerCase().contains("dolby")) {
            if (parameters.toLowerCase().contains("atmos")) {
                str = z2 ? "mtk.intent.action.dolby.vision_atmos" : "mtk.intent.action.dolby.atmos";
                i3 = 2;
            } else {
                str = z2 ? "mtk.intent.action.dolby.vision_audio" : "mtk.intent.action.dolby.audio";
            }
        } else if (parameters.contains("DTS")) {
            String strSubstring = parameters.substring(parameters.indexOf("DTS"));
            switch (strSubstring.hashCode()) {
                case -664708621:
                    b = !strSubstring.equals("DTS Express") ? (byte) -1 : (byte) 2;
                    break;
                case 68035:
                    if (!strSubstring.equals("DTS")) {
                        b = -1;
                    }
                    break;
                case 65383521:
                    b = !strSubstring.equals("DTS:X") ? (byte) -1 : (byte) 4;
                    break;
                case 1873858866:
                    b = !strSubstring.equals("DTS-HD Master Audio") ? (byte) -1 : (byte) 3;
                    break;
                case 2026876230:
                    b = !strSubstring.equals("DTS-HD") ? (byte) -1 : (byte) 1;
                    break;
                default:
                    b = -1;
                    break;
            }
            if (b == 0) {
                str = "mtk.intent.action.mm.logo.dts";
                i3 = 11;
            } else if (b == 1) {
                i3 = 12;
                str = "mtk.intent.action.mm.logo.dts_hd";
            } else if (b == 2) {
                i3 = 13;
                str = "mtk.intent.action.mm.logo.dts_express";
            } else if (b == 3) {
                i3 = 14;
                str = "mtk.intent.action.mm.logo.dts_hd_master";
            } else if (b != 4) {
                str = "mtk.intent.action.mm.logo.dts";
                i3 = 11;
            } else {
                i3 = 15;
                str = "mtk.intent.action.mm.logo.dts_x";
            }
        } else {
            i3 = i;
        }
        if (str != null && (z || (i2 = this.audioIndicator) <= 0 || i3 != i2)) {
            this.context.sendBroadcast(new Intent(str));
            Log.d("ExoMediaPlayer", "showLogo: force=" + z + ", intent=" + str + ", indicator=" + i3 + ", audioIndicator=" + this.audioIndicator + ", audioIndicatorString=" + parameters);
        }
        this.audioIndicator = i3;
    }

    private class ExoHanlderCallback implements Handler.Callback {
        private ExoHanlderCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 6001:
                    if (ExoMediaPlayer.this.onPreparedlistener == null) {
                        return true;
                    }
                    Log.d("ExoMediaPlayer", "onPrepared called");
                    ExoMediaPlayer.this.state = 3;
                    ExoMediaPlayer.this.onPreparedlistener.onPrepared(ExoMediaPlayer.this);
                    ExoMediaPlayer.this.checkLastMemory();
                    return true;
                case 6002:
                    if (ExoMediaPlayer.this.onInfoListener == null) {
                        return true;
                    }
                    Log.d("ExoMediaPlayer", "onInfo called: arg1=" + message.arg1);
                    ExoMediaPlayer.this.onInfoListener.onInfo(ExoMediaPlayer.this, message.arg1, message.arg2);
                    return true;
                case 6003:
                    if (ExoMediaPlayer.this.context != null) {
                        ExoMediaPlayer.this.context.sendBroadcast(new Intent("mtk.intent.action.dolby.exit"));
                    }
                    if (ExoMediaPlayer.this.onCompletionListener == null) {
                        return true;
                    }
                    Log.d("ExoMediaPlayer", "onCompletion called");
                    ExoMediaPlayer.this.state = 7;
                    ExoMediaPlayer.this.onCompletionListener.onCompletion(ExoMediaPlayer.this);
                    return true;
                case 6004:
                    if (ExoMediaPlayer.this.onVideoSizeChangedListener == null) {
                        return true;
                    }
                    Log.d("ExoMediaPlayer", "onVideoSizeChanged called, width:" + ExoMediaPlayer.this.videoWidth + ", height:" + ExoMediaPlayer.this.videoHeight);
                    MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = ExoMediaPlayer.this.onVideoSizeChangedListener;
                    ExoMediaPlayer exoMediaPlayer = ExoMediaPlayer.this;
                    onVideoSizeChangedListener.onVideoSizeChanged(exoMediaPlayer, exoMediaPlayer.videoWidth, ExoMediaPlayer.this.videoHeight);
                    return true;
                case 6005:
                    if (ExoMediaPlayer.this.onInfoListener == null) {
                        return true;
                    }
                    ExoMediaPlayer.this.onInfoListener.onInfo(ExoMediaPlayer.this, -5015, 0);
                    return true;
                case 6006:
                    if (ExoMediaPlayer.this.pendingSeekPosition != -1) {
                        Log.i("ExoMediaPlayer", "Has pendingSeekPosition[" + ExoMediaPlayer.this.pendingSeekPosition + "], call seekTo internally!");
                        ExoMediaPlayer exoMediaPlayer2 = ExoMediaPlayer.this;
                        exoMediaPlayer2.seekTo(exoMediaPlayer2.pendingSeekPosition);
                        ExoMediaPlayer.this.pendingSeekPosition = -1;
                    }
                    if (ExoMediaPlayer.this.onSeekCompleteListener == null) {
                        return true;
                    }
                    Log.d("ExoMediaPlayer", "onSeekComplete called");
                    ExoMediaPlayer.this.onSeekCompleteListener.onSeekComplete(ExoMediaPlayer.this);
                    return true;
                case 6007:
                    if (ExoMediaPlayer.this.onErrorListener == null) {
                        return true;
                    }
                    Log.d("ExoMediaPlayer", "onError called: arg1=" + message.arg1);
                    ExoMediaPlayer.this.state = 9;
                    if (ExoMediaPlayer.this.onErrorListener.onError(ExoMediaPlayer.this, message.arg1, message.arg2) || ExoMediaPlayer.this.onCompletionListener == null) {
                        return true;
                    }
                    Log.d("ExoMediaPlayer", "onCompletion called");
                    ExoMediaPlayer.this.state = 7;
                    ExoMediaPlayer.this.onCompletionListener.onCompletion(ExoMediaPlayer.this);
                    return true;
                default:
                    return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLastMemory() {
        LastMemoryFilePosition lastMemoryFilePosition = this.mLastMemoryInfo;
        if (lastMemoryFilePosition != null) {
            long j = lastMemoryFilePosition.ui8_clip_id;
            int i = lastMemoryFilePosition.ui2_vid_idx;
            int i2 = lastMemoryFilePosition.ui2_aud_idx;
            int i3 = lastMemoryFilePosition.ui2_sub_idx;
            long j2 = lastMemoryFilePosition.ui8_frame_position;
            int trackCount = this.player.getTrackCount(1);
            int trackCount2 = this.player.getTrackCount(2);
            int trackCount3 = this.player.getTrackCount(0);
            int selectedTrack = this.player.getSelectedTrack(1);
            int selectedTrack2 = this.player.getSelectedTrack(2);
            int selectedTrack3 = this.player.getSelectedTrack(0);
            Log.d("ExoMediaPlayer", "checkLastMemory() ui8_clip_id = " + j + " -ui8_frame_position = " + j2 + " - ui2_aud_idx = " + i2 + " - ui2_sub_idx = " + i3 + " - ui2_vid_idx = " + i);
            if (i2 < trackCount && i2 != selectedTrack && i2 != -1) {
                this.player.setSelectedTrack(1, i2);
            }
            if (i3 < trackCount2 && i3 != selectedTrack2 && i3 != -1) {
                this.player.setSelectedTrack(2, i3);
            }
            if (i < trackCount3 && i != selectedTrack3 && i != -1) {
                this.player.setSelectedTrack(0, i);
            }
            if (j2 > 0) {
                this.player.seekTo(j2);
            }
        }
        this.mLastMemoryInfo = null;
    }
}
