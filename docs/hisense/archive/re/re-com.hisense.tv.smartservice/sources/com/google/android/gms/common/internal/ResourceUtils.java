package com.google.android.gms.common.internal;

import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public final class ResourceUtils {
    public static final Uri zza = new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();
}
