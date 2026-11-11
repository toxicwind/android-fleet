package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzam extends IInterface {
    void zze(boolean z, int i) throws RemoteException;

    void zzf(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException;

    void zzg(int i) throws RemoteException;

    void zzh(Bundle bundle) throws RemoteException;

    void zzi(ConnectionResult connectionResult) throws RemoteException;

    void zzj(int i) throws RemoteException;
}
