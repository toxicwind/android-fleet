package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "ConditionalUserPropertyParcelCreator")
public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzab> CREATOR = new zzac();

    @Nullable
    @SafeParcelable.Field(id = 2)
    public String zza;

    @SafeParcelable.Field(id = 3)
    public String zzb;

    @SafeParcelable.Field(id = 4)
    public zzkv zzc;

    @SafeParcelable.Field(id = 5)
    public long zzd;

    @SafeParcelable.Field(id = 6)
    public boolean zze;

    @Nullable
    @SafeParcelable.Field(id = 7)
    public String zzf;

    @Nullable
    @SafeParcelable.Field(id = 8)
    public final zzat zzg;

    @SafeParcelable.Field(id = 9)
    public long zzh;

    @Nullable
    @SafeParcelable.Field(id = 10)
    public zzat zzi;

    @SafeParcelable.Field(id = 11)
    public final long zzj;

    @Nullable
    @SafeParcelable.Field(id = 12)
    public final zzat zzk;

    public zzab(zzab zzabVar) {
        Preconditions.checkNotNull(zzabVar);
        this.zza = zzabVar.zza;
        this.zzb = zzabVar.zzb;
        this.zzc = zzabVar.zzc;
        this.zzd = zzabVar.zzd;
        this.zze = zzabVar.zze;
        this.zzf = zzabVar.zzf;
        this.zzg = zzabVar.zzg;
        this.zzh = zzabVar.zzh;
        this.zzi = zzabVar.zzi;
        this.zzj = zzabVar.zzj;
        this.zzk = zzabVar.zzk;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i, false);
        long j = this.zzd;
        parcel.writeInt(524293);
        parcel.writeLong(j);
        boolean z = this.zze;
        parcel.writeInt(262150);
        parcel.writeInt(z ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        long j2 = this.zzh;
        parcel.writeInt(524297);
        parcel.writeLong(j2);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i, false);
        long j3 = this.zzj;
        parcel.writeInt(524299);
        parcel.writeLong(j3);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzab(@Nullable @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) zzkv zzkvVar, @SafeParcelable.Param(id = 5) long j, @SafeParcelable.Param(id = 6) boolean z, @Nullable @SafeParcelable.Param(id = 7) String str3, @Nullable @SafeParcelable.Param(id = 8) zzat zzatVar, @SafeParcelable.Param(id = 9) long j2, @Nullable @SafeParcelable.Param(id = 10) zzat zzatVar2, @SafeParcelable.Param(id = 11) long j3, @Nullable @SafeParcelable.Param(id = 12) zzat zzatVar3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzkvVar;
        this.zzd = j;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzatVar;
        this.zzh = j2;
        this.zzi = zzatVar2;
        this.zzj = j3;
        this.zzk = zzatVar3;
    }
}
