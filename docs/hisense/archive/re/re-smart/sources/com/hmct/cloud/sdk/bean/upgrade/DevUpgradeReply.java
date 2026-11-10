package com.hmct.cloud.sdk.bean.upgrade;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DevUpgradeReply extends ReplyInfo {
    public static final long serialVersionUID = -8608708547035026764L;
    public String areaId;
    public String areaName;
    public String dppChkSum;
    public String dppSHA256Sum;
    public String dppUrl;
    public String eulaChkSum;
    public String eulaSHA256Sum;
    public String eulaUrl;
    public List<FileInfo> fileList;
    public int fileNum;
    public String latestVersion;
    public int packageType = 0;
    public String releaseTime;
    public int ruleId;
    public int updateFlag;
    public int updateType;
    public String versionDesc;

    public String getAreaId() {
        return this.areaId;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public String getDppChkSum() {
        return this.dppChkSum;
    }

    public String getDppSHA256Sum() {
        return this.dppSHA256Sum;
    }

    public String getDppUrl() {
        return this.dppUrl;
    }

    public String getEulaChkSum() {
        return this.eulaChkSum;
    }

    public String getEulaSHA256Sum() {
        return this.eulaSHA256Sum;
    }

    public String getEulaUrl() {
        return this.eulaUrl;
    }

    public List<FileInfo> getFileList() {
        return this.fileList;
    }

    public int getFileNum() {
        return this.fileNum;
    }

    public String getLatestVersion() {
        return this.latestVersion;
    }

    public int getPackageType() {
        return this.packageType;
    }

    public String getReleaseTime() {
        return this.releaseTime;
    }

    public int getRuleId() {
        return this.ruleId;
    }

    public int getUpdateFlag() {
        return this.updateFlag;
    }

    public int getUpdateType() {
        return this.updateType;
    }

    public String getVersionDesc() {
        return this.versionDesc;
    }

    public void setAreaId(String str) {
        this.areaId = str;
    }

    public void setAreaName(String str) {
        this.areaName = str;
    }

    public void setDppChkSum(String str) {
        this.dppChkSum = str;
    }

    public void setDppSHA256Sum(String str) {
        this.dppSHA256Sum = str;
    }

    public void setDppUrl(String str) {
        this.dppUrl = str;
    }

    public void setEulaChkSum(String str) {
        this.eulaChkSum = str;
    }

    public void setEulaSHA256Sum(String str) {
        this.eulaSHA256Sum = str;
    }

    public void setEulaUrl(String str) {
        this.eulaUrl = str;
    }

    public void setFileList(List<FileInfo> list) {
        this.fileList = list;
    }

    public void setFileNum(int i) {
        this.fileNum = i;
    }

    public void setLatestVersion(String str) {
        this.latestVersion = str;
    }

    public void setPackageType(int i) {
        this.packageType = i;
    }

    public void setReleaseTime(String str) {
        this.releaseTime = str;
    }

    public void setRuleId(int i) {
        this.ruleId = i;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }

    public void setUpdateType(int i) {
        this.updateType = i;
    }

    public void setVersionDesc(String str) {
        this.versionDesc = str;
    }
}
