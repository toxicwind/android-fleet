package com.vt.interact;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ICFileDetail implements Parcelable {
    public static final Parcelable.Creator<ICFileDetail> CREATOR = new Parcelable.Creator<ICFileDetail>() { // from class: com.vt.interact.ICFileDetail.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ICFileDetail createFromParcel(Parcel parcel) {
            ICFileDetail iCFileDetail = new ICFileDetail();
            iCFileDetail.readFromParcel(parcel);
            return iCFileDetail;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ICFileDetail[] newArray(int i) {
            return new ICFileDetail[i];
        }
    };
    public String contentType;
    public String fileData;
    public int fileLength;
    public String relativeUrl;
    public String validFrom;
    public String validUntil;
    public int version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.relativeUrl);
        parcel.writeInt(this.version);
        parcel.writeString(this.validFrom);
        parcel.writeString(this.validUntil);
        parcel.writeString(this.contentType);
        parcel.writeInt(this.fileLength);
        parcel.writeString(this.fileData);
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
            this.relativeUrl = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.version = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.validFrom = parcel.readString();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.validUntil = parcel.readString();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.contentType = parcel.readString();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.fileLength = parcel.readInt();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.fileData = parcel.readString();
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
