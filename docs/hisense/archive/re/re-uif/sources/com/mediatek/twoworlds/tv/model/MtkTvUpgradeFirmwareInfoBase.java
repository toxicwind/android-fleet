package com.mediatek.twoworlds.tv.model;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvUpgradeFirmwareInfoBase {
    private String mBuildDate;
    private String mBuildName;
    private String mHwVer;
    private String mIcVer;
    private String mManufacturer;
    private String mModel;
    private String mSerialNum;
    ArrayList<MtkTvUpgradeItemInfoBase> mUpgradeItemList;

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

    public String getManufacturerName() {
        return this.mManufacturer;
    }

    public void setManufacturerName(String str) {
        this.mManufacturer = str;
    }

    public String getModelName() {
        return this.mModel;
    }

    public void setModelName(String str) {
        this.mModel = str;
    }

    public String getIcVersion() {
        return this.mIcVer;
    }

    public void setIcVersion(String str) {
        this.mIcVer = str;
    }

    public String getHwVersion() {
        return this.mHwVer;
    }

    public void setHwVersion(String str) {
        this.mHwVer = str;
    }

    public String getBuildName() {
        return this.mBuildName;
    }

    public void setBuildName(String str) {
        this.mBuildName = str;
    }

    public String getBuildDate() {
        return this.mBuildDate;
    }

    public void setBuildDate(String str) {
        this.mBuildDate = str;
    }

    public String getSerialNumber() {
        return this.mSerialNum;
    }

    public void setSerialNumber(String str) {
        this.mSerialNum = str;
    }

    public ArrayList<MtkTvUpgradeItemInfoBase> getUpgradeItemList() {
        return this.mUpgradeItemList;
    }

    public void setUpgradeItemList(ArrayList<MtkTvUpgradeItemInfoBase> arrayList) {
        this.mUpgradeItemList = arrayList;
    }

    public String toSting() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUpgradeFirmwareInfo\n\t Manufactuer: ");
        String str = this.mManufacturer;
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("\n\t Model: ");
        String str2 = this.mModel;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append("\n\t IC Version: ");
        String str3 = this.mIcVer;
        if (str3 == null) {
            str3 = "";
        }
        sb.append(str3);
        sb.append("\n\t Hardware Version: ");
        String str4 = this.mHwVer;
        if (str4 == null) {
            str4 = "";
        }
        sb.append(str4);
        sb.append("\n\t Build Name: ");
        String str5 = this.mBuildName;
        if (str5 == null) {
            str5 = "";
        }
        sb.append(str5);
        sb.append("\n\t Build Date: ");
        String str6 = this.mBuildDate;
        if (str6 == null) {
            str6 = "";
        }
        sb.append(str6);
        sb.append("\n\t Serial Number: ");
        String str7 = this.mSerialNum;
        sb.append(str7 != null ? str7 : "");
        String string = sb.toString();
        Iterator<MtkTvUpgradeItemInfoBase> it = this.mUpgradeItemList.iterator();
        while (it.hasNext()) {
            string = string + it.next().toString();
        }
        return string;
    }
}
