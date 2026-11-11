package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzam extends IInterface {
    Bundle zzb(String str) throws RemoteException;

    String zzc() throws RemoteException;

    void zzd(Bundle bundle, int i) throws RemoteException;

    void zze(Bundle bundle, zzao zzaoVar) throws RemoteException;

    void zzf() throws RemoteException;

    void zzg(Bundle bundle) throws RemoteException;

    void zzh() throws RemoteException;

    void zzi(String str) throws RemoteException;

    void zzj(int i) throws RemoteException;

    boolean zzk() throws RemoteException;

    boolean zzl() throws RemoteException;

    boolean zzm(Bundle bundle, int i) throws RemoteException;
}
