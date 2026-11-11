package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzfn extends IInterface {
    void zzb(Status status, zzfk zzfkVar) throws RemoteException;

    void zzc(Status status, boolean z, zzew zzewVar) throws RemoteException;

    void zzd(Status status, zzew zzewVar) throws RemoteException;

    void zze(Status status, List list) throws RemoteException;

    void zzf(PendingIntent pendingIntent) throws RemoteException;

    void zzg(Status status, zzey zzeyVar) throws RemoteException;

    void zzh(Status status, zzew zzewVar) throws RemoteException;

    void zzi(Status status, zzff zzffVar) throws RemoteException;

    void zzj(Status status) throws RemoteException;

    void zzk(Status status) throws RemoteException;

    void zzl(Status status) throws RemoteException;

    void zzm(Status status) throws RemoteException;

    void zzn(Status status) throws RemoteException;

    void zzo(Status status) throws RemoteException;
}
