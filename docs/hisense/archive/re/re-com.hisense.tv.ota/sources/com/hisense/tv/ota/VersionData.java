package com.hisense.tv.ota;

import android.text.TextUtils;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class VersionData implements Serializable {
    private static final long serialVersionUID = 1;
    private String downloadUrl;
    private String eulaLocalPath;
    private String eulaUrl;
    private long fileSize;
    private boolean isForced;
    private boolean isFte;
    private boolean isSlient;
    private String md5Value;
    private String newVersion;
    private String oldVersion;
    private String upgradeInfo;

    public VersionData() {
    }

    public VersionData(String str, String str2, String str3, String str4, String str5, long j, String str6, boolean z, boolean z2, boolean z3) {
        this.oldVersion = str;
        this.newVersion = str2;
        this.downloadUrl = str3;
        this.upgradeInfo = str4;
        this.eulaUrl = str5;
        this.fileSize = j;
        this.md5Value = str6;
        this.isForced = z;
        this.isSlient = z2;
        this.isFte = z3;
    }

    public String a() {
        return this.downloadUrl;
    }

    public String b() {
        return this.eulaLocalPath;
    }

    public String c() {
        return this.eulaUrl;
    }

    public long d() {
        return this.fileSize;
    }

    public String e() {
        return this.newVersion;
    }

    public String f() {
        return this.oldVersion;
    }

    public String g() {
        return this.upgradeInfo;
    }

    public String h() {
        return this.md5Value;
    }

    public boolean i() {
        return this.isForced;
    }

    public boolean j() {
        return this.isFte;
    }

    public boolean k() {
        return this.isSlient;
    }

    public void l(String str) {
        this.eulaLocalPath = str;
    }

    public void m(String str) {
        this.oldVersion = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("\n");
        stringBuffer.append(String.format("old version : %s\n", this.oldVersion));
        stringBuffer.append(String.format("new version : %s\n", this.newVersion));
        stringBuffer.append(String.format("is forced   : %b\n", Boolean.valueOf(this.isForced)));
        stringBuffer.append(String.format("is slient   : %b\n", Boolean.valueOf(this.isSlient)));
        stringBuffer.append(String.format("is ftemode   : %b\n", Boolean.valueOf(this.isFte)));
        stringBuffer.append(String.format("size        : %d\n", Long.valueOf(this.fileSize)));
        stringBuffer.append(String.format("md5        : %s\n", this.md5Value));
        stringBuffer.append(String.format("has eula    : %b\n", Boolean.valueOf(!TextUtils.isEmpty(this.eulaUrl))));
        return stringBuffer.toString();
    }
}
