package com.hmct.cloud.sdk.bean.upgrade;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class FileInfo implements Serializable {
    public static final long serialVersionUID = 2520139463309007080L;
    public String checkSum;
    public String downloadUrl;
    public long fakeSize;
    public String fileName;
    public long fileSize;
    public int fileType;
    public String fileVersion;
    public String sha256Sum;

    public String getCheckSum() {
        return this.checkSum;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public long getFakeSize() {
        return this.fakeSize;
    }

    public String getFileName() {
        return this.fileName;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public int getFileType() {
        return this.fileType;
    }

    public String getFileVersion() {
        return this.fileVersion;
    }

    public String getSha256Sum() {
        return this.sha256Sum;
    }

    public void setCheckSum(String str) {
        this.checkSum = str;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setFakeSize(long j) {
        this.fakeSize = j;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    public void setFileVersion(String str) {
        this.fileVersion = str;
    }

    public void setSha256Sum(String str) {
        this.sha256Sum = str;
    }
}
