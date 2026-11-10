package com.google.android.gms.common.internal.service;

import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public abstract class zaj extends com.google.android.gms.internal.base.zaa implements zak {
    public zaj() {
        super("com.google.android.gms.common.internal.service.ICommonCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zaa
    public boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        zaj(parcel.readInt());
        parcel2.writeNoException();
        return true;
    }
}
