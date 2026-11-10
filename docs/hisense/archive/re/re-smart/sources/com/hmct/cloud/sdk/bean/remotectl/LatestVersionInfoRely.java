package com.hmct.cloud.sdk.bean.remotectl;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;

/* JADX INFO: loaded from: classes2.dex */
public class LatestVersionInfoRely extends ReplyInfo {
    public static final long serialVersionUID = -815639351039217649L;
    public String latestVersion;
    public String md5sum;
    public String resourceFile;
    public String upgradeFlag;

    public String getLatestVersion() {
        return this.latestVersion;
    }

    public String getMd5sum() {
        return this.md5sum;
    }

    public String getResourceFile() {
        return this.resourceFile;
    }

    public String getUpgradeFlag() {
        return this.upgradeFlag;
    }

    public void setLatestVersion(String str) {
        this.latestVersion = str;
    }

    public void setMd5sum(String str) {
        this.md5sum = str;
    }

    public void setResourceFile(String str) {
        this.resourceFile = str;
    }

    public void setUpgradeFlag(String str) {
        this.upgradeFlag = str;
    }
}
