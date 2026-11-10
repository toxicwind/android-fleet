package com.google.android.exoplayer.drm;

import android.annotation.TargetApi;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.media.ResourceBusyException;
import android.media.UnsupportedSchemeException;
import com.google.android.exoplayer.util.Assertions;
import java.util.HashMap;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public final class FrameworkMediaDrm implements ExoMediaDrm<FrameworkMediaCrypto> {
    private final MediaDrm mediaDrm;

    public FrameworkMediaDrm(UUID uuid) throws UnsupportedSchemeException {
        Assertions.checkNotNull(uuid);
        this.mediaDrm = new MediaDrm(uuid);
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public void setOnEventListener(final ExoMediaDrm.OnEventListener<? super FrameworkMediaCrypto> onEventListener) {
        this.mediaDrm.setOnEventListener(onEventListener == null ? null : new MediaDrm.OnEventListener() { // from class: com.google.android.exoplayer.drm.FrameworkMediaDrm.1
            @Override // android.media.MediaDrm.OnEventListener
            public void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
                onEventListener.onEvent(FrameworkMediaDrm.this, bArr, i, i2, bArr2);
            }
        });
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public byte[] openSession() throws ResourceBusyException, NotProvisionedException {
        return this.mediaDrm.openSession();
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public void closeSession(byte[] bArr) {
        this.mediaDrm.closeSession(bArr);
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] bArr, byte[] bArr2, String str, int i, HashMap<String, String> map) throws NotProvisionedException {
        final MediaDrm.KeyRequest keyRequest = this.mediaDrm.getKeyRequest(bArr, bArr2, str, i, map);
        return new ExoMediaDrm.KeyRequest(this) { // from class: com.google.android.exoplayer.drm.FrameworkMediaDrm.2
            @Override // com.google.android.exoplayer.drm.ExoMediaDrm.KeyRequest
            public byte[] getData() {
                return keyRequest.getData();
            }

            @Override // com.google.android.exoplayer.drm.ExoMediaDrm.KeyRequest
            public String getDefaultUrl() {
                return keyRequest.getDefaultUrl();
            }
        };
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) throws DeniedByServerException, NotProvisionedException {
        return this.mediaDrm.provideKeyResponse(bArr, bArr2);
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        final MediaDrm.ProvisionRequest provisionRequest = this.mediaDrm.getProvisionRequest();
        return new ExoMediaDrm.ProvisionRequest(this) { // from class: com.google.android.exoplayer.drm.FrameworkMediaDrm.3
            @Override // com.google.android.exoplayer.drm.ExoMediaDrm.ProvisionRequest
            public byte[] getData() {
                return provisionRequest.getData();
            }

            @Override // com.google.android.exoplayer.drm.ExoMediaDrm.ProvisionRequest
            public String getDefaultUrl() {
                return provisionRequest.getDefaultUrl();
            }
        };
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public void provideProvisionResponse(byte[] bArr) throws DeniedByServerException {
        this.mediaDrm.provideProvisionResponse(bArr);
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        this.mediaDrm.restoreKeys(bArr, bArr2);
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public String getPropertyString(String str) {
        return this.mediaDrm.getPropertyString(str);
    }

    @Override // com.google.android.exoplayer.drm.ExoMediaDrm
    public FrameworkMediaCrypto createMediaCrypto(UUID uuid, byte[] bArr) throws MediaCryptoException {
        return new FrameworkMediaCrypto(new MediaCrypto(uuid, bArr));
    }
}
