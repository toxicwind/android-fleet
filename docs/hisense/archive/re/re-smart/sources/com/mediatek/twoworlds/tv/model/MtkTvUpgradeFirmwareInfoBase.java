package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvUpgradeFirmwareInfoBase {
    public String mBuildDate;
    public String mBuildName;
    public String mHwVer;
    public String mIcVer;
    public String mManufacturer;
    public String mModel;
    public String mSerialNum;
    public ArrayList<MtkTvUpgradeItemInfoBase> mUpgradeItemList;

    public MtkTvUpgradeFirmwareInfoBase() {
        this.mManufacturer = null;
        this.mModel = null;
        this.mIcVer = null;
        this.mHwVer = null;
        this.mBuildName = null;
        this.mBuildDate = null;
        this.mSerialNum = null;
        this.mUpgradeItemList = new ArrayList<>();
    }

    public String getBuildDate() {
        return this.mBuildDate;
    }

    public String getBuildName() {
        return this.mBuildName;
    }

    public String getHwVersion() {
        return this.mHwVer;
    }

    public String getIcVersion() {
        return this.mIcVer;
    }

    public String getManufacturerName() {
        return this.mManufacturer;
    }

    public String getModelName() {
        return this.mModel;
    }

    public String getSerialNumber() {
        return this.mSerialNum;
    }

    public ArrayList<MtkTvUpgradeItemInfoBase> getUpgradeItemList() {
        return this.mUpgradeItemList;
    }

    public void setBuildDate(String str) {
        this.mBuildDate = str;
    }

    public void setBuildName(String str) {
        this.mBuildName = str;
    }

    public void setHwVersion(String str) {
        this.mHwVer = str;
    }

    public void setIcVersion(String str) {
        this.mIcVer = str;
    }

    public void setManufacturerName(String str) {
        this.mManufacturer = str;
    }

    public void setModelName(String str) {
        this.mModel = str;
    }

    public void setSerialNumber(String str) {
        this.mSerialNum = str;
    }

    public void setUpgradeItemList(ArrayList<MtkTvUpgradeItemInfoBase> arrayList) {
        this.mUpgradeItemList = arrayList;
    }

    public String toSting() {
        StringBuilder sbA = a.a("\nUpgradeFirmwareInfo\n\t Manufactuer: ");
        String str = this.mManufacturer;
        if (str == null) {
            str = "";
        }
        sbA.append(str);
        sbA.append("\n\t Model: ");
        String str2 = this.mModel;
        if (str2 == null) {
            str2 = "";
        }
        sbA.append(str2);
        sbA.append("\n\t IC Version: ");
        String str3 = this.mIcVer;
        if (str3 == null) {
            str3 = "";
        }
        sbA.append(str3);
        sbA.append("\n\t Hardware Version: ");
        String str4 = this.mHwVer;
        if (str4 == null) {
            str4 = "";
        }
        sbA.append(str4);
        sbA.append("\n\t Build Name: ");
        String str5 = this.mBuildName;
        if (str5 == null) {
            str5 = "";
        }
        sbA.append(str5);
        sbA.append("\n\t Build Date: ");
        String str6 = this.mBuildDate;
        if (str6 == null) {
            str6 = "";
        }
        sbA.append(str6);
        sbA.append("\n\t Serial Number: ");
        String str7 = this.mSerialNum;
        if (str7 == null) {
            str7 = "";
        }
        sbA.append(str7);
        String string = sbA.toString();
        for (MtkTvUpgradeItemInfoBase mtkTvUpgradeItemInfoBase : this.mUpgradeItemList) {
            StringBuilder sbA2 = a.a(string);
            sbA2.append(mtkTvUpgradeItemInfoBase.toString());
            string = sbA2.toString();
        }
        return string;
    }

    public MtkTvUpgradeFirmwareInfoBase(String str, String str2, String str3, String str4, String str5, String str6, String str7, ArrayList<MtkTvUpgradeItemInfoBase> arrayList) {
        this.mManufacturer = str;
        this.mModel = str2;
        this.mIcVer = str3;
        this.mHwVer = str4;
        this.mBuildName = str5;
        this.mBuildDate = str6;
        this.mSerialNum = str7;
        this.mUpgradeItemList = arrayList;
    }
}
