package com.mediatek.twoworlds.tv.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventComponentBase implements Parcelable {
    public static final Parcelable.Creator<MtkTvEventComponentBase> CREATOR = new Parcelable.Creator<MtkTvEventComponentBase>() { // from class: com.mediatek.twoworlds.tv.model.MtkTvEventComponentBase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvEventComponentBase createFromParcel(Parcel parcel) {
            return new MtkTvEventComponentBase(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvEventComponentBase[] newArray(int i) {
            return new MtkTvEventComponentBase[i];
        }
    };
    private short componentTag;
    private short componentType;
    private short streamContent;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public short getStreamContent() {
        return this.streamContent;
    }

    public void setStreamContent(short s) {
        this.streamContent = s;
    }

    public short getComponentType() {
        return this.componentType;
    }

    public void setComponentType(short s) {
        this.componentType = s;
    }

    public short getComponentTag() {
        return this.componentTag;
    }

    public void setComponentTag(short s) {
        this.componentTag = s;
    }

    public MtkTvEventComponentBase() {
    }

    public MtkTvEventComponentBase(short s, short s2, short s3) {
        this.streamContent = s;
        this.componentType = s2;
        this.componentTag = s3;
    }

    private MtkTvEventComponentBase(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.streamContent);
        parcel.writeInt(this.componentType);
        parcel.writeInt(this.componentTag);
    }

    public void readFromParcel(Parcel parcel) {
        this.streamContent = (short) parcel.readInt();
        this.componentType = (short) parcel.readInt();
        this.componentTag = (short) parcel.readInt();
    }
}
