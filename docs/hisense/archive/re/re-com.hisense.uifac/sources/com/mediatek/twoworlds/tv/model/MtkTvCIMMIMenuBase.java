package com.mediatek.twoworlds.tv.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCIMMIMenuBase extends MtkTvCIMMIBase {
    public static final Parcelable.Creator<MtkTvCIMMIMenuBase> CREATOR = new Parcelable.Creator<MtkTvCIMMIMenuBase>() { // from class: com.mediatek.twoworlds.tv.model.MtkTvCIMMIMenuBase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvCIMMIMenuBase createFromParcel(Parcel parcel) {
            return new MtkTvCIMMIMenuBase(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvCIMMIMenuBase[] newArray(int i) {
            return new MtkTvCIMMIMenuBase[i];
        }
    };
    private static final String TAG = "MtkTvCIMMIMenu";
    private String bottom;
    private String[] itemList;
    private byte item_nb;
    private String subtitle;
    private String title;

    public int describeContents() {
        return 0;
    }

    public MtkTvCIMMIMenuBase(int i, byte b, String str, String str2, String str3, String[] strArr) {
        super(i);
        this.item_nb = b;
        this.title = str;
        this.subtitle = str2;
        this.bottom = str3;
        this.itemList = strArr;
    }

    public MtkTvCIMMIMenuBase() {
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setBottom(String str) {
        this.bottom = str;
    }

    public void setItemNum(byte b) {
        this.item_nb = b;
    }

    public void setItemList(String[] strArr) {
        this.itemList = strArr;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getBottom() {
        return this.bottom;
    }

    public byte getItemNum() {
        return this.item_nb;
    }

    public String[] getItemList() {
        return this.itemList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MMIMenu [menu id=");
        sb.append(this.mmi_id);
        sb.append("\n");
        sb.append("choice_nb=");
        sb.append("\n");
        sb.append((int) this.item_nb);
        sb.append("title=");
        sb.append(this.title);
        sb.append("\n");
        sb.append("subtitle=");
        sb.append(this.subtitle);
        sb.append("\n");
        sb.append("bottom=");
        sb.append(this.bottom);
        sb.append("\n");
        for (int i = 0; i < this.item_nb; i++) {
            sb.append(", itemList=");
            sb.append(this.itemList[i]);
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("]");
        return sb.toString();
    }

    private MtkTvCIMMIMenuBase(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mmi_id);
        parcel.writeByte(this.item_nb);
        parcel.writeString(this.title);
        parcel.writeString(this.subtitle);
        parcel.writeString(this.bottom);
        String[] strArr = this.itemList;
        if (strArr == null || strArr.length == 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(strArr.length);
        }
        parcel.writeStringArray(this.itemList);
    }

    public void readFromParcel(Parcel parcel) {
        this.mmi_id = parcel.readInt();
        this.item_nb = parcel.readByte();
        this.title = parcel.readString();
        this.subtitle = parcel.readString();
        this.bottom = parcel.readString();
        int i = parcel.readInt();
        if (i > 0) {
            String[] strArr = new String[i];
            this.itemList = strArr;
            parcel.readStringArray(strArr);
        }
    }
}
