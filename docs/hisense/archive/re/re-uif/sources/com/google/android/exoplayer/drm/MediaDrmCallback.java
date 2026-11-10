package com.google.android.exoplayer.drm;

import android.annotation.TargetApi;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public interface MediaDrmCallback {
    byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws Exception;

    byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws Exception;
}
