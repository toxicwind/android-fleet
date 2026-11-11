package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzbe extends IInterface {
    long zzb() throws RemoteException;

    IObjectWrapper zzc() throws RemoteException;

    void zzd(boolean z) throws RemoteException;

    void zze(Bundle bundle) throws RemoteException;

    void zzf(Bundle bundle) throws RemoteException;

    void zzg(Bundle bundle) throws RemoteException;

    void zzh(Bundle bundle) throws RemoteException;

    void zzi(Bundle bundle) throws RemoteException;
}
