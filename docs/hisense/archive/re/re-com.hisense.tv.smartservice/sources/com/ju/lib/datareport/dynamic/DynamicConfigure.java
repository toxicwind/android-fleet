package com.ju.lib.datareport.dynamic;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicConfigure implements Parcelable {
    public static final Parcelable.Creator<DynamicConfigure> CREATOR = new Parcelable.Creator<DynamicConfigure>() { // from class: com.ju.lib.datareport.dynamic.DynamicConfigure.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DynamicConfigure createFromParcel(Parcel parcel) {
            return new DynamicConfigure(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DynamicConfigure[] newArray(int i) {
            return new DynamicConfigure[i];
        }
    };
    public DynamicRequestInfo dynamicRequestInfo;
    public int platformInfo;

    public static final class PlateformInfo {
        public static final int HOME = 0;
        public static final int OVER_SEA_ANDROID = 2;
        public static final int OVER_SEA_VIDAA = 1;
    }

    public DynamicConfigure() {
        this.platformInfo = 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DynamicRequestInfo getDynamicRequestInfo() {
        return this.dynamicRequestInfo;
    }

    public int getPlatformInfo() {
        return this.platformInfo;
    }

    public boolean isDynamic() {
        return this.dynamicRequestInfo != null;
    }

    public boolean isSame(DynamicConfigure dynamicConfigure) {
        if (dynamicConfigure == null || dynamicConfigure.toString() == null) {
            return false;
        }
        return dynamicConfigure.toString().equals(toString());
    }

    public void setDynamicRequestInfo(DynamicRequestInfo dynamicRequestInfo) {
        this.dynamicRequestInfo = dynamicRequestInfo;
    }

    public void setPlatformInfo(int i) {
        this.platformInfo = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("OverSeaReporterInfo{dynamicRequestInfo=");
        sbA.append(this.dynamicRequestInfo);
        sbA.append(", platformInfo=");
        sbA.append(this.platformInfo);
        sbA.append('}');
        return sbA.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.dynamicRequestInfo, i);
        parcel.writeInt(this.platformInfo);
    }

    public DynamicConfigure(DynamicRequestInfo dynamicRequestInfo, int i) {
        this.platformInfo = 0;
        this.dynamicRequestInfo = dynamicRequestInfo;
        this.platformInfo = i;
    }

    public DynamicConfigure(Parcel parcel) {
        this.platformInfo = 0;
        this.dynamicRequestInfo = (DynamicRequestInfo) parcel.readParcelable(DynamicRequestInfo.class.getClassLoader());
        this.platformInfo = parcel.readInt();
    }
}
