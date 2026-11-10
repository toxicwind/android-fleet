package com.vt.interact;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ICHTMLEntryPkg implements Parcelable {
    public static final Parcelable.Creator<ICHTMLEntryPkg> CREATOR = new Parcelable.Creator<ICHTMLEntryPkg>() { // from class: com.vt.interact.ICHTMLEntryPkg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ICHTMLEntryPkg createFromParcel(Parcel parcel) {
            ICHTMLEntryPkg iCHTMLEntryPkg = new ICHTMLEntryPkg();
            iCHTMLEntryPkg.readFromParcel(parcel);
            return iCHTMLEntryPkg;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ICHTMLEntryPkg[] newArray(int i) {
            return new ICHTMLEntryPkg[i];
        }
    };
    public String appContextId;
    public int appDefault;
    public int appRendering;
    public String bbandEntryPgUrl;
    public String bcastEntryPgUrl;
    public String bcastEntryPkgUrl;
    public String clrAppConCacheDate;
    public int[] coupledServices;
    public int coupledSvcCount;
    public String rqdCapabilities;
    public String validFm;
    public String validUntil;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.appContextId);
        parcel.writeString(this.rqdCapabilities);
        parcel.writeInt(this.appRendering);
        parcel.writeString(this.clrAppConCacheDate);
        parcel.writeString(this.bcastEntryPkgUrl);
        parcel.writeString(this.bcastEntryPgUrl);
        parcel.writeString(this.bbandEntryPgUrl);
        parcel.writeString(this.validFm);
        parcel.writeString(this.validUntil);
        parcel.writeInt(this.coupledSvcCount);
        parcel.writeIntArray(this.coupledServices);
        parcel.writeInt(this.appDefault);
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
            this.appContextId = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.rqdCapabilities = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.appRendering = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.clrAppConCacheDate = parcel.readString();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.bcastEntryPkgUrl = parcel.readString();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.bcastEntryPgUrl = parcel.readString();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.bbandEntryPgUrl = parcel.readString();
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        this.validFm = parcel.readString();
                                        if (parcel.dataPosition() - iDataPosition < i) {
                                            this.validUntil = parcel.readString();
                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                this.coupledSvcCount = parcel.readInt();
                                                if (parcel.dataPosition() - iDataPosition < i) {
                                                    this.coupledServices = parcel.createIntArray();
                                                    if (parcel.dataPosition() - iDataPosition < i) {
                                                        this.appDefault = parcel.readInt();
                                                        if (parcel.dataPosition() - iDataPosition < i) {
                                                            parcel.setDataPosition(iDataPosition + i);
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
