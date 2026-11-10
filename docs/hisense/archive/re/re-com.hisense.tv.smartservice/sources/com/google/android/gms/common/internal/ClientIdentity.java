package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.installations.Utils;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "ClientIdentityCreator")
@SafeParcelable.Reserved({1000})
public class ClientIdentity extends AbstractSafeParcelable {

    @KeepForSdk
    public static final Parcelable.Creator<ClientIdentity> CREATOR = new zab();

    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 2)
    public final String packageName;

    @SafeParcelable.Field(defaultValueUnchecked = "0", id = 1)
    public final int uid;

    @SafeParcelable.Constructor
    public ClientIdentity(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) String str) {
        this.uid = i;
        this.packageName = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ClientIdentity)) {
            ClientIdentity clientIdentity = (ClientIdentity) obj;
            if (clientIdentity.uid == this.uid && Objects.equal(clientIdentity.packageName, this.packageName)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.uid;
    }

    public String toString() {
        int i = this.uid;
        String str = this.packageName;
        StringBuilder sb = new StringBuilder(a.a((Object) str, 12));
        sb.append(i);
        sb.append(Utils.APP_ID_IDENTIFICATION_SUBSTRING);
        sb.append(str);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.uid;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }
}
