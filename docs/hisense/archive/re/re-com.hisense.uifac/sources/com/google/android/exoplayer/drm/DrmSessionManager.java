package com.google.android.exoplayer.drm;

import android.annotation.TargetApi;
import com.google.android.exoplayer.drm.ExoMediaCrypto;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public interface DrmSessionManager<T extends ExoMediaCrypto> {
    void close();

    Exception getError();

    T getMediaCrypto();

    int getState();

    void open(DrmInitData drmInitData);

    boolean requiresSecureDecoderComponent(String str);
}
