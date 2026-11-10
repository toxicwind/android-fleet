package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "ConverterWrapperCreator")
public final class zab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zab> CREATOR = new zaa();

    @SafeParcelable.VersionField(id = 1)
    public final int zali;

    @SafeParcelable.Field(getter = "getStringToIntConverter", id = 2)
    public final StringToIntConverter zapz;

    @SafeParcelable.Constructor
    public zab(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) StringToIntConverter stringToIntConverter) {
        this.zali = i;
        this.zapz = stringToIntConverter;
    }

    public static zab zaa(FastJsonResponse.FieldConverter<?, ?> fieldConverter) {
        if (fieldConverter instanceof StringToIntConverter) {
            return new zab((StringToIntConverter) fieldConverter);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zali;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zapz, i, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public final FastJsonResponse.FieldConverter<?, ?> zacg() {
        StringToIntConverter stringToIntConverter = this.zapz;
        if (stringToIntConverter != null) {
            return stringToIntConverter;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    public zab(StringToIntConverter stringToIntConverter) {
        this.zali = 1;
        this.zapz = stringToIntConverter;
    }
}
