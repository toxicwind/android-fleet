package com.hisense.tv.smartsense.sdk.dynamicdomain.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class DynamicDomainObject implements Parcelable {
    public static final Parcelable.Creator CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f73a;
    private String b;
    private URL c;
    private String d;
    private String e;

    public DynamicDomainObject() {
    }

    protected DynamicDomainObject(Parcel parcel) {
        this.f73a = parcel.readString();
        this.b = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.c = (URL) parcel.readSerializable();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f73a);
        parcel.writeString(this.b);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeSerializable(this.c);
    }
}
