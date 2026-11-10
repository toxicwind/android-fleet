package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class StringResourceValueReader {
    public final Resources zza;
    public final String zzb;

    public StringResourceValueReader(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        this.zza = context.getResources();
        this.zzb = this.zza.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
    }

    @Nullable
    @KeepForSdk
    public String getString(@NonNull String str) {
        int identifier = this.zza.getIdentifier(str, "string", this.zzb);
        if (identifier == 0) {
            return null;
        }
        return this.zza.getString(identifier);
    }
}
