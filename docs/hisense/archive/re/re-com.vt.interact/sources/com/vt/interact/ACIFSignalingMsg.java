package com.vt.interact;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ACIFSignalingMsg implements Parcelable {
    public static final Parcelable.Creator<ACIFSignalingMsg> CREATOR = new Parcelable.Creator<ACIFSignalingMsg>() { // from class: com.vt.interact.ACIFSignalingMsg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ACIFSignalingMsg createFromParcel(Parcel parcel) {
            ACIFSignalingMsg aCIFSignalingMsg = new ACIFSignalingMsg();
            aCIFSignalingMsg.readFromParcel(parcel);
            return aCIFSignalingMsg;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ACIFSignalingMsg[] newArray(int i) {
            return new ACIFSignalingMsg[i];
        }
    };
    public int group;
    public int version;
    public String xmlData;
    public int xmlDataLength;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.group);
        parcel.writeInt(this.version);
        parcel.writeInt(this.xmlDataLength);
        parcel.writeString(this.xmlData);
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
            this.group = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.version = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.xmlDataLength = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.xmlData = parcel.readString();
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
