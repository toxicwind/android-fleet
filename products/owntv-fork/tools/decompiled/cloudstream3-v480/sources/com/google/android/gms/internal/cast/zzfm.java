package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzfm extends zzb implements zzfn {
    public zzfm() {
        super("com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                Status status = (Status) zzc.zza(parcel, Status.CREATOR);
                zzfk zzfkVar = (zzfk) zzc.zza(parcel, zzfk.CREATOR);
                zzc.zzb(parcel);
                zzb(status, zzfkVar);
                return true;
            case 3:
                Status status2 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzc.zzb(parcel);
                zzm(status2);
                return true;
            case 4:
                Status status3 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzc.zzb(parcel);
                zzj(status3);
                return true;
            case 5:
                Status status4 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzc.zzb(parcel);
                zzo(status4);
                return true;
            case 6:
                Status status5 = (Status) zzc.zza(parcel, Status.CREATOR);
                ArrayList<String> arrayListCreateStringArrayList = parcel.createStringArrayList();
                zzc.zzb(parcel);
                zze(status5, arrayListCreateStringArrayList);
                return true;
            case 7:
                Status status6 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzc.zzb(parcel);
                zzk(status6);
                return true;
            case 8:
                Status status7 = (Status) zzc.zza(parcel, Status.CREATOR);
                boolean zZzf = zzc.zzf(parcel);
                zzew zzewVar = (zzew) zzc.zza(parcel, zzew.CREATOR);
                zzc.zzb(parcel);
                zzc(status7, zZzf, zzewVar);
                return true;
            case 9:
                Status status8 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzew zzewVar2 = (zzew) zzc.zza(parcel, zzew.CREATOR);
                zzc.zzb(parcel);
                zzh(status8, zzewVar2);
                return true;
            case 10:
                PendingIntent pendingIntent = (PendingIntent) zzc.zza(parcel, PendingIntent.CREATOR);
                zzc.zzb(parcel);
                zzf(pendingIntent);
                return true;
            case 11:
                Status status9 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzc.zzb(parcel);
                zzl(status9);
                return true;
            case 12:
                Status status10 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzey zzeyVar = (zzey) zzc.zza(parcel, zzey.CREATOR);
                zzc.zzb(parcel);
                zzg(status10, zzeyVar);
                return true;
            case 13:
                Status status11 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzc.zzb(parcel);
                zzn(status11);
                return true;
            case 14:
                Status status12 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzff zzffVar = (zzff) zzc.zza(parcel, zzff.CREATOR);
                zzc.zzb(parcel);
                zzi(status12, zzffVar);
                return true;
            case 15:
                Status status13 = (Status) zzc.zza(parcel, Status.CREATOR);
                zzew zzewVar3 = (zzew) zzc.zza(parcel, zzew.CREATOR);
                zzc.zzb(parcel);
                zzd(status13, zzewVar3);
                return true;
            default:
                return false;
        }
    }
}
