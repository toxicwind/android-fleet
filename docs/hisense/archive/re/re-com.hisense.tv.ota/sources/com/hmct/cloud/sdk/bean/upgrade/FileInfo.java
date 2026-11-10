package com.hmct.cloud.sdk.bean.upgrade;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class FileInfo implements Serializable {
    private static final long serialVersionUID = 2520139463309007080L;
    private String checkSum;
    private String downloadUrl;
    private long fakeSize;
    private String fileName;
    private long fileSize;
    private int fileType;
    private String fileVersion;
    private String sha256Sum;

    public String a() {
        return this.checkSum;
    }

    public String b() {
        return this.downloadUrl;
    }

    public long c() {
        return this.fileSize;
    }

    public void d(String str) {
        this.checkSum = str;
    }

    public void e(String str) {
        this.downloadUrl = str;
    }

    public void f(String str) {
        this.fileName = str;
    }

    public void g(long j) {
        this.fileSize = j;
    }

    public void h(int i) {
        this.fileType = i;
    }

    public void i(String str) {
        this.fileVersion = str;
    }

    public void j(String str) {
        this.sha256Sum = str;
    }
}
