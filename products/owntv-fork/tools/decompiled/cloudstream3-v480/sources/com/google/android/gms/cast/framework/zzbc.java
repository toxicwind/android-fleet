package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzbc extends IInterface {
    IObjectWrapper zzb(String str) throws RemoteException;

    String zzc() throws RemoteException;

    boolean zzd() throws RemoteException;
}
