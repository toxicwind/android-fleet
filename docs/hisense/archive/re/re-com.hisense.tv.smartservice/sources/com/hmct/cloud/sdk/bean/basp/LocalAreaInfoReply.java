package com.hmct.cloud.sdk.bean.basp;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;

/* JADX INFO: loaded from: classes2.dex */
public class LocalAreaInfoReply extends ReplyInfo {
    public static final long serialVersionUID = -6335364669825031421L;
    public String deviceIp;
    public String sysCity;
    public String sysCountry;
    public String sysProvince;
    public String tvCity;
    public String tvCountry;
    public String tvProvince;

    public String getDeviceIp() {
        return this.deviceIp;
    }

    public String getSysCity() {
        return this.sysCity;
    }

    public String getSysCountry() {
        return this.sysCountry;
    }

    public String getSysProvince() {
        return this.sysProvince;
    }

    public String getTvCity() {
        return this.tvCity;
    }

    public String getTvCountry() {
        return this.tvCountry;
    }

    public String getTvProvince() {
        return this.tvProvince;
    }

    public void setDeviceIp(String str) {
        this.deviceIp = str;
    }

    public void setSysCity(String str) {
        this.sysCity = str;
    }

    public void setSysCountry(String str) {
        this.sysCountry = str;
    }

    public void setSysProvince(String str) {
        this.sysProvince = str;
    }

    public void setTvCity(String str) {
        this.tvCity = str;
    }

    public void setTvCountry(String str) {
        this.tvCountry = str;
    }

    public void setTvProvince(String str) {
        this.tvProvince = str;
    }
}
