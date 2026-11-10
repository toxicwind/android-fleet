package com.vt.interact;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ICFilesMsg implements Parcelable {
    public static final Parcelable.Creator<ICFilesMsg> CREATOR = new Parcelable.Creator<ICFilesMsg>() { // from class: com.vt.interact.ICFilesMsg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ICFilesMsg createFromParcel(Parcel parcel) {
            ICFilesMsg iCFilesMsg = new ICFilesMsg();
            iCFilesMsg.readFromParcel(parcel);
            return iCFilesMsg;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ICFilesMsg[] newArray(int i) {
            return new ICFilesMsg[i];
        }
    };
    public String[] appContextIdList;
    public int appContextIdListCount;
    public int[] filterCodes;
    public int filterCodesCount;
    public int packageContentLength;
    public String packageContentLocation;
    public int resourceFileCount;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.appContextIdListCount);
        parcel.writeStringArray(this.appContextIdList);
        parcel.writeInt(this.filterCodesCount);
        parcel.writeIntArray(this.filterCodes);
        parcel.writeString(this.packageContentLocation);
        parcel.writeInt(this.packageContentLength);
        parcel.writeInt(this.resourceFileCount);
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
            this.appContextIdListCount = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.appContextIdList = parcel.createStringArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.filterCodesCount = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.filterCodes = parcel.createIntArray();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.packageContentLocation = parcel.readString();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.packageContentLength = parcel.readInt();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.resourceFileCount = parcel.readInt();
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        parcel.setDataPosition(iDataPosition + i);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
