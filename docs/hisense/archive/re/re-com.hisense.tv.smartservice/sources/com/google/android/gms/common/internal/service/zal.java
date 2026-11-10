package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class zal extends com.google.android.gms.internal.base.zab implements zam {
    public zal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    @Override // com.google.android.gms.common.internal.service.zam
    public final void zaa(zak zakVar) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, zakVar);
        zac(1, parcelZaa);
    }
}
