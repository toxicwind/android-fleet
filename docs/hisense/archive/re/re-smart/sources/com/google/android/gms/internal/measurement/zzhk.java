package com.google.android.gms.internal.measurement;

import android.net.Uri;
import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;

/* JADX INFO: loaded from: classes.dex */
public final class zzhk {

    @GuardedBy("PhenotypeConstants.class")
    public static final ArrayMap<String, Uri> zza = new ArrayMap<>();

    public static synchronized Uri zza(String str) {
        Uri uri;
        uri = zza.get("com.google.android.gms.measurement");
        if (uri == null) {
            String strValueOf = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
            uri = Uri.parse(strValueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(strValueOf) : new String("content://com.google.android.gms.phenotype/"));
            zza.put("com.google.android.gms.measurement", uri);
        }
        return uri;
    }
}
