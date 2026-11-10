package com.google.android.exoplayer.demo;

import android.annotation.TargetApi;
import com.google.android.exoplayer.drm.ExoMediaDrm;
import com.google.android.exoplayer.drm.MediaDrmCallback;
import java.io.IOException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public class MarlinNautilusTestMediaDrmCallback implements MediaDrmCallback {
    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws IOException {
        return null;
    }

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws IOException {
        return "Marlin Nautilus BB".getBytes();
    }
}
