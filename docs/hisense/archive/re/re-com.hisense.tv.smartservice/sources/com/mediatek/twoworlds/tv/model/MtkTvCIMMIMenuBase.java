package com.mediatek.twoworlds.tv.model;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCIMMIMenuBase extends MtkTvCIMMIBase {
    public static final Parcelable.Creator<MtkTvCIMMIMenuBase> CREATOR = new Parcelable.Creator<MtkTvCIMMIMenuBase>() { // from class: com.mediatek.twoworlds.tv.model.MtkTvCIMMIMenuBase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvCIMMIMenuBase createFromParcel(Parcel parcel) {
            return new MtkTvCIMMIMenuBase(parcel, null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MtkTvCIMMIMenuBase[] newArray(int i) {
            return new MtkTvCIMMIMenuBase[i];
        }
    };
    public static final String TAG = "MtkTvCIMMIMenu";
    public String bottom;
    public String[] itemList;
    public byte item_nb;
    public String subtitle;
    public String title;

    public MtkTvCIMMIMenuBase(int i, byte b2, String str, String str2, String str3, String[] strArr) {
        super(i);
        this.item_nb = b2;
        this.title = str;
        this.subtitle = str2;
        this.bottom = str3;
        this.itemList = strArr;
    }

    public int describeContents() {
        return 0;
    }

    public String getBottom() {
        return this.bottom;
    }

    public String[] getItemList() {
        return this.itemList;
    }

    public byte getItemNum() {
        return this.item_nb;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public void readFromParcel(Parcel parcel) {
        this.mmi_id = parcel.readInt();
        this.item_nb = parcel.readByte();
        this.title = parcel.readString();
        this.subtitle = parcel.readString();
        this.bottom = parcel.readString();
        int i = parcel.readInt();
        if (i > 0) {
            this.itemList = new String[i];
            parcel.readStringArray(this.itemList);
        }
    }

    public void setBottom(String str) {
        this.bottom = str;
    }

    public void setItemList(String[] strArr) {
        this.itemList = strArr;
    }

    public void setItemNum(byte b2) {
        this.item_nb = b2;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("MMIMenu [menu id=");
        sbA.append(this.mmi_id);
        sbA.append("\n");
        sbA.append("choice_nb=");
        sbA.append("\n");
        sbA.append((int) this.item_nb);
        sbA.append("title=");
        sbA.append(this.title);
        sbA.append("\n");
        sbA.append("subtitle=");
        sbA.append(this.subtitle);
        sbA.append("\n");
        sbA.append("bottom=");
        sbA.append(this.bottom);
        sbA.append("\n");
        for (int i = 0; i < this.item_nb; i++) {
            sbA.append(", itemList=");
            sbA.append(this.itemList[i]);
            sbA.append("\n");
        }
        return a.a(sbA, "\n", "]");
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

    public MtkTvCIMMIMenuBase() {
    }

    public MtkTvCIMMIMenuBase(Parcel parcel) {
        readFromParcel(parcel);
    }

    public /* synthetic */ MtkTvCIMMIMenuBase(Parcel parcel, AnonymousClass1 anonymousClass1) {
        readFromParcel(parcel);
    }
}
