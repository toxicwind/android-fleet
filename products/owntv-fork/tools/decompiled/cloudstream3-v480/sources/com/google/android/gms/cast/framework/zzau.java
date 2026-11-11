package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzau extends com.google.android.gms.internal.cast.zza implements zzaw {
    zzau(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISession");
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(17, zza());
        int i = parcelZzb.readInt();
        parcelZzb.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final int zzf() throws RemoteException {
        Parcel parcelZzb = zzb(18, zza());
        int i = parcelZzb.readInt();
        parcelZzb.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final IObjectWrapper zzg() throws RemoteException {
        Parcel parcelZzb = zzb(1, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final String zzh() throws RemoteException {
        Parcel parcelZzb = zzb(2, zza());
        String string = parcelZzb.readString();
        parcelZzb.recycle();
        return string;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final String zzi() throws RemoteException {
        Parcel parcelZzb = zzb(3, zza());
        String string = parcelZzb.readString();
        parcelZzb.recycle();
        return string;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzj(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(15, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzk(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(12, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzl(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(13, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzm(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = com.google.android.gms.internal.cast.zzc.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzc(14, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzn(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(11, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final void zzo(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(16, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final boolean zzp() throws RemoteException {
        Parcel parcelZzb = zzb(5, zza());
        boolean zZzf = com.google.android.gms.internal.cast.zzc.zzf(parcelZzb);
        parcelZzb.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final boolean zzq() throws RemoteException {
        Parcel parcelZzb = zzb(6, zza());
        boolean zZzf = com.google.android.gms.internal.cast.zzc.zzf(parcelZzb);
        parcelZzb.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final boolean zzr() throws RemoteException {
        Parcel parcelZzb = zzb(8, zza());
        boolean zZzf = com.google.android.gms.internal.cast.zzc.zzf(parcelZzb);
        parcelZzb.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final boolean zzs() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        boolean zZzf = com.google.android.gms.internal.cast.zzc.zzf(parcelZzb);
        parcelZzb.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final boolean zzt() throws RemoteException {
        Parcel parcelZzb = zzb(9, zza());
        boolean zZzf = com.google.android.gms.internal.cast.zzc.zzf(parcelZzb);
        parcelZzb.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.cast.framework.zzaw
    public final boolean zzu() throws RemoteException {
        Parcel parcelZzb = zzb(10, zza());
        boolean zZzf = com.google.android.gms.internal.cast.zzc.zzf(parcelZzb);
        parcelZzb.recycle();
        return zZzf;
    }
}
