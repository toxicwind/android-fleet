package com.hmct.cloud.sdk.bean.upgrade;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UpgradePolicyInfo implements Serializable {
    public static final long serialVersionUID = 1993803749936481347L;
    public String autoUpgradeEnd;
    public String autoUpgradeStart;
    public int checkFrequence;
    public String displayVersion;
    public List<FileInfo> fileList;
    public String latestVersion;
    public int maxDownloadTimes;
    public int newCheckFrequence;
    public int packageType = 0;
    public String releaseTime;
    public int screenCloseTime;
    public int updateType;
    public String upgradePolicyDesc;
    public String versionDesc;
    public int wifiDownload;

    public String getAutoUpgradeEnd() {
        return this.autoUpgradeEnd;
    }

    public String getAutoUpgradeStart() {
        return this.autoUpgradeStart;
    }

    public int getCheckFrequence() {
        return this.checkFrequence;
    }

    public String getDisplayVersion() {
        return this.displayVersion;
    }

    public List<FileInfo> getFileList() {
        return this.fileList;
    }

    public String getLatestVersion() {
        return this.latestVersion;
    }

    public int getMaxDownloadTimes() {
        return this.maxDownloadTimes;
    }

    public int getNewCheckFrequence() {
        return this.newCheckFrequence;
    }

    public int getPackageType() {
        return this.packageType;
    }

    public String getReleaseTime() {
        return this.releaseTime;
    }

    public int getScreenCloseTime() {
        return this.screenCloseTime;
    }

    public int getUpdateType() {
        return this.updateType;
    }

    public String getUpgradePolicyDesc() {
        return this.upgradePolicyDesc;
    }

    public String getVersionDesc() {
        return this.versionDesc;
    }

    public int getWifiDownload() {
        return this.wifiDownload;
    }

    public void setAutoUpgradeEnd(String str) {
        this.autoUpgradeEnd = str;
    }

    public void setAutoUpgradeStart(String str) {
        this.autoUpgradeStart = str;
    }

    public void setCheckFrequence(int i) {
        this.checkFrequence = i;
    }

    public void setDisplayVersion(String str) {
        this.displayVersion = str;
    }

    public void setFileList(List<FileInfo> list) {
        this.fileList = list;
    }

    public void setLatestVersion(String str) {
        this.latestVersion = str;
    }

    public void setMaxDownloadTimes(int i) {
        this.maxDownloadTimes = i;
    }

    public void setNewCheckFrequence(int i) {
        this.newCheckFrequence = i;
    }

    public void setPackageType(int i) {
        this.packageType = i;
    }

    public void setReleaseTime(String str) {
        this.releaseTime = str;
    }

    public void setScreenCloseTime(int i) {
        this.screenCloseTime = i;
    }

    public void setUpdateType(int i) {
        this.updateType = i;
    }

    public void setUpgradePolicyDesc(String str) {
        this.upgradePolicyDesc = str;
    }

    public void setVersionDesc(String str) {
        this.versionDesc = str;
    }

    public void setWifiDownload(int i) {
        this.wifiDownload = i;
    }
}
