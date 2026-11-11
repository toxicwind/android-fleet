package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzba extends IInterface {
    IObjectWrapper zzb() throws RemoteException;

    void zzc(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zzd(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zze(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zzf(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException;

    void zzg(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    void zzh(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zzi(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    void zzj(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzk(IObjectWrapper iObjectWrapper, int i) throws RemoteException;
}
