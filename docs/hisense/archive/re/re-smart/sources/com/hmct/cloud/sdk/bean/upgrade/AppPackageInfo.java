package com.hmct.cloud.sdk.bean.upgrade;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class AppPackageInfo implements Serializable {
    public static final long serialVersionUID = -7277321785820405337L;
    public long appId;
    public String checkSum;
    public String downloadUrl;
    public long fileSize;
    public String packageName;
    public int updateFlag;
    public String version;
    public String versionDesc;
    public String versionName;

    public long getAppId() {
        return this.appId;
    }

    public String getCheckSum() {
        return this.checkSum;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int getUpdateFlag() {
        return this.updateFlag;
    }

    public String getVersion() {
        return this.version;
    }

    public String getVersionDesc() {
        return this.versionDesc;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setAppId(long j) {
        this.appId = j;
    }

    public void setCheckSum(String str) {
        this.checkSum = str;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setVersionDesc(String str) {
        this.versionDesc = str;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }
}
