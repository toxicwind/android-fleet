package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzaf extends com.google.android.gms.internal.cast.zzb implements zzag {
    public zzaf() {
        super("com.google.android.gms.cast.framework.ICastConnectionController");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            String string = parcel.readString();
            String string2 = parcel.readString();
            com.google.android.gms.internal.cast.zzc.zzb(parcel);
            zzc(string, string2);
            parcel2.writeNoException();
        } else if (i == 2) {
            String string3 = parcel.readString();
            LaunchOptions launchOptions = (LaunchOptions) com.google.android.gms.internal.cast.zzc.zza(parcel, LaunchOptions.CREATOR);
            com.google.android.gms.internal.cast.zzc.zzb(parcel);
            zzd(string3, launchOptions);
            parcel2.writeNoException();
        } else if (i == 3) {
            String string4 = parcel.readString();
            com.google.android.gms.internal.cast.zzc.zzb(parcel);
            zze(string4);
            parcel2.writeNoException();
        } else if (i == 4) {
            int i3 = parcel.readInt();
            com.google.android.gms.internal.cast.zzc.zzb(parcel);
            zzb(i3);
            parcel2.writeNoException();
        } else {
            if (i != 5) {
                return false;
            }
            parcel2.writeNoException();
            parcel2.writeInt(12451000);
        }
        return true;
    }
}
