package com.ju.lib.datareport.dynamic;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicPolicyRequest implements Parcelable {
    public static final Parcelable.Creator<DynamicPolicyRequest> CREATOR = new Parcelable.Creator<DynamicPolicyRequest>() { // from class: com.ju.lib.datareport.dynamic.DynamicPolicyRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DynamicPolicyRequest createFromParcel(Parcel parcel) {
            return new DynamicPolicyRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DynamicPolicyRequest[] newArray(int i) {
            return new DynamicPolicyRequest[i];
        }
    };
    public String appKey;
    public String brandCode;
    public String countryCode;
    public String deviceId;
    public String policySeq;
    public String receiveTag;
    public String reportTag;
    public String version;

    public DynamicPolicyRequest() {
        this.reportTag = "0";
        this.receiveTag = "0";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getBrandCode() {
        return this.brandCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getPolicySeq() {
        return this.policySeq;
    }

    public String getReceiveTag() {
        return this.receiveTag;
    }

    public String getReportTag() {
        return this.reportTag;
    }

    public String getVersion() {
        return this.version;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setBrandCode(String str) {
        this.brandCode = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setPolicySeq(String str) {
        this.policySeq = str;
    }

    public void setReceiveTag(String str) {
        this.receiveTag = str;
    }

    public void setReportTag(String str) {
        this.reportTag = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("DynamicPolicyRequest{policySeq='");
        a.a(sbA, this.policySeq, '\'', ", deviceId='");
        a.a(sbA, this.deviceId, '\'', ", appKey='");
        a.a(sbA, this.appKey, '\'', ", version='");
        a.a(sbA, this.version, '\'', ", reportTag='");
        a.a(sbA, this.reportTag, '\'', ", receiveTag='");
        a.a(sbA, this.receiveTag, '\'', ", countryCode='");
        a.a(sbA, this.countryCode, '\'', ", brandCode='");
        sbA.append(this.brandCode);
        sbA.append('\'');
        sbA.append('}');
        return sbA.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.policySeq);
        parcel.writeString(this.deviceId);
        parcel.writeString(this.appKey);
        parcel.writeString(this.version);
        parcel.writeString(this.reportTag);
        parcel.writeString(this.receiveTag);
        parcel.writeString(this.countryCode);
        parcel.writeString(this.brandCode);
    }

    public DynamicPolicyRequest(String str, String str2, String str3, String str4, String str5, String str6) {
        this.reportTag = "0";
        this.receiveTag = "0";
        this.policySeq = str;
        this.deviceId = str2;
        this.appKey = str3;
        this.version = str4;
        this.reportTag = str5;
        this.receiveTag = str6;
    }

    public DynamicPolicyRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.reportTag = "0";
        this.receiveTag = "0";
        this.policySeq = str;
        this.deviceId = str2;
        this.appKey = str3;
        this.version = str4;
        this.reportTag = str5;
        this.receiveTag = str6;
        this.countryCode = str7;
        this.brandCode = str8;
    }

    public DynamicPolicyRequest(Parcel parcel) {
        this.reportTag = "0";
        this.receiveTag = "0";
        this.policySeq = parcel.readString();
        this.deviceId = parcel.readString();
        this.appKey = parcel.readString();
        this.version = parcel.readString();
        this.reportTag = parcel.readString();
        this.receiveTag = parcel.readString();
        this.countryCode = parcel.readString();
        this.brandCode = parcel.readString();
    }
}
