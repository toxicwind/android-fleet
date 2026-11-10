package com.hmct.cloud.sdk.bean.upgrade;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;

/* JADX INFO: loaded from: classes2.dex */
public class AppUpgradeReply extends ReplyInfo {
    public static final long serialVersionUID = -7398362005492181215L;
    public long appId;
    public String areaId;
    public String areaName;
    public String checkSum;
    public int cleanUpType;
    public String downloadUrl;
    public long fileSize;
    public String latestVersion;
    public String latestVersionName;
    public int logLevel;
    public String remindPolicyInfo;
    public long ruleId;
    public long switches;
    public int updateFlag;
    public String versionDesc;

    public long getAppId() {
        return this.appId;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public String getCheckSum() {
        return this.checkSum;
    }

    public int getCleanUpType() {
        return this.cleanUpType;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getLatestVersion() {
        return this.latestVersion;
    }

    public String getLatestVersionName() {
        return this.latestVersionName;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public String getRemindPolicyInfo() {
        return this.remindPolicyInfo;
    }

    public long getRuleId() {
        return this.ruleId;
    }

    public long getSwitches() {
        return this.switches;
    }

    public int getUpdateFlag() {
        return this.updateFlag;
    }

    public String getVersionDesc() {
        return this.versionDesc;
    }

    public void setAppId(long j) {
        this.appId = j;
    }

    public void setAreaId(String str) {
        this.areaId = str;
    }

    public void setAreaName(String str) {
        this.areaName = str;
    }

    public void setCheckSum(String str) {
        this.checkSum = str;
    }

    public void setCleanUpType(int i) {
        this.cleanUpType = i;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setLatestVersion(String str) {
        this.latestVersion = str;
    }

    public void setLatestVersionName(String str) {
        this.latestVersionName = str;
    }

    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    public void setRemindPolicyInfo(String str) {
        this.remindPolicyInfo = str;
    }

    public void setRuleId(long j) {
        this.ruleId = j;
    }

    public void setSwitches(long j) {
        this.switches = j;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }

    public void setVersionDesc(String str) {
        this.versionDesc = str;
    }
}
