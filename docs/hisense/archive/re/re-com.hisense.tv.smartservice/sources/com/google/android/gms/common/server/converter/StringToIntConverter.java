package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
@SafeParcelable.Class(creator = "StringToIntConverterCreator")
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {
    public static final Parcelable.Creator<StringToIntConverter> CREATOR = new zad();

    @SafeParcelable.VersionField(id = 1)
    public final int zali;
    public final HashMap<String, Integer> zaqc;
    public final SparseArray<String> zaqd;

    @SafeParcelable.Field(getter = "getSerializedMap", id = 2)
    public final ArrayList<zaa> zaqe;

    @SafeParcelable.Constructor
    public StringToIntConverter(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ArrayList<zaa> arrayList) {
        this.zali = i;
        this.zaqc = new HashMap<>();
        this.zaqd = new SparseArray<>();
        this.zaqe = null;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            zaa zaaVar = arrayList.get(i2);
            i2++;
            zaa zaaVar2 = zaaVar;
            add(zaaVar2.zaqa, zaaVar2.zaqb);
        }
    }

    @KeepForSdk
    public final StringToIntConverter add(String str, int i) {
        this.zaqc.put(str, Integer.valueOf(i));
        this.zaqd.put(i, str);
        return this;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* synthetic */ Integer convert(String str) {
        Integer num = this.zaqc.get(str);
        return num == null ? this.zaqc.get("gms_unknown") : num;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* synthetic */ String convertBack(Integer num) {
        String str = this.zaqd.get(num.intValue());
        return (str == null && this.zaqc.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zali;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zaqc.keySet()) {
            arrayList.add(new zaa(str, this.zaqc.get(str).intValue()));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final int zach() {
        return 7;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final int zaci() {
        return 0;
    }

    @SafeParcelable.Class(creator = "StringToIntConverterEntryCreator")
    public static final class zaa extends AbstractSafeParcelable {
        public static final Parcelable.Creator<zaa> CREATOR = new zac();

        @SafeParcelable.VersionField(id = 1)
        public final int versionCode;

        @SafeParcelable.Field(id = 2)
        public final String zaqa;

        @SafeParcelable.Field(id = 3)
        public final int zaqb;

        @SafeParcelable.Constructor
        public zaa(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2) {
            this.versionCode = i;
            this.zaqa = str;
            this.zaqb = i2;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            int i2 = this.versionCode;
            parcel.writeInt(262145);
            parcel.writeInt(i2);
            SafeParcelWriter.writeString(parcel, 2, this.zaqa, false);
            int i3 = this.zaqb;
            parcel.writeInt(262147);
            parcel.writeInt(i3);
            SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
        }

        public zaa(String str, int i) {
            this.versionCode = 1;
            this.zaqa = str;
            this.zaqb = i;
        }
    }

    @KeepForSdk
    public StringToIntConverter() {
        this.zali = 1;
        this.zaqc = new HashMap<>();
        this.zaqd = new SparseArray<>();
        this.zaqe = null;
    }
}
