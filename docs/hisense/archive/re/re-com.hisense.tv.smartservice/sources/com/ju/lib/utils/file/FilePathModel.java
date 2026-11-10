package com.ju.lib.utils.file;

import b.a.a.a.a;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class FilePathModel implements Serializable {
    public static final long serialVersionUID = -4738251103452577675L;
    public String mName;
    public FilePathConstants.Type mPathType;
    public String mUrl;

    public FilePathModel(String str, String str2, FilePathConstants.Type type) {
        this.mUrl = str;
        this.mName = str2;
        this.mPathType = type;
    }

    public String getmName() {
        return this.mName;
    }

    public FilePathConstants.Type getmPathType() {
        return this.mPathType;
    }

    public String getmUrl() {
        return this.mUrl;
    }

    public void setmPathType(FilePathConstants.Type type) {
        this.mPathType = type;
    }

    public String toString() {
        StringBuilder sbA = a.a("FilePathModel [mUrl=");
        sbA.append(this.mUrl);
        sbA.append(", mName=");
        sbA.append(this.mName);
        sbA.append(", mPathType=");
        return a.a(sbA, this.mPathType, "]");
    }
}
