package com.google.firebase.storage.internal;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.installations.Utils;
import com.google.firebase.storage.network.NetworkRequest;

/* JADX INFO: loaded from: classes.dex */
public class StorageReferenceUri {
    public final Uri gsUri;
    public final Uri httpBaseUri;
    public final Uri httpUri;

    public StorageReferenceUri(@NonNull Uri uri) {
        this(uri, null);
    }

    @NonNull
    public Uri getGsUri() {
        return this.gsUri;
    }

    @NonNull
    public Uri getHttpBaseUri() {
        return this.httpBaseUri;
    }

    @NonNull
    public Uri getHttpUri() {
        return this.httpUri;
    }

    public StorageReferenceUri(@NonNull Uri uri, @Nullable EmulatedServiceSettings emulatedServiceSettings) {
        Uri uri2;
        this.gsUri = uri;
        if (emulatedServiceSettings == null) {
            uri2 = NetworkRequest.PROD_BASE_URL;
        } else {
            StringBuilder sbA = a.a("http://");
            sbA.append(emulatedServiceSettings.getHost());
            sbA.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
            sbA.append(emulatedServiceSettings.getPort());
            sbA.append("/v0");
            uri2 = Uri.parse(sbA.toString());
        }
        this.httpBaseUri = uri2;
        Uri.Builder builderAppendEncodedPath = this.httpBaseUri.buildUpon().appendPath("b").appendEncodedPath(uri.getAuthority());
        String strNormalizeSlashes = Slashes.normalizeSlashes(uri.getPath());
        if (strNormalizeSlashes.length() > 0 && !"/".equals(strNormalizeSlashes)) {
            builderAppendEncodedPath = builderAppendEncodedPath.appendPath("o").appendPath(strNormalizeSlashes);
        }
        this.httpUri = builderAppendEncodedPath.build();
    }
}
