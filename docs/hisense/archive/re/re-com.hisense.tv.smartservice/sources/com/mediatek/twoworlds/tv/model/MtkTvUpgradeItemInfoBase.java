package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvUpgradeItemInfoBase {
    public String mCheckSum;
    public String mDevPath;
    public String mFileName;
    public MtkTvUpgradeMethodBase mMethod;
    public String mName;
    public long mOffset;
    public long mSize;
    public String mTag;
    public String mVer;

    public MtkTvUpgradeItemInfoBase() {
        this.mTag = null;
        this.mDevPath = null;
        this.mMethod = null;
        this.mName = null;
        this.mFileName = null;
        this.mVer = null;
        this.mCheckSum = null;
        this.mSize = 0L;
        this.mOffset = 0L;
    }

    public String getCheckSum() {
        return this.mCheckSum;
    }

    public String getDevicePath() {
        return this.mDevPath;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public MtkTvUpgradeMethodBase getMethod() {
        return this.mMethod;
    }

    public String getName() {
        return this.mName;
    }

    public long getOffset() {
        return this.mOffset;
    }

    public long getSize() {
        return this.mSize;
    }

    public String getTag() {
        return this.mTag;
    }

    public String getVersion() {
        return this.mVer;
    }

    public void setCheckSum(String str) {
        this.mCheckSum = str;
    }

    public void setDevicePath(String str) {
        this.mDevPath = str;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public void setMethod(MtkTvUpgradeMethodBase mtkTvUpgradeMethodBase) {
        this.mMethod = mtkTvUpgradeMethodBase;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setOffset(long j) {
        this.mOffset = j;
    }

    public void setSize(long j) {
        this.mSize = j;
    }

    public void setTag(String str) {
        this.mTag = str;
    }

    public void setVersion(String str) {
        this.mVer = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("\nUpgradeItemInfo\n\tTag: ");
        String str = this.mTag;
        if (str == null) {
            str = "";
        }
        sbA.append(str);
        sbA.append("\n\tDevPath: ");
        String str2 = this.mDevPath;
        if (str2 == null) {
            str2 = "";
        }
        sbA.append(str2);
        sbA.append("\n\tMethod: ");
        sbA.append(this.mMethod);
        sbA.append("\n\tName: ");
        String str3 = this.mName;
        if (str3 == null) {
            str3 = "";
        }
        sbA.append(str3);
        sbA.append("\n\tFileName: ");
        String str4 = this.mFileName;
        if (str4 == null) {
            str4 = "";
        }
        sbA.append(str4);
        sbA.append("\n\tVersion: ");
        String str5 = this.mVer;
        if (str5 == null) {
            str5 = "";
        }
        sbA.append(str5);
        sbA.append("\n\tCheckSum: ");
        String str6 = this.mCheckSum;
        if (str6 == null) {
            str6 = "";
        }
        sbA.append(str6);
        sbA.append("\n\tSize: ");
        sbA.append(this.mSize);
        sbA.append("\n\tOffset: ");
        sbA.append(this.mOffset);
        return sbA.toString();
    }

    public MtkTvUpgradeItemInfoBase(String str, String str2, MtkTvUpgradeMethodBase mtkTvUpgradeMethodBase, String str3, String str4, String str5, String str6, long j, long j2) {
        this.mTag = str;
        this.mDevPath = str2;
        this.mMethod = mtkTvUpgradeMethodBase;
        this.mName = str3;
        this.mFileName = str4;
        this.mVer = str5;
        this.mCheckSum = str6;
        this.mSize = j;
        this.mOffset = j2;
    }
}
