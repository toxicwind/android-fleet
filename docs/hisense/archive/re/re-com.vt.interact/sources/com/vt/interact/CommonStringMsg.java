package com.vt.interact;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class CommonStringMsg implements Parcelable {
    public static final Parcelable.Creator<CommonStringMsg> CREATOR = new Parcelable.Creator<CommonStringMsg>() { // from class: com.vt.interact.CommonStringMsg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CommonStringMsg createFromParcel(Parcel parcel) {
            CommonStringMsg commonStringMsg = new CommonStringMsg();
            commonStringMsg.readFromParcel(parcel);
            return commonStringMsg;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CommonStringMsg[] newArray(int i) {
            return new CommonStringMsg[i];
        }
    };
    public String result;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.result);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.result = parcel.readString();
            if (parcel.dataPosition() - iDataPosition >= i) {
                parcel.setDataPosition(iDataPosition + i);
            } else {
                parcel.setDataPosition(iDataPosition + i);
            }
        } catch (Throwable th) {
            parcel.setDataPosition(iDataPosition + i);
            throw th;
        }
    }
}
