package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zze extends com.google.android.gms.internal.cast.zza implements zzg {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.INotificationActionsProvider");
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final IObjectWrapper zze() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final List zzf() throws RemoteException {
        Parcel parcelZzb = zzb(3, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZzb.createTypedArrayList(NotificationAction.CREATOR);
        parcelZzb.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final int[] zzg() throws RemoteException {
        Parcel parcelZzb = zzb(4, zza());
        int[] iArrCreateIntArray = parcelZzb.createIntArray();
        parcelZzb.recycle();
        return iArrCreateIntArray;
    }
}
