package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class zzaa extends com.google.android.gms.internal.common.zza implements IGmsCallbacks {
    public zzaa(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        parcelZza.writeStrongBinder(iBinder);
        com.google.android.gms.internal.common.zzc.zzc(parcelZza, bundle);
        zzC(1, parcelZza);
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zzb(int i, Bundle bundle) {
        throw null;
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    public final void zzc(int i, IBinder iBinder, zzj zzjVar) {
        throw null;
    }
}
