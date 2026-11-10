package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes.dex */
public final class zzm implements Parcelable.Creator<GetServiceRequest> {
    public static void zza(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = getServiceRequest.zza;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        int i3 = getServiceRequest.zzb;
        parcel.writeInt(262146);
        parcel.writeInt(i3);
        int i4 = getServiceRequest.zzc;
        parcel.writeInt(262147);
        parcel.writeInt(i4);
        SafeParcelWriter.writeString(parcel, 4, getServiceRequest.zzd, false);
        SafeParcelWriter.writeIBinder(parcel, 5, getServiceRequest.zze, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, getServiceRequest.zzf, i, false);
        SafeParcelWriter.writeBundle(parcel, 7, getServiceRequest.zzg, false);
        SafeParcelWriter.writeParcelable(parcel, 8, getServiceRequest.zzh, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 10, getServiceRequest.zzi, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, getServiceRequest.zzj, i, false);
        boolean z = getServiceRequest.zzk;
        parcel.writeInt(262156);
        parcel.writeInt(z ? 1 : 0);
        int i5 = getServiceRequest.zzl;
        parcel.writeInt(262157);
        parcel.writeInt(i5);
        boolean z2 = getServiceRequest.zzm;
        parcel.writeInt(262158);
        parcel.writeInt(z2 ? 1 : 0);
        SafeParcelWriter.writeString(parcel, 15, getServiceRequest.zza(), false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public final GetServiceRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        boolean z2 = false;
        String strCreateString = null;
        IBinder iBinder = null;
        Scope[] scopeArr = null;
        Bundle bundleCreateBundle = null;
        Account account = null;
        Feature[] featureArr = null;
        Feature[] featureArr2 = null;
        String strCreateString2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int i5 = parcel.readInt();
            switch ((char) i5) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 3:
                    i3 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, i5);
                    break;
                case 5:
                    iBinder = SafeParcelReader.readIBinder(parcel, i5);
                    break;
                case 6:
                    scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel, i5, Scope.CREATOR);
                    break;
                case 7:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, i5);
                    break;
                case '\b':
                    account = (Account) SafeParcelReader.createParcelable(parcel, i5, Account.CREATOR);
                    break;
                case '\t':
                default:
                    SafeParcelReader.skipUnknownField(parcel, i5);
                    break;
                case '\n':
                    featureArr = (Feature[]) SafeParcelReader.createTypedArray(parcel, i5, Feature.CREATOR);
                    break;
                case 11:
                    featureArr2 = (Feature[]) SafeParcelReader.createTypedArray(parcel, i5, Feature.CREATOR);
                    break;
                case '\f':
                    z = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case '\r':
                    i4 = SafeParcelReader.readInt(parcel, i5);
                    break;
                case 14:
                    z2 = SafeParcelReader.readBoolean(parcel, i5);
                    break;
                case 15:
                    strCreateString2 = SafeParcelReader.createString(parcel, i5);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GetServiceRequest(i, i2, i3, strCreateString, iBinder, scopeArr, bundleCreateBundle, account, featureArr, featureArr2, z, i4, z2, strCreateString2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GetServiceRequest[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
