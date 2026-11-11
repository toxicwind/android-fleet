package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzat extends IInterface {
    int zze(Intent intent, int i, int i2) throws RemoteException;

    IBinder zzf(Intent intent) throws RemoteException;

    void zzg() throws RemoteException;

    void zzh() throws RemoteException;
}
