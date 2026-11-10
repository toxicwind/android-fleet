package com.vt.interact;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ICSignalingMsg implements Parcelable {
    public static final Parcelable.Creator<ICSignalingMsg> CREATOR = new Parcelable.Creator<ICSignalingMsg>() { // from class: com.vt.interact.ICSignalingMsg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ICSignalingMsg createFromParcel(Parcel parcel) {
            ICSignalingMsg iCSignalingMsg = new ICSignalingMsg();
            iCSignalingMsg.readFromParcel(parcel);
            return iCSignalingMsg;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ICSignalingMsg[] newArray(int i) {
            return new ICSignalingMsg[i];
        }
    };
    public int bsid;
    public int entryDataCount;
    public int groupId;
    public int serviceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.bsid);
        parcel.writeInt(this.groupId);
        parcel.writeInt(this.serviceId);
        parcel.writeInt(this.entryDataCount);
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
            this.bsid = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.groupId = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.serviceId = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.entryDataCount = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            parcel.setDataPosition(iDataPosition + i);
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
