package com.google.android.exoplayer.demo;

import android.annotation.TargetApi;
import android.text.TextUtils;
import com.google.android.exoplayer.drm.ExoMediaDrm;
import com.google.android.exoplayer.drm.MediaDrmCallback;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public class PlayreadyTestMediaDrmCallback implements MediaDrmCallback {
    private static final Map<String, String> KEY_REQUEST_PROPERTIES;
    private String licenseURL;

    static {
        HashMap map = new HashMap();
        map.put("Content-Type", "text/xml");
        map.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        KEY_REQUEST_PROPERTIES = map;
    }

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws IOException {
        return Util.executePost(provisionRequest.getDefaultUrl() + "&signedRequest=" + new String(provisionRequest.getData()), null, null);
    }

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws IOException {
        String defaultUrl;
        if (!TextUtils.isEmpty(this.licenseURL)) {
            defaultUrl = this.licenseURL;
        } else {
            defaultUrl = keyRequest.getDefaultUrl();
            if (TextUtils.isEmpty(defaultUrl)) {
                defaultUrl = "http://playready.directtaps.net/pr/svc/rightsmanager.asmx";
            }
        }
        return Util.executePost(defaultUrl, keyRequest.getData(), KEY_REQUEST_PROPERTIES);
    }
}
