package com.hisense.tv.smartsense.sdk.dynamicdomain.bean;

import android.os.Parcel;
import android.os.Parcelable;
import b.f.b.b.c.a.a.a;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicDomainObject implements Parcelable {
    public static final Parcelable.Creator<DynamicDomainObject> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1971a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1972b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public URL f1973c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1974d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1975e;

    public DynamicDomainObject() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1971a);
        parcel.writeString(this.f1972b);
        parcel.writeString(this.f1974d);
        parcel.writeString(this.f1975e);
        parcel.writeSerializable(this.f1973c);
    }

    public DynamicDomainObject(Parcel parcel) {
        this.f1971a = parcel.readString();
        this.f1972b = parcel.readString();
        this.f1974d = parcel.readString();
        this.f1975e = parcel.readString();
        this.f1973c = (URL) parcel.readSerializable();
    }
}
