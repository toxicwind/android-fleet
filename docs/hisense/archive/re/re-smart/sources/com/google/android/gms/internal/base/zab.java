package com.google.android.gms.internal.base;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public class zab implements IInterface {
    public final IBinder zab;
    public final String zac;

    public zab(IBinder iBinder, String str) {
        this.zab = iBinder;
        this.zac = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zab;
    }

    public final Parcel zaa() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zac);
        return parcelObtain;
    }

    public final void zab(int i, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zab.transact(i, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }

    public final void zac(int i, Parcel parcel) {
        try {
            this.zab.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }

    public final Parcel zaa(int i, Parcel parcel) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.zab.transact(i, parcel, parcelObtain, 0);
                parcelObtain.readException();
                parcel.recycle();
                return parcelObtain;
            } catch (RuntimeException e2) {
                parcelObtain.recycle();
                throw e2;
            }
        } catch (Throwable th) {
            parcel.recycle();
            throw th;
        }
    }
}
