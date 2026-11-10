package com.ju.lib.datareport.dynamic;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicRequestInfo implements Parcelable {
    public static final Parcelable.Creator<DynamicRequestInfo> CREATOR = new Parcelable.Creator<DynamicRequestInfo>() { // from class: com.ju.lib.datareport.dynamic.DynamicRequestInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DynamicRequestInfo createFromParcel(Parcel parcel) {
            return new DynamicRequestInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DynamicRequestInfo[] newArray(int i) {
            return new DynamicRequestInfo[i];
        }
    };
    public String accessToken;
    public String areacode;
    public String brand;
    public String capabilitycode;
    public String chipplatform;
    public String deviceid;
    public String devicemsg;
    public String md5;
    public String os;
    public String tvversion;
    public String zone;

    public DynamicRequestInfo() {
        this.accessToken = "";
        this.deviceid = "";
        this.devicemsg = "";
        this.tvversion = "";
        this.brand = "";
        this.os = "";
        this.capabilitycode = "";
        this.chipplatform = "";
        this.zone = "";
        this.areacode = "";
        this.md5 = "";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAreacode() {
        return this.areacode;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getCapabilitycode() {
        return this.capabilitycode;
    }

    public String getChipplatform() {
        return this.chipplatform;
    }

    public String getDeviceid() {
        return this.deviceid;
    }

    public String getDevicemsg() {
        return this.devicemsg;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getOs() {
        return this.os;
    }

    public String getTvversion() {
        return this.tvversion;
    }

    public String getZone() {
        return this.zone;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAreacode(String str) {
        this.areacode = str;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public void setCapabilitycode(String str) {
        this.capabilitycode = str;
    }

    public void setChipplatform(String str) {
        this.chipplatform = str;
    }

    public void setDeviceid(String str) {
        this.deviceid = str;
    }

    public void setDevicemsg(String str) {
        this.devicemsg = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public void setTvversion(String str) {
        this.tvversion = str;
    }

    public void setZone(String str) {
        this.zone = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("DynamicRequestInfo{accessToken='");
        a.a(sbA, this.accessToken, '\'', ", deviceid='");
        a.a(sbA, this.deviceid, '\'', ", devicemsg='");
        a.a(sbA, this.devicemsg, '\'', ", tvversion='");
        a.a(sbA, this.tvversion, '\'', ", brand='");
        a.a(sbA, this.brand, '\'', ", os='");
        a.a(sbA, this.os, '\'', ", capabilitycode='");
        a.a(sbA, this.capabilitycode, '\'', ", chipplatform='");
        a.a(sbA, this.chipplatform, '\'', ", zone='");
        a.a(sbA, this.zone, '\'', ", areacode='");
        a.a(sbA, this.areacode, '\'', ", md5='");
        sbA.append(this.md5);
        sbA.append('\'');
        sbA.append('}');
        return sbA.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.accessToken);
        parcel.writeString(this.deviceid);
        parcel.writeString(this.devicemsg);
        parcel.writeString(this.tvversion);
        parcel.writeString(this.brand);
        parcel.writeString(this.os);
        parcel.writeString(this.capabilitycode);
        parcel.writeString(this.chipplatform);
        parcel.writeString(this.zone);
        parcel.writeString(this.areacode);
        parcel.writeString(this.md5);
    }

    public DynamicRequestInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.accessToken = "";
        this.deviceid = "";
        this.devicemsg = "";
        this.tvversion = "";
        this.brand = "";
        this.os = "";
        this.capabilitycode = "";
        this.chipplatform = "";
        this.zone = "";
        this.areacode = "";
        this.md5 = "";
        this.accessToken = str;
        this.deviceid = str2;
        this.devicemsg = str3;
        this.tvversion = str4;
        this.brand = str5;
        this.os = str6;
        this.capabilitycode = str7;
        this.chipplatform = str8;
        this.zone = str9;
        this.areacode = str10;
    }

    public DynamicRequestInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.accessToken = "";
        this.deviceid = "";
        this.devicemsg = "";
        this.tvversion = "";
        this.brand = "";
        this.os = "";
        this.capabilitycode = "";
        this.chipplatform = "";
        this.zone = "";
        this.areacode = "";
        this.md5 = "";
        this.accessToken = str;
        this.deviceid = str2;
        this.devicemsg = str3;
        this.tvversion = str4;
        this.brand = str5;
        this.os = str6;
        this.capabilitycode = str7;
        this.chipplatform = str8;
        this.zone = str9;
        this.areacode = str10;
        this.md5 = str11;
    }

    public DynamicRequestInfo(Parcel parcel) {
        this.accessToken = "";
        this.deviceid = "";
        this.devicemsg = "";
        this.tvversion = "";
        this.brand = "";
        this.os = "";
        this.capabilitycode = "";
        this.chipplatform = "";
        this.zone = "";
        this.areacode = "";
        this.md5 = "";
        this.accessToken = parcel.readString();
        this.deviceid = parcel.readString();
        this.devicemsg = parcel.readString();
        this.tvversion = parcel.readString();
        this.brand = parcel.readString();
        this.os = parcel.readString();
        this.capabilitycode = parcel.readString();
        this.chipplatform = parcel.readString();
        this.zone = parcel.readString();
        this.areacode = parcel.readString();
        this.md5 = parcel.readString();
    }
}
