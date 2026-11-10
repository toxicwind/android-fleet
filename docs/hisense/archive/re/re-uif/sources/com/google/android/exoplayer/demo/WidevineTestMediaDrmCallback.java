package com.google.android.exoplayer.demo;

import android.annotation.TargetApi;
import android.text.TextUtils;
import com.google.android.exoplayer.drm.ExoMediaDrm;
import com.google.android.exoplayer.drm.MediaDrmCallback;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public class WidevineTestMediaDrmCallback implements MediaDrmCallback {
    private static final Map<String, String> REQUEST_PROPERTIES = Collections.singletonMap("Content-Type", "application/octet-stream");
    private final String defaultUri;

    public WidevineTestMediaDrmCallback(String str, String str2) {
        if (str.startsWith("http")) {
            this.defaultUri = str + str2;
            return;
        }
        this.defaultUri = "https://proxy.uat.widevine.com/proxy" + ("?video_id=" + str + "&provider=" + str2);
    }

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws IOException {
        return Util.executePost(provisionRequest.getDefaultUrl() + "&signedRequest=" + new String(provisionRequest.getData()), null, null);
    }

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws IOException {
        String defaultUrl = keyRequest.getDefaultUrl();
        if (TextUtils.isEmpty(defaultUrl)) {
            defaultUrl = this.defaultUri;
        }
        return Util.executePost(defaultUrl, keyRequest.getData(), REQUEST_PROPERTIES);
    }
}
