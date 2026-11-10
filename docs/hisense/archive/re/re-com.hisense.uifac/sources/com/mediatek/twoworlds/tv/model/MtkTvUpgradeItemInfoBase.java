package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvUpgradeItemInfoBase {
    private String mCheckSum;
    private String mDevPath;
    private String mFileName;
    private MtkTvUpgradeMethodBase mMethod;
    private String mName;
    private long mOffset;
    private long mSize;
    private String mTag;
    private String mVer;

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

    public String getTag() {
        return this.mTag;
    }

    public void setTag(String str) {
        this.mTag = str;
    }

    public String getDevicePath() {
        return this.mDevPath;
    }

    public void setDevicePath(String str) {
        this.mDevPath = str;
    }

    public MtkTvUpgradeMethodBase getMethod() {
        return this.mMethod;
    }

    public void setMethod(MtkTvUpgradeMethodBase mtkTvUpgradeMethodBase) {
        this.mMethod = mtkTvUpgradeMethodBase;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public String getVersion() {
        return this.mVer;
    }

    public void setVersion(String str) {
        this.mVer = str;
    }

    public String getCheckSum() {
        return this.mCheckSum;
    }

    public void setCheckSum(String str) {
        this.mCheckSum = str;
    }

    public long getSize() {
        return this.mSize;
    }

    public void setSize(long j) {
        this.mSize = j;
    }

    public long getOffset() {
        return this.mOffset;
    }

    public void setOffset(long j) {
        this.mOffset = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUpgradeItemInfo\n\tTag: ");
        String str = this.mTag;
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("\n\tDevPath: ");
        String str2 = this.mDevPath;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append("\n\tMethod: ");
        sb.append(this.mMethod);
        sb.append("\n\tName: ");
        String str3 = this.mName;
        if (str3 == null) {
            str3 = "";
        }
        sb.append(str3);
        sb.append("\n\tFileName: ");
        String str4 = this.mFileName;
        if (str4 == null) {
            str4 = "";
        }
        sb.append(str4);
        sb.append("\n\tVersion: ");
        String str5 = this.mVer;
        if (str5 == null) {
            str5 = "";
        }
        sb.append(str5);
        sb.append("\n\tCheckSum: ");
        String str6 = this.mCheckSum;
        sb.append(str6 != null ? str6 : "");
        sb.append("\n\tSize: ");
        sb.append(this.mSize);
        sb.append("\n\tOffset: ");
        sb.append(this.mOffset);
        return sb.toString();
    }
}
