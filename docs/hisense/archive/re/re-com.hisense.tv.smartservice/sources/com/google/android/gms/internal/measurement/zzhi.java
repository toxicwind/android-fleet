package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzhi {
    public final Map<String, Map<String, String>> zza;

    public zzhi(Map<String, Map<String, String>> map) {
        this.zza = map;
    }

    public final String zza(Uri uri, String str, String str2, String str3) {
        if (uri == null) {
            return null;
        }
        Map<String, String> map = this.zza.get(uri.toString());
        if (map == null) {
            return null;
        }
        String strValueOf = String.valueOf(str3);
        return map.get(strValueOf.length() != 0 ? "".concat(strValueOf) : new String(""));
    }
}
