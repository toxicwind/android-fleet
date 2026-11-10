package com.ju.lib.datacommunication.network.http.address;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicAddressParam {
    public String appSecretForSign;
    public String appVersion;
    public String deviceId;
    public String format;
    public String languageId;
    public String refAppKey;
    public String sourceType;
    public String version;

    public DynamicAddressParam(String str, String str2, String str3, String str4) {
        this.refAppKey = str;
        this.deviceId = str2;
        this.appSecretForSign = str3;
        this.appVersion = str4;
    }

    public DynamicAddressParam copyObject() {
        DynamicAddressParam dynamicAddressParam = new DynamicAddressParam(this.refAppKey, this.deviceId, this.appSecretForSign, this.appVersion);
        dynamicAddressParam.version = this.version;
        dynamicAddressParam.format = this.format;
        dynamicAddressParam.languageId = this.languageId;
        dynamicAddressParam.sourceType = this.sourceType;
        return dynamicAddressParam;
    }

    public String getAppSecretForSign() {
        return this.appSecretForSign;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getRefAppKey() {
        return this.refAppKey;
    }
}
