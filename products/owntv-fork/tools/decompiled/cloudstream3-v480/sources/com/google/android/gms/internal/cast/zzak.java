package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public interface zzak extends IInterface {
    int zze() throws RemoteException;

    com.google.android.gms.cast.framework.zzaj zzf(IObjectWrapper iObjectWrapper, CastOptions castOptions, zzam zzamVar, Map map) throws RemoteException;

    com.google.android.gms.cast.framework.zzam zzg(CastOptions castOptions, IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.zzag zzagVar) throws RemoteException;

    com.google.android.gms.cast.framework.zzat zzh(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    com.google.android.gms.cast.framework.zzaw zzi(String str, String str2, com.google.android.gms.cast.framework.zzbe zzbeVar) throws RemoteException;

    com.google.android.gms.cast.framework.media.internal.zzh zzj(IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.media.internal.zzj zzjVar, int i, int i2, boolean z, long j, int i3, int i4, int i5) throws RemoteException;

    com.google.android.gms.cast.framework.media.internal.zzh zzk(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, com.google.android.gms.cast.framework.media.internal.zzj zzjVar, int i, int i2, boolean z, long j, int i3, int i4, int i5) throws RemoteException;
}
