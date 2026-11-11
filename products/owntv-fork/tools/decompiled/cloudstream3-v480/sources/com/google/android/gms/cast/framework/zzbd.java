package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzbd extends com.google.android.gms.internal.cast.zzb implements zzbe {
    public zzbd() {
        super("com.google.android.gms.cast.framework.ISessionProxy");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperZzc = zzc();
                parcel2.writeNoException();
                com.google.android.gms.internal.cast.zzc.zze(parcel2, iObjectWrapperZzc);
                return true;
            case 2:
                Bundle bundle = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzh(bundle);
                parcel2.writeNoException();
                return true;
            case 3:
                Bundle bundle2 = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzg(bundle2);
                parcel2.writeNoException();
                return true;
            case 4:
                boolean zZzf = com.google.android.gms.internal.cast.zzc.zzf(parcel);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzd(zZzf);
                parcel2.writeNoException();
                return true;
            case 5:
                long jZzb = zzb();
                parcel2.writeNoException();
                parcel2.writeLong(jZzb);
                return true;
            case 6:
                parcel2.writeNoException();
                parcel2.writeInt(12451000);
                return true;
            case 7:
                Bundle bundle3 = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzf(bundle3);
                parcel2.writeNoException();
                return true;
            case 8:
                Bundle bundle4 = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zze(bundle4);
                parcel2.writeNoException();
                return true;
            case 9:
                Bundle bundle5 = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzi(bundle5);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
