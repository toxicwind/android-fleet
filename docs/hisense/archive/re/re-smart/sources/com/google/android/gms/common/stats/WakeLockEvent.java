package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "WakeLockEventCreator")
@Deprecated
public final class WakeLockEvent extends StatsEvent {

    @NonNull
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zza();

    @SafeParcelable.VersionField(id = 1)
    public final int zza;

    @SafeParcelable.Field(getter = "getTimeMillis", id = 2)
    public final long zzb;

    @SafeParcelable.Field(getter = "getEventType", id = 11)
    public int zzc;

    @SafeParcelable.Field(getter = "getWakeLockName", id = 4)
    public final String zzd;

    @SafeParcelable.Field(getter = "getSecondaryWakeLockName", id = 10)
    public final String zze;

    @SafeParcelable.Field(getter = "getCodePackage", id = 17)
    public final String zzf;

    @SafeParcelable.Field(getter = "getWakeLockType", id = 5)
    public final int zzg;

    @SafeParcelable.Field(getter = "getCallingPackages", id = 6)
    public final List<String> zzh;

    @SafeParcelable.Field(getter = "getEventKey", id = 12)
    public final String zzi;

    @SafeParcelable.Field(getter = "getElapsedRealtime", id = 8)
    public final long zzj;

    @SafeParcelable.Field(getter = "getDeviceState", id = 14)
    public int zzk;

    @SafeParcelable.Field(getter = "getHostPackage", id = 13)
    public final String zzl;

    @SafeParcelable.Field(getter = "getBeginPowerPercentage", id = 15)
    public final float zzm;

    @SafeParcelable.Field(getter = "getTimeout", id = 16)
    public final long zzn;

    @SafeParcelable.Field(getter = "getAcquiredWithTimeout", id = 18)
    public final boolean zzo;
    public long zzp = -1;

    @SafeParcelable.Constructor
    public WakeLockEvent(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 11) int i2, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 6) List<String> list, @SafeParcelable.Param(id = 12) String str2, @SafeParcelable.Param(id = 8) long j2, @SafeParcelable.Param(id = 14) int i4, @SafeParcelable.Param(id = 10) String str3, @SafeParcelable.Param(id = 13) String str4, @SafeParcelable.Param(id = 15) float f2, @SafeParcelable.Param(id = 16) long j3, @SafeParcelable.Param(id = 17) String str5, @SafeParcelable.Param(id = 18) boolean z) {
        this.zza = i;
        this.zzb = j;
        this.zzc = i2;
        this.zzd = str;
        this.zze = str3;
        this.zzf = str5;
        this.zzg = i3;
        this.zzh = list;
        this.zzi = str2;
        this.zzj = j2;
        this.zzk = i4;
        this.zzl = str4;
        this.zzm = f2;
        this.zzn = j3;
        this.zzo = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zza;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        long j = this.zzb;
        parcel.writeInt(524290);
        parcel.writeLong(j);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        int i3 = this.zzg;
        parcel.writeInt(262149);
        parcel.writeInt(i3);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzh, false);
        long j2 = this.zzj;
        parcel.writeInt(524296);
        parcel.writeLong(j2);
        SafeParcelWriter.writeString(parcel, 10, this.zze, false);
        int i4 = this.zzc;
        parcel.writeInt(262155);
        parcel.writeInt(i4);
        SafeParcelWriter.writeString(parcel, 12, this.zzi, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzl, false);
        int i5 = this.zzk;
        parcel.writeInt(262158);
        parcel.writeInt(i5);
        float f2 = this.zzm;
        parcel.writeInt(262159);
        parcel.writeFloat(f2);
        long j3 = this.zzn;
        parcel.writeInt(524304);
        parcel.writeLong(j3);
        SafeParcelWriter.writeString(parcel, 17, this.zzf, false);
        boolean z = this.zzo;
        parcel.writeInt(262162);
        parcel.writeInt(z ? 1 : 0);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final long zzb() {
        return this.zzp;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final long zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    @NonNull
    public final String zzd() {
        List<String> list = this.zzh;
        String str = this.zzd;
        int i = this.zzg;
        String strJoin = list == null ? "" : TextUtils.join(",", list);
        int i2 = this.zzk;
        String str2 = this.zze;
        if (str2 == null) {
            str2 = "";
        }
        String str3 = this.zzl;
        if (str3 == null) {
            str3 = "";
        }
        float f2 = this.zzm;
        String str4 = this.zzf;
        String str5 = str4 != null ? str4 : "";
        boolean z = this.zzo;
        StringBuilder sb = new StringBuilder(str5.length() + str3.length() + str2.length() + String.valueOf(str).length() + 51 + String.valueOf(strJoin).length());
        sb.append("\t");
        sb.append(str);
        sb.append("\t");
        sb.append(i);
        sb.append("\t");
        sb.append(strJoin);
        sb.append("\t");
        sb.append(i2);
        a.a(sb, "\t", str2, "\t", str3);
        sb.append("\t");
        sb.append(f2);
        sb.append("\t");
        sb.append(str5);
        sb.append("\t");
        sb.append(z);
        return sb.toString();
    }
}
