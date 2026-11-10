package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class zzac implements IGmsServiceBroker {
    public final IBinder zza;

    public zzac(IBinder iBinder) {
        this.zza = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zza;
    }

    @Override // com.google.android.gms.common.internal.IGmsServiceBroker
    public final void getService(IGmsCallbacks iGmsCallbacks, @Nullable GetServiceRequest getServiceRequest) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            parcelObtain.writeStrongBinder(iGmsCallbacks != null ? iGmsCallbacks.asBinder() : null);
            if (getServiceRequest != null) {
                parcelObtain.writeInt(1);
                zzm.zza(getServiceRequest, parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            this.zza.transact(46, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
