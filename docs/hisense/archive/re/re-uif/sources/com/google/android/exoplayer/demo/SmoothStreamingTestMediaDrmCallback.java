package com.google.android.exoplayer.demo;

import android.annotation.TargetApi;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.drm.ExoMediaDrm;
import com.google.android.exoplayer.drm.MediaDrmCallback;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public class SmoothStreamingTestMediaDrmCallback implements MediaDrmCallback {
    private static final Map<String, String> KEY_REQUEST_PROPERTIES;
    private static final Map<String, String> PROVISIONING_REQUEST_PROPERTIES = Collections.singletonMap("Content-Type", "application/octet-stream");
    private String laUrl;

    static {
        HashMap map = new HashMap();
        map.put("Content-Type", "text/xml");
        map.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        KEY_REQUEST_PROPERTIES = map;
    }

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws IOException {
        String defaultUrl = provisionRequest.getDefaultUrl();
        if (!TextUtils.isEmpty(this.laUrl)) {
            defaultUrl = this.laUrl;
        }
        return Util.executePost(defaultUrl + "&signedRequest=" + new String(provisionRequest.getData()), null, PROVISIONING_REQUEST_PROPERTIES);
    }

    @Override // com.google.android.exoplayer.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws Exception {
        String defaultUrl = keyRequest.getDefaultUrl();
        if (TextUtils.isEmpty(defaultUrl)) {
            defaultUrl = "http://playready.directtaps.net/pr/svc/rightsmanager.asmx";
        }
        if (!TextUtils.isEmpty(this.laUrl)) {
            defaultUrl = this.laUrl;
        }
        Log.d("SmoothStreamingTestMediaDrmCallback", "executeKeyRequest laUrl=" + defaultUrl);
        return Util.executePost(defaultUrl, keyRequest.getData(), KEY_REQUEST_PROPERTIES);
    }

    public SmoothStreamingTestMediaDrmCallback() {
        Log.d("SmoothStreamingTestMediaDrmCallback", "laUrl=" + this.laUrl);
    }

    public SmoothStreamingTestMediaDrmCallback(String str) {
        this();
        this.laUrl = str;
        Log.d("SmoothStreamingTestMediaDrmCallback", "set laUrl=" + str);
    }
}
