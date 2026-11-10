package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "FieldMappingDictionaryCreator")
public final class zaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zaj> CREATOR = new zao();

    @SafeParcelable.VersionField(id = 1)
    public final int zali;
    public final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zarj;

    @SafeParcelable.Field(getter = "getSerializedDictionary", id = 2)
    public final ArrayList<zam> zark;

    @SafeParcelable.Field(getter = "getRootClassName", id = 3)
    public final String zarl;

    @SafeParcelable.Constructor
    public zaj(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ArrayList<zam> arrayList, @SafeParcelable.Param(id = 3) String str) {
        this.zali = i;
        this.zark = null;
        HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> map = new HashMap<>();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            zam zamVar = arrayList.get(i2);
            String str2 = zamVar.className;
            HashMap map2 = new HashMap();
            int size2 = zamVar.zaro.size();
            for (int i3 = 0; i3 < size2; i3++) {
                zal zalVar = zamVar.zaro.get(i3);
                map2.put(zalVar.zarm, zalVar.zarn);
            }
            map.put(str2, map2);
        }
        this.zarj = map;
        Preconditions.checkNotNull(str);
        this.zarl = str;
        zacp();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.zarj.keySet()) {
            sb.append(str);
            sb.append(":\n");
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zarj.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ");
                sb.append(str2);
                sb.append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zali;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zarj.keySet()) {
            arrayList.add(new zam(str, this.zarj.get(str)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.writeString(parcel, 3, this.zarl, false);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public final void zaa(Class<? extends FastJsonResponse> cls, Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.zarj.put(cls.getCanonicalName(), map);
    }

    public final void zacp() {
        Iterator<String> it = this.zarj.keySet().iterator();
        while (it.hasNext()) {
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zarj.get(it.next());
            Iterator<String> it2 = map.keySet().iterator();
            while (it2.hasNext()) {
                map.get(it2.next()).zaa(this);
            }
        }
    }

    public final void zacq() {
        for (String str : this.zarj.keySet()) {
            Map<String, FastJsonResponse.Field<?, ?>> map = this.zarj.get(str);
            HashMap map2 = new HashMap();
            for (String str2 : map.keySet()) {
                map2.put(str2, map.get(str2).zacj());
            }
            this.zarj.put(str, map2);
        }
    }

    public final String zacr() {
        return this.zarl;
    }

    public final Map<String, FastJsonResponse.Field<?, ?>> zai(String str) {
        return this.zarj.get(str);
    }

    public final boolean zaa(Class<? extends FastJsonResponse> cls) {
        return this.zarj.containsKey(cls.getCanonicalName());
    }

    public zaj(Class<? extends FastJsonResponse> cls) {
        this.zali = 1;
        this.zark = null;
        this.zarj = new HashMap<>();
        this.zarl = cls.getCanonicalName();
    }
}
