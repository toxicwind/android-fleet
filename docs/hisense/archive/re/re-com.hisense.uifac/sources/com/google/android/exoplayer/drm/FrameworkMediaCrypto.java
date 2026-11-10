package com.google.android.exoplayer.drm;

import android.annotation.TargetApi;
import android.media.MediaCrypto;
import com.google.android.exoplayer.util.Assertions;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public final class FrameworkMediaCrypto implements ExoMediaCrypto {
    private final MediaCrypto mediaCrypto;

    FrameworkMediaCrypto(MediaCrypto mediaCrypto) {
        Assertions.checkNotNull(mediaCrypto);
        this.mediaCrypto = mediaCrypto;
    }

    public MediaCrypto getWrappedMediaCrypto() {
        return this.mediaCrypto;
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaCrypto
    public boolean requiresSecureDecoderComponent(String str) {
        return this.mediaCrypto.requiresSecureDecoderComponent(str);
    }
}
