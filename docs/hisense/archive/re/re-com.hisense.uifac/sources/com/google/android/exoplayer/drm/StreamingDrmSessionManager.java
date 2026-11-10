package com.google.android.exoplayer.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.DeniedByServerException;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.google.android.exoplayer.drm.ExoMediaCrypto;
import com.google.android.exoplayer.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer.util.Util;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public class StreamingDrmSessionManager<T extends ExoMediaCrypto> implements DrmSessionManager<T> {
    final MediaDrmCallback callback;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private Exception lastException;
    private T mediaCrypto;
    private final ExoMediaDrm<T> mediaDrm;
    final StreamingDrmSessionManager<T>.MediaDrmHandler mediaDrmHandler;
    private int openCount;
    private final HashMap<String, String> optionalKeyRequestParameters;
    private Handler postRequestHandler;
    final StreamingDrmSessionManager<T>.PostResponseHandler postResponseHandler;
    private boolean provisioningInProgress;
    private HandlerThread requestHandlerThread;
    private DrmInitData.SchemeInitData schemeInitData;
    private byte[] sessionId;
    private int state;
    final UUID uuid;
    public static final UUID WIDEVINE_UUID = new UUID(-1301668207276963122L, -6645017420763422227L);
    public static final UUID PLAYREADY_UUID = new UUID(-7348484286925749626L, -6083546864340672619L);
    public static final UUID MARLIN_UUID = new UUID(6801168765294362723L, -8541191043631480108L);
    public static final UUID VERYMATRIX_UUID = new UUID(81985529216486895L, 81985529216486895L);

    public interface EventListener {
        void onDrmKeysLoaded();

        void onDrmSessionManagerError(Exception exc);
    }

    private static FrameworkMediaDrm createFrameworkDrm(UUID uuid) throws UnsupportedDrmException {
        try {
            return new FrameworkMediaDrm(uuid);
        } catch (UnsupportedSchemeException e) {
            throw new UnsupportedDrmException(1, e);
        } catch (Exception e2) {
            throw new UnsupportedDrmException(2, e2);
        }
    }

    public static StreamingDrmSessionManager<FrameworkMediaCrypto> newWidevineInstance(Looper looper, MediaDrmCallback mediaDrmCallback, HashMap<String, String> map, Handler handler, EventListener eventListener) throws UnsupportedDrmException {
        return newFrameworkInstance(WIDEVINE_UUID, looper, mediaDrmCallback, map, handler, eventListener);
    }

    public static StreamingDrmSessionManager<FrameworkMediaCrypto> newPlayReadyInstance(Looper looper, MediaDrmCallback mediaDrmCallback, String str, Handler handler, EventListener eventListener) throws UnsupportedDrmException {
        HashMap map;
        if (TextUtils.isEmpty(str)) {
            map = null;
        } else {
            map = new HashMap();
            map.put("PRCustomData", str);
        }
        return newFrameworkInstance(PLAYREADY_UUID, looper, mediaDrmCallback, map, handler, eventListener);
    }

    public static StreamingDrmSessionManager<FrameworkMediaCrypto> newFrameworkInstance(UUID uuid, Looper looper, MediaDrmCallback mediaDrmCallback, HashMap<String, String> map, Handler handler, EventListener eventListener) throws UnsupportedDrmException {
        return newInstance(uuid, looper, mediaDrmCallback, map, handler, eventListener, createFrameworkDrm(uuid));
    }

    public static <T extends ExoMediaCrypto> StreamingDrmSessionManager<T> newInstance(UUID uuid, Looper looper, MediaDrmCallback mediaDrmCallback, HashMap<String, String> map, Handler handler, EventListener eventListener, ExoMediaDrm<T> exoMediaDrm) throws UnsupportedDrmException {
        return new StreamingDrmSessionManager<>(uuid, looper, mediaDrmCallback, map, handler, eventListener, exoMediaDrm);
    }

    private StreamingDrmSessionManager(UUID uuid, Looper looper, MediaDrmCallback mediaDrmCallback, HashMap<String, String> map, Handler handler, EventListener eventListener, ExoMediaDrm<T> exoMediaDrm) throws UnsupportedDrmException {
        this.uuid = uuid;
        this.callback = mediaDrmCallback;
        this.optionalKeyRequestParameters = map;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.mediaDrm = exoMediaDrm;
        exoMediaDrm.setOnEventListener(new MediaDrmEventListener());
        this.mediaDrmHandler = new MediaDrmHandler(looper);
        this.postResponseHandler = new PostResponseHandler(looper);
        this.state = 1;
    }

    @Override // com.google.android.exoplayer.drm.DrmSessionManager
    public final int getState() {
        return this.state;
    }

    @Override // com.google.android.exoplayer.drm.DrmSessionManager
    public final T getMediaCrypto() {
        int i = this.state;
        if (i != 3 && i != 4) {
            throw new IllegalStateException();
        }
        return this.mediaCrypto;
    }

    @Override // com.google.android.exoplayer.drm.DrmSessionManager
    public boolean requiresSecureDecoderComponent(String str) {
        int i = this.state;
        if (i != 3 && i != 4) {
            throw new IllegalStateException();
        }
        return this.mediaCrypto.requiresSecureDecoderComponent(str);
    }

    @Override // com.google.android.exoplayer.drm.DrmSessionManager
    public final Exception getError() {
        if (this.state == 0) {
            return this.lastException;
        }
        return null;
    }

    public final String getPropertyString(String str) {
        return this.mediaDrm.getPropertyString(str);
    }

    @Override // com.google.android.exoplayer.drm.DrmSessionManager
    public void open(DrmInitData drmInitData) {
        byte[] schemeSpecificData;
        int i = this.openCount + 1;
        this.openCount = i;
        if (i != 1) {
            return;
        }
        if (this.postRequestHandler == null) {
            HandlerThread handlerThread = new HandlerThread("DrmRequestHandler");
            this.requestHandlerThread = handlerThread;
            handlerThread.start();
            this.postRequestHandler = new PostRequestHandler(this.requestHandlerThread.getLooper());
        }
        if (this.schemeInitData == null) {
            DrmInitData.SchemeInitData schemeInitData = drmInitData.get(this.uuid);
            this.schemeInitData = schemeInitData;
            if (schemeInitData == null) {
                onError(new IllegalStateException("Media does not support uuid: " + this.uuid));
                return;
            }
            if (Util.SDK_INT < 21 && (schemeSpecificData = PsshAtomUtil.parseSchemeSpecificData(schemeInitData.data, WIDEVINE_UUID)) != null) {
                this.schemeInitData = new DrmInitData.SchemeInitData(this.schemeInitData.mimeType, schemeSpecificData);
            }
        }
        this.state = 2;
        openInternal(true);
    }

    @Override // com.google.android.exoplayer.drm.DrmSessionManager
    public void close() {
        int i = this.openCount - 1;
        this.openCount = i;
        if (i != 0) {
            return;
        }
        this.state = 1;
        this.provisioningInProgress = false;
        this.mediaDrmHandler.removeCallbacksAndMessages(null);
        this.postResponseHandler.removeCallbacksAndMessages(null);
        this.postRequestHandler.removeCallbacksAndMessages(null);
        this.postRequestHandler = null;
        this.requestHandlerThread.quit();
        this.requestHandlerThread = null;
        this.schemeInitData = null;
        this.mediaCrypto = null;
        this.lastException = null;
        byte[] bArr = this.sessionId;
        if (bArr != null) {
            this.mediaDrm.closeSession(bArr);
            this.sessionId = null;
        }
    }

    private void openInternal(boolean z) {
        try {
            byte[] bArrOpenSession = this.mediaDrm.openSession();
            this.sessionId = bArrOpenSession;
            this.mediaCrypto = (T) this.mediaDrm.createMediaCrypto(this.uuid, bArrOpenSession);
            this.state = 3;
            postKeyRequest();
        } catch (NotProvisionedException e) {
            if (z) {
                postProvisionRequest();
            } else {
                onError(e);
            }
        } catch (Exception e2) {
            onError(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postProvisionRequest() {
        if (this.provisioningInProgress) {
            return;
        }
        this.provisioningInProgress = true;
        this.postRequestHandler.obtainMessage(0, this.mediaDrm.getProvisionRequest()).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProvisionResponse(Object obj) {
        this.provisioningInProgress = false;
        int i = this.state;
        if (i == 2 || i == 3 || i == 4) {
            if (obj instanceof Exception) {
                onError((Exception) obj);
                return;
            }
            try {
                this.mediaDrm.provideProvisionResponse((byte[]) obj);
                if (this.state == 2) {
                    openInternal(false);
                } else {
                    postKeyRequest();
                }
            } catch (DeniedByServerException e) {
                onError(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postKeyRequest() {
        try {
            ExoMediaDrm.KeyRequest keyRequest = this.mediaDrm.getKeyRequest(this.sessionId, this.schemeInitData.data, this.schemeInitData.mimeType, 1, this.optionalKeyRequestParameters);
            if (this.uuid == PLAYREADY_UUID && keyStored(keyRequest)) {
                onKeyResponse(keyRequest.getDefaultUrl().getBytes());
            } else {
                this.postRequestHandler.obtainMessage(1, keyRequest).sendToTarget();
            }
        } catch (NotProvisionedException e) {
            onKeysError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onKeyResponse(Object obj) {
        int i = this.state;
        if (i == 3 || i == 4) {
            if (obj instanceof Exception) {
                onKeysError((Exception) obj);
                return;
            }
            try {
                byte[] bArrProvideKeyResponse = this.mediaDrm.provideKeyResponse(this.sessionId, (byte[]) obj);
                if (this.uuid == PLAYREADY_UUID && bArrProvideKeyResponse != null && isKidStart((byte[]) obj)) {
                    this.mediaDrm.restoreKeys(this.sessionId, bArrProvideKeyResponse);
                }
                this.state = 4;
                if (this.eventHandler == null || this.eventListener == null) {
                    return;
                }
                this.eventHandler.post(new Runnable() { // from class: com.google.android.exoplayer.drm.StreamingDrmSessionManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        StreamingDrmSessionManager.this.eventListener.onDrmKeysLoaded();
                    }
                });
            } catch (Exception e) {
                onKeysError(e);
            }
        }
    }

    private void onKeysError(Exception exc) {
        if (exc instanceof NotProvisionedException) {
            postProvisionRequest();
        } else {
            onError(exc);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(final Exception exc) {
        this.lastException = exc;
        Handler handler = this.eventHandler;
        if (handler != null && this.eventListener != null) {
            handler.post(new Runnable() { // from class: com.google.android.exoplayer.drm.StreamingDrmSessionManager.2
                @Override // java.lang.Runnable
                public void run() {
                    StreamingDrmSessionManager.this.eventListener.onDrmSessionManagerError(exc);
                }
            });
        }
        if (this.state != 4) {
            this.state = 0;
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class MediaDrmHandler extends Handler {
        public MediaDrmHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (StreamingDrmSessionManager.this.openCount != 0) {
                if (StreamingDrmSessionManager.this.state == 3 || StreamingDrmSessionManager.this.state == 4) {
                    int i = message.what;
                    if (i == 1) {
                        StreamingDrmSessionManager.this.state = 3;
                        StreamingDrmSessionManager.this.postProvisionRequest();
                    } else if (i == 2) {
                        StreamingDrmSessionManager.this.postKeyRequest();
                    } else {
                        if (i != 3) {
                            return;
                        }
                        StreamingDrmSessionManager.this.state = 3;
                        StreamingDrmSessionManager.this.onError(new KeysExpiredException());
                    }
                }
            }
        }
    }

    private class MediaDrmEventListener implements ExoMediaDrm.OnEventListener<T> {
        private MediaDrmEventListener() {
        }

        @Override // com.google.android.exoplayer.drm.ExoMediaDrm.OnEventListener
        public void onEvent(ExoMediaDrm<? extends T> exoMediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
            StreamingDrmSessionManager.this.mediaDrmHandler.sendEmptyMessage(i);
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class PostResponseHandler extends Handler {
        public PostResponseHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                StreamingDrmSessionManager.this.onProvisionResponse(message.obj);
            } else {
                if (i != 1) {
                    return;
                }
                StreamingDrmSessionManager.this.onKeyResponse(message.obj);
            }
        }
    }

    @SuppressLint({"HandlerLeak"})
    private class PostRequestHandler extends Handler {
        public PostRequestHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == 0) {
                    e = StreamingDrmSessionManager.this.callback.executeProvisionRequest(StreamingDrmSessionManager.this.uuid, (ExoMediaDrm.ProvisionRequest) message.obj);
                } else if (i == 1) {
                    e = StreamingDrmSessionManager.this.callback.executeKeyRequest(StreamingDrmSessionManager.this.uuid, (ExoMediaDrm.KeyRequest) message.obj);
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e = e;
            }
            StreamingDrmSessionManager.this.postResponseHandler.obtainMessage(message.what, e).sendToTarget();
        }
    }

    public static StreamingDrmSessionManager<FrameworkMediaCrypto> newMarlinInstance(Looper looper, MediaDrmCallback mediaDrmCallback, HashMap<String, String> map, Handler handler, EventListener eventListener) throws UnsupportedDrmException {
        return newFrameworkInstance(MARLIN_UUID, looper, mediaDrmCallback, map, handler, eventListener);
    }

    private boolean keyStored(ExoMediaDrm.KeyRequest keyRequest) {
        return keyRequest.getDefaultUrl().startsWith("kid");
    }

    private boolean isKidStart(byte[] bArr) {
        return new String(bArr).startsWith("kid");
    }
}
