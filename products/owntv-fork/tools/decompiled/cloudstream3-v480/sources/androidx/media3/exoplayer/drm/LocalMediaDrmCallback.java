package androidx.media3.exoplayer.drm;

import com.google.common.base.Preconditions;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public final class LocalMediaDrmCallback implements MediaDrmCallback {
    private final MediaDrmCallback.Response keyResponse;

    public LocalMediaDrmCallback(byte[] bArr) {
        this.keyResponse = new MediaDrmCallback.Response((byte[]) Preconditions.checkNotNull(bArr));
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public MediaDrmCallback.Response executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public MediaDrmCallback.Response executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) {
        return this.keyResponse;
    }
}
