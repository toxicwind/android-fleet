package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IGmsCallbacks extends IInterface {
    void onPostInitComplete(int i, @NonNull IBinder iBinder, @NonNull Bundle bundle);

    void zzb(int i, @NonNull Bundle bundle);

    void zzc(int i, IBinder iBinder, zzj zzjVar);
}
